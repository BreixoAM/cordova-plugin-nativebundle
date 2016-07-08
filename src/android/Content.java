package com.breixoam.tgw;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.util.Log;
import android.animation.AnimatorListenerAdapter;
import android.animation.Animator;

public class Content {

    private static final String TAG = "TGWDebug Content.java";

    /**
     * Constructor 
     */
    public Content() {
    }

    public void enableBounce() {
    	
        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View content = ((TgwActivity) activity).browser;
                content.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);

            }
        });
    }

    public void disableBounce() {
        
        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View content = ((TgwActivity) activity).browser;
                content.setOverScrollMode(View.OVER_SCROLL_NEVER);

            }
        });
    }

    public void showScrollbar() {
        
        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View content = ((TgwActivity) activity).browser;
                content.setVerticalScrollBarEnabled(true);

            }
        });
    }

    public void hideScrollbar() {
        
        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View content = ((TgwActivity) activity).browser;
                content.setVerticalScrollBarEnabled(false);

            }
        });
    }

    public void fadeIn(final CallbackContext callbackContext) {
        
        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View content = ((TgwActivity) activity).browser;

                content.clearAnimation();
                content.setAlpha(0f);

                content.animate()
                    .alpha(1f)
                    .setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            callbackContext.success();
                        }
                    });
            }
        });
    }

    public void fadeOut(final CallbackContext callbackContext) {
        
        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View content = ((TgwActivity) activity).browser;

                content.clearAnimation();
                content.setAlpha(1f);

                content.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            callbackContext.success();
                        }
                    });
            }
        });
    }

    public void hide() {
        
        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View content = ((TgwActivity) activity).browser;

                content.clearAnimation();
                content.animate()
                    .alpha(0f)
                    .setDuration(0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                        }
                    });
            }
        });
    }

    public void show() {
        
        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View content = ((TgwActivity) activity).browser;

                content.clearAnimation();
                content.animate()
                    .alpha(1f)
                    .setDuration(0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                        }
                    });
            }
        });
    }

    public void clearHistory() {
        
        final AppCompatActivity activity = Tgw.activity;
        
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                CordovaWebView finalBrowser = ((TgwActivity) Tgw.activity).webView;
                finalBrowser.clearHistory();
            }
        });
        

    }

}
