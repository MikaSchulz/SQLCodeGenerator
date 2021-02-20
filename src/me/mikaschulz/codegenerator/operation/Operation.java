package me.mikaschulz.codegenerator.operation;

import me.mikaschulz.codegenerator.entry.Entry;
import me.mikaschulz.codegenerator.entry.entries.TextEntry;

public abstract class Operation extends TextEntry {

//	public boolean addParameter(String parameter) {
//		System.out.println("parameter: " + parameter);
////		Entry entry;
//		String[] splitted = parameter.split("\\.");
//		System.out.println(Arrays.toString(splitted));
//		if (splitted.length > 2) return false;
//		if (splitted.length == 2) {
//			PrefixEntry prefixEntry = new PrefixEntry();
//			prefixEntry.setPrefix(splitted[0]);
//			prefixEntry.setText(splitted[1]);
//			return addParameter(prefixEntry);
//		} else {
//			return addParameter((Object) parameter);
//		}
//	}

	private int paramCount = -1;

//	public abstract boolean addParameter(Object parameter);
	public boolean addParameter(Entry entry) {
		paramCount++;
		return addOperationParameter(entry);
	}
	protected abstract boolean addOperationParameter(Entry entry);

	protected int getParamCount() {
		return paramCount;
	}

	protected void setParamCount(int count) {
		paramCount = count;
	}

	//	private final List<OutputValue> outputValues = new ArrayList<>();
//	private final boolean formatted;
//
//	public Operation(boolean formatted) {
//		this.formatted = formatted;
//	}
//
//	public List<OutputValue> getOutputValues() {
//		return outputValues;
//	}
//
//	public void addOutputValue(OutputValue value) {
//		outputValues.add(value);
//	}
//
//	public boolean isFormatted() {
//		return formatted;
//	}
//
//	@Override
//	public String getText() {
//		return (isFormatted() ? toFormattedString() : toUnformattedString());
//	}
//
//	protected abstract String toFormattedString();
//
//	protected abstract String toUnformattedString();
}
