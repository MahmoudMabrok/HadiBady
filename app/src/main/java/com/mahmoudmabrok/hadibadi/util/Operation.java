package com.mahmoudmabrok.hadibadi.util;

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


}
