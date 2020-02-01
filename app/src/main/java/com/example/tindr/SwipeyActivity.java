package com.example.tindr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SwipeyActivity extends AppCompatActivity {

    public static DataObject data;
    private TextView textView;
    private Button next, call;
    private int count;
    private String firstName, lastName, image_url, gender, bio;
    double lat, lon;
    String[] languages, specialties, phoneNums;
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE;

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
                if(ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED)
                {
                Intent phoneCall = new Intent(Intent.ACTION_CALL);
                phoneCall.setData(Uri.parse("tel:"+phoneNums[0]));
                startActivity(phoneCall);
            }



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
            Toast.makeText(getApplicationContext(), "Sorry, no more doctors available", Toast.LENGTH_LONG).show();
            return;
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
