/*
 * Class: TimePickerFragment
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Handles the creation of a DialogFragment on screen that will allow the
 *      editing of the time an emotion was created
 * Rationale:
 *      Separates the operation of setting the time from any specific activity,
 *      this allows any class that implements its interface to receive a new
 *      date value set by the user.
 *      Thus it creates greater modularity, with it being possible to reuse
 *      this code in other projects with date selection
 *
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
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {
    TimePickerFragment.OnNewTimeSetListener mCallback;

    // Interface an implementing class must support, so that the newly returned time values will
    // be received
    public interface OnNewTimeSetListener {
        public void onNewTimeSet(int hour, int minute, int second);
    }

    // Creates the actual dialog residing within the fragment, creating a wholly new view for the
    // dialog so that seconds as well as minutes and hours can be set.
    @Override
    public @NonNull Dialog onCreateDialog(Bundle savedInstanceState) {

        // Retrieves any pre-existing time provided in an argument, if none presents current time
        Bundle timeData = getArguments();
        final Calendar c = Calendar.getInstance();
        int hour = timeData.getInt(this.getString(R.string.timePickerFragment_HourArgument),c.get(Calendar.HOUR_OF_DAY));
        int minute = timeData.getInt(this.getString(R.string.timePickerFragment_MinuteArgument),c.get(Calendar.MINUTE));
        int second = timeData.getInt(this.getString(R.string.timePickerFragment_SecondArgument),c.get(Calendar.SECOND));

        // Sets the view used within the dialog, creating a numberPicker for hour, minutes and seconds
        // and setting their limits to those allowed for time, their default times are also set to
        // those found above
        LayoutInflater viewInflater = LayoutInflater.from(getActivity());
        View timePickerView = viewInflater.inflate(R.layout.fragment_time_picker, null);
        final NumberPicker hourPicker = timePickerView.findViewById(R.id.timePickerFragment_HourPicker);
        hourPicker.setMaxValue(23);
        hourPicker.setMinValue(0);
        hourPicker.setValue(hour);
        final NumberPicker minutePicker = timePickerView.findViewById(R.id.timePickerFragment_MinutePicker);
        minutePicker.setMaxValue(59);
        minutePicker.setMinValue(0);
        minutePicker.setValue(minute);
        final NumberPicker secondPicker = timePickerView.findViewById(R.id.timePickerFragment_SecondPicker);
        secondPicker.setMaxValue(59);
        secondPicker.setMinValue(0);
        secondPicker.setValue(second);

        // Builds the dialog using the view provided, sets the positive and negative return buttons
        // to return the time or to simply cancel
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(timePickerView)
                .setPositiveButton(R.string.timePickerFragment_ConfirmButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.onNewTimeSet(hourPicker.getValue(),minutePicker.getValue(),secondPicker.getValue());

                    }
                })
                .setNegativeButton(R.string.timePickerFragment_CancelButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TimePickerFragment.this.getDialog().cancel();
                    }
                });

        // Returns the created dialog with its proper number pickers and its buttons established
        return dialogBuilder.create();
    }

    // Handles the fragment being attached to an activity, ensures that it knows its parentContext
    // which will receive the resulting time that is set
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (TimePickerFragment.OnNewTimeSetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnNewTimeSetListener");
        }
    }
}
