package com.aidl.test;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aidl.test.bean.Person;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView content;
    private Button btnBind, btnUnBind, btnAdd, btnUpdate;
    private IMyAidl mIBinder;
    int i = 1;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIBinder = IMyAidl.Stub.asInterface(iBinder);
            Toast.makeText(MainActivity.this, "onServiceConnected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIBinder = null;
            Toast.makeText(MainActivity.this, "onServiceDisconnected", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (TextView) findViewById(R.id.info_tv);
        btnBind = (Button) findViewById(R.id.btn_bind);
        btnUnBind = (Button) findViewById(R.id.btn_unbind);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(new Intent(getApplicationContext(), MyAidlService.class), serviceConnection, BIND_AUTO_CREATE);
//                startService(new Intent(getApplicationContext(), MyAidlService.class));
            }
        });
        btnUnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);
//                stopService(new Intent(getApplicationContext(), MyAidlService.class));
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mIBinder != null) {
                        Person person = new Person("person-" + (i++), 20 + i);
                        mIBinder.addPerson(person);
                        Toast.makeText(MainActivity.this, person.toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mIBinder != null) {
                        List<Person> list = mIBinder.getPersonList();
                        content.setText(list.toString());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
