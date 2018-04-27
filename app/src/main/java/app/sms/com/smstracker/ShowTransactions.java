package app.sms.com.smstracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by jessicaannor on 25/03/2018.
 */

public class ShowTransactions extends AppCompatActivity {
private DatabaseReference myRef;
private ListView listViewC, listViewD;
private TabHost tabHost;
private TabHost.TabSpec tabIndicator;
ArrayAdapter adapterDebit, adapterCredit;
ArrayList<String> debit, credit;

@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.layout_overview);


tabHost = findViewById(R.id.tabHost);
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

listViewC = findViewById(R.id.listViewC);
listViewD = findViewById(R.id.listViewD);

debit = new ArrayList<>();
credit = new ArrayList<>();
adapterDebit = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, debit);
adapterCredit = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, credit);
listViewC.setAdapter(adapterCredit);
listViewD.setAdapter(adapterDebit);

//Initializing DB
myRef = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("transactions");

Query debit = myRef.orderByChild("type").equalTo("Debit");
debit.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
Iterable<DataSnapshot> transactions = dataSnapshot.getChildren();
for (DataSnapshot cash : transactions) {
Cash c = cash.getValue(Cash.class);
Log.d("Debits-Reader:: ", c.purpose + "" + c.amount + "" + c.date);
adapterDebit.add(c.purpose);
}


}

@Override
public void onCancelled(DatabaseError databaseError) {
// Getting Post failed, log a message
Log.w("DEBIT-READ ERROR", "loadPost:onCancelled", databaseError.toException());
// [START_EXCLUDE]
/*Toast.makeText(Savings.this, "Failed to load transactions.",
                            Toast.LENGTH_SHORT).show();*/// [END_EXCLUDE]
}
});

}

@Override
protected void onStart() {
super.onStart();

Log.d("APPPPPPP", "Started");
Query credit = myRef.orderByChild("type").equalTo("Credit");
credit.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
Iterable<DataSnapshot> transactions = dataSnapshot.getChildren();

for (DataSnapshot cash : transactions) {
Cash c = cash.getValue(Cash.class);
Log.d("Credits-Reader:: ", c.purpose + "" + c.amount + "" + c.date);
adapterCredit.add(c.purpose);
}


}

@Override
public void onCancelled(DatabaseError databaseError) {
// Getting Post failed, log a message
Log.w("CREDIT-READ ERROR", "loadPost:onCancelled", databaseError.toException());
// [START_EXCLUDE]
/*Toast.makeText(Savings.this, "Failed to load transactions.",
                            Toast.LENGTH_SHORT).show();*/
// [END_EXCLUDE]
}
});

}


}