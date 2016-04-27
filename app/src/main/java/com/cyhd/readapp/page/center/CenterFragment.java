package com.cyhd.readapp.page.center;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyhd.readapp.R;

/**
 * Created by huzhimin on 16/3/28.
 */
public class CenterFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_root,null);
        TextView textView = (TextView) view.findViewById(R.id.fragment_root_type);
        textView.setText("我");
        return view;
    }
}
