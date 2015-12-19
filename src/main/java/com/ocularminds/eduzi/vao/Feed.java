package com.ocularminds.eduzi.vao;

import java.util.Date;

public class Feed implements java.io.Serializable {

	private static final long serialVersionUID = 8881008581492811600L;

	private Long id;

	private String category;

	private Date time;

	private String text;

	private String name;

	private String icon;

	private String image;

	private String url;

	public Feed(Long id,String category,Date time,String text,String name,String icon,String image,String url){

		setId(id);
		setCategory(category);
		setTime(time);
		setText(text);
		setName(name);
		setIcon(icon);
		setImage(image);
		setUrl(url);

	}

	public Feed(){

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode(){
		return (id.intValue()*17) * name.hashCode();
	}

	@Override
	public boolean equals(Object o){

		if(o == null) return false;
		if(o instanceof Feed){
			return ((Feed)o).getId().equals(this.getId());
		}
		return true;
	}

	@Override
	public String toString() {
		return id + "," + time + "," + text + ","+ name + "," + icon + "," + image + "," + url;
	}
}
