package com.target.dealbrowserpoc.dealbrowser.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.target.dealbrowserpoc.dealbrowser.adapters.DealsListItemAdapter;
import com.target.dealbrowserpoc.dealbrowser.activities.ProductDetailActivity;
import com.target.dealbrowserpoc.dealbrowser.entities.deals.Product;
import com.target.dealbrowserpoc.dealbrowser.utils.ListUtils;
import com.target.dealbrowserpoc.dealbrowser.viewModels.ProductItemViewModel;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.application.DealApplication;
import com.target.dealbrowserpoc.dealbrowser.entities.deals.RealDeal;
import com.target.dealbrowserpoc.dealbrowser.services.DealsService;

import java.util.Comparator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yashwantsingh on 21/05/18.
 */

public class DealsListFragment extends Fragment {

    @Inject
    DealsService dealsService;

    @BindView(R.id.product_container_recycler)
    RecyclerView productContainerRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private DealsListItemAdapter dealsListItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DealApplication.get(getActivity()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_deal_list, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Grid view for products
        productContainerRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        //Divider lines
        productContainerRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
        productContainerRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));

        dealsListItemAdapter = new DealsListItemAdapter();
        dealsListItemAdapter.setProductItemClickListener(this::openProductDetails);

        progressBar.setVisibility(View.VISIBLE);
        //Making get deals call
        getRealDeals();
    }

    private void getRealDeals() {
        Call<RealDeal> realDealCall = dealsService.getDeals();
        realDealCall.enqueue(new Callback<RealDeal>() {
            @Override
            public void onResponse(Call<RealDeal> call, Response<RealDeal> response) {
                dealsListItemAdapter.setProductList(ListUtils.sort(response.body().getData(), new Comparator<Product>() {
                    @Override
                    public int compare(Product product, Product t1) {
                        return product.getPrice().compareTo(t1.getPrice());
                    }
                }));
                productContainerRecyclerView.setAdapter(dealsListItemAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RealDeal> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void openProductDetails(ProductItemViewModel productItemViewModel) {
        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra("Product", productItemViewModel.getProduct().getValue());
        startActivity(intent);
    }
}
