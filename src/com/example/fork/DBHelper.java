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
		super(context, "ForkDB.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE ForkTable"
				+ "(id INTEGER PRIMARY KEY, name TEXT, menu TEXT, desc TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS ForkTable");
		onCreate(db);
	}
	
	public boolean insertRestaurant(String name, String menu, String desc){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		//contentValues.put("id", "null");
		contentValues.put("name", name);
		contentValues.put("menu", menu);
		contentValues.put("desc", desc);
		db.insert("ForkTable", null, contentValues);

		
		return true;
	}
	public Cursor getMainList(){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor;
		cursor = db.rawQuery("SELECT id AS _id, name, menu, desc FROM ForkTable",null);
		//startManagingCursor(cursor);
		return cursor;
	}
	public Cursor getSearchData(String SearchString){
	      SQLiteDatabase db = this.getReadableDatabase();
	      // get data easily
	      //Cursor res =  db.rawQuery( "select * from ForkTable where id="+id+"", null );
	      Cursor res =  db.rawQuery( "SELECT id AS _id, name, menu, desc FROM ForkTable WHERE name like '%" + SearchString + "%'", null );
	      return res;
	   }
	
	public Cursor getData(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      // get data easily
	      //Cursor res =  db.rawQuery( "select * from ForkTable where id="+id+"", null );
	      Cursor res =  db.rawQuery( "SELECT * FROM ForkTable", null );
	      return res;
	   }
}
