package com.learning.mydictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private static final String ROOT_FRAGMENT_TAG = "0";
    DrawerLayout drawerLayout;
    Toolbar toolbar22;
    NavigationView navigationView;
    EditText input_area;
    LinearLayout my_click;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        input_area = findViewById(R.id.input_search);


        FragmentManager fragment1 = getSupportFragmentManager();
        FragmentTransaction transaction = fragment1.beginTransaction();
        String v = String.valueOf(Integer.valueOf( R.string.app_name));


        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar22 = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar22);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar22,R.string.openDrawer,R.string.closeDrawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // default call for fragment initially
        FragsLoad(new HomePage(),0);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id  = item.getItemId();
                if (id == R.id.homepage){
                    FragsLoad(new HomePage(),0 );
                }
                else if (id == R.id.share){
                    Toast.makeText(getApplicationContext(),"I have shared something",Toast.LENGTH_LONG).show();
                }
                else if (id == R.id.developers){
                    FragsLoad(new developersFrag(),1);
                }
                else if (id == R.id.about_app){
                    FragsLoad(new AboutApp(),2);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (id == R.id.searches){
            input_area.setVisibility(View.VISIBLE);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.drawer_menu_for_search,menu);
         return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    private void FragsLoad(Fragment fragment, int uniqueId) {
        FragmentManager fragment1 = getSupportFragmentManager();
        FragmentTransaction transaction = fragment1.beginTransaction();

        if (uniqueId == 0){
            transaction.add(R.id.frags,fragment);
            //transaction.add(R.id.frags,fragment);
            // On thing that has to do that , back stack to managing correctly for that u've to do below code too
            fragment1.popBackStack(ROOT_FRAGMENT_TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        else{
            transaction.replace(R.id.frags,fragment);
            // addToBackStack(null) <- this gives us back stack management to go step by step previously
            transaction.addToBackStack(null);
            transaction.addToBackStack(ROOT_FRAGMENT_TAG);
        }
        transaction.commit();

    }
}