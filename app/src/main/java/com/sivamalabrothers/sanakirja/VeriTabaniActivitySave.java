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

public class VeriTabaniActivitySave extends AppCompatActivity {
    private EditText e1,e2,e3;

    private Button btnKaydet;
    private static int kayitNo = 0;



    VeriTabani vt;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.save);

        // geri butonu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        vt = new VeriTabani(VeriTabaniActivitySave.this);
        e1 =  findViewById(R.id.kelime);
        e2 =  findViewById(R.id.kelimeAnlam);
        e3 =  findViewById(R.id.kelimeCumle);


        btnKaydet = findViewById(R.id.btnKaydet);

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkEditText();

                Toast.makeText(getApplicationContext(),e1.getText().toString(),Toast.LENGTH_LONG).show();
                    String str = e1.getText().toString();
                    String anlm =  e2.getText().toString();

                    String klm = str.substring(0,1).toUpperCase()+str.substring(1).toLowerCase();

                    String cml =  e3.getText().toString();
                    cml = cml.replace(".","<br>");

                    vt.VeriEkle(klm,
                            anlm,
                            cml

                            );
                e1.setText("");
                e2.setText("");
                e3.setText("");
                    Toast.makeText(getApplicationContext(),"Kayıt eklendi",Toast.LENGTH_LONG).show();
                if (Build.VERSION.SDK_INT >= 21) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(VeriTabaniActivitySave.this);
                    Intent krn = new Intent(getApplicationContext(), KelimeBilgileriListele.class);
                    startActivity(krn, options.toBundle());
                } else {
                    Intent krn = new Intent(getApplicationContext(), KelimeBilgileriListele.class);
                    startActivity(krn);
                }

            }
        });

      /*  btnKaydet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String ids = e8.getText().toString();
                //Toast.makeText(getApplicationContext(),ids,Toast.LENGTH_LONG).show();
                int id =  Integer.valueOf( ids.trim());


                if(!ids.equals("")){
                    vt.VeriSil(id);
                    Toast.makeText(getApplicationContext(),"Kayıt silindi",Toast.LENGTH_LONG).show();
                    goruntule();
                }else
                    snackBarShow(v);

            }
        });
*/
    }

    private void checkEditText(){
        String kontrol = "";
        kayitNo++;
        kontrol = e1.getText().toString().trim();
        if(kontrol.equals(""))
            e1.setText(String.valueOf(kayitNo));


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
