package com.ac.behrendapp.whattodo;

/**
 * Created by Aiyu on 12/25/2015.
 */
public class Event {

    //Data List


    String eName,eStartTime,eEndTime,eLoc,eDate,description;
    int eYear,eMonth,eDay,eStartHour,eStartMin,eEndHour,eEndMin;



    public Event(){
    }

    public void reset(){

    }

    public void setEName(String name){eName=name;}
    public String getEname(){return eName;}


    public void setELoc(String loc){eLoc=loc;}
    public String getELoc(){return eLoc;}

    public void setEStartTime(String time){eStartTime=time;}
    public String getEStartTime(){return eStartTime;}

    public void setEEndTime(String time){eEndTime=time;}
    public String getEEndTime(){return eEndTime;}

    public void setEDate(String time){eDate=time;}
    public String getEDate(){return eDate;}

    public void setDescription(String des){description=des;}
    public String getDescription(){return description;}

    public void setEEndHour(int hour){eEndHour=hour;}
    public int getEEndHour(){return eEndHour;}

    public void setEEndMin(int min){eEndMin=min;}
    public int getEEndMin(){return eEndMin;}

    public void setEStartHour(int hour){eStartHour=hour;}
    public int getEStartHour(){return eStartHour;}

    public void setEStartMin(int min){eStartMin=min;}
    public int getEStartMin(){return eStartMin;}

    public void setEDay(int day){eDay=day;}
    public int getEDay(){return eDay;}

    public void setEMonth(int month){eMonth=month;}
    public int getEMonth(){return eMonth;}

    public void setEYear(int year){eYear=year;}
    public int getEYear(){return eYear;}


}
