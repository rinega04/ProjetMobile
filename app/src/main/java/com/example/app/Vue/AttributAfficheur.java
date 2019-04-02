package com.example.app.Vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app.Model.Constants;
import com.example.app.Model.Pokemon;
import com.example.app.R;
import com.google.gson.Gson;

public class AttributAfficheur extends AppCompatActivity {


        private TextView txt;
        private TextView attri;
        private ImageView img;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_attribut_afficheur);

            txt = findViewById(R.id.nompoke);
            img = findViewById(R.id.pokeimg);

            Gson gson = new Gson();

            String sessionId= getIntent().getStringExtra(Constants.KEY);
            Pokemon attr = gson.fromJson(sessionId, Pokemon.class);
            txt.setText(Constants.fromHtml(attr.getName()));

            /* permet de récuperer les image d'un site pour chacun des 964 (environ) pokemon*/
            String[] values = attr.getUrl().split("/");
            Glide.with(getApplicationContext())
                    .load("https://www.pokebip.com/pokedex-images/artworks/"+values[values.length - 1]+".png")
                    .into(img);

            /*animation entre les activity*/
            overridePendingTransition(R.anim.slide_up, R.anim.slide_down);


        }
    }

