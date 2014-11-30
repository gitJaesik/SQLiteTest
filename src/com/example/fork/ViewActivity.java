package com.example.fork;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewActivity extends ActionBarActivity {

	DBHelper mydb;
	Cursor cursor;
	EditText nameView;
	EditText menuView;
	EditText descView;
	String idVal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		Intent intent = getIntent();
		String nameVal = intent.getStringExtra("name");
		String menuVal = intent.getStringExtra("menu");
		String descVal = intent.getStringExtra("desc");
		idVal = intent.getStringExtra("_id");

		mydb = new DBHelper(this);

		// 그림과 연결

		nameView = (EditText) findViewById(R.id.name);
		menuView = (EditText) findViewById(R.id.menu);
		descView = (EditText) findViewById(R.id.desc);

		nameView.setText(nameVal);
		menuView.setText(menuVal);
		descView.setText(descVal);

		Button btnupdate = (Button) findViewById(R.id.btnupdate);
		btnupdate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				String nameValUpdated = nameView.getText().toString();
				String menuValUpdated = menuView.getText().toString();
				String descValUpdated = descView.getText().toString();
				mydb.updateData(idVal, nameValUpdated, menuValUpdated,
						descValUpdated);
				Toast.makeText(getApplicationContext(), "Update Completed!",
						Toast.LENGTH_LONG).show();
				finish();
			}

		});

		Button btndelete = (Button) findViewById(R.id.btndelete);
		btndelete.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				mydb.deleteData(idVal);
				Toast.makeText(getApplicationContext(), "Delete Completed!",
						Toast.LENGTH_LONG).show();
				finish();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view, menu);
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
