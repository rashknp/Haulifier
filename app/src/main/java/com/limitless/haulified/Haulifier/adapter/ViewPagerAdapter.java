package com.limitless.haulified.Haulifier.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MenuItem;

import com.limitless.haulified.Haulifier.fragment.Bookings;
import com.limitless.haulified.Haulifier.fragment.Deliveries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rashmi on 04-Sep-17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    MenuItem item3;
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, MenuItem item3) {
        super(fm);
        this.fragments = fragments;
        this.item3 = item3;
    }
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }
    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                Deliveries deliveries=new Deliveries();
//                item3.setVisible(false);
                return "Deliveries";
            case 1:
                Bookings bookings=new Bookings();
//                item3.setVisible(true);
                return "Bookings";

        }
        return super.getPageTitle(position);


    }
}