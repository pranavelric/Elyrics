package com.lyrics.elyrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Fragment fragment;
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isNetworkAvailable()) {
            setContentView(R.layout.activity_main);

            sNavigationDrawer = findViewById(R.id.navigationDrawer);
            List<MenuItem> menuItems = new ArrayList<>();
            menuItems.add(new MenuItem("Lyrics Search", R.drawable.m));
            menuItems.add(new MenuItem("Quotes", R.drawable.lamp));

            sNavigationDrawer.setMenuItemList(menuItems);
            fragmentClass = FragmentSong.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
            }

            sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
                @Override
                public void onMenuItemClicked(int position) {
                    System.out.println("Position " + position);

                    switch (position) {
                        case 0: {
                            fragmentClass = FragmentSong.class;
                            break;
                        }
                        case 1: {
                            fragmentClass = QuotesFragment.class;

                            break;
                        }
                        default:
                            fragmentClass = FragmentSong.class;
                            break;

                    }
                    sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                        @Override
                        public void onDrawerOpened() {


                        }

                        @Override
                        public void onDrawerOpening() {

                        }

                        @Override
                        public void onDrawerClosing() {

                            try {
                                fragment = (Fragment) fragmentClass.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (fragment != null) {
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
                            }
                        }

                        @Override
                        public void onDrawerClosed() {

                        }

                        @Override
                        public void onDrawerStateChanged(int newState) {
                        }
                    });
                }
            });


        } else {
            setContentView(R.layout.no_internet);
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void refresh(View v) {
        if (isNetworkAvailable()) {
            finish();
            startActivity(getIntent());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isNetworkAvailable()) {
            setContentView(R.layout.no_internet);
        }
    }


}
