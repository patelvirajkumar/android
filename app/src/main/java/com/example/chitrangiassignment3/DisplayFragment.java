package com.example.chitrangiassignment3;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {


    public DisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        ImageView ivBook = view.findViewById(R.id.ivBook);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvAmount = view.findViewById(R.id.tvAmount);
        TextView tvDetails = view.findViewById(R.id.tvDetails);
        Button btnCheckout = view.findViewById(R.id.btnCheckout);

        Bundle bundle = getArguments();

        if (bundle != null) {
            final ProductItem productItem = new ProductItem(bundle.getInt("image"), bundle.getString("title"), bundle.getString("details"), bundle.getInt("amount"));

            ivBook.setImageResource(productItem.image);
            tvTitle.setText(productItem.title);
            tvDetails.setText(productItem.details);
            tvAmount.setText(String.format(Locale.CANADA, "$%d", productItem.amount));

            btnCheckout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                    intent.putExtra("title", productItem.title);
                    intent.putExtra("details", productItem.details);
                    intent.putExtra("image", productItem.image);
                    intent.putExtra("amount", productItem.amount);
                    startActivity(intent);
                    if (getActivity() instanceof DisplayActivity){
                        getActivity().finish();
                    }
                }
            });
        }

        return view;
    }

}
