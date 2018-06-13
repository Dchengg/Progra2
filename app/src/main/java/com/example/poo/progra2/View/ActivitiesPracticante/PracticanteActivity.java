package com.example.poo.progra2.View.ActivitiesPracticante;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.poo.progra2.View.LogInActivity;
import com.example.poo.progra2.R;
import com.example.poo.progra2.logica.Practicante;
import com.example.poo.progra2.xml.PeriodoDAO;
import com.example.poo.progra2.xml.PracticanteDAO;

public class PracticanteActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private PracticanteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicante);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        dao = new PracticanteDAO(getApplicationContext());
        Bundle b = getIntent().getExtras();
        String id = b.getString("id");
        Practicante p = dao.buscarPracticante(id);
        String profA = p.getCorreoProfAsesor();
        String profC = p.getCorreoProfCurso();
        String correo = p.getCorreo();
        String emp = p.getEmpresa();
        TextView t = (TextView)findViewById(R.id.textView4);
        t.setText(id);
        TextView t2 = (TextView)findViewById(R.id.textView68);
        t2.setText(profA);
        TextView t3 = (TextView)findViewById(R.id.textView69);
        t3.setText(profC);
        TextView t4 = (TextView)findViewById(R.id.textView70);
        t4.setText(emp);
        TextView t5 = (TextView)findViewById(R.id.textView37);
        t5.setText(correo);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        switch (item.getItemId()){
                            case R.id.nav_entregable:
                                startActivity(new Intent(PracticanteActivity.this,RegistrarEntregableActivity.class));
                                break;
                            case R.id.nav_calendario:
                                startActivity(new Intent(PracticanteActivity.this, CalendarioPracticanteActivity.class ));
                                break;
                            case R.id.nav_minuta:
                                startActivity(new Intent(PracticanteActivity.this, MinutaActivity.class));
                                break;
                            case R.id.nav_log_out:
                                startActivity(new Intent(PracticanteActivity.this, LogInActivity.class));
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
