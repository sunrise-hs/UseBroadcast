package com.example.hanshu.usebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by HanShu on 2016/7/17.
 */
public class MessReceiver extends BroadcastReceiver {
    private static final String TAG = "MessReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] objs=(Object[])intent.getExtras().get("pdus");
        for (Object obj:objs){
            SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) obj);
            String body=smsMessage.getMessageBody();
            String sender=smsMessage.getOriginatingAddress();
            Log.i(TAG, "onReceive: "+body);
            Log.i(TAG, "onReceive: "+sender);
            if("10086".equals(sender)){
                Log.i(TAG, "onReceive: 垃圾短信，立即终止");
                abortBroadcast();
            }
        }
    }
}
