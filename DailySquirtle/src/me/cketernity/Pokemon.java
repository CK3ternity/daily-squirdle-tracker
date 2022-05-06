package me.cketernity;

public class Pokemon {
	private String name;
	private int gen;
	private String type1;
	private String type2;
	private double height;
	private double weight;
	private int round;
	
	public Pokemon(String name, int gen, String type1, String type2, double height, double weight, int round) {
		this.name = name;
		this.gen = gen;
		this.type1 = type1;
		this.type2 = type2;
		this.height = height;
		this.weight = weight;
		this.round = round;
	}
	
	public String getName() { return this.name; }
	public int getGen() { return this.gen; }
	public String getType1() { return this.type1; }
	public String getType2() { return this.type2; }
	public double getHeight() { return this.height; }
	public double getWeight() { return this.weight; }
	public int getRound() { return this.round; }
	
	public String toString() {
		String str = "";
		
		str += this.name + "\n";
		str += this.type1 + (this.type2.equals("None") ? "" : "/" + this.type2) + " Type\n";
		str += "Generation:\t" + this.gen + "\n";
		str += "Height:\t\t" + this.height + "m\n";
		str += "Weight:\t\t" + this.weight + "kg\n";
		
		return str;
	}
}
