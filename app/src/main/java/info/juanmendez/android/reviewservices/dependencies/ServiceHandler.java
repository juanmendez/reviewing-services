package info.juanmendez.android.reviewservices.dependencies;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Juan Mendez on 3/25/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class ServiceHandler extends Handler {

    @Override
    public void handleMessage(Message msg) {

        if( msg.what == Codes.FROM_CODE_REQUEST){
            Bundle bundle = msg.getData();
            Messenger messenger = msg.replyTo;
            int febCount = bundle.getInt(Codes.FROM_FIELD_REQUEST, 0);

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

                Thread.sleep(5000);
                e.onSuccess( result );
            }).subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        send( messenger, s );
                    }, throwable -> {
                        send( messenger, throwable.getMessage() );
                    });
        }
    }

    private void send( Messenger messenger, String fibString ){

        Message msg = Message.obtain(null, Codes.TO_CODE_REPLY);
        Bundle bundle = new Bundle();
        bundle.putString(Codes.TO_FIELD_REPLY, fibString );
        msg.setData(bundle);

        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
