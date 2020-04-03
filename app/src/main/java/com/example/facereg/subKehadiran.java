package com.example.facereg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.facereg.controller.sbKehadiran;
import com.example.facereg.controller.control;

public class subKehadiran extends AppCompatActivity {

    sbKehadiran sb ;
    control ctrl;

    Button btnlanjut;
    Spinner spinHari, spinJam, spinLokasi, spinPertemuan;
    EditText etKelas;
    String[] hari = { "-","Senin", "Selasa", "Rabu", "Kamis","Jum'at", "Sabtu", "Minggu"};
    String[] jam_masuk= { "-","08.00", "09.40", "13.00", "14.40","16.20"};
    String[] lokasi = { "-","Lab. Multimedia", "Lab. Industri", "Lab. RPL", "Lab. Jaringan","Lab. Dasar"};
    String[] pertemuan = { "-","1", "2", "3", "4","5","6","7","8","9","10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_kehadiran);

        etKelas = findViewById(R.id.etKelas);
        spinHari = (Spinner) findViewById(R.id.spinnerHari);
        spinJam = (Spinner) findViewById(R.id.spinnerJam);
        spinLokasi = (Spinner) findViewById(R.id.spinnerLab);
        spinPertemuan = findViewById(R.id.spinnerPertemuan);
        ArrayAdapter<String> aa1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, hari);
        ArrayAdapter<String> aa2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, jam_masuk);
        ArrayAdapter<String> aa3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lokasi);
        ArrayAdapter<String> aa4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, pertemuan);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinHari.setAdapter(aa1);
        spinJam.setAdapter(aa2);
        spinLokasi.setAdapter(aa3);
        spinPertemuan.setAdapter(aa4);

        sb = new sbKehadiran();
        ctrl = new control();

        spinHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sb.setHr(hari[position]);
//                Toast.makeText(subKehadiran.this, ""+sb.getHr(), Toast.LENGTH_SHORT).show();
//                hr = hari[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sb.setJm(jam_masuk[position]);
//                Toast.makeText(subKehadiran.this, ""+sb.getJm(), Toast.LENGTH_SHORT).show();
//                jm = jam_masuk[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinLokasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sb.setLok(lokasi[position]);
//                Toast.makeText(subKehadiran.this, ""+sb.getLok(), Toast.LENGTH_SHORT).show();
//                lok = lokasi[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinPertemuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ctrl.setPert(pertemuan[position]);
//                Toast.makeText(subKehadiran.this, ""+sb.getPrt(), Toast.LENGTH_SHORT).show();
//                prt = pertemuan[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnlanjut = findViewById(R.id.btnLanjut);

        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sb.setKls(etKelas.getText().toString());
//                kls = etKelas.getText().toString();

                Intent intent = new Intent(subKehadiran.this, Recognize.class);
                startActivity(intent);
//                startActivity(new Intent(subKehadiran.this, Recognize.class));
            }
        });
    }

//    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
//        Toast.makeText(this, "Anda Memilih: " + hari[position],Toast.LENGTH_LONG).show();
//    }
//
//    public void onNothingSelected(AdapterView<?> parent) {
//        Toast.makeText(this, "Silahkan Pilih Negara", Toast.LENGTH_LONG).show();
//    }

}
