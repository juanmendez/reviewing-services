package info.juanmendez.android.reviewservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import icepick.Icepick;
import icepick.State;
import info.juanmendez.android.reviewservices.dependencies.Codes;
import info.juanmendez.android.reviewservices.services.FibonacciService;

/**
 * Created by Juan Mendez on 3/25/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class MainPresenter {

    private MainActivity activity;
    @State String fibString;

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }

    public void onStart(Bundle savedInstanceState){
        Icepick.restoreInstanceState( this, savedInstanceState );
        activity.setResultValue( fibString );
        startReceiver();
    }

    public void onStop(Bundle outState){
        Icepick.saveInstanceState(this, outState);
        endReceiver();
    }

    public void doFibonacci( int value ){
        Intent intent = new Intent(activity, FibonacciService.class);
        intent.putExtra(Codes.FROM_FIELD_REQUEST, value );
        activity.startService( intent );
    }

    private void startReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Codes.LOCAL_BROADCASTER);
        activity.registerReceiver(broadcastReceiver, intentFilter);
    }

    private void endReceiver(){
        activity.unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra(Codes.TO_FIELD_REPLY);
            activity.setResultValue(result);
        }
    };
}