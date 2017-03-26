package info.juanmendez.android.reviewservices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;

import icepick.Icepick;
import icepick.State;
import info.juanmendez.android.reviewservices.dependencies.Codes;
import info.juanmendez.android.reviewservices.dependencies.FibonacciConn;

/**
 * Created by Juan Mendez on 3/25/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class MainPresenter {

    private MainActivity activity;
    private Messenger messenger = new Messenger( new MainHandler() );
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

        Message msg = Message.obtain(null, Codes.FROM_CODE_REQUEST);
        Bundle bundle = new Bundle();
        bundle.putInt(Codes.FROM_FIELD_REQUEST, value );

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

    private class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String value = bundle.getString(Codes.TO_FIELD_REPLY);
            activity.setResultValue(value);
        }
    }
}