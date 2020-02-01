package com.example.tindr;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchData extends AsyncTask<Void, Void, Void> {

    private String data = "";
    private Activity activity;

    public FetchData(Activity act){
        activity = act;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.betterdoctor.com/2016-03-01/doctors?specialty_uid=psychiatrist&location=ca&gender=female&skip=0&limit=10&user_key=7a728d1907e7e9fd24f301e36a806c34");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = "";
            while(line != null){
                line = br.readLine();
                data += line;
            }
            data = data.substring(0, data.length()-4);
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Gson gson = new Gson();
        SwipeyActivity.data = gson.fromJson(this.data, DataObject.class);

        activity.startActivity(new Intent(activity, SwipeyActivity.class));
    }
}
