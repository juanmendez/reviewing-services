package info.juanmendez.android.reviewservices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;

import icepick.Icepick;
import icepick.State;
import info.juanmendez.android.reviewservices.helpers.ComponentHandler;
import info.juanmendez.android.reviewservices.helpers.FibonacciConn;
import info.juanmendez.android.reviewservices.services.FibonacciService;

/**
 * Created by Juan Mendez on 3/25/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class MainPresenter {

    private MainActivity activity;
    private Messenger messenger = new Messenger(new ComponentHandler());
    private FibonacciConn connection = new FibonacciConn();
    @State String fibString;

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }

    public void onStart(Bundle savedInstanceState){
        Icepick.restoreInstanceState( this, savedInstanceState );
        startConnection();
        activity.setResultValue( fibString );
    }

    public void onStop(Bundle outState){
        Icepick.saveInstanceState(this, outState);
        endConnection();
    }

    public void doFibonacci( int value ){

        Message msg = Message.obtain(null, 43);
        Bundle bundle = new Bundle();
        bundle.putInt("value", value );

        msg.setData(bundle);
        msg.replyTo = messenger;
        connection.sendMessage( msg );
    }

    private void startConnection(){
        Intent intent = new Intent(activity, FibonacciService.class);
        activity.bindService( intent, connection, Context.BIND_AUTO_CREATE );
    }

    private void endConnection(){
        if (connection.getBound()) {
            activity.unbindService(connection);
            connection.setBound( false );
        }
    }
}