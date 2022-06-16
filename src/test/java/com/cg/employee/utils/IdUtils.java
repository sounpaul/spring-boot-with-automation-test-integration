package com.cg.employee.utils;

import java.util.Random;

public class IdUtils {

    public static int randomIdGenerator() {
        Random random = new Random();
        return random.nextInt(5000);
    }

}
