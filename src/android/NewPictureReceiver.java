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

        boolean isNewPicture = Camera.ACTION_NEW_PICTURE
                                .equals(intent.getAction());
        boolean isWifiChange = WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION
                                .equals(intent.getAction());
        boolean isWifiConnected = intent.getBooleanExtra(
                            WifiManager.EXTRA_SUPPLICANT_CONNECTED, false);
        boolean isListeningConfig = sharedPrefs.getBoolean(
                    JSBackgroundServicePlugin.PREF_LISTEN_NEW_PICTURE, false);

        if ((isNewPicture || isWifiChange && isWifiConnected) &&
            isListeningConfig) {
            LifecycleManager manager = new LifecycleManager(context);
            manager.debouncedStart("NEW_PICTURE");
        }
    }
}
