package com.example.intenttoanother;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.Toast;

public class fourth extends AppCompatActivity {


    Button buttonGallery, buttonCapture, movee;
    VideoView VideoView;

    // ActivityResultLauncher'ları tanımlayın
    private ActivityResultLauncher<Intent> galleryLauncher;
    private ActivityResultLauncher<Intent> videoCaptureLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        buttonGallery = findViewById(R.id.buttonGallery);
        buttonCapture = findViewById(R.id.buttonCapture);
        VideoView = findViewById(R.id.VideoView1);
        movee = findViewById(R.id.intenttofifth);


        // Kamera izni kontrolü
        if (ContextCompat.checkSelfPermission(fourth.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(fourth.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        // Galeri başlatıcı
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri videoUri = data.getData();
                            Toast.makeText(this, "Video Seçildi: " + videoUri, Toast.LENGTH_SHORT).show();
                            VideoView.setVideoURI(videoUri); // Seçilen videoyu VideoView üzerinde göster
                        }
                    }
                }
        );

        // Video çekim başlatıcı
        videoCaptureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri videoUri = data.getData();
                            Toast.makeText(this, "Video Kaydedildi: " + videoUri, Toast.LENGTH_SHORT).show();
                            VideoView.setVideoURI(videoUri); // Çekilen videoyu VideoView üzerinde göster
                        }
                    }
                }
        );

        // Galeri butonu tıklama dinleyicisi
        buttonGallery.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(intent);
        });

        // Video çekim butonu tıklama dinleyicisi
        buttonCapture.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            videoCaptureLauncher.launch(intent);
        });

        movee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(fourth.this, fifth.class);
                startActivity(intent);
            }
        });
    }
}
