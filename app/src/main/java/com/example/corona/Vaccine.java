package com.example.corona;

public class Vaccine {
    public String place;
    public String date;

    public Vaccine(String place, String date){
        this.place=place;
        this.date=date;
    }
    public Vaccine(){
        place=null;
        date=null;
    }
}
