package com.jc.slidingmenufragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Lifecycle methods of a Fragment: 
 * 1. onCreate()
 *    The system calls this when creating the fragment. Within your implementation, you should 
 *    initialize essential components of the fragment that you want to retain when the fragment 
 *    is paused or stopped, then resumed.
 * 2. onCreateView()
 *    The system calls this when it's time for the fragment to draw its user interface for the 
 *    first time. To draw a UI for your fragment, you must return a View from this method that 
 *    is the root of your fragment's layout. You can return null if the fragment does not provide a UI.
 * @author jorgecasariego
 *
 */
public class ColorFragment extends Fragment {
	
	private int mColorRes = -1;
	
	public ColorFragment() { 
		this(R.color.white);
	}
	
	public ColorFragment(int colorRes) {
		mColorRes = colorRes;
		/**
		 * setRetainInstance retiene los datos a traves de los cambios de configuraci—n
		 * (por ejemplo rotaci—n del dispositivo). Los fragments no retenidos son destruidos
		 * y recreados en el cambio de configuraci—n; los retenidos no. Por lo tanto
		 * los fragmentos retenidos estan disponibles para las actividades posteriores a la
		 * configuraci—n de cambio
		 */
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (savedInstanceState != null)
			mColorRes = savedInstanceState.getInt("mColorRes");
		
		int color = getResources().getColor(mColorRes);
		// construct the RelativeLayout
		RelativeLayout v = new RelativeLayout(getActivity());
		v.setBackgroundColor(color);		
		return v;
	}
	
	/**
	 * Also like an activity, you can retain the state of a fragment using a Bundle, in case 
	 * the activity's process is killed and you need to restore the fragment state when the 
	 * activity is recreated. You can save the state during the fragment's onSaveInstanceState() 
	 * callback and restore it during either onCreate(), onCreateView(), or onActivityCreated()
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("mColorRes", mColorRes);
	}
	
}
