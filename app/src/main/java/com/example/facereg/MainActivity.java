package com.example.facereg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void trainingButton(View view){
//        startActivity(new Intent(MainActivity.this, NameActivity.class));
        Intent intent = new Intent(MainActivity.this, mKehadiran.class);
        intent.putExtra("data1", "addface");
        startActivity(intent);
    }

    public void faceRecognition(View view){
        Intent intent = new Intent(MainActivity.this, mKehadiran.class);
        intent.putExtra("data1", "faceReg");
        startActivity(intent);
//        startActivity(new Intent(MainActivity.this, mKehadiran.class));
    }
}
