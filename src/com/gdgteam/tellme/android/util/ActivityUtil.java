package com.gdgteam.tellme.android.util;

import java.util.Enumeration;
import java.util.Properties;

import com.gdgteam.tellme.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;

/**
 * 
 * @author Andrey Pereverzin
 *
 */
public class ActivityUtil {
    public static void showException(Activity parent, Throwable ex) {
        if(ex != null) {
            Log.e(parent.getClass().getSimpleName(), ex.getLocalizedMessage(), ex);
            new AlertDialog.Builder(parent).setTitle("Error").setMessage(ex.getLocalizedMessage()).setNeutralButton("Close", null).show();
        }
    }
    
    public static void showAlert(Activity parent, String alert) {
        new AlertDialog.Builder(parent).setTitle("Error").setMessage(alert).setNeutralButton("Close", null).show();
    }
    
//    public static void processCheckConnectionFailure(Activity parent, Throwable ex) {
//        if(ex.getCause() != null && ex.getCause() instanceof ConnectorException) {
//            Throwable cause = ex.getCause();
//            if(cause.getCause() != null && cause.getCause() instanceof AuthenticationFailedException) {
//                showAlert(parent, parent.getString(R.string.error_message_authentication_failed));
//            } else if(cause.getCause() != null && cause.getCause() instanceof MessagingException) {
//                ActivityUtil.showAlert(parent, parent.getString(R.string.error_message_connection_failed));
//            } else {
//                showException(parent, ex);
//            }
//        } else {
//            ActivityUtil.showException(parent, ex);
//        }
//    }
//    
    public static void logException(String tag, Throwable ex) {
        Log.e(tag, ex.getLocalizedMessage(), ex);
    }
    
    public static StringBuilder buildActivityTitle(Activity activity, int... stringIds) {
        StringBuilder title = new StringBuilder(activity.getString(R.string.app_name));
        for(int stringId: stringIds) {
            title.append(": ").append(activity.getString(stringId));
        }
        return title;
    }
    
    public static void printStackTrace(String TAG, Throwable ex) {
        StackTraceElement[] sta = ex.getStackTrace();
        Log.e(TAG, ex.getMessage());
        Log.e(TAG, ex.getClass().getName());
        for(StackTraceElement st: sta) {
            Log.e(TAG, "   " + st.getFileName() + "." + st.getMethodName() + ": " + st.getLineNumber());
        }
    }
    
    public static void printProperties(String TAG, Properties props) {
        @SuppressWarnings("unchecked")
        Enumeration<String> propNames = (Enumeration<String>)props.propertyNames();
        while(propNames.hasMoreElements()) {
            String propName = propNames.nextElement();
            Log.d(TAG, propName + "=" + props.getProperty(propName));
        }
    }
}
