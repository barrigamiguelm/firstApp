package com.miguelbarriga.firstapp;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        ImageView mGirl = findViewById(R.id.fondo);

        Glide.with(this)
                .load(R.drawable.fondologinbuena)
                .apply(bitmapTransform(new BlurTransformation(60)))
                .into(mGirl);

    }


    public void openLogin(View v) {
        Intent intent = new Intent(Signup.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
