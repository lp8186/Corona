package com.example.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Main Activity.
 * @author Liad Peretz
 * @version	1.0
 * @since 16/4/2021
 * Short description- The first screen you see when you enter to the app, from this screen you can go to any activity you need.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /**
     * Students.
     * Short description- Starts the Students activity.
     * <p>
     *      View view
     * @param view- the chosen item.
     */
    public void Students(View view) {
        Intent a = new Intent(this, Students.class);
        startActivity(a);
    }
    /**
     * Sorting.
     * Short description- Starts the Sorting activity.
     * <p>
     *      View view
     * @param view- the chosen item.
     */
    public void Sorting(View view) {
        Intent a = new Intent(this, Sorting.class);
        startActivity(a);
    }
    /**
     * New Student.
     * Short description- Starts the NewStudent activity.
     * <p>
     *      View view
     * @param view- the chosen item.
     */
    public void NewStudent(View view) {
        Intent a = new Intent(this, NewStudent.class);
        startActivity(a);
    }
    /**
     * Second.
     * Short description- Starts the Second activity.
     * <p>
     *      View view
     * @param view- the chosen item.
     */
    public void Second(View view) {
        Intent a = new Intent(this, Second.class);
        startActivity(a);
    }
    /**
     * Credits.
     * Short description- Starts the Credits activity.
     * <p>
     *      View view
     * @param view- the chosen item.
     */
    public void Credits(View view) {
        Intent a = new Intent(this, Credits.class);
        startActivity(a);
    }

    /**
     * OnCreateOptionsMenu.
     * Short descriptions- "Calls" the options menu.
     * <p>
     *    Menu menu
     * @param menu the menu
     * @return true if it worked.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /**
     * OnOptionsItemSelected.
     * Short description- Moves from this activity to other activity according to the selected item.
     * <p>
     *     MenuItem item
     * @param item the selected item
     * @return true if it worked.
     */
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