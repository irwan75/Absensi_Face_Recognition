package com.example.facereg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NameActivity extends AppCompatActivity {

    String nmstb, dataAmbil, stb;
    TextView txStambuk;

    EditText name;
    EditText stambuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        dataAmbil = getIntent().getStringExtra("dataAsis");

        txStambuk = findViewById(R.id.txStambuk);
        name = (EditText) findViewById(R.id.etName);
        stambuk = (EditText) findViewById(R.id.etStambuk);
        Button nextButton = (Button) findViewById(R.id.nextButton);

        if (dataAmbil.equals("asisten")){
            stambuk.setVisibility(View.INVISIBLE);
            txStambuk.setVisibility(View.INVISIBLE);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataAmbil.equals("asisten")){
                    stb = "Asisten";
                }else if (dataAmbil.equals("praktikan")){
                    stb = stambuk.getText().toString();
                }
                eksekusi();
            }
        });
    }

    public void eksekusi(){
        if(!name.getText().toString().equals("") && stb!=null) {
            nmstb = name.getText().toString() +"-"+stb;
                    Intent intent = new Intent(NameActivity.this, Add.class);
                    intent.putExtra("name", nmstb.trim());
                    startActivity(intent);
                    finish();
//            Toast.makeText(NameActivity.this, ""+nmstb, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(NameActivity.this, "Please enter the name or stambuk", Toast.LENGTH_LONG).show();
        }
    }
}
