package com.example.avocadroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private static final int PERMISO_DE_ABRIR_CAMARA = 1 ;
    private static final int FOTOGRAFIA_ID = 123;

    Button bttnVerificarID;
    ImageView imgCamaraID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        PERMISO_DE_ABRIR_CAMARA);

            }
        }


            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bttnVerificarID = (Button)findViewById(R.id.bttnVerificar);
        imgCamaraID = (ImageView)findViewById(R.id.imgCamara);

        imgCamaraID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent camera_intent
                        = new Intent(MediaStore
                        .ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, FOTOGRAFIA_ID);
            }
        });
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        if (requestCode == FOTOGRAFIA_ID) {
            Bitmap photo = (Bitmap)data.getExtras()
                    .get("data");
            imgCamaraID.setImageBitmap(photo);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISO_DE_ABRIR_CAMARA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //PERMISO OTORGADO
                } else {
                //PERMISO DENEGADO
                }
                return;
            }
        }
    }

}

