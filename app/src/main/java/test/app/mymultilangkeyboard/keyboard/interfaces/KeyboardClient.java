package test.app.mymultilangkeyboard.keyboard.interfaces;

import android.widget.EditText;

import java.util.List;

/**
 * Created by lsoco_user on 9/1/2016.
 */
public interface KeyboardClient {
    List<EditText> getViews();
    void attachKeyboardToEdits(List<EditText> editTexts);
}
