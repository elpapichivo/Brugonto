package com.example.yanina.mysong.View;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by yanina on 13/12/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

   public static final String TAG = "noticias";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Token;" + token);

    }
}
