package app.sms.com.smstracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.support.v4.app.FragmentActivity;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jessicaannor on 25/03/2018.
 */

public class ShowTransactions extends AppCompatActivity {
    //private FragmentTabHost tabHost;
    private DatabaseReference myRef;
//    private ListView listview;
    private TabHost tabHost;
    private TabHost.TabSpec tabIndicator;
//    ArrayAdapter adapter;
//    ArrayList<String> tx =  new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tx);
//        listview = findViewById(R.id.txListView);
//        listview.setAdapter(adapter);

        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();

        //Credit Tab
        tabIndicator = tabHost.newTabSpec("Credit");
        tabIndicator.setContent(R.id.credit);
        tabIndicator.setIndicator("Credit");
        tabHost.addTab(tabIndicator);

        //Debit Tab
        tabIndicator = tabHost.newTabSpec("Debit");
        tabIndicator.setContent(R.id.debit);
        tabIndicator.setIndicator("Debit");
        tabHost.addTab(tabIndicator);

        //Initializing DB
        myRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("transactions");

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("APPPPPPP","Started");
        // Add value event listener to the post
        // [START post_value_event_listener]
        //final ArrayList<Cash> transactions = new ArrayList<>();
//        listview.setAdapter(adapter);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                //Cash tx = dataSnapshot.getValue(Cash.class);
                //transactions.add(tx);
                //Log.d("Transaction-P",tx.purpose);
                //Toast.makeText(ShowTransactions.this, tx.purpose,Toast.LENGTH_SHORT).show();

                //Log.e("Transaction", "onDataChange: Transaction data is updated: " + tx.purpose );

                //https://www.quora.com/How-do-I-read-all-child-key-values-of-a-child-from-Firebase-database-in-Android
                Iterable<DataSnapshot> transactions = dataSnapshot.getChildren();
                ArrayList<Cash>tx = new ArrayList<>();
                for (DataSnapshot contact : transactions) {
                    Cash c = contact.getValue(Cash.class);
                    Log.d("Purpose:: ", c.purpose);
                    tx.add(c);
                }
//                adapter = new ArrayAdapter<Cash>();
//                listview.setAdapter(adapter);

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(ShowTransactions.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        myRef.addListenerForSingleValueEvent(postListener);
        // [END post_value_event_listener]



    }

    //    DatabaseReference myRef = transactionDatabase.getReference();
//
//
//
//
//    //READ
//    public ArrayList<String> retrieve()
//    {
//        db.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                fetchData(dataSnapshot);
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        return cash;
//    }
//
//    private void fetchData(DataSnapshot dataSnapshot)
//    {
//        cash.clear();
//
//        for (DataSnapshot ds : dataSnapshot.getChildren())
//        {
//            String name=ds.getValue(addTransaction.class).getName();
//            cash.add(name);
//        }
//    }
}
