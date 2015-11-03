package com.android.mytest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Receives system broadcasts (boot, network connectivity)
 */
public class MyTestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.e("@@@@", "receiver boot completed, start service");
            //context.startService(new Intent(context, TestService.class));
        }
    }
}
