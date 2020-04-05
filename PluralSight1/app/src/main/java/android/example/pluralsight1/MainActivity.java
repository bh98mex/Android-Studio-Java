package android.example.pluralsight1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //identify the elements in the view
        Button doubleButton = findViewById(R.id.double_button);

    //create a onclick method attached to the existing button in the view
        doubleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textValue = findViewById(R.id.text_value);
                //convert the value of the textView into a String
                String stringValue = textValue.getText().toString();
                //convert the value into an integer
                int originalVaue = Integer.parseInt(stringValue);
                //find the new value with the aid of the worker class, MyWorker
                int newValue =  MyWorker.doubleTheValue(originalVaue);
                //write the final value to the existing view
                textValue.setText(Integer.toString(newValue));


                //create a toast message to keep the user up to date with the changes
                Toast toast = Toast.makeText(getApplicationContext(),"The number has doubled...from "+ originalVaue+" to "+ newValue, Toast.LENGTH_LONG);
                toast.show();

                //the process will only go to the max limit of the integer and will return to 0

            }
        });


    }
}
