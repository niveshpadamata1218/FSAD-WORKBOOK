package com.klu.demo.service;

import org.springframework.stereotype.Service;

@Service
public class CalciService {
	public int add(int a,int b)
	{
		return a+b;
	}
	public int sub(int a,int b)
	{
		return a-b;
	}
	public int multiple(int a,int b)
	{
		return a*b;
	}
	public double division(int a,int b)
	{
		if(b==0)
		{
			throw new ArithmeticException("division not allowed");
		}
		return (double) a/b;
	}
	public int module(int a,int b)
	{
		return a%b;
	}

}
