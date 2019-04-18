package com.example.chitrangiassignment3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    public ProductFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            List<ProductItem> productList = new ArrayList<>();
            String details = getString(R.string.randomData);
            productList.add(new ProductItem(R.drawable.brave, "Brave", "Actress and activist McGowan releases her long-awaited memoir chronicling her childhood in a cult and her complicated, painful experiences at the hands of the Hollywood machine." , 100));
            productList.add(new ProductItem(R.drawable.feelfree, "Feel Free", "Iconic White Teeth and Swing Time author Smith trains her elegant critical eye on culture, politics, and herself in this essential collection. " , 150));
            productList.add(new ProductItem(R.drawable.thefemale, "The Female Persuasion", "Once nestled under Faith's wing, though, Greer is forced to reckon with what transforming into the kind of woman she wants to be actually means for the woman she actually is, the things she stands to gain, and the parts of herself she might have to sacrifice." , 200));
            productList.add(new ProductItem(R.drawable.theel, "The Elizas", " As novelist Eliza Fontaine delves into the investigation of her own attempted murder, things quickly take a turn for the meta-textual. Embroiled in situations that eerily mirror her fiction, the lines between reality and Eliza's own imaginings rapidly blur. " , 250));
            productList.add(new ProductItem(R.drawable.thewind, "The Winds Of Winter", "Dare we even dream? We’ve been burned by George R.R. Martin before, and there has been distressingly little word in recent months on the progress of his hotly-anticipated sixth novel in the A Song of Ice and Fire series (on which HBO's mega-hit Game of Thrones is based). Still, until we get confirmation otherwise, we choose to keep believing that (The Winds of) Winter is coming—soon." , 300));
            productList.add(new ProductItem(R.drawable.ihave, "I Have Lost My Way", "A powerful display of empathy and friendship from the #1 New York Times BestSelling author of If I Stay.Around the time that Freya loses her voice while recording her debut album Harun is making plans to run away from home to find the boy that he loves and Nathaniel is arriving in the New York city after a family tragedy leaves him isolated on the outskirts of Washington." , 400));

            ProductViewAdapter adapter = new ProductViewAdapter(productList, itemListener);
            recyclerView.setAdapter(adapter);
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        }
        return view;
    }

    ProductFragment.OnListItemListener itemListener = new ProductFragment.OnListItemListener() {
        @Override
        public void onItem(ProductItem item) {
            ShopActivity shopActivity = (ShopActivity) getActivity();
            if (shopActivity != null) {
                shopActivity.itemListener.onItem(item);
            }
        }
    };

    public interface OnListItemListener {
        void onItem(ProductItem item);
    }
}
