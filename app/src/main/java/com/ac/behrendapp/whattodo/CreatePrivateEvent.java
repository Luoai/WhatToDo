package com.ac.behrendapp.whattodo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

import java.util.Calendar;

public class CreatePrivateEvent extends AppCompatActivity {

    private EditText nameET, desET, locET;
    private TextView dateTextView, startTimeTextView, endTimeTextView, textView, title;
    // private RatingBar impLevelRB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_private_event);

        initialize();

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

            //TODO

        }
    }

    //Jump back to the previous page after clicking CANCEL button
    public void onCancelClick(View v) {
        if (v.getId() == R.id.cancelButton) {

            //TODO

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

            Event newEvent = new Event();
            newEvent = getEvent();

            MyEventDBHandler eventDBHandler = new MyEventDBHandler(this, null, null, 1);
            eventDBHandler.addEvent(newEvent);

/*
            textView = (TextView) findViewById(R.id.textView);

            textView.setText(newEvent.getEDate() + "\n" +
                    newEvent.getEYear() + " " +
                    newEvent.getEMonth() + " " +
                    newEvent.getEDay() + "\n" +
                    newEvent.getEEndHour() + " " +
                    newEvent.getEEndMin() + "\n" +
                    newEvent.getEStartHour() + " " +
                    newEvent.getEStartMin());
*/

        }

    }

    //This function is to save data into Event Object from the textfield
    public Event getEvent() {

        Event e = new Event();
        //get data from fields

        //get location
        String loc = locET.getText().toString();
        e.setELoc(loc);

        // boolean flag_validInput = true;

        //get name
        String name = nameET.getText().toString();
        name = getName(name);
        if (name != null) {
            e.setEName(name);
        } else {
            e = null;
            return e;
        }

        //get date
        int day, month, year;
        String date = dateTextView.getText().toString();
        int dateInt = getDate(date);
        if (dateInt != -1) {
            day = dateInt % 100;
            dateInt /= 100;
            month = dateInt % 100;
            year = dateInt / 100;

            e.setEDate(dateInt);
            e.setEDay(day);
            e.setEMonth(month);
            e.setEYear(year);
        } else {
            e = null;
            return e;
        }

        //get startHour,startMin
        int startHour, startMin;
        String startTime = startTimeTextView.getText().toString();
        int startTimeInt = getStartTime(startTime);
        if (startTimeInt != -1) {

            startMin = startTimeInt % 100;
            startHour = startTimeInt / 100;

            e.setEStartTime(startTime);
            e.setEStartHour(startHour);
            e.setEStartMin(startMin);
        } else {
            e = null;
            return e;
        }

        //get endHour,endMin
        int endHour, endMin;
        String endTime = endTimeTextView.getText().toString();
        int endTimeInt = getEndTime(startTime, endTime);
        if (endTimeInt != -1) {
            endMin = endTimeInt % 100;
            endHour = endTimeInt / 100;

            e.setEEndTime(endTime);
            e.setEEndHour(endHour);
            e.setEEndMin(endMin);
        } else {
            e = null;
            return e;
        }

        //get descrpition
        String decription = desET.getText().toString();
        e.setDescription(decription);

        return e;
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
            alertmessage("Your " + name + " cannot be empty");
            return null;
        } else
            return name;
    }

    public int getDate(String date) {
        //Input: String "mm/dd/yyyy"
        //Output: 8-digit Integer yyyymmdd
        //          or -1 if the input is empty (pop out error message)
        int result = -1;
        if (date == "mm/dd/yyyy") {
            alertmessage("Date cannot be empty");
        } else {
            String[] str = date.split("/");
            int day = Integer.parseInt(str[1]);
            int month = Integer.parseInt(str[0]);
            int year = Integer.parseInt(str[2]);
            result = year * 10000 + month * 100 + day;
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
        if (startTime == "hh:mm") {
            alertmessage("Start time cannot be empty");
        } else {

            String[] str = startTime.split(":");
            int hour = Integer.parseInt(str[0]);
            int minute = Integer.parseInt(str[1]);
            result = hour * 100 + minute;
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
        if (endTime == "hh:mm")
            alertmessage("End time cannot be empty");
        else if (endTime.compareTo(startTime) < 0)
            alertmessage("End time cannot be earlier than starting time");
        else {
            String[] str = endTime.split(":");
            int hour = Integer.parseInt(str[0]);
            int minute = Integer.parseInt(str[1]);
            result = hour * 100 + minute;
        }
        return result;

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


}


