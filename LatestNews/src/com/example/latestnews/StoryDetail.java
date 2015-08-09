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
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
//import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StoryDetail extends Activity  {
	ArrayList<Story> storyList;
	StoryAdapter adapter;
	ImageView img;
	TextView src;
	TextView headline;
	TextView date;
	ImageLoader imageloader;
	ShareActionProvider mShareActionProvider;
	public String link;
	ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_detail);
		imageloader=new ImageLoader(StoryDetail.this);
		String image=HeadlineStory.Image;
		String source=HeadlineStory.source;
		String head=HeadlineStory.head;
		String ID=HeadlineStory.ID;
		String pubdate=HeadlineStory.pubdate;
		link=HeadlineStory.link;
		//TextView tv=(TextView)findViewById(R.id.storydetail);
		storyList=new ArrayList<Story>();
		new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/story/getStoryDetails?storyIds="+ID);
        ListView lv=(ListView)findViewById(R.id.list);
        src=(TextView)findViewById(R.id.source);
        src.setText(source);
        headline=(TextView)findViewById(R.id.head);
        headline.setText(head);
        date=(TextView)findViewById(R.id.pubdate);
        date.setText(pubdate);
        
        
      
        img=(ImageView)findViewById(R.id.imageView1);
        imageloader.DisplayImage(image, img);
        img.setAdjustViewBounds(true);
        img.setMaxWidth(400);
		img.setMaxHeight(225);
		
	    img.setMinimumWidth(380);
	    actionBar=getActionBar();
	    actionBar.setDisplayShowTitleEnabled(true);
	    actionBar.setTitle("Story");
	    actionBar.setIcon(R.drawable.story);
        adapter = new StoryAdapter(getApplicationContext(), R.layout.story, storyList);
	    lv.setAdapter(adapter);
	    
	    
	 
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	 
		// Inflate the menu
		// this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.share, menu);
	 
		// Access the Share Item defined in menu XML
		MenuItem shareItem = menu.findItem(R.id.menu_item_share);
	 
		// Access the object responsible for 
		// putting together the sharing submenu
		if (shareItem != null) {
			mShareActionProvider 
				= (ShareActionProvider) shareItem.getActionProvider();
		}
	 
		setShareIntent();
	 
		return true;
	}
	private void setShareIntent() {
		 
		// create an Intent with the contents of the TextView
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, 
			"Shared via Latest News");
	
		shareIntent.putExtra(Intent.EXTRA_TEXT, link);
 
		// Make sure the provider knows 
		// it should work with that Intent
		mShareActionProvider.setShareIntent(shareIntent);
	}
	
	
	

	
	
	
	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
  		
	  	  ProgressDialog dialog;
	  	  @Override
	  		protected void onPreExecute() {
	  			super.onPreExecute();
	  			dialog = new ProgressDialog(StoryDetail.this);
	  			dialog.setMessage("fetching story");
	  			dialog.setTitle("Story");
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
	            JSONArray para=c.getJSONArray("paras");
	            for(int i=0;i<para.length();i++){
	            	Story str=new Story();
	            	str.setStory(para.getString(i));
	            	storyList.add(str);
	              }
	  		return true;
	          }
	  			
	  		
	  	} catch (ParseException e1) {
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
	  		adapter.notifyDataSetChanged();
	  		if(result == false)
	  			Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

	  	}
	  }
	
		
	}


