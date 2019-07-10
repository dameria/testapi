package com.hcitest.testapi.ui;

import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hcitest.testapi.R;


public class BusyDialog extends Dialog {
	
 
private ImageView iv;
	
public BusyDialog(Context context) {
	super(context, R.style.TransparentProgressDialog);
    	WindowManager.LayoutParams wlmp = getWindow().getAttributes();
    	wlmp.gravity = Gravity.CENTER_HORIZONTAL;
    	getWindow().setAttributes(wlmp);
		setTitle(null);
		setCancelable(false);
		setOnCancelListener(null);
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		iv = new ImageView(context);
		
		iv.setImageResource(R.drawable.busy);

		ProgressBar progressBar = new ProgressBar(context);
		layout.addView(progressBar, params);
		
		textView = new TextView(context);
		textView.setGravity(Gravity.CENTER);
		layout.addView(textView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		addContentView(layout, params);
	}
	private TextView textView ;
	public void setMessage(String s){
		if (textView!=null) {
			textView.setText(s);
		}
	}
	
}
