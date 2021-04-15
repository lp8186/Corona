package com.example.corona;

public class Users {
    public String studentName;
    public String studentLastName;
    public int studentGrade;
    public int studentNumGrade;
    public boolean studentCondition;
    public Vaccine firstVaccine;
    public Vaccine secondVaccine;

    public Users(String studentName,String studentLastName,int studentGrade,int studentNumGrade,boolean studentCondition,Vaccine firstVaccine,Vaccine secondVaccine){
        this.studentName=studentName;
        this.studentLastName=studentLastName;
        this.studentGrade=studentGrade;
        this.studentNumGrade=studentNumGrade;
        this.studentCondition=studentCondition;
        this.firstVaccine=firstVaccine;
        this.secondVaccine=secondVaccine;
    }
    public Users(){
        this.studentName=null;
        this.studentLastName=null;
        this.studentGrade= 0;
        this.studentNumGrade= 0;
        this.studentCondition= false;
        this.firstVaccine=new Vaccine(null,null);
        this.secondVaccine=new Vaccine(null,null);
    }

    public String getStudentName() {
        return studentName;
    }
    public String getStudentLastName() {
        return studentLastName;
    }
    public int getStudentGrade() {
        return studentGrade;
    }
    public int getStudentNumGrade() {
        return studentNumGrade;
    }
    public boolean getStudentCondition() {
        return studentCondition;
    }
    public Vaccine getFirstVaccine(){
        return firstVaccine;
    }
    public Vaccine getSecondVaccine(){
        return secondVaccine;
    }

    public void setStudentName(String studentName) {
        this.studentName=studentName;
    }
    public void setStudentLastName(String studentLastName) {
        this.studentLastName=studentLastName;
    }
    public void setStudentGrade(int studentGrade) {
        this.studentGrade=studentGrade;
    }
    public void setStudentNumGrade(int studentNumGrade) {
        this.studentNumGrade=studentNumGrade;
    }
    public void setStudentCondition(boolean studentCondition) {
        this.studentCondition=studentCondition;
    }
    public void setFirstVaccine(Vaccine firstVaccine){
        this.firstVaccine= firstVaccine;
    }
    public void setSecondVaccine(Vaccine secondVaccine){
        this.secondVaccine=secondVaccine;
    }

}
