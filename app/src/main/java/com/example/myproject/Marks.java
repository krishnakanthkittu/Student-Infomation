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

public class Marks extends Activity {
	String Rno;
	TextView tv1;
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marks);
        Intent myIntent = getIntent();
    	Rno = myIntent.getStringExtra("Rno");
    	ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFF4500")));
      
    	   	
    	
    	tv1=(TextView) findViewById(R.id.textViewMarks);
    	tv1.setText("MARKS FOR : "+Rno);
    	//start
    	String result="";
		InputStream isr=null;
		try{
			HttpClient httpclient=new DefaultHttpClient();
			HttpPost httppost=new HttpPost("http://www.mvrcoe.ac.in/android/stumarks.php");
			
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
		String s="";
		JSONArray jArray=new JSONArray(result);
		int im=0,em=0;
			
		for(int i=0;i<jArray.length();i++)
		{
			JSONObject json=jArray.getJSONObject(i);
			try{
			im=json.getInt("im");
			em=json.getInt("em");
			}
			catch(Exception e){
				im=0;
				em=0;
			}
			s=s+ (i+1)+ 
					"--->Sem  :"+json.getInt("sem")+"\n"+
					"Code :"+json.getString("CSCode")+"==>"+"\n"+
					"Subject :"+json.getString("SName")+"\n"+
					"Internal:"+im +
					"  External:"+em+
					"  Total :"+(im+em)+"\n"+
					"Credits :"+json.getString("Credit");
			if(json.getInt("Credit")==0)
				s=s+"\nResult  :Fail\n\n";
			else
				s=s+"\nResult  :Pass\n\n";
			Log.i("info"," process"+ i);
			
		}
		tv1.setText(s);
		
		
			
		}catch(Exception e)
		{
		
			Log.e("log-tag","Error in parsing data...."+e.toString());
			
		}
    	//end
    
    	
    	
	}



}
