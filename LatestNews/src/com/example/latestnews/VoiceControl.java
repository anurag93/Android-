package com.example.latestnews;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VoiceControl extends Activity {
    ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voice_control);
        actionBar=getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Settings");
        actionBar.setIcon(R.drawable.step0001);
        
	}
	public void saved(View v){
		finish();
		Intent saved=new Intent(getApplicationContext(),SavedHeadlines.class);
		startActivity(saved);
	}
}
