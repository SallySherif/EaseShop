package com.companyname.easeshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.companyname.easeshop.R;
import com.companyname.easeshop.databinding.ActivityMainBinding;
import com.google.android.material.badge.BadgeDrawable;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    private boolean doubleBackToExitPressedOnce = false;
    BadgeDrawable badge;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupNavigationComponent();
        setupBottomNavigationView();
        setupAppBar();
        handleNavigationEvents();
    }

    private void setupNavigationComponent(){
        NavHostFragment navHostFragment = ((NavHostFragment) getSupportFragmentManager().
                findFragmentById(R.id.nav_host_fragment));
        navController = Objects.requireNonNull(navHostFragment).getNavController();
        navController.setGraph(R.navigation.nav_graph);
    }

    private void setupBottomNavigationView(){
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        badge = binding.bottomNavView.getOrCreateBadge(R.id.cart_fragment);
        badge.setBackgroundColor(getResources().getColor(R.color.yellow));
        badge.setBadgeTextColor(getResources().getColor(R.color.colorPrimary));
        badge.setVisible(false);
    }

    private void setupAppBar(){
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.marketplace_fragment,
                R.id.my_orders_fragment, R.id.cart_fragment, R.id.my_account_fragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void handleNavigationEvents() {
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            boolean ShowBottomNav = false;
            boolean ShowAppBar = false;
            if (arguments != null) {
                ShowBottomNav = arguments.getBoolean("ShowBottomNav", false);
                ShowAppBar = arguments.getBoolean("ShowAppBar", true);
            }
            if (ShowBottomNav) {
                binding.bottomNavView.setVisibility(View.VISIBLE);
            } else {
                binding.bottomNavView.setVisibility(View.GONE);
            }
            if (ShowAppBar) {
                Objects.requireNonNull(getSupportActionBar()).show();
            } else {
                Objects.requireNonNull(getSupportActionBar()).hide();
            }
        });
    }

    // update number of products in the cart
    public void updateCartBadge(int number){
        if(number == 0){
            badge.setVisible(false);
        }
        else{
            badge.setVisible(true);
            badge.setNumber(number);
        }
    }
    @Override
    public void onBackPressed() {
        if (Objects.requireNonNull(navController.getCurrentDestination()).getId()== R.id.marketplace_fragment) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getResources().getString(R.string.double_back_press), Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        } else
            super.onBackPressed();
    }
}