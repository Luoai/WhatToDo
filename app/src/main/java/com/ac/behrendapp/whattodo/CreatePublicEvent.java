package com.ac.behrendapp.whattodo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import org.w3c.dom.Text;

import java.util.Calendar;

public class CreatePublicEvent extends AppCompatActivity {

    private Spinner locSpinner;
    private EditText nameET,startTimeET,endTimeET,desET;
    private TextView dateET,ETstartTime,ETendTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_public_event);
        initialize();
        //Add items to spinners here
        addItemsToLocSpinner();






    }

    // This function is to initialize all the field variable and wait for input
    public void initialize(){
        locSpinner=(Spinner)findViewById(R.id.Loc_Spinner);
        nameET=(EditText)findViewById(R.id.eventName);
        //dateET=(EditText)findViewById(R.id.date);
        startTimeET=(EditText)findViewById(R.id.start_time);
        endTimeET=(EditText)findViewById(R.id.end_time);
        desET=(EditText)findViewById(R.id.des);
        dateET=(TextView)findViewById(R.id.date);
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
                    selectedmonth+=1;
                    ET_dateTime.setText("" + selectedmonth + "/" + selectedday + "/" + selectedyear);
                }
            }, mYear, mMonth, mDay);
            mDatePicker.setTitle("Select Date");
            mDatePicker.show();
        }
    }

    //This function is to pop out a dialog to pick time when click on the text field of StartTime
    public void onStartTimeClick(View v){
        final EditText ET_startTime = (EditText)findViewById(R.id.start_time);
        if(v.getId()==R.id.start_time){
            Calendar currentTime=Calendar.getInstance();
            int curr_hour=currentTime.HOUR_OF_DAY;
            int curr_minute=currentTime.MINUTE;

            TimePickerDialog TimePicker1;


            TimePicker1=new TimePickerDialog(CreatePublicEvent.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            ET_startTime.setText( hourOfDay + ":" + minute);
                        }
                    },curr_hour,curr_minute,false);
            TimePicker1.setTitle("Select Time");
            TimePicker1.show();
        }
    }

    //This function is to pop out a dialog to pick time when click on the text field of EndTime
    public void onEndTimeClick(View v){
        final EditText ET_endTime = (EditText)findViewById(R.id.end_time);
        if(v.getId()==R.id.end_time){
            Calendar currentTime=Calendar.getInstance();
            int curr_hour=currentTime.HOUR_OF_DAY;
            int curr_minute=currentTime.MINUTE;

            TimePickerDialog TimePicker1;


            TimePicker1=new TimePickerDialog(CreatePublicEvent.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            ET_endTime.setText( hourOfDay + ":" + minute);
                        }
                    },curr_hour,curr_minute,false);
            TimePicker1.setTitle("Select Time");
            TimePicker1.show();
        }
    }

    //This function is going to save the user input into DB when the next Button is clicked
    public void onNextClick(View v){

        if(v.getId()==R.id.nextButton){

            //get data from fields
            int spinnerPos=locSpinner.getSelectedItemPosition();
            String loc=locSpinner.getItemAtPosition(spinnerPos).toString();
            String name=nameET.getText().toString();
            String date=dateET.getText().toString();
            String startTime=startTimeET.getText().toString();
            String endTime=endTimeET.getText().toString();
            String des=desET.getText().toString();


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

    public String getName(String name){
        //A. This function is to check whether the name input is valid
        //      and will return a boolean value.
        //B. If the name is not correct, it will pop out a dialog to display
        //      the requirements, ask users to re-input the value, stay at the previous page and return -1,
        //C. else return name;
        // Requirement:
        //  1) not empty
        return name;
    }

    public int getDate(String date){
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
        return -1;

    }

    public int getStartTime(String startTime){
        //A. This function is to check whether the date input is vaild
        //      and will a boolean value
        //B. If the name is not correct, it will pop out a dialog to display
        //      the requirements, ask users to re-input the value, stay at the previous page and return -1,
        //C. else return integer hhmm;
        //D. Requirement:
        //  1)not empty
        //  2)the time has exactly format hh:mm
        return -1;
    }

    public int getEndTime(String startTime, String endTime){
        //A. This function is to check whether the date input is vaild
        //      and will a boolean value
        //B. If the name is not correct, it will pop out a dialog to display
        //      the requirements, ask users to re-input the value, stay at the previous page and return -1,
        //C. else return integer hhmm;
        //D. Requirement:
        //  1)not empty
        //  2)the time has exactly format hh:mm
        // 3)the endTime should be some time after startTime
        return -1;
    }

    // This function is to add items on the location Spinner
    public void addItemsToLocSpinner(){
        locSpinner = (Spinner)findViewById(R.id.Loc_Spinner);
        ArrayAdapter<CharSequence> locSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.LOCATIONS,
                        android.R.layout.simple_spinner_item);

        locSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        locSpinner.setAdapter(locSpinnerAdapter);
    }

    // TBC
    public void addListenerToLocSpinner(){
            //https://www.youtube.com/watch?v=OY8dRInKaqY
        final String loc;
        locSpinner=(Spinner)findViewById(R.id.Loc_Spinner);
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

}
