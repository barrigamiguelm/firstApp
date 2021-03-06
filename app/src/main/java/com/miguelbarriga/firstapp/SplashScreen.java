package com.miguelbarriga.firstapp;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        //splash-abrir solo

        openApp(true);


        ImageView fondo = findViewById(R.id.fondo);
        ImageView icono = findViewById(R.id.anima);

        lottieAnimationView = findViewById(R.id.anima);

        TextView tv1 = findViewById(R.id.tv1);



        Glide.with(this)
                .load(R.drawable.avocado4)
                .apply(bitmapTransform(new BlurTransformation(40)))
                .into(fondo);







        Animation moveicono = AnimationUtils.loadAnimation(
                this, R.anim.moveicono);
        lottieAnimationView.startAnimation(moveicono);

        Animation moveletra = AnimationUtils.loadAnimation(
                this, R.anim.moveletra);
        tv1.startAnimation(moveletra);


    }


    private void openApp(boolean locationPerission) {
       new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent = new Intent ( SplashScreen
                       .this,LoginActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
               overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
               }
           },2400);
       }
    }




