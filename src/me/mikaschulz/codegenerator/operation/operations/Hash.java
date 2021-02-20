package me.mikaschulz.codegenerator.operation.operations;

import me.mikaschulz.codegenerator.entry.entries.BoolEntry;
import me.mikaschulz.codegenerator.entry.Entry;
import me.mikaschulz.codegenerator.entry.entries.TextEntry;
import me.mikaschulz.codegenerator.operation.Operation;

import java.util.ArrayList;
import java.util.List;

public class Hash extends Operation {

	private boolean formatted = false;
	private final List<Entry> entryList = new ArrayList<>();

	public boolean addOperationParameter(Entry entry) {
		switch (getParamCount()) {
			case 0:
				if (entry instanceof BoolEntry) {
					BoolEntry boolEntry = (BoolEntry) entry;
					formatted = boolEntry.getBoolValue();
					return true;
				}
				setParamCount(1);
			case 1:
				if (entry instanceof TextEntry) {
					entryList.add(entry);
				}
				return true;
		}
		return false;
	}

	public String getText() {
		return isFormatted() ? toFormattedString() : toUnformattedString();
	}

	public String toFormattedString() {

		if (getEntryList().size() == 1) return toUnformattedString();

		StringBuilder formatted = new StringBuilder();
		formatted.append("CONVERT(VARCHAR(32), HashBytes('MD5',\n");

		for (int i = 0; i < getEntryList().size(); i++) {
			formatted.append("\t");
			if (i == 0) {
				formatted.append("\t");
			} else {
				formatted.append("+\t");
			}
			Entry entry = getEntryList().get(i);
			formatted.append(entry.getText());
			formatted.append("\n");
		}
		formatted.append("), 2)");
		return formatted.toString();
	}

	public String toUnformattedString() {
		StringBuilder unformatted = new StringBuilder();
		unformatted.append("CONVERT(VARCHAR(32), HashBytes('MD5', ");
		for (int i = 0; i < getEntryList().size(); i++) {
			Entry entry = getEntryList().get(i);
			unformatted.append(entry.getText());
			if (i != getEntryList().size() - 1) {
				unformatted.append(" + ");
			}
		}
		unformatted.append("), 2)");
		return unformatted.toString();
	}

	public boolean isFormatted() {
		return formatted;
	}

	public List<Entry> getEntryList() {
		return entryList;
	}

	public String toString() {
		return getClass().getSimpleName() + ": " + isFormatted() + ", " + entryList;
	}

}
