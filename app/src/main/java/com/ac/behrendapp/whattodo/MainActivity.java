package com.ac.behrendapp.whattodo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Context context = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // for the edit menu, 0 for Existing Event, 1 for Create Public Event, 2 for Create Private Schedule
                final String[] tools = {"Existing Event", "Create Public Event", "Create Private Schedule"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                //setup for the edit menu

                builder.setTitle("请选择工具").setSingleChoiceItems(tools, -1,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int selected){

                                dialog.cancel();

                                switch (selected) {
                                    case 1:
                                        Intent intent = new Intent(MainActivity.this, CreatePublicEvent.class);
                                        startActivity(intent);
                                        break;
                                }
                            }
                        });

                //show the dialog
                AlertDialog ad = builder.create();
                ad.show();

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
