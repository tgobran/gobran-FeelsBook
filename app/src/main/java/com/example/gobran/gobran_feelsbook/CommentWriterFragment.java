package com.example.gobran.gobran_feelsbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.Calendar;

public class CommentWriterFragment extends DialogFragment {
    CommentWriterFragment.OnCommentSetListener mCallback;

    public interface OnCommentSetListener {
        public void onCommentSet(String comment);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View commentWriterView = inflater.inflate(R.layout.fragment_comment_writer, null);
        final EditText commentEditor = commentWriterView.findViewById(R.id.commentWriterFragment_EditComment);

        Bundle commentData = getArguments();
        if (commentData != null) {
            commentEditor.setText(commentData.getString("COMMENT",""));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(commentWriterView)
                .setPositiveButton(R.string.commentWriterFragment_ConfirmButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.onCommentSet(commentEditor.getText().toString());

                    }
                })
                .setNegativeButton(R.string.commentWriterFragment_CancelButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.onCommentSet(null);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (CommentWriterFragment.OnCommentSetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnCommentSetListener");
        }
    }
}
