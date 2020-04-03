package com.example.facereg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ResultAdd extends AppCompatActivity {

    String listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_add);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewAdd);

        listName = getIntent().getStringExtra("text");
//        List<String> reviewList = Arrays.asList(getIntent().getStringArrayExtra("text"));

        Toast.makeText(this, ""+listName, Toast.LENGTH_SHORT).show();
    }
}
