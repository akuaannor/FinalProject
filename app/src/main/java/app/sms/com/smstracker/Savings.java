package app.sms.com.smstracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by jessicaannor on 08/04/2018.
 */

public class Savings implements AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l);
    }

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
}


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


