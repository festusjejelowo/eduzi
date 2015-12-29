package com.ocularminds.eduzi;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.ocularminds.eduzi.util.TextUtil;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.dmg.pmml.PMML;
//import org.dmg.pmml.ImportFilter;
import org.jpmml.model.ImportFilter;
import org.xml.sax.InputSource;
import javax.xml.transform.sax.SAXSource;
import org.jpmml.model.JAXBUtil;
import org.jpmml.model.visitors.LocatorNullifier;

public class Analyser{

	public static void analyse(List<String> data){

		data.stream().filter(s -> s.startsWith("c"))
		.map(String::toUpperCase).sorted()
		.forEach(System.out::println);

	}

	public static void createModel()throws Exception{

		 String file = "insight.data.txt";
		 ClassLoader cl = Thread.currentThread().getContextClassLoader();
		 InputStream is = cl.getResourceAsStream(file);
		 PMML pmml = load(is);

		 LocatorNullifier nullifier = new LocatorNullifier();
         nullifier.applyTo(pmml);

         OutputStream os = new FileOutputStream(new File("edizi.xml"));

         StreamResult result = new StreamResult(os);
         JAXBUtil.marshalPMML(pmml,result);
	}

	public static PMML load(InputStream is) throws Exception {

	  InputSource source = new InputSource(is);
	  SAXSource transformedSource = ImportFilter.apply(source);

	  return JAXBUtil.unmarshalPMML(transformedSource);
    }

    public fibonacci(){

		IntStream fs = Stream.iterate(
			new int[]{1, 1},
			fib -> new int[] {fib[1], fib[0] + fib[1]}).mapToInt(fib -> fib[0]);
	    fs.limit(10).forEach(fib -> System.out.print(fib + " "));


    public boolean isPrime(long number) {
	  return number > 1 && LongStream.rangeClosed(2,(long) Math.sqrt(number))
	    .parallel().noneMatch(index -> number % index == 0);
    }

    public long factorial(int n) {
	 return LongStream.rangeClosed(1, n).reduce((a, b) -> a * b).getAsLong();
    }

    private static String extractRoute(String s){

		int from = s.toLowerCase().indexOf("from");
		int is = s.toLowerCase().indexOf("is");
		if(is < 0) is = s.toLowerCase().indexOf("being");
		if(is < from)  is = s.toLowerCase().indexOf("being");
		if(is < from)  is = s.toLowerCase().indexOf("as");
		if(from < 0) from = s.toLowerCase().indexOf("at");
		//System.out.println("from "+from+",is "+is);
		return (from > -1 && is >-1)?s.substring(from+5,is):"NA";
	}

	//gather traffic data to include, attribute(slow,heavy,free,win,draw,loose),time and place(route,town,location,home,away)
	public static void reduce(){

		String[] data = {"Traffic,http://www.tsaboin.com, Be the first to tell your friends and colleagues about Tsaboin's LIVE #LagosTraffic Cameras.",
		"Traffic,http://www.tsaboin.com, From Palm Grove to Anthony Isale is Slow This traffic is serious from Stadium and Ojuelegba being slow up to Anthony",
		"Traffic,http://www.tsaboin.com, From Obanikoro to Onipanu is Slow Same situation inward !palmgrove >> !onipanu ..slow traffic all the way",
		"Traffic,http://www.tsaboin.com, From Onipanu to Anthony Isale is Slow Very slow traffic along Ikorodu road inward and outward !anthonyisale",
		"Traffic,http://www.tsaboin.com, From Second Rainbow to Mile 2 Oke is Slow Traffic is on the high along this route ..>> !mile2oke",
		"Traffic,http://www.tsaboin.com, From Anthony Isale to Obanikoro is Slow Slow traffic towards that side",
		"Traffic,http://www.tsaboin.com, This traffic is serious from Stadium and Ojuelegba being slow up to Anthony",
		"Traffic,http://www.tsaboin.com, Same situation inward !palmgrove >> !onipanu ..slow traffic all the way",
		"Traffic,http://www.tsaboin.com, Very slow traffic along Ikorodu road inward and outward !anthonyisale",
		"Slow,http://www.tsaboin.com, From Third Mainland Bridge to Iyana Oworo is Moving fine but slower at the Iyana Oworo as usual.",
		"Slow,http://www.tsaboin.com, From Palm Grove to Anthony Isale is Slow This traffic is serious from Stadium and Ojuelegba being slow up to Anthony",
		"Slow,http://www.tsaboin.com, From Costain to Ijora is Slow Slow movement inward !ijora causeway >> !barracksapapa",
		"Slow,http://www.tsaboin.com, From Obanikoro to Onipanu is Slow Same situation inward !palmgrove >> !onipanu ..slow traffic all the way",
		"Slow,http://www.tsaboin.com, From Onipanu to Anthony Isale is Slow Very slow traffic along Ikorodu road inward and outward !anthonyisale",
		"Slow,http://www.tsaboin.com, From Ojota to Maryland is Slow Slow movement inward !ojotanewgarage >> !marylandindepencetunnel",
		"Slow,http://www.tsaboin.com, From Anthony Isale to Obanikoro is Slow Slow traffic towards that side",
		"Slow,http://www.tsaboin.com, From Obanikoro to Onipanu is Slow Slow movement inward !palmgrove >> !onipanu",
		"Slow,http://www.tsaboin.com, fine but slower at the Iyana Oworo as usual.",
		"Slow,http://www.tsaboin.com, This traffic is serious from Stadium and Ojuelegba being slow up to Anthony",
		"Slow,http://www.tsaboin.com, Same situation inward !palmgrove >> !onipanu ..slow traffic all the way",
		"Slow,http://www.tsaboin.com, Very slow traffic along Ikorodu road inward and outward !anthonyisale",
		"Slow,http://www.tsaboin.com, Slow movement inward !ijora causeway >> !barracksapapa"};

		List<SearchObjectCache> search = new ArrayList<SearchObjectCache>();

		for(int x = 0; x < data.length; x++){

			String[] s = data[x].split(",");
			String id = Integer.toString(x);
			String cat = s[0];
			String text = s[2];
			String source = s[1];
			String route = extractRoute(text);
			java.util.Date dd = new java.util.Date();
		    search.add(new SearchObjectCache(x,source,cat,text,dd,route));

		}

		List<SearchObjectCache> filtered = search.stream()
		        .filter(p -> p.getUrl().contains("tsaboin"))
		        .collect(Collectors.toList());

       // filtered.forEach(p ->System.out.println(p.getCategory()+" "+extractRoute(p.getText())+" "+p.getText()));

		//The search groups all SearchObjectCache by category:
		Map<String, List<SearchObjectCache>> byCategory = search.stream().collect(Collectors.groupingBy(o -> o.location));
		byCategory.forEach((cat,o) -> System.out.format("location %s: %s\n", cat,o));

		final Map<String,Map<String,Long>> byRoute = search.stream().collect(
                Collectors.groupingBy(SearchObjectCache::getCategory,Collectors.groupingBy(SearchObjectCache::getLocation, HashMap::new, Collectors.counting())));
       // bycategory.forEach((cat,i,o) -> System.out.format("%s\t| %s\t| %d\n", cat,i,o));

       System.out.println("\n"+TextUtil.format("Category",15)+" | "+TextUtil.format("Route",45)+" | Traffic");
       System.out.println("---------------------------------------------------------------------------------------");
       byRoute.forEach((m,c) -> c.forEach((m2,c2) -> System.out.println(TextUtil.format(m,15)+" | "+TextUtil.format(m2,45)+" | "+c2)));

	  // System.out.println("\nObjectSummary by category");
	  // Stream categorySummary = search.stream().collect(Collectors.sumarizingObject(p -> p.category));
      // System.out.println(categorySummary);

        List<String> al = Arrays.asList(new String[] {
		      "This sample is by Steve from doublecloud.org, a leading ",
		      "technical blog on virtualization, cloud computing, and ",
		      "software architecture." });

		//int total = al.parallelStream().mapToInt(e -> e.split(" ").length).sum();
        //System.out.println("Total words:" + total+"\n");

        final TreeMap<String, Long> bc = search.stream()
        .collect(Collectors.groupingBy(SearchObjectCache::getLocation, TreeMap::new, Collectors.counting()));
		bc.forEach((c, count) ->System.out.println(TextUtil.format(c,45)+" | "+count));

		/*
		 groupingBy with multiple Collectors

		Map<Integer, Data> result = search.stream().collect(
		        groupingBy(SearchObjectCache::getCategory,collectingAndThen(summarizingDouble(SearchObjectCache::getAge), dss -> new Data((long)dss.getAverage(), (long)dss.getSum()))));

        //Java 8 on multiple fields with aggregations [duplicate]
        You should create the custom key for your map. The simplest way is to use Arrays.asList:
        In this case the keys are lists of 5 elements in fixed order. Not quite object-oriented, but simple.
        Alternatively you can define your own type which represents the custom key and create proper hashCode/equals implementations.


        Function<WebRecord, List<Object>> keyExtractor = wr ->
		    Arrays.<Object>asList(wr.getFiveMinuteWindow(), wr.getCdn(), wr.getIsp(),wr.getResultCode(), wr.getTxnTime());

		Map<List<Object>, Integer> aggregatedData = webRecords.stream().collect(
        Collectors.groupingBy(keyExtractor, Collectors.summingInt(WebRecord::getReqBytes)));


        groupingBy(Pojo::getMajorCategory, groupingBy(Pojo::getMinorCategory))
      */
	}

	public static void order(){

		Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };
		List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));

		System.out.println("listOfIntegers:");
		listOfIntegers.stream().forEach(e -> System.out.print(e + " "));
		System.out.println("");

		System.out.println("listOfIntegers sorted in reverse order:");
		Comparator<Integer> normal = Integer::compare;
		Comparator<Integer> reversed = normal.reversed();
		Collections.sort(listOfIntegers, reversed);

		listOfIntegers.stream().forEach(e -> System.out.print(e + " "));
		System.out.println("");

		System.out.println("Parallel stream");
		listOfIntegers.parallelStream().forEach(e -> System.out.print(e + " "));
		System.out.println("");

		System.out.println("Another parallel stream:");
		listOfIntegers.parallelStream().forEach(e -> System.out.print(e + " "));
		System.out.println("");

		System.out.println("With forEachOrdered:");
		listOfIntegers.parallelStream().forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        //Cities with customers doing expensive transactions
        /*java.util.Set<String> cities = transactions.stream()
                .filter(t -> t.getValue() > 1000)
                .map(Transaction::getCity)
                .collect(toSet());*/
	}

	//java -cp target\eduzi-core-1.0.jar;target\dependency\* com.ocularminds.eduzi.Analyser
	public static void main(String[] args)throws Exception{

		//java  -XX:+UseG1GC -XX:+UseStringDeduplication -cp .;target\eduzi-core-1.0.jar;target\dependency\* com.ocularminds.eduzi.Analyser
		//java  -XX:+UseG1GC -XX:+UseStringDeduplication -cp .;target\eduzi-core-1.0.jar;target\dependency\* com.ocularminds.eduzi.WebService

       //List<String> data = Arrays.asList("a1", "a2", "b1", "c2", "c1");
       //Analyser.analyse(data);
       //IntStream.range(1, 5).forEach(System.out::println);

       Analyser.reduce();
       //Analyser.order();
      // Analyser.createModel();
	}

}