package com.companyname.easeshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.easeshop.data.VendorEntity;
import com.companyname.easeshop.databinding.VendorsListItemBinding;

import java.util.List;

public class VendorsAdapter extends RecyclerView.Adapter<VendorsAdapter.VendorsViewHolder> {

    private final List<VendorEntity> vendors;
    private View.OnClickListener clickListener;

    public VendorsAdapter(List<VendorEntity> vendors) {
        this.vendors = vendors;
    }

    @NonNull
    @Override
    public VendorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VendorsViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        VendorsListItemBinding mainView = VendorsListItemBinding.inflate(inflater, parent, false);
        viewHolder = new VendorsViewHolder(mainView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VendorsViewHolder holder, int position) {
        final VendorEntity vendor = vendors.get(position);
        holder.layoutBinding.vendorName.setText(vendor.getVendorName());
    }

    @Override
    public int getItemCount() {
        return vendors.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class VendorsViewHolder extends RecyclerView.ViewHolder {
        VendorsListItemBinding layoutBinding;

        public VendorsViewHolder(VendorsListItemBinding binding) {
            super(binding.getRoot());
            this.layoutBinding = binding;
            binding.getRoot().setOnClickListener(clickListener);
        }
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        clickListener = itemClickListener;
    }
}
