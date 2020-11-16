package com.example.jackolanternsos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class UsernameSaveDialogFragment extends DialogFragment {

    public interface UsernameSaveDialogListener {
        public void onSetUsername(String newUsername);
    }

    private UsernameSaveDialogListener activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (UsernameSaveDialogListener)context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //Inflate and set the layout for the dialog
        //Pass null as the parent view because it's going in the dialog layout
        final View view = inflater.inflate(R.layout.dialog_username,null);
        final EditText username = view.findViewById(R.id.username);
        builder.setView(view);
        builder.setTitle("Please Pick a Username");
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.onSetUsername(username.getText().toString());
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UsernameSaveDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
