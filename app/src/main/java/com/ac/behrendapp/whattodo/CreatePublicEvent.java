package com.ac.behrendapp.whattodo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.util.Calendar;

public class CreatePublicEvent extends AppCompatActivity {

    private Spinner locSpinner;
    private EditText nameET, startTimeET, endTimeET, desET;
    private TextView dateET, ETstartTime, ETendTime;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_public_event);
        initialize();
        //Add items to spinners here
        addItemsToLocSpinner();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    // This function is to initialize all the field variable and wait for input
    public void initialize() {
        locSpinner = (Spinner) findViewById(R.id.Loc_Spinner);
        nameET = (EditText) findViewById(R.id.eventName);
        //dateET=(EditText)findViewById(R.id.date);
        startTimeET = (EditText) findViewById(R.id.start_time);
        endTimeET = (EditText) findViewById(R.id.end_time);
        desET = (EditText) findViewById(R.id.des);
        dateET = (TextView) findViewById(R.id.date);
    }

    //This function is to pop out a dialog to pick date when click on the text field of date
    public void onDateClick(View v) {
        final TextView ET_dateTime = (TextView) findViewById(R.id.date);
        if (v.getId() == R.id.date) {
            Calendar mcurrentDate = Calendar.getInstance();
            int mYear = mcurrentDate.get(Calendar.YEAR);
            int mMonth = mcurrentDate.get(Calendar.MONTH);
            int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog mDatePicker;
            mDatePicker = new DatePickerDialog(CreatePublicEvent.this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    selectedmonth += 1;
                    ET_dateTime.setText("" + selectedmonth + "/" + selectedday + "/" + selectedyear);
                }
            }, mYear, mMonth, mDay);
            mDatePicker.setTitle("Select Date");
            mDatePicker.show();
        }
    }

    //This function is to pop out a dialog to pick time when click on the text field of StartTime
    public void onStartTimeClick(View v) {
        final EditText ET_startTime = (EditText) findViewById(R.id.start_time);
        if (v.getId() == R.id.start_time) {
            Calendar currentTime = Calendar.getInstance();
            int curr_hour = currentTime.HOUR_OF_DAY;
            int curr_minute = currentTime.MINUTE;

            TimePickerDialog TimePicker1;


            TimePicker1 = new TimePickerDialog(CreatePublicEvent.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            ET_startTime.setText(hourOfDay + ":" + minute);
                        }
                    }, curr_hour, curr_minute, false);
            TimePicker1.setTitle("Select Time");
            TimePicker1.show();
        }
    }

    //This function is to pop out a dialog to pick time when click on the text field of EndTime
    public void onEndTimeClick(View v) {
        final EditText ET_endTime = (EditText) findViewById(R.id.end_time);
        if (v.getId() == R.id.end_time) {
            Calendar currentTime = Calendar.getInstance();
            int curr_hour = currentTime.HOUR_OF_DAY;
            int curr_minute = currentTime.MINUTE;

            TimePickerDialog TimePicker1;


            TimePicker1 = new TimePickerDialog(CreatePublicEvent.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            ET_endTime.setText(hourOfDay + ":" + minute);
                        }
                    }, curr_hour, curr_minute, false);
            TimePicker1.setTitle("Select Time");
            TimePicker1.show();
        }
    }

    //This function is going to save the user input into DB when the next Button is clicked
    public void onNextClick(View v) {

        if (v.getId() == R.id.nextButton) {

            //get data from fields
            int spinnerPos = locSpinner.getSelectedItemPosition();
            String loc = locSpinner.getItemAtPosition(spinnerPos).toString();
            String name = nameET.getText().toString();
            String date = dateET.getText().toString();
            String startTime = startTimeET.getText().toString();
            String endTime = endTimeET.getText().toString();
            String des = desET.getText().toString();


          /*
                        //Set up a new event by the input data
                        Event newEvent= new Event();
                        newEvent.setEName(name);
                        newEvent.setEDate(date);
                        newEvent.setEStartTime(startTime);
                        newEvent.setEEndTime(endTime);
                        newEvent.setELoc(loc);
                        newEvent.setDescription(des);

                        // Save event object into Database
                        MyEventDBHandler eventDBHandler;
                        eventDBHandler = new MyEventDBHandler(this,null,null,1);
                        eventDBHandler.addEvent(newEvent);
                        */


        }

    }

    public String getName(String name) {
        //A. This function is to check whether the name input is valid
        //      and will return a boolean value.
        //B. If the name is not correct, it will pop out a dialog to display
        //      the requirements, ask users to re-input the value, stay at the previous page and return -1,
        //C. else return name;
        // Requirement:
        //  1) not empty
        if (name == null) {
            alertmessage("Your name cannot be empty");
            return null;
        } else
            return name;
    }

    public int getDate(String date) {
        //A. This function is to check whether the date input is valid.
        //      and will return a boolean value
        //B. If the name is not correct, it will pop out a dialog to display
        //      the requirements, ask users to re-input the value, stay at the previous page and return -1,
        //C. else return mmddyyyy;
        //D. Requirements: The String date should be
        //  1)not empty
        //  2)the date has exact format mm/dd/yyyy
        //  * String "mm/dd/yyyy" => int "mmddyyyy"
        //  3)the date is some day after today
        int result = -1;
        if (date == null) {
            alertmessage("Date cannot be empty");
        } else {
            String[] str = date.split("/");
            int day = Integer.parseInt(str[0]);
            int month = Integer.parseInt(str[1]);
            int year = Integer.parseInt(str[2]);
            result = day * 1000000 + month * 10000 + year;
        }
        return result;
    }

    public int getStartTime(String startTime) {
        //A. This function is to check whether the date input is vaild
        //      and will a boolean value
        //B. If the name is not correct, it will pop out a dialog to display
        //      the requirements, ask users to re-input the value, stay at the previous page and return -1,
        //C. else return integer hhmm;
        //D. Requirement:
        //  1)not empty
        //  2)the time has exactly format hh:mm
        int result = -1;
        if (startTime == null) {
            alertmessage("Start time cannot be empty");
        } else {
            String[] str = startTime.split("/");
            int hour = Integer.parseInt(str[0]);
            int minute = Integer.parseInt(str[1]);
            result = hour*100 + minute;
        }
        return result;
    }

    public int getEndTime(String startTime, String endTime) {
        //A. This function is to check whether the date input is vaild
        //      and will a boolean value
        //B. If the name is not correct, it will pop out a dialog to display
        //      the requirements, ask users to re-input the value, stay at the previous page and return -1,
        //C. else return integer hhmm;
        //D. Requirement:
        //  1)not empty
        //  2)the time has exactly format hh:mm
        // 3)the endTime should be some time after startTime
        int result = -1;
        if (endTime == null)
            alertmessage("End time cannot be empty");
        else if (endTime.compareTo(startTime) < 0)
            alertmessage("End time cannot be earlier than starting time");
        else {
            String[] str = endTime.split("/");
            int hour = Integer.parseInt(str[0]);
            int minute = Integer.parseInt(str[1]);
            result = hour*100 + minute;
        }
        return result;
    }

    private void alertmessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message,Toast.LENGTH_LONG);
        /*
        LayoutInflater askInput = LayoutInflater.from(this);
        View promptView = askInput.inflate(R.layout.prompt, null);

        // get edit text setup and give dialog a view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(promptView);
        final EditText inputText = (EditText) promptView.findViewById(R.id.userInput);
        inputText.setText(UploadRequestUtil.address);
        AlertDialog dialog;

        // build the dialog
        builder.setCancelable(false)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        // get the user input
                        UploadRequestUtil.address = inputText.getText().toString();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });

        // show the dialog
        dialog = builder.create();
        dialog.show();
        */
    }

    // This function is to add items on the location Spinner
    public void addItemsToLocSpinner() {
        locSpinner = (Spinner) findViewById(R.id.Loc_Spinner);
        ArrayAdapter<CharSequence> locSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.LOCATIONS,
                        android.R.layout.simple_spinner_item);

        locSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        locSpinner.setAdapter(locSpinnerAdapter);
    }


    // TBC
    public void addListenerToLocSpinner() {
        //https://www.youtube.com/watch?v=OY8dRInKaqY
        final String loc;
        locSpinner = (Spinner) findViewById(R.id.Loc_Spinner);
        locSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(position).toString();
                //TextView test=(TextView)findViewById(R.id.test);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreatePublicEvent Page", // TODO: Define a title for the content shown.
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
                "CreatePublicEvent Page", // TODO: Define a title for the content shown.
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
