//package id.secondgroup.laboratorydigitalsignagesystem;
//
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.provider.Settings;
//import android.view.Window;
//import android.view.WindowManager;
//
//import java.util.Calendar;
//
//public class BrightnessBroadcastReceiver extends BroadcastReceiver {
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        // The BroadcastReceiver receives the alarm trigger here.
//        // Adjust the brightness based on the time
//        Calendar calendar = Calendar.getInstance();
//        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
//
//        if (hourOfDay >= 19 || hourOfDay < 7) {
//            // It's between 7 pm and 7 am, so set the brightness to 100%
//            setScreenBrightness(context, 1.0f);
//        } else {
//            // It's between 7 am and 7 pm, so set the brightness to 1%
//            setScreenBrightness(context, 0.01f);
//        }
//    }
//
//    private void setScreenBrightness(Context context, float brightness) {
//        Window window = ((Activity) context).getWindow();
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        layoutParams.screenBrightness = brightness;
//        window.setAttributes(layoutParams);
//
//        // Save the brightness level in the system settings
//        ContentResolver contentResolver = context.getContentResolver();
//        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, (int) (brightness * 255));
//    }
//}
