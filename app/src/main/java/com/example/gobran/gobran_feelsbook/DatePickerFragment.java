package com.example.gobran.gobran_feelsbook;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    OnNewDateSetListener mCallback;

    public interface OnNewDateSetListener {
        public void onNewDateSet(int year, int month, int day);
    }

    @Override
    public @NonNull Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle dateData = getArguments();
        final Calendar c = Calendar.getInstance();
        int year = dateData.getInt(this.getString(R.string.datePickerFragment_YearArgument),c.get(Calendar.YEAR));
        int month = dateData.getInt(this.getString(R.string.datePickerFragment_MonthArgument),c.get(Calendar.MONTH));
        int day = dateData.getInt(this.getString(R.string.datePickerFragment_DayArgument),c.get(Calendar.DAY_OF_MONTH));

        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnNewDateSetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnNewDateSetListener");
        }
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        mCallback.onNewDateSet(year,month,day);
    }
}
