package com.miguelbarriga.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;


public class LoginActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;

    public Button boton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        boton1 = (Button)  findViewById(R.id.boton1);
        lottieAnimationView = findViewById(R.id.anima);
        ImageView mBoy = findViewById(R.id.fondo);

        Glide.with(this)
                .load(R.drawable.mesa)


  //              .load("https://t1.ev.ltmcdn.com/es/posts/4/9/2/que_es_ser_vegano_1294_1_600.jpg")
//                .load("https://images.unsplash.com/photo-1565214975484-3cfa9e56f914?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1482&q=80")
                .transition(DrawableTransitionOptions.withCrossFade(100))
//                .centerCrop()
//                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.fucsia_200)))
//                .circleCrop()
                .into(mBoy);


        Animation animlogin = AnimationUtils.loadAnimation(
                this, R.anim.animlogin);
        lottieAnimationView.startAnimation(animlogin);



    }



    public void openMain(View v){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void openSignup(View v){
        Intent intent = new Intent(LoginActivity.this, Signup.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }






}
