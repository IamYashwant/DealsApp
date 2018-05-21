package com.target.dealbrowserpoc.dealbrowser.activities;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.entities.deals.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yashwantsingh on 21/05/18.
 */

public class ProductDetailActivity extends Activity {

    @BindView(R.id.product_image)
    ImageView imageProduct;

    @BindView(R.id.product_price)
    TextView txtPrice;

    @BindView(R.id.product_description)
    TextView txtDesc;

    @BindView(R.id.product_sales_price)
    TextView txtSalesPrice;

    @BindView(R.id.product_title)
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_layout);
        ButterKnife.bind(this);
        Product product = getIntent().getExtras().getParcelable("Product");
        setData(product);
    }

    private void setData(Product productDetail) {
        Picasso.with(getApplicationContext())
                .load(productDetail.getImage())
                .placeholder(getResources().getDrawable(R.drawable.img_loading))
                .into(imageProduct);
        txtSalesPrice.setText(productDetail.getSalePrice());
        txtPrice.setText(productDetail.getPrice());
        if(productDetail.getSalePrice() != null)
            txtPrice.setPaintFlags(txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        txtTitle.setText(productDetail.getTitle());
        txtDesc.setText(productDetail.getDescription());
    }
}
