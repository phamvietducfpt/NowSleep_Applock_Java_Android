package com.PVD.NowSleep.widget;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.PVD.NowSleep.activities.main.SettimeActivity;
import com.PVD.NowSleep.base.AppConstants;
import com.PVD.NowSleep.utils.SpUtil;

import java.util.Calendar;
public class TimePickerFragmentEnd extends DialogFragment {
    public static String DATEPICKER_TAG = "";
    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        DATEPICKER_TAG = "Set time end";
        SpUtil.getInstance().putBoolean(AppConstants.Alarm_Receiver,false);
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
