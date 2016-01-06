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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("setting up");
        final Context context = this;//for alert dialog

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        System.out.println("\nmain activity setup completed\n");

        //test here. AC
        displayPrivateEvents();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] tools = {"Existing Event", "Creating Public Event", "Creating Private Schedule"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                //setup for the edit menu

                builder.setTitle("Choice New Schedule from").setSingleChoiceItems(tools, -1,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int selected){

                                dialog.cancel();

                                switch (selected) {

                                    //Existing Event
                                    case 0:
                                        break;

                                    //Creating Public Event
                                    case 1:
                                        Intent intent1 = new Intent(MainActivity.this, CreatePublicEvent.class);
                                        intent1.addFlags(1);
                                        startActivity(intent1);
                                        break;

                                    //Creating Private Schedule
                                    case 2:
                                        Intent intent2 = new Intent(MainActivity.this, CreatePublicEvent.class);
                                        intent2.addFlags(2);
                                        startActivity(intent2);
                                        break;
                                }
                            }
                        });

                //show the dialog
                AlertDialog ad = builder.create();
                ad.show();

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });
    }

    public void displayPrivateEvents() {
        TextView content = (TextView) findViewById(R.id.contentText);
        this.deleteDatabase("events");
        //content.setText("Hello Jason");
        MyEventDBHandler helper = new MyEventDBHandler(this, null, null, 1);

        content.setText("Event\tDate\tLocation\n\n" +
                helper.databaseToString());
        System.out.println("\ndisplay private events\n");
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
