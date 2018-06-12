package com.example.myproject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Secgrid extends Activity{
	GridView g;
	String Rno;
	String arr[]={"My Profile","Marks","Attendence","Library","Change Password"};


	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secgrid);g=(GridView)findViewById(R.id.gridView1);
        Intent myIntent = getIntent();
    	Rno = myIntent.getStringExtra("Rno");
        g.setAdapter(new SeImageAdapter(this));
        ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFF4500")));
        g.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int index=arg2;
				Toast.makeText(getApplicationContext(), "Welcome to :"+arr[index]+"page",Toast.LENGTH_LONG).show();
				 switch (arg2) {
	                case 0:

	                    Intent i = new Intent(getApplicationContext(), MyProfile.class);
	                    i.putExtra("arg3", arg2);
	                    startActivity(i);
	                    break;

	                case 1:
	                    //Use some different intent here
	                    Intent j = new Intent(getApplicationContext(), IEmarks.class);
	                    j.putExtra("Rno", Rno);
	                    j.putExtra("arg3", arg2);
	                    startActivity(j);
	                    break;
	                case 2:
	                    //Use some different intent here
	                    Intent k = new Intent(getApplicationContext(), Atencdance.class);
	                    k.putExtra("Rno", Rno);
	                    startActivity(k);
	                    break;
	                case 3:
	                    //Use some different intent here
	                    Intent l = new Intent(getApplicationContext(),Library.class);
	                    l.putExtra("arg3", arg2);
	                    startActivity(l);
	                    break;
	                case 4:
	                    //Use some different intent here
	                    Intent m = new Intent(getApplicationContext(), Chpwd.class);
	                    m.putExtra("Rno", Rno);
	                    startActivity(m);
	                    break;
	           
	               
	                default:
	                    break;
	                }
				
		}});
        }

}

