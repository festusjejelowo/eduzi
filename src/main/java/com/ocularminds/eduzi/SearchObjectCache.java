package com.ocularminds.eduzi;

import java.util.Date;
import com.ocularminds.eduzi.util.TextUtil;

public class SearchObjectCache implements java.io.Serializable{

  private int id;
  private String url;
  String category;
  private String title;
  String text;
  private Date date;

  public SearchObjectCache(){
    date = new Date();
  }

  public SearchObjectCache(int id,String url,String category,String text,Date date){

  	this.id = id;
  	this.url = url;
  	this.category = category;
  	this.text = text;
  	this.date = date;
  }

  public void setId(int id){
      this.id = id;
  }

  public int getId(){
	  return this.id;
  }

  public void setUrl(String url){
	  this.url = url;
  }

  public String getUrl(){
	  return this.url;
  }

  public void setCategory(String category){
	  this.category = category;
  }

  public String getCategory(){
	  return this.category;
  }

  public void setText(String text){
	  this.text = text;
  }

  public String getText(){
	  return this.text;
  }

  public void setTitle(String title){
	  this.title = title;
  }

  public String getTitle(){
	  return this.title;
  }

  public void setDate(Date date){
	  this.date = date;
  }

  public Date getDate(){
	  return this.date;
  }

  @Override
  public String toString(){
	  return text;
  }

	@Override
	public int hashCode(){
		return (id *17) * text.hashCode();
	}

	@Override
	public boolean equals(Object o){

		if(o == null) return false;
		if(o instanceof SearchObjectCache){
			return (TextUtil.isSimilar(((SearchObjectCache)o).getText(),this.getText()))?true:false;
		}
		return true;
	}
}