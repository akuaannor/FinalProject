package app.sms.com.smstracker;

/**
 * Created by jessicaannor on 26/04/2018.
 */

import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

    public class UserSession {
        // Shared Preferences reference
        SharedPreferences pref;

        // Editor reference for Shared preferences
        Editor editor;

        // Context
        Context _context;

        // Shared preferences mode
        int PRIVATE_MODE = 0;

        // Shared preferences file name
        public static final String PREFER_NAME = "Reg";

        // All Shared Preferences Keys
        public static final String IS_USER_LOGIN = "IsUserLoggedIn";

        // User name (make variable public to access from outside)
        public static final String KEY_NAME = "Name";

        // Email address (make variable public to access from outside)
        public static final String KEY_EMAIL = "Email";

        // password
        public static final String KEY_PASSWORD = "txtPassword";

        public UserSession(Context context){
            this._context = context;
        }

    }
