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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.corona.FBref.refUsers;

public class Students extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView studentsList2;
    TextView name2,lastName2,grade2,gradeNum2,condition2;
    EditText place01,date01,place02,date02;
    Button submit2;
    ArrayAdapter adp;
    ArrayList<Users> students2= new ArrayList<Users>();
    ArrayList<String> studentsNames2= new ArrayList<String>();
    Users stuTmp2;
    Vaccine vaccine02;
    int position02=0;
    boolean help=false;
    String[]grade02={"ז","ח","ט","י","יא","יב"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        studentsList2= (ListView) findViewById(R.id.studentsList2);
        name2=(TextView) findViewById(R.id.name2);
        lastName2=(TextView) findViewById(R.id.lastName2);
        grade2=(TextView) findViewById(R.id.grade2);
        gradeNum2=(TextView) findViewById(R.id.gradeNum2);
        condition2=(TextView) findViewById(R.id.condition2);
        place02= (EditText) findViewById(R.id.place02);
        date01= (EditText) findViewById(R.id.date01);
        place01= (EditText) findViewById(R.id.place01);
        date02= (EditText) findViewById(R.id.date02);
        submit2= (Button) findViewById(R.id.submit2);

        place01.setVisibility(View.INVISIBLE);
        date01.setVisibility(View.INVISIBLE);
        place02.setVisibility(View.INVISIBLE);
        date02.setVisibility(View.INVISIBLE);
        submit2.setVisibility(View.INVISIBLE);

        refUsers.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                for (DataSnapshot data : dS.getChildren()) {
                    stuTmp2= data.getValue(Users.class);
                    students2.add(stuTmp2);
                    studentsNames2.add(stuTmp2.getStudentName());
                }
                adp = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, studentsNames2);
                studentsList2.setAdapter(adp);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        studentsList2.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        name2.setText(students2.get(position).getStudentName());
        lastName2.setText(students2.get(position).getStudentLastName());
        grade2.setText(grade02[students2.get(position).getStudentGrade()-7]);
        gradeNum2.setText(String.valueOf(students2.get(position).getStudentNumGrade()));
        if (students2.get(position).getStudentCondition()){
            position02=position;
            if ((students2.get(position).getSecondVaccine().getPlace().equals("null"))&&(students2.get(position).getSecondVaccine().getDate().equals("null"))){
                help=false;
                place01.setVisibility(View.VISIBLE);
                date01.setVisibility(View.VISIBLE);
                place02.setVisibility(View.INVISIBLE);
                date02.setVisibility(View.INVISIBLE);
                submit2.setVisibility(View.VISIBLE);
                condition2.setText("got vaccine once");
                place01.setText(students2.get(position).getFirstVaccine().getPlace());
                date01.setText(students2.get(position).getFirstVaccine().getDate());
            }
            else{
                help=true;
                place01.setVisibility(View.VISIBLE);
                date01.setVisibility(View.VISIBLE);
                place02.setVisibility(View.VISIBLE);
                date02.setVisibility(View.VISIBLE);
                submit2.setVisibility(View.VISIBLE);
                condition2.setText("got vaccine twice");
                place01.setText(students2.get(position).getFirstVaccine().getPlace());
                date01.setText(students2.get(position).getFirstVaccine().getDate());
                place02.setText(students2.get(position).getSecondVaccine().getPlace());
                date02.setText(students2.get(position).getSecondVaccine().getDate());
            }
        }
        else{
            place01.setVisibility(View.INVISIBLE);
            date01.setVisibility(View.INVISIBLE);
            place02.setVisibility(View.INVISIBLE);
            date02.setVisibility(View.INVISIBLE);
            submit2.setVisibility(View.INVISIBLE);
            condition2.setText("can't get vaccine");
        }
    }
    public void submit3(View view) {
        if(!(help)){
            if ((place01.getText().toString().equals(students2.get(position02).getFirstVaccine().getPlace()))&&(date01.getText().toString().equals(students2.get(position02).getFirstVaccine().getDate())))
                Toast.makeText(this, "You didn't change anything", Toast.LENGTH_SHORT).show();
            else if ((place01.getText().toString().equals(""))||(date01.getText().toString().equals("")))
                Toast.makeText(this, "You missed something...", Toast.LENGTH_SHORT).show();
            else{
                stuTmp2=students2.get(position02);
                vaccine02= new Vaccine(place01.getText().toString(),date01.getText().toString());
                stuTmp2.setFirstVaccine(vaccine02);
                refUsers.child(String.valueOf(position02)).setValue(stuTmp2);
            }

        }
        else{
            if ((place01.getText().toString().equals(students2.get(position02).getFirstVaccine().getPlace()))&&(date01.getText().toString().equals(students2.get(position02).getFirstVaccine().getDate()))
            &&(place02.getText().toString().equals(students2.get(position02).getSecondVaccine().getPlace()))&&(date02.getText().toString().equals(students2.get(position02).getSecondVaccine().getDate())))
                Toast.makeText(this, "You didn't change anything", Toast.LENGTH_SHORT).show();
            else if ((place01.getText().toString().equals(""))||(date01.getText().toString().equals(""))||(place02.getText().toString().equals(""))||(date02.getText().toString().equals("")))
                Toast.makeText(this, "You missed something...", Toast.LENGTH_SHORT).show();
            else{
                stuTmp2=students2.get(position02);
                vaccine02= new Vaccine(place01.getText().toString(),date01.getText().toString());
                stuTmp2.setFirstVaccine(vaccine02);
                vaccine02= new Vaccine(place02.getText().toString(),date02.getText().toString());
                stuTmp2.setSecondVaccine(vaccine02);
                refUsers.child(String.valueOf(position02)).setValue(stuTmp2);
            }

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if (id==R.id.sorting){
            Intent a = new Intent(this, Sorting.class);
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