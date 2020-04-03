package com.example.facereg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.facereg.controller.sbKehadiran;
import com.example.facereg.controller.control;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class Attendees{

    public String names;
    public String date;

    public Attendees() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Attendees(String names, String date) {
        this.names  = names;
        this.date = date;
    }

}

public class ReviewResult extends AppCompatActivity implements ReviewListAdapter.ClickListener{

    sbKehadiran sb = new sbKehadiran();
    control ctrl = new control();
    DatabaseReference dbReff;

    int nilai = 0;

    private List<String> commitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_result);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        Button commitButton = (Button) findViewById(R.id.button);
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Review and Mark");
        }

        List<String> reviewList = Arrays.asList(getIntent().getStringArrayExtra("list"));

        ReviewListAdapter reviewListAdapter = new ReviewListAdapter(this, reviewList);
        reviewListAdapter.setClickListener(this);
        recyclerView.setAdapter(reviewListAdapter);

        //Setting LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onItemClick(String name) {
        if(commitList.contains(name))
            commitList.remove(name);
        else
            commitList.add(name);
    }

//    private void addItemToSheet(final String nm, final String stb) {
//
//        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycby5VM5oMQTysEYhpNP1JoB4lLYG3504y12nkPCIZ8fRDB6FpfOK/exec",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        loading.dismiss();
//                        Toast.makeText(ReviewResult.this,response,Toast.LENGTH_LONG).show();
//                        for (int i = 0; i<=5;i++){
//                            System.out.println(response);
//                        }
//                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                        startActivity(intent);
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parmas = new HashMap<>();
//
//                //here we pass params
//                parmas.put("action","addItemAP");
//                parmas.put("stambuk",stb);
//                parmas.put("nama",nm);
//                parmas.put("kelas",sb.getKls());
//                parmas.put("lokasi",sb.getLok());
//                parmas.put("hari",sb.getHr());
//                parmas.put("jam",sb.getJm());
//
//                return parmas;
//            }
//        };
//
//        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
//
//        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(retryPolicy);
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        queue.add(stringRequest);
//
//    }

    public void commit() {
        String lengkap;
        if(commitList.size() != 0) {

            for(Object o : commitList)
            {
                lengkap = o.toString();
                String[] kata = lengkap.split("-");
                String[] freq = sb.getKls().split("-");

                sb.setNm(kata[0]);
                sb.setStb(kata[1]);

                if (ctrl.getPert().equals("1")){
                    sb.setPrt1("1");
                    sb.setPrt2("0");
                    sb.setPrt3("0");
                    sb.setPrt4("0");
                    sb.setPrt5("0");
                    sb.setPrt6("0");
                    sb.setPrt7("0");
                    sb.setPrt8("0");
                    sb.setPrt9("0");
                    sb.setPrt10("0");
                }else if (!ctrl.getPert().equals("1")){
                    inisialisasiData();
                    nilai = Integer.parseInt(ctrl.getPert());
                }

                dbReff = FirebaseDatabase.getInstance().getReference().child("Absensi Praktikan");

                if(sb.getStb().equals("Asisten")){
                    insertDataAsisten("Asisten");
                }else if (freq[0].equals("AP")){
                    insertData("Algoritma Pemrograman");
                }else if (freq[0].equals("BD")){
                    insertData("Basis Data");
                }else if (freq[0].equals("JF")){
                    insertData("Java Fundamental");
                }else if (freq[0].equals("PT")){
                    insertData("Pemrograman Terstruktur");
                }else {
                    Toast.makeText(this, "Praktikum Tidak Terdaftar / Frequensi Salah", Toast.LENGTH_SHORT).show();
                }

//                addItemToSheet(text1, text2);
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Please select at least one student", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertDataAsisten(String nmPrak){
        if (ctrl.getPert().equals("1")){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getNm()).setValue(sb);
        }else if (nilai==2){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getNm()).child("prt2").setValue("1");
        }else if (nilai==3){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getNm()).child("prt3").setValue("1");
        }else if (nilai==4){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getNm()).child("prt4").setValue("1");
        }else if (nilai==5){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getNm()).child("prt5").setValue("1");
        }else if (nilai==6){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getNm()).child("prt6").setValue("1");
        }else if (nilai==7){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getNm()).child("prt7").setValue("1");
        }else if (nilai==8){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getNm()).child("prt8").setValue("1");
        }else if (nilai==9){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getNm()).child("prt9").setValue("1");
        }else if (nilai==10){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getNm()).child("prt10").setValue("1");
        }
        pindahActiv();
    }

    public void insertData(String nmPrak){
        if (ctrl.getPert().equals("1")){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getStb()).setValue(sb);
        }else if (nilai==2){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getStb()).child("prt2").setValue("1");
        }else if (nilai==3){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getStb()).child("prt3").setValue("1");
        }else if (nilai==4){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getStb()).child("prt4").setValue("1");
        }else if (nilai==5){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getStb()).child("prt5").setValue("1");
        }else if (nilai==6){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getStb()).child("prt6").setValue("1");
        }else if (nilai==7){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getStb()).child("prt7").setValue("1");
        }else if (nilai==8){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getStb()).child("prt8").setValue("1");
        }else if (nilai==9){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getStb()).child("prt9").setValue("1");
        }else if (nilai==10){
            dbReff.child(nmPrak).child(sb.getKls()).child(sb.getStb()).child("prt10").setValue("1");
        }
        pindahActiv();
    }

    public void pindahActiv(){
        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void inisialisasiData(){
//        Toast.makeText(this, "Suksesji Tawwa", Toast.LENGTH_SHORT).show();
        sb.setPrt1("0");
        sb.setPrt2("0");
        sb.setPrt3("0");
        sb.setPrt4("0");
        sb.setPrt5("0");
        sb.setPrt6("0");
        sb.setPrt7("0");
        sb.setPrt8("0");
        sb.setPrt9("0");
        sb.setPrt10("0");
    }


//    public void commit() {

//        if(commitList.size() != 0) {
//            Enable firebase and then uncomment the following lines

//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference myRef = database.getReference("attendence");

//            convert to a comma separated string
//            this has to be the worst way to push data to a db
//            StringBuilder sb = new StringBuilder();
//            for (String s : commitList) {
//                sb.append(s);
//                sb.append(",");
//            }

//            Attendees at = new Attendees(sb.toString(), (new Date()).toString());
//            String key = myRef.push().getKey();
//            myRef.child(key).setValue(at);

//            Toast.makeText(getApplicationContext(), "Enable firebase for this to work", Toast.LENGTH_LONG).show();
//            finish();


//            System.out.println(sb.toString());

//        }
//        else {
//            Toast.makeText(getApplicationContext(), "Please select at least one student", Toast.LENGTH_SHORT).show();
//        }
//    }
}
