package com.miguelbarriga.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

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
                //  .load(R.drawable.hipman)

                .load("https://images.pexels.com/photos/1580466/pexels-photo-1580466.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .centerCrop()
//                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.fucsia_200)))
//                .circleCrop()
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




