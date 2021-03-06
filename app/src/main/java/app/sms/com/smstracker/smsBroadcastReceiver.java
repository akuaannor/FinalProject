package app.sms.com.smstracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jessicaannor on 17/03/2018.
 */
public class smsBroadcastReceiver extends BroadcastReceiver{

    private addTransaction addTransaction;
    public static final String SMS_BUNDLE = "pdus";
    public static final String AMT_REGEX = "GHS\\s?\\d+.[0-9]{2}";
    public String purpose, type, date;
    FirebaseDatabase transactionDatabase;
    addTransaction newTrans = new addTransaction();
    private DatabaseReference myRef;
    private double totaldebit;
    // newTrans.addTrans(String type, String purpose, String date, double amt);
//    boolean isCredit = smsBody.contains("credited");
//    boolean isDebit = smsBody.contains("debited");



    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"SMS Received",Toast.LENGTH_LONG).show();
        Bundle intentExtras = intent.getExtras();
        totaldebit = 0.0;
        myRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("transactions");
        if(intentExtras != null) {

            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            String address = "";
            for (int i = 0; i < sms.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);
//                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsMessage.getMessageBody().toString();
                address += smsMessage.getDisplayOriginatingAddress();
                Log.d("Sender", "Sender: " + address);

            }


//            //——————————————
//            switch (address.trim()) {
//                case  "0505848699":
//                purpose = smsMessageStr;
//                    Log.d("SMS-STAT", "Sender: " + address);
//                    String[] separated = smsMessageStr.split(" ");
//                String fullamt = separated[0]; // this will contain “GHS10.00"
//                String[] ghssplit = fullamt.split("GHS");
//                double amount = Double.parseDouble(ghssplit[1].toString());
//                String type = separated[1];//  this will contain “credited"
//                //String dateRegex = " on (\\d{2}-\\w-\\d{2})";
//                //Matcher matcher = Pattern.compile(dateRegex).matcher(smsMessageStr);
//                //String date = separated[20];//  this will contain “date"
//                //String date = matcher.group();
//
//
//                //then u can check if the separated[1] is either credited or debited 
//                switch (separated[1]) {
//                    case "credited":
//                        type = "Credit";
//                        break;
//                    case "debited":
//                        type = "Debit";
//                        break;
//                }
//
//               if (separated[1] == "credited" || separated[1] == "debited") {
//
//                Log.d("SMS-STAT", "Amount: " + fullamt + "; Type: " + type);
//
//                addTrans(type, purpose, "null", amount);
//            }
//            break;
//                case "0208133352":
//                    purpose = smsMessageStr;
//                    separated = smsMessageStr.split(" ");
//                     fullamt = separated[0]; // this will contain “GHS10.00"
//                     ghssplit = fullamt.split("GHS");
//                     amount = Double.parseDouble(ghssplit[1].toString());
//                     type = separated[1];//  this will contain “credited"
//                    //String dateRegex = " on (\\d{2}-\\w-\\d{2})";
//                    //Matcher matcher = Pattern.compile(dateRegex).matcher(smsMessageStr);
//                    //String date = separated[20];//  this will contain “date"
//                    //String date = matcher.group();
//
//
//                    //then u can check if the separated[1] is either credited or debited 
//                    switch (separated[1]) {
//                        case "credited":
//                            type = "Credit";
//                            break;
//                        case "debited":
//                            type = "Debit";
//                            break;
//                    }
//
//                    if (separated[1] == "credited" || separated[1] == "debited") {
//
//                        Log.d("SMS-STAT", "Amount: " + fullamt + "; Type: " + type);
//
//                        addTrans(type, purpose, "null", amount);
//                    }
//                    break;
//                case "VF-CASH":
//                    purpose = smsMessageStr;
//                 separated = smsMessageStr.split(" ");
//                 fullamt = separated[5]; // this will contain “GHS10.00"
//                 ghssplit = fullamt.split("GHS");
//                 amount = Double.parseDouble(ghssplit[1].toString());
//                 type = separated[4];//  this will contain “credited"
//                //String dateRegex = " on (\\d{2}-\\w-\\d{2})";
//                //Matcher matcher = Pattern.compile(dateRegex).matcher(smsMessageStr);
//                //String date = separated[20];//  this will contain “date"
//                //String date = matcher.group();
//
//
//                //then u can check if the separated[4] is either credited or debited 
//                switch (separated[4]) {
//                    case "received":
//                        type = "Credit";
//                        break;
//                    case "paid":
//                        type = "Debit";
//                        break;
//                }
//
//                Log.d("SMS-STAT", "Amount: " + fullamt + "; Type: " + type);
//                if (separated[4] =="received" ||separated[4] == "paid") {
//
//                    addTrans(type, purpose, "null", amount);
//                }
//                    break;
//
//        }
//_________________________________END SWITCH______________________________________________________

            //——————————————
            if (Integer.parseInt(address) == Integer.parseInt("0505848699")) {
                purpose = smsMessageStr;
                Log.d("SMS-STAT", "Sender: " + address);
                String[] separated = smsMessageStr.split(" ");
                String fullamt = separated[0]; // this will contain “GHS10.00"
                String[] ghssplit = fullamt.split("GHS");
                double amount = Double.parseDouble(ghssplit[1].toString());
                String type = separated[1];//  this will contain “credited"
                //String dateRegex = " on (\\d{2}-\\w-\\d{2})";
                //Matcher matcher = Pattern.compile(dateRegex).matcher(smsMessageStr);
                //String date = separated[20];//  this will contain “date"
                //String date = matcher.group();


                //then u can check if the separated[1] is either credited or debited 
                switch (separated[1]) {
                    case "credited":
                        type = "Credit";
                        break;
                    case "debited":
                        type = "Debit";
                        break;
                }

//                if (separated[1] == "credited" || separated[1] == "debited") {

                    Log.d("SMS-STAT", "Amount: " + fullamt + "; Type: " + type);

                    addTrans(type, purpose, "null", amount);

            }
                   else if (Integer.parseInt(address) == Integer.parseInt( "0208133352")) {
                purpose = smsMessageStr;
                String[] separated = smsMessageStr.split(" ");
                String fullamt = separated[0]; // this will contain “GHS10.00"
                String[] ghssplit = fullamt.split("GHS");
               double amount = Double.parseDouble(ghssplit[1].toString());
                type = separated[1];//  this will contain “credited"
                //String dateRegex = " on (\\d{2}-\\w-\\d{2})";
                //Matcher matcher = Pattern.compile(dateRegex).matcher(smsMessageStr);
                //String date = separated[20];//  this will contain “date"
                //String date = matcher.group();


                //then u can check if the separated[1] is either credited or debited 
                switch (separated[1]) {
                    case "credited":
                        type = "Credit";
                        break;
                    case "debited":
                        type = "Debit";
                        break;
                }

//                if (separated[1] == "credited" || separated[1] == "debited") {

                    Log.d("SMS-STAT", "Amount: " + fullamt + "; Type: " + type);

                    addTrans(type, purpose, "null", amount);

            }
            else if (address == "VF-CASH"){
                    purpose = smsMessageStr;
                String[] separated = smsMessageStr.split(" ");
                  String  fullamt = separated[5]; // this will contain “GHS10.00"
                String[]  ghssplit = fullamt.split("GHS");
                 double   amount = Double.parseDouble(ghssplit[1].toString());
                    type = separated[4];//  this will contain “credited"
                    //String dateRegex = " on (\\d{2}-\\w-\\d{2})";
                    //Matcher matcher = Pattern.compile(dateRegex).matcher(smsMessageStr);
                    //String date = separated[20];//  this will contain “date"
                    //String date = matcher.group();


                    //then u can check if the separated[4] is either credited or debited 
                    switch (separated[4]) {
                        case "received":
                            type = "Credit";
                            break;
                        case "paid":
                            type = "Debit";
                            break;
                    }

                    Log.d("SMS-STAT", "Amount: " + fullamt + "; Type: " + type);
                    if (separated[4] =="received" ||separated[4] == "paid") {

                        addTrans(type, purpose, "null", amount);
                    }
            }
            else if (address == "ECOBANK"){
                purpose = smsMessageStr;
                String[] separated = smsMessageStr.split(" ");
                String fullamt = separated[0]; // this will contain “GHS10.00"
                String[] ghssplit = fullamt.split("GHS");
                double amount = Double.parseDouble(ghssplit[1].toString());
                type = separated[1];//  this will contain “credited"
                //String dateRegex = " on (\\d{2}-\\w-\\d{2})";
                //Matcher matcher = Pattern.compile(dateRegex).matcher(smsMessageStr);
                //String date = separated[20];//  this will contain “date"
                //String date = matcher.group();


                //then u can check if the separated[1] is either credited or debited 
                switch (separated[1]) {
                    case "credited":
                        type = "Credit";
                        break;
                    case "debited":
                        type = "Debit";
                        break;
                }

//                if (separated[1] == "credited" || separated[1] == "debited") {

                Log.d("SMS-STAT", "Amount: " + fullamt + "; Type: " + type);

                addTrans(type, purpose, "null", amount);
            }


//            } else if (address == "VF-CASH") {
//                purpose = smsMessageStr;
//                String[] separated = smsMessageStr.split(" ");
//                String fullamt = separated[5]; // this will contain “GHS10.00"
//                String[] ghssplit = fullamt.split("GHS");
//                double amount = Double.parseDouble(ghssplit[1].toString());
//                String type = separated[4];//  this will contain “credited"
//                //String dateRegex = " on (\\d{2}-\\w-\\d{2})";
//                //Matcher matcher = Pattern.compile(dateRegex).matcher(smsMessageStr);
//                //String date = separated[20];//  this will contain “date"
//                //String date = matcher.group();
//
//
//                //then u can check if the separated[1] is either credited or debited 
//                switch (separated[4]) {
//                    case "received":
//                        type = "Credit";
//                        break;
//                    case "paid":
//                        type = "Debit";
//                        break;
//                }
//
//                Log.d("SMS-STAT", "Amount: " + fullamt + "; Type: " + type);
//                if (separated[4] =="received" ||separated[1] == "paid") {
//
//                    addTrans(type, purpose, "null", amount);
//                }
//            }

            //listener for a new transaction that's a debit
            com.google.firebase.database.Query txType = myRef.orderByChild("type").equalTo("Debit");
            txType.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> transactions = dataSnapshot.getChildren();
                    ArrayList<Cash> tx = new ArrayList<>();
                    for (DataSnapshot cash : transactions) {
                        Cash c = cash.getValue(Cash.class);
                        Log.d("Debits-Reader:: ", c.purpose + "" + c.amount + "" + c.date);
                        tx.add(c);
                        totaldebit += c.amount;
                    }
                    Log.d("Debits-TOTAL:: ", String.valueOf(totaldebit));


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w("DEBIT-READ ERROR", "loadPost:onCancelled", databaseError.toException());
                    // [START_EXCLUDE]
                    /*Toast.makeText(Savings.this, "Failed to load transactions.",
                            Toast.LENGTH_SHORT).show();*/
                    // [END_EXCLUDE]
                }
            });
        }       //addTrans(type, purpose, date, amount);





//                if(isCredit){
//
//                        String type = "credit";
//                        String purpose = smsBody;
////                         Pattern pattern = Pattern.compile(AMT_REGEX);
////                Matcher matcher = pattern.matcher(smsBody);
////                String amt;
////                while (matcher.find()) {
////                    amt = matcher.group();
////                }
//                    addTrans(String type, String purpose, String date, double amt);
//                        DatabaseReference myRef = transactionDatabase.getReference();
//                        String key = myRef.push().getKey();
//                        Cash transaction = new Cash(type, purpose, date, amt);
//                        myRef.child("transaction").child(key).setValue(transaction);
//
//                }
//                else if (isDebit){
//
//                        String type = "debit";
//                        String purpose = smsBody;
////                         Pattern pattern = Pattern.compile(AMT_REGEX);
////                Matcher matcher = pattern.matcher(smsBody);
////                String amt;
////                while (matcher.find()) {
////                    amt = matcher.group();
////                }
//
//                    addTrans(String type, String purpose, String datee, double amt);
//                        DatabaseReference myRef = transactionDatabase.getReference();
//                        String key = myRef.push().getKey();
//                        Cash transaction = new Cash(type, purpose2, datee, amt);
//                        myRef.child("transaction").child(key).setValue(transaction);
//
//                }

            //}
            //Toast.makeText(context, smsMessageStr, Toast.LENGTH_LONG).show();

            //MainActivity inst = MainActivity.instance().instance();
            //inst.updateList(smsMessageStr);
        //}
    }

    public void addTrans(String type, String purpose, String date, double amt) {
        transactionDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = transactionDatabase.getReference();
        String key = myRef.push().getKey();
        Cash transaction = new Cash(type, purpose, date, amt);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        myRef.child("users").child(currentUser.getUid()).child("transactions").child(key).setValue(transaction);

    }



//    if(isCredit){
//        public void addTrans(String type, String purpose, String datee, double amt) {
//            String type = "credit";
//            String purpose = smsBody;
//            amt =
//            DatabaseReference myRef = transactionDatabase.getReference();
//            String key = myRef.push().getKey();
//            Cash transaction = new Cash(type, purpose2, datee, amt);
//            myRef.child("transaction").child(key).setValue(transaction);
//        }
//    }
//     else if (isDebit){
//        public void addTrans(String type, String purpose, String datee, double amt) {
//            String type = "debit";
//            String purpose = smsBody;
//            amt =
//                    DatabaseReference myRef = transactionDatabase.getReference();
//            String key = myRef.push().getKey();
//            Cash transaction = new Cash(type, purpose2, datee, amt);
//            myRef.child("transaction").child(key).setValue(transaction);
//        }
//    }
}



