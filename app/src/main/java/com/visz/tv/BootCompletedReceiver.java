package com.visz.tv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedReceiver extends BroadcastReceiver {
    private static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_BOOT_COMPLETED)) {
            Intent data = new Intent(context, MainActivity.class);
            data.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(data);
        }
    }
}
