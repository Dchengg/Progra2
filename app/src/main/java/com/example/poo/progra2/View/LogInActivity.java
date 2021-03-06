package com.example.poo.progra2.View;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.poo.progra2.R;
import com.example.poo.progra2.View.ActivitiesAsesor.AsesorActivity;
import com.example.poo.progra2.View.ActivitiesEncargado.EncargadoActivity;
import com.example.poo.progra2.View.ActivitiesEncargado.RegistrarPracticanteActivity;
import com.example.poo.progra2.View.ActivitiesPracticante.PracticanteActivity;
import com.example.poo.progra2.View.ActivitiesProfCurso.PCursoActivity;
import com.example.poo.progra2.logica.Empresa;
import com.example.poo.progra2.logica.Encargado;
import com.example.poo.progra2.logica.Periodo;
import com.example.poo.progra2.logica.Profesor;
import com.example.poo.progra2.xml.EmpresaDAO;
import com.example.poo.progra2.xml.PeriodoDAO;
import com.example.poo.progra2.xml.PracticanteDAO;
import com.example.poo.progra2.xml.ProfesorDAO;

public class LogInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] tiposDeUsuario = {"Practicante", "Profesor de Curso", "Profesor Asesor", "Encargado"};

    private ProfesorDAO daoP;
    private PracticanteDAO dao;
    private PeriodoDAO daoPeriodo;
    private EmpresaDAO empresaDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        final Spinner usuarios = (Spinner) findViewById(R.id.tiposUsuarios);
        usuarios.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,tiposDeUsuario);
        usuarios.setAdapter(aa);
        daoP = new ProfesorDAO(getApplicationContext());
        daoP.parseXml();
        dao = new PracticanteDAO(getApplicationContext());
        dao.parseXml();
        daoPeriodo = new PeriodoDAO(getApplicationContext());
        daoPeriodo.parseXml();
        empresaDAO =  new EmpresaDAO(getApplicationContext());
        empresaDAO.parseXml();
        Button button = (Button) findViewById(R.id.log_in_button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int tipo  = usuarios.getSelectedItemPosition();
                EditText idField = findViewById(R.id.textFieldId);
                String id = idField.getText().toString();
                EditText contraField = findViewById(R.id.contraTextField);
                String contra = contraField.getText().toString();
                switch (tipo){
                    case 0:
                        if(dao.logIn(id,contra)) {
                            Intent i = new Intent(LogInActivity.this, PracticanteActivity.class);
                            i.putExtra("id", id);
                            startActivity(i);
                        }else{
                            Toast.makeText(LogInActivity.this,"Usuario/Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        if(daoP.logIn(id, contra)) {
                            Intent i = new Intent(LogInActivity.this, PCursoActivity.class);
                            i.putExtra("id", id);
                            startActivity(i);
                        }else{
                            Toast.makeText(LogInActivity.this,"Usuario/Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if(daoP.logIn(id, contra)) {
                            Intent i = new Intent(LogInActivity.this, AsesorActivity.class);
                            i.putExtra("id", id);
                            startActivity(i);
                        }else{
                            Toast.makeText(LogInActivity.this,"Usuario/Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        Encargado encargado = new Encargado();
                        if(id.equals(encargado.getUsuario()) && contra.equals(encargado.getContrasena())) {
                            startActivity(new Intent(LogInActivity.this, EncargadoActivity.class));
                        }else{
                            Toast.makeText(LogInActivity.this,"Usuario/Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
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
