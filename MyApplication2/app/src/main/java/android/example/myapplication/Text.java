package android.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

public class Text extends ImageAndText{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);


        getData();
        Intent intent = getIntent();
        bitmap = intent.getParcelableExtra("BitmapImage");
        bitmap= decodeBase64(savedBitmap);
//        Intent intent = getIntent();

//
//        byte[] byteArray = getIntent().getByteArrayExtra("BitmapImage");
//        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView2.setImageBitmap(bitmap);

    }

    public static Bitmap decodeBase64(String input){
        byte[]decodeBytes = Base64.decode(input,0);
        return BitmapFactory.decodeByteArray(decodeBytes,0,decodeBytes.length);
    }

}
