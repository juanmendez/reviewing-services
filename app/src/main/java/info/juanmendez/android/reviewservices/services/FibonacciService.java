package info.juanmendez.android.reviewservices.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import info.juanmendez.android.reviewservices.dependencies.Codes;


/**
 * Created by Juan Mendez on 3/24/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class FibonacciService extends IntentService {

    public FibonacciService() {
        super("FibonacciService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        ResultReceiver resultReceiver = intent.getParcelableExtra(Codes.FROM_FIELD_RECEIVER);
        int febCount = intent.getIntExtra(Codes.FROM_FIELD_REQUEST, 0);


        Bundle bundle = new Bundle();
        bundle.putString(Codes.TO_FIELD_REPLY, runFibonacci(febCount));
        resultReceiver.send(Codes.TO_CODE_REPLY, bundle);
    }

    private String runFibonacci(int febCount ){

        String result = "";

        int[] feb = new int[febCount];
        feb[0] = 0;
        feb[1] = 1;
        for(int i=2; i < febCount; i++){
            feb[i] = feb[i-1] + feb[i-2];
        }

        for(int i=0; i< febCount; i++) {
            result += feb[i] + " ";
        }

        return result;
    }
}
