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
        } else {
            textView.setText(data.getData()[count].getProfile().getFirst_name());
        }
    }
}
