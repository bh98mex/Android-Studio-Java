package android.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class ImageAndText extends AppCompatActivity {

    public String ADDRESS;
    public String savedData;
    TextView address;
    TextView savedEmotions;
    String message;
    Intent newActivity;
    // define values for the camera intent
    Button photo;
    ImageView imageView;
    String pathToFile;
    Bitmap bitmap;
    byte[] byteArray;
    String savedBitmap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_and_text);
        photo = (Button)findViewById(R.id.photo);

        getData();

        //create a method to get input from the camera

        //ask for permissions

        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dispatchPictureTakerAction();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //very important--define the elements in to be used.
         imageView = (ImageView) findViewById(R.id.imageView);

        if(resultCode == RESULT_OK){
            if(requestCode==1){
                 bitmap = BitmapFactory.decodeFile(pathToFile);
                imageView.setImageBitmap(bitmap);

                //rotate the image by 90 degrees

                imageView.setRotation(imageView.getRotation()+90);
                savedBitmap = encodeToBase64(bitmap,Bitmap.CompressFormat.JPEG, 50);

//                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bStream);
//                byteArray = bStream.toByteArray();

                //pass bitmap as extended data


            }
        }
        Toast toast = Toast.makeText(getApplicationContext(),"Photo taken...",Toast.LENGTH_LONG);
        toast.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                newActivity = new Intent(ImageAndText.this, Text.class);
                newActivity.putExtra("ADDRESS",ADDRESS.toString());
                newActivity.putExtra("Feel",savedData.toString());

                newActivity.putExtra("BitmapImage",bitmap);
                startActivity(newActivity);
                finish();
            }
            //Make sure you choose an ideal delay based on coverage
        },6000);
    }

    private void dispatchPictureTakerAction() {

        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePic.resolveActivity(getPackageManager()) != null) {
            //check if there is an app to handle pictures
            //create a file to store the pictures

            File photoFile = null;
            photoFile = createPhotoFile();
            //save he picture in this photofile variable
            //check if the photofile is not null and has been successfully created
            if (photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(ImageAndText.this, "android.example.myapplication", photoFile);
                //this line of code will give other apps the authority to access file in our app temporarily
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePic, 1);

            }

        }
    }

        //method to create a photo file

        private File createPhotoFile() {

            String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            //create the actual file

            File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File image = null;
            //the declaration must be kept outside otherwise we will not be able to return it

            try {
                image = File.createTempFile(name, ".jpg", storageDir);
            } catch (IOException e) {
                Log.d("mylog", "Except: " + e.toString());
            }
            return image;

        }

        public void getData () {
            Intent intent = getIntent();
            ADDRESS = intent.getStringExtra("ADDRESS");
            savedData = intent.getStringExtra("Feel");

            address = (TextView) findViewById(R.id.adresa);
            address.setText(ADDRESS);

            savedEmotions = (TextView) findViewById(R.id.savedData);
            savedEmotions.setText((savedData));

            ImageButton feelButton = (ImageButton) findViewById(R.id.feelButton);

            switch (savedData) {
                case "happy":
                    feelButton.setImageResource(R.drawable.happy);
                    break;
                case "calm":
                    feelButton.setImageResource(R.drawable.calm);
                    break;
                case "silly":
                    feelButton.setImageResource(R.drawable.silly);
                    break;
                case "cool":
                    feelButton.setImageResource(R.drawable.cool);
                    break;
                case "nervous":
                    feelButton.setImageResource(R.drawable.nervous);
                    break;
                case "annoyed":
                    feelButton.setImageResource(R.drawable.annoyed);
                    break;
                case "sad":
                    feelButton.setImageResource(R.drawable.sad);
                    break;
                case "shy":
                    feelButton.setImageResource(R.drawable.shy);
                    break;
                case "surprised":
                    feelButton.setImageResource(R.drawable.surprised);
                    break;
                case "hungry":
                    feelButton.setImageResource(R.drawable.hungry);
                    break;
                case "tired":
                    feelButton.setImageResource(R.drawable.annoyed);
                    break;
                case "confused":
                    feelButton.setImageResource(R.drawable.confused);
                    break;
                case "sleepy":
                    feelButton.setImageResource(R.drawable.sleepy);
                    break;
                case "sick":
                    feelButton.setImageResource(R.drawable.sick);
                    break;
                case "hurt":
                    feelButton.setImageResource(R.drawable.hurt);
                    break;
            }

        }
    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
    }




    }

