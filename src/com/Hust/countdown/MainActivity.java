package com.Hust.countdown;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{
	private EditText input;
	private Button getTime,startTime,stopTime;
	private TextView time;
	private int i=0;
	private Timer timer=null;
	private TimerTask task=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

     }
    
    private void initView(){
    	input=(EditText) findViewById(R.id.inputtime);
    	getTime=(Button) findViewById(R.id.gettime);
    	startTime=(Button) findViewById(R.id.starttime);
    	stopTime=(Button) findViewById(R.id.stoptime);
    	time=(TextView) findViewById(R.id.time);
    	getTime.setOnClickListener(this);
    	startTime.setOnClickListener(this);
    	stopTime.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.gettime:
			time.setText(input.getText().toString());
			i=Integer.parseInt(input.getText().toString());
			break;

		case R.id.starttime:
			startTime();
			break;
		
		case R.id.stoptime:
			stopTime();
			break;
		}
	}
	
	private Handler mhandler=new Handler(){
		public void handleMessage(Message msg){
			time.setText(msg.arg1+"");
			startTime();
		}
	};
	
	public void startTime(){
		timer=new Timer();
		task=new TimerTask() {
			
			@Override
			public void run() {
			   i--;
			   Message message=mhandler.obtainMessage();
			   message.arg1=i;
			   mhandler.sendMessage(message);
				
			}
		};
		timer.schedule(task, 1000);
	}
	
	public void stopTime(){
		timer.cancel();
	}

}
