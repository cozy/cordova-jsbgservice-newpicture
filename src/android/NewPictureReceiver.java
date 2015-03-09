package io.cozy.jsbackgroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.net.wifi.WifiManager;
import android.hardware.Camera;
import android.util.Log;

import android.content.SharedPreferences;
import android.content.Context;

public class NewPictureReceiver extends BroadcastReceiver {
    private static final String TAG = "JSBackgroundPlugin";

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(
            JSBackgroundServicePlugin.PREFERENCES, Context.MODE_PRIVATE);


        if ((Camera.ACTION_NEW_PICTURE.equals(intent.getAction()) ||
            (WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION
                    .equals(intent.getAction()) &&
                intent.getBooleanExtra(
                    WifiManager.EXTRA_SUPPLICANT_CONNECTED, false))) &&
            sharedPrefs.getBoolean(
                JSBackgroundServicePlugin.PREF_LISTEN_NEW_PICTURE, false)
            ) {
            LifecycleManager manager = new LifecycleManager(context);
            manager.debouncedStart("NEW_PICTURE");
        }
    }
}
