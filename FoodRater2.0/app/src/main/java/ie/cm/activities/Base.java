package ie.cm.activities;

import ie.cm.R;
import ie.cm.fragments.CoffeeFragment;
import ie.cm.main.CoffeeMateApp;
import ie.cm.models.Coffee;
import ie.cm.notes.NoteActivity;
import ie.cm.registration.ProfileActivity;
import ie.cm.registration.SignUpActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class Base extends AppCompatActivity {

    public CoffeeMateApp app;
    public Bundle activityInfo;
    public CoffeeFragment coffeeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (CoffeeMateApp) getApplication();
    }

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
                       // we could put some code here too
                    }
                })
                .show();
    }
    public void menuNote(MenuItem m) {
        startActivity(new Intent(this, NoteActivity.class));
    }
    //        startActivity(new Intent(this, ie.cm.journal.NoteActivity.class));
}

