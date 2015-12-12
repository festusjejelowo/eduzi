package com.ocularminds.eduzi;

public class Fault{

   private String error;
   private String fault;
   private Object data;

   public Fault(String error,String fault){
      new Fault(error,fault,new String(""));
   }

   public Fault(String error,String fault,Object data){

      this.error = error;
      this.fault = fault;
      this.data = data;

   }

   public String getError(){
       return error;
   }

   public String getFault(){
	   return fault;
   }

   public Object getData(){
	   return data;
   }

}