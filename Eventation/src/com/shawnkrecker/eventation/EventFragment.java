package com.shawnkrecker.eventation;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link EventFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class EventFragment extends Fragment {

	private OnFragmentInteractionListener mListener;
	private CustomEventAdapter adapter;
	private Context context;
	private MyDBHelper helper;
	private SQLiteDatabase db;
	private Cursor cursor;
	
	
	String[] fromColumns = {"eventname", "eventmonth", "eventday", "eventyear"};
	int [] toViews = {android.R.id.text1,R.id.month, R.id.day,R.id.year};


	public static EventFragment newInstance() {
		EventFragment fragment = new EventFragment();
		return fragment;
	}

	public EventFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		
		View view = inflater.inflate(R.layout.fragment_event, container, false);

		context = getActivity().getApplicationContext();
		helper = new MyDBHelper(context);
		db = helper.getReadableDatabase();
		
		ListView listView = (ListView) view.findViewById(R.id.listView1);
		
		cursor = null;
		
		try{
			cursor = db.rawQuery("SELECT * FROM myevents",null);
		
			if(cursor != null){

				
				adapter = new CustomEventAdapter(context, R.layout.event_list_view, cursor,fromColumns,toViews);
				listView.setAdapter(adapter);
				
				listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
					
					@Override
					public void onItemClick(AdapterView parentView, View childView, int position, long id ){

						Cursor cursor = (Cursor) adapter.getItem(position);
						
						String eventName = cursor.getString(0);
						viewEvent(eventName);
						
						cursor.close();
						
					}
					
					public void onNothingSelected(AdapterView parentView){}
				});
				
			}
		
		}catch(SQLiteException ex){
			
		}finally{
			//cursor.close();
		}
		
		
		return view;
	}
	
	public void viewEvent(String eventName){
		
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void onPause(){
		super.onPause();
		cursor.close();
		db.close();
		helper.close();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		helper = new MyDBHelper(context);
		db = helper.getReadableDatabase();
		adapter.swapCursor(db.rawQuery("SELECT * FROM myevents",null));
	}
	
	
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

}
