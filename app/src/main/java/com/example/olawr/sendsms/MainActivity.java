package com.example.olawr.sendsms;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.Manifest;

public class MainActivity extends AppCompatActivity {
    Button reply;
    String phonenumber, message;
    private int SMS_PERMISSION=1;
    private SmsManager manager = SmsManager.getDefault();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
            requestSMSPermission();
        }
        SmsReceiver sms=new SmsReceiver();
        reply = findViewById(R.id.reply);
        phonenumber = "6846142";
        message = "order received";

    }

    public void replier(View v){
        try {
            SmsManager smsman = SmsManager.getDefault();
            //SmsReceiver.
            smsman.sendTextMessage(phonenumber,null,message,null,null);
            Toast.makeText(getApplicationContext(),"Reply Sent",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Reply failed to send",Toast.LENGTH_SHORT).show();
        }
    }

    public void requestSMSPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SMS_PERMISSION);
    }
}
