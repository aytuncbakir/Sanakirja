package com.sivamalabrothers.sanakirja;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;


public class GirisSayfasi extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    LinearLayout listItem;
    TextView tv;
    RelativeLayout arkaplan;
    int layType;
    ArrayList<String> eklediklerim;
    FloatingActionButton fabekle,fabupdate,fabpaylas;
    public static ArrayList<String> listeItems;

    VeriTabani vt;

    // Justify tag
    String justifyTag = "<html><body style='text-align:justify;'>%s</body></html>";
    // Concatenate your string with the tag to Justify it
    String data = "" ;
    String dataString = "" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Make to run your application only in portrait mode
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Make to run your application only in LANDSCAPE mode
        //setContentView(R.layout.disable_android_orientation_change);


        setContentView(R.layout.giris_sayfasi);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();



        tv = findViewById(R.id.tv);
        arkaplan = findViewById(R.id.giris_layout);

        recyclerView = findViewById(R.id.recyclerview_giris);

       // resizeAppViews();

        GirisMenuCustomAdapter girisMenuCustomAdapter =
                new GirisMenuCustomAdapter(this,GirisMenuItem.getGirisMenuItems());
        recyclerView.setAdapter(girisMenuCustomAdapter);

       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        /* GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);  */


        spanText();
        animasyonUygula();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.fabpaylas:
                uygulamayi_paylas();
                break;

        }
    }




    private void animasyonUygula(){
        if(Build.VERSION.SDK_INT >=21){
            Slide enterTransition = new Slide();
            enterTransition.setDuration(300);
            enterTransition.setSlideEdge(Gravity.BOTTOM);
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


    @Override
    protected void onPause() {
        super.onPause();


    }

    public  void tiklananMenuItem(int position) {

        //Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_LONG).show();

        if(position == 0) {

            if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), VeriTabaniActivitySave.class);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), VeriTabaniActivitySave.class);
                startActivity(krn);
            }
        }else if(position == 1) {

           if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), KelimeBilgileriListele.class);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), KelimeBilgileriListele.class);
                startActivity(krn);
            }
        }else if(position == 2) {

            if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), VeriTabaniActivitySearch.class);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), VeriTabaniActivitySearch.class);
                startActivity(krn);
            }
        }else if(position == 3) {

            if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), VeriTabaniActivityDelete.class);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), VeriTabaniActivityDelete.class);
                startActivity(krn);
            }
        }else if(position == 4) {

            if (Build.VERSION.SDK_INT >= 21) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(GirisSayfasi.this);
                Intent krn = new Intent(getApplicationContext(), VeriTabaniActivityUpdate.class);
                startActivity(krn, options.toBundle());
            } else {
                Intent krn = new Intent(getApplicationContext(), VeriTabaniActivityUpdate.class);
                startActivity(krn);
            }

        }



    }

    private void spanText(){

        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/fofbb_reg.ttf");

      
        tv.setTypeface(font,Typeface.BOLD);
        tv.setTextSize(28);
        tv.setText("SÖZLÜK");
        //yourTextView.setText(sb);

    }

    private void uygulamayi_paylas(){


        data = eklenenDatalarim();
        dataString = String.format(Locale.US, justifyTag, data);

        Spanned spanned = Html.fromHtml(dataString);
        char[] chars = new char[spanned.length()];
        TextUtils.getChars(spanned, 0, spanned.length(), chars, 0);
        String plainText = new String(chars);

        Intent paylas = new Intent(Intent.ACTION_SEND);
        paylas.setType("text/plain");
        paylas.putExtra(Intent.EXTRA_TEXT,plainText);
        startActivity(Intent.createChooser(paylas,"Paylaş"));
    }

    private String eklenenDatalarim() {

        return "";
    }



}
