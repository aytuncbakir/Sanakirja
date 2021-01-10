package com.sivamalabrothers.sanakirja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.RelativeLayout;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;


public class KelimeBilgileriListele extends AppCompatActivity implements View.OnClickListener
{

    WebView cvs;
    FloatingActionButton fabpaylas,fabpaylaslink;

    RelativeLayout arkaplan;
    private static int reklamGoster = 1;

    int gelenPosition;
    String gelenKelime;


    ArrayList<String> eklediklerim;

    // Justify tag
    String justifyTag = "<html><body style='text-align:justify;'>%s</body></html>";
    // Concatenate your string with the tag to Justify it
    String data = "" ;
    String dataString = "" ;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelime_listele);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        arkaplan = findViewById(R.id.cslay1);

        initViews();
        duaGoruntule();
        animasyonUygula();

    }



    private void animasyonUygula(){
        if(Build.VERSION.SDK_INT >=21){
            Fade enterTransition = new Fade();
            enterTransition.setDuration(1000);
            getWindow().setEnterTransition(enterTransition);

        }
    }

    // geri butonuna basıldığında çalışır
    @Override
    public boolean onSupportNavigateUp(){
        if(Build.VERSION.SDK_INT >= 21)
            finishAfterTransition();
        else
            finish();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void ayarlari_yukle() {

    }

    @SuppressLint("RestrictedApi")
    public void initViews(){

        cvs = findViewById(R.id.cevsentahtasi);
        fabpaylas = findViewById(R.id.fabpaylas);
        fabpaylas.setOnClickListener(this);
        fabpaylas.setVisibility(View.VISIBLE);


        fabpaylaslink = findViewById(R.id.fabpaylaslink);
        fabpaylaslink.setOnClickListener(this);
        fabpaylaslink.setVisibility(View.VISIBLE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gelenKelime = extras.getString("kelime");
            gelenPosition = extras.getInt("id");
            fabpaylas.setVisibility(View.INVISIBLE);

            // and get whatever type user account id is
        }else {
            gelenPosition = -1;
            gelenKelime = "";
            fabpaylaslink.setVisibility(View.INVISIBLE);
        }


    }

    public void duaGoruntule(){

        //data = GirisSayfasi.listeItems.get(gelenPosition);

        data = eklenenDatalarim();
        dataString = String.format(Locale.US, justifyTag, data);

        // Load the data in the web view
        cvs.loadDataWithBaseURL("", dataString, "text/html", "UTF-8", "");

    }

    private String eklenenDatalarim(){

        VeriTabani vt;
        vt = new VeriTabani(KelimeBilgileriListele.this);


        eklediklerim = new ArrayList<>();

        if(gelenPosition == -1)
            eklediklerim = (ArrayList<String>) vt.VeriListele();
        else  if(gelenPosition >= 0)
            eklediklerim = (ArrayList<String>) vt.VeriListele();
        else if(!gelenKelime.equals(""))
                eklediklerim = (ArrayList<String>) vt.veriGetir(gelenKelime);

        String s ="";
        String p = "";
        String k = "";
        String tmp ="";
        p = "";
        k = "";

        for(int i = 0; i<eklediklerim.size();i++){

            if(i%4 == 0) {
                tmp = "<b>SıraNo: </b>";
                s += tmp;
                s += eklediklerim.get(i)+"<br>";
            }
            else if(i%4 == 1) {
                tmp = "<b>Kelime: </b>";
                s += tmp;
                s += eklediklerim.get(i)+"<br>";
            }else if(i%4  == 2){
                tmp = "<b>Anlamı: </b>";
                s += tmp;
                s += eklediklerim.get(i)+"<br>";
            }else {
                tmp = "<b>Cümleler: </b><br>";
                s += tmp;
                s += eklediklerim.get(i)+"<br><br>";

            }


        }

       s = "<center><font color ='brown'><b>SÖZLÜK</b></font></center><br><br>"+p+ "<font color ='brown'>"+s+"</font>" + k+"<br><br>";


        return s;
    }

    private String cumleDuzenle(String cumle){

        String sonuc = "";
        String[] arrayOfCumle = cumle.split(".");

        for (String a : arrayOfCumle)
            sonuc += a+"<br>";

        return sonuc;
    }

    private void verileri_paylas(){


        Spanned spanned = Html.fromHtml(dataString);
        char[] chars = new char[spanned.length()];
        TextUtils.getChars(spanned, 0, spanned.length(), chars, 0);
        String plainText = new String(chars);

        Intent paylas = new Intent(Intent.ACTION_SEND);
        paylas.setType("text/plain");
        paylas.putExtra(Intent.EXTRA_TEXT,plainText);
        startActivity(Intent.createChooser(paylas,"Paylaş"));
    }

    private void link_paylas(){

        String plainText = eklediklerim.get(17);

        Intent paylas = new Intent(Intent.ACTION_SEND);
        paylas.setType("text/plain");
        paylas.putExtra(Intent.EXTRA_TEXT,plainText);
        startActivity(Intent.createChooser(paylas,"Paylaş"));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.fabpaylas:
                verileri_paylas();
                break;
            case R.id.fabpaylaslink:
                link_paylas();
                break;

        }
    }


}

