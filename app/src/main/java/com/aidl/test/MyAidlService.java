package com.aidl.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.aidl.test.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjian on 2018/4/20.
 */

public class MyAidlService extends Service {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Person> mPersons;


    private IBinder mIBinder = new IMyAidl.Stub() {

        @Override
        public void addPerson(Person person) throws RemoteException {
            mPersons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersons;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPersons = new ArrayList<>();
        Log.d(TAG, "------onBind");
        return mIBinder;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG, "------onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "------onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "------onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "------onDestroy");
        super.onDestroy();
    }
}
