package com.android.mytest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.INetworkManagementService;
import android.os.ServiceManager;
import android.util.Slog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyTestActivity extends Activity {
    /** Called when the activity is first created. */
    Button myButton1;
    TextView myText1;

    Button myButton2;
    TextView myText2;
    INetworkManagementService mNMService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myButton1 = (Button)findViewById(R.id.myButton1);
        myText1 = (TextView)findViewById(R.id.myText1);

        myButton2 = (Button)findViewById(R.id.myButton2);
        myText2 = (TextView)findViewById(R.id.myText2);

        final IBinder b = ServiceManager.getService(Context.NETWORKMANAGEMENT_SERVICE);
        mNMService = INetworkManagementService.Stub.asInterface(b);

        myButton1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {

            }
        });

        myButton2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {

            }
        });

        //startService(new Intent(this, TestService.class));
    }
}