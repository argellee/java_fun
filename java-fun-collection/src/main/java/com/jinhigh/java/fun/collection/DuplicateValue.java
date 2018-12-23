package com.jinhigh.java.fun.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 使用Set去除list中的重复数据
 * @author lijin9
 *
 * summary of interfaces
 * http://docs.oracle.com/javase/tutorial/collections/interfaces/summary.html
 *
 */
public class DuplicateValue {

	public void removeDuplicateValue() {
		List myList = new ArrayList();
		myList.add(1);
		myList.add(2);
		myList.add(1);
		myList.add(3);
		myList.add(4);
		myList.add(5);
		myList.add(6);
		myList.add(5);

		Set myset = new HashSet(myList);
		myList = new ArrayList(myset);
		Iterator it = myList.iterator();
		while (it.hasNext()) {
			System.out.println(""+it.next());
		}
	}

	public static void main(String[] args) {
		DuplicateValue dv = new DuplicateValue();
		dv.removeDuplicateValue();

	}


}
