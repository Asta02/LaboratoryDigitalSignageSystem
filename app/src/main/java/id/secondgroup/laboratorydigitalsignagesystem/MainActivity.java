package id.secondgroup.laboratorydigitalsignagesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import java.util.Calendar;
import java.util.UUID;

import id.secondgroup.laboratorydigitalsignagesystem.repository.RuanganRepository;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_WRITE_SETTINGS = 100;
    private static final int BRIGHTNESS_REQUEST_CODE = 101;
    /*private BrightnessBroadcastReceiver brightnessReceiver;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*brightnessReceiver = new BrightnessBroadcastReceiver();*/
        RuanganRepository.initialize(this);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = LoginFragment.newInstance();
            fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the BroadcastReceiver to listen for the alarm trigger
        /*registerReceiver(brightnessReceiver, new IntentFilter("id.secondgroup.laboratorydigitalsignagesystem.BROADCAST"));
        // Schedule the brightness changes when the app resumes (usually when it's opened)
        scheduleBrightnessChanges();*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the BroadcastReceiver to avoid potential leaks
        /*unregisterReceiver(brightnessReceiver);*/
    }

    private void scheduleBrightnessChanges() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        // Set the time to 7 pm
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 0);
        scheduleAlarm(calendar, BRIGHTNESS_REQUEST_CODE);

        // Set the time to 7 am
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        scheduleAlarm(calendar, BRIGHTNESS_REQUEST_CODE + 1);
    }

    private void scheduleAlarm(Calendar calendar, int requestCode) {
        Intent intent = new Intent("id.secondgroup.laboratorydigitalsignagesystem.BROADCAST");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}