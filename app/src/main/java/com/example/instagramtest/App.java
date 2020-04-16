package com.example.instagramtest;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("OKRs917HO2iQxl9MLPbVwxH8OsMP89oVbguEgVcA")
                // if defined
                .clientKey("SQcucBFBp50RzY3Ct3Yqkp26kXRd2gsVcicHPRBf")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
