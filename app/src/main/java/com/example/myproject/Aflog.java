package com.example.myproject;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class Aflog extends TabActivity {
	String Rno;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aflog);
        Intent myIntent = getIntent();
    	Rno = myIntent.getStringExtra("Rno");
        
        TabHost tabHost = getTabHost();
        
       
        TabSpec profspec = tabHost.newTabSpec("My Profile");
        profspec.setIndicator("MY PROFILE", getResources().getDrawable(R.drawable.icprofile));
        Intent profIntent = new Intent(this, MyProfile.class);
        profspec.setContent(profIntent);
        
        
        
        TabSpec marksspec = tabHost.newTabSpec("Marks");
        // setting Title and Icon for the Tab
        marksspec.setIndicator("MARKS", getResources().getDrawable(R.drawable.icmarks));
        Intent marksIntent = new Intent(this, Marks.class);
        marksspec.setContent(marksIntent);
        
    
        TabSpec attenspec = tabHost.newTabSpec("Attendance");
        attenspec.setIndicator("ATTENDANCE", getResources().getDrawable(R.drawable.icatten));
        Intent attenIntent = new Intent(this, Atencdance.class);
        attenspec.setContent(attenIntent);
        
  
        TabSpec libspec = tabHost.newTabSpec("Library");
        libspec.setIndicator("LIBRARY", getResources().getDrawable(R.drawable.iclib));
        Intent libIntent = new Intent(this, Library.class);
        libspec.setContent(libIntent);
        
        TabSpec resetspec = tabHost.newTabSpec("Change password");
        resetspec.setIndicator("CHANGE PASSWORD", getResources().getDrawable(R.drawable.icchpwd));
        Intent resetIntent = new Intent(this, Chpwd.class);
        resetspec.setContent(resetIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(profspec); // Adding profile tab
        tabHost.addTab(marksspec); // Adding marks tab
        tabHost.addTab(attenspec);// Adding attend tab
        tabHost.addTab(libspec);// Adding library tab
        tabHost.addTab(resetspec);// Adding Password tab
        
    }
}