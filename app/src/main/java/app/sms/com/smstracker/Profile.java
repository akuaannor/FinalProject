package app.sms.com.smstracker;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jessicaannor on 26/04/2018.
 */

public class Profile extends AppCompatActivity{
    FirebaseDatabase transactionDatabase;
   private EditText target;
   private double targetvalue;
   private ImageView profilepic;
    private StorageReference mStorageRef;
    private FirebaseAuth auth;
    private ArrayList<String> pathArray;
    private int array_position;
    private static final String TAG  = "Profile";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile);
        target = findViewById(R.id.targetEditText);
        profilepic = findViewById(R.id.imageView5);
        Button update = findViewById(R.id.updatebutton);
        pathArray = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    targetvalue = Double.parseDouble(target.getText().toString());
                }catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }


                updateInfo(targetvalue);
                Toast.makeText(Profile.this,"Profile Updated",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void loadImageFromStorage()
    {
        try{
            String path = pathArray.get(array_position);
            File f=new File(path, "");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            profilepic.setImageBitmap(b);
        }catch (FileNotFoundException e){
            Log.e(TAG, "loadImageFromStorage: FileNotFoundException: " + e.getMessage() );
        }

    }

    public void updateInfo(double target) {
        DatabaseReference myRef = transactionDatabase.getReference();
        String key = myRef.push().getKey();
        User profile = new User(target);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        Log.i("Profile:",profile.getDate());
//      myRef.child("transaction").child(key).setValue(transaction);
//        myRef.child("users" +newPostKey).child(currentUser.getUid()).child("profile").child(key).setValue(profile);
        try {
            myRef.child("users").child(currentUser.getUid()).child("profile").child("key").setValue("profile");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
