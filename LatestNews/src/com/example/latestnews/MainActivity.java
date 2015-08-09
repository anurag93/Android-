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
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity  {
	ArrayList<Channels> channelList;
	ChannelAdapter adapterch;
	static String name;
	static String title;
	static String addurl;
	static String images;
	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		channelList=new ArrayList<Channels>();
		new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=cnn,abcnews,timesofindia,forbes,cricinfo,huffingtonpost,hindunational,reuters,cnnibnmovies,nyt,bonappetit,thedailytvl,thenextweb,gigaom,tmz,rollingstone,hreporter,uefa");
		GridView gv =(GridView)findViewById(R.id.gridView);
		adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
		gv.setAdapter(adapterch);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				name=channelList.get(position).getFeed();
				images=channelList.get(position).getImage1();
				title=channelList.get(position).getName();
				Intent i=new Intent(getApplicationContext(),HeadlineStory.class);
				startActivity(i);
			}		
			});
		actionBar=getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("Home");
		actionBar.setIcon(R.drawable.images1);
		
		
        
    }
	
	
	
	
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.voicecontrol, menu);
		return super.onCreateOptionsMenu(menu);
	}
	 

	@SuppressLint("CutPasteId")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.Voice:
			Voice();
			return true;
		case R.id.home:
			channelList=new ArrayList<Channels>();
			new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=cnn,abcnews,timesofindia,forbes,cricinfo,huffingtonpost,hindunational,reuters,cnnibnmovies,nyt,bonappetit,thedailytvl,thenextweb,gigaom,tmz,rollingstone,hreporter,uefa");
			GridView gvhome =(GridView)findViewById(R.id.gridView);
			adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
			gvhome.setAdapter(adapterch);
			actionBar.setTitle("Home");
			actionBar.setIcon(R.drawable.images1);
			return true;
		case R.id.news:
			channelList=new ArrayList<Channels>();
			new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=abcnews,gawker,mercurynews,bbc");
			GridView gvnews =(GridView)findViewById(R.id.gridView);
			adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
			gvnews.setAdapter(adapterch);
			actionBar.setTitle("News");
			actionBar.setIcon(R.drawable.news);
			return true;
			
		case R.id.biz:
			channelList=new ArrayList<Channels>();
			new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=marketwatch,cnnmoney");
			GridView gvbiz =(GridView)findViewById(R.id.gridView);
			adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
			gvbiz.setAdapter(adapterch);
			actionBar.setTitle("Business");
			actionBar.setIcon(R.drawable.business);
			return true;
		case R.id.tech:
			channelList=new ArrayList<Channels>();
			new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=9to5mac,cnet,wired,gizmodo,zdnet,venturebeat,mashable");
			GridView gvtech =(GridView)findViewById(R.id.gridView);
			adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
			gvtech.setAdapter(adapterch);
			actionBar.setTitle("Technology");
			actionBar.setIcon(R.drawable.technology);
			return true;
		case R.id.sci:
			channelList=new ArrayList<Channels>();
			new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=cnntech,space");
			GridView gvsci =(GridView)findViewById(R.id.gridView);
			adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
			gvsci.setAdapter(adapterch);
			actionBar.setTitle("Science");
			actionBar.setIcon(R.drawable.science);
			return true;
		case R.id.spo:
			channelList=new ArrayList<Channels>();
			new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=espn,nfl,nhl,mlb,csnbayarea,yahoosports,csnnewengland");
			GridView gvspo =(GridView)findViewById(R.id.gridView);
			adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
			gvspo.setAdapter(adapterch);
			actionBar.setTitle("Sports");
			actionBar.setIcon(R.drawable.sports);
			return true;
		case R.id.ent:
			channelList=new ArrayList<Channels>();
			new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=eweekly,bollywoodlife,enews,ndtvmovies,people");
			GridView gvent =(GridView)findViewById(R.id.gridView);
			adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
			gvent.setAdapter(adapterch);
			actionBar.setTitle("Entertainment");
			actionBar.setIcon(R.drawable.entertainment);
			return true;
		case R.id.art:
			channelList=new ArrayList<Channels>();
			new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=bbcculture,cnnliving");
			GridView gvart =(GridView)findViewById(R.id.gridView);
			adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
			gvart.setAdapter(adapterch);
			actionBar.setTitle("Art/Lifestyle");
			actionBar.setIcon(R.drawable.art);
			return true;
		case R.id.fnd:
			channelList=new ArrayList<Channels>();
			new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=sanjosemercury,sfgate");
			GridView gvfnd =(GridView)findViewById(R.id.gridView);
			adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
			gvfnd.setAdapter(adapterch);
			actionBar.setTitle("Food/Dining");
			actionBar.setIcon(R.drawable.food);
			return true;
		case R.id.travel:
			channelList=new ArrayList<Channels>();
			new JSONAsyncTask().execute("http://ec2-50-17-146-176.compute-1.amazonaws.com:8080/ethrweb-v1/ethr/topic/getTopicMetaData?topics=cnntravel,bbctravel");
			GridView gvtravel =(GridView)findViewById(R.id.gridView);
			adapterch=new ChannelAdapter(getApplicationContext(),R.layout.channels,channelList);
			gvtravel.setAdapter(adapterch);
			actionBar.setTitle("Travel");
			actionBar.setIcon(R.drawable.travel);
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	public void Voice(){
		Intent voice=new Intent(getApplicationContext(),VoiceControl.class);
		startActivity(voice);
	}


	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
			
		  ProgressDialog dialog;
		  @Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new ProgressDialog(MainActivity.this);
				dialog.setMessage("fetching channels");
				dialog.setTitle("Channels");
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
		          for(int i=0;i<jsono.length();i++){
		        	  JSONObject c=jsono.getJSONObject(i);
		        	  Channels ch=new Channels();
		        	  ch.setName(c.getString("name"));
		        	  ch.setDesc(c.getString("desc"));
		        	  ch.setFeed(c.getString("feedId"));
		        	  JSONArray json=c.getJSONArray("startImages");
		        	  ch.setImage1(json.getString(0));
		        	  
		        	  
		        	  channelList.add(ch);
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
			adapterch.notifyDataSetChanged();
			if(result == false)
				Toast.makeText(getApplicationContext(), "Unable to fetch the channels", Toast.LENGTH_LONG).show();

		}
	}


	
}
