package com.example.ajay.myevent;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AJAY on 04-Nov-16.
 */
public class DisplayMyViewPagerActivity extends AppCompatActivity{


    // public List<ToDo> todoList = new ArrayList<>();
    int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_view_pager);
        // function created to get stored data
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                position = 0;
            } else {

                position = extras.getInt("position");

            }
        } else {
            position =  Integer.parseInt((String)savedInstanceState.getSerializable("position"));

        }

        //setting adapter to view pager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        viewPager.setCurrentItem(position);


    }

    // function created to go to main page on toolbar button
    public void goToMyHome(View v)
    {
        Intent home_screen = new Intent(this,MainActivity.class);
        startActivity(home_screen);
    }

    //function created to delete event from the database
    public void deleteMyEvent(View view)
    {
        String taskTitle = ((TextView) findViewById(R.id.title)).getText().toString();
        MyDatabaseHandler dh = new MyDatabaseHandler(this);
        int res = dh.deleteEvent(taskTitle);
        if(res>0) {
            Toast.makeText(this, "Event has been deleted successfully!!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "No such event is present!!!", Toast.LENGTH_SHORT).show();
        }
        ((TextView) findViewById(R.id.title)).setText("");
        ((TextView) findViewById(R.id.details)).setText("");

        startActivity(new Intent(this, MainActivity.class));

    }

}
