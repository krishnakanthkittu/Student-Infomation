package com.example.myproject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Login extends Activity {
EditText user,pass;
Button login;
int status;
ImageView note;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		StrictMode.enableDefaults();
		user=(EditText)findViewById(R.id.editText1);
		pass=(EditText)findViewById(R.id.editText2);
		login=(Button)findViewById(R.id.button1);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFF4500")));
		note=(ImageView)findViewById(R.id.imageView1);
		note.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent (Login.this,Notification.class);
				startActivity(i);
				
			}
		});
		
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//CONNECT TO DATABASE 
				//STATUS=0 OR STATUS =1
				//start
				 
				 HttpClient httpclient = new DefaultHttpClient();
				 HttpPost httppost = new HttpPost("http://www.mvrcoe.ac.in/android/loginstu1.php");
				 
		     
				 try {
			
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
					nameValuePairs.add(new BasicNameValuePair("user", user.getText().toString()));
					nameValuePairs.add(new BasicNameValuePair("pass", pass.getText().toString()));

					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					// Execute HTTP Post Request

					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					String response = httpclient.execute(httppost, responseHandler);
				
					//This is the response from a php application
				 status=Integer.parseInt(response);
					//Toast.makeText(MainActivity.this, "status   :" + status, Toast.LENGTH_LONG).show();
					//user.setText("RESPONSE  :"+status);
					} catch (ClientProtocolException e) {
					Toast.makeText(Login.this, "CPE response " + e.toString(), Toast.LENGTH_LONG).show();
					// TODO Auto-generated catch block
					} catch (IOException e) {
					Toast.makeText(Login.this, "IOE response " + e.toString(), Toast.LENGTH_LONG).show();
					// TODO Auto-generated catch block
					}
		    	  
		    
				//end
				
				//status=1;
			if(status==1 )
				{
				Intent i=new Intent(Login.this,Secgrid.class);
				i.putExtra("Rno",""+user.getText());
				startActivity(i);
				}
				else
				{
					//user.setText("Invalid user name or password");
					pass.setText("");
					Toast.makeText(getApplicationContext(), "Invalid usernane & password", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
	}
}
