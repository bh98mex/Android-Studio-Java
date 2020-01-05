package android.example.cameraphotos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

public class SavePhoto extends MainActivity {






    public static Bitmap decodeBase64(String input){
        byte[]decodeBytes = Base64.decode(input,0);
        return BitmapFactory.decodeByteArray(decodeBytes,0,decodeBytes.length);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_photo);

        ImageView  savedImage = (ImageView)findViewById(R.id.newImage);
//successsssss
        Intent intent = getIntent();
        Bitmap photo = (Bitmap) intent.getParcelableExtra("Bitmap");
         //photo = decodeBase64(savedBitmap);
        savedImage.setImageBitmap(photo);


//        savedImage.setImageBitmap(savedPhoto);
//        Intent intent = getIntent();
//

//
//        //get the intent
//        //decode the bitmap image
//        //set the decoded image to an image view
//        savedImage.setImageBitmap(savedPhoto);
    }


}
