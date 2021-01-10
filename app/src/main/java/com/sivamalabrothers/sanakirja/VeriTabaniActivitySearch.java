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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;


public class VeriTabaniActivitySearch extends AppCompatActivity {

    private EditText e1,e2;
    private Button btnSearch,btnSearchID;
    ArrayList<String> veriler = new ArrayList<String>();
    int id;
    // Justify tag
    String justifyTag = "<html><body style='text-align:justify;'>%s</body></html>";
    // Concatenate your string with the tag to Justify it
    String data = "" ;
    String dataString = "" ;
    WebView cvs;
    VeriTabani vt;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.search);

        // geri butonu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cvs = findViewById(R.id.arananKelime);
        vt = new VeriTabani(VeriTabaniActivitySearch.this);
        e2 =  findViewById(R.id.aranacakKelime);


        btnSearch = findViewById(R.id.btnSearch);

        final VeriTabani vt = new VeriTabani(VeriTabaniActivitySearch.this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!e2.getText().toString().equals("")) {
                    String str = e2.getText().toString();
                    String klm = str.substring(0,1).toUpperCase()+str.substring(1).toLowerCase();
                    veriler = (ArrayList<String>) vt.veriGetir(klm);
                    veriGoruntule();
                    e2.setText("");
                }
            }
        });






    }


    public void veriGoruntule(){

        //data = GirisSayfasi.listeItems.get(gelenPosition);

        data = gelenDataDuzenle();
        dataString = String.format(Locale.US, justifyTag, data);

        // Load the data in the web view
        cvs.loadDataWithBaseURL("", dataString, "text/html", "UTF-8", "");

    }


    private String gelenDataDuzenle(){



        String s ="";
        String p = "";
        String k = "";
        String tmp ="";
        p = "<table border='2'><font color ='brown'>";
        k = "</table ></font>";

        for(int i = 0; i<veriler.size();i++){

            if(i%4 == 0) {
                tmp = "<b>Id No: </b>";
                s += tmp;
                s += veriler.get(i)+"<br>";
            }
            else if(i%4 == 1) {
                tmp = "<b>Kelime: </b>";
                s += tmp;
                s += veriler.get(i)+"<br>";
            }else if(i%4  == 2){
                tmp = "<b>Anlamı: </b>";
                s += tmp;
                s += veriler.get(i)+"<br>";
            }else {
                tmp = "<b>Cümleler: </b><br>";
                s += tmp;
                s += veriler.get(i)+"<br>";
                s = "<tr>"+s+"</tr>";
            }


        }

        s = p + s + k;


        return s;
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
