package edu.rit.axv3012.spacekid;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseConnector extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "ScoreDB";

	public DatabaseConnector(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_SCORE_TABLE = "CREATE TABLE scores ( "
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, "
				+ "score INTEGER )";

		db.execSQL(CREATE_SCORE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS scores");

		this.onCreate(db);
	}

	private static final String TABLE_SCORES = "scores";
	private static final String KEY_NAME = "name";
	private static final String KEY_SCORE = "score";

	public void addScore(Score score) {
		Log.d("addScore", score.toString());

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, score.getName());
		values.put(KEY_SCORE, score.getScore());

		db.insert(TABLE_SCORES, null, values);

		db.close();
	}

	public String getAllScores() {
		List<Score> scores = new LinkedList<Score>();

		String query = "SELECT * FROM " + TABLE_SCORES + " ORDER BY score DESC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		Score score = null;
		if (cursor.moveToFirst()) {
			do {
				score = new Score();
				score.setId(Integer.parseInt(cursor.getString(0)));
				score.setName(cursor.getString(1));
				score.setScore(Integer.parseInt(cursor.getString(2)));

				scores.add(score);
			} while (cursor.moveToNext());
		}

		return scores.toString().replace(",", "").replace("[", "")
				.replace("]", "").trim();
	}

	public void deleteAllScores() {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_SCORES, null, null);
		db.close();
	}
}