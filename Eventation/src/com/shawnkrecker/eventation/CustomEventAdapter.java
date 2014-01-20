package com.shawnkrecker.eventation;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CustomEventAdapter extends SimpleCursorAdapter {

	 private Context mContext;
     private Context appContext;
     private int layout;
     private Cursor cursor;
     private LayoutInflater inflater;
     
     String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct","Nov", "Dec"};
     
     public CustomEventAdapter(Context context, int layout, Cursor c, String[] from, int[] to){
    	 super(context, layout, c, from, to, 0);
    	 this.layout = layout; 
    	 this.mContext = context;
    	 this.inflater = LayoutInflater.from(context);
    	 this.cursor = c;
     }
     
     @Override
     public View newView (Context context, Cursor cursor, ViewGroup parent) {
             return inflater.inflate(layout, null);
     }
     
     @Override
     public void bindView(View view, Context context, Cursor cursor) {
         super.bindView(view, context, cursor);
         
         TextView titleView = (TextView) view.findViewById(R.id.title);
         TextView monthView = (TextView) view.findViewById(R.id.month);
         TextView dayView = (TextView) view.findViewById(R.id.day);
         TextView yearView = (TextView) view.findViewById(R.id.year);
         
         int title_index = cursor.getColumnIndexOrThrow("eventname");    
         int month_index = cursor.getColumnIndexOrThrow("eventmonth");
         int day_index = cursor.getColumnIndexOrThrow("eventday");
         int year_index = cursor.getColumnIndexOrThrow("eventyear");

         titleView.setText(cursor.getString(title_index));
         
         monthView.setText(months[Integer.parseInt(cursor.getString(month_index))]);     
         
         dayView.setText(cursor.getString(day_index));
         yearView.setText(cursor.getString(year_index));

     }
	
}
