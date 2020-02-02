package com.example.tindr;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;

import java.io.IOException;


public class FetchData extends AsyncTask<Void, Void, Void> {

    private String data = "";
    private Activity activity;
    String title;
    Button butt;

    public FetchData(Activity act, Button b){
        activity = act;
        butt = b;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        title = "STIPID";
        try {
            Document doc = Jsoup.connect("https://www.psychologytoday.com/us/therapists/sara-abadie-los-angeles-ca/462562").get();
            title = doc.title();
        } catch(IOException e){
            e.printStackTrace();
        }

        /*Gson gson = new Gson();
        SwipeyActivity.data = gson.fromJson(this.data, DataObject.class);*/

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        butt.setText(title);

        super.onPostExecute(aVoid);
        activity.startActivity(new Intent(activity, SwipeyActivity.class));
    }
}
