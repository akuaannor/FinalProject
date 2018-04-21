package app.sms.com.smstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by jessicaannor on 25/03/2018.
 */

public class layout_main_navigation extends AppCompatActivity{

    public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
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
