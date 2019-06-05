package bst;

public class Mammal implements Comparable<Mammal>{
	private String 			common_name;
	private double 			weight_in_lbs;
	
	public Mammal() {
		common_name			= "";
		weight_in_lbs 		= 0;
	}
	public Mammal(String common_name, double weight_in_lbs) {
		this.common_name	= common_name;
		this.weight_in_lbs	= weight_in_lbs;
	}
	public String get_common_name() {
		return common_name;
	}
	public double get_weight_in_lbs() {
		return weight_in_lbs;
	}
	public void set_common_name(String common_name) {
		this.common_name	= common_name;
	}
	public void set_weight_in_lbs(double weight_in_lbs) {
		this.weight_in_lbs	= weight_in_lbs;
	}
	
	public boolean equals(Object object) {
		Mammal mammal		= (Mammal)object;
		return this.compareTo(mammal) == 0;
	}
	public int compareTo(Mammal mammal) {
		//Lets keep the compare to method really trivial, which is not the best practice.
		//But let's do it for testing purposes and be careful with the weak compareTo() method.
		if (weight_in_lbs > mammal.get_weight_in_lbs())
			return 1;
		else if (weight_in_lbs < mammal.get_weight_in_lbs())
			return -1;
		else
			return common_name.compareTo(mammal.get_common_name());
	}
}
