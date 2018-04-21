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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jessicaannor on 17/03/2018.
 */
public class smsBroadcastReceiver extends BroadcastReceiver{
    /*private addTransaction addTransaction;
    public static final String SMS_BUNDLE = "pdus";
    public static final String AMT_REGEX = "GHS\\s?\\d+.[0-9]{2}";
    public String purpose, type, date;
    FirebaseDatabase transactionDatabase;
    String fullamt;
    addTransaction newTrans = new addTransaction();*/
   // newTrans.addTrans(String type, String purpose, String date, double amt);
//    boolean isCredit = smsBody.contains("credited");
//    boolean isDebit = smsBody.contains("debited");



    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"SMS Received",Toast.LENGTH_LONG).show();
        /*Bundle intentExtras = intent.getExtras();
        if(intentExtras != null) {
            Log.d("Status", "New SMS received");
        }*/
            //Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            //String smsMessageStr = "";
            /*for(int i = 0; i< sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[1]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS Form: " + address + "\n";
                smsMessageStr += smsBody + "\n";


                //——————————————
                 purpose = smsBody;
                String[] separated = smsBody.split(" ");
                fullamt  = separated[0]; // this will contain “GHS10.00"
                String[] ghssplit = fullamt.split("GHS");
                double amount = Double.parseDouble(ghssplit[1].toString());
                //double amount =0;
                String type = separated[1];//  this will contain “credited"
                String date = separated[13];//  this will contain “date"

                //then u can check if the separated[1] is either credited or debited 
                switch(separated[1]){
                    case "credited":
                         type = "credit";
                        break;
                    case "debited":
                         type = "debit";
                        break;
                }*/
                //addTrans(type, purpose, date, amount);





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

    /*public void addTrans(String type, String purpose, String date, double amt) {
        DatabaseReference myRef = transactionDatabase.getReference();
        String key = myRef.push().getKey();
        Cash transaction = new Cash(type, purpose, date, amt);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.i("Transaction:",transaction.getDate());
//      myRef.child("transaction").child(key).setValue(transaction);
        myRef.child("users").child(currentUser.getUid()).child("transactions").child(key).setValue(transaction);

    }*/



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



