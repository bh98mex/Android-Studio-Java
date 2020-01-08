package android.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Details extends  AppCompatActivity  implements View.OnClickListener{

    TextView address;
    public String ADDRESS;
   public String savedData;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getData();

        ImageButton happy = findViewById(R.id.happy);
        ImageButton calm = findViewById(R.id.calm);
        ImageButton silly  = findViewById(R.id.silly);
        ImageButton cool = findViewById(R.id.cool);
        ImageButton nervous = findViewById(R.id.nervous);
        ImageButton annoyed = findViewById(R.id.annoyed);
        ImageButton sad = findViewById(R.id.sad);
        ImageButton shy = findViewById(R.id.shy);
        ImageButton surprised = findViewById(R.id.surprised);
        ImageButton hungry = findViewById(R.id.hungry);
        ImageButton tired = findViewById(R.id.annoyed);
        ImageButton confused = findViewById(R.id.confused);
        ImageButton sleepy = findViewById(R.id.sleepy);
        ImageButton sick = findViewById(R.id.sick);
        ImageButton hurt = findViewById(R.id.hurt);

        happy.setOnClickListener(this);
        calm.setOnClickListener(this);
        silly.setOnClickListener(this);
        cool.setOnClickListener(this);
        nervous.setOnClickListener(this);
        annoyed.setOnClickListener(this);
        sad.setOnClickListener(this);
        shy.setOnClickListener(this);
        surprised.setOnClickListener(this);
        hungry.setOnClickListener(this);
        tired.setOnClickListener(this);
        confused.setOnClickListener(this);
        sleepy.setOnClickListener(this);
        sick.setOnClickListener(this);
        hurt.setOnClickListener(this);


    }

  public void toastMessages (String message){
      Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
  }


//get the data from the previous activity
public void getData(){

    Intent intent = getIntent();
    ADDRESS = intent.getStringExtra("ADDRESS");

    address = (TextView)findViewById(R.id.adresa);
    address.setText(ADDRESS);


}

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.happy:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.calm:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.silly:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cool:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nervous:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.annoyed:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sad:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.shy:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.surprised:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.hungry:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tired:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.confused:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sleepy:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sick:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.hurt:
                Toast.makeText(this, "You are "+view.getTag().toString()+"!",Toast.LENGTH_SHORT).show();
                break;

        }
        //get the tag of the selected button to the next view
        savedData = view.getTag().toString();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent newActivity = new Intent(Details.this, ImageAndText.class);
                newActivity.putExtra("ADDRESS",ADDRESS.toString());
                newActivity.putExtra("Feel",savedData.toString());
                startActivity(newActivity);
                finish();
            }
            //Make sure you choose an ideal delay based on coverage
        },3000);
    }

}
