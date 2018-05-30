package com.example.poo.progra2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.poo.progra2.ActivitiesAsesor.AsesorActivity;
import com.example.poo.progra2.ActivitiesAsesor.AsignarNotaActivity;
import com.example.poo.progra2.ActivitiesEncargado.EncargadoActivity;
import com.example.poo.progra2.ActivitiesPracticante.PracticanteActivity;
import com.example.poo.progra2.ActivitiesProfCurso.PCursoActivity;

public class LogInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] tiposDeUsuario = {"Practicante", "Profesor de Curso", "Profesor Asesor", "Encargado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        final Spinner usuarios = (Spinner) findViewById(R.id.tiposUsuarios);
        usuarios.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,tiposDeUsuario);
        usuarios.setAdapter(aa);
        Button button = (Button) findViewById(R.id.log_in_button);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int tipo  = usuarios.getSelectedItemPosition();
                switch (tipo){
                    case 0:
                        startActivity(new Intent(LogInActivity.this, PracticanteActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(LogInActivity.this, PCursoActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(LogInActivity.this, AsesorActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(LogInActivity.this, EncargadoActivity.class));
                        break;
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> Av , View v, int position, long id){

    }

    @Override
    public void onNothingSelected(AdapterView<?> aa){

    }


}
