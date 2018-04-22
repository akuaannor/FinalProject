package app.sms.com.smstracker;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import java.util.ArrayList;
import java.util.EventListener;


/**
 * Created by jessicaannor on 08/04/2018.
 */

public class Savings extends AppCompatActivity {
    private DatabaseReference myRef;
    private double totalcredit = 0;
    private double totaldebit = 0;
    private TextView debcreddiff;

//    private
//    String progressMess2 = progressMess.getText().toString();
//    public String statusmessage;
////    String progressMess = ((TextView)findViewById(R.id.progressMessage)).getText().toString();
//TextView message = findViewById(R.id.progressMessage);


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_balanceupdate);
        debcreddiff = findViewById(R.id.progressValue);
        //Initializing DB
        myRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("transactions");
    }


    // Get a reference to our posts
//    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("transactions");

    @Override
    protected void onStart() {
        super.onStart();
myRef.child("users").child("transactions").orderByChild("type").equalTo("debit").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterable<DataSnapshot> transactions = dataSnapshot.getChildren();
                ArrayList<Cash> tx = new ArrayList<>();
                for (DataSnapshot cash : transactions) {
                    Cash c = cash.getValue(Cash.class);
                    totaldebit += c.amount;
                    System.out.println(totaldebit);
                    Log.d("cash:: ", c.purpose + "" + c.amount + "" + c.date);
                    tx.add(c);
                }
            debcreddiff.setText(Double.toString(totaldebit));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Iterable<DataSnapshot> transactions = dataSnapshot.getChildren();
                ArrayList<Cash> tx = new ArrayList<>();
                for (DataSnapshot cash : transactions) {
                    Cash c = cash.getValue(Cash.class);
                    totaldebit += c.amount;
                    System.out.println(totaldebit);
                    Log.d("cash:: ", c.purpose + "" + c.amount + "" + c.date);
                    tx.add(c);
                }
                debcreddiff.setText(Double.toString(totaldebit));
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
        com.google.firebase.database.ValueEventListener postListener = new com.google.firebase.database.ValueEventListener() {
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
                Toast.makeText(Savings.this, "Failed to load transactions.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        myRef.addListenerForSingleValueEvent(postListener);
        // [END post_value_event_listener]
    }
}

//    reference.addChildEventListener(new ValueEventListener){
//        @Override
//        public void onDataChange(DataSnapshot snapshot) {
//for (DataSnapshot ds:snapshot.getChildren()){
//    totalcredit = totalcredit + amount;
//
//}

//    Firebase.setAndroidContext(this);
//    Firebase ref = new Firebase("https://sikasemx.firebaseio.com/transactions");
//    Query queryref = ref.orderByChild("amount");

    // Attach an listener to read the datqueryref.addChildEventListener

//    ValueEventListener transactionListener =  new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot snapshot) {
//            System.out.println(snapshot.getValue());
//        }
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//            //System.out.println("The read failed: " + firebaseError.getMessage());
//        }
//    };

//    Resources res = getResources();
//    if (totalcredit > totaldebit)
//
//    {
//         statusmessage = res.getString(R.string.congratulations);
//    }
//    else{
//         statusmessage = res.getString(R.string.warning);
//
//    }
//
//    message.setText(statusmessage);























//    var ref = new Firebase("https://sikasemx.firebaseio.com/transactions");
//
//    var ref = firebase.database().ref("users/ada");
//ref.once("value")
//        .
//
//    then(function(snapshot) {
//        var key = snapshot.key; // "ada"
//        var childKey = snapshot.child("name/last").key; // "last"
//    });
//
//    var query = firebase.database().ref("users").orderByKey();
//query.once("value")
//        .
//
//    then(function(snapshot) {
//        snapshot.forEach(function(childSnapshot) {
//            // key will be "ada" the first time and "alan" the second time
//            var key = childSnapshot.key;
//            // childData will be the actual contents of the child
//            var childData = childSnapshot.val();
//        });
//    });

//
//List<Cash> commentList = new ArrayList<>();
////    List<Cash> commentList = new ArrayList<>();
//myRef.addValueEventListener(new ValueEventListener() {
//        public void onDataChange(DataSnapshot snapshot) {
//            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                Cash comment = postSnapshot.getValue(Cash.class);
//                commentList.add(comment);
//            }
//            // here you can print the size of your list
//            Log.d(TAG,list.size())
//        }
//
//    public void onCancelled(FirebaseError firebaseError) {
//        System.out.println("The read failed: " + firebaseError.getMessage());
//    }
//});
//}
//private void calculateCredits() {
//
//
//    var ref = new Firebase("https://sikasemx.firebaseio.com/transactions");
//    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//    DatabaseReference usersRef = rootRef.child("Users");
//    ValueEventListener eventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            int totalCreditAmount = 0;
//            ref.orderBy('type').equalTo('credit').on('child_added', function(snapshot){
//            for(DataSnapshot dSnapshot : dataSnapshot.getChildren()) {
//                for(DataSnapshot ds: dSnapshot.child("transaction").getChildren()) {
//                    int credit = ds.child("amount").getValue(Double.class);
//                    totalCreditAmount = totalCreditAmount + credit;
//                }
//            }
//            Log.d("TAG", totalCreditAmount);
//        }
//        });
//    }
//
//        public void onCancelled(DatabaseError databaseError) {}
//    };
//    usersRef.addListenerForSingleValueEvent(eventListener);
//
//}



//    ref.orderBy('type').equalTo('credit').on('child_added', function(snapshot) {
//        var transactiontype = snapshot.val();
//        var totalCreditAmount = 0;
//query.on("child_added", function(snapshot) {
//            totalCreditAmount += snapshot.val().amount;
//        }
//            console.log("Total credits: "+totalCreditAmount);
//        });
//        }










//    ref.child("stegosaurus").child("height").on("value", function(stegosaurusHeightSnapshot) {
//        var favoriteDinoHeight = stegosaurusHeightSnapshot.val();




//
//    let parentRef = self.dbRef.child("transactions")
//
//    let ratingRef = parentRef.child("rating") // rating node
//    ratingRef.observe(.value, with:{
//        snapshot in
//        let count = snapshot.childrenCount
//        var total:Double = 0.0
//        for child in snapshot.children {
//            let snap = child as !FIRDataSnapshot
//            let val = snap.value as !Double
//            total += val
//        }
//        let average = total / Double(count)
//        print(average)
//    })


