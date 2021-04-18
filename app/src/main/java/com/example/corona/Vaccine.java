package com.example.corona;

/**
 * Vaccine.
 * @author Liad Peretz
 * @version	1.0
 * @since 14/4/2021
 * short description- new object called Vaccine.
 */
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
    public void setDate(String date){
        this.date=date;
    }
}
