package com.example.simplechat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity implements LoaderCallbacks<Cursor>{
	private MatrixCursor mCursor;
	//private String mUnrealDate;
	private RecyclerView mRecyclerView;
	private MessagesRecyclerViewAdapter mAdapter;
	private LinearLayoutManager mLayoutManager;
	private static final int URL_LOADER = 0;
	public static long time24;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
		mRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);
		getLoaderManager().initLoader(URL_LOADER, null, this);
		requestCursor();
		mAdapter = new MessagesRecyclerViewAdapter(getMessagesList());
		mRecyclerView.setAdapter(mAdapter);
		
		
	}
	private ArrayList<String[]> getMessagesList(){
		ArrayList<String[]> fixed  = new ArrayList<String[]>();
		fixed.add(new String[]{"Shimrit",setAnotherDate(System.currentTimeMillis()),"hi","Brad",setAnotherDate(System.currentTimeMillis()),"what's up"});
		fixed.add(new String[]{"Shimrit",setAnotherDate(System.currentTimeMillis()+200000),"tired","Brad",setAnotherDate(System.currentTimeMillis()+200000),"really?"});
		fixed.add(new String[]{"Shimrit",setAnotherDate(System.currentTimeMillis()+400000),"yes to much wirk","Brad",setAnotherDate(System.currentTimeMillis()+400000),"take abreak"});
		fixed.add(new String[]{"shimrit",setAnotherDate(System.currentTimeMillis()+300000000),"never!!","Brad",setAnotherDate(System.currentTimeMillis()+300000000),":)"});
		return fixed;
	}
	private void requestCursor(){
		  mCursor=new MatrixCursor(new String[]{"Name","Time","Message"});
		  mCursor.addRow(new String[]{"Shimrit",setAnotherDate(234230),"Item 1"});
		  mCursor.addRow(new String[]{"Brad",setAnotherDate(234230),"Item 2"});
		  mCursor.addRow(new String[]{"Shimrit",setAnotherDate(234500),"Item 3"});
		  mCursor.addRow(new String[]{"Brad",setAnotherDate(234500),"Item 4"});
		  mCursor.addRow(new String[]{"Shimrit",setAnotherDate(234500),"Item 5"});
		  mCursor.addRow(new String[]{"Brad",setAnotherDate(234500),"Item 6"});
		  mCursor.addRow(new String[]{"Shimrit",setAnotherDate(234500),"Item 7"});
		  mCursor.addRow(new String[]{"Brad",setAnotherDate(234500),"Item 8"});
		}
	
	private String setAnotherDate(long time){
		return new SimpleDateFormat("dd-MM-yyyy").format(time);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		Uri CONTENT_URI = ContactsContract.RawContacts.CONTENT_URI;
		  return new CursorLoader(this, CONTENT_URI, null, null, null, null);
	}
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		
		Log.d("ds", "sd");
	}
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		Log.d("ds", "sd");
		
	}
}
