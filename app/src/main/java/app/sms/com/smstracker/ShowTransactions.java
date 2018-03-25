package app.sms.com.smstracker;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by jessicaannor on 25/03/2018.
 */

public class ShowTransactions extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_overview);
    }



    //READ
    public ArrayList<String> retrieve()
    {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return spacecrafts;
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        spacecrafts.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            String name=ds.getValue(Spacecraft.class).getName();
            spacecrafts.add(name);
        }
    }
}
