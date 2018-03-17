package com.projects.shrungbhatt.medikit.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.projects.shrungbhatt.medikit.R;
import com.projects.shrungbhatt.medikit.listeners.CallBack;
import com.projects.shrungbhatt.medikit.models.TimePickerDialogModel;
import com.projects.shrungbhatt.medikit.util.DateConverter;
import com.projects.shrungbhatt.medikit.util.URLGenerator;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jigsaw on 12/3/18.
 */

public class BaseActivity extends AppCompatActivity {

    public static String SUCCESS = "success";
    public String errorMessage = "";
    private Dialog mProgressDialog;
    private boolean mFromTimeSelectFlag = false;
    private boolean mToTimeSelectFlag = false;
    private Calendar calender;
    private TimePickerDialogModel mTimePickerDialogModel;
    private CallBack mCallBack;


    {
        mTimePickerDialogModel = new TimePickerDialogModel();
    }

    /**
     * Date converter method used to convert the date in yyyy-MM-dd format.
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String formatDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day);
        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        return sdf.format(date);
    }

    public static String TimeToString(String timeString) {

        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
            DateTime date = formatter.parseDateTime(timeString);
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("hh:mm a");
            return dtfOut.print(date);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;

        return isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();
    }

    public void fromTimePickerDialog(final TextView textView) {
        int hour, minute;
        if (mFromTimeSelectFlag) {
            hour = mTimePickerDialogModel.getFromSelectedHour();
            minute = mTimePickerDialogModel.getFromSelectedMinute();
        } else {
            Calendar mcurrentTime = Calendar.getInstance();
            hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            minute = mcurrentTime.get(Calendar.MINUTE);
            mFromTimeSelectFlag = true;
        }

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                mTimePickerDialogModel.setFromSelectedHour(selectedHour);
                mTimePickerDialogModel.setFromSelectedMinute(selectedMinute);
                textView.setText(TimeToString(String.format("%02d:%02d", selectedHour, selectedMinute)));
            }
        }, hour, minute, false);//Yes 12 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    public void toTimePickerDialog(final TextView textView) {
        int hour, minute;
        if (mToTimeSelectFlag) {
            hour = mTimePickerDialogModel.getToSelectedHour();
            minute = mTimePickerDialogModel.getToSelectedMinute();
        } else {
            Calendar mcurrentTime = Calendar.getInstance();
            hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            minute = mcurrentTime.get(Calendar.MINUTE);
            mToTimeSelectFlag = true;
        }

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                mTimePickerDialogModel.setToSelectedHour(selectedHour);
                mTimePickerDialogModel.setToSelectedMinute(selectedMinute);
                textView.setText(TimeToString(String.format("%02d:%02d", selectedHour, selectedMinute)));
            }
        }, hour, minute, false);//Yes 12 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    //textview set for date;
    public void datePickerDialog(final TextView tvSetdate) {
        calender = Calendar.getInstance();
        int mYear = calender.get(Calendar.YEAR);
        int mMonth = calender.get(Calendar.MONTH);
        int mDay = calender.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvSetdate.setText(formatDate(year, month, dayOfMonth));
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public void showProgressBar(Context context, String TAG) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.setContentView(R.layout.circleprogress);
        mProgressDialog.setCancelable(false);

        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mProgressDialog.getWindow().setGravity(Gravity.CENTER);
        try {
            mProgressDialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void hideProgressBar() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        } catch (Exception e) {
            Log.e("BaseClassForInterface", "Error in hideProgressBar");
        }
    }

    public void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public boolean TimeValidationwith12(String timefrom, String Timeto, String Errormsg, Context context) {
        String TIME_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        String fromtime = DateConverter.Time24ToString(timefrom);
        String totime = DateConverter.Time24ToString(Timeto);
        if (fromtime.matches(String.valueOf(TIME_PATTERN))) {
            if (totime.matches(String.valueOf(TIME_PATTERN))) {
                CharSequence from = fromtime;
                CharSequence to = totime;
                int tvFrom = Integer.valueOf(from.toString().substring(0, 2));
                int tvLastFrom = Integer.valueOf(from.toString().substring(3, 5));
                int tvTo = Integer.valueOf(to.toString().substring(0, 2));
                int tvLastTo = Integer.valueOf(to.toString().substring(3, 5));


                if (tvFrom <= tvTo) {
                    if ((tvFrom == tvTo &&
                            tvLastFrom > tvLastTo)
                            ||
                            (tvFrom == tvTo &&
                                    tvLastFrom == tvLastTo)) {
                        errorMessage = (context.getString(R.string.Choose_proper_timeslot));
                        return false;
                    } else if ((tvFrom == tvTo && tvLastFrom < tvLastTo)
                            ||
                            (tvFrom < tvTo &&
                                    ((tvLastFrom <= tvLastTo) || (tvLastFrom >= tvLastTo)))) {
                        errorMessage = SUCCESS;
                        return true;
                    } else {
                        errorMessage = (context.getString(R.string.Choose_proper_timeslot));
                        return false;
                    }

                } else {
                    errorMessage = (context.getString(R.string.Choose_proper_timeslot));
                    return false;
                }
            } else {
                errorMessage = (context.getString(R.string.Please_select) + Errormsg);
                return false;
            }
        } else {
            errorMessage = (context.getString(R.string.Please_select) + Errormsg);
            return false;
        }
    }

    public void showErrorDialog(String msg) {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create().show();
    }


    public void updateAppointmentStatus(final String id, final String statusId,CallBack callBack) {
        showProgressBar(this, "BaseActivity");
        mCallBack = callBack;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLGenerator.UPDATE_APPOINTMENTSTATUS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressBar();
                        if (response.equalsIgnoreCase("Updated status")) {
                            showToastMessage("Status updated");
                            mCallBack.callBack();
                        } else {
                            showToastMessage("Something went wrong");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressBar();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("status_id", statusId);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
