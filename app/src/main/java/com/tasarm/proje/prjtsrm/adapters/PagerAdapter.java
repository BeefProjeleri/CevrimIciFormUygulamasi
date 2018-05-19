package com.tasarm.proje.prjtsrm.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.tasarm.proje.prjtsrm.fragments.MyComment;
import com.tasarm.proje.prjtsrm.fragments.MyLike;
import com.tasarm.proje.prjtsrm.fragments.MyShared;

/**
 * Created by User on 08.03.2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int number;
    String isim;
    public PagerAdapter(FragmentManager fm, int number,String isim) {
        super(fm);
        this.number=number;
        this.isim=isim;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                MyLike myLike=new MyLike();
                myLike.degis(isim);
                return myLike;
            case 1:
                MyShared myShared=new MyShared();
                myShared.degis1(isim);
                return myShared;
            case 2:
                MyComment myComment=new MyComment();
                myComment.degis2(isim);
                return myComment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return number;
    }
}
