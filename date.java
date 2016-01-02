
public class date{

public static void main(String[] args)throws Exception{

  String ds = "22nd Dec 2015 04:20PM";
  ds = ds.replaceAll("nd","")
  .replaceAll("st","")
  .replaceAll("th","")
  .replaceAll("rd","");

  String pattern = "dd MMM yyyy hh:mma";
  java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
  java.util.Date d = sdf.parse(ds);
  System.out.println(d);
}
}