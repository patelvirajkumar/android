package com.example.chitrangiassignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        if (intent != null) {

            DisplayFragment displayFragment = new DisplayFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", intent.getStringExtra("title"));
            bundle.putString("details", intent.getStringExtra("details"));
            bundle.putInt("image", intent.getIntExtra("image", 0));
            bundle.putInt("amount", intent.getIntExtra("amount", 0));
            displayFragment.setArguments(bundle);
            updateFragment(displayFragment, R.id.fragmentDisplay);
        }

    }

    public void updateFragment(Fragment fragment, int fragment_id) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragment_id, fragment);
        fragmentTransaction.commit();

    }
}
