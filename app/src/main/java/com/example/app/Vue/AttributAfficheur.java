package com.example.app.Vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app.Model.Constants;
import com.example.app.Model.Pokemon;
import com.example.app.R;
import com.google.gson.Gson;

public class AttributAfficheur extends AppCompatActivity {


        private TextView txt;
        private TextView attri;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_attribut_afficheur);

            txt = findViewById(R.id.nompoke);
            attri = findViewById(R.id.attripoke);

            Gson gson = new Gson();

            String sessionId= getIntent().getStringExtra(Constants.KEY);
            Pokemon attr = gson.fromJson(sessionId, Pokemon.class);
            txt.setText(Constants.fromHtml(attr.getName()));
            attri.setText(Constants.fromHtml((attr.getUrl())));

        }
    }

