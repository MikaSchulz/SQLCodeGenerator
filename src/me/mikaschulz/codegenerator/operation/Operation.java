package me.mikaschulz.codegenerator.operation;

import me.mikaschulz.codegenerator.entry.Entry;

import java.util.ArrayList;
import java.util.List;

public abstract class Operation extends Entry {

	private final List<Entry> entryList = new ArrayList<>();
	private final boolean formatted;

	public Operation(boolean formatted) {
		this.formatted = formatted;
	}

	public List<Entry> getEntryList() {
		return entryList;
	}

	public void addEntry(Entry entry) {
		entryList.add(entry);
	}

	public boolean isFormatted() {
		return formatted;
	}

	@Override
	public String getText() {
		return (isFormatted() ? toFormattedString() : toUnformattedString());
	}

	protected abstract String toFormattedString();

	protected abstract String toUnformattedString();
}
