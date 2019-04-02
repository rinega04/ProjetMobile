package com.example.app.Vue;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import com.example.app.R;


public class MainActivity extends AppCompatActivity{

    MediaPlayer mediaPlayer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mediaPlayer == null) {
         mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.back_sound);
         mediaPlayer.setLooping(true);
         mediaPlayer.start();
        }

        Button button1 = (Button) findViewById(R.id.button);


        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), ListeAfficheur.class);
                startActivityForResult(myIntent, 0);
                overridePendingTransition(R.anim.slide_to_right, R.anim.slide_to_left);
            }

        });


    }

}
