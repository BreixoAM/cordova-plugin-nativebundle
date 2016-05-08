package com.breixoam.tgw;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import java.util.ArrayList;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.animation.ValueAnimator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.widget.ImageButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Header {

    private static final String TAG = "TGWDebug Header.java";

    protected String title;
    protected ArrayList options;
    protected ArrayList actions;

    /**
     * Constructor 
     */
    public Header(String title) {
    	this.title = title;
        this.options = new ArrayList<OptionHeader>();
        this.actions = new ArrayList<ActionHeader>();
    }

    //Return header title
    public String getTitle() {
    	return this.title;
    }

    //Set header title
    public void setTitle(final String title) {

    	this.title = title;
    	final AppCompatActivity activity = Tgw.activity;

    	activity.runOnUiThread(new Runnable() {
	        @Override
	        public void run() {
				((AppCompatActivity) activity).getSupportActionBar().setTitle(title);
	        }
    	});
		
    }

    //Change color of header items
    public void setItemsColor(final String color) {

        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toolbar toolbar = ((TgwActivity) activity).toolBar;
                toolbar.setTitleTextColor(Color.parseColor(color));

                final PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_ATOP);

                for (int i = 0; i < toolbar.getChildCount(); i++) {
                    final View v = toolbar.getChildAt(i);

                    if (v instanceof ImageButton) {       
                        ((ImageButton) v).setColorFilter(colorFilter);
                    }

                    if(v instanceof ActionMenuView) {

                        Menu menu = ((ActionMenuView)v).getMenu();

                        for(int j = 0; j < menu.size(); j++) {

                            MenuItem item = menu.getItem(j);
                            Drawable icon = item.getIcon();
                            if (icon != null) {
                                icon.setColorFilter(colorFilter);
                            }

                        }
                    }
                }

            }
        });
        
    }

    //Change icon to back button with animation
    public void showBackButton() {

        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float slideOffset = (Float) valueAnimator.getAnimatedValue();
                        DrawerLayout drawer = (DrawerLayout) ((TgwActivity) activity).findViewById(((TgwActivity) activity).resources.getIdentifier("drawer_layout", "id", ((TgwActivity) activity).package_name));
                        ((TgwActivity) activity).toggle.onDrawerSlide(drawer, slideOffset);
                    }
                });
                anim.setInterpolator(new DecelerateInterpolator());
                anim.setDuration(200);
                anim.addListener(new AnimatorListenerAdapter()  {
                    @Override
                    public void onAnimationEnd(Animator animation) 
                    {
                        ((TgwActivity) activity).toggle.setDrawerIndicatorEnabled(false);
                        ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    }
                });
                anim.start();
                
            }
        });
        
    }

    //Show hamburguer button with animation
    public void showSliderButton() {

        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                final DrawerLayout drawer = (DrawerLayout) ((TgwActivity) activity).findViewById(((TgwActivity) activity).resources.getIdentifier("drawer_layout", "id", ((TgwActivity) activity).package_name));

                if (!((TgwActivity) activity).toggle.isDrawerIndicatorEnabled()) {
                    ((TgwActivity) activity).toggle.onDrawerSlide(drawer, 1);
                    ((TgwActivity) activity).toggle.setDrawerIndicatorEnabled(true);
                    ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    ((TgwActivity) activity).toggle.syncState();

                    ValueAnimator anim = ValueAnimator.ofFloat(1, 0);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            float slideOffset = (Float) valueAnimator.getAnimatedValue();
                            ((TgwActivity) activity).toggle.onDrawerSlide(drawer, slideOffset);
                        }
                    });
                    anim.setInterpolator(new DecelerateInterpolator());
                    anim.setDuration(200);
                    anim.addListener(new AnimatorListenerAdapter()  {
                        @Override
                        public void onAnimationEnd(Animator animation) 
                        {
                        }
                    });
                    anim.start();
                }

            }
        });
        
    }

    //Empty options
    public void clearOptions() {

        this.options = new ArrayList<OptionHeader>();
    }

    //Add option
    public void addOption(CallbackContext callbackContext, String title) {

        OptionHeader option = new OptionHeader();
        option.title = title;
        option.callbackContext = callbackContext;

        this.options.add(option);
    }

    //Empty actions
    public void clearActions() {

        this.actions = new ArrayList<ActionHeader>();
    }

    //Añade una accion al toolbar
    public void addAction(CallbackContext callbackContext, String title, String icon) {

        ActionHeader action = new ActionHeader();
        action.title = title;
        action.icon = icon;
        action.callbackContext = callbackContext;

        this.actions.add(action);
    }

    //Update header ui with actions and options
    public void update() {

        final AppCompatActivity activity = Tgw.activity;
        final ArrayList options = this.options;
        final ArrayList actions = this.actions;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toolbar toolbar = ((TgwActivity) activity).toolBar;
                Menu menu = toolbar.getMenu();

                menu.clear();

                for (int i = 0; i < options.size(); i++) {

                    final OptionHeader option = ((OptionHeader) options.get(i));

                    MenuItem menuItem = menu.add(1, 2, i, option.title);

                    menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            PluginResult progressResult = new PluginResult(PluginResult.Status.OK, option.title);
                            progressResult.setKeepCallback(true);
                            option.callbackContext.sendPluginResult(progressResult);

                            return true;
                        }
                    });

                }

                for (int i = 0; i < actions.size(); i++) {

                    final ActionHeader action = ((ActionHeader) actions.get(i));

                    MenuItem menuItem = menu.add(Menu.NONE, Menu.NONE, Menu.NONE, action.title);
                    menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

                    int resID = ((TgwActivity) activity).resources.getIdentifier(action.icon , "drawable", ((TgwActivity) activity).package_name);
                    if (resID != 0) {
                        Drawable icon = ((TgwActivity) activity).resources.getDrawable(resID);
                        icon.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
                        menuItem.setIcon(icon);
                    }

                    menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            PluginResult progressResult = new PluginResult(PluginResult.Status.OK, action.title);
                            progressResult.setKeepCallback(true);
                            action.callbackContext.sendPluginResult(progressResult);

                            return true;
                        }
                    });

                }

            }
        });

    }

}
