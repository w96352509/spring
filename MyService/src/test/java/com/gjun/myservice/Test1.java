package com.gjun.myservice;

public class Test1 {

	public static void pnc(){
		long pnc = 0;
	  	for(int i=1;i<=4;i++){
	  		for(int j=1;j<=4;j++){
	  			for(int k=1;k<=4;k++){
	  				if(i!=j && i<j && i<k && j<k && i!=k && j!=k){
	  					pnc++;
	  					int num = i*100+j*10+k;
	  					System.out.print(num + " "); 
	  				}
	  			}
	  		}
	  	}
		System.out.println("\n" + "數組: "+ pnc ); 
	}
	
	public static void main(String args[]){
		pnc();
	}
}
