package com.example.fork;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class SearchActivity extends ActionBarActivity {

	private DBHelper myDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		Intent intent = getIntent();
		String searchVal = intent.getStringExtra("SearchString");

		myDB = new DBHelper(this);

		Cursor rs = myDB.getSearchData(searchVal);
		startManagingCursor(rs);

		SimpleCursorAdapter Adapter = null;
		Adapter = new SimpleCursorAdapter(SearchActivity.this,
				android.R.layout.simple_list_item_2, rs, new String[] { "name",
						"menu" }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		ListView list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(Adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
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
}
