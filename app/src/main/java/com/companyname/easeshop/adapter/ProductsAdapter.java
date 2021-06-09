package com.companyname.easeshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.easeshop.data.ProductEntity;
import com.companyname.easeshop.databinding.ProductsListItemBinding;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private final List<ProductEntity> products;
    private View.OnClickListener clickListener;

    public ProductsAdapter(List<ProductEntity> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductsViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ProductsListItemBinding mainView = ProductsListItemBinding.inflate(inflater, parent, false);
        viewHolder = new ProductsViewHolder(mainView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        final ProductEntity product = products.get(position);
        holder.layoutBinding.productName.setText(product.getProductName());
        holder.layoutBinding.productPrice.setText(product.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {
        ProductsListItemBinding layoutBinding;

        public ProductsViewHolder(ProductsListItemBinding binding) {
            super(binding.getRoot());
            this.layoutBinding = binding;
            binding.getRoot().setOnClickListener(clickListener);
            binding.minusIcon.setOnClickListener(clickListener);
            binding.plusIcon.setOnClickListener(clickListener);
        }
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        clickListener = itemClickListener;
    }
}
