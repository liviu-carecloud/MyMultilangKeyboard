package test.app.mymultilangkeyboard.test;

import test.app.mymultilangkeyboard.R;
import test.app.mymultilangkeyboard.keyboard.KeyboardHolderActivity;

public class Main2Activity extends KeyboardHolderActivity {


    @Override
    public void addContentsFragment() {
        // add the other fragments
        EditsFragment editsFragment = (EditsFragment) fm.findFragmentByTag("contents");
        if (editsFragment == null) {
            editsFragment = new EditsFragment();
            fm.beginTransaction().replace(R.id.content_holder, editsFragment, "contents").commit();
        }

    }
}