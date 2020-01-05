package android.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //displayFreeThrow(1);
        displayForTeamA(0);
        displayForTeamB(0);

    }

    public void displayForTeamA(int scoreTeamA){
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreTeamA));

    }

    public void addThreeForTeamA (View v){
        scoreTeamA = scoreTeamA+3;
        displayForTeamA(scoreTeamA);


    }

    public void addTwoForTeamA (View v){
        scoreTeamA = scoreTeamA +2;
        displayForTeamA(scoreTeamA);

    }

    public void addOneForTeamA (View v){
        scoreTeamA = scoreTeamA+1;
        displayForTeamA(scoreTeamA);

    }

    public void displayForTeamB (int scoreTeamB){

        TextView scoreViewB = findViewById(R.id.team_b_score);
        scoreViewB.setText(String.valueOf(scoreTeamB));
    }

    public void addThreeForTeamB (View v){
        scoreTeamB = scoreTeamB+3;
        displayForTeamB(scoreTeamB);


    }

    public void addTwoForTeamB (View v){
        scoreTeamB = scoreTeamB +2;
        displayForTeamB(scoreTeamB);

    }

    public void addOneForTeamB (View v){
        scoreTeamB = scoreTeamB+1;
        displayForTeamB(scoreTeamB);

    }

    public void reset (View v){
        scoreTeamB =0;
        scoreTeamA=0;
        displayForTeamB(0);
        displayForTeamA(0);
    }
}
