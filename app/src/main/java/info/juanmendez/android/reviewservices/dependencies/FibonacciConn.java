package info.juanmendez.android.reviewservices.dependencies;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;


/**
 * Created by Juan Mendez on 3/25/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class FibonacciConn implements ServiceConnection {
    Messenger messenger;
    Boolean isBound = false;

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        messenger = new Messenger(iBinder);
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

    public void sendMessage( Message msg ){
        try {
            messenger.send( msg );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
