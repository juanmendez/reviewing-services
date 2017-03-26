package info.juanmendez.android.reviewservices;

import android.content.Intent;
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
    }

    public void onStop(Bundle outState){
        Icepick.saveInstanceState(this, outState);
    }

    public void doFibonacci( int value ){
        Intent intent = new Intent(activity, FibonacciService.class);
        intent.putExtra(Codes.FIELD_REQUEST, value );
        activity.startService(intent);
    }
}