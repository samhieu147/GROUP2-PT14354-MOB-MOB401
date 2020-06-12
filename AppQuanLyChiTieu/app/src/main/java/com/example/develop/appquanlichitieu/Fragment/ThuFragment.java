package com.example.develop.appquanlichitieu.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.develop.appquanlichitieu.Adapter.PageAdapter;
import com.example.develop.appquanlichitieu.R;


public class ThuFragment extends Fragment {
    private ViewPager pager;
    private TabLayout tabLayout;
    FragmentManager fragmentManager;
    PageAdapter paperAdapter;
    private FragmentActivity myContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_thu,container,false);
        pager=view.findViewById(R.id.view_pager);
        tabLayout=view.findViewById(R.id.tab_layout);
        addControl();
        return view;
    }

    private void addControl() {

        fragmentManager=myContext.getSupportFragmentManager();
        paperAdapter=new PageAdapter(fragmentManager);
        pager.setAdapter(paperAdapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(paperAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0)
                {
                    pager.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @Override
    public void onAttach(Activity activity) {
        myContext= (FragmentActivity) activity;
        super.onAttach(activity);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
