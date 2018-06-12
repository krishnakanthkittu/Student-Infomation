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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class Second extends Activity  {
	GridView g;
	
	String arr[]={"Login","Cse","Ece","Eee","Civil","Mech","Mba"};

	 @SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.second);
	        g=(GridView)findViewById(R.id.gridView1);
	        
	        g.setAdapter(new ImageAdapter(this));
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

		                    Intent i = new Intent(getApplicationContext(), Login.class);
		                    i.putExtra("arg3", arg2);
		                    startActivity(i);
		                    break;

		                case 1:
		                    //Use some different intent here
		                    Intent j = new Intent(getApplicationContext(), Cse.class);
		                    
		                    j.putExtra("arg3", arg2);
		                    startActivity(j);
		                    break;
		                case 2:
		                    //Use some different intent here
		                    Intent k = new Intent(getApplicationContext(), Ece.class);
		                    k.putExtra("arg3", arg2);
		                    startActivity(k);
		                    break;
		                case 3:
		                    //Use some different intent here
		                    Intent l = new Intent(getApplicationContext(),Eee.class);
		                    l.putExtra("arg3", arg2);
		                    startActivity(l);
		                    break;
		                case 4:
		                    //Use some different intent here
		                    Intent m = new Intent(getApplicationContext(), Civil.class);
		                    m.putExtra("arg3", arg2);
		                    startActivity(m);
		                    break;
		                case 5:
		                    //Use some different intent here
		                    Intent n = new Intent(getApplicationContext(), Mech.class);
		                    n.putExtra("arg3", arg2);
		                    startActivity(n);
		                    break;
		                case 6:
		                    //Use some different intent here
		                    Intent o = new Intent(getApplicationContext(), Mba.class);
		                    o.putExtra("arg3", arg2);
		                    startActivity(o);
		                    break;
		               
		                default:
		                    break;
		                }
					
			}});
	        }
	
	}


