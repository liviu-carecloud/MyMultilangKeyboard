package test.app.mymultilangkeyboard.unused;


import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

/**
 * Util to save/restore the contents of the edit view
 */
public class EditTextRestorer {

    public static void restoreTexts(Bundle savedInstanceState, List<EditText> editTexts) {
        // restore
        if (savedInstanceState != null) {
            String baseKey = "key_save_texts_";
            for (int i = 0; i < editTexts.size(); i++) {
                EditText editText = editTexts.get(i);
                if (savedInstanceState.containsKey(baseKey + i)) {
                    editText.setText(savedInstanceState.getString(editText.toString()));
                } else {
                    editText.setText("");
                }
            }
        }
    }

    public static void saveTexts(Bundle outState, List<EditText> editTexts) {
        String baseKey = "key_save_texts_";
        for (int i = 0; i < editTexts.size(); i++) {
            outState.putString(baseKey + i, editTexts.get(i).getText().toString());
        }
    }
}