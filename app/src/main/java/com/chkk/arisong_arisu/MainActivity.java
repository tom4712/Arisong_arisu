package com.chkk.arisong_arisu;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.chkk.arisong_arisu.Fragment.HomeFragment;
import com.chkk.arisong_arisu.Fragment.SearchFragment;
import com.chkk.arisong_arisu.Fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.GoHome:

                    HomeFragment fragment1 = new HomeFragment();
                    FragmentTransaction fragmentTransactions1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransactions1.replace(R.id.frame_layout, fragment1);
                    fragmentTransactions1.commit();
                    return true;
                case R.id.GoSetting:

                    SettingFragment fragment2 = new SettingFragment();
                    FragmentTransaction fragmentTransactions2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransactions2.replace(R.id.frame_layout, fragment2);
                    fragmentTransactions2.commit();
                    return true;
                case R.id.GoSearch:

                    SearchFragment fragment3 = new SearchFragment();
                    FragmentTransaction fragmentTransactions3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransactions3.replace(R.id.frame_layout, fragment3);
                    fragmentTransactions3.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HomeFragment fragment1 = new HomeFragment();
        FragmentTransaction fragmentTransactions1 = getSupportFragmentManager().beginTransaction();
        fragmentTransactions1.replace(R.id.frame_layout, fragment1);
        fragmentTransactions1.commit();
    }
}
