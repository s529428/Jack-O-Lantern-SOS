package com.example.jackolanternsos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SaveDialogFragment extends DialogFragment {

    public interface SaveCallBack {
        void cancelBTNpressed();
        void selectedOption(int optionIndex);
    }

    private SaveCallBack activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (SaveCallBack)context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Where would you like to save your face to?");
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //User changed their mind about saving
                activity.cancelBTNpressed();
            }
        });
        //Add the options
        builder.setItems(R.array.saveOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.selectedOption(i);
            }
        });
        return builder.create();
    }
}