package com.wbq.event.javabinderipcdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    IDogInterface service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent =new Intent(this,AppDogService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                 service= (IDogInterface) AppDogServiceProxy.asInterface(iBinder);
                try {
                    service.addDog(new Dog(20, "祁长号"));
                    TextView tv = (TextView) findViewById(R.id.sample_text);
                    tv.setText(service.getDogList().toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, Context.BIND_AUTO_CREATE);
        // Example of a call to a native method

//        tv.setText(stringFromJNI());
        try {
            if (service==null){
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            }else {
                service.addDog(new Dog(20, "祁长号"));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {if (null!=service) {
            Toast.makeText(this, service.getDogList().toString(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "get null", Toast.LENGTH_SHORT).show();
        }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
