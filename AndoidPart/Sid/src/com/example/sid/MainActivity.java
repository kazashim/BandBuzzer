package com.example.sid;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	Button connectButton;
	Socket s;
	PrintWriter writer = null;
	DataOutputStream dout;
	String serverAddress;
	EditText et ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.serverAddress);
        
        
       
    }
	 private class connectToNetwork extends AsyncTask<Integer,Integer,Integer>
	 {
		 protected Integer doInBackground(Integer... i)
		 {
			 try{
				 	String conStr = "CONNECTED!!";
  			         s = new Socket(serverAddress,5000);
			     	 dout = new DataOutputStream(s.getOutputStream());
			        
			       	//s.close();
			       //System.out.println("Closed!!!!");
			        }
			        catch(Exception e)
			        {
			        		System.out.println(e);
			        }
			return 1;
			 
		 }
		 protected void onProgressUpdate(Integer progress) {
	         ///setProgressPercent(progress[0]);
	     }

	     protected void onPostExecute(Integer  result) {
	    	 	String conStr = "Connected!";
	    	 Toast.makeText(MainActivity.this,conStr,Toast.LENGTH_LONG).show();
	    	 //showDialog("Downloaded " + result + " bytes");
	     }

	 }
	 
 public void callBuzz(View v) throws IOException
 {
	 	 dout.writeUTF("LEts have a talk!");
	 	 
 }
 public void connect(View v)	
 {
	 String TAG = null;
	 
	 	//Log.d(TAG,"Pressed");
	 //	Toast.makeText(this,"SENT!!",Toast.LENGTH_LONG).show();
	 System.out.println("Heyy!!!");
	 serverAddress = et.getText().toString();
	 new connectToNetwork().execute(1,1,1);
	// new buzzer().execute(1,1,1);x      
 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
