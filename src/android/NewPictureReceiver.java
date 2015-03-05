package io.cozy.jsbackgroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NewPictureReceiver extends BroadcastReceiver {
    //private static final String TAG = "JSBackgroundPlugin";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Cursor cursor = arg0.getContentResolver().query(arg1.getData(),      null,null, null, null);
        // cursor.moveToFirst();
        // String image_path = cursor.getString(cursor.getColumnIndex("_data"));
        // Toast.makeText(arg0, "New Photo is Saved as : " + image_path, 1000).show();
        LifecycleManager manager = new LifecycleManager(context);
        manager.debouncedStart("NEW_PICTURE");
    }
}
