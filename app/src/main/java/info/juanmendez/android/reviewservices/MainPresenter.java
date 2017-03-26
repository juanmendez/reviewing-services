package info.juanmendez.android.reviewservices;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

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
    private Handler handler = new Handler();
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
        intent.putExtra(Codes.FROM_FIELD_REQUEST, value );
        intent.putExtra(Codes.FROM_FIELD_RECEIVER, new FiboReceiver(null) );
        activity.startService(intent);
    }

    class FiboReceiver extends ResultReceiver{

        public FiboReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);


            if (resultCode == Codes.TO_CODE_REPLY && resultData != null) {

                final String value = resultData.getString(Codes.TO_FIELD_REPLY);

                handler.post(() -> {
                   activity.setResultValue(value);
                });
            }
        }
    }
}