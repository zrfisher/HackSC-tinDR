package com.example.tindr;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;


public class SwipeyActivity extends AppCompatActivity {

    public static DataObject data;
    private TextView textView;
    private Button next, call;
    private int count;
    private String firstName, lastName, image_url, gender, bio;
    double lat, lon;
    String[] languages, specialties, phoneNums;
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE;

    //FROM TUTORIAL
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arr;
    private int i;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipey);

        textView = findViewById(R.id.testTv);
        next = findViewById(R.id.button3);
        call = findViewById(R.id.callButton);

        count = 0;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                loadNextDr();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Intent phoneCall = new Intent(Intent.ACTION_CALL);
                    phoneCall.setData(Uri.parse("tel:" + phoneNums[0]));
                    startActivity(phoneCall);
                }


            }
        });

        //FROM TUTORIAL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipey);
        arr = new ArrayList<>();
        for(int i = 0; i<data.getData().length; i++){
            arr.add(data.getData()[i].getProfile().getImage_url());
        }

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, data.getData());

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                arr.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(SwipeyActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(SwipeyActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                Toast.makeText(SwipeyActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(MyActivity.this, "Clicked!");
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED)
        {
                    ActivityCompat.requestPermissions(SwipeyActivity.this,
                    new String[] {Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }

        loadNextDr();
    }

    private void loadNextDr(){
        if(count >= data.getData().length){
            count = 0;
        }

        Profile profile = data.getData()[count].getProfile();
        firstName = profile.getFirst_name();
        lastName = profile.getLast_name();
        image_url = profile.getLast_name();
        gender = profile.getGender();
        bio = profile.getBio();
        Language[] l = profile.getLanguages();
        languages = new String[l.length];
        for(int i = 0; i<languages.length; i++){
            languages[i] = l[i].getName();
        }

        Specialty[] sps = data.getData()[count].getSpecialties();
        specialties = new String[sps.length];
        for(int i = 0; i<specialties.length; i++){
            specialties[i] = sps[i].getDescription();
        }

        Practice practice = data.getData()[count].getPractices()[0];
        lat = practice.getLat();
        lon = practice.getLon();
        Phone[] phones = practice.getPhones();
        phoneNums = new String[phones.length];
        for(int i = 0; i<phoneNums.length; i++){
            phoneNums[i] = phones[i].getNumber();
        }

        textView.setText(firstName);

    }
}
