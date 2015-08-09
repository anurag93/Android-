package com.example.latestnews;

public class Headlines {
	private String title;
	private String src;
    private String srcid;

	private String pubdate;
	private String image;
    private String link;

	public Headlines() {
		// TODO Auto-generated constructor stub
	}

	public Headlines(String src, String title,
			String srcid,String pubdate,String image,String link) {
		super();
		this.title = title;
		this.src = src;
		this.pubdate = pubdate;
		this.image = image;
		this.srcid=srcid;
		this.link=link;
		//this.image3 = image3;
	}


	public String getTitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}

	
	public String getid(){
		return srcid;
	}
	public void setid(String srcid){
		this.srcid=srcid;
	}
	public String getsrc(){
		return src;
	}
	public void setsrc(String src){
		this.src=src;
	}
	public String getimg(){
		return image;
	}
	public void setimg(String image){
		this.image=image;
	}
	public String getpubdate(){
		return pubdate;
	}
	public void setpubdate(String pubdate){
		this.pubdate=pubdate;
	}
    public void setlink(String link){
    	this.link=link;
    }
    public String getlink(){
    	return link;
    }
	
}