package com.example.i.AndroidDemos.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.i.AndroidDemos.R;
import com.example.i.AndroidDemos.base.BaseActivityWithLL;
import com.example.i.AndroidDemos.ui.fragment.FragmentNoteAndTools;

/***
 * Created by I on 2017/8/26.
 */

public class ActivityNoteAndTools extends BaseActivityWithLL {
    FragmentNoteAndTools fragmentNoteAndTools;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentNoteAndTools = new FragmentNoteAndTools();
        getSupportFragmentManager().beginTransaction().add(R.id.container_ll, fragmentNoteAndTools, "fragment_root").commit();
    }
}
