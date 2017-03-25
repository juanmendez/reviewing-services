package info.juanmendez.android.reviewservices.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.Nullable;

import info.juanmendez.android.reviewservices.helpers.ServiceHandler;


/**
 * Created by Juan Mendez on 3/24/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class FibonacciService extends Service {

    private Messenger messenger = new Messenger( new ServiceHandler() );

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
