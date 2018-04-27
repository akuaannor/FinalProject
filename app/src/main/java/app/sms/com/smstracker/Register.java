package app.sms.com.smstracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by jessicaannor on 18/04/2018.
 */

public class Register extends BaseActivity{
    private FirebaseAuth mAuth;
    private EditText emailText;
    private EditText passwordText;
    SharedPreferences.Editor editor;
    private String email, password;
    UserSession session;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        sharedpreferences = getApplicationContext().getSharedPreferences("Register", 0);
        emailText = (EditText) findViewById(R.id.emaileditText);
        passwordText = (EditText) findViewById(R.id.passwordeditText);
    }

    //method to create a new user
    public void createNewUser(View view){

        showProgressDialog();

         email = emailText.getText().toString();
         password  = passwordText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(Register.this,"User has been created",Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("Email ", email);
                            editor.putString("Password ",password);
                            editor.commit();   // commit the values
                            Intent intent = new Intent(Register.this, CreateProfile.class);
                            startActivity(intent);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                            //updateUI(null);
                        }

                        // ...
                        hideProgressDialog();
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void signin(View view){
        //Intent intent = new Intent(Register.this, Login.class);
        //startActivity(intent);
        showProgressDialog();
        session = new UserSession(getApplicationContext());
        final String email = emailText.getText().toString();
        final String password  = passwordText.getText().toString();

       sharedpreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(Register.this, "Welcome",
                                    Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            //check for and/or craete session
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("Email ", email);
                            editor.putString("Password ",password);
                            editor.commit();   // commit the values

                            Intent intent = new Intent(Register.this, layout_main_navigation.class);
                            startActivity(intent);
                            updateUI(user);

                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                            //updateUI(null);
                        }

                        // ...
                        hideProgressDialog();
                    }
                });
    }

    public void updateUI(FirebaseUser user){

    }

    public  void logout(View view){
        SharedPreferences sharedpreferences = getSharedPreferences(Register.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void close(View view){
        finish();
    }

    private static final String TAG  = "Register";

}
