package com.miguelbarriga.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        ImageView mGirl = findViewById(R.id.fondo);

        Glide.with(this)
                .load(R.drawable.tabla2)

//                .load("https://t1.ev.ltmcdn.com/es/posts/4/9/2/que_es_ser_vegano_1294_1_600.jpg")
                .transition(DrawableTransitionOptions.withCrossFade(100))
//                .centerCrop()
//                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.fucsia_200)))
//                .circleCrop()
                .into(mGirl);

    }


    public void openLogin(View v){
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
