package com.example.poo.progra2.View.ActivitiesAsesor;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.poo.progra2.View.LogInActivity;
import com.example.poo.progra2.R;

public class ConsultarMinutasActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_minutas);
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
                        switch (item.getItemId()){
                            case R.id.nav_calendario:
                                startActivity(new Intent(ConsultarMinutasActivity.this,CalendarioAsesorActivity.class));
                                break;
                            case R.id.nav_asignar:
                                startActivity(new Intent(ConsultarMinutasActivity.this, AsignarNotaActivity.class ));
                                break;
                            case R.id.nav_consultarEntregables:
                                startActivity(new Intent(ConsultarMinutasActivity.this, ConsultarEntregableActivity.class));
                                break;
                            case R.id.nav_solicitar:
                                startActivity(new Intent(ConsultarMinutasActivity.this, SolicitarReunionActivity.class));
                                break;
                            case R.id.nav_inicio:
                                startActivity(new Intent(ConsultarMinutasActivity.this, AsesorActivity.class));
                                break;
                            case R.id.nav_log_out:
                                startActivity(new Intent(ConsultarMinutasActivity.this, LogInActivity.class));
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
