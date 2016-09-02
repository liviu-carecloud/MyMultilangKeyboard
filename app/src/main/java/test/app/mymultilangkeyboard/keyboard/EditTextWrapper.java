package test.app.mymultilangkeyboard.keyboard;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import test.app.mymultilangkeyboard.keyboard.interfaces.KeyboardHolder;

import static android.view.MotionEvent.*;

/**
 * Wrapper for an EditText that is used with MyKeyboard
 */
public class EditTextWrapper {

    private static final String LOG_TAG = EditTextWrapper.class.getSimpleName();
    private boolean    alreadyClick;
    private Activity   mActivity;
    private EditText   mTargetEditText;
    private MyKeyboard myKeyboard;
    private int        mEditIndex;

    public EditTextWrapper(Activity activity, EditText editText, int editIndex, MyKeyboard keyboard, String text) {
        mActivity = activity;
        mTargetEditText = editText;
        myKeyboard = keyboard;
        alreadyClick = false;
        mEditIndex = editIndex;

//        setText(text);

        mTargetEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == ACTION_DOWN) {
//                if(!alreadyClick) { // to avoid requesting the focus multiple times
                    myKeyboard.setTargetEditIndex(mEditIndex);
                    view.requestFocus();
                    ((KeyboardHolder) mActivity).toggleKeyboardVisible(true); // todo test if activity is KeyboardHolder
//                    alreadyClick = true;
                }
                else { // already clicked
                    KeyboardHolder kh = ((KeyboardHolder)mActivity);
                    if(!kh.isKeyboardVisible()) {
                        kh.toggleKeyboardVisible(true);
                    }
                }
                return true;
            }
        });

        mTargetEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    Log.v(LOG_TAG, "hasFocus()");
                    mTargetEditText.setSelection(0, mTargetEditText.getText().length());
                } else {
                    // reset alreadyClicked
                    alreadyClick = false;
                    mTargetEditText.setSelection(0);
                }
            }
        });
    }

    public void setText(String text) {
        if(mTargetEditText != null) {
            mTargetEditText.setText(text);
            mTargetEditText.setSelection(0);
        }
    }
}
