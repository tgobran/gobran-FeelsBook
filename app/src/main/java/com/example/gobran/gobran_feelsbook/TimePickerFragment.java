package com.example.gobran.gobran_feelsbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();

        Bundle timeData = getArguments();
        int hour = timeData.getInt("HOUR",c.get(Calendar.HOUR_OF_DAY));
        int minute = timeData.getInt("MINUTE",c.get(Calendar.MINUTE));
        int second = timeData.getInt("SECOND",c.get(Calendar.SECOND));

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View numberChoiceView = inflater.inflate(R.layout.fragment_time_picker, null);
        final NumberPicker hourPicker = numberChoiceView.findViewById(R.id.timePickerFragment_HourPicker);
        hourPicker.setMaxValue(23);
        hourPicker.setMinValue(0);
        hourPicker.setValue(hour);
        final NumberPicker minutePicker = numberChoiceView.findViewById(R.id.timePickerFragment_MinutePicker);
        minutePicker.setMaxValue(59);
        minutePicker.setMinValue(0);
        minutePicker.setValue(minute);
        final NumberPicker secondPicker = numberChoiceView.findViewById(R.id.timePickerFragment_SecondPicker);
        secondPicker.setMaxValue(59);
        secondPicker.setMinValue(0);
        secondPicker.setValue(second);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(numberChoiceView)
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

        return builder.create();
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
