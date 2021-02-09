package me.mikaschulz.codegenerator.operation.operations;

import me.mikaschulz.codegenerator.operation.Operation;

public class Hash extends Operation {

	public Hash(boolean formatted) {
		super(formatted);
	}

	protected String toFormattedString() {

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
			formatted.append(getEntryList().get(i).getText());
			formatted.append("\n");
		}
		formatted.append("), 2)");
		return formatted.toString();
	}

	protected String toUnformattedString() {
		StringBuilder unformatted = new StringBuilder();
		unformatted.append("CONVERT(VARCHAR(32), HashBytes('MD5', ");
		for (int i = 0; i < getEntryList().size(); i++) {
			unformatted.append(getEntryList().get(i).getText());
			if (i != getEntryList().size() - 1) {
				unformatted.append(" + ");
			}
		}
		unformatted.append("), 2)");
		return unformatted.toString();
	}
}
