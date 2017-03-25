package info.juanmendez.android.reviewservices.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Juan Mendez on 3/24/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class FibonacciService extends Service {

    private Binder fibonacciBinder = new Binder(){
        FibonacciService getService() {
            return FibonacciService.this;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return fibonacciBinder;
    }

    public Completable runFibonacci(int febCount ){

        /**
         * This message showed up when I tried instead to run callable in mainThread
         * 03-24 23:56:53.334 3182-3192/info.juanmendez.android.reviewservices I/art: Background partial concurrent mark sweep GC freed 46(1776B) AllocSpace objects, 64(13MB) LOS objects, 39% free, 5MB/9MB, paused 6.469ms total 58.319ms
         */
        return Completable.fromAction(() -> {

            String result = "";

            // See more at: http://www.java2novice.com/java-interview-programs/fibonacci-series/

            int[] feb = new int[febCount];
            feb[0] = 0;
            feb[1] = 1;
            for(int i=2; i < febCount; i++){
                feb[i] = feb[i-1] + feb[i-2];
            }

            for(int i=0; i< febCount; i++){
                result +=  feb[i] + " ";
            }


        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }
}
