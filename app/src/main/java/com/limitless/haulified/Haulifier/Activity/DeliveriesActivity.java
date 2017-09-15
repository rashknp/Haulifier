package com.limitless.haulified.Haulifier.Activity;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.adapter.ViewPagerAdapter;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.fragment.Bookings;
import com.limitless.haulified.Haulifier.fragment.Deliveries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rashmi on 04-Sep-17.
 */

public class DeliveriesActivity extends BaseActivity {
    private LinearLayout rlProductDetailActivity;
    Preference prefe;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    int account_id,user_id;
    List<Fragment> fragments=new ArrayList<>();
    private Menu mMenu;
    int position = 0;
    @Override
    public void initialize() {
        rlProductDetailActivity = (LinearLayout) inflater.inflate(R.layout.deliveries, null);
        llBody.addView(rlProductDetailActivity);
        prefe = new Preference(DeliveriesActivity.this);
        account_id=getIntent().getIntExtra("account_id",0);
        user_id=getIntent().getIntExtra("user_id",0);
        fragments = getFragments();
        initilizeView();
        String del=fragments.get(0).toString();

      viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int mPosition, float positionOffset, int positionOffsetPixels) {
//                if(position == 1)
//                {
//                    *//*if(item3 != null)
//                       // item3.setVisible(true);
//                    item3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item) {
//                            Intent i=new Intent(DeliveriesActivity.this,CommisionCalcutaor.class);
//                            i.putExtra("user_id",user_id);
//                            startActivity(i);
//                            return false;
//                        }
//                    });*//*
//                }
//                else
//                {
//                    if(item3 != null)
//                        item3.setVisible(false);
//                }

            }

            @Override
            public void onPageSelected(int mPosition) {
                position = mPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initilizeView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.colorPrimary1));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary1));


    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments,item3);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setMenuTitle("Items Details");
        hideLoader();
        setupViewPager(viewPager);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<Fragment>();
//        fList.add(MyFragment.newInstance("Deliveries"));
        Bundle bundle1 = new Bundle();
        bundle1.putInt("account_id", account_id);
        bundle1.putInt("user_id", user_id);
        Bookings bookings=new Bookings();
        Deliveries myFragment = new Deliveries();
        myFragment.setArguments(bundle1);
        fList.add(myFragment);
        bookings.setArguments(bundle1);
        fList.add(bookings);
        return fList;
    }
}
