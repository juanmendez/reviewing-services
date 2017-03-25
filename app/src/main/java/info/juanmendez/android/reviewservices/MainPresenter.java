package info.juanmendez.android.reviewservices;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import info.juanmendez.android.reviewservices.helpers.FibServiceConnection;
import info.juanmendez.android.reviewservices.services.FibonacciService;

/**
 * Created by Juan Mendez on 3/25/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class MainPresenter {

    private MainActivity activity;
    private FibServiceConnection connection = new FibServiceConnection();

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }

    public void onStart(){
        startConnection();
    }

    public void onStop(){
        endConnection();
    }

    public void doFibonacci( int value ){
        connection.getService().runFibonacci( value )
                .subscribe(s -> {
                    Log.i("MainActivity", s );
                });
    }

    private void startConnection(){
        if (!connection.getBound()) {

            Intent intent = new Intent(activity, FibonacciService.class);
            activity.bindService( intent, connection, Context.BIND_AUTO_CREATE );
        }
    }

    private void endConnection(){
        if (connection.getBound()) {
            activity.unbindService(connection);
            connection.setBound( false );
        }
    }
}