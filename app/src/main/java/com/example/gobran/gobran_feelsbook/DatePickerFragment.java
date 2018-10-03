/*
 * Class: DatePickerFragment
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Handles the creation of a DialogFragment on screen that will allow the 
 *      editing of the date an emotion was created
 * Rationale:
 *      Seperates the operation of setting the date from any specific activity, 
 *      this allows any class that implements its interface to recieve a new
 *      date value set by the user.
 *      Thus it creates greater modularity, with it being possible to reuse
 *      this code in other projects with date selection
 *
 * Outstanding Issues:
 *     Few issues are prevalent with the current design due to its relative simplicity
 */

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

    //Interface an implementing class must support, so that the newly returned date values will be recieved
    public interface OnNewDateSetListener {
        public void onNewDateSet(int year, int month, int day);
    }

    //Creates the actual dialog residing within the fragment, makes use of the already present dialog
    //for date selection provided by Android
    @Override
    public @NonNull Dialog onCreateDialog(Bundle savedInstanceState) {
        
        //Changes the date dialog to present any pre-existing date provided in an argument
        //If none present then the inital date is set to the current date
        Bundle dateData = getArguments();
        final Calendar c = Calendar.getInstance();
        int year = dateData.getInt(this.getString(R.string.datePickerFragment_YearArgument),c.get(Calendar.YEAR));
        int month = dateData.getInt(this.getString(R.string.datePickerFragment_MonthArgument),c.get(Calendar.MONTH));
        int day = dateData.getInt(this.getString(R.string.datePickerFragment_DayArgument),c.get(Calendar.DAY_OF_MONTH));

        return new DatePickerDialog(getActivity(),this,year,month,day);
    }

    
    //Handles the fragment being attached to an activity, ensures that it knows its 
    //parent context which will recieve the resulting date that is set
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnNewDateSetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnNewDateSetListener");
        }
    }

    //Handles the date being set within the DatePickerDialog, passes the value along to the parent context
    public void onDateSet(DatePicker view, int year, int month, int day) {
        mCallback.onNewDateSet(year,month,day);
    }
}
