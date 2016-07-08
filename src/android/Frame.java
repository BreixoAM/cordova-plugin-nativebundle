package com.breixoam.tgw;

import org.apache.cordova.CordovaActivity;
import android.util.Log;
import android.graphics.Color;
import android.view.Window;
import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.widget.Toolbar;

public class Frame {

    private static final String TAG = "TGWDebug Frame.java";

	protected String mainColor;
    protected String mainColorDark;
    protected Header header;
    protected Slider slider;
    protected Content content;

    /**
     * Constructor 
     */
    public Frame() {
        this.header = new Header();
        this.slider = new Slider();
        this.content = new Content();
    }

    //Return header
    public Header getHeader() {
        return this.header;
    }

    //Return slider
    public Slider getSlider() {
        return this.slider;
    }

    //Return content
    public Content getContent() {
        return this.content;
    }

    //Return main color
    public String getMainColor() {
    	return this.mainColor;
    }

    //Set and update de main color
    public void setMainColor(final String mainColor) {

    	this.mainColor = mainColor;
        final CordovaActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ((TgwActivity) activity).toolBar.setBackgroundColor(Color.parseColor(mainColor));

                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
                    for (ActivityManager.AppTask appTask : activityManager.getAppTasks()) {
                        if (appTask.getTaskInfo().id == activity.getTaskId()) {
                            ActivityManager.TaskDescription description = appTask.getTaskInfo().taskDescription;
                            ActivityManager.TaskDescription a = new ActivityManager.TaskDescription(description.getLabel(), description.getIcon(), Color.parseColor(mainColor));
                            activity.setTaskDescription(a);
                        }
                    }
                }

            }
        });
    }

    //Return main color dark
	public String getMainColorDark() {
    	return this.mainColorDark;
    }

    //Set and update de main color dark
    public void setMainColorDark(final String mainColorDark) {

    	this.mainColorDark = mainColorDark;
        final CordovaActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    final Window window = activity.getWindow();
                    window.setStatusBarColor(Color.parseColor(mainColorDark));
                }

            }
        });
    }

    //Show spinner dialog
    public void showSpinner() {

        final CordovaActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TgwActivity) activity).progress.setMessage("Loading...");
                ((TgwActivity) activity).progress.show();
            }
        });

    }

    //Hide spinner dialog
    public void hideSpinner() {

        final CordovaActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TgwActivity) activity).progress.dismiss();
            }
        });

    }

}
