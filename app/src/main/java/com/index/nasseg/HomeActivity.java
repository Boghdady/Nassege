package com.index.nasseg;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.index.nasseg.Adapter.ViewPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

import at.markushi.ui.CircleButton;

public class HomeActivity extends BaseActivity {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    CirclePageIndicator indicator;
    CircleButton btnOtherServices , btnConsltationServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);
        indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new PagerVeiwTimerSwip() , 2000 , 4000);

        btnOtherServices = (CircleButton)findViewById(R.id.btnOtherServices);
        btnOtherServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this , OtherServicesActivity.class);
                startActivity(i);
            }
        });

        btnConsltationServices = (CircleButton) findViewById(R.id.btnConsltationServices);
        btnConsltationServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this , ConsultationServicesAcivity.class);
                startActivity(i);
            }
        });

    }

    //------------------------------------------------------------------------------------------------------------
    @Override
    int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_main;
    }

//----------------------------------------------------------------------------------------------------------------
    // to change images in ViewPager Auto
    public class PagerVeiwTimerSwip extends TimerTask {

        @Override
        public void run() {

            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }
                    else if (viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }
                    else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------
}
