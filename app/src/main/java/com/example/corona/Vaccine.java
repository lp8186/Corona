package com.example.corona;

public class Vaccine {
    public String place;
    public String date;

    public Vaccine(String place, String date){
        this.place=place;
        this.date=date;
    }
    public Vaccine(){
        place="null";
        date="null";
    }
    public String getPlace(){
        return place;
    }
    public String getDate(){
        return date;
    }
    public void setPlace(String place){
        this.place=place;
    }
    public void setDate(String data){
        this.date=date;
    }
}
