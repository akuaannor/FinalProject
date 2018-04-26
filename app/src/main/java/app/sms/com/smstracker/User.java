package app.sms.com.smstracker;

import android.widget.ImageView;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by jessicaannor on 18/04/2018.
 */

@IgnoreExtraProperties
public class User{
    public String uid, name, email, targetstr;
    public double target;
    public int tel;
    public ImageView image;
    public String DOB;
    public User(){
        // Default constructor
    }

    public User(String uid, String name, String email, int tel, double target, ImageView image, String DOB){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.target = target;
        this.image = image;
        this.DOB = DOB;
    }
    public User( String name, String email, int tel, double target, ImageView image, String DOB){
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.target = target;
        this.image = image;
        this.DOB = DOB;
    }

    public User(double target, ImageView image){

        this.target = target;
        this.image = image;
    }
    public User(String targetstr){

        this.targetstr = targetstr;
    }

    public User(double target){

        this.target = target;
    }

    public User( String name, String email, int tel, double target, String DOB){
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.target = target;
        this.DOB = DOB;
    }

    public User( String name, int tel, double target, String DOB){
        this.name = name;
        this.tel = tel;
        this.target = target;
        this.DOB = DOB;
    }


}
