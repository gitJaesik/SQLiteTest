package com.example.fork;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends ActionBarActivity {

	private DBHelper mydb;
	EditText name;
	EditText menu;
	EditText desc;
	Button btnsav;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert);

		mydb = new DBHelper(this);

		// 그림과 연결

		name = (EditText) findViewById(R.id.name);
		menu = (EditText) findViewById(R.id.menu);
		desc = (EditText) findViewById(R.id.desc);
		Button btnsave = (Button) findViewById(R.id.btnsave);

		btnsave.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mydb.insertRestaurant(name.getText().toString(), menu.getText()
						.toString(), desc.getText().toString());
				Toast.makeText(getApplicationContext(), "Add Completed!",
						Toast.LENGTH_LONG).show();

				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insert, menu);
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
