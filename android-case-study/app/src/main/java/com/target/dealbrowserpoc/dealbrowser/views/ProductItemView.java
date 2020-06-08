package com.target.dealbrowserpoc.dealbrowser.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.dealbrowser.viewModels.ProductItemViewModel;
import com.target.dealbrowserpoc.dealbrowser.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yashwantsingh on 21/05/18.
 */

public class ProductItemView extends RelativeLayout {

    public interface Listener {
        void actionPerformed(ProductItemViewModel productItemViewModel);
    }

    ProductItemViewModel productItemViewModel;
    Context context;

    @BindView(R.id.product)
    RelativeLayout layoutProduct;

    @BindView(R.id.product_image)
    ImageView productImageView;

    @BindView(R.id.product_desc)
    TextView productTextView;

    @BindView(R.id.product_price)
    TextView priceTextView;

    @BindView(R.id.product_aile)
    TextView productAile;

    Listener listener;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public ProductItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public void bindTo(ProductItemViewModel productItemViewModel) {
        this.productItemViewModel = productItemViewModel;
    }

    public void setData() {
        productItemViewModel.getProduct().observeForever(product -> {
            Picasso.get()
                    .load(product.getImage())
                    .placeholder(getResources().getDrawable(R.drawable.img_loading))
                    .into(productImageView);
            productTextView.setText(product.getTitle());
            priceTextView.setText(product.getSalePrice() == null ? product.getPrice() : product.getSalePrice());
            productAile.setText(product.getAisle());
        });
        layoutProduct.setOnClickListener(v -> listener.actionPerformed(productItemViewModel));
    }

    public void setProductItemClickListener(Listener productItemClickListener) {
        this.listener = productItemClickListener;
    }
}
