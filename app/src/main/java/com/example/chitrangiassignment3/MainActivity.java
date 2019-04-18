package com.example.chitrangiassignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private ImageView ivLogo;
    private TextView tvSubTitle;
    private TextView tvDescription;
    private Button btnShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShop = (Button) findViewById(R.id.btnShop);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvSubTitle = (TextView) findViewById(R.id.tvSubTitle);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);

        this.btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginFirebase.class));
            }
        });
    }
}
