package com.example.onul_app;

import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment=new HomeFragment();
    ShareDiaryFragment shareDiaryFragment=new ShareDiaryFragment();
    MypageFragment mypageFragment=new MypageFragment();

    FragmentManager fragmentManager=getSupportFragmentManager();
    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("onul","제발");
        fragmentTransaction.add(R.id.frameLayout, homeFragment).commit();
        Log.e("name","first");
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new NavigationSelectedListener());

    }
    class NavigationSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.e("name1","navigation_homein");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commitAllowingStateLoss();
                    Log.e("name2","navigation_homeout");
                    break;
                case R.id.navigation_share_diary:
                    Log.e("name3","navigation_share_diaryin");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ShareDiaryFragment()).commitAllowingStateLoss();
                    Log.e("name4","navigation_share_diaryout");
                    break;
                case R.id.navigation_mypage:
                    Log.e("name5","navigation_mypagein");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MypageFragment()).commitAllowingStateLoss();
                    Log.e("name6","navigation_mypageout");
                    break;
            }
            return true;
        }
    }


}





