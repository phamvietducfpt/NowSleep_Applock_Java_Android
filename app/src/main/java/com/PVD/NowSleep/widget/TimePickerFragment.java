package com.PVD.NowSleep.widget;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import  androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.text.format.DateFormat;

import com.PVD.NowSleep.base.AppConstants;
import com.PVD.NowSleep.utils.SpUtil;

import java.util.Calendar;
public class TimePickerFragment extends DialogFragment  {
    public static String DATEPICKER_TAG = "";
    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default values for the picker
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        DATEPICKER_TAG = "Set time on";
        SpUtil.getInstance().putBoolean(AppConstants.Alarm_Receiver,true);
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
