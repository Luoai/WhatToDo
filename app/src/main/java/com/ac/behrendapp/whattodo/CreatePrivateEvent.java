package com.ac.behrendapp.whattodo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

public class CreatePrivateEvent extends AppCompatActivity {

    private EditText nameET, desET, locET;
    private TextView dateTextView, startTimeTextView, endTimeTextView, textView, title;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    // private RatingBar impLevelRB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_private_event);

        initialize();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    // This function is to initialize all the field variable and wait for input
    public void initialize() {

        nameET = (EditText) findViewById(R.id.name);
        locET = (EditText) findViewById(R.id.location);
        startTimeTextView = (TextView) findViewById(R.id.startTime);
        endTimeTextView = (TextView) findViewById(R.id.endTime);
        desET = (EditText) findViewById(R.id.des);
        dateTextView = (TextView) findViewById(R.id.date);
        // impLevelRB = (RatingBar) findViewById(R.id.ratingBar);
    }


    //Save data to DB after clicking the "CREATE" button
  /*  public void onCreateClick(View v) {

        if (v.getId() == R.id.createButton) {

            // read data from user interface to an Event Object
            Event newEvent = getEvent();
            //Save Event Object into DB
            MyEventDBHandler eventDBHandler = new MyEventDBHandler(this, null, null, 1);
            eventDBHandler.addEvent(newEvent);

            // Jump out some page

        }
    }*/

    //Reset all input field after clicking the reset button
    public void onResetClick(View v) {
        if (v.getId() == R.id.resetButton) {


        }
    }

    //Jump back to the previous page after clicking CANCEL button
    public void onCancelClick(View v) {
        if (v.getId() == R.id.cancelButton) {
        }
    }

    //This function is to pop out a dialog to pick date when click on the text field of date
    public void onDateClick(View v) {
        final TextView dateTimeTextView = (TextView) findViewById(R.id.date);
        if (v.getId() == R.id.date) {
            Calendar mcurrentDate = Calendar.getInstance();
            int mYear = mcurrentDate.get(Calendar.YEAR);
            int mMonth = mcurrentDate.get(Calendar.MONTH);
            int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog mDatePicker;
            mDatePicker = new DatePickerDialog(CreatePrivateEvent.this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    selectedmonth += 1;
                    dateTimeTextView.setText(selectedmonth + "/" + selectedday + "/" + selectedyear);
                }
            }, mYear, mMonth, mDay);
            mDatePicker.setTitle("Select Date");
            mDatePicker.show();
        }
    }

    //This function is to pop out a dialog to pick time when click on the text field of StartTime
    public void onStartTimeClick(View v) {
        final TextView startTimeTextView = (TextView) findViewById(R.id.startTime);
        if (v.getId() == R.id.startTime) {
            Calendar currentTime = Calendar.getInstance();
            int curr_hour = currentTime.HOUR_OF_DAY;
            int curr_minute = currentTime.MINUTE;

            TimePickerDialog TimePicker1;


            TimePicker1 = new TimePickerDialog(CreatePrivateEvent.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            startTimeTextView.setText(hourOfDay + ":" + minute);
                        }
                    }, curr_hour, curr_minute, false);
            TimePicker1.setTitle("Select Time");
            TimePicker1.show();
        }
    }

    //This function is to pop out a dialog to pick time when click on the text field of EndTime
    public void onEndTimeClick(View v) {
        final TextView endTimeTextView = (TextView) findViewById(R.id.endTime);
        if (v.getId() == R.id.endTime) {
            Calendar currentTime = Calendar.getInstance();
            int curr_hour = currentTime.HOUR_OF_DAY;
            int curr_minute = currentTime.MINUTE;

            TimePickerDialog TimePicker1;


            TimePicker1 = new TimePickerDialog(CreatePrivateEvent.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            endTimeTextView.setText(hourOfDay + ":" + minute);
                        }
                    }, curr_hour, curr_minute, false);
            TimePicker1.setTitle("Select Time");
            TimePicker1.show();
        }
    }


    //This function is going to save the user input into DB when the next Button is clicked
    public void onCreateClick(View v) {

        if (v.getId() == R.id.createButton) {

            Event newEvent = getEvent();

            MyEventDBHandler eventDBHandler = new MyEventDBHandler(this, null, null, 1);
            eventDBHandler.addEvent(newEvent);


        }

    }

    //This function is to save data into Event Object from the textfield
    public Event getEvent() {

        Event e = new Event();
        //get data from fields

        //get name
        String name = nameET.getText().toString();
        if (name != "") {
            e.setEName(name);
        } else {
            alertmessage("Name cannot be empty!");
            e = null;
            return e;
        }


        //get location
        String loc = locET.getText().toString();
        if (loc != "") {
            e.setELoc(loc);
        } else {
            alertmessage("Location cannot be empty!");
            return null;
        }

        //get date
        String date = dateTextView.getText().toString();
        if (date != "mm/dd/yyyy") {
            e.setSplitedDate(date);
        } else {
            alertmessage("Date cannot be empty!");
            e = null;
            return e;
        }

        //get startHour,startMin
        String startTime = startTimeTextView.getText().toString();
        if (startTime != "hh:mm") {
            e.setSplitStartTime(startTime);
        } else {
            alertmessage("Start Time Cannot be Empty!");
            e = null;
            return e;
        }

        //get endHour,endMin
        String endTime = endTimeTextView.getText().toString();
        if (endTime != "hh:mm") {
            e.setSplitEndTime(endTime);
        } else {
            alertmessage("End Time cannot be empty!");
            e = null;
            return e;
        }

        //get descrpition
        String decription = desET.getText().toString();
        e.setDescription(decription);

        return e;
    }



    private void alertmessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);

        LayoutInflater askInput = LayoutInflater.from(this);
        View promptView = askInput.inflate(R.layout.prompt, null);

        // get edit text setup and give dialog a view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(promptView);
        final TextView prop = (TextView) promptView.findViewById(R.id.prop);
        prop.setText(message);
        AlertDialog dialog;

        // build the dialog
        builder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        // show the dialog
        dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreatePrivateEvent Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.ac.behrendapp.whattodo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreatePrivateEvent Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.ac.behrendapp.whattodo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}


