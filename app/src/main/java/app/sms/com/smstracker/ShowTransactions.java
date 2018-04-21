package app.sms.com.smstracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v4.app.FragmentActivity;
import android.widget.TabHost;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


/**
 * Created by jessicaannor on 25/03/2018.
 */

public class ShowTransactions extends AppCompatActivity {
    private FragmentTabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_overview);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
//        tabHost.setup(this, getSupportFragmentManager(), R.id.tabHost);

//        host.setup();
        //Credit Tab
        TabHost.TabSpec spec = host.newTabSpec("Credit");
        spec.setContent(R.id.credit);
        spec.setIndicator("Credit");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Debit");
        spec.setContent(R.id.debit);
        spec.setIndicator("Debit");
        host.addTab(spec);

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
