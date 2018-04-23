package app.sms.com.smstracker;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.Manifest;

/**
 * Created by jessicaannor on 25/03/2018.
 */

public class layout_main_navigation extends AppCompatActivity{

    private static final int PERMISSION_REQUEST_RECEIVE_SMS = 0;

    public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(layout_main_navigation.this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            Log.d("PERMISSION","Not granted");
            if (ActivityCompat.shouldShowRequestPermissionRationale(layout_main_navigation.this,
                    Manifest.permission.RECEIVE_SMS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            }else{

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(layout_main_navigation.this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        PERMISSION_REQUEST_RECEIVE_SMS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            }

        }

    }



    public void navigateGraph(View view){
        Intent intent = new Intent(layout_main_navigation.this, MainActivity.class);
        startActivity(intent);
    }

    public void navigateAdd(View view){
        Intent intent = new Intent(layout_main_navigation.this, addTransaction.class);
        startActivity(intent);
    }

    public void navigateView(View view){
        Intent intent = new Intent(layout_main_navigation.this, ShowTransactions.class);
        startActivity(intent);
    }

    public void navigateSavings(View view){
        Intent intent = new Intent(layout_main_navigation.this, Savings.class);
        startActivity(intent);
    }

    public void home(){

    }

}
