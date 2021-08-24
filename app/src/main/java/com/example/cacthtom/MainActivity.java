package com.example.cacthtom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView text1, text2;
    private int score = 0, counter = 1000, counttime;
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    private ImageView[] imageViews;
    private MediaPlayer  mediaPlayer2,mediaPlayer;
    private Handler handler;
    private Runnable runnable;
    private Intent changeactvity;
    private Random random;
    private int getcounter;
    private static int maxpoint;
    private boolean click=false,music,musicstart=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.textview);
        text2 = findViewById(R.id.textview2);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);
        imageViews = new ImageView[]{image1, image2, image3, image4, image5, image6, image7, image8, image9};
        mediaPlayer2 = MediaPlayer.create(this, R.raw.second);
        //mediaPlayer.start();
        Intent intent = getIntent();
        Intent intent1 = getIntent();
        click=intent1.getBooleanExtra("click",false);
        music=intent1.getBooleanExtra("music",false);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        if(music || musicstart){
            mediaPlayer = MediaPlayer.create(this, R.raw.music);
            mediaPlayer.start();
        }
        changeactvity = new Intent(MainActivity.this, firstactivity.class);
        counttime = intent1.getIntExtra("name", 0);
        getcounter = intent1.getIntExtra("name1", 1000);
        hidephotos();
        new CountDownTimer(counttime, 1000) {
            @Override
            public void onTick(long i) {
                text1.setText("Time:" + i / 1000);
            }
            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                if(score>maxpoint){
                    maxpoint=score;
                }
                for (ImageView image : imageViews) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("New Game?");
                alert.setMessage("Do you want to continue?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendmax();
                        functioncall(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendmax();
                        startActivity(changeactvity);
                        mediaPlayer.stop();
                        finish();
                    }
                });
               alert.show();
            }
        }.start();
    }

    private void hidephotos() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageViews) {
                    image.setVisibility(View.INVISIBLE);
                }
                random = new Random();
                int a = random.nextInt(9);
                imageViews[a].setVisibility(View.VISIBLE);
                handler.postDelayed(this, getcounter);
            }
        };
        handler.post(runnable);
    }

    public void clickme(View view) {
        score++;
        if(click){
            mediaPlayer2.start();
        }
        text2.setText("Score:" + score);
    }
    public void functioncall(Intent intent) {
        musicstart=true;
        mediaPlayer.stop();
        finish();
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        sendmax();
        startActivity(changeactvity);
        mediaPlayer.stop();
        finish();
        super.onBackPressed();
    }
    public void sendmax(){
        changeactvity.putExtra("maxpoint",maxpoint);
    }

}


