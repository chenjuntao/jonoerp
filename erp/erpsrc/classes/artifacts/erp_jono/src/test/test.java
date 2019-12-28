package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class test {
	private static void test01(int size) {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			Laptop obj = new Laptop();
		}
		long end = System.currentTimeMillis();
		System.out.println("new的方式创建对象耗时：" + (end - begin));
	}

	private static void test02(int size) {
		long begin = System.currentTimeMillis();
		Laptop obj = new Laptop();
		for (int i = 0; i < size; i++) {
			Laptop obj1 = (Laptop) obj.clone();
		}
		long end = System.currentTimeMillis();
		System.out.println("clone的方式创建对象耗时：" + (end - begin));
	}

	private static void test03(int size) {
		long begin = System.currentTimeMillis();
		Laptop obj = new Laptop();
		for (int i = 0; i < size; i++) {
			Laptop obj1 = (Laptop) obj.deepClone();
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化的方式创建对象耗时：" + (end - begin));
	}

	public static void main(String[] args) {
		test01(100);
		test02(100);
		test03(100);
	}
}

// 笔记本电脑
class Laptop implements Cloneable, Serializable {
	public Laptop() {
		try {
			Thread.sleep(10); // 模拟创建对象很耗时
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 利用Object的clone()方法实现深拷贝
	protected Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 利用序列号和反序列化实现深拷贝
	public Object deepClone() {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(this);

			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			return oi.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
