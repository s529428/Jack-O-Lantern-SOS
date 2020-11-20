package com.example.jackolanternsos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PrivateGalleryDialogFragment extends DialogFragment {

    public interface PrivateGalleryDialogListener {
        //Methods to send the user to where they want to go
        public void goPrintPumpkin();

    }


    private PrivateGalleryDialogListener activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (PrivateGalleryDialogListener)context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Create the Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Add the options to the dialog
        builder.setTitle("What would you like to do?");
        builder.setItems(R.array.privateGalleryOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    if(i==0){
                        activity.goPrintPumpkin();
                    }else{
                        PrivateGalleryDialogFragment.this.getDialog().cancel();
                    }
            }
        });
        //LayoutInflater inflater = requireActivity().getLayoutInflater();


        return builder.create();
    }
}
