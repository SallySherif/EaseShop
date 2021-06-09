package com.companyname.easeshop.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.companyname.easeshop.R;
import com.companyname.easeshop.databinding.FragmentSplashBinding;

import java.util.Objects;

public class SplashFragment extends Fragment {
    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = ((NavHostFragment) Objects.requireNonNull(requireActivity()
                .getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment))).getNavController();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.companyname.easeshop.databinding.FragmentSplashBinding binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startSplash();
    }

    private void startSplash() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (Objects.requireNonNull(navController.getCurrentDestination()).getId() == R.id.splash_fragment) {
                navController.navigate(R.id.action_splashFragment_to_marketplaceFragment);
            }
        }, 2000);
    }

}