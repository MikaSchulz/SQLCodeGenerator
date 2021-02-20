package me.mikaschulz.codegenerator.operation.operations;

import me.mikaschulz.codegenerator.entry.Entry;
import me.mikaschulz.codegenerator.operation.Operation;

import java.util.ArrayList;
import java.util.List;

public class EntryList extends Operation {

	private final List<Entry> entries = new ArrayList<>();

	public List<Entry> getEntries() {
		return entries;
	}

	public boolean addOperationParameter(Entry entry) {
		entries.add(entry);
		return true;
	}

	public String getText() {
		StringBuilder sb = new StringBuilder();
		for (Entry entry : getEntries()) {
			sb.append(entry.getText());
			sb.append("\n");
		}
		return sb.toString();
	}

	public String toString() {
		return getClass().getSimpleName() + ": " + getEntries();
	}

}
