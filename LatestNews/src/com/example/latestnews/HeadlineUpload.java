package com.example.latestnews;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class HeadlineUpload extends Activity {
    ArrayList<Headlines> headlineList;
    HeadlineAdapter adapter;
    DBAdapter db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_headline_upload);
		headlineList=HeadlineStory.headlineList;
		adapter=HeadlineStory.adapterch;
		db=new DBAdapter(this);
		ListView lv=(ListView)findViewById(R.id.list);
        lv.setAdapter(adapter);
        
       lv.setOnItemClickListener(new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long id) {
			String headlines=headlineList.get(position).getTitle();
			String pubdate=headlineList.get(position).getpubdate();
			String storyid=headlineList.get(position).getid();
			String source=headlineList.get(position).getsrc();
			String link=headlineList.get(position).getlink();
			db.open();
			if(db.insertHeadlines(headlines, storyid, pubdate, source, link)>=0){
				Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
			}
			
			db.close();
			
			
		}
    	   
       });
      
		
	}

}
