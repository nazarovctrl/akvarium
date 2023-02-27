package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AquariumUtil {
    public static List<String> maleNameList = new ArrayList<>();
    public static List<String> femaleNameList = new ArrayList<>();
    public static final int length = 1;
    public static final int height = 1;
    public static final int width = 1;
    public static final int totalAmount = 10;
    public static Random random = new Random();


    public static String getRandomMaleName() {
        int random1 = getRandom(maleNameList.size());
        String s = maleNameList.get(random1);
        maleNameList.remove(random1);
        return s;
    }

    public static int getRandom(int n) {
        return random.nextInt(n);
    }

    public static int getRandomBetween(int a, int b) {
        return random.nextInt(a, b);
    }

    public static Gender getRandomGender() {
        if (random.nextInt(2) == 0) {
            return Gender.MALE;
        }
        return Gender.FEMALE;
    }

    public static String getRandomFemaleName() {
        int random1 = getRandom(femaleNameList.size());
        String s = femaleNameList.get(random1);
        femaleNameList.remove(random1);
        return s;
    }
}
