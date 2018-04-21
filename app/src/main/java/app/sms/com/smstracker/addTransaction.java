package app.sms.com.smstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.FirebaseDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
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
    public Double amt;
    public String type;
    public String purpose2;
    public String datee;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_addcash);

        transactionDatabase = FirebaseDatabase.getInstance();
        Spinner transtype = (Spinner) findViewById(R.id.typeSpinner);
        type = (String) transtype.getSelectedItem();
        EditText amount = findViewById(R.id.amountEditText);
        EditText purpose = findViewById(R.id.purposeEditText2);
        EditText date = findViewById(R.id.date);
        Button add = findViewById(R.id.addButton);


//These are the variables that would be stored into the database
        purpose2 = purpose.getText().toString();
        datee = date.getText().toString();
        try {
             amt = Double.parseDouble(amount.getText().toString());
        }catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        //final String date2 = new SimpleDateFormat("dd/MM/yyyy").format(datee);

//         type = transtype.getSelectedItem().toString();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTrans(type, purpose2, datee, amt);
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
        myRef.child("transaction").child(key).setValue(transaction);
    }



}





