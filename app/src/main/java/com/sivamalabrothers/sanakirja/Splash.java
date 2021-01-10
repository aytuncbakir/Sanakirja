package com.sivamalabrothers.sanakirja;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;


public class Splash extends Activity implements Animation.AnimationListener {

    private TextView tv;
    private ImageView img;
    private Animation mImgAnim;
    private Animation mTextAnim;

    private AdView adView;
    LinearLayout reklam_layout;
    private static final String REKLAM_ID = "ca-app-pub-3183404528711365/8591627816";

    RelativeLayout arkaplan;

    private int screenWidth;
    private int screenHeight;

    ArrayList<String> eklediklerim;

    public static ArrayList<String> listeItems;

    VeriTabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Make to run your application only in portrait mode
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Make to run your application only in LANDSCAPE mode
        //setContentView(R.layout.disable_android_orientation_change);
        setContentView(R.layout.splash);

        initViews();
        //reklam_yukle();
        alphaAnimation(img);

    }

    private void reklam_yukle(){

        reklam_layout = findViewById(R.id.reklam_layout);
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(REKLAM_ID);

        reklam_layout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);
    }


    private void initViews(){

        tv = findViewById(R.id.tv);
        img = findViewById(R.id.img);
        arkaplan = findViewById(R.id.splash);
        spanText();
        screenWidth = getScreenWidth();
        screenHeight= getScreenHeight();
        //Toast.makeText(getApplicationContext(),screenWidth+""+screenHeight,Toast.LENGTH_LONG).show();
        resizeAppViews();

    }

    private void scaleAnimation(View view){

        mTextAnim = AnimationUtils.loadAnimation(this, R.anim.splash_anim_text);
        mTextAnim.setAnimationListener(this);
        tv.startAnimation(mTextAnim);

    }

    private void alphaAnimation(View view){

        mImgAnim = AnimationUtils.loadAnimation(this, R.anim.splash_anim_image);
        mImgAnim.setAnimationListener(this);
        img.startAnimation(mImgAnim);

    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if(animation.equals(mImgAnim))
            scaleAnimation(tv);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation.equals(mTextAnim)) {

            vt = new VeriTabani(Splash.this);


            eklediklerim = new ArrayList<>();
            eklediklerim = (ArrayList<String>) vt.VeriListele();

            if(Build.VERSION.SDK_INT>=21 ){
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Splash.this);
                Intent intent = new Intent(Splash.this, GirisSayfasi.class);
                startActivity(intent,options.toBundle());
            }else {
                Intent intent = new Intent(Splash.this, GirisSayfasi.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void spanText(){

        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/fofbb_reg.ttf");
        tv = findViewById(R.id.tv);

        int sizeText = setTextViewSize();
        tv.setTextSize(32);
        tv.setTypeface(font,Typeface.BOLD);
        tv.setText("Sözlük");
        //yourTextView.setText(sb);

    }

    private void resizeAppViews(){

        float sabitDpi = 240.0f;
        float sabitWidth = 480.0f;
        float sabitHeight = 800.0f;

        float w = 1.0f;
        float h = 1.0f;
        float k = 1.0f;
        float x = 1.0f;
        float y = 1.0f;

        Resources r = getResources();

        switch (getDpi()){
            case 120:
                k = (getDpi()/sabitDpi);
                break;
            case 160:
                k = (getDpi()/sabitDpi);
                break;
            case 213:
                k = (getDpi()/sabitDpi);
                break;
            case 240:
                k = (getDpi()/sabitDpi);
                break;
            case 320:
                k = (getDpi()/sabitDpi);
                break;
            case 480:
                k = (getDpi()/sabitDpi);
                break;
            case 640:
                k = (getDpi()/sabitDpi);
                break;
        }

        x = getScreenWidth() /sabitWidth;
        y= getScreenHeight()/sabitHeight;

        w = x/k;
        h = y/k;

        //Toast.makeText(getApplicationContext(),w+" "+h,Toast.LENGTH_LONG).show();

        ViewGroup.LayoutParams imgLayoutParams = img.getLayoutParams();
        imgLayoutParams.width = (int) (imgLayoutParams.width* w);
        imgLayoutParams.height = (int) (imgLayoutParams.height * h);
        img.setLayoutParams(imgLayoutParams);

    }

    private static int getDpi(){
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    private static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private int setTextViewSize(){
        switch (screenHeight){

            case 1280:
                return 38;
            case 1920:
                return 57;
            case 2560:
                return 76;
            case 2880:
                return 86;
            case 1800:
                return 54;
            case 1600:
                return 48;
        }
        return 24;
    }
}
