package me.cketernity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static final int LOW = 0;
	public static final int HIGH = 1;
	public static final char TYPE_ONE = '1';
	public static final char TYPE_TWO = '2';
	public static final char UNCHECKED = 'u';
	public static final char INCORRECT = 'i';
	
	private static Pokemon currentPokemon;
	private static ArrayList<Pokemon> pokemon;
	private static ArrayList<Pokemon> currentRoundPokemon;
	
	private static int round;
	private static int[] gen;
	private static char[] types;
	private static double[] height;
	private static double[] weight;
	private static HashMap<String, Integer> typeKey;
	private static final String FILE_NAME = "pokemon.txt";
	
	public static void main(String[] args) {
		new SquirtleUI();
		init();
	}
	
	private static void init() {
		round = -1;
		pokemon = getMonsFromFile();
		currentRoundPokemon = new ArrayList<Pokemon>();
		gen = new int[2];
		setGenLow(0);
		setGenHigh(9);
		height = new double[2];
		setHeightLow(Double.MIN_VALUE);
		setHeightHigh(Double.MAX_VALUE);
		weight = new double[2];
		setWeightLow(Double.MIN_VALUE);
		setWeightHigh(Double.MAX_VALUE);
		
		types = new char[19];
		for(int i = 0; i < types.length; i++) {
			types[i] = 'u';
		}
		
		typeKey = new HashMap<String, Integer>();
		
		typeKey.put("None", 0);
		typeKey.put("Water", 1);
		typeKey.put("Normal", 2);
		typeKey.put("Grass", 3);
		typeKey.put("Flying", 4);
		typeKey.put("Psychic", 5);
		typeKey.put("Bug", 6);
		typeKey.put("Fire", 7);
		typeKey.put("Poison", 8);
		typeKey.put("Ground", 9);
		typeKey.put("Fighting", 10);
		typeKey.put("Dark", 11);
		typeKey.put("Rock", 12);
		typeKey.put("Steel", 13);
		typeKey.put("Electric", 14);
		typeKey.put("Dragon", 15);
		typeKey.put("Ghost", 16);
		typeKey.put("Fairy", 17);
		typeKey.put("Ice", 18);
		
		increaseRound();
	}
	private static ArrayList<Pokemon> getMonsFromFile() {
		ArrayList<Pokemon> mons = new ArrayList<Pokemon>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
			
			String line;
			String[] information;
			while((line = reader.readLine()) != null) {
				information = line.split(",");
				mons.add(new Pokemon(information[0], Integer.parseInt(information[1]), information[2], information[3], Double.parseDouble(information[4]), Double.parseDouble(information[5]), Integer.parseInt(information[6])));
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
				writer.close();
				
				return new ArrayList<>();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mons;
	}
	public static void increaseRound() {
		round++;
		currentPokemon = null;
		currentRoundPokemon.clear();
		for(Pokemon mon: pokemon) {
			if(mon.getRound() == round) {
				currentRoundPokemon.add(mon);
			}
		}
		
		for(Pokemon p: currentRoundPokemon) {
			if(validatePokemon(p)) {
				currentPokemon = p;
				break;
			}
		}
		
		if(currentPokemon != null) {
				SquirtleUI.setNextGuess(currentPokemon.getName());
				SquirtleUI.reset();
		} else {
			SquirtleUI.getNewPokemon();
		}
		
	}
	private static boolean validatePokemon(Pokemon p) {
		if(!(p.getGen() > getGenLow() && p.getGen() < getGenHigh()))
			return false;
		
		boolean typeOne = false;
		boolean typeTwo = false;
		for(int i = 0; i < types.length; i++) {
			if(types[i] == TYPE_ONE) {
				typeOne = true;
				System.out.println("Type 1 found");
			}
			
			if(types[i] == TYPE_TWO) {
				typeTwo = true;
				System.out.println("Type 2 found");
			}
		}
		
		if((typeOne && types[typeKey.get(p.getType1())] != TYPE_ONE) || (typeTwo && types[typeKey.get(p.getType2())] != TYPE_TWO) || types[typeKey.get(p.getType1())] == INCORRECT || types[typeKey.get(p.getType2())] == INCORRECT)
			return false;
		
		if(!(p.getHeight() > getHeightLow() && p.getHeight() < getHeightHigh()))
			return false;
		
		if(!(p.getWeight() > getWeightLow() && p.getWeight() < getWeightHigh()))
			return false;
		
		return true;
	}
	public static boolean updateResults(boolean[][] array) {
		boolean[] gen = array[0];
		boolean[] type1 = array[1];
		boolean[] type2 = array[2];
		boolean[] height = array[3];
		boolean[] weight = array[4];
		boolean win = true;
		
		
		if(gen[0]) {
			setGenLow(currentPokemon.getGen());
			win = false;
		} else if(gen[1]) {
			setGenLow(currentPokemon.getGen() - 1);
			setGenHigh(currentPokemon.getGen() + 1);
		} else if(gen[2]) {
			setGenHigh(currentPokemon.getGen());
			win = false;
		} else {
			System.out.println("Something has gone wrong, please contact CKEternity#4861 with the error message \"Gen all false\"");
		}
		
		if(type1[0]) {
			setTypeValue(currentPokemon.getType1(), TYPE_ONE);
		} else if(type1[1]) {
			setTypeValue(currentPokemon.getType1(), TYPE_TWO);
			win = false;
		} else if(type1[2]) {
			setTypeValue(currentPokemon.getType1(), INCORRECT);
			win = false;
		} else {
			System.out.println("Something has gone wrong, please contact CKEternity#4861 with the error message \"Type 1 all false\"");
		}

		if(type2[0]) {
			setTypeValue(currentPokemon.getType2(), TYPE_TWO);
		} else if(type2[1]) {
			setTypeValue(currentPokemon.getType2(), TYPE_ONE);
			win = false;
		} else if(type2[2]) {
			setTypeValue(currentPokemon.getType2(), INCORRECT);
			win = false;
		} else {
			System.out.println("Something has gone wrong, please contact CKEternity#4861 with the error message \"Type 2 all false\"");
		}

		if(height[0]) {
			setHeightLow(currentPokemon.getHeight());
			win = false;
		} else if(height[1]) {
			setHeightLow(currentPokemon.getHeight() - 0.1);
			setHeightHigh(currentPokemon.getHeight() + 0.1);
		} else if(height[2]) {
			setHeightHigh(currentPokemon.getHeight());
			win = false;
		} else {
			System.out.println("Something has gone wrong, please contact CKEternity#4861 with the error message \"Height all false\"");
		}

		if(weight[0]) {
			setWeightLow(currentPokemon.getWeight());
			win = false;
		} else if(weight[1]) {
			setWeightLow(currentPokemon.getWeight() - 0.1);
			setWeightHigh(currentPokemon.getWeight() + 0.1);
		} else if(weight[2]) {
			setWeightHigh(currentPokemon.getWeight());
			win = false;
		} else {
			System.out.println("Something has gone wrong, please contact CKEternity#4861 with the error message \"Weight all false\"");
		}
		
		return win;
	}
	public static void addNewPokemon(Pokemon p) {
		String line, file = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/me/cketernity/pokemon.txt"));
			
			while((line = reader.readLine()) != null) {
				file += line + "\n";
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
			
			writer.write(file + p.getName() + "," + p.getGen() + "," + p.getType1() + "," + p.getType2() + "," + p.getHeight() + "," + p.getWeight() + "," + p.getRound());
			
			writer.close();
			
			System.out.println("Pokemon added to file: " + p);
			currentPokemon = p;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getRound() {
		return round;
	}
	public static int getGenLow() {
		return gen[LOW];
	}
	public static int getGenHigh() {
		return gen[HIGH];
	}
	public static double getHeightLow() {
		return height[LOW];
	}
	public static double getHeightHigh() {
		return height[HIGH];
	}
	public static double getWeightLow() {
		return weight[LOW];
	}
	public static double getWeightHigh() {
		return weight[HIGH];
	}
	public static char getTypeValue(String type) {
		return types[typeKey.get(type)];
	}
	public static String[] getTypes() {
		String[] types = new String[typeKey.size()];
		int i = 0;
		for(String type: typeKey.keySet()) {
			types[i] = type;
			i++;
		}
		
		return types;
	}
	public static void setGenLow(int genLow) {
		gen[LOW] = genLow;
	}
	public static void setGenHigh(int genHigh) {
		gen[HIGH] = genHigh;
	}
	public static void setHeightLow(double heightLow) {
		height[LOW] = heightLow;
	}
	public static void setHeightHigh(double heightHigh) {
		height[HIGH] = heightHigh;
	}
	public static void setWeightLow(double weightLow) {
		weight[LOW] = weightLow;
	}
	public static void setWeightHigh(double weightHigh) {
		weight[HIGH] = weightHigh;
	}
	public static void setTypeValue(String type, char value) {
		types[typeKey.get(type)] = value;
	}
}
