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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Random;


/**
 * Created by jessicaannor on 08/04/2018.
 */

public class Savings extends AppCompatActivity {
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



//    private
//    String progressMess2 = progressMess.getText().toString();
//    public String statusmessage;
////    String progressMess = ((TextView)findViewById(R.id.progressMessage)).getText().toString();
//TextView message = findViewById(R.id.progressMessage);


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_balanceupdate);
        debcreddiff = findViewById(R.id.progressValue);
        percent = findViewById(R.id.percentLabel);
        progressmessage = findViewById(R.id.progressMessage);
       final TextView savingtips = findViewById(R.id.savingTips);
        final TextView target = findViewById(R.id.targetEditText);
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
                Toast.makeText(Savings.this, "Failed to load transactions.",
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
                debcreddiff.setText("GHS " + progressvaluestr);
                progresspercentage = ((totalcredit - totaldebit) / totalcredit) * 100;
//                    progresspercentagerd = DF.format(progresspercentage);
                String per = String.format(Double.toString(progresspercentage), "%.2f");
                percent.setText(per + "% saved");
                //set alerts based on the 50/30/20 rule, where at least 20 % of income is saved.

                if (progressvalue < targetvalue){
                    savingtips.setText(randomStr);
                    progressmessage.setText(R.string.warning);
                }
                else{
                    savingtips.setText(randomStr);
                    progressmessage.setText(R.string.congratulations);
                }
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
                    target.setText("GHS " + targetvalue);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Savings.this, "Failed to load target value.",
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
                debcreddiff.setText("GHS " + progressvaluestr);
                progresspercentage = ((totalcredit - totaldebit)/totalcredit)*100;
                String per = String.format(Double.toString(progresspercentage), "%.2f");
                percent.setText(per + "% saved");

                //set alerts based on the 50/30/20 rule, where at least 20 % of income is saved.

                if (progressvalue < targetvalue){
                    savingtips.setText(randomStr);
                    progressmessage.setText(R.string.warning);
                }
                else{
                    savingtips.setText(randomStr);
                    progressmessage.setText(R.string.congratulations);
                }
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
        });
        myRef.addListenerForSingleValueEvent(postListener);

        //method to listen for a change in target value and update accordingly

//        myRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> profile = dataSnapshot.getChildren();
//                ArrayList<User> pr = new ArrayList<>();
//                for (DataSnapshot user : profile) {
//
//                    User u = user.getValue(User.class);
//                    Log.d("User:: ", "target is" + u.target);
//                    pr.add(u);
//                    targetvalue = u.target;
//                    target.setText("GHS " + targetvalue);
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(Savings.this, "Failed to load target value.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//        // [END post_value_event_listener]
//        myRef2.addListenerForSingleValueEvent(postListener);




    }


    // Get a reference to our posts
//    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("transactions");

    @Override
    protected void onStart() {
        super.onStart();

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


