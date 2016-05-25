package com.studio.nitz.pollpedia;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

/**
 * Created by nitinpoddar on 12/22/15.
 */
public class PollPedia extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this,
                getResources().getString(R.string.parse_app_id),
                getResources().getString(R.string.parse_client_key));
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
