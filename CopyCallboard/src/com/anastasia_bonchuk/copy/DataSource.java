package com.anastasia_bonchuk.copy;

public abstract class DataSource {
	
	abstract void readFromFile(String fileName);
	abstract void writeToFile(String fileName);

}
