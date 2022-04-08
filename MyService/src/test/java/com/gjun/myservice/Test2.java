package com.gjun.myservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test2 {

	private static Set<Set<String>> exhaustion() {
		//數組設定
	    List<String> m = Arrays.asList("1", "2", "3", "4","5");
	    //記錄結果
	    Set<Set<String>> result = new HashSet<>();
	    //
	    int count = 4;
	    for (int a = 1; a < m.size(); a++) {
	        for (int b = 0; b < m.size(); b++) {
	            for (int c = 0; c < m.size(); c++) {
	            	for(int d = 0; d< m.size();d++) {
	            		 Set<String> tempCollection = new HashSet<>();
	 	                tempCollection.add(m.get(a));
	 	                tempCollection.add(m.get(b));
	 	                tempCollection.add(m.get(c));
	 	                tempCollection.add(m.get(d));
	 	                // 如果三個元素中有重複的會被 Set 排重，導致 Set 的大小不為 3
	 	                if (tempCollection.size() == count) {
	 	                    result.add(tempCollection);
	 	                }
	            	}
	            }
	        }
	    }
    return result;
	}
	 public static void main(String[] args) {
		System.out.println(exhaustion());
	}
}
