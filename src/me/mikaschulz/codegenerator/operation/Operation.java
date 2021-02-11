package me.mikaschulz.codegenerator.operation;

import me.mikaschulz.codegenerator.entry.Entry;
import me.mikaschulz.codegenerator.entry.OutputValue;

import java.util.ArrayList;
import java.util.List;

public abstract class Operation extends Entry {

	private final List<OutputValue> outputValues = new ArrayList<>();
	private final boolean formatted;

	public Operation(boolean formatted) {
		this.formatted = formatted;
	}

	public List<OutputValue> getOutputValues() {
		return outputValues;
	}

	public void addOutputValue(OutputValue entry) {
		outputValues.add(entry);
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
