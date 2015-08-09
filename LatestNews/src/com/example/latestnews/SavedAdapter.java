package com.example.latestnews;

import java.util.ArrayList;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SavedAdapter extends ArrayAdapter<Saved> {
	ArrayList<Saved> savedList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;
	public SavedAdapter(Context context, int resource,
			ArrayList<Saved> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		savedList = objects;

}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		if(v==null){
			holder=new ViewHolder();
			v=vi.inflate(Resource,null);
		
			holder.title=(TextView)v.findViewById(R.id.headline);
			holder.source=(TextView)v.findViewById(R.id.source);
			holder.pubdate=(TextView)v.findViewById(R.id.pubdate);
			
			v.setTag(holder);
		}else{
			holder=(ViewHolder)v.getTag();
		}
		
		holder.title.setText(savedList.get(position).getheadline());
		holder.source.setText(savedList.get(position).getsource());
		holder.pubdate.setText(savedList.get(position).getpubdate());
		
		
		return v;
	}
static class ViewHolder {
		
		public TextView title;
		public TextView source;
		public TextView pubdate;
		
		
}
	
}
