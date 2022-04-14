//Abraham Ebietsuwa, N01420710, RNB
package abraham.ebietsuwa0710;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AbrahamActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar=findViewById(R.id.toolbar);
      //setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new homeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_message);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new homeFragment()).commit();
                break;
            case R.id.nav_downloads:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new downloadsFragment()).commit();
                break;
            case R.id.nav_weather:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new weatherFragment()).commit();
                break;
            case R.id.nav_shape:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new shapeFragment()).commit();
                break;
            case R.id.nav_filecontent:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new filecontentFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new settingsFragment()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override

    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
         }else super.onBackPressed();
}
}