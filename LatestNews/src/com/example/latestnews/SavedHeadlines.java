package com.example.latestnews;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class SavedHeadlines extends Activity {
	static String source;
	static String headline;
	static String pubdate;
	static String storyid;
	static String link;
    ArrayList<Saved> savedList;
    SavedAdapter adapter;
    DBAdapter db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saved_headlines);
		db=new DBAdapter(this);
		
        savedList=new ArrayList<Saved>();
        getAllHeadlines();
        
        adapter=new SavedAdapter(getApplicationContext(),R.layout.saved,savedList);
		ListView lv=(ListView)findViewById(R.id.list);
		lv.setAdapter(adapter);
		
		
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
			source=savedList.get(position).getsource();
			headline=savedList.get(position).getheadline();
			pubdate=savedList.get(position).getpubdate();
			storyid=savedList.get(position).getstoryid();
			link=savedList.get(position).getlink();
			Intent story=new Intent(getApplicationContext(),SavedStory.class);
			startActivity(story);
	
				
				
			}
			
		});
		lv.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				long rowid=savedList.get(position).getrowid();
				
				db.open();
				if (db.deleteHeadlines(rowid)){
					finish();
					Intent nn=new Intent(getApplicationContext(),SavedHeadlines.class);
				    startActivity(nn);
			        return true;
				}
					
				else
					db.close();
					return false;
				
			}
			
		});
		}
	private void getAllHeadlines() {
		
		db.open();
		Cursor c=db.getAllHeadlines();
		if(c.moveToFirst()){
			do{
				Saved saved=new Saved();
				saved.setrowid(c.getLong(0));
				saved.setheadline(c.getString(1));
				saved.setstoryid(c.getString(2));
				saved.setpubdate(c.getString(3));
				saved.setsource(c.getString(4));
				saved.setlink(c.getString(5));
				savedList.add(saved);
			}
			while(c.moveToNext());
			
		}
		db.close();
	
	}
	
	}


