package com.example.intenttoanother;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class third extends AppCompatActivity {
    private Button move4, move44;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        move44 = findViewById(R.id.yaziyicevirme);
        move4 = findViewById(R.id.Move4);
        move4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(third.this, fourth.class);
                startActivity(intent);
            }
        });
        move44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(third.this, sixth.class);
                startActivity(intent);
            }
        });
    }
}