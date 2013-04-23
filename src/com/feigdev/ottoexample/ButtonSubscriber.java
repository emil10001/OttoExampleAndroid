package com.feigdev.ottoexample;

import android.util.Log;

import com.squareup.otto.Subscribe;

public class ButtonSubscriber {
	static final String TAG = "ButtonSubscriber";
	private static final ButtonSubscriber instance = new ButtonSubscriber();
	
	private ButtonSubscriber(){
		BusProvider.getInstance().register(this);
	}
	
	static ButtonSubscriber getInstance(){
		return instance;
	}
	
	@Subscribe
	public void printButtonPress(ButtonEvent event){
		Log.d(TAG,"button pressed");
	}
}
