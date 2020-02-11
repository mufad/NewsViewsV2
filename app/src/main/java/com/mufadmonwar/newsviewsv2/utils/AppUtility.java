package com.mufadmonwar.newsviewsv2.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.File;


public class AppUtility {

    private static AppUtility mAppUtility = null;

    // create single instance
    public static AppUtility getInstance() {
        if (mAppUtility == null) {
            mAppUtility = new AppUtility();
        }
        return mAppUtility;
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static String generateSdCardUrl(String directory, String fileName) {
        String sdCardUrl = Environment.getExternalStorageDirectory() + File.separator + directory;
        File file = new File(sdCardUrl);
        if(!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + File.separator + fileName;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static String getPhone11Digite(String fullPhoneNo){
//        if (fullPhoneNo == null) {
//            return null;
//        }

        if (fullPhoneNo == null || fullPhoneNo.isEmpty()) {
            fullPhoneNo = "01";
        }

        char[] pHone = fullPhoneNo.toCharArray();
        fullPhoneNo = "";
        for (int i = 0; i< pHone.length && i < 11; i++) {
            fullPhoneNo = fullPhoneNo + (pHone[pHone.length - (1 + i)]);
        }
        fullPhoneNo = new StringBuilder(fullPhoneNo).reverse().toString();
        return fullPhoneNo;
    }

    private static long backPressed = 0;

    public static void tapToExit(Activity activity) {
        if (backPressed + 2500 > System.currentTimeMillis()) {
            activity.finish();
        } else {
           // showToast(activity.getApplicationContext(), activity.getResources().getString(R.string.tapAgain));
        }
        backPressed = System.currentTimeMillis();
    }


    public static void makePhoneCall(Activity activity, String phoneNumber) {
        if (phoneNumber != null) {
            /*Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            if (PermissionUtils.isPermissionGranted(activity, PermissionUtils.CALL_PERMISSIONS, PermissionUtils.REQUEST_CALL)) {
                activity.startActivity(callIntent);
            }*/

            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            activity.startActivity(intent);
        }
    }
}
