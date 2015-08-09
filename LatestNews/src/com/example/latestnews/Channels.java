package com.example.latestnews;

public class Channels {
	private String name;
	private String desc;
	private String image1;
	private String feedid;
	
public Channels() {
		// TODO Auto-generated constructor stub
	}
public Channels(String name,String desc,String feedid,String image1){
	super();
	this.name=name;
	this.desc=desc;
	this.image1=image1;
	this.feedid=feedid;
	
	
}
public String getName(){
	return name;
}
public void setName(String name){
	this.name=name;
}
public String getDesc(){
	return desc;
}
public void setDesc(String desc){
	this.desc=desc;
}
public String getImage1(){
	return image1;
}
public void setImage1(String image1){
	this.image1=image1;
}
public String getFeed(){
	return feedid;
}
public void setFeed(String feedid){
	this.feedid=feedid;
}

}
