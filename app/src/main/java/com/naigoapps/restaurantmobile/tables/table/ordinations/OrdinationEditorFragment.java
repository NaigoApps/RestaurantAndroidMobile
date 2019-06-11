package com.naigoapps.restaurantmobile.tables.table.ordinations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naigoapps.restaurantmobile.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class OrdinationEditorFragment extends Fragment {

    private ViewPager pager;
    private OrdinationEditorAdapter adapter;

    public OrdinationEditorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ordination_editor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new OrdinationEditorAdapter(getChildFragmentManager());
        pager = view.findViewById(R.id.ordinationEditorPager);
        pager.setAdapter(adapter);
    }

    private class OrdinationEditorAdapter extends FragmentPagerAdapter{


        public OrdinationEditorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    MenuFragment f1 = new MenuFragment();
                    return f1;
                case 1:
                    MenuFragment f2 = new MenuFragment();
                    return f2;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
