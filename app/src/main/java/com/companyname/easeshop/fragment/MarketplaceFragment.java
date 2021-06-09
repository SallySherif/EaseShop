package com.companyname.easeshop.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.companyname.easeshop.adapter.VendorsAdapter;
import com.companyname.easeshop.data.VendorEntity;
import com.companyname.easeshop.databinding.FragmentMarketPlaceBinding;
import com.companyname.easeshop.R;

import java.util.ArrayList;
import java.util.Objects;

public class MarketplaceFragment extends Fragment {

    private FragmentMarketPlaceBinding binding;
    private NavController navController;
    private final ArrayList<VendorEntity> vendors = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        navController = ((NavHostFragment) Objects.requireNonNull(requireActivity().getSupportFragmentManager().
                findFragmentById(R.id.nav_host_fragment))).getNavController();
        prepareVendorsData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMarketPlaceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeVendorsRecyclerView();
    }

    private void prepareVendorsData() {
        VendorEntity vendor = new VendorEntity();
        vendor.setVendorName("Supermarkets");
        vendors.add(vendor);
        vendor = new VendorEntity();
        vendor.setVendorName("Pharmacies");
        vendors.add(vendor);
        vendor = new VendorEntity();
        vendor.setVendorName("Butchery & BBQ");
        vendors.add(vendor);
        vendor = new VendorEntity();
        vendor.setVendorName("Houseware");
        vendors.add(vendor);
    }

    private void initializeVendorsRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(),
                RecyclerView.VERTICAL, false);
        binding.allVendorsRecyclerView.setLayoutManager(layoutManager);
        VendorsAdapter vendorsAdapter = new VendorsAdapter(vendors);
        binding.allVendorsRecyclerView.setAdapter(vendorsAdapter);
        vendorsAdapter.setOnItemClickListener(v ->
                navController.navigate(R.id.action_marketplaceFragment_to_productsFragment));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        SearchManager searchManager = (SearchManager) requireContext().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}