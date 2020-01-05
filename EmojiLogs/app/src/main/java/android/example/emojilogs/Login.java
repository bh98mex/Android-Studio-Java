package android.example.emojilogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    EditText ed8, ed9;
    Button btn3;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed8 = (EditText)findViewById(R.id.ed8);
        ed9 = (EditText) findViewById(R.id.ed9);
        btn3 = findViewById(R.id.btn3);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uid = ed8.getText().toString();
                String pwd = ed9.getText().toString();

                //move through the database data
                cursor = db.rawQuery("SELECT *FROM "+ DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_5 + " =? AND " + DatabaseHelper.COL_4 + " =? ", new String[]{uid,pwd});

                if(cursor!= null){
                    if (cursor.getCount()>0){
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT ).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Login error", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, Signup.class);
                        startActivity(i);
                    }
                }
            }
        });
    }
}
