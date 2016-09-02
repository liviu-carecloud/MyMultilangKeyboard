package test.app.mymultilangkeyboard.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import test.app.mymultilangkeyboard.R;
import test.app.mymultilangkeyboard.keyboard.KeyboardHolderActivity;
import test.app.mymultilangkeyboard.keyboard.interfaces.KeyboardClient;
import test.app.mymultilangkeyboard.keyboard.interfaces.KeyboardHolder;

/**
 * Created by lsoco_user on 8/31/2016.
 */
public class EditsFragment extends Fragment implements KeyboardClient {

    ArrayList<EditText> mEdits;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edits_fragment, container, false);

        // fetch all edits
        mEdits = new ArrayList<>();
        mEdits.add((EditText) view.findViewById(R.id.edit1));
        mEdits.add((EditText) view.findViewById(R.id.edit2));
        mEdits.add((EditText) view.findViewById(R.id.edit3));
        mEdits.add((EditText) view.findViewById(R.id.edit5));
        mEdits.add((EditText) view.findViewById(R.id.edit6));
        mEdits.add((EditText) view.findViewById(R.id.edit7));

        // bind the keyboard to he views
        attachKeyboardToEdits(mEdits);

        // buttons
        view.findViewById(R.id.button_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((KeyboardHolder)getActivity()).toggleKeyboardVisible(true);
            }
        });

        view.findViewById(R.id.button_hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((KeyboardHolder)getActivity()).toggleKeyboardVisible(false);
            }
        });
        return view;
    }

    @Override
    public List<EditText> getViews() {
        return mEdits;
    }

    @Override
    public void attachKeyboardToEdits(List<EditText> editTexts) {
        ((KeyboardHolder)getActivity()).bindKeyboardToEdits(editTexts, ((KeyboardHolderActivity)getActivity()).getLangId());
    }
}