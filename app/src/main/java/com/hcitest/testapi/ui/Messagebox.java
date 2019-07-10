package com.hcitest.testapi.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


public class Messagebox {

	private static ProgressDialog showProgresBar(Context context, String message){
		ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(message);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        return mProgressDialog;
	}
	public static AlertDialog DialogBox(Activity context, String title, String message){
		Builder dlg = new Builder(context);
		dlg.setTitle(title);
		dlg.setMessage(message);
        return dlg.create();
	}
	public interface DoubleRunnable {
		public void run();
		public void runUI();
	}

	public static void newTask(DoubleRunnable run){
		new AsyncTask<DoubleRunnable, Void, DoubleRunnable>(){
		      protected DoubleRunnable doInBackground(DoubleRunnable... params) {
		    	  params[0].run();
		    	  return params[0];
		      }      
		      protected void onPostExecute(DoubleRunnable result) {    
		    	  result.runUI();
		      }
		      protected void onPreExecute() {}
		      protected void onProgressUpdate(Void... values) {}
		}.execute(run);
	}	
	public static ProgressDialog currProgressDialog;

	private static BusyDialog dialogBusy = null;
	public static void showBusyDialog(Context context, Runnable run, Runnable ui){
			new AsyncTask<Runnable, Void, Runnable>(){
			      protected Runnable doInBackground(Runnable... params) {
			    	  params[0].run();
			    	  return params[1];
			      }      
			      protected void onPostExecute(Runnable result) {
			    	  result.run();
			      }
			      protected void onPreExecute() {}
			      protected void onProgressUpdate(Void... values) {}
			}.execute(run, new Runnable() {
				private Runnable ui;
				private BusyDialog prb;
				public Runnable get(Runnable ui, BusyDialog prb){
					this.ui=ui;
					this.prb=prb;
					Messagebox.dialogBusy=prb;
					prb.show();
					return this;
				}
				public void run() {
					prb.dismiss();
					this.ui.run();
					
				}
			}.get(ui, new BusyDialog(context )));
	}
	

}
