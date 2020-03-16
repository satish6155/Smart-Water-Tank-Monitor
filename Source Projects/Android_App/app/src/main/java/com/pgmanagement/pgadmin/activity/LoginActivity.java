package com.pgmanagement.pgadmin.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.transactional.User;
import com.pgmanagement.pgadmin.volly.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {


   // String baseURL;
    TextView goToController,urlText,changeUrl,notific;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        goToController= (TextView) findViewById(R.id.goToController);
        urlText= (TextView) findViewById(R.id.urlText);
        changeUrl = (TextView) findViewById(R.id.changeUrl);
        notific =  (TextView) findViewById(R.id.notific);


        notific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });

        changeUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                urlText.setVisibility(View.VISIBLE);

            }
        });
        goToController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

               try {

                   Intent i = new Intent(LoginActivity.this, WebViewActivity.class);
                   i.putExtra("url", urlText.getText().toString());
                   startActivity(i);
               }catch (Exception e){
                   showMessage("In makeGetReq_exception",e.getMessage());
               }


            }
        });
    }
    @Override
    protected void onResume(){

        super.onResume();
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    /*private void addNotification() {

        showMessage("Hi","1");
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                      //  .setSmallIcon(R.drawable.abc)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        showMessage("Hi","2");
        try {
            Intent notificationIntent = new Intent(this, LoginActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);
            showMessage("Hi", "3");
            // Add as notification
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //    showMessage("Hi", "4");
            manager.notify(0, builder.build());
        } catch (Exception e) {
            showMessage("Error", e.toString());
        }
       // showMessage("Hi","5");
    }*/
    public void addNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        Intent intent = new Intent(this, NotificationView.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
      //  builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Alert!");
        builder.setContentText("Your tank is almost empty!");
        builder.setSubText("Tap to turn ON the tank");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }
}


