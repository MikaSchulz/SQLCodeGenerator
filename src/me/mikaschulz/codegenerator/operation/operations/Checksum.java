package me.mikaschulz.codegenerator.operation.operations;

import me.mikaschulz.codegenerator.entry.OutputValue;
import me.mikaschulz.codegenerator.operation.Operation;

public class Checksum extends Operation {

	public Checksum(boolean formatted) {
		super(formatted);
	}

	protected String toFormattedString() {
		StringBuilder formatted = new StringBuilder();
		formatted.append("Checksum (\n");

		for (int i = 0; i < getOutputValues().size(); i++) {
//			System.out.println(name);
			formatted.append("\t");
			if (i == 0) {
				formatted.append(" ");
			} else {
				formatted.append(",");
			}
			OutputValue outputValue = getOutputValues().get(i);
			if (!outputValue.getPrefix().isEmpty()) formatted.append(outputValue.getPrefix()).append(".");
			formatted.append(outputValue.getEntry().getText());
			formatted.append("\n");
		}
		formatted.append(")");
		return formatted.toString();
	}

	protected String toUnformattedString() {
		StringBuilder unformatted = new StringBuilder();
		unformatted.append("Checksum ( ");
		for (int i = 0; i < getOutputValues().size(); i++) {
			OutputValue outputValue = getOutputValues().get(i);
			if (!outputValue.getPrefix().isEmpty()) unformatted.append(outputValue.getPrefix()).append(".");
			unformatted.append(outputValue.getEntry().getText());
			if (i != getOutputValues().size() - 1) {
				unformatted.append(", ");
			}
		}
		unformatted.append(" )");
		return unformatted.toString();
	}

}