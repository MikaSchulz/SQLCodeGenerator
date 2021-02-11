package me.mikaschulz.codegenerator.entry;

public class OutputValue {

	private Entry entry;
	private String prefix;

	public OutputValue(Entry entry, String prefix) {
		this.entry = entry;
		this.prefix = prefix;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
