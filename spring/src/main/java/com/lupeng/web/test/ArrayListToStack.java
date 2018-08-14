package com.lupeng.web.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 实现栈
 * Created by lupeng on 2018/8/14.
 */
public class ArrayListToStack {
    List list = null;

    public ArrayListToStack(){
        list = new ArrayList();
    }
    public void push (Object o) {
        list.add(o);
    }
    public Object pop () {
        Object o = null;
        if (list.size()>0) {
            o = list.get(list.size()-1);
            list.remove(list.size()-1);
        }
        System.out.print(o);
        return o;
    }

    public static void main(String[] args) {
        ArrayListToStack arrayListToQueue = new  ArrayListToStack();
        arrayListToQueue.push("11");
        arrayListToQueue.push("20");
        arrayListToQueue.push("9");
        arrayListToQueue.pop();
        arrayListToQueue.pop();
        arrayListToQueue.pop();
        ArrayList arrayList = new ArrayList();
        arrayList.add("111");
        arrayList.add("200");
        arrayList.add("99");
        for (Object arrayList1: arrayList) {
            System.out.print(arrayList1);
        }

    }
}
