package com.example.poo.progra2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.poo.progra2.ActivitiesAsesor.AsesorActivity;
import com.example.poo.progra2.ActivitiesPracticante.PracticanteActivity;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void abrirMenuPracticante(View v){
        startActivity(new Intent(LogInActivity.this, AsesorActivity.class));
    }
}
