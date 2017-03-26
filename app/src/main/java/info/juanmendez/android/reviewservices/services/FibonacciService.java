package info.juanmendez.android.reviewservices.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import info.juanmendez.android.reviewservices.dependencies.Codes;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Juan Mendez on 3/24/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class FibonacciService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        runFibonacci( intent.getIntExtra(Codes.FROM_FIELD_REQUEST, 0));
        return START_STICKY;
    }

    public void runFibonacci(int febCount ){

         Single.<String>create(e -> {
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
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(s -> {
              stopSelf();
              Intent intent = new Intent(Codes.LOCAL_BROADCASTER);
              intent.putExtra(Codes.TO_FIELD_REPLY, s );
              sendBroadcast( intent );
          }, throwable -> {
              stopSelf();
              Intent intent = new Intent(Codes.LOCAL_BROADCASTER);
              intent.putExtra(Codes.TO_FIELD_REPLY, throwable.getMessage() );
              sendBroadcast( intent );
          });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
