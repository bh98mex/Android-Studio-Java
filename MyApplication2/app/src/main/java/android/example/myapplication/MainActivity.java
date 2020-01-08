package android.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.OnMapReadyCallback;

public class MainActivity extends AppCompatActivity  {

    Button start, logs;
    public String ADDRESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




            start = findViewById(R.id.start);
           logs = findViewById(R.id.logs);

            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(MainActivity.this,MapsActivity.class);
                    startActivity(i);
                }
            });

            logs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                  //  Intent y = new Intent (MainActivity.this, Signup.class);
                  //  startActivity(y);
                }
            });
        }
    }

