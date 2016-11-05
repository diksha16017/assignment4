package com.example.ajay.myevent;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by AJAY on 04-Nov-16.
 */
public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    LayoutInflater mLayoutInflater;


    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    //function created to instantiate an object to display various events using view pager
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.activity_display_event, container, false);

        TextView title_text_view = (TextView) itemView.findViewById(R.id.title);
        TextView details_text_view = (TextView) itemView.findViewById(R.id.details);
        String title = MainActivity.eventList.get(position).getTitle();
        String details = MainActivity.eventList.get(position).getDetails();
        title_text_view.setText(title);
        details_text_view.setText(details);

        container.addView(itemView);

        return itemView;

    }

    //function to destroy item view
    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    //function to get count of event list created at main activity
    @Override
    public int getCount() {
        return MainActivity.eventList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //function to get title of event from event list
    @Override
    public CharSequence getPageTitle(int position) {

        return MainActivity.eventList.get(position).getTitle();
    }
}

