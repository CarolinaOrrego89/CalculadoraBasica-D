package diana.orrego.calculadorabasica_d;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

public class UserActivity extends AppCompatActivity {

    implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            android.app.FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.fMain, new GmapFragment()).commit();

            navigationView.setCheckedItem(R.id.nav_home);


        }

        public void setActionBarTitle(String title){
            getSupportActionBar().setTitle(title);
        }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.user, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                Intent markerH = new Intent(this, MarkerActivity.class);
                startActivity(markerH);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            android.app.FragmentManager fm = getFragmentManager();

            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                fm.beginTransaction().replace(R.id.fMain, new GmapFragment()).commit();
            } else if (id == R.id.nav_maps){
                fm.beginTransaction().replace(R.id.fMain, new HomeFragment()).commit();
            } else if (id == R.id.nav_profile) {
                fm.beginTransaction().replace(R.id.fMain, new ProfileFragment()).commit();
            } else if (id == R.id.nav_doctor){
                fm.beginTransaction().replace(R.id.fMain, new DocFragment()).commit();
            } else if (id == R.id.nav_about) {
                fm.beginTransaction().replace(R.id.fMain, new AboutFragment()).commit();
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }