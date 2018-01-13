package com.itm.mobile.petlover;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.itm.mobile.util.Session;
import com.itm.mobile.util.UserSessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String LOG_TAG = MainActivity.class.getSimpleName();

    Intent iservice;

    private Session session;

    private UserSessionManager userSession;

    HashMap<String, String> user;

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

   /* private int[] tabIcons = {
            R.drawable.ic_dica_24dp_black,
            R.drawable.ic_info_24dp_black
    };*/

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.mn_action_home:
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    return true;
                case R.id.mn_action_petshop:
                    intent = new Intent(MainActivity.this, MainPetShopActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    return true;
                case R.id.mn_action_petsaude:
                    intent = new Intent(MainActivity.this, MainPetSaudeActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petlover_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_logo_pet_24dp);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicio dos Procedimentos MainMenu
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        viewPager = (ViewPager) findViewById(R.id.viewpager_main);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_main);
        tabLayout.setupWithViewPager(viewPager);

        BottomNavigationView bt_nav_main = (BottomNavigationView) findViewById(R.id.bt_nav_main);
        bt_nav_main.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Attach the page change listener inside the activity
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }

    ViewPagerAdapter adapter;
    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragMainPetBook(), "PETLIFE");
        adapter.addFragment(new FragMainPetFeira(), "PETFEIRA");
        adapter.addFragment(new FragMainPetProcurase(), "PROCURA-SE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            //case R.id.action_main_menu_creditos:
            //intent = new Intent(CondoMainActivity.this, CreditosProjeto.class);
            //startActivity(intent);
            //return true;

            case R.id.action_main_menu_exit:
                alertMainExit();
                return true;
            case R.id.action_main_menu_settings:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        alertMainExit();
    }

    private void alertMainExit() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(R.string.label_sair_app)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        closeApplication(); // Close Application method called
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void closeApplication() {
        /*iservice = new Intent(this, LocationService.class);
        stopService(iservice);*/
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Construct our Intent specifying the Service

    }


}
