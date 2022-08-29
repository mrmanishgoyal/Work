package FrameworkCreation.FWCreation;
import java.util.concurrent.ThreadLocalRandom;

import com.github.javafaker.Faker;

import java.util.Random;

public class TestRandom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random r=new Random();
		int number;
		number=r.nextInt();
		System.out.println(number);
		
		/*
		 * for(int i=1;i<10000;i++) {
		 * 
		 * number=r.nextInt(); }
		 */
		int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
		System.out.println(randomNum);
		
		Faker faker = new Faker();

		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		System.out.println(name);
		System.out.println(firstName);
		System.out.println(lastName);
		
		
		
	}

}
