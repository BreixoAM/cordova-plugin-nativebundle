package com.breixoam.tgw;


import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaActivity;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.Intent;
import android.util.Log;

public class Tgw extends CordovaPlugin {

    private static final String TAG = "TGWDebug Tgw.java";

    public static Frame frame;
    public static CordovaActivity activity;

    /**
     * Constructor
     */
    public Tgw() {
    }

    /**
     * Sets the context of the Command.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.activity = ((CordovaActivity) cordova.getActivity());
    }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if ("init".equals(action)) {

            //Check if the cordova activity is the de TgwActivity
            if ("TgwActivity".equals(cordova.getActivity().getClass().getSimpleName())) {
                callbackContext.success();
                return true;
            }

            this.frame = new Frame(args.getString(0), args.getString(1), args.getString(2));

            //Replace main activiy with tgw activity
            Intent i = new Intent(cordova.getActivity(), TgwActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            cordova.getActivity().startActivity(i);
            cordova.getActivity().overridePendingTransition(0, 0);
            return true;

        } else if (action.startsWith("frame")) {

            return this.parseFrame(action, args, callbackContext);

        } else if (action.startsWith("header")) {

            return this.parseHeader(action, args, callbackContext);

        } else if (action.startsWith("slider")) {

            return this.parseSlider(action, args, callbackContext);

        } else if (action.startsWith("content")) {

            return this.parseContent(action, args, callbackContext);

        } else {
            return false;
        }
        
    }

    //Frame operations
    public boolean parseFrame(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        String parseAction = action.substring("frame".length());
        parseAction = Character.toLowerCase(parseAction.charAt(0)) + (parseAction.length() > 1 ? parseAction.substring(1) : "");

        if ("getMainColor".equals(parseAction)) {

            String title = this.frame.getMainColor();
            callbackContext.success(title);

        } else if ("setMainColor".equals(parseAction)) {

            this.frame.setMainColor(args.getString(0));
            callbackContext.success();

        } else if ("getMainColorDark".equals(parseAction)) {

            String title = this.frame.getMainColorDark();
            callbackContext.success(title);

        } else if ("setMainColorDark".equals(parseAction)) {

            this.frame.setMainColorDark(args.getString(0));
            callbackContext.success();

        } else if ("showSpinner".equals(parseAction)) {

            this.frame.showSpinner();
            callbackContext.success();

        } else if ("hideSpinner".equals(parseAction)) {

            this.frame.hideSpinner();
            callbackContext.success();
            
        } else {
            return false;
        }

        return true;
    }

    //Header operations
    public boolean parseHeader(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        String parseAction = action.substring("header".length());
        parseAction = Character.toLowerCase(parseAction.charAt(0)) + (parseAction.length() > 1 ? parseAction.substring(1) : "");

        if ("getTitle".equals(parseAction)) {

            String title = this.frame.header.getTitle();
            callbackContext.success(title);

        } else if ("setTitle".equals(parseAction)) {

            this.frame.header.setTitle(args.getString(0));
            callbackContext.success();

        } else if ("setItemsColor".equals(parseAction)) {

            this.frame.header.setItemsColor(args.getString(0));
            callbackContext.success();

        } else if ("showBackButton".equals(parseAction)) {

            this.frame.header.showBackButton();
            callbackContext.success();

        } else if ("showSliderButton".equals(parseAction)) {

            this.frame.header.showSliderButton();
            callbackContext.success();

        } else if ("addOption".equals(parseAction)) {

            this.frame.header.addOption(callbackContext, args.getString(0));

        } else if ("clearOptions".equals(parseAction)) {

            this.frame.header.clearOptions();

        } else if ("addAction".equals(parseAction)) {

            this.frame.header.addAction(callbackContext, args.getString(0), args.getString(1));

        } else if ("clearActions".equals(parseAction)) {

            this.frame.header.clearActions();

        } else if ("update".equals(parseAction)) {

            this.frame.header.update();

        } else {
            return false;
        }

        return true;
    }

    //Slider operations
    public boolean parseSlider(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        String parseAction = action.substring("slider".length());
        parseAction = Character.toLowerCase(parseAction.charAt(0)) + (parseAction.length() > 1 ? parseAction.substring(1) : "");

        if ("setColor".equals(parseAction)) {

            this.frame.slider.setColor(args.getString(0));
            callbackContext.success();

        } else if ("getTitle1".equals(parseAction)) {

            String title = this.frame.slider.getTitle1();
            callbackContext.success(title);

        } else if ("setTitle1".equals(parseAction)) {

            this.frame.slider.setTitle1(args.getString(0));
            callbackContext.success();

        } else if ("getTitle2".equals(parseAction)) {

            String title = this.frame.slider.getTitle2();
            callbackContext.success(title);

        } else if ("setTitle2".equals(parseAction)) {

            this.frame.slider.setTitle2(args.getString(0));
            callbackContext.success();

        } else if ("setIcon".equals(parseAction)) {

            this.frame.slider.setIcon(args.getString(0), args.getString(1), args.getString(2));
            callbackContext.success();

        } else if ("clearOptions".equals(parseAction)) {

            this.frame.slider.clearOptions();

        } else if ("addOption".equals(parseAction)) {

            this.frame.slider.addOption(callbackContext, args.getString(0), args.getString(1), args.getString(2));

        } else if ("update".equals(parseAction)) {

            this.frame.slider.update();

        } else {
            return false;
        }

        return true;
    }

    //Content operations
    public boolean parseContent(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        String parseAction = action.substring("content".length());
        parseAction = Character.toLowerCase(parseAction.charAt(0)) + (parseAction.length() > 1 ? parseAction.substring(1) : "");

        if ("enableBounce".equals(parseAction)) {

            this.frame.content.enableBounce();
            callbackContext.success();

        } else if ("disableBounce".equals(parseAction)) {

            this.frame.content.disableBounce();
            callbackContext.success();

        } else if ("showScrollbar".equals(parseAction)) {

            this.frame.content.showScrollbar();
            callbackContext.success();

        } else if ("hideScrollbar".equals(parseAction)) {

            this.frame.content.hideScrollbar();
            callbackContext.success();

        } else if ("fadeIn".equals(parseAction)) {

            this.frame.content.fadeIn(callbackContext);
            callbackContext.success();

        } else if ("fadeOut".equals(parseAction)) {

            this.frame.content.fadeOut(callbackContext);
            callbackContext.success();

        } else if ("hide".equals(parseAction)) {

            this.frame.content.hide();
            callbackContext.success();

        } else if ("show".equals(parseAction)) {

            this.frame.content.show();
            callbackContext.success();

        } else {
            return false;
        }

        return true;
    }

}
