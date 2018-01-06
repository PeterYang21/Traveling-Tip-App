package csc214.project3.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import csc214.project3.R;

public class Fragment_Dialog extends android.support.v4.app.DialogFragment{

    private static final String Key_Message = "INFO";

    public Fragment_Dialog(){
        //
    }

    public static Fragment_Dialog newInstance(String prompt){ // factory method

        Fragment_Dialog fragment = new Fragment_Dialog();
        Bundle args = new Bundle();
        args.putString(Key_Message, prompt);
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) { // create a dialog


        Log.d("My Tag", "Dialog is created");

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_prompt_dialog, null);

        Bundle args = getArguments(); // get the bundle
        String prompt = args.getString(Key_Message);

        TextView description = (TextView) view.findViewById(R.id.Dialog_prompt);
        if (args != null) {
            Log.d("my Tag", "setText() is called in Dialog class");

            description.setText(prompt);
        }

        return new AlertDialog.Builder(getActivity()) // build an alert dialog
                .setTitle("\n")
                .setView(view)
                .setPositiveButton(R.string.OK, null)
                .create();
    }
}
