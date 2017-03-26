package info.juanmendez.android.reviewservices.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Juan Mendez on 3/24/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class FibonacciService extends IntentService {

    public FibonacciService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    private void runFibonacci(int febCount ){

        Single.<String>create(e -> {
            Log.i( "MainActivity", "doing computation in " + Thread.currentThread().getName() );
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

            Thread.sleep(3000);
            e.onSuccess( result );
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
