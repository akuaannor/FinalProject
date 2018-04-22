package app.sms.com.smstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.FirebaseDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by jessicaannor on 23/03/2018.
 */

public class addTransaction extends AppCompatActivity {

    FirebaseDatabase transactionDatabase;
    public double amt = 0;
    public String type;
    public String purpose2;
    public String datee;
    private EditText amount;
    private  Spinner transtype;
    private EditText purpose;
    private EditText date;
    private FirebaseAuth mAuth;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addcash);

        transactionDatabase = FirebaseDatabase.getInstance();
        transtype = (Spinner) findViewById(R.id.typeSpinner);
//        type = (String) transtype.getSelectedItem();
        amount = (EditText) findViewById(R.id.amountEditText);
        purpose = findViewById(R.id.purposeEditText2);
        date = findViewById(R.id.date);
        Button add = findViewById(R.id.addButton);


//These are the variables that would be stored into the database


        //final String date2 = new SimpleDateFormat("dd/MM/yyyy").format(datee);

//         type = transtype.getSelectedItem().toString();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = (String) transtype.getSelectedItem();
                purpose2 = purpose.getText().toString();
                datee = date.getText().toString();
                try {
                    amt = Double.parseDouble(amount.getText().toString());
                }catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                addTrans(type, purpose2, datee, amt);
                reset();
                Toast.makeText(addTransaction.this,"Transaction Added",Toast.LENGTH_SHORT).show();

            }


        });

//Values from the spinner
        List <String> spinner =  new ArrayList<String>();
        spinner.add("Credit");
        spinner.add("Debit");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transtype.setAdapter(adapter);

    }





    public void addTrans(String type, String purpose2, String datee, double amt) {
        DatabaseReference myRef = transactionDatabase.getReference();
        String key = myRef.push().getKey();
        Cash transaction = new Cash(type, purpose2, datee, amt);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.i("Transaction:",transaction.getDate());
//      myRef.child("transaction").child(key).setValue(transaction);
        myRef.child("users").child(currentUser.getUid()).child("transactions").child(key).setValue(transaction);

    }

    public void reset(){
        this.amount.setText("");
        this.purpose.setText("");
        this.date.setText("");
    }



}





