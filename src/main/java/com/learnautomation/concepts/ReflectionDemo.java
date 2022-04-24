package com.learnautomation.concepts;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionDemo {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		Student s1=new Student();
		
		Class cls=s1.getClass();
		
		Constructor cons=cls.getConstructor();

		System.out.println(cons.getName());
		
		Method[] allMethods=cls.getMethods();
		
		for(Method m:allMethods)
		{
			System.out.println(m.getName());
		}
		
		Field f1=cls.getDeclaredField("univerName");
		
		System.out.println(f1.getName());
		
		System.out.println(f1.get(s1));
	}

}
