package com.example.chitrangiassignment3;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {

    ProductFragment.OnListItemListener itemListener = new ProductFragment.OnListItemListener() {
        @Override
        public void onItem(ProductItem item) {

            int orientation = getResources().getConfiguration().orientation;

            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                DisplayFragment displayFragment = new DisplayFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", item.title);
                bundle.putString("details", item.details);
                bundle.putInt("image", item.image);
                bundle.putInt("amount", item.amount);
                displayFragment.setArguments(bundle);
                updateFragment(displayFragment, R.id.fragmentDisplay);
            } else {

                Intent intent = new Intent(ShopActivity.this, DisplayActivity.class);
                intent.putExtra("title", item.title);
                intent.putExtra("details", item.details);
                intent.putExtra("image", item.image);
                intent.putExtra("amount", item.amount);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ProductFragment productFragment = new ProductFragment();
        int orientation = getResources().getConfiguration().orientation;
        updateFragment(productFragment, R.id.fragmentProducts);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            updateFragment(new EmptyFragment(), R.id.fragmentDisplay);
        }

    }

    public void updateFragment(Fragment fragment, int fragment_id) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragment_id, fragment);
        fragmentTransaction.commit();

    }
}
