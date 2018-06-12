package com.example.myproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Chpwd extends Activity{
	TextView t;
	Button b;
	String Rno;
	int status;
	EditText cp,np,cnp;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.chpswd);
	        Intent myIntent = getIntent();
	        Rno = myIntent.getStringExtra("Rno");
	        b=(Button)findViewById(R.id.submit);
	        t=(TextView)findViewById(R.id.test);
	        cp=(EditText)findViewById(R.id.editText1);
	        np=(EditText)findViewById(R.id.editText2);
	        cnp=(EditText)findViewById(R.id.editText3);
	        t.setText(" your are requesting to change password Mr/Ms. :"+Rno);
	        b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(np.getText().toString().equals(cnp.getText().toString()))
					{
						HttpClient httpclient = new DefaultHttpClient();
						 HttpPost httppost = new HttpPost("http://www.mvrcoe.ac.in/android/updatepassword.php");
						 try {
								
								List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
								nameValuePairs.add(new BasicNameValuePair("Rno", Rno));
								nameValuePairs.add(new BasicNameValuePair("pass1", cp.getText().toString()));
								nameValuePairs.add(new BasicNameValuePair("pass2", np.getText().toString()));

								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

								// Execute HTTP Post Request

								ResponseHandler<String> responseHandler = new BasicResponseHandler();
								String response = httpclient.execute(httppost, responseHandler);
							
								//This is the response from a php application
							 status=Integer.parseInt(response);
								//Toast.makeText(MainActivity.this, "status   :" + status, Toast.LENGTH_LONG).show();
								//user.setText("RESPONSE  :"+status);
								} catch (ClientProtocolException e) {
								Toast.makeText(Chpwd.this, "CPE response " + e.toString(), Toast.LENGTH_LONG).show();
								// TODO Auto-generated catch block
								} catch (IOException e) {
								Toast.makeText(Chpwd.this, "IOE response " + e.toString(), Toast.LENGTH_LONG).show();
								// TODO Auto-generated catch block
								}
					    	  
						 if(status==1 )
							{
							 Toast.makeText(getApplicationContext(), "You have changed password successfully", Toast.LENGTH_LONG).show();

							Intent i=new Intent(Chpwd.this,Secgrid.class);
							i.putExtra("Rno", Rno);
							startActivity(i);
							}
							else if(status==0)
							{
								//user.setText("Invalid user name or password");
								cp.setText("");
								np.setText("");
								cnp.setText("");
								Toast.makeText(getApplicationContext(), "Make Sure about the current password you entered", Toast.LENGTH_LONG).show();
							}
							else{
							Toast.makeText(getApplicationContext(), "Server Busy", Toast.LENGTH_SHORT);
							}
							
					}
					else {
						Toast.makeText(getApplicationContext(), "Enter same data in new and conform password fields", Toast.LENGTH_LONG).show();
						cp.setText("");
						np.setText("");
						cnp.setText("");
					}
				}
			});
	        }
	 		
}
