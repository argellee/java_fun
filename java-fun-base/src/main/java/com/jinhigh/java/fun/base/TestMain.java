package com.jinhigh.java.fun.base;

import java.util.List;

public class TestMain {

	public static void main(String[] args) {
//		List<Integer> list = new ArrayList<Integer>();
//		for (int i = 0; i < 10; i++) {
//			list.add(i);
//		}
//		add(list);
//		for (Integer j : list) {
//			System.err.print(j+",");;
//		} //0,1,2...100
//		System.err.println("");
//		System.err.println("*********************");
//		String a="A";
//		append(a);
//		System.err.println(a); //A
//		int num = 5;
//		addNum(num); //5
//		System.err.println(num);


	}
	
	static void add(List<Integer> list){
		list.add(100);
	}
	static void append(String str){
		str+="is a";
	}
	static void addNum(int a){
		a=a+10;
	}
}