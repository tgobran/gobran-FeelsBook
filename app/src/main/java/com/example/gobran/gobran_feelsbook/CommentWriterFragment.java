/*
 * Class: CommentWriterFragment
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Handles the creation of a DialogFragment on screen that will allow the 
 *      editing and creation of comments for emotion records.
 * Rationale:
 *      Seperates the operation of creating comments from specific editText views
 *      in specific activities, allows any class that implements its method to
 *      recieve a comment be able to allow comment editing easily
 *      In this sense it allows for greater modularity, with it being possible
 *      to reuse this code in other projects with commenting.
 * Outstanding Issues:
 *     Few issues are prevalent with the current design due to its relative simplicity
 */

package com.example.gobran.gobran_feelsbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class CommentWriterFragment extends DialogFragment {
    CommentWriterFragment.OnCommentSetListener mCallback;

    //Interface a implementaing class must support, so that the returned comment will be handled 
    public interface OnCommentSetListener {
        public void onCommentSet(String comment);
    }

    //Creates the actual dialog residing within the fragment
    @Override
    public @NonNull Dialog onCreateDialog(Bundle savedInstanceState) {
        
        //Grabs the view containing the comment editor textEdit, places that as the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View commentWriterView = inflater.inflate(R.layout.fragment_comment_writer, null);
 
        //Changes the editText object within the view to present any pre-existing comment
        //Such comments are passed to the commentWriter via an argument
        final EditText commentEditor = commentWriterView.findViewById(R.id.commentWriterFragment_EditComment);
        Bundle commentData = getArguments();
        if (commentData != null) {
            commentEditor.setText(commentData.getString(this.getString(R.string.commentWriterFragment_CommentArgument),""));
        }

        //Builds the dialog using the view provided, sets the positive and negative return buttons to return the sttring or nothing 
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(commentWriterView)
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
        
        //Returns the created dialog with its proper editText and its buttons established
        return dialogBuilder.create();
    }

    //Handles the fragment being attached to an activity, ensures that it knows its parentContext which will recieve the
    //resulting comments that are written
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
