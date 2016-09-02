package test.app.mymultilangkeyboard.test;

import android.inputmethodservice.KeyboardView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.List;

import test.app.mymultilangkeyboard.R;
import test.app.mymultilangkeyboard.keyboard.Constants;
import test.app.mymultilangkeyboard.keyboard.KeyboardBinderHelper;
import test.app.mymultilangkeyboard.keyboard.MyKeyboardFragment;
import test.app.mymultilangkeyboard.keyboard.interfaces.KeyboardHolder;

public class Main2Activity extends AppCompatActivity implements KeyboardHolder {

    private FragmentManager fm;
    private int langId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setLangId(Constants.LANG_ES);

        // hide sys keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        fm = getSupportFragmentManager();

        // add the keyboard
        MyKeyboardFragment kbrdFrag = (MyKeyboardFragment) fm.findFragmentByTag("keyboard");
        if (kbrdFrag == null) {
            kbrdFrag = new MyKeyboardFragment();
            // bind the edits from all fragments with edits here
        }
        fm.beginTransaction().replace(R.id.keyboard_holder, kbrdFrag, "keyboard").commit();

        // add the other fragments
        EditsFragment editsFragment = (EditsFragment) fm.findFragmentByTag("contents");
        if (editsFragment == null) {
            editsFragment = new EditsFragment();
            fm.beginTransaction().replace(R.id.content_holder, editsFragment, "contents").commit();
        }

        // hide the keyboard
        fm.beginTransaction().hide(kbrdFrag).commit();
    }

    @Override
    public void onBackPressed() {
        if(isKeyboardVisible()) {
            toggleKeyboardVisible(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean isKeyboardVisible() {
        MyKeyboardFragment keyboardFragment = (MyKeyboardFragment) fm.findFragmentByTag("keyboard");
        if(keyboardFragment != null) {
            return keyboardFragment.isVisible();
        }
        return false;
    }

    @Override
    public void bindKeyboardToEdits(List<EditText> edits, int langId) {
        // bind the keyboard to the mEdits
        (new KeyboardBinderHelper(this, langId, edits)).bindEditsToKeyboard();
    }

    @Override
    public void toggleKeyboardVisible(boolean visible) {
        MyKeyboardFragment keyboardFragment = (MyKeyboardFragment) fm.findFragmentByTag("keyboard");
        if (keyboardFragment != null) {
            if (keyboardFragment.isVisible()) {
                if (!visible) {
                    fm.beginTransaction().hide(keyboardFragment).commit();
                }
            } else { // keyboard invisible
                if(visible) { // should make it visible
                    fm.beginTransaction().show(keyboardFragment).commit();
                }
            }
        }
    }

    @Override
    public KeyboardView getKeyboardView() {
        MyKeyboardFragment keyboardFragment = (MyKeyboardFragment) fm.findFragmentByTag("keyboard");
        if (keyboardFragment != null) {
            return keyboardFragment.getKeyboard().getKeyboardView();
        }
        return null;
    }

    /**
     *
     * @return
     */
    public int getLangId() {
        return langId;
    }

    /**
     *
     * @param langId
     */
    public void setLangId(int langId) {
        this.langId = langId;
    }
}