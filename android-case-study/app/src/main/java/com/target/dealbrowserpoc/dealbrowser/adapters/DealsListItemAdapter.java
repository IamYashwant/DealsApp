package com.target.dealbrowserpoc.dealbrowser.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.target.dealbrowserpoc.dealbrowser.views.ProductItemView;
import com.target.dealbrowserpoc.dealbrowser.viewModels.ProductItemViewModel;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.entities.deals.Product;

import java.util.List;

/**
 * Created by yashwantsingh on 21/05/18.
 */

public class DealsListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> productList = null;
    private ProductItemView.Listener productItemClickListener;

    private class ProductItemViewHolder extends RecyclerView.ViewHolder {

        private ProductItemView view;

        ProductItemViewHolder(ProductItemView itemView) {
            super(itemView);
            view = itemView;
        }

        public void setViewModel(ProductItemViewModel viewModel, ProductItemView.Listener listener) {
            view.bindTo(viewModel);
            view.setProductItemClickListener(listener);
            view.setData();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductItemViewHolder((ProductItemView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ProductItemViewHolder) holder).setViewModel(new ProductItemViewModel(productList.get(position)),productItemClickListener);
    }

    @Override
    public int getItemCount() {
        if(productList == null)
            return 0;
        else
            return productList.size();
    }

    public void setProductList(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    public void setProductItemClickListener(ProductItemView.Listener listener){
        productItemClickListener = listener;
    }
}
