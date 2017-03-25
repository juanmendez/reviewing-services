package info.juanmendez.android.reviewservices.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import io.reactivex.Single;
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

    public Single runFibonacci(int febCount ){

        return  Single.create(e -> {
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
        }).subscribeOn(Schedulers.computation())
          .observeOn(AndroidSchedulers.mainThread());
    }
}
