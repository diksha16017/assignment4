package com.example.ajay.myevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
;


public class MainActivity extends AppCompatActivity {


    public static List<Event> eventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //function to get all events stored in database in event list to show in recycler view
        displayAllEvents();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //list is sent to adapter
        mAdapter = new EventAdapter(eventList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //touch listener is added to each item of recycler view
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Event event = eventList.get(position);
                Intent displayscreen = new Intent(getApplicationContext(), DisplayMyViewPagerActivity.class);
                displayscreen.putExtra("todotitle", event.getTitle());
                displayscreen.putExtra("tododetails", event.getDetails());
                displayscreen.putExtra("position", position);
                startActivity(displayscreen);



            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), " Long click is selected!", Toast.LENGTH_SHORT).show();
            }
        }));
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    public void displayAllEvents()
    {
        MyDatabaseHandler dh = new MyDatabaseHandler(this);
        eventList = dh.getAllEvents();

    }

    //activity to add events
    public void addMyEvent(View v) {
        Intent add_screen = new Intent(this, AddMyEventActivity.class);
        startActivity(add_screen);
    }


}
