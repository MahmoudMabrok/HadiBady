package com.mahmoudmabrok.hadibadi.Util;

import java.util.Random;

/**
 * Created by Mahmoud on 9/11/2018.
 */
public class Operation {

    public static  int getRandom(int max){

        Random random = new Random();
        return   random.nextInt(max) ;
       // return  (int)( Math.random() * max) ;
    }


}
