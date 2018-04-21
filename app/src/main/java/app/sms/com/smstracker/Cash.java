package app.sms.com.smstracker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Date;

/**
 * Created by jessicaannor on 23/03/2018.
 */
@IgnoreExtraProperties
public class Cash {

    public double amount;
    public String type, purpose;
    public String date;

    public Cash() {
        // Default constructor
    }

    public Cash(String type, String purpose,String date, double amount) {
        this.type = type;
        this.purpose = purpose;
        this.date = date;
        this.amount = amount;
    }

    public Cash(String type, String purpose, double amount) {
        this.type = type;
        this.purpose = purpose;
        this.amount = amount;
    }

//    public void addTransaction(String type, String purpose, Date date, double amount) {
//        DatabaseReference myRef = database.getReference();
//        String key = myRef.push().getKey();
//        Cash transaction = new Cash( type,  purpose,  date, amount);
//        myRef.child("transaction").child(key).setValue(transaction);
//    }





    public double getAmount() {

        return amount;
    }

    public String getPurpose() {

        return purpose;
    }

    public String getType() {

        return type;
    }

    public String getDate() {

        return date;
    }
}


