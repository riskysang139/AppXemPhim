package com.example.appxemphim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.appxemphim.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import LichSu.FrameLichSu;
import NguoiDung.Frame_NguoiDung;
import TrangChu.FrameTrangChu;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.mainFragment,new FrameTrangChu()).commit();
        getSupportActionBar().hide();
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.trangChu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new FrameTrangChu()).commit();
                        break;
                    case R.id.lichSu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new FrameLichSu()).commit();
                        break;
                    case R.id.nguoiDung:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_NguoiDung()).commit();
                        break;
                }
                return true;
            }
        });
    }
}