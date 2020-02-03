package algo_basic_day1;

import java.util.Arrays;
import java.util.Comparator;

import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Header1_1Impl;

public class HeroSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hero[] heros = {new Hero(200, "Hulk"),new Hero(200, "CA"),new Hero(40,"Ironman")};
		Arrays.sort(heros);
		
		Arrays.sort(heros, new Comparator<Hero>() {

			@Override
			public int compare(Hero o1, Hero o2) {
				// TODO Auto-generated method stub
				if(o1.power>o2.power) {
					return 1;
				}else if(o1.power<o2.power) {
					return -1;
				}else {
					return o1.name.compareTo(o2.name);
				}
			}
			
		});
		System.out.println(Arrays.toString(heros));
	}
	
}

class Hero implements Comparable<Hero>{
	int power;
	String name;
	public Hero(int power, String name) {
		super();
		this.power = power;
		this.name = name;
	}
	@Override
	public int compareTo(Hero o) {
		// TODO Auto-generated method stub
		return (this.power==o.power?this.name.compareTo(o.name):(this.power>o.power?1:-1));
	
	}
	public String toString() {
		return name+": "+power;
	}
	
}