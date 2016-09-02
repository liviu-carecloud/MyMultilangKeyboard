package test.app.mymultilangkeyboard.keyboard;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.List;

import test.app.mymultilangkeyboard.R;
import test.app.mymultilangkeyboard.keyboard.interfaces.KeyboardHolder;
import test.app.mymultilangkeyboard.test.EditsFragment;

/**
 * Created by lsoco_user on 9/2/2016.
 */
public abstract class KeyboardHolderActivity extends AppCompatActivity implements KeyboardHolder {

    protected FragmentManager fm;
    protected int             langId;

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

        // abstract part
        addContentsFragment();

        // hide the keyboard
        fm.beginTransaction().hide(kbrdFrag).commit();


    }

    public abstract void addContentsFragment();

    @Override
    public void onBackPressed() {
        if (isKeyboardVisible()) {
            toggleKeyboardVisible(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean isKeyboardVisible() {
        MyKeyboardFragment keyboardFragment = (MyKeyboardFragment) fm.findFragmentByTag("keyboard");
        if (keyboardFragment != null) {
            return keyboardFragment.isVisible();
        }
        return false;
    }

    @Override
    public void bindKeyboardToEdits(List<EditText> edits, int langId) {
        // bind the keyboard to the mEdits
        (new KeyboardBinderHelper(this,
                                  ((MyKeyboardFragment) fm.findFragmentByTag("keyboard")).getKeyboard(),
                                  edits)).bindEditsToKeyboard();
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
                if (visible) { // should make it visible
                    fm.beginTransaction().show(keyboardFragment).commit();
                }
            }
        }
    }

    /**
     * @return
     */
    @Override
    public int getLangId() {
        return langId;
    }

    /**
     * @param langId
     */
    @Override
    public void setLangId(int langId) {
        this.langId = langId;
    }
}
