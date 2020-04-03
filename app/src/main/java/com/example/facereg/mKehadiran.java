package com.example.facereg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.facereg.controller.asisPrak;

public class mKehadiran extends AppCompatActivity {

    String dataAmbil;
    asisPrak as ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_kehadiran);

        dataAmbil = getIntent().getStringExtra("data1");

        as = new asisPrak();

    }

    public void Praktikan(View view){
        if (dataAmbil.equals("faceReg")){
            as.setStatus("praktikan");
            startActivity(new Intent(mKehadiran.this, subKehadiran.class));
            finish();
        }else if(dataAmbil.equals("addface")){
//            Toast.makeText(this, "Data Praktikan", Toast.LENGTH_SHORT).show();
            as.setStatus("praktikan");
            Intent intent = new Intent(mKehadiran.this, NameActivity.class);
            intent.putExtra("dataAsis", "praktikan");
            startActivity(intent);
        }
    }

    public void Asisten(View view){
        if (dataAmbil.equals("faceReg")){
//            Toast.makeText(this, "Face Recognition Masuk", Toast.LENGTH_SHORT).show();
            as.setStatus("asisten");
            startActivity(new Intent(mKehadiran.this, subKehadiran.class));
            finish();
        }else if (dataAmbil.equals("addface")){
//            Toast.makeText(this, "Data Asisten", Toast.LENGTH_SHORT).show();
            as.setStatus("asisten");
            Intent intent = new Intent(mKehadiran.this, NameActivity.class);
            intent.putExtra("dataAsis", "asisten");
            startActivity(intent);
        }

    }
}
