package me.mikaschulz.codegenerator.entry.entries;

import me.mikaschulz.codegenerator.entry.Entry;

public class BoolEntry extends Entry {

	private boolean bool = false;

	public boolean getBoolValue() {
		return bool;
	}

	public void setBoolValue(boolean bool) {
		this.bool = bool;
	}

	public String getText() {
		return Boolean.toString(bool);
	}

	public String toString() {
		return getClass().getSimpleName() + ": " + getText();
	}
}



