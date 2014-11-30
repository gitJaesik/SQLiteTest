package com.example.fork;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	EditText searchText;
	DBHelper myDB;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myDB = new DBHelper(this);
		myDB.getWritableDatabase();

		cursor = myDB.getMainList();

		startManagingCursor(cursor);

		SimpleCursorAdapter Adapter = null;
		Adapter = new SimpleCursorAdapter(MainActivity.this,
				android.R.layout.simple_list_item_2, cursor, new String[] {
						"name", "menu" }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		ListView list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(Adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				//Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
				cursor.moveToFirst();
				for(int i = 0; i < position; i++){
					cursor.moveToNext();
				}
				
				Intent intent = new Intent(getApplicationContext(),
						ViewActivity.class);
				intent.putExtra("_id", cursor.getString(cursor.getColumnIndex("_id")));
				intent.putExtra("name", cursor.getString(cursor.getColumnIndex("name")));
				intent.putExtra("menu", cursor.getString(cursor.getColumnIndex("menu")));
				intent.putExtra("desc", cursor.getString(cursor.getColumnIndex("desc")));
				startActivity(intent);
			}
		});
		

		Button addButton = (Button) findViewById(R.id.add);
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						InsertActivity.class);
				startActivity(intent);
			}
		});

		Button searchButton = (Button) findViewById(R.id.search);
		searchText = (EditText) findViewById(R.id.searchText);

		searchButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO 검색어를 가지고 검색을 실행

				if (!searchText.getText().toString().equals("")) {
					// Execute when there are some string

					Intent intent = new Intent(getApplicationContext(),
							SearchActivity.class);
					intent.putExtra("SearchString", searchText.getText()
							.toString());
					startActivity(intent);
				}
			}
		});

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
}
