package android.example.cookie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void eatCookie (View view){


        //find a reference to the ImageView in the layout. Change the image
        ImageView beforeCookieImageView = (ImageView)findViewById(R.id.android_cookie_image_view);
        beforeCookieImageView.setImageResource(R.drawable.after_cookie);


        //Find a reference to the textView in the layout. Change the text
        TextView statusInitialTextView = (TextView) findViewById(R.id.status_text_view);
        statusInitialTextView.setText("I am totally full!");

    }
}
