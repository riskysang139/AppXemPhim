package com.example.appxemphim;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.appxemphim.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import LichSu.FragmentLichSu;
import NguoiDung.NguoiDungFragment;
import TrangChu.TrangChuFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.mainFragment,new TrangChuFragment()).commit();
        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.trangChu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new TrangChuFragment()).commit();
                        break;
                    case R.id.lichSu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new FragmentLichSu()).commit();
                        break;
                    case R.id.nguoiDung:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new NguoiDungFragment()).commit();
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new TrangChuFragment()).commit();
                        break;

                }
                return true;
            }
        });
    }
}