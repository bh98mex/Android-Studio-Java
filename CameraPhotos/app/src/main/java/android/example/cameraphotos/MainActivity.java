package android.example.cameraphotos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button btnTakePhoto;
    ImageView imgDispPhoto;
    Bitmap photo;
    private static final int CAMERA_REQUEST = 500;
    ImageView savedImage;
    String savedBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTakePhoto = (Button)findViewById(R.id.btnTakePhoto);
        imgDispPhoto = (ImageView) findViewById(R.id.imgDispPhoto);
       // savedImage = (ImageView)findViewById(R.id.imgDispPhoto);
        //set the onClick Listeners

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }
        });

        //get a timer to move to the next activity



    }

  protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_REQUEST &&resultCode == Activity.RESULT_OK){
            //create the Bitmap image
            photo = (Bitmap)data.getExtras().get("data");
            imgDispPhoto.setImageBitmap(photo);
            //savedBitmap = encodeToBase64(photo,Bitmap.CompressFormat.JPEG, 100);
        }



      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
          @Override
          public void run() {
              Intent intent = new Intent(MainActivity.this, SavePhoto.class);
              intent.putExtra("Bitmap", photo);
              startActivity(intent);
              finish();
          }
      },2000);
  }

  public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality){

      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      image.compress(compressFormat, quality, byteArrayOutputStream);
      return Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
  }
}
