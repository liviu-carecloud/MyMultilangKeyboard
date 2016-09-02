package test.app.mymultilangkeyboard.keyboard;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import test.app.mymultilangkeyboard.keyboard.interfaces.KeyboardHolder;

/**
 * Binds edit text to a custom keyboard
 */
public class KeyboardBinderHelper {

    private Activity              mActivity;
    private List<EditText>        mEdits;
    private List<EditTextWrapper> mWrappers;
    private MyKeyboard            mKeyboard;

    public KeyboardBinderHelper(Activity activity, int langId, List<EditText> edits) {
        mActivity = activity;
        KeyboardView kv = ((KeyboardHolder)activity).getKeyboardView();
        mKeyboard = new MyKeyboard(mActivity, kv, langId);
        mEdits = edits;
        mWrappers = new ArrayList<>();
    }

    public void bindEditsToKeyboard() {
        for (int i = 0; i < mEdits.size(); i++) {
            EditText ed = mEdits.get(i);
            mWrappers.add(new EditTextWrapper(mActivity, ed, mKeyboard, ed.getText().toString()));
        }
    }
}
