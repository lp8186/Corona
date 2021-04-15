package com.example.corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import static com.example.corona.FBref.refUsers;
public class NewStudent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name,lastName,place,date;
    Spinner grade,gradeNum,condition;
    String [] options1= {"grade","ז","ח","ט","י","יא","יב"};
    String [] options2= {"grade num","1","2","3","4","5","6","7","8","9"};
    String [] options3= { "can't get vaccinated", "get vaccinated once"};
    ArrayAdapter adp;
    int position=0;

    int grade1=0,gradeNum1=6;
    boolean condition1=false;
    Users student;
    Vaccine vaccine1,vaccine2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        refUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                position= (int) dS.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        name= (EditText) findViewById(R.id.name);
        lastName= (EditText) findViewById(R.id.lastName);
        place= (EditText) findViewById(R.id.place);
        date= (EditText) findViewById(R.id.date);
        grade= (Spinner) findViewById(R.id.grade);
        gradeNum= (Spinner) findViewById(R.id.gradeNum);
        condition= (Spinner) findViewById(R.id.condition);

        adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, options1);
        grade.setAdapter(adp);
        grade.setOnItemSelectedListener(this);
        adp= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, options2);
        gradeNum.setAdapter(adp);
        gradeNum.setOnItemSelectedListener(this);
        adp= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, options3);
        condition.setAdapter(adp);
        condition.setOnItemSelectedListener(this);

        place.setVisibility(View.INVISIBLE);
        date.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent==grade)
            grade1=position;
        if (parent==gradeNum)
            gradeNum1=position+6;
        if (parent==condition){
            if(position==0){
                condition1=false;
                place.setVisibility(View.INVISIBLE);
                date.setVisibility(View.INVISIBLE);
            }
            else{
                condition1=true;
                place.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
            }
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void submit(View view) {
        if (name.getText().toString().equals(""))
            Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show();
        else if (lastName.getText().toString().equals(""))
            Toast.makeText(this, "Enter the last name", Toast.LENGTH_SHORT).show();
        else if (grade1==0)
            Toast.makeText(this, "Chose the grade", Toast.LENGTH_SHORT).show();
        else if (gradeNum1==6)
            Toast.makeText(this, "Chose the grade num", Toast.LENGTH_SHORT).show();
        else {
            if (condition1) {
                if (place.getText().toString().equals(""))
                    Toast.makeText(this, "Enter the place", Toast.LENGTH_SHORT).show();
                else if (date.getText().toString().equals(""))
                    Toast.makeText(this, "Enter the date", Toast.LENGTH_SHORT).show();
                else {
                    refUsers.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dS) {
                            position= (int) dS.getChildrenCount();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });

                    vaccine2= new Vaccine("null","null");
                    vaccine1= new Vaccine(place.getText().toString(),date.getText().toString());
                    student = new Users(name.getText().toString(), lastName.getText().toString(), grade1, gradeNum1,true,vaccine1, vaccine2);
                    refUsers.child(String.valueOf(position)).setValue(student);
                    name.setText("");
                    lastName.setText("");
                    place.setText("");
                    date.setText("");
                }
            }
            else{
                refUsers.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dS) {
                        position= (int) dS.getChildrenCount();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                vaccine2= new Vaccine("null","null");
                vaccine1= new Vaccine("null","null");
                student = new Users(name.getText().toString(), lastName.getText().toString(), grade1, gradeNum1,true,vaccine1, vaccine2);
                refUsers.child(String.valueOf(position)).setValue(student);
                name.setText("");
                lastName.setText("");
            }
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
        if (id==R.id.sorting){
            Intent a = new Intent(this, Sorting.class);
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