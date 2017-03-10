package com.askme.askmeassignment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.askme.askmeassignment.entity.ItemEntity;
import com.askme.askmeassignment.fragment.ImageFragment;

import java.util.List;

/**
 * Created by sandeeplabs108 on 28/05/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private List<ItemEntity> itemEntities;
    public PagerAdapter(FragmentManager fm, List<ItemEntity> itemEntities) {
        super(fm);
        this.itemEntities = itemEntities;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return ImageFragment.newInstance(itemEntities.get(position).getImage());
    }

    @Override
    public int getCount() {
        return itemEntities.size();
    }

}

