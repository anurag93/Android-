package com.example.latestnews;

public class Story {
	private String story;


public Story(){
	
}
public Story( String story) {
	super();
	this.story = story;
	
	//this.image3 = image3;
}
public String getStory(){
	return story;
}
public void setStory(String story){
	this.story=story;
}
}