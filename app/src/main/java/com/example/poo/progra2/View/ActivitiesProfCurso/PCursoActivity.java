package com.example.poo.progra2.View.ActivitiesProfCurso;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.poo.progra2.View.LogInActivity;
import com.example.poo.progra2.R;
import com.example.poo.progra2.logica.Profesor;
import com.example.poo.progra2.xml.ProfesorDAO;

public class PCursoActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private ProfesorDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcurso);
        dao = new ProfesorDAO(getApplicationContext());
        Bundle b = getIntent().getExtras();
        String id = b.getString("id");
        Profesor p = dao.buscarProfesor(id);
        String nombre = p.getNombre();
        TextView t = (TextView)findViewById(R.id.textView72);
        t.setText(id);
        TextView t2 = (TextView)findViewById(R.id.textView71);
        t2.setText(nombre);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        switch (item.getItemId()) {
                            case R.id.nav_reporteNotas:
                                startActivity(new Intent(PCursoActivity.this, ConsultarNotasFinales.class));
                                break;
                            case R.id.nav_datosPracticante:
                                startActivity(new Intent(PCursoActivity.this, ConsultarDatosActivity.class));
                                break;
                            case R.id.nav_asignarNota:
                                startActivity(new Intent(PCursoActivity.this, AsignarNotaProfCursoActivity.class));
                                break;
                            case R.id.nav_log_out:
                                startActivity(new Intent(PCursoActivity.this, LogInActivity.class));
                                break;
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
