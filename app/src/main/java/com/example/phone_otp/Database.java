package com.example.phone_otp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class Database extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE = "Database.db";
    private static final String ID = "user_id";
    private static final String FABLE = "user";
    private static final String EMAIL = "user_email";
    private static final String PASSWORD = "user_password";

    public Database(Context context) {
        super( context, DATABASE, null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "create table " + FABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Email TEXT, Password TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void add(Model model) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( EMAIL, model.getEmail() );
        contentValues.put( PASSWORD, model.getPassword() );
        sqLiteDatabase.insert( FABLE, null, contentValues );
        sqLiteDatabase.close();

    }

    public boolean checkUser(String email, String password) {
        String[] columns = {ID};
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selection = EMAIL + "=?" + " AND " + PASSWORD + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = sqLiteDatabase.query( FABLE, columns, selection, selectionArgs,
                                              null, null, null );
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        return cursorCount > 0;
    }
}
