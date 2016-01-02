package com.ac.behrendapp.whattodo;

/**
 * Created by Aiyu on 12/25/2015.
 */
public class Event {

    //Data List


    String eName,eStartTime,eEndTime,eLoc,eDate,description;

    public Event(){

    }


   // public void setEventId(int id){eventId=id;}
   // public int getEventId(){return eventId;}

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


}
