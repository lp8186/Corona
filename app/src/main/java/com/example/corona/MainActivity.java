package com.example.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void Students(View view) {
        Intent a = new Intent(this, Students.class);
        startActivity(a);
    }

    public void Sorting(View view) {
        Intent a = new Intent(this, Sorting.class);
        startActivity(a);
    }

    public void NewStudent(View view) {
        Intent a = new Intent(this, NewStudent.class);
        startActivity(a);
    }

    public void Second(View view) {
        Intent a = new Intent(this, Second.class);
        startActivity(a);
    }

    public void Credits(View view) {
        Intent a = new Intent(this, Credits.class);
        startActivity(a);
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