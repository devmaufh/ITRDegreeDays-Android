package com.devmaufh.itrdegreedays.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.devmaufh.itrdegreedays.Database.InsectViewModel;
import com.devmaufh.itrdegreedays.Database.InsectsDatabase;
import com.devmaufh.itrdegreedays.Entities.InsectEntity;
import com.devmaufh.itrdegreedays.Fragments.FragmentAbout;
import com.devmaufh.itrdegreedays.Fragments.FragmentHome;
import com.devmaufh.itrdegreedays.Fragments.FragmentSettings;
import com.devmaufh.itrdegreedays.Fragments.FragmentSync;
import com.devmaufh.itrdegreedays.R;
import com.devmaufh.itrdegreedays.Utilities.DatabaseUtilities;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        bindUI();
        setFragmentByDefault();
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction= false;
                Fragment fragment=null;
                switch (item.getItemId()){
                    case R.id.menu_menu:
                        fragment= new FragmentHome();
                        fragmentTransaction=true;
                        break;
                    case R.id.menu_settings:
                        fragment= new FragmentSettings();
                        fragmentTransaction=true;
                        break;
                    case R.id.menu_cuenta:
                        fragment= new FragmentSync();
                        fragmentTransaction=true;
                        break;
                    case R.id.menu_about:
                        fragment= new FragmentAbout();
                        fragmentTransaction=true;
                }
                if(fragmentTransaction){
                    changeFragment(fragment,item);
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });

        //Testing Database
        //databaseTest();
    }

    private void setToolbar() {
        Toolbar toolbar=(Toolbar)findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void changeFragment(Fragment fragment, MenuItem item){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_contentFrame,fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }
    private void setFragmentByDefault(){
        changeFragment(new FragmentHome(),navigationView.getMenu().getItem(0));
    }

    private void bindUI(){
        drawerLayout=(DrawerLayout)findViewById(R.id.home_drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.home_navView);
        //dbInit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    //Database test:(
    private InsectViewModel mInsectViewModel;
    private void databaseTest(){
        mInsectViewModel= ViewModelProviders.of(this).get(InsectViewModel.class);
        InsectEntity test= new InsectEntity();
        //test.setName("Testing"+ Calendar.getInstance().getTime());test.setTl(10);test.setTu(109);
        //mInsectViewModel.insertInsect(test);
        mInsectViewModel.getmAllInsects().observe(this, new Observer<List<InsectEntity>>() {
            @Override
            public void onChanged(@Nullable List<InsectEntity> insectEntities) {
                for(InsectEntity insectEntity: insectEntities){
                    Toast.makeText(MainActivity.this, "Testing database :'v "+insectEntity.getName(), Toast.LENGTH_LONG).show();
                    Log.w("ROOM P",insectEntity.getName());
                }
            }
        });
    }


}
