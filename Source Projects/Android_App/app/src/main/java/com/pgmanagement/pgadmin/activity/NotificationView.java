package com.pgmanagement.pgadmin.activity;

import android.os.Bundle;
import android.app.Activity;
import com.pgmanagement.pgadmin.R;

public class NotificationView extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
    }
}