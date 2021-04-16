package com.example.corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.corona.FBref.refUsers;

public class Sorting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner option01,option02,option03;
    TextView information;
    Button submit3;
    String [] options001={"chose sorting","According grade and grade num","According grade","Can get vaccine","Can't get vaccine"};
    String [] options002={"chose the grade","ז","ח","ט","י","יא","יב"};
    String [] options003={"chose the grade num","1","2","3","4","5","6","7","8","9"};
    ArrayAdapter adp;
    Query query;
    Users stuTmp3;
    int chosen=0,chosenGrade=6,chosenGradeNum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);

        option01= (Spinner) findViewById(R.id.option01);
        option02= (Spinner) findViewById(R.id.option02);
        option03= (Spinner) findViewById(R.id.option03);
        information= (TextView) findViewById(R.id.information);
        submit3= (Button) findViewById(R.id.submit3);
        option02.setVisibility(View.INVISIBLE);
        option03.setVisibility(View.INVISIBLE);
        submit3.setVisibility(View.INVISIBLE);
        adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,options001);
        option01.setAdapter(adp);
        adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,options002);
        option02.setAdapter(adp);
        adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,options003);
        option03.setAdapter(adp);
        option01.setOnItemSelectedListener(this);
        option02.setOnItemSelectedListener(this);
        option03.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent==option01){
            chosen=position;
            if (position==0){
                option02.setVisibility(View.INVISIBLE);
                option03.setVisibility(View.INVISIBLE);
                submit3.setVisibility(View.INVISIBLE);
            }
            else if (position==1){
                option02.setVisibility(View.VISIBLE);
                option03.setVisibility(View.VISIBLE);
                submit3.setVisibility(View.VISIBLE);
            }
            else if (position==2){
                option02.setVisibility(View.VISIBLE);
                option03.setVisibility(View.INVISIBLE);
                submit3.setVisibility(View.VISIBLE);
            }
            else {
                option02.setVisibility(View.INVISIBLE);
                option03.setVisibility(View.INVISIBLE);
                submit3.setVisibility(View.VISIBLE);
            }
        }
        if (parent==option02)
            chosenGrade = position + 6;
        if (parent==option03)
            chosenGradeNum=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void submit4(View view) {
        if (chosen==1){
            information.setText("");
            if ((chosenGrade==6)||(chosenGradeNum==0))
                Toast.makeText(this, "chose the grade and the grade num", Toast.LENGTH_SHORT).show();
            else{
                query=refUsers.orderByChild("studentName");
                query.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dS) {
                        for (DataSnapshot data : dS.getChildren()) {
                            stuTmp3= data.getValue(Users.class);
                            if ((stuTmp3.getStudentGrade()==chosenGrade)&&(stuTmp3.getStudentNumGrade()==chosenGradeNum))
                                information.setText(information.getText().toString()+"\n"+stuTmp3.getStudentName()+" "+stuTmp3.getStudentLastName());
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

            }
        }
        else if (chosen==2){
            information.setText("");
            if (chosenGrade==6)
                Toast.makeText(this, "chose the grade", Toast.LENGTH_SHORT).show();
            else{
                query=refUsers.orderByChild("studentGradeNum");
                query.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dS) {
                        for (DataSnapshot data : dS.getChildren()) {
                            stuTmp3= data.getValue(Users.class);
                            if (stuTmp3.getStudentGrade()==chosenGrade)
                                information.setText(information.getText().toString()+"\n"+stuTmp3.getStudentName()+" "+stuTmp3.getStudentLastName());
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });


            }

        }
        else if (chosen==3){
            information.setText("");
            query=refUsers.orderByChild("studentGrade");
            query.addListenerForSingleValueEvent(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot dS) {
                    for (DataSnapshot data : dS.getChildren()) {
                        stuTmp3= data.getValue(Users.class);
                        if (stuTmp3.getStudentCondition())
                            information.setText(information.getText().toString()+"\n"+stuTmp3.getStudentName()+" "+stuTmp3.getStudentLastName());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        else if(chosen==4){
            information.setText("");
            query=refUsers.orderByChild("studentCondition").equalTo(false);
            query.addListenerForSingleValueEvent(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot dS) {
                    for (DataSnapshot data : dS.getChildren()) {
                        stuTmp3= data.getValue(Users.class);
                        information.setText(information.getText().toString()+"\n"+stuTmp3.getStudentName()+" "+stuTmp3.getStudentLastName());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if (id==R.id.students){
            Intent a = new Intent(this, Students.class);
            startActivity(a);
        }
        if(id==R.id.newStudent){
            Intent a = new Intent(this, NewStudent.class);
            startActivity(a);
        }
        if (id==R.id.second){
            Intent a = new Intent(this, Second.class);
            startActivity(a);
        }
        if(id==R.id.credits){
            Intent a = new Intent(this, Credits.class);
            startActivity(a);
        }
        return true;
    }
}