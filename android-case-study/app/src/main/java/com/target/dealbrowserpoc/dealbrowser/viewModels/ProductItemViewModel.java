package com.target.dealbrowserpoc.dealbrowser.viewModels;

import com.target.dealbrowserpoc.dealbrowser.entities.deals.Product;

/**
 * Created by yashwantsingh on 21/05/18.
 */

public class ProductItemViewModel {
    private Product product;

    public ProductItemViewModel(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
