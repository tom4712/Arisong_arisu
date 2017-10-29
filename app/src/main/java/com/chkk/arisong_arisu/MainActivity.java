package com.chkk.arisong_arisu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.chkk.arisong_arisu.Fragment.HomeFragment;
import com.chkk.arisong_arisu.Fragment.SearchFragment;
import com.chkk.arisong_arisu.Fragment.SettingFragment;

public class MainActivity extends AppCompatActivity  {
    int pressTime;
    boolean chkboolean = false;
    private GPSInfo gpsInfo;
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


//                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                    showProgressDialog("아리수를 찾는 중입니다.");
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
        onBackPressed();
        chkboolean = true;
        HomeFragment fragment1 = new HomeFragment();
        FragmentTransaction fragmentTransactions1 = getSupportFragmentManager().beginTransaction();
        fragmentTransactions1.replace(R.id.frame_layout, fragment1);
        fragmentTransactions1.commit();
        gpsInfo = new GPSInfo(this);
        gpsInfo.checkPermission();
    }
    public ProgressDialog progressDialog;

    public void showProgressDialog(String Msg){
        if (progressDialog == null){
            //다이얼로그가 생성되어있지 않을경우
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(Msg);
            //받은 String a를 메시지로넣기
            progressDialog.setIndeterminate(true);
        }
        progressDialog.show();
    }
    public void hideProgressDialog(){
        if (progressDialog != null && progressDialog.isShowing()) {
            //Progress다이얼로그가 켜져있거나 (Not Null) 다이얼로그가 보여지고있는경우
            progressDialog.dismiss();
        }
    }
    public void ProgaressDialogoff(){
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {

        if (chkboolean) {
            if (pressTime == 0) {
                Toast.makeText(this, "한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
                pressTime = (int) System.currentTimeMillis();

            } else {
                int seconds = (int) (System.currentTimeMillis() - pressTime);
                if (seconds > 1500) {
                    pressTime = 0;
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

}
