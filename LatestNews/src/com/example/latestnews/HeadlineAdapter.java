package com.example.latestnews;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HeadlineAdapter extends ArrayAdapter<Headlines> {
	ArrayList<Headlines> headlineList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;
	public HeadlineAdapter(Context context, int resource, ArrayList<Headlines> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		headlineList = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		if(v==null){
			holder=new ViewHolder();
			v=vi.inflate(Resource,null);
		
			holder.title=(TextView)v.findViewById(R.id.title);
			holder.pubdate=(TextView)v.findViewById(R.id.pubdate);
			
			v.setTag(holder);
		}else{
			holder=(ViewHolder)v.getTag();
		}
		
		holder.title.setText(headlineList.get(position).getTitle());
		holder.pubdate.setText(headlineList.get(position).getpubdate());
		
		return v;
	}
	static class ViewHolder {
		
		public TextView title;
		public TextView pubdate;
		

	}}
