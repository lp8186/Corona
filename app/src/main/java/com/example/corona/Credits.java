package com.example.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
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
        return true;
    }
}