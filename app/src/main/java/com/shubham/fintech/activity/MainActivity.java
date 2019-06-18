package com.shubham.fintech.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shubham.fintech.App.AppController;
import com.shubham.fintech.App.Constants;
import com.shubham.fintech.R;

import com.shubham.fintech.adapter.PagerAdapter;
import com.ypyproductions.utils.ApplicationUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    public ViewPager viewPager;
    private static String TAG = MainActivity.class.getSimpleName();
    FloatingActionButton foting;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private ActionBar actionBar;
    FrameLayout ly;
    ImageView draw_image;

    Runnable refresh;
    public static final String ADMOB_ID_BANNER = "ca-app-pub-9972921565167432/6005897703";
    private FirebaseAuth mAuth;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        foting = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(mToolbar);
        ly = (FrameLayout) findViewById(R.id.container_body);
        ly.setVisibility(View.GONE);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_home));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_friends));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_IPL));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_messages));
        getSupportActionBar().setElevation(0);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);
        //setUpLayoutAdmob();
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                viewPager.setVisibility(View.VISIBLE);
                ly.setVisibility(View.GONE);
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    mToolbar.setTitle(R.string.title_home);

                }
                if (tab.getPosition() == 1) {
                    mToolbar.setTitle(R.string.title_friends);
                }
                if (tab.getPosition() == 2) {
                    mToolbar.setTitle(R.string.title_IPL);
                }
                if (tab.getPosition() == 3) {
                    mToolbar.setTitle(R.string.title_messages);
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);


        foting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;

                FragmentTabHost tabHost3 = (FragmentTabHost) findViewById(android.R.id.tabhost);
                int current_tab = viewPager.getCurrentItem();

                switch (current_tab) {
                    case 0:
                        fragment = new livecricket();

                        break;
                    case 1:
                        fragment = new recent();

                        break;
                    case 2:

                        fragment = new iplschedule();
                        break;
                    case 3:
                        fragment = new Lastestnews();
                        break;

                    default:
                        break;
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.commit();
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Disclaimer) {
            showDdisclaimerdialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
        ly.setVisibility(View.GONE);
        viewPager.setCurrentItem(position);
        viewPager.setVisibility(View.VISIBLE);

    }
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new livecricket();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new recent();
                title = getString(R.string.title_friends);
                break;
            case 2:
                fragment = new iplschedule();
                title = getString(R.string.title_IPL);

                break;
            case 3:
                fragment = new Lastestnews();
                title = getString(R.string.title_messages);

                break;
            case 4:
                showDiaglogAboutUs();
                break;
            case 5:
                rateme();
                break;
            case 6:
                share();
                break;
            case 7:
                more();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }


   /*private void setUpLayoutAdmob() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout_ad);
        if (ApplicationUtils.isOnline(this)) {
            boolean b = true;
            if (b) {
                adView = new AdView(this);
                adView.setAdUnitId(ADMOB_ID_BANNER);
                adView.setAdSize(AdSize.SMART_BANNER);

                layout.addView(adView);
                AdRequest mAdRequest = new AdRequest.Builder().build();
                adView.loadAd(mAdRequest);


            } else {
                layout.setVisibility(View.GONE);
            }
        } else {
            layout.setVisibility(View.GONE);
        }
    }*/
    public void rateme() {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

        //Copy App URL from Google Play Store.
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=%1$s"));

        startActivity(intent);

        Log.i("Code2care ", "Cancel button Clicked!");
    }

    public void more() {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

        //Copy App URL from Google Play Store.
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=%1$s"));

        startActivity(intent);

        Log.i("Code2care ", "Cancel button Clicked!");
    }

    public void share() {

        String message = "https://play.google.com/store/apps/details?id=%1$s";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Share the link of Live Cricket"));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showDiaglogAboutUs() {


        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_about)
                .setTitle("About Us")
                .setNegativeButton("close", null)
                .show();


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showDdisclaimerdialog() {


        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_disclaimer)
                .setTitle("Disclaimer")
                .setNegativeButton("close", null)
                .show();


    }
}