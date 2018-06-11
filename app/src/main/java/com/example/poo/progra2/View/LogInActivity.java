package com.example.poo.progra2.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.poo.progra2.R;
import com.example.poo.progra2.View.ActivitiesAsesor.AsesorActivity;
import com.example.poo.progra2.View.ActivitiesEncargado.EncargadoActivity;
import com.example.poo.progra2.View.ActivitiesPracticante.PracticanteActivity;
import com.example.poo.progra2.View.ActivitiesProfCurso.PCursoActivity;
import com.example.poo.progra2.logica.Calendario;
import com.example.poo.progra2.logica.Entregable;
import com.example.poo.progra2.logica.Periodo;
import com.example.poo.progra2.xml.PeriodoDAO;
import com.example.poo.progra2.xml.PracticanteDAO;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

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
        final PeriodoDAO daoPeriodo = new PeriodoDAO(getApplicationContext());
        Calendario calendario = new Calendario();
        Entregable entregable = new Entregable("Tarea 1","Esto es una tarea","15/2/18",true);
        calendario.agregarEntregable(entregable);
        daoPeriodo.agregarPeriodo("1 Semestre","2018",calendario);
        Calendario calendario1 =  new Calendario();
        daoPeriodo.agregarPeriodo("2 semestre","2018",calendario1);
        daoPeriodo.writeXml();
        final PracticanteDAO dao = new PracticanteDAO(getApplicationContext());
        dao.writeXml();
        dao.parseXml();
        dao.writeXml();
        Button button = (Button) findViewById(R.id.log_in_button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int tipo  = usuarios.getSelectedItemPosition();
                switch (tipo){
                    case 0:
                        EditText idField = findViewById(R.id.idTextField);
                        String id = idField.getText().toString();
                        EditText contraField = findViewById(R.id.contraTextField);
                        String contra = contraField.getText().toString();
                        if(dao.logIn(id,contra)) {
                            startActivity(new Intent(LogInActivity.this, PracticanteActivity.class));
                        }else{

                        }
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
