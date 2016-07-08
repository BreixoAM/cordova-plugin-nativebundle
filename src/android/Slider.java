package com.breixoam.tgw;

import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import java.util.ArrayList;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

public class Slider {

    private static final String TAG = "TGWDebug Slider.java";

    protected String title1;
    protected String title2;
    protected ArrayList options;

    /**
     * Constructor 
     */
    public Slider() {
    	this.title1 = "";
    	this.title2 = "";
        this.options = new ArrayList<OptionSlider>();
    }

    //Set header color
    public void setColor(final String color) {

        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                NavigationView nav = ((TgwActivity) activity).navigationView;
                View view = nav.getHeaderView(0);
                ((LinearLayout) view).setBackgroundColor(Color.parseColor(color));

            }
        });
        
    }

    //Return title1
    public String getTitle1() {
        return this.title1;
    }

    //Update title1
    public void setTitle1(String title) {

        this.title1 = title;

        final AppCompatActivity activity = Tgw.activity;
        final String newTitle = title;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                NavigationView nav = ((TgwActivity) activity).navigationView;
                View view = nav.getHeaderView(0);
                View child = ((ViewGroup)view).getChildAt(1);
                ((TextView)child).setText(newTitle);
            }
        });
        
    }

    //Return title2
    public String getTitle2() {
        return this.title2;
    }

    //Update el titulo2
    public void setTitle2(String title) {

        this.title2 = title;

        final AppCompatActivity activity = Tgw.activity;
        final String newTitle = title;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                NavigationView nav = ((TgwActivity) activity).navigationView;
                View view = nav.getHeaderView(0);
                View child = ((ViewGroup)view).getChildAt(2);
                ((TextView)child).setText(newTitle);
            }
        });
        
    }

    //Update icon header
    public void setIcon(final String c, final String color1, final String color2) {

        final AppCompatActivity activity = Tgw.activity;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                NavigationView nav = ((TgwActivity) activity).navigationView;
                View view = nav.getHeaderView(0);
                View child = ((ViewGroup)view).getChildAt(0);
                ((TextView)child).setText(c);
                ((TextView)child).setTextColor(Color.parseColor(color1));

                Drawable d = ((TextView)child).getBackground();
                d.setColorFilter(Color.parseColor(color2), PorterDuff.Mode.SRC_ATOP);

            }
        });
        
    }

    //Empty options
    public void clearOptions() {

        this.options = new ArrayList<OptionSlider>();
    }

    //Add option
    public void addOption(CallbackContext callbackContext, String icon, String title, String checked) {

        OptionSlider option = new OptionSlider();
        option.icon = icon;
        option.title = title;
        option.checked = checked;
        option.callbackContext = callbackContext;

        this.options.add(option);
    }

    //Update ui with options
    public void update() {

        final AppCompatActivity activity = Tgw.activity;
        final ArrayList options = this.options;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                NavigationView nav = ((TgwActivity) activity).navigationView;
                Menu menuNav = nav.getMenu();

                menuNav.clear();
                menuNav.setGroupCheckable(1, true, true);

                for (int i = 0; i < options.size(); i++) {

                    final OptionSlider option = ((OptionSlider) options.get(i));

                    MenuItem menuItem = menuNav.add(1, 2, i, option.title);
                    menuItem.setCheckable(false);

                    if (option.checked.equals("1")) {
                        menuItem.setChecked(true);
                    }

                    int resID = ((TgwActivity) activity).resources.getIdentifier(option.icon , "drawable", ((TgwActivity) activity).package_name);
                    if (resID != 0) {
                        Drawable icon = ((TgwActivity) activity).resources.getDrawable(resID);
                        menuItem.setIcon(icon);                        
                    }

                    menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            PluginResult progressResult = new PluginResult(PluginResult.Status.OK, option.title);
                            progressResult.setKeepCallback(true);
                            option.callbackContext.sendPluginResult(progressResult);

                            DrawerLayout drawer = (DrawerLayout) ((TgwActivity) activity).findViewById(((TgwActivity) activity).resources.getIdentifier("drawer_layout", "id", ((TgwActivity) activity).package_name));
                            drawer.closeDrawer(GravityCompat.START);
                            return true;
                        }
                    });

                }

            }
        });

    }

}
