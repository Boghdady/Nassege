package com.index.Nassege;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.KeyEvent;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.index.Nassege.Activities.AboutUs;
import com.index.Nassege.Activities.ContactUS;
import com.index.Nassege.Activities.OurServices;
import com.index.Nassege.Adapter.ViewPagerAdapter;
import com.index.Nassege.helper.CustomTypefaceSpan;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    private static long time=0;
    TextView txtToolbarTitle;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    CirclePageIndicator indicator;
    CircleButton btnOtherServices , btnConsltationServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ChangeMenueFont();

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);
        indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new PagerVeiwTimerSwip() , 2000 , 4000);
    }





    // to change images in ViewPager Auto
    public class PagerVeiwTimerSwip extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
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


//-------------------------------------------------------------------------------------------------------------------

    // change font for navigation Drawer

    private void ChangeMenueFont(){
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
    }
    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/flat_regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    public void text_title(View view) {

    }
//---------------------------------------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//-----------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     if (id == R.id.Register) {

         txtToolbarTitle.setText("تسجيل جديد");
         //getSupportActionBar().setTitle(" تسجيل جديد");
       }
      else  if (id == R.id.myAcount) {

         txtToolbarTitle.setText("حسابى");
        // getSupportActionBar().setTitle(" حسابى");
        }
      else  if (id == R.id.login) {

         txtToolbarTitle.setText("تسجل الدخول");
        // getSupportActionBar().setTitle(" تسجل الدخول");
        }
        else if(id == R.id.cart) {

            txtToolbarTitle.setText("سلة الشراء");
           // getSupportActionBar().setTitle(" سلة الشراء");
        }


        return super.onOptionsItemSelected(item);
    }

//--------------------------------------------------------------------------------------------------------------------
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Nav_home) {

            txtToolbarTitle.setText(getString(R.string.title_main));



        } else if (id == R.id.Nav_OurServices) {
            txtToolbarTitle.setText(getString(R.string.title_our_services));



        }
        else if (id == R.id.Nav_About_us) {

            txtToolbarTitle.setText(getString(R.string.title_about_us));

        } else if (id == R.id.Nav_Contact_Us) {

            txtToolbarTitle.setText(getString(R.string.title_contact_us));
        }
        else if(id == R.id.nav_send){
            String[] to = {"admin@ksa-2.net"};
            Intent sendemail = new Intent(Intent.ACTION_SEND);
            sendemail.putExtra(Intent.EXTRA_EMAIL, to);
            sendemail.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            sendemail.putExtra(Intent.EXTRA_TEXT, "السلام عليكم ورحمة الله وبركاته عندى استفسار وهو :");
            sendemail.setType("message/rfc822");
            if (sendemail.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(sendemail, "Send Email"));
            }
        }
        else if (id == R.id.nav_sharee) {

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name) + "\n" + "https://play.google.com/store/apps/details?id=com.index.ksaappp");
            if (share.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(share, "مشاركة التطبيق"));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//----------------------------------------------------------------------------------------------------------
    // check internet connection
private boolean haveNetworkConnection() {
    boolean haveConnectedWifi = false;
    boolean haveConnectedMobile = false;

    ConnectivityManager cm = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
    for (NetworkInfo ni : netInfo) {
        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
            if (ni.isConnected())
                haveConnectedWifi = true;
        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
            if (ni.isConnected())
                haveConnectedMobile = true;
    }
    return haveConnectedWifi || haveConnectedMobile;
}

//---------------------------------------------------------------------------------------------------




}
