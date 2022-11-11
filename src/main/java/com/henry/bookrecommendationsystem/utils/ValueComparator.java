package com.henry.bookrecommendationsystem.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
public class ValueComparator implements Comparator<Long> {
    private final Map<Long, Double> base;

    public ValueComparator(Map<Long, Double> base) {
        this.base = base;
    }

    public int compare(Long a, Long b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}
