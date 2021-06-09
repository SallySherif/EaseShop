package com.companyname.easeshop.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.companyname.easeshop.R;
import com.companyname.easeshop.activity.MainActivity;
import com.companyname.easeshop.adapter.ProductsAdapter;
import com.companyname.easeshop.data.ProductEntity;
import com.companyname.easeshop.databinding.FragmentProductsBinding;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    private FragmentProductsBinding binding;
    private final ArrayList<ProductEntity> products = new ArrayList<>();
    final static int[] number = {0};
    private boolean firstClick = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareProductsData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeProductsRecyclerView();
    }

    private void prepareProductsData() {
        ProductEntity product = new ProductEntity();
        product.setId(1);
        product.setProductName("Bakery");
        product.setProductPrice("$ 30");
        products.add(product);
        product = new ProductEntity();
        product.setId(2);
        product.setProductName("Water");
        product.setProductPrice("$ 20");
        products.add(product);
        product = new ProductEntity();
        product.setId(3);
        product.setProductName("Dairy");
        product.setProductPrice("$ 40");
        products.add(product);
        product = new ProductEntity();
        product.setId(4);
        product.setProductName("Soft Drinks");
        product.setProductPrice("$ 80");
        products.add(product);
        product = new ProductEntity();
        product.setId(5);
        product.setProductName("Coffee");
        product.setProductPrice("$ 10");
        products.add(product);
        product = new ProductEntity();
        product.setId(6);
        product.setProductName("Ice Cream");
        product.setProductPrice("$ 50");
        products.add(product);
        product = new ProductEntity();
        product.setId(7);
        product.setProductName("Pet Care");
        product.setProductPrice("$ 70");
        products.add(product);
        product = new ProductEntity();
        product.setId(8);
        product.setProductName("Baby Care");
        product.setProductPrice("$ 90");
        products.add(product);
    }

    private void initializeProductsRecyclerView() {
        ProductsAdapter productsAdapter = new ProductsAdapter(products);
        GridLayoutManager layoutManager = new GridLayoutManager(requireActivity(), 2);
        binding.productsRecyclerView.setLayoutManager(layoutManager);
        binding.productsRecyclerView.setAdapter(productsAdapter);
        productsAdapter.setOnItemClickListener(view -> {
            handleOrdering(view);
            ((MainActivity) requireActivity()).updateCartBadge(number[0]);
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void handleOrdering(View view) {
        switch (view.getId()) {
            case R.id.product_card_view:
                if (firstClick) {
                    firstClick = false;
                    number[0]++;
                    view.findViewById(R.id.minus_icon).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.plus_icon).setVisibility(View.VISIBLE);
                }
                break;
            case R.id.minus_icon:
                if (number[0] != 0) {
                    number[0]--;
                } else {
                    if (view.findViewById(R.id.minus_icon) != null)
                        view.findViewById(R.id.minus_icon).setVisibility(View.GONE);
                    if (view.findViewById(R.id.plus_icon) != null)
                        view.findViewById(R.id.plus_icon).setVisibility(View.GONE);
                }
                break;
            case R.id.plus_icon:
                number[0]++;
                view.findViewById(R.id.minus_icon).setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}