package com.kamil184.newmotivate.util;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ColorGenerator {
    public static final String COLOR_LIST = "color list";
    private List<Integer> materialColors;

    public static List<Integer> getAllColors(){
        return new ArrayList<Integer>(Arrays.asList(
                0xffe57373,
                0xfff06292,
                0xffba68c8,
                0xff9575cd,
                0xff7986cb,
                0xff64b5f6,
                0xff4fc3f7,
                0xff4dd0e1,
                0xff4db6ac,
                0xff81c784,
                0xffaed581,
                0xffff8a65,
                0xffd4e157,
                0xffffd54f,
                0xffffb74d,
                0xffa1887f,
                0xff90a4ae));
    }

    private Random random;

    public ColorGenerator(List<Integer> materialColors) {
        random = new Random();
        this.materialColors = materialColors;
    }

    public int getColor() {
        if (materialColors.size() != 0) {
            return materialColors.remove(random.nextInt(materialColors.size()));
        } else {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            return Color.rgb(r, g, b);
        }
    }
}
