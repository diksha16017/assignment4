package com.example.ajay.myevent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


/**
 * Created by AJAY on 04-Nov-16.
 * class created to add event in recycler view
 * this class is adapter to recycler view
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private List<Event> eventList;


    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, details;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);


        }



    }

//all methods of recycler view adapter  are overridden


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.title.setText(event.getTitle());



    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
