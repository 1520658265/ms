package com.xunjer.linsenshares.util;

import java.util.Random;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1116:38
 */
public class IpUtils {

    private static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    private static int randomIpPart() {
        Random random = new Random();
        return random.nextInt(255) % (256);
    }

    public static String randomIp() {
        return randomIpPart() + "." + randomIpPart() + "." + randomIpPart() + "." + randomIpPart();
    }
}
