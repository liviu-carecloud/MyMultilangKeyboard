package test.app.mymultilangkeyboard.keyboard;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.app.mymultilangkeyboard.R;

/**
 * Supporting fragment for MyKeyboard
 * todo: finish it
 */
public class MyKeyboardFragment extends Fragment {

    // hash map with references to all mEdits
    private MyKeyboard mKeyboard; // the keyboard
    private int mLangId = Constants.LANG_EN; // keyboard language id; en by default

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        KeyboardView view = (KeyboardView) inflater.inflate(R.layout.keyboard, container, false);
        mKeyboard = new MyKeyboard(getActivity(), view, mLangId);
        return view;
    }

    public MyKeyboard getKeyboard() {
        return mKeyboard;
    }

    public void setKeyboard(int langId) {
        mLangId = langId;
    }
}