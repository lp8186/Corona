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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.corona.FBref.refUsers;

public class Second extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView studentsList;
    EditText place2,date2;
    Button submit;
    ArrayAdapter adp;
    ArrayList<Users> students= new ArrayList<Users>();
    ArrayList<String> studentsNames= new ArrayList<String>();
    Users stuTmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        studentsList= (ListView) findViewById(R.id.studentsList);
        place2= (EditText) findViewById(R.id.place2);
        date2= (EditText) findViewById(R.id.date2);
        submit= (Button) findViewById(R.id.submit);
        refUsers.addListenerForSingleValueEvent(new ValueEventListener(){
        @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                for (DataSnapshot data : dS.getChildren()) {
                    stuTmp= data.getValue(Users.class);
                    students.add(stuTmp);
                    studentsNames.add(stuTmp.getStudentName());
                }
            adp = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, studentsNames);
            studentsList.setAdapter(adp);

        }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        studentsList.setOnItemClickListener(this);


    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (students.get(position).getStudentCondition()) {
            place2.setVisibility(View.VISIBLE);
            date2.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
        }
        else{
            place2.setVisibility(View.INVISIBLE);
            date2.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Can't get vaccine", Toast.LENGTH_SHORT).show();
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
        if(id==R.id.newStudent){
            Intent a = new Intent(this, NewStudent.class);
            startActivity(a);
        }
        if(id==R.id.credits){
            Intent a = new Intent(this, Credits.class);
            startActivity(a);
        }
        return true;
    }
}