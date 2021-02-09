package me.mikaschulz.codegenerator.entry.entries;

import me.mikaschulz.codegenerator.entry.Entry;

public class TextEntry extends Entry {

	private final String text;

	public TextEntry(String text) {
		this.text = text;
	}

	@Override
	public String getText() {
		return text;
	}
}