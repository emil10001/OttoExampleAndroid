package com.feigdev.ottoexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

public class ButtonSubscriber extends Service {
	static final String TAG = "ButtonSubscriber";

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "create service");
		BusProvider.getInstance().register(this);
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "going down");
		super.onDestroy();
	}

	// Two separate subscribers to the button event.
	@Subscribe
	public void printButtonPress(ButtonEvent event) {
		Toast.makeText(this, "Service called", Toast.LENGTH_SHORT).show();
	}

	// Use bus to kill the service
	@Subscribe
	public void killSerivce(KillService event) {
		onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
