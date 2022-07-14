package com.example.hospital;

import android.app.Application;

import com.example.hospital.local.MyRoomDatabase;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyRoomDatabase.initRoom(this);
    }
}
