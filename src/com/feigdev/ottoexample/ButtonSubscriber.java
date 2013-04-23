package com.feigdev.ottoexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.squareup.otto.Subscribe;

public class ButtonSubscriber extends Service {
	static final String TAG = "ButtonSubscriber";
	
	@Override
    public void onCreate() {
		super.onCreate();
		Log.d(TAG,"create service");
		BusProvider.getInstance().register(this);
	}

	@Override
	public void onDestroy(){
		Log.d(TAG,"going down");
		super.onDestroy();
	}

	@Subscribe
	public void printButtonPress(ButtonEvent event){
		Log.d(TAG,"button pressed");
	}

	@Subscribe
	public void killSerivce(KillService event){
		onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
