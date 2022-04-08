package com.gjun.myservice;

import java.util.ArrayList;
import java.util.List;

public class move {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1); //0
		list.add(2); //1
		list.remove(1);
		list.add(3); //2
		System.out.println(list.indexOf(1));
		System.out.println(list.indexOf(2));
		System.out.println(list.indexOf(3));
	}
	
}
