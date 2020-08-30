package com.xn.tvdeploy.test;

public class Test2 {
public static void main(String[] args) {
	String sb = "xlj_001:100,xlj_002:1000";
	String[]ff =  sb.split(",");
	for(int i=0;i<ff.length;i++){
		String []dd = ff[i].split(":");
		System.out.println("two:"+dd[1]);
		System.out.println("one:"+dd[0]);
	}
//	String sb2 = "text##1065842230##MDAwMDU0MzIzNV4zNzIvIzEzNTQ1NTY0ZjAwYTY0MjJNSTF0NjdaT2ovQ3RobUNqVjVzSEVQPHQ3LzUoPT00ZzxZMDd5ezdCOTkzNGgzMjApUm0wMDZkNjAxMzZlTTAwMHAwMDAwQlQuaUJYVi9SSXYkOEpkNURVaFZIcFEweEhDPg==";
//	String []msg = sb2.split("##");
//	System.out.println("1"+msg[0]);
//	System.out.println("2"+msg[1]);
//	System.out.println("3"+msg[2]);
//	String tt = sb+sb2;
//	System.out.println("LOL"+tt);
	
	String ms= "031000HOQRGslf17";
	String taskid = ms.substring(ms.length()-11,ms.length()-5);
	System.out.println(taskid);
}
}
