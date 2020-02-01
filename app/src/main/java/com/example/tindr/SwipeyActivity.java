package com.example.tindr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SwipeyActivity extends AppCompatActivity {

    public static DataObject data;
    private TextView textView;
    private Button next;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipey);

        textView = findViewById(R.id.testTv);
        next = findViewById(R.id.button3);

        count = 0;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                loadNextDr();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadNextDr();
    }

    private void loadNextDr(){
        if(count >= data.getData().length){
            Toast.makeText(getApplicationContext(), "Sorry, no more doctors available", Toast.LENGTH_LONG).show();
            return;
        }

        Profile profile = data.getData()[count].getProfile();
        String firstName = profile.getFirst_name();
        String lastName = profile.getLast_name();
        String image_url = profile.getLast_name();
        String gender = profile.getGender();
        String bio = profile.getBio();
        Language[] l = profile.getLanguages();
        String[] languages = new String[l.length];
        for(int i = 0; i<languages.length; i++){
            languages[i] = l[i].getName();
        }

        Specialty[] sps = data.getData()[count].getSpecialties();
        String[] specialties = new String[sps.length];
        for(int i = 0; i<specialties.length; i++){
            specialties[i] = sps[i].getDescription();
        }

        Practice practice = data.getData()[count].getPractices()[0];
        double lat = practice.getLat();
        double lon = practice.getLon();
        Phone[] phones = practice.getPhones();
        String[] phoneNums = new String[phones.length];
        for(int i = 0; i<phoneNums.length; i++){
            phoneNums[i] = phones[i].getNumber();
        }

        textView.setText(firstName);
    }
}
