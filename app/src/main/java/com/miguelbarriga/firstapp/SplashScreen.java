package com.miguelbarriga.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        //splash-abrir solo

        openApp(true);


        ImageView fondo = findViewById(R.id.fondo);
        ImageView icono = findViewById(R.id.icono);
        TextView tv1 = findViewById(R.id.tv1);




        Glide.with(this)
                //  .load(R.drawable.hipman)

                .load("https://images.pexels.com/photos/1580466/pexels-photo-1580466.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .centerCrop()
//                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.fucsia_200)))
//                .circleCrop()
                .into(fondo);


        Animation fadein = AnimationUtils.loadAnimation(
                this, R.anim.fadein);
        fondo.startAnimation(fadein);

        Animation moveicono = AnimationUtils.loadAnimation(
                this, R.anim.moveicono);
        icono.startAnimation(moveicono);

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
               startActivity(intent);
               }
           },2000);
       }
    }



