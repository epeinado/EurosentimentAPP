package com.eurosentiment.EurosentimentAPP;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;
import com.eurosentiment.EurosentimentAPP.twitter.TwitterService;

import java.util.ArrayList;

/**
 * User: epeinado
 * Date: 23/07/14
 * Time: 12:18
 */
public class DisplayTweets extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweets);

        ListView lista = (ListView) findViewById(R.id.tweetList);


        // Obtenemos el mensaje de la intención
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        ArrayList arraydir = new ArrayList();
        if (networkInfo != null && networkInfo.isConnected()) {
            TwitterService twitterService = new TwitterService();
            arraydir = twitterService.searchTwitter(message);
        } else {
            System.out.println("No network connection available.");
        }

        // Creo el adapter personalizado
        AdapterTweets adapter = new AdapterTweets(this, arraydir);

        // Lo aplico
        lista.setAdapter(adapter);
    }

}
