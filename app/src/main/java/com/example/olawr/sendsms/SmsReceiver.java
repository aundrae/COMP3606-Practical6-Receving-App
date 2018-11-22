package com.example.olawr.sendsms;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    String str = "";
    Context context=null;
    SmsMessage[] recMsg = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Toast.makeText(context,"Reply Sent",Toast.LENGTH_SHORT).show();


        if(bundle!=null){
            Object[] pdus = (Object[])bundle.get("pdus");
            recMsg = new SmsMessage[pdus.length];
            for(int i=0; i<recMsg.length;i++){
                String format = bundle.getString("format");
                recMsg[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                str += "SMS Message received from: " +
                recMsg[i].getOriginatingAddress();
                str += "=>";
                str += recMsg;
            }
            Toast.makeText(context,str,Toast.LENGTH_LONG).show();

            TextView textView=(TextView)((Activity)context).findViewById(R.id.message);
            textView.setText(str);
        }
    }
}
