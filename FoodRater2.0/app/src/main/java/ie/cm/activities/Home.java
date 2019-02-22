package ie.cm.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.widget.Switch;


import com.google.firebase.auth.FirebaseAuth;
import com.google.rpc.Help;

import ie.cm.R;
import ie.cm.fragments.AddFragment;
import ie.cm.fragments.CoffeeFragment;
import ie.cm.fragments.EditFragment;
import ie.cm.fragments.SearchFragment;
import ie.cm.models.Coffee;
import ie.cm.notes.NoteActivity;
import ie.cm.notes.Note;
import ie.cm.registration.LoginActivity;
import ie.cm.registration.ProfileActivity;

public class Home extends Base
        implements NavigationView.OnNavigationItemSelectedListener,
        EditFragment.OnFragmentInteractionListener {

    FragmentTransaction ft;
//new code down
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void menuHome(MenuItem m) {
        startActivity(new Intent(this, Home.class));
    }

    public void menuInfo(MenuItem m) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.appAbout))
                .setMessage(getString(R.string.appDesc)
                        + "\n\n"
                        + getString(R.string.appMoreInfo))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    public void menuNote(MenuItem m) {
        startActivity(new Intent(this, NoteActivity.class));
        //startActivity(new Intent(this, ie.cm.journal.NoteActivity.class));

    }

    public void menuProfile(MenuItem m) {
        startActivity(new Intent(this, ProfileActivity.class));

    }

    public void menuJournal(MenuItem m) {
        startActivity(new Intent(this, ie.cm.journal.NotesListActivity.class));

    }
//new code up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Information", Snackbar.LENGTH_LONG)
                        .setAction("More Info...", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                            }
                        }).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ft = getSupportFragmentManager().beginTransaction();

        CoffeeFragment fragment = CoffeeFragment.newInstance();
        ft.replace(R.id.homeFrame, fragment);
        ft.commit();

        this.setupCoffees();
        this.setTitle(R.string.recentlyViewedLbl);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        int id = item.getItemId();
        Fragment fragment;
        ft = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_home) {
            fragment = CoffeeFragment.newInstance();
            ((CoffeeFragment)fragment).favourites = false;
            ft.replace(R.id.homeFrame, fragment);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_add) {
            fragment = AddFragment.newInstance();
            ft.replace(R.id.homeFrame, fragment);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_favourites) {
            fragment = CoffeeFragment.newInstance();
            ((CoffeeFragment)fragment).favourites = true;
            ft.replace(R.id.homeFrame, fragment);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_search) {
            fragment = SearchFragment.newInstance();
            ((CoffeeFragment)fragment).favourites = false;
            ft.replace(R.id.homeFrame, fragment);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_note) {
            fragment = AddFragment.newInstance();
            ft.replace(R.id.homeFrame, fragment);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_camera) {


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setupCoffees(){

    }

    @Override
    public void toggle(View v) {
        EditFragment editFrag = (EditFragment)
                getSupportFragmentManager().findFragmentById(R.id.homeFrame);
        if (editFrag != null) {
            editFrag.toggle(v);
        }
    }

    @Override
    public void saveCoffee(View v) {
        EditFragment editFrag = (EditFragment)
                getSupportFragmentManager().findFragmentById(R.id.homeFrame);
        if (editFrag != null) {
            editFrag.saveCoffee(v);
        }
    }

    // below method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuLogout:
                //case R.id.menuHome:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));

                // startActivity(new Intent(this, Home.class));

                break;
        }

        return true;
    }
}
