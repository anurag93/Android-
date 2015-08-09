package com.example.latestnews;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StoryAdapter extends ArrayAdapter<Story> {
	ArrayList<Story> storyList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;
	public StoryAdapter(Context context, int resource, ArrayList<Story> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		storyList = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		
		if(v==null){
			holder=new ViewHolder();
			v=vi.inflate(Resource,null);
			
			holder.story=(TextView) v.findViewById(R.id.story);
			v.setTag(holder);
		}else{
			holder=(ViewHolder)v.getTag();
		}
		
		
		holder.story.setText(storyList.get(position).getStory());
		return v;
	}
	static class ViewHolder {
		
		public TextView story;
		
		
		

	}

}
