package com.shawnkrecker.eventation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.content.Context;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link GroupFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link GroupFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class GroupFragment extends Fragment {
	
	private static final String MY_GROUPS_TABLENAME = "mygroups";
	private static final String SUBSCRIBED_GROUPS_TABLENAME = "subscribed_groups";
	

	private Button createGroupButton;
	
	private Button addGroupButton;

	private OnFragmentInteractionListener mListener;
	
	private SimpleCursorAdapter myGroupAdapter;
	
	private SimpleCursorAdapter subscribedGroupsAdapter;
	
	private MyDBHelper myDBHelper;
	private SQLiteDatabase db;
	private Context context;
	
	private String[] fromColumns;
	private int [] toViews;
	
	

	public static GroupFragment newInstance() {
		GroupFragment fragment = new GroupFragment();
		return fragment;
	}

	public GroupFragment() {
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

			View view = inflater.inflate(R.layout.fragment_group, container, false);
						
			createGroupButton = (Button) view.findViewById(R.id.button1);	
			
			addGroupButton = (Button) view.findViewById(R.id.button2);

			createGroupButton.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					Log.d("Blah", "Blah");
					createGroup();
				}
			});
			
			addGroupButton.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					Intent intent = new Intent(getActivity(), FindGroupActivity.class);
					startActivity(intent);
				}
			});
			
			
			context = getActivity().getApplicationContext();
			myDBHelper = new MyDBHelper(context);
			db = myDBHelper.getReadableDatabase();
							
			ListView myGroupListView = (ListView) view.findViewById(R.id.listView1);
			ListView groupSubscribedListView = (ListView) view.findViewById(R.id.listView2);
			
			Cursor myGroupCursor = null;
			
			fromColumns = new String[]{"_id"};
			toViews = new int[]{R.id.textView1};
				
			
			try{
				myGroupCursor = db.rawQuery("SELECT * FROM " + MY_GROUPS_TABLENAME,null); 
				
				if(myGroupCursor != null){
				
					myGroupAdapter = new SimpleCursorAdapter(context, R.layout.group_adapter_view, myGroupCursor,fromColumns,toViews,0);
									
					myGroupListView.setAdapter(myGroupAdapter);
					
					myGroupListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
						
						@Override
						public void onItemClick(AdapterView parentView, View childView, int position, long id ){
	
							Cursor cursor = (Cursor) myGroupAdapter.getItem(position);					
							String groupName = cursor.getString(0);
							viewGroup(groupName);
							cursor.close();
							//myGroupAdapter.swapCursor(db.rawQuery("SELECT * FROM " + MY_GROUPS_TABLENAME,null));
									
						}
						
						public void onNothingSelected(AdapterView parentView){}
					});
					
				}
			
			}catch(SQLiteException ex){
				
			}finally{
				if(myGroupCursor != null) myGroupCursor.close();
			}
			

			Cursor subscribedGroupsCursor = null;

			try{
				subscribedGroupsCursor = db.rawQuery("SELECT * FROM " + SUBSCRIBED_GROUPS_TABLENAME,null);
			
				if(subscribedGroupsCursor != null){
					
					subscribedGroupsAdapter = new SimpleCursorAdapter(context, R.layout.group_adapter_view, subscribedGroupsCursor,fromColumns,toViews,0);
					
					groupSubscribedListView.setAdapter(subscribedGroupsAdapter);
					
					groupSubscribedListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
						
						@Override
						public void onItemClick(AdapterView parentView, View childView, int position, long id ){
							
							Cursor cursor = (Cursor) subscribedGroupsAdapter.getItem(position);				
							String groupName = cursor.getString(0);
							
							//change for subscribedgroupview
							viewGroup(groupName);
							cursor.close();
							//subscribedGroupsAdapter.swapCursor(db.rawQuery("SELECT * FROM " + SUBSCRIBED_GROUPS_TABLENAME,null));				
						}
						
						public void onNothingSelected(AdapterView parentView){}
					});
					
				}
			}catch(SQLiteException ex){
				
			}finally{
				if(subscribedGroupsCursor != null) subscribedGroupsCursor.close();
			}
			
		
			return view;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void createGroup() {
		mListener.onFragmentInteraction();
				
	}
	
	public void viewGroup(String listItem){
		Intent intent = new Intent(getActivity(),GroupViewActivity.class);
		Bundle bundle = new Bundle();
		
		bundle.putString("id",listItem);
		
		intent.putExtras(bundle);
		startActivity(intent);
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
	public void onResume(){
		super.onResume();
		myDBHelper = new MyDBHelper(context);
		db = myDBHelper.getReadableDatabase();
		myGroupAdapter.swapCursor(db.rawQuery("SELECT * FROM " + MY_GROUPS_TABLENAME,null));
		subscribedGroupsAdapter.swapCursor(db.rawQuery("SELECT * FROM " + SUBSCRIBED_GROUPS_TABLENAME,null));

		
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}
	
	@Override
	public void onPause(){
		super.onPause();
		db.close();
		myDBHelper.close();
	
	}
	
	
	

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction();
	}

}
