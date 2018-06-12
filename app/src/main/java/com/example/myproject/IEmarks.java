package com.example.myproject;

import android.R.string;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class IEmarks extends Activity{
	TextView t1,t2;
    String Rno;
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iemarks);
        t1=(TextView)findViewById(R.id.textInt);
        t2=(TextView)findViewById(R.id.textExt);
        Intent myIntent = getIntent();
    	Rno = myIntent.getStringExtra("Rno");
    	ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFF4500")));
      
        t1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(IEmarks.this,Intmarks.class);
				i.putExtra("Rno", Rno);
				startActivity(i);
			}
		});
 t2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(IEmarks.this,Marks.class);
				i.putExtra("Rno", Rno);
				startActivity(i);
				
			}
		});
	}
}
