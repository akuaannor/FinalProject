package app.sms.com.smstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jessicaannor on 25/03/2018.
 */

public class layout_main_navigation extends AppCompatActivity{

    public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
    }


    public void navigateAdd(){
        Intent intent = new Intent(layout_main_navigation.this, addTransaction.class);
        startActivity(intent);
    }


    public void navigateView(){
        Intent intent = new Intent(layout_main_navigation.this, ShowTransactions.class);
        startActivity(intent);
    }

    public void navigateGraphs(){

    }

    public void navigateSavings(){

    }

    public void home(){

    }

}
