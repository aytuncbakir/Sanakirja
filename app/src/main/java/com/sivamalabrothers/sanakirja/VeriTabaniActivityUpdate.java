package com.sivamalabrothers.sanakirja;


import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class VeriTabaniActivityUpdate extends AppCompatActivity {
    private EditText e1,e2,e3;

    private Button btnUpdate,btnGetir;

    int id;

    VeriTabani vt;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.update);

        // geri butonu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        vt = new VeriTabani(VeriTabaniActivityUpdate.this);


        e1 =  findViewById(R.id.kelime);
        e2 =  findViewById(R.id.kelimeAnlam);
        e3 =  findViewById(R.id.kelimeCumle);



        btnGetir = findViewById(R.id.btnGetir);
        btnUpdate = findViewById(R.id.btnUpdate);




        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!e1.getText().toString().equals("") &&
                        !e2.getText().toString().equals("")&&
                        !e3.getText().toString().equals(""))
                vt.VeriDuzenle(e1.getText().toString(), e2.getText().toString(), e3.getText().toString());
                Toast.makeText(getApplicationContext(),"Kayıt güncellendi.",Toast.LENGTH_LONG).show();
                e1.setText("");
                e2.setText("");
                e3.setText("");
                if (Build.VERSION.SDK_INT >= 21) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(VeriTabaniActivityUpdate.this);
                    Intent krn = new Intent(getApplicationContext(), KelimeBilgileriListele.class);
                    startActivity(krn, options.toBundle());
                } else {
                    Intent krn = new Intent(getApplicationContext(), KelimeBilgileriListele.class);
                    startActivity(krn);
                }

            }
        });

        btnGetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String str = e1.getText().toString();
                String ids = str.substring(0,1).toUpperCase()+str.substring(1).toLowerCase();

                ArrayList<String> data;
                if(!ids.equals("")){
                    data = (ArrayList<String>) vt.veriGetir(ids);
                    e1.setText(data.get(1));
                    e2.setText(data.get(2));
                    e3.setText(data.get(3));

                    //Toast.makeText(getApplicationContext(),tempData,Toast.LENGTH_LONG).show();
                    //splitData(tempData);
                }else
                    snackBarShow(v);

            }
        });

    }




    // geri butonuna basıldığında çalışır
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



    @Override
    protected void onResume() {
        // klavye açılınca buton vesairin kaymasını önleyen kod
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
        super.onResume();
    }


    private void snackBarShow(View view){
        Snackbar mSnackBar = Snackbar.make(view, "Tüm alanları doldurmalısınız.", Snackbar.LENGTH_LONG);

        view = mSnackBar.getView();

        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();

        params.gravity = Gravity.CENTER;
        view.setLayoutParams(params);
        view.setBackgroundResource(R.color.burc);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            view.setAlpha(0.7f);
        }
        TextView mainTextView = (view).findViewById(R.id.snackbar_text);
        mainTextView.setTextColor(Color.WHITE);
        mainTextView.setPadding(40,0,40,0);
        mainTextView.setGravity(Gravity.CENTER);
        mSnackBar.show();
    }


}
