package com.mahmoudmabrok.hadibadi.Util;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import java.util.Random;

/**
 * Created by Mahmoud on 9/11/2018.
 */
public class Operation {

    public static  int getRandom(int max){

        Random random = new Random();
        if (max > 1)
            return   random.nextInt(max) ;
        else
            return 0;
       // return  (int)( Math.random() * max) ;
    }

    public static void hideInputKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }


}
