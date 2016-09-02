package test.app.mymultilangkeyboard.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.app.mymultilangkeyboard.R;

/**
 * Created by lsoco_user on 9/1/2016.
 */
public class SelectLanguageFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sel_lang, container, false);
        return view;
    }
}
