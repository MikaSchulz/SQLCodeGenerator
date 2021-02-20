package me.mikaschulz.codegenerator.operation.operations;

import me.mikaschulz.codegenerator.entry.entries.BoolEntry;
import me.mikaschulz.codegenerator.entry.Entry;
import me.mikaschulz.codegenerator.entry.entries.TextEntry;
import me.mikaschulz.codegenerator.operation.Operation;

import java.util.ArrayList;
import java.util.List;

public class Checksum extends Operation {

	private boolean formatted = false;
	private final List<Entry> entryList = new ArrayList<>();

	protected boolean addOperationParameter(Entry entry) {
		if (getParamCount() == 0) {
			if (entry instanceof BoolEntry) {
				BoolEntry boolEntry = (BoolEntry) entry;
				formatted = boolEntry.getBoolValue();
				return true;
			}
			setParamCount(1);
		}
		if (getParamCount() >= 1) {
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
		StringBuilder formatted = new StringBuilder();
		formatted.append("Checksum (\n");

		for (int i = 0; i < getEntryList().size(); i++) {
//			System.out.println(name);
			formatted.append("\t");
			if (i == 0) {
				formatted.append(" ");
			} else {
				formatted.append(",");
			}
			Entry entry = getEntryList().get(i);
			formatted.append(entry.getText());
			formatted.append("\n");
		}
		formatted.append(")");
		return formatted.toString();
	}

	public String toUnformattedString() {
		StringBuilder unformatted = new StringBuilder();
		unformatted.append("Checksum ( ");
		for (int i = 0; i < getEntryList().size(); i++) {
			Entry entry = getEntryList().get(i);
			unformatted.append(entry.getText());
			if (i != getEntryList().size() - 1) {
				unformatted.append(", ");
			}
		}
		unformatted.append(" )");
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