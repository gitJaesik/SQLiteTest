/**
 * 
 */
package com.example.fork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "ForkDB.db", null, 4);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE ForkTable"
				+ "(_id INTEGER PRIMARY KEY, name TEXT, menu TEXT, desc TEXT);");
		db.execSQL("INSERT INTO ForkTable ('name', 'menu', 'desc') "
				+ "VALUES ('BOYS RECIPE', '2.0 PIZZA', 'UNIQUE RESTAURANT');");
		db.execSQL("INSERT INTO ForkTable ('name', 'menu', 'desc') "
				+ "VALUES ('SPAZIO BUONO', 'STEAK SALAD', 'LIKE SEOGA AND COOK');");
		db.execSQL("INSERT INTO ForkTable ('name', 'menu', 'desc') "
				+ "VALUES ('Lga', 'Shaved ice', 'very popular and famous');");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS ForkTable");
		onCreate(db);
	}

	public boolean insertRestaurant(String name, String menu, String desc) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		// contentValues.put("id", "null");
		contentValues.put("name", name);
		contentValues.put("menu", menu);
		contentValues.put("desc", desc);
		db.insert("ForkTable", null, contentValues);

		return true;
	}

	public Cursor getMainList() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor;
		cursor = db.rawQuery("SELECT _id, name, menu, desc FROM ForkTable",
				null);
		// startManagingCursor(cursor);
		return cursor;
	}

	public Cursor getSearchData(String SearchString) {
		SQLiteDatabase db = this.getReadableDatabase();
		// get data easily
		// Cursor res = db.rawQuery( "select * from ForkTable where id="+id+"",
		// null );
		Cursor res = db.rawQuery(
				"SELECT _id, name, menu, desc FROM ForkTable WHERE name like '%"
						+ SearchString + "%'", null);
		return res;
	}

	public boolean updateData(String _id, String name, String menu, String desc) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		contentValues.put("menu", menu);
		contentValues.put("desc", desc);
		db.update("ForkTable", contentValues, "_id = ?", new String[] { _id });
		return true;
	}

	public Integer deleteData(String _id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("ForkTable", "_id = ?", new String[] { _id });
	}

	public Cursor getData(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		// get data easily
		// Cursor res = db.rawQuery( "select * from ForkTable where id="+id+"",
		// null );
		Cursor res = db.rawQuery("SELECT * FROM ForkTable", null);
		return res;
	}
}
