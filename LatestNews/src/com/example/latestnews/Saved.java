package com.example.latestnews;

public class Saved {
	private String headlines;
	private String source;
    private String storyid;
    private long rowid;
	private String pubdate;
	private String link;
	public Saved(){
		
	}
	public Saved(String headlines,long rowid,String storyid,String pubdate,String source,String link){
		super();
	this.headlines=headlines;
	this.source=source;
	this.storyid=storyid;
	this.pubdate=pubdate;
	this.rowid=rowid;
	this.link=link;
	
	}
	public String getheadline(){
		return headlines;
	}
	public void setheadline(String headlines){
		this.headlines=headlines;
	}
	public String getstoryid(){
		return storyid;
	}
	public void setstoryid(String storyid){
		this.storyid=storyid;
	}
	public String getpubdate(){
		return pubdate;
	}
	public void setpubdate(String pubdate){
		this.pubdate=pubdate;
	}
	public String getsource(){
		return source;
	}
	public void setsource(String source){
		this.source=source;
	}
	public long getrowid(){
		return rowid;
	}
	public void setrowid(long rowid){
		this.rowid=rowid;
	}
	public String getlink(){
		return link;
		
	}
	public void setlink(String link){
		this.link=link;
	}
}
