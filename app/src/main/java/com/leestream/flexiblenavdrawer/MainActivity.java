package com.leestream.flexiblenavdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.leestream.flexiblenavdrawer.Fragments.AboutFragment;
import com.leestream.flexiblenavdrawer.Fragments.HomeFragment;
import com.leestream.flexiblenavdrawer.Fragments.ProfileFragment;
import com.leestream.flexiblenavdrawer.Fragments.SettingsFragment;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DuoDrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initialize();
    }

    private void initialize() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        View contentView = drawerLayout.getContentView();
        View menuView = drawerLayout.getMenuView();

        LinearLayout home = menuView.findViewById(R.id.home1);
        LinearLayout profile = menuView.findViewById(R.id.profile);
        LinearLayout settings = menuView.findViewById(R.id.settings);
        LinearLayout about = menuView.findViewById(R.id.about);

        home.setOnClickListener(this);
        profile.setOnClickListener(this);
        settings.setOnClickListener(this);
        about.setOnClickListener(this);

        replace(new HomeFragment());
    }

    @Override
    public void onClick(View v) {
        int destinationId = v.getId();
        if (destinationId == R.id.home1){
            replace(new HomeFragment(),"HOME");
        } else if (destinationId == R.id.profile) {
            replace(new ProfileFragment(),"PROFILE");
        }else if (destinationId == R.id.settings) {
            replace(new SettingsFragment(),"SETTINGS");
        }else if (destinationId == R.id.about) {
            replace(new AboutFragment(),"ABOUT");
        }
        drawerLayout.closeDrawer();
    }

    private void replace(Fragment fragment, String s) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.addToBackStack(s);
        fragmentTransaction.commit();
    }
    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
//        fragmentTransaction.addToBackStack(s);
        fragmentTransaction.commit();
    }
}