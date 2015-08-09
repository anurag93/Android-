package com.example.latestnews;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChannelAdapter extends ArrayAdapter<Channels>{
	ArrayList<Channels> channelList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;
	ImageLoader imageloader;
	
	
    public ChannelAdapter(Context context,int resource,ArrayList<Channels> objects){
    	super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		channelList = objects;
		 imageloader=new ImageLoader(context);
    }
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		if(v==null){
			holder=new ViewHolder();
			v=vi.inflate(Resource,null);
			
			holder.name=(TextView)v.findViewById(R.id.name);
			
			holder.image1=(ImageView)v.findViewById(R.id.imageView1);
			
						v.setTag(holder);
		}else{
			holder=(ViewHolder)v.getTag();
		}
		
	    
	    
		holder.name.setText(channelList.get(position).getName());
		imageloader.DisplayImage(channelList.get(position).getImage1(),holder.image1);
		holder.image1.setAdjustViewBounds(true);
		holder.image1.setImageAlpha(225);
		holder.image1.setMinimumHeight(400);
	    holder.image1.setMinimumWidth(600);
	    
		return v;
		
	}
  
static class ViewHolder {
		
		public TextView name;
		
		public ImageView image1;
		
		
		

	}



}
