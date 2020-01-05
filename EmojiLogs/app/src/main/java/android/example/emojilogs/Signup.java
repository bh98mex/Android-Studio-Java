package android.example.emojilogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    //declare global variables
    DatabaseHelper databaseHelper;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg, _btnlogin;
    EditText _txtfanme, _txtusername, _txtpass, _txtemail, _txtphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //find the elements throughout the layout
        _txtfanme = (EditText) findViewById(R.id.ed1);
        _txtusername = (EditText) findViewById(R.id.ed4);
        _txtpass = (EditText) findViewById(R.id.ed5);
        _txtemail = (EditText)findViewById(R.id.ed3);
        _txtphone= (EditText) findViewById(R.id.ed2);

        _btnreg = (Button) findViewById(R.id. btn1);
        _btnlogin = (Button) findViewById(R.id.btn2);

        //set the onClick listeners
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db= openHelper.getWritableDatabase();
                String fname = _txtfanme.getText().toString();
                String lname = _txtusername.getText().toString();
                String pass = _txtpass.getText().toString();
                String email = _txtemail.getText().toString();
                String phone = _txtphone.getText().toString();


                // method to input data in the database
                insertdata(fname, lname, pass, email, phone);
            }
        });

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Signup.this,Login.class);
                startActivity(i);
            }
        });

        //create a method to input data

    }

    public void insertdata (String fname, String lname, String pass, String email, String phone){

        boolean addData  = databaseHelper.insertdata( fname, lname, pass, email, phone);

        if (addData){
            toastMessage("Data entered successfully!");
        }else{
            toastMessage("Everything failed");
        }

    }
    public void toastMessage(String message){
        Toast.makeText(this,message ,Toast.LENGTH_LONG).show();
    }

}
