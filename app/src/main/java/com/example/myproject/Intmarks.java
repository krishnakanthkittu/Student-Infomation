package com.example.myproject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Intmarks extends Activity{
	String Rno;
	TextView marks;
	
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intmarks);
        marks=(TextView)findViewById(R.id.textViewMarks);
        Intent myIntent = getIntent();
    	Rno = myIntent.getStringExtra("Rno");
    	ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFF4500")));
		String result="";
		InputStream isr=null;
		try{
			HttpClient httpclient=new DefaultHttpClient();
			HttpPost httppost=new HttpPost("http://www.mvrcoe.ac.in/android/stuim.php");
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("Rno",Rno.toUpperCase()));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse response=httpclient.execute(httppost);
			HttpEntity entity=response.getEntity();
			isr=entity.getContent();
			
		}
		catch(Exception e)
		{
			Log.e("log-tag","Error in Http connection...."+e.toString());
			marks.setText("Hi... could not connect to Database");
		}
		
		try{
			BufferedReader reader=new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
			StringBuilder sb= new StringBuilder();
			String line="";
			while((line=reader.readLine())!=null)
			{
				sb.append(line+"\n");
				
			}
			isr.close();
			
			result=sb.toString();
		}catch(Exception e)
		{
			Log.e("log-tag","Error in converting result...."+e.toString());
			marks.setText("Hi... could not connect to Database");
	
			
		}
		
		
		try{
		String s="";
		JSONArray jArray=new JSONArray(result);
			
		for(int i=0;i<jArray.length();i++)
		{
			JSONObject json=jArray.getJSONObject(i);
			//for r10 avg calulation
			int ct1,ct2,ct3,at1,at2,at3,afm,asm,bot = 0,tafm,tasm,tbot=0;
			try{
				ct1=json.getInt("CT1");
						
			}catch(Exception e){
				ct1=0;
				
			}
			try{
				ct2=json.getInt("CT2");
						
			}catch(Exception e){
				ct2=0;
				
			}
			try{
				ct3=json.getInt("CT3");
						
			}catch(Exception e){
				ct3=0;
				
			}
			try{
				at1=json.getInt("AT1");
						
			}catch(Exception e){
				at1=0;
				
			}
			try{
				at2=json.getInt("AT2");
						
			}catch(Exception e){
				at2=0;
				
			}
			try{
				at3=json.getInt("AT3");
						
			}catch(Exception e){
				at3=0;
				
			}
			//m1 = ((Integer.parseInt(json.getString("CT1").toString()))*(3/8))+((Integer.parseInt(json.getString("AT1").toString()))*(1/2));
			afm=(int) ((ct1*(0.375))+(at1*(0.5)));
			asm=(int) ((ct2*(0.375))+(at2*(0.5)));
			tafm=(int)(ct1+(ct3/2)+((at1+at2)/2));
			tasm=(int)(ct2+(at3/2)+((at1+at2)/2));
			if(afm<asm)
			{
				bot=asm;
			}
			else if(afm>=asm){
				bot=afm;
			}
			if(tafm<tasm)
			{
				tbot=tasm;
			}
			else if(tafm>=tasm){
				tbot=tafm;
			}
			if(json.getInt("Year")<2013){
				
			s=s+ (i+1)+ 
					" . Regulation : R10"+"\n"+
					"Code :"+json.getString("CSCode")+"==>"+
					"Subject :"+json.getString("SubName")+"\n"+
					"1st mid(Theory):"+ct1 +"\t\t"+
					"1st mid(Online) :"+at1+"\t"+
					"Aggregate of 1st mid:"+afm+"\n"+
					"2nd mid(Theory):"+ct2+"\t"+
					"2nd mid(Online) :"+at2+"\t"+
					"Aggregate of 2nd mid:"+asm+"\n"+
					"Best of 2:"+bot+"\n\n\n";
			}
			
			
					
			else if(json.getInt("Year")>=2013)
				s=s+"     Code :"+json.getString("CSCode")+"==>"+"\n"+
						"Subject :"+json.getString("SubName")+"\n"+
						"1st mid(Theory):"+ct1 +"\t"+
						"  Assignment1 :"+at1+"\t"+
						"1st mid(Online):"+ct3+"\t"+
						"Aggregate of 1st mid:"+tafm+"\n"+
						"  2nd mid(Theory):"+ct2+"\t"+
						"  Assignment2 :"+at2+"\t"+
				        "2nd mid(Online):"+at3+"\t"+
			            "Aggregate of 2nd mid:"+tasm+"\n"+
			            "Best of 2:"+tbot+"\n";
					
			
			
			Log.i("info"," process"+ i);
			
		}
		marks.setText(s);
		
		
			
		}catch(Exception e)
		{
		
			Log.e("log-tag","Error in parsing data...."+e.toString());
			
		}
    	//end
    
	}
}
