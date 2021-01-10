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

public class VeriTabaniActivityDelete extends AppCompatActivity {
    private EditText e1,e2,e3,e4,e5,e6,e7,e8;
    private Button btnSil;

    int id;

    VeriTabani vt;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.delete);

        // geri butonu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        vt = new VeriTabani(VeriTabaniActivityDelete.this);

        e1 =  findViewById(R.id.silinecekData);
        btnSil = findViewById(R.id.btnSil);



        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!e1.getText().toString().equals("")) {
                    String str = e1.getText().toString();
                    String ids = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
                    vt.VeriSil(ids);
                    e1.setText("");


                }
                Toast.makeText(getApplicationContext(),"Kayıt silindi.",Toast.LENGTH_LONG).show();
                if (Build.VERSION.SDK_INT >= 21) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(VeriTabaniActivityDelete.this);
                    Intent krn = new Intent(getApplicationContext(), KelimeBilgileriListele.class);
                    startActivity(krn, options.toBundle());
                } else {
                    Intent krn = new Intent(getApplicationContext(), KelimeBilgileriListele.class);
                    startActivity(krn);
                }
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
