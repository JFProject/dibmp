package cn.mldn.util;

public class MyMath {
	public static double round(double value,int prec) {
		return Math.round(value * Math.pow(10, prec))/Math.pow(10, prec) ;
	}
}
