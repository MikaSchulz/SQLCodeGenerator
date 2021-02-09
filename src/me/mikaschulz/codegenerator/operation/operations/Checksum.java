package me.mikaschulz.codegenerator.operation.operations;

import me.mikaschulz.codegenerator.operation.Operation;

public class Checksum extends Operation {

	public Checksum(boolean formatted) {
		super(formatted);
	}

	protected String toFormattedString() {
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
			formatted.append(getEntryList().get(i).getText());
			formatted.append("\n");
		}
		formatted.append(")");
		return formatted.toString();
	}

	protected String toUnformattedString() {
		StringBuilder unformatted = new StringBuilder();
		unformatted.append("Checksum ( ");
		for (int i = 0; i < getEntryList().size(); i++) {
			unformatted.append(getEntryList().get(i).getText());
			if (i != getEntryList().size() - 1) {
				unformatted.append(", ");
			}
		}
		unformatted.append(" )");
		return unformatted.toString();
	}

}