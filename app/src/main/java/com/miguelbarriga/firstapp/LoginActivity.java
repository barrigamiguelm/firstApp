package com.miguelbarriga.firstapp;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

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

import jp.wasabeef.glide.transformations.BlurTransformation;


public class LoginActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;

    public Button boton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        boton1 = (Button) findViewById(R.id.boton1);
        lottieAnimationView = findViewById(R.id.anima);
        ImageView cafe = findViewById(R.id.fondo);

        Glide.with(this)
                .load(R.drawable.fondochampis)
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .apply(bitmapTransform(new BlurTransformation(60)))
                .into(cafe);


        Animation animlogin = AnimationUtils.loadAnimation(
                this, R.anim.animlogin);
        lottieAnimationView.startAnimation(animlogin);


    }


    public void openMain(View v) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openSignup(View v) {
        Intent intent = new Intent(LoginActivity.this, Signup.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


}
