package com.shawnkrecker.eventation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link ProfileFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class ProfileFragment extends Fragment {

	public static final String PREF_NAME = "prefs";

	private String userName;
	private String firstName;
	private String lastName;
	private String email;

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment ProfileFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ProfileFragment newInstance() {
		ProfileFragment fragment = new ProfileFragment();
		Bundle args = new Bundle();
	
		fragment.setArguments(args);
		return fragment;
	}

	public ProfileFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
			View view = inflater.inflate(R.layout.fragment_profile, container, false);
		
			//SharedPreferences prefs = getActivity().getSharedPreferences("PREF_NAME",0);
			
			
			try{
			
				InputStream inputStream = getActivity().openFileInput("user_info");
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				
				String result = bufferedReader.readLine();
				
				if(result != null){
				
					String[] info = result.split(" ");
								
					TextView userView = (TextView) view.findViewById(R.id.textView1);
					userView.setText(info[0]);
						
					TextView firstNameView = (TextView) view.findViewById(R.id.textView2);
					firstNameView.setText(info[1]);
						
					TextView lastNameView = (TextView) view.findViewById(R.id.textView3);
					lastNameView.setText(info[2]);
						
					TextView emailView = (TextView) view.findViewById(R.id.textView5);
					emailView.setText(info[3]);
				}
				
				bufferedReader.close();
				
			} catch(FileNotFoundException ex1){
				Log.d("eventation", "ProfileFragment: FileNotFoundException");
				
			}catch(IOException ex2){
				Log.d("eventation", "ProfileFragment: IOException");
			}
	
		
		
		return view;
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
		public void onFragmentInteraction(Uri uri);
	}

}
