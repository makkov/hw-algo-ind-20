package com.hw.maxim;

import com.hw.maxim.list.CustomList;
import com.hw.maxim.list.StringList;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        StringList stringList = new CustomList();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");
        String[] arr = stringList.toArray();
    }
}
