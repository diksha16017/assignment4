package com.example.ajay.myevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by AJAY on 04-Nov-16.
 */
public class AddMyEventActivity extends AppCompatActivity {

    //editText for title and details
    private EditText title_edit_text;
    private EditText details_edit_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getting reference of edidtText boxes
        setContentView(R.layout.activity_add_event);
        title_edit_text = (EditText)findViewById(R.id.title);
        details_edit_text = (EditText)findViewById(R.id.details);

    }

    //fuction to return to main activity after pressing and to add event in the database on going back
    public void goToMyHome(View view)
    {
        String title = title_edit_text.getText().toString();
        String details = details_edit_text.getText().toString();

        //checking for the emptiness of the fields
        if(title.equalsIgnoreCase("") || details.equalsIgnoreCase(""))
        {
            Toast.makeText(this,"Fields cannot be empty ...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Event event = new Event(title,details);
            MyDatabaseHandler dh = new MyDatabaseHandler(this);
            dh.addEvent(event);
            Toast.makeText(this,"Event Successfully added.",Toast.LENGTH_SHORT).show();

        }

        Intent main_screen = new Intent(this,MainActivity.class);
        startActivity(main_screen);

    }

    //function for adding event in database on pressing of add button
    public void addEvent(View view)
    {
        String title = title_edit_text.getText().toString();
        String details = details_edit_text.getText().toString();

        if(title.equalsIgnoreCase("") || details.equalsIgnoreCase(""))
        {
            Toast.makeText(this,"Fields cannot be empty ...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Event event = new Event(title,details);
            MyDatabaseHandler dh = new MyDatabaseHandler(this);
            dh.addEvent(event);
            Toast.makeText(this,"Event Successfully added.",Toast.LENGTH_SHORT).show();

        }

        Intent main_screen = new Intent(this,MainActivity.class);
        startActivity(main_screen);
    }
}
