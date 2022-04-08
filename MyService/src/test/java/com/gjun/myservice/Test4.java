package com.gjun.myservice;

import static org.mockito.ArgumentMatchers.anyString;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Test4 {

	
	
	private static Boolean color(String s) {
		/*
		 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
		 */
		Boolean flag = null;
		List<Character> list = new LinkedList<>();
		Set<Character> save = new LinkedHashSet<>();
		list.add('a'); // 0
		list.add('b'); // 1
		list.add('c'); // 2
		list.add('d'); // 3
		list.add('e'); // 4
		list.add('f'); // 5
		list.add('g'); // 6
		list.add('h'); // 7
		list.add('i'); // 8
		list.add('j'); // 9
		list.add('k'); // 10
		list.add('l');
		list.add('m');
		list.add('n');
		list.add('o');
		list.add('p');
		char[] charArray = s.toCharArray();
		//印出 
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
			if ((i + 1) % 4 == 0) {
				System.out.println("");
			}
		}
		
		for (int j = 0; j < charArray.length - 1; j++) {
			 for (int k = 0; k < list.size(); k++) {
				 while (charArray[j] == list.get(k)) {
						if (k == 0) {
							save.add(list.get(k));
							save.add(list.get(k + 1));
							save.add(list.get(k + 4));
							save.add(list.get(k + 5));
						}
						if (k == 1 || k == 2) {
							save.add(list.get(k));
							save.add(list.get(k - 1));
							save.add(list.get(k + 1));
							save.add(list.get(k + 3));
							save.add(list.get(k + 4));
							save.add(list.get(k + 5));
						}
						if (k == 3) {
							save.add(list.get(k));
							save.add(list.get(k - 1));
							save.add(list.get(k + 3));
							save.add(list.get(k + 4));
						}
						if (k == 4 || k == 8) {
							save.add(list.get(k));
							save.add(list.get(k - 3));
							save.add(list.get(k - 4));
							save.add(list.get(k + 1));
							save.add(list.get(k + 4));
							save.add(list.get(k + 5));
						}
						if (k == 7 || k == 11) {
							save.add(list.get(k));
							save.add(list.get(k - 4));
							save.add(list.get(k - 5));
							save.add(list.get(k - 1));
							save.add(list.get(k + 3));
							save.add(list.get(k + 4));
						}
						if (k == 5 ||k == 6 ||k == 9 ||k == 10) {
							save.add(list.get(k - 5));
							save.add(list.get(k - 4));
							save.add(list.get(k - 3));
							save.add(list.get(k - 1));
							save.add(list.get(k));
							save.add(list.get(k + 1));
							save.add(list.get(k + 5));
							save.add(list.get(k + 4));
							save.add(list.get(k + 3));
						}
						if (k == 12) {
							save.add(list.get(k));
							save.add(list.get(k - 4));
							save.add(list.get(k - 3));
							save.add(list.get(k + 1));
						}
						if (k == 13 || k == 14) {
							save.add(list.get(k));
							save.add(list.get(k - 1));
							save.add(list.get(k + 1));
							save.add(list.get(k - 4));
							save.add(list.get(k - 3));
						}

						if (k == 15) {
							save.add(list.get(k));
							save.add(list.get(k - 5));
							save.add(list.get(k - 4));
							save.add(list.get(k - 1));
						}
						//預測下方位置
						if (save.contains(charArray[j+1])) {
						    flag = true;
						    break;
						} 
						if(save.contains(charArray[j+1])==false) {
							flag = false;
							return flag;
						}
                    } //while
				 }
			} // for
		 //for each	
		return flag;
	}

	public static void main(String[] args) {
		System.out.println("-------");
		System.out.println(color("abcd"));
	}

}
