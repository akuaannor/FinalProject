package app.sms.com.smstracker;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jessicaannor on 26/04/2018.
 */


public class CreateProfile extends BaseActivity {
        FirebaseDatabase profileDatabase;
        private EditText target, DOB, phone, name;
        private TextView email;
        private double targetvalue= 0;
        private ImageView profilepic;
        private String setname,  setemail, date2;
        private int tel;
        private ImageView image;
    private final static int mWidth = 512;
    private final static int mLength = 512;
    private StorageReference mStorageRef;
    private FirebaseAuth auth;
    private ArrayList<String> pathArray;
        private int array_position;
    Calendar myCalendar = Calendar.getInstance();
    @Override
        public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_createprofile);
            target = findViewById(R.id.targetEditView);
            profilepic = findViewById(R.id.imageView5);
            Button update = findViewById(R.id.updatebutton);
            DOB = findViewById(R.id.DOBEditView);
            email =findViewById(R.id.emailTextView);
            phone = findViewById(R.id.phoneedittext);
            name = findViewById(R.id.nameedittext);
        profileDatabase = FirebaseDatabase.getInstance();
        pathArray = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }

            };

            DOB.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    new DatePickerDialog(CreateProfile.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showProgressDialog();

                    try {
                        targetvalue = Double.parseDouble(target.getText().toString());
                    }catch(NumberFormatException nfe) {
                        System.out.println("Could not parse " + nfe);
                    }
                    setname = name.getText().toString();
                    email.setText(auth.getCurrentUser().getEmail().toString());
                    Log.d("e-mai", auth.getCurrentUser().getEmail().toString());
                    tel = Integer.parseInt( phone.getText().toString());
                    date2 = DOB.getText().toString();
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = currentUser.getUid();
//                    StorageReference storageReference = mStorageRef.child("profileimg/users/" + uid + "/" + setname + ".jpg";
                    createProfile(setname, tel, targetvalue, date2 );
                    Toast.makeText(CreateProfile.this,"Profile Created",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateProfile.this, layout_main_navigation.class);
                    startActivity(intent);
                    hideProgressDialog();


                }

            });

        }

//        public void nav(View view) {
//
//            Intent intent = new Intent(CreateProfile.this, layout_main_navigation.class);
//            startActivity(intent);
//        }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        DOB.setText(sdf.format(myCalendar.getTime()));
    }
        public void createProfile(String setname, int tel, double targetvalue, String date2) {
//            FirebaseUser user = auth.getCurrentUser();
//            String userID = user.getUid();
            DatabaseReference myRef = profileDatabase.getReference();
            String key = myRef.push().getKey();
            User profile = new User(setname, setemail, tel, targetvalue, image, date2);
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            String uid = currentUser.getUid();
//            Uri uri = Uri.fromFile(new File(pathArray.get(array_position)));
//            StorageReference storageReference = mStorageRef.child("profileimg/users/" + uid + "/" + setname + ".jpg");
//            storageReference.putFile(uri);
//        Log.i("Profile:",profile.getDate());
//      myRef.child("transaction").child(key).setValue(transaction);
            myRef.child("users").child(currentUser.getUid()).child("profile").child(key).setValue(profile);

        }
    }


