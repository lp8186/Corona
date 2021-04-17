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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.corona.FBref.refUsers;

/**
 * Second.
 * @author Liad Peretz
 * @version	1.0
 * @since 16/4/2021
 * Short description- You can update the information about the second vaccine.
 */
public class Second extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView studentsList;
    EditText place2,date2;
    Button submit;
    ArrayAdapter adp;
    ArrayList<Users> students= new ArrayList<Users>();
    ArrayList<String> studentsNames= new ArrayList<String>();
    Users stuTmp;
    Vaccine vaccine2;
    int position2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        studentsList= (ListView) findViewById(R.id.studentsList);
        place2= (EditText) findViewById(R.id.place2);
        date2= (EditText) findViewById(R.id.date2);
        submit= (Button) findViewById(R.id.submit);

        place2.setVisibility(View.INVISIBLE);
        date2.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);

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
    /**
     * OnItemClick.
     * Short description- Get the position(key_id) of the chosen student.
     * <p>
     *      AdapterView<?> parent
     *      View view
     *      int position
     *      long id
     * @param parent- the chosen listView, view- the chosen item, position- the place of the chosen item, id- the chosen line.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (students.get(position).getStudentCondition()) {
            if ((students.get(position).getSecondVaccine().getDate().equals("null"))&&(students.get(position).getSecondVaccine().getPlace().equals("null"))) {
                    place2.setVisibility(View.VISIBLE);
                    date2.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.VISIBLE);
                    position2 = position;
                }
            else{
                    place2.setVisibility(View.INVISIBLE);
                    date2.setVisibility(View.INVISIBLE);
                    submit.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, "Already got vaccine", Toast.LENGTH_SHORT).show();
                }

        }
        else{
            place2.setVisibility(View.INVISIBLE);
            date2.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Can't get vaccine", Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * Submit2.
     * Short description- Update the new information about the student's second vaccine in the Fire Base data base.
     * <p>
     *      View view
     * @param view- the chosen item.
     */
    public void submit2(View view) {
        if(place2.getText().toString().equals(""))
            Toast.makeText(this, "Enter the place", Toast.LENGTH_SHORT).show();
        else if (date2.getText().toString().equals(""))
            Toast.makeText(this, "Enter the date", Toast.LENGTH_SHORT).show();
        else{
            stuTmp=students.get(position2);
            vaccine2= new Vaccine(place2.getText().toString(),date2.getText().toString());
            stuTmp.setSecondVaccine(vaccine2);
            refUsers.child(String.valueOf(position2)).setValue(stuTmp);
            place2.setVisibility(View.INVISIBLE);
            date2.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.INVISIBLE);
        }

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
        if(id==R.id.credits){
            Intent a = new Intent(this, Credits.class);
            startActivity(a);
        }
        return true;
    }
}