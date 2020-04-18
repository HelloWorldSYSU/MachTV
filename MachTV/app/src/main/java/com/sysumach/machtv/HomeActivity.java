package com.sysumach.machtv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.sysumach.machtv.base.BaseActivity;
import com.sysumach.machtv.base.BaseFragment;

public class HomeActivity extends BaseActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentManager mFragmentManager;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Fragment mResultFragment;
    private MenuItem mMenuItem;


    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.drawable.ic_drawer_home);
        setTitle("首页");

        drawerLayout = bindView(R.id.drawer_layout);
        navigationView = bindView(R.id.navigation_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);

        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        mMenuItem = navigationView.getMenu().getItem(0);
        mMenuItem.setChecked(true);
        initFragment();
        handleNavigationView();

        switchFragment(HomeFragment.class);
        mToolbar.setTitle(R.string.Home_title);
    }

    private void initFragment() {
        mResultFragment = FragmentManagerWrapper.getInstance().createFragment(HomeFragment.class, true);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_main_content, mResultFragment);
        mFragmentManager.beginTransaction().commit();
    }

    private void handleNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(mMenuItem != null){
                    mMenuItem.setChecked(false);
                }
                switch (menuItem.getItemId()){
                    case R.id.navigation_item_video:
                        switchFragment(HomeFragment.class);
                        mToolbar.setTitle(R.string.Home_title);
                        break;
                    case R.id.navigation_item_blog:
                        switchFragment(BlogFragment.class);
                        mToolbar.setTitle(R.string.Blog_title);
                        break;
                    case R.id.navigation_item_about:
                        switchFragment(AboutFragment.class);
                        mToolbar.setTitle(R.string.About_title);
                        break;
                        default:
                            break;
                }
                drawerLayout.closeDrawer(Gravity.LEFT);
                mMenuItem = menuItem;
                menuItem.setChecked(true);
                return false;
            }
        });
    }

    private void switchFragment(Class<?> clazz) {
        Fragment fragment = FragmentManagerWrapper.getInstance().createFragment(clazz, true);
        if(fragment.isAdded()){
            mFragmentManager.beginTransaction().hide(mResultFragment).show(fragment).commitAllowingStateLoss();
        }else{
            mFragmentManager.beginTransaction().add(R.id.fl_main_content, fragment).commitAllowingStateLoss();
        }
        mResultFragment = (BaseFragment) fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_home;
    }
}
