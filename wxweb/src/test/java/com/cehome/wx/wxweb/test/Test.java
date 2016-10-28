package com.cehome.wx.wxweb.test;

public class Test {
	public static final int[] arri=new int[]{12,3,4,56,7,8,9,4,345,23,43,24,34,546,65,7,5,645,6,4564,5,12,3,4,56,7,8,9,4,345,23,43,24,34,546,65,7,5,645,6,4564,5,12,3,4,56,7,8,9,4,345,23,43,24,34,546,65,7,5,645,6,4564,5,12,3,4,56,7,8,9,4,345,23,43,24,34,546,65,7,5,645,6,4564,5,12,3,4,56,7,8,9,4,345,23,43,24,34,546,65,7,5,645,6,4564,5,12,3,4,56,7,8,9,4,345,23,43,24,34,546,65,7,5,645,6,4564,5,12,3,4,56,7,8,9,4,345,23,43,24,34,546,65,7,5,645,6,4564,5,12,3,4,56,7,8,9,4,345,23,43,24,34,546,65,7,5,645,6,4564,5,12,3,4,56,7,8,9,4,345,23,43,24,34,546,65,7,5,645,6,4564,5};
	public static void main(String[] args) {
		Test.Terd tg =  new Test().new Terd();
		Test.Terd tg2 =  new Test().new Terd();
		tg.start();
		tg2.start();
	}
	class Terd extends Thread{
		@Override
		public void run() {
			int i=1;
			while(true){
				if(i<arri.length){
					if(arri[arri.length-i]!=0){
						System.err.println(i+"---------------"+this.getName());
						arri[arri.length-i]=0;
						System.err.println(i+"e-------------"+arri[arri.length-i]+"-------"+this.getName());
					}
					i++;
				}else{
					break;
				}
			}
			for(int m:arri)
				System.out.println(m);
		}
		
	}
}
