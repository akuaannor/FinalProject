package app.sms.com.smstracker;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by jessicaannor on 18/04/2018.
 */

@IgnoreExtraProperties
public class User{
    public String name, email, tel, org, uid;
    public User(){
        // Default constructor
    }

    public User(String uid, String name, String email, String tel, String org){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.org = org;
    }
    public User(String name, String email, String tel, String org){
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.org = org;
    }




}
