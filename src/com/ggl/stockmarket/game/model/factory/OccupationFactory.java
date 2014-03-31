package com.ggl.stockmarket.game.model.factory;

import java.util.ArrayList;
import java.util.List;

import com.ggl.stockmarket.game.model.Occupation;

public class OccupationFactory {
	
	protected static OccupationFactory instance;
	
	protected static List<Occupation> occupations;
	
	private OccupationFactory() {
		occupations = new ArrayList<Occupation>();
		generateOccupations();
	}
	
	public static List<Occupation> getOccupations() {
		if (instance == null) instance = new OccupationFactory();
		return occupations;		
	}
	
	protected void generateOccupations() {
//		occupations.add(generateOccupation("Policeman", 100, 5, 9));
		occupations.add(generateOccupation("Policeman", 150, 5, 9));
		occupations.add(generateOccupation("Doctor", 200, 4, 10));
		occupations.add(generateOccupation("Deep Sea Diver", 300, 3, 11));
//		occupations.add(generateOccupation("Prospector", 400, 2, 12));
		occupations.add(generateOccupation("Prospector", 600, 2, 12));
	}

	protected Occupation generateOccupation(String name, int amount, 
			int roll_1,	int roll_2) {
		Occupation occupation = new Occupation(name, amount);
		List<Integer> list = new ArrayList<Integer>();
		list.add(roll_1);
		list.add(roll_2);
		occupation.setRolls(list);
		return occupation;
	}

}
