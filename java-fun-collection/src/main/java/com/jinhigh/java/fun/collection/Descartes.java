package com.jinhigh.java.fun.collection;

import java.util.ArrayList;
import java.util.List;

public class Descartes {
    /**
     * 递归实现dimValue中的笛卡尔积，结果放在result中
     * @param dimValue 原始数据
     * @param result 结果数据
     * @param layer dimValue的层数
     * @param curList 每次笛卡尔积的结果
     */
    private static List<List<String>> recursive (List<List<String>> dimValue,
                                                 List<List<String>> result, int layer, List<String> curList) {
        //大于一个集合时
        if (layer < dimValue.size() - 1) {
            //如果第一个集合为空
            if (dimValue.get(layer).size() == 0) {
                recursive(dimValue, result, layer + 1, curList);
            } else {
                for (int i = 0; i < dimValue.get(layer).size(); i++) {
                    List<String> list = new ArrayList<String>(curList);
                    list.add(dimValue.get(layer).get(i));
                    recursive(dimValue, result, layer + 1, list);
                }
            }
            //只有一个集合时
        } else if (layer == dimValue.size() - 1) {
            //如果集合中没有元素
            if (dimValue.get(layer).size() == 0) {
                result.add(curList);
                //只有一个集合，且集合中有元素时：其笛卡尔积就是这个元素本身
            } else {
                for (int i = 0; i < dimValue.get(layer).size(); i++) {
                    List<String> list = new ArrayList<String>(curList);
                    list.add(dimValue.get(layer).get(i));
                    result.add(list);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        List<List<String>> dimValue = new ArrayList<>();
        List<String> a = new ArrayList<>();
        a.add("a:a1");
        a.add("a:a2");
        a.add("a:a3");

        List<String> b = new ArrayList<>();
        b.add("b:b1");
        b.add("b:b2");
        b.add("b:b3");

        List<String> c = new ArrayList<>();
        c.add("c:c1");
        c.add("c:c2");

        dimValue.add(a);
        dimValue.add(b);
        dimValue.add(c);

        List<List<String>> result = new ArrayList<>();
        List<String> curList = new ArrayList<>();

        result = recursive(dimValue, result, 0, curList);
        System.out.println(result);
        System.out.println("ok!");

    }

}
