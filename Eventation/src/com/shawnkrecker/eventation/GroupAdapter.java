package com.shawnkrecker.eventation;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class GroupAdapter extends SimpleCursorAdapter {

	 private Context mContext;
     private Context appContext;
     private int layout;
     private Cursor cursor;
     private LayoutInflater inflater;
     
     
     public GroupAdapter(Context context, int layout, Cursor c, String[] from, int[] to){
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
         
         TextView titleView = (TextView) view.findViewById(R.id.textView1);
          
         int title_index = cursor.getColumnIndexOrThrow("_id");    

         titleView.setText(cursor.getString(title_index));
  
     

     }
	
}
