package com.example.latestnews;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HeadlineStory extends Activity {
	static ArrayList<Headlines> headlineList;
	static HeadlineAdapter adapterch;
	ImageView img;
	ImageLoader imageloader;
	TextView txt;
	TextView txtname;
	static String ID;
	static String Image;
	static String source;
	static String head;
	static String pubdate;
	static String link;
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_headline_story);
		String name=MainActivity.name;
		String images=MainActivity.images;
		String title=MainActivity.title;
		headlineList=new ArrayList<Headlines>();
		new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/story/getTopStories?topics="+name);
		img=(ImageView)findViewById(R.id.imageView1);
		imageloader=new ImageLoader(HeadlineStory.this);
		imageloader.DisplayImage(images,img);
		img.setAdjustViewBounds(true);
		img.setMaxWidth(400);
		img.setMaxHeight(300);
		
	    img.setMinimumWidth(380);
	    txt=(TextView)findViewById(R.id.name);
	    txt.setText(title);
	    txtname=(TextView)findViewById(R.id.nameimage);
	    txtname.setText(title);
	    ListView lv=(ListView)findViewById(R.id.list);
		adapterch=new HeadlineAdapter(getApplicationContext(),R.layout.headlines,headlineList);
		actionBar=getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("Headlines");
		actionBar.setIcon(R.drawable.headlines);
		lv.setAdapter(adapterch);
		
	    lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
								
				ID=headlineList.get(position).getid();
				Image=headlineList.get(position).getimg();
				source=headlineList.get(position).getsrc();
				head=headlineList.get(position).getTitle();
				pubdate=headlineList.get(position).getpubdate();
				link=headlineList.get(position).getlink();
				Intent nn=new Intent(getApplicationContext(),StoryDetail.class);
				startActivity(nn);
				
			  }
			
		});
		
		
		
		
	}
	 

	


	
	
	
	@Override
	   public void onDestroy() {
	    
	 
	      super.onDestroy();
	      finish();
	   }
	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater=getMenuInflater();
	      inflater.inflate(R.menu.upload_menu, menu);
	     
	      return super.onCreateOptionsMenu(menu);
	  }
	 

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.Upload:
			Upload();
			//Toast.makeText(getApplicationContext(), "upload", Toast.LENGTH_LONG).show();
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	public void Upload(){
		Intent upload=new Intent(getApplicationContext(),HeadlineUpload.class);
		startActivity(upload);
	}
	

	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
	  		
    	  ProgressDialog dialog;
    	  @Override
    		protected void onPreExecute() {
    			super.onPreExecute();
    			dialog = new ProgressDialog(HeadlineStory.this);
    			dialog.setMessage("fetching headlines");
    			dialog.setTitle("Headlines");
    			dialog.show();
    			dialog.setCancelable(false);
    		}
    	  protected void onDestroy(){
    		  dialog.cancel();
    		  finish();
    	  }
    		

    	@Override
    	protected Boolean doInBackground(String... urls) {
    		try{
    		HttpGet httppost = new HttpGet(urls[0]);
    		HttpClient httpclient = new DefaultHttpClient();
    		HttpResponse response = httpclient.execute(httppost);

    		// StatusLine stat = response.getStatusLine();
    		int status = response.getStatusLine().getStatusCode();

    		if (status == 200) {
    			HttpEntity entity = response.getEntity();
    			String data = EntityUtils.toString(entity);
    			
    		
    			JSONArray jsono = new JSONArray(data);
                JSONObject c=jsono.getJSONObject(0);
                JSONArray stories=c.getJSONArray("stories");
                for(int i=0;i<stories.length();i++){
                	JSONObject d=stories.getJSONObject(i);
                	Headlines head=new Headlines();
                	head.setsrc(d.getString("src"));
                	head.settitle(d.getString("title"));
                	head.setpubdate(d.getString("pubDate"));
                	head.setid(d.getString("storyId"));
                	head.setlink(d.getString("link"));
                	if(d.optJSONArray("images")==null){
                		head.setimg("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTxQrwv-HIrZfi0Vnz7yt3SHaGbHmCl2MYSSooKOjS4pa6_AURUJA");
                	}
                	else{
                		JSONArray images=d.getJSONArray("images");
                		JSONObject jobj=images.getJSONObject(0);
                		head.setimg(jobj.getString("url"));
                	}
                	headlineList.add(head);
                }
    		return true;
            }
    			
    		
    	
    		}catch (ParseException e1) {
    		e1.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    	
    	protected void onPostExecute(Boolean result) {
    		dialog.cancel();
    		 adapterch.notifyDataSetChanged();
    		if(result == false)
    			Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

    	}
    }


	
	
	

}
