package me.mikaschulz.codegenerator.entry.entries;

import me.mikaschulz.codegenerator.entry.Entry;

public class TextEntry extends Entry {

	private String text;

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		return getClass().getSimpleName() + ": " + getText();
	}

}



