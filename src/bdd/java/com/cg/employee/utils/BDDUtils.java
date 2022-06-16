package com.cg.employee.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class BDDUtils {

    private static Logger logger = LoggerFactory.getLogger(BDDUtils.class);

    public static int randomIdGenerator() {
        Random random = new Random();
        int id = random.nextInt(5000);
        logger.info("Generated ID : " + id);
        return id;
    }

}
