package com.breixoam.tgw;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.CordovaWebView;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.util.Log;
import android.graphics.Color;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.content.Context;
import android.webkit.WebView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

public class TgwActivity extends CordovaActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "TGWDebug TgwActivity.java";

    protected String package_name;
    protected Resources resources;
    protected Toolbar toolBar;
    protected NavigationView navigationView;
    protected ActionBarDrawerToggle toggle;
    protected View browser;
    protected ProgressDialog progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        progress = new ProgressDialog(this);

        overridePendingTransition(0, 0);

        loadUrl(launchUrl);

        package_name = getApplication().getPackageName();
        resources = getApplication().getResources();

        this.browser = (View) this.appView.getView();

        View cordovaView = (View) this.appView.getView();
        ViewGroup vg = (ViewGroup)cordovaView.getParent();
        if (vg != null) {
            vg.removeView(cordovaView);
        }
        cordovaView.setVerticalScrollBarEnabled(true);
        cordovaView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        setContentView(resources.getIdentifier("activity_tgw", "layout", package_name));
        
        Toolbar toolbar = (Toolbar) findViewById(resources.getIdentifier("toolbar", "id", package_name));
        this.toolBar = toolbar;

        //Init ui with values conf
        if (Tgw.frame != null) {

            //Set title
            toolbar.setTitle(Tgw.frame.header.getTitle());

            //Update color toolbar
            toolbar.setBackgroundColor(Color.parseColor(Tgw.frame.getMainColor()));

            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
            if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP){
                
                //Update color statusbar
                final Window window = this.getWindow();
                window.setStatusBarColor(Color.parseColor(Tgw.frame.getMainColorDark()));
                //Update color activitydesc
                ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
                for (ActivityManager.AppTask appTask : activityManager.getAppTasks()) {
                    if (appTask.getTaskInfo().id == this.getTaskId()) {
                        ActivityManager.TaskDescription description = appTask.getTaskInfo().taskDescription;
                        ActivityManager.TaskDescription a = new ActivityManager.TaskDescription(description.getLabel(), description.getIcon(), Color.parseColor(Tgw.frame.getMainColor()));
                        this.setTaskDescription(a);
                    }
                }
            }

        }

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(resources.getIdentifier("drawer_layout", "id", package_name));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        this.toggle = toggle;

        NavigationView navigationView = (NavigationView) findViewById(resources.getIdentifier("nav_view", "id", package_name));
        navigationView.setNavigationItemSelectedListener(this);
        this.navigationView = navigationView;

        RelativeLayout content = (RelativeLayout) findViewById(resources.getIdentifier("content", "id", package_name));
        content.addView(cordovaView);

        //Click listener vavigation icon
        final ActionBarDrawerToggle finalToggle = toggle;
        final DrawerLayout finalDrawer = drawer;
        final CordovaWebView finalBrowser = this.appView;

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!finalToggle.isDrawerIndicatorEnabled()) {

                    if (finalBrowser.canGoBack()) {
                        finalBrowser.backHistory();
                    } else {
                        onBackPressed();
                    }
                    
                } else {
                    finalDrawer.openDrawer(GravityCompat.START);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(resources.getIdentifier("drawer_layout", "id", package_name));
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return true;
    }

}
