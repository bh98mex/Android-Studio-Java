package android.example.emojilogs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME = "register";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FirstName";
    public static final String COL_3 = "LastName";
    public static final String COL_4 = "Password";
    public static final String COL_5 = "Email";
    public static final String COL_6 = "Phone";


    public DatabaseHelper(Context context) {


        super(context, DATABASE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FirstName TEXT, LastName TEXT, Password TEXT, Email TEXT, Phone TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME); //Drop older table if exists
        onCreate(db);

    }

    public boolean insertdata (String nfname, String nlname, String npass, String nemail, String nphone){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COL_2, nfname);
        contentValues.put(DatabaseHelper.COL_3, nlname);
        contentValues.put(DatabaseHelper.COL_4, npass);
        contentValues.put(DatabaseHelper.COL_5, nemail);
        contentValues.put(DatabaseHelper.COL_6,nphone);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

        if (id == -1){
            return false;
        } else {
            return true;
        }

    }
}
