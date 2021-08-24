package com.example.cacthtom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class firstactivity extends AppCompatActivity {
    private Button b1, b2, b3;
    private Intent intent;
    private CheckBox check1, check2, check3;
    private TextView textView;
    private static SharedPreferences sharedPreferences;
    private int k = 0;
    private int maxpoint, storedage;
    private int z = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstactivity);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        check1 = findViewById(R.id.check1);
        textView = findViewById(R.id.highscore);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
        sharedPreferences = this.getSharedPreferences("com.example.cacthtom", MODE_PRIVATE);
        Intent getintent = getIntent();
        maxpoint = getintent.getIntExtra("maxpoint", 0);
        if (maxpoint != 0) {
            if (maxpoint > sharedPreferences.getInt("storemaxpoint", 0)) {
                sharedPreferences.edit().putInt("storemaxpoint", maxpoint).apply();
                Toast.makeText(getApplicationContext(),"You are Great!!",Toast.LENGTH_SHORT).show();
            }
        }
        storedage = sharedPreferences.getInt("storemaxpoint", 0);
        textView.setText("High Score:" + storedage);
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    k = 1;
                }
            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    z = 1;
                } else {
                    z = 0;
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void clickbutton1(View view) {
        chagingintent(20000, 750);
    }

    public void clickbutton2(View view) {
        chagingintent(15000, 400);
    }

    public void chagingintent(int a, int b) {
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", a);
        intent.putExtra("name1", b);
        if (z == 1) {
            intent.putExtra("click", true);
        }
        if (k == 1) {
            intent.putExtra("music", true);
        }
        startActivity(intent);
        finish();
    }
}