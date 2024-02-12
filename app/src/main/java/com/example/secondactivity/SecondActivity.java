package com.example.secondactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.activity.result.contract.ActivityResultContracts;

public class SecondActivity extends AppCompatActivity {
public static String MSG;
public static final int REQUEST_IMAGE_CAPTURE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Get the intent content to display in the textview
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Get the textview and put the string inside of it
        TextView textView = findViewById(R.id.textView);
        textView.setText("Welcome back " + emailAddress + "!");

        //Get the data from the telephone field
        Button callButton = findViewById(R.id.buttonCallNumber);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editTextPhone);
                String phoneNumberEditText = editText.getText().toString();

                //Start a phone call
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:"+phoneNumberEditText));
                startActivity(call);
            }
        });

        // This following part is related to the picture and camera
        Button picButton = findViewById(R.id.buttonChangePicture);
        picButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // Start the camera
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView photo =  (ImageView) findViewById(R.id.imageView);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photo.setImageBitmap(imageBitmap);
        }
    };
}