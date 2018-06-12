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

public class Atencdance extends Activity {
	String Rno;
	float title;
	String s="";
	TextView tv1;
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atten);
        Intent myIntent = getIntent();
    	Rno = myIntent.getStringExtra("Rno");
    	ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFF4500")));
		bar.setTitle("Percentage is:"+title);
      
    	   	
    	
    	tv1=(TextView) findViewById(R.id.textViewAttendence);
    	tv1.setText("ATTENDENCE FOR : "+Rno+"\n"+s);
    	//start
    	String result="";
		InputStream isr=null;
		try{
			HttpClient httpclient=new DefaultHttpClient();
			HttpPost httppost=new HttpPost("http://www.mvrcoe.ac.in/android/stuatt.php");
			
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
			tv1.setText("Hi... could not connect to Database");
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
			tv1.setText("Hi... could not connect to Database");
	
			
		}
		
		
		try{
		
		JSONArray jArray=new JSONArray(result);
		int ch,ca,tch=0,tca=0, pa = 0;
		
		for(int i=0;i<jArray.length();i++)
		{
			JSONObject json=jArray.getJSONObject(i);
		
			try{
				ch=json.getInt("CHeld");
				ca=json.getInt("CAttended");
			}
			catch(Exception e){
				ch=0;
				ca=0;
			}
			if(json.getInt("Credit")==2)
				{
				tch=tch+(3*ch);
			tca=tca+(3*ca);
			
			}
			else if(json.getInt("Credit")==1)
			{
				tch=tch+ch;
				tca=tca+ca;
				
			
			}
			
			s=s+ (i+1)+ 
					"==>"+
					"Subject:"+json.getString("SubName")+"\n"+
					"Classes Held:"+ch +"\n"+
					"Classes Attended:"+ca+"\n"+
					"Dates:"+json.getString("Dates")+"\n\n";
					
					
			
			Log.i("info"," process"+ i);
		}
		
		
	//pa=(int) ((double)(tca)/(double)(tch))*100;
		float fa = ((float)tca/(float)tch)*100;
		pa=(int)fa;
		title=pa;
		s="\n"+s+"Total Classes Held:"+tch+"\n"+
		"Total Classes Attended:"+tca+"\n";
		
		tv1.setText(s);
		bar.setTitle(" Percentage of Attendance is:"+title+"%");
		
		
		
			
		}catch(Exception e)
		{
		
			Log.e("log-tag","Error in parsing data...."+e.toString());
			
		}
    	//end
    
    	
    	
	}



}
