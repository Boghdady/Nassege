package com.index.nasseg.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.index.nasseg.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by Omar on 1/7/2017.
 */
public class Utils {

    public static void notifyMe(Context context, int requestCode, Intent intent, String title, String body){
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);
        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Random r = new Random();
        notificationManager.notify(r.nextInt(), mBuilder.build());
    }

    public static void turnGPSOn(Context ctx) {
        ctx.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    public static void turnGPSOff(Context c) {
        String provider = Settings.Secure.getString(c.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (provider.contains("gps")) { //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            c.sendBroadcast(poke);
        }
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Audio.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static class InternetStatus extends AsyncTask<Context, Void, Boolean> {

        InternetStatusListener internetStatusListener;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            internetStatusListener.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Context... c) {
            return isConnectingToInternet(c[0]);

        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);
            internetStatusListener.onPostExecute();
            if (s) {
                internetStatusListener.success();
            } else {
                internetStatusListener.faild();
            }
        }

        private boolean isConnectingToInternet(Context c) {


            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("https://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(5000); //choose your own timeframe
                urlc.setReadTimeout(5000); //choose your own timeframe
                urlc.connect();
                int networkcode2 = urlc.getResponseCode();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.d("alerttt", e.getMessage());
                return (false);  //connectivity exists, but no internet.

            }
            //return false;
        }

        public InternetStatus addListener(InternetStatusListener internetStatusListener) {
            this.internetStatusListener = internetStatusListener;
            return this;
        }

        public interface InternetStatusListener {
            void success();

            void faild();

            void onPreExecute();

            void onPostExecute();
        }
    }


    public static class Wait extends AsyncTask<Long, Void, Void> {


        TaskListener taskListener;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (taskListener != null) {
                taskListener.onPreExecute();
            }
        }


        // t in seconds
        @Override
        protected Void doInBackground(Long... t) {
            long startedTime = System.nanoTime();
            long allTime = t[0] * 1000000000l;
            while (System.nanoTime() - startedTime <= allTime) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (taskListener != null) {
                taskListener.onPostExecute();
            }
        }

        public Wait addListener(TaskListener taskListener) {
            this.taskListener = taskListener;
            return this;
        }

        public interface TaskListener {
            void onPreExecute();
            void onPostExecute();
        }
    }
}
