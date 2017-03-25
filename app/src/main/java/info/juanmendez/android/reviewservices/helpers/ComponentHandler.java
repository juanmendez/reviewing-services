package info.juanmendez.android.reviewservices.helpers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


/**
 * Created by Juan Mendez on 3/25/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 */

public class ComponentHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {

        Bundle bundle = msg.getData();
        String value = bundle.getString("value");
    }
}
