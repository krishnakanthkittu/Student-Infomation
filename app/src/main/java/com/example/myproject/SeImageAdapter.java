package com.example.myproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class SeImageAdapter extends BaseAdapter {
	private Context mcontext;
	
	 public SeImageAdapter(Context c){
		 mcontext=c;
	 }
	

	@Override
	public int getCount() {
		return mthumbs.length;
	
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		ImageView iv;
		if(arg1==null){
			iv=new ImageView(mcontext);
			iv.setLayoutParams(new GridView.LayoutParams(120,120));
			iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			iv.setPadding(10,10,10,10);
		}else{
			iv=(ImageView) arg1;
		}
		iv.setImageResource(mthumbs[arg0]);
		return iv;
	}
	private Integer[] mthumbs = {
			R.drawable.profile,R.drawable.marks,
			R.drawable.ten,R.drawable.libr,R.drawable.chpwd
	};
			
	}


