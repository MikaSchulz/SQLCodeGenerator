package me.mikaschulz.codegenerator.entry.entries;

import me.mikaschulz.codegenerator.entry.Entry;

public class ColumnEntry extends Entry {

	private final String text;
	private final String dataType;

	public ColumnEntry(String text, String dataType) {
		this.text = text;
		this.dataType = dataType;
	}

	public String getDataType() {
		return dataType;
	}

	public String getTextWithPrefix(String prefix) {
		return prefix + "." + getText();
	}

	@Override
	public String getText() {
		return text;
	}
}
