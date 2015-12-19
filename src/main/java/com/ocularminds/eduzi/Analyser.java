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

public class Analyser{

	public static void analyse(List<String> data){

		data.stream().filter(s -> s.startsWith("c"))
		.map(String::toUpperCase).sorted()
		.forEach(System.out::println);

	}

	public static void reduce(){

	    String[] category = {"crime","traffic","slow","kill","death"};
		List<SearchObjectCache> search = new ArrayList<SearchObjectCache>();

		for(int x = 0; x < 28; x++){

			String id = Integer.toString(x);
			String cat = category[((int)(Math.random()*5))];
			String text = cat +" "+id;
			String source = "http://losdsd/.io";
			java.util.Date dd = new java.util.Date();
		    search.add(new SearchObjectCache(x,source,cat,text,dd));

		}

		List<SearchObjectCache> filtered = search.stream()
		        .filter(p -> p.text.contains("4"))
		        .collect(Collectors.toList());

        filtered.forEach(p ->System.out.println(p.getCategory()+" "+p.getText()));

		//The search groups all SearchObjectCache by category:
		Map<String, List<SearchObjectCache>> searchByCategory = search.stream()
		    .collect(Collectors.groupingBy(o -> o.category));

		searchByCategory.forEach((cat,o) -> System.out.format("category %s: %s\n", cat,o));

		final Map<String, Long> bycategory = search.stream().collect(
                Collectors.groupingBy(SearchObjectCache::getCategory, HashMap::new, Collectors.counting()));
       // bycategory.forEach((cat,i,o) -> System.out.format("%s\t| %s\t| %d\n", cat,i,o));

       System.out.println("\nCategory\t\t| Frequency");
       System.out.println("-----------------------------------");
       bycategory.forEach((m,c) -> System.out.println(m+"\t\t| "+c));

		//Stream categorySummary = search.stream().collect(Collectors.summarizingObject(p -> p.category));
       // System.out.println(categorySummary);

        List<String> al = Arrays.asList(new String[] {
		      "This sample is by Steve from doublecloud.org, a leading ",
		      "technical blog on virtualization, cloud computing, and ",
		      "software architecture." });

		int total = al.parallelStream().mapToInt(e -> e.split(" ").length).sum();
        System.out.println("Total words:" + total);

        final TreeMap<String, Long> bc = search.stream()
        .collect(Collectors.groupingBy(SearchObjectCache::getCategory, TreeMap::new, Collectors.counting()));
		bc.forEach((c, count) ->System.out.println(c + "\t\t:" + count));

		/*
		 groupingBy with multiple Collectors

		Map<Integer, Data> result = persons.stream().collect(
		        groupingBy(Person::getGroup,
		                collectingAndThen(summarizingDouble(Person::getAge),
                        dss -> new Data((long)dss.getAverage(), (long)dss.getSum()))));

        //Java 8 on multiple fields with aggregations [duplicate]
        You should create the custom key for your map. The simplest way is to use Arrays.asList:
        In this case the keys are lists of 5 elements in fixed order. Not quite object-oriented, but simple. Alternatively you can define your own type which represents the custom key and create proper hashCode/equals implementations.


        Function<WebRecord, List<Object>> keyExtractor = wr ->
		    Arrays.<Object>asList(wr.getFiveMinuteWindow(), wr.getCdn(), wr.getIsp(),
		             wr.getResultCode(), wr.getTxnTime());
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
		listOfIntegers
		    .stream()
		    .forEach(e -> System.out.print(e + " "));
		System.out.println("");

		System.out.println("Parallel stream");
		listOfIntegers
		    .parallelStream()
		    .forEach(e -> System.out.print(e + " "));
		System.out.println("");

		System.out.println("Another parallel stream:");
		listOfIntegers
		    .parallelStream()
		    .forEach(e -> System.out.print(e + " "));
		System.out.println("");

		System.out.println("With forEachOrdered:");
		listOfIntegers
		    .parallelStream()
		    .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");

        //Cities with customers doing expensive transactions
        /*java.util.Set<String> cities = transactions.stream()
                .filter(t -> t.getValue() > 1000)
                .map(Transaction::getCity)
                .collect(toSet());*/
	}

	//java -cp target\eduzi-core-1.0.jar;target\dependency\* com.ocularminds.eduzi.Analyser
	public static void main(String[] args){

       List<String> data = Arrays.asList("a1", "a2", "b1", "c2", "c1");
       Analyser.analyse(data);
       IntStream.range(1, 5).forEach(System.out::println);

       Analyser.reduce();
       Analyser.order();
	}

}