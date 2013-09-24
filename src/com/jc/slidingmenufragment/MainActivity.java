package com.jc.slidingmenufragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends BaseActivity {
	private Fragment mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Obtenemos el fragment asociado a la actividad
		if (savedInstanceState != null) 
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		
		if (mContent == null) 
			mContent = new ColorFragment(R.color.red);	
		
		
		// set the Above View
		setContentView(R.layout.content_frame);
		getSupportFragmentManager() 			// Obtenemos una referencia del FragmentManager
		.beginTransaction()						// Iniciamos una transacción
		.replace(R.id.content_frame, mContent)	// llamamos al método replace(ViewGroup, Fragment), 
		.commit();								// pasándole el ID de un ViewGroup y el fragmento que 
												// queremos embeber en dicho ViewGroup	
		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new ColorMenuFragment())
		.commit();
		
		// customize the SlidingMenu
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		getSlidingMenu().showContent();
	}

}
