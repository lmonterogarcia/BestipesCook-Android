package com.medac.bestipescook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;

    NoticiasFragment frNoticias = NoticiasFragment();
    ChallengesFragment frChallenges = ChallengesFragment();
    RecetasFragment frRecetas = RecetasFragment();
    RankingFragment frRanking = RankingFragment();
    CuentaFragment frCuenta = CuentaFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.botton_navigation);
        //navigationView.liste

        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,0,0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        openFrament(new NoticiasFRagment());
    }

    private void openFrament(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.menu_noticias:openFrament(new NoticiasFragment()); break;
            case R.id.menu_challenges:openFrament(new ChallengesFragment()); break;
            case R.id.menu_recetas:openFrament(new RecetasFragment()); break;
            case R.id.menu_ranking:openFrament(new RankingFragment()); break;
            case R.id.menu_cuenta:openFrament(new CuentaFragment()); break;
        }
        return false;
    }
}