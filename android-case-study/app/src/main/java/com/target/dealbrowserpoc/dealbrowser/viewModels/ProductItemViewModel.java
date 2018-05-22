package com.target.dealbrowserpoc.dealbrowser.viewModels;

import android.arch.lifecycle.MutableLiveData;

import com.target.dealbrowserpoc.dealbrowser.entities.deals.Product;

/**
 * Created by yashwantsingh on 21/05/18.
 */

public class ProductItemViewModel {
    private MutableLiveData<Product> product = new MutableLiveData<>();

    public void setProduct(Product product) {
        this.product.setValue(product);
    }

    public MutableLiveData<Product> getProduct() {
        return product;
    }
}
