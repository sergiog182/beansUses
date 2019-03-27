package com.cdi.beans;

import java.util.Random;
import javax.inject.Named;

@Named("randomNumbers")
public class RandomNumbers {
	
	public RandomNumbers() {
		
	}
	
	public double getRandomBySeed(long seed) {
		Random rand = new Random(seed);
		return rand.nextDouble();
	}
	
	public long getSeed() {
		return System.currentTimeMillis();
	}
	
	public double getRandom() {
		Random rand = new Random(System.currentTimeMillis());
		return rand.nextDouble();
	}
}
