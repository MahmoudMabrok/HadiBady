package com.mahmoudmabrok.hadibadi.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mahmoudmabrok.hadibadi.R;

/**
 * Created by Mahmoud on 9/13/2018.
 */
public class Alert extends AlertDialog {

    private Context context;

    protected Alert(Context context) {
        super(context);
        this.context = context;
    }

    public static AlertDialog getDialog(Context context, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.custome_dialoge_title, null);
        TextView textView = view.findViewById(R.id.tvFarg3);
        textView.setText(message);
        return new AlertDialog.Builder(context).setView(view).create();
    }

}
