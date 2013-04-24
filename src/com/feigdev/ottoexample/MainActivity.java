package com.feigdev.ottoexample;

import com.squareup.otto.Subscribe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		startService(new Intent(this, ButtonSubscriber.class));

		// Register self with the only bus that we're using
		BusProvider.getInstance().register(this);
		
        ((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BusProvider.getInstance().post(new ButtonEvent());
			}
		});
        
    }

    // Two separate subscribers to the button event.
	@Subscribe
	public void anotherButtonPress(ButtonEvent event){
		Toast.makeText(this, "Activity called", 1000).show();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onDestroy(){
    	// Let's kill the service with a bus message when we kill the activity
    	BusProvider.getInstance().post(new KillService());
    	super.onDestroy();
    }
}