package info.juanmendez.android.reviewservices.helpers;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import info.juanmendez.android.reviewservices.services.FibonacciService;


/**
 * Created by Juan Mendez on 3/25/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class FibServiceConnection implements ServiceConnection {

    FibonacciService service;
    Boolean isBound = false;

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        FibonacciService.FibBinder binder = (FibonacciService.FibBinder) iBinder;
        service = binder.getService();
        isBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
    }

    public void setBound(Boolean bound) {
        isBound = bound;
    }

    public Boolean getBound() {
        return isBound;
    }

    public FibonacciService getService() {
        return service;
    }
}
