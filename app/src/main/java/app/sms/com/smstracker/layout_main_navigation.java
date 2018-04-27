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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jessicaannor on 25/03/2018.
 */

public class layout_main_navigation extends AppCompatActivity{
    private DatabaseReference myRef;
    private DatabaseReference myRef2;
    private double totalcredit, targetvalue ;
    private double totaldebit, formattedval ;
    private double progressvalue;
    private double progresspercentage;
    private double progresspercentagerd;
    private TextView percent, progressmessage;
    private TextView debcreddiff;
    private double save = 20.00;
    private static DecimalFormat DF = new DecimalFormat("0.00E0");
    String [] tips = new String[]{"Record your expenses", "Make a budget","Plan on saving money","Choose something to save for",
            "Prioritize, prioritize, prioritize!", "Choose Sikasem!", "Automatically save","Watch your savings grow"};
    //    String[] tips = getBaseContext().getResources().getStringArray(R.array.tips);
    String randomStr = tips[new Random().nextInt(tips.length)];


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

    @Override
    protected void onStart() {
        super.onStart();
        //Initializing DB
        myRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("transactions");
        myRef2 = FirebaseDatabase.getInstance().getReference()
                .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profile");


        com.google.firebase.database.ValueEventListener postListener = new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //https://www.quora.com/How-do-I-read-all-child-key-values-of-a-child-from-Firebase-database-in-Android
                Iterable<DataSnapshot> transactions = dataSnapshot.getChildren();
                ArrayList<Cash> tx = new ArrayList<>();
                for (DataSnapshot cash : transactions) {
                    Cash c = cash.getValue(Cash.class);
                    Log.d("cash:: ", c.purpose + "" + c.amount + "" + c.date);
                    tx.add(c);
                }

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(layout_main_navigation.this, "Failed to load transactions.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        myRef.addListenerForSingleValueEvent(postListener);
        // [END post_value_event_listener]

//listener for a new transaction that's a debit
        com.google.firebase.database.Query txType = myRef.orderByChild("type").equalTo("Debit");
        txType.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> transactions = dataSnapshot.getChildren();
                ArrayList<Cash> tx = new ArrayList<>();
                for (DataSnapshot cash : transactions) {
                    Cash c = cash.getValue(Cash.class);
                    Log.d("Debits:: ", c.purpose + "" + c.amount + "" + c.date);
                    tx.add(c);
                    totaldebit += c.amount;

                    //method to listen for a change in target value and update accordingly
                }
                progressvalue = totalcredit - totaldebit;
                String progressvaluestr = Double.toString(progressvalue);
                progresspercentage = ((totalcredit - totaldebit) / totalcredit) * 100;
//                    progresspercentagerd = DF.format(progresspercentage);
                String per = String.format(Double.toString(progresspercentage), "%.2f");
                //set alerts based on the 50/30/20 rule, where at least 20 % of income is saved.


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(layout_main_navigation.this, "Failed to load transactions.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        });
        myRef.addListenerForSingleValueEvent(postListener);
        // [END post_value_event_listener]
        //method to listen for a change in target value and update accordingly







        //Listener fo a change in target value
        myRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> profile = dataSnapshot.getChildren();
                ArrayList<User> pr = new ArrayList<>();
                for (DataSnapshot user : profile) {

                    User u = user.getValue(User.class);
                    Log.d("User:: ", "target is" + u.target);
                    pr.add(u);
                    targetvalue = u.target;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(layout_main_navigation.this, "Failed to load target value.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        // [END post_value_event_listener]
        myRef2.addListenerForSingleValueEvent(postListener);



//listener for a new transaction that's a debit
        com.google.firebase.database.Query txType2 = myRef.orderByChild("type").equalTo("Credit");
        txType2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> transactions = dataSnapshot.getChildren();
                ArrayList<Cash> tx = new ArrayList<>();
                for (DataSnapshot cash : transactions) {
                    Cash c = cash.getValue(Cash.class);
                    Log.d("Credits:: ", c.purpose + "" + c.amount + "" + c.date);
                    tx.add(c);
                    totalcredit += c.amount;
                    Log.d("tc:: ", "cedits"+ totalcredit);
                    progressvalue = totalcredit - totaldebit;

                }
                String progressvaluestr = String.format(Double.toString(progressvalue), "%.2f");
                progresspercentage = ((totalcredit - totaldebit)/totalcredit)*100;
                String per = String.format(Double.toString(progresspercentage), "%.2f");

                //set alerts based on the 50/30/20 rule, where at least 20 % of income is saved.

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(layout_main_navigation.this, "Failed to load transactions.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        });
        myRef.addListenerForSingleValueEvent(postListener);


    }



    public void navigateGraph(View view){
        Intent intent = new Intent(layout_main_navigation.this, Barchart.class);
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
