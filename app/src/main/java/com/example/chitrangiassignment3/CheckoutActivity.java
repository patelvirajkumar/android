package com.example.chitrangiassignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.ImageView ivBook;
    private android.widget.TextView tvTitle;
    private android.widget.TextView tvAmount;
    private android.widget.ImageView ivRemove;
    private android.widget.TextView tvNoItems;
    private android.widget.ImageView ivAdd;
    private android.widget.EditText etName;
    private android.widget.RadioGroup rgPayMode;
    private android.widget.Button btnPay;

    int numberOfItems = 1;
    ProductItem productItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        this.tvAmount = (TextView) findViewById(R.id.tvAmount);
        this.btnPay = (Button) findViewById(R.id.btnPay);
        this.rgPayMode = (RadioGroup) findViewById(R.id.rgPayMode);
        this.etName = (EditText) findViewById(R.id.etName);
        this.ivAdd = (ImageView) findViewById(R.id.ivAdd);
        this.tvNoItems = (TextView) findViewById(R.id.tvNoItems);
        this.ivRemove = (ImageView) findViewById(R.id.ivRemove);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.ivBook = (ImageView) findViewById(R.id.ivBook);


        Intent intent = getIntent();
        if (intent != null) {
            productItem = new ProductItem(intent.getIntExtra("image", 0), intent.getStringExtra("title"), intent.getStringExtra("details"), intent.getIntExtra("amount", 0));
            tvTitle.setText(productItem.title);
            tvNoItems.setText(String.valueOf(numberOfItems));
            tvAmount.setText(String.format(Locale.CANADA, "$%d", productItem.amount * numberOfItems));
            ivBook.setImageResource(productItem.image);

            rgPayMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    String payMode = getString(R.string.cash);
                    if (checkedId == R.id.rbVisa) {
                        payMode = getString(R.string.visa);
                    } else if (checkedId == R.id.rbDebit) {
                        payMode = getString(R.string.debit);
                    }
                    btnPay.setText(String.format(Locale.CANADA, getString(R.string.pay), payMode));
                }
            });
            btnPay.setText(String.format(Locale.CANADA, getString(R.string.pay), getString(R.string.visa)));

            btnPay.setOnClickListener(this);
            ivAdd.setOnClickListener(this);
            ivRemove.setOnClickListener(this);

        } else {
            onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnPay) {

            String name = etName.getText().toString();
            if (name.isEmpty()) {
                etName.setError(getString(R.string.cannotBeEmpty));
                etName.requestFocus();
                return;
            }

            Toast.makeText(this, getString(R.string.payDone), Toast.LENGTH_SHORT).show();
            finish();

        } else if (id == R.id.ivAdd) {

            if (numberOfItems < 10) {
                numberOfItems = numberOfItems + 1;
                tvNoItems.setText(String.valueOf(numberOfItems));
                tvAmount.setText(String.format(Locale.CANADA, "$%d", productItem.amount * numberOfItems));
            } else {
                Toast.makeText(this, getString(R.string.maxLimit), Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.ivRemove) {
            if (numberOfItems > 1) {
                numberOfItems = numberOfItems - 1;
                tvNoItems.setText(String.valueOf(numberOfItems));
                tvAmount.setText(String.format(Locale.CANADA, "$%d", productItem.amount * numberOfItems));
            } else {
                Toast.makeText(this, getString(R.string.minLimit), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
