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

    public interface OnNewTimeSetListener {
        public void onNewTimeSet(int hour, int minute, int second);
    }

    @Override
    public @NonNull Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle timeData = getArguments();
        final Calendar c = Calendar.getInstance();
        int hour = timeData.getInt(this.getString(R.string.timePickerFragment_HourArgument),c.get(Calendar.HOUR_OF_DAY));
        int minute = timeData.getInt(this.getString(R.string.timePickerFragment_MinuteArgument),c.get(Calendar.MINUTE));
        int second = timeData.getInt(this.getString(R.string.timePickerFragment_SecondArgument),c.get(Calendar.SECOND));

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

        return dialogBuilder.create();
    }

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
