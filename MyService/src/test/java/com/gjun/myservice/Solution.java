package com.gjun.myservice;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//原序列總長度為n
	//cur = 當前位置
	//存放已選出來得數字
	static List<Integer> temp = new ArrayList<Integer>();
	//回傳內容
    static List<List<Integer>> ans = new ArrayList<List<Integer>>();
    
    public static List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    public static void dfs(int cur, int n, int k) {
       
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }
        
        if (temp.size() == k) {
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        //可慮當前位置
        temp.add(cur);
        System.out.println("未使用的"+cur);
        dfs(cur+1, n, k); //要執行拉
        System.out.println("正確的:"+cur);
        
        //不考慮當前位置
        temp.remove(temp.size() - 1);
        dfs(cur+1, n, k); //要執行拉
        System.out.println("失敗的:"+cur);
    }
    public static void main(String[] args) {
		System.out.println(combine(5, 4));
	}
}



	



