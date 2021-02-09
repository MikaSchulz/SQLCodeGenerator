package me.mikaschulz.codegenerator.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperationConstructor {

	private Class<?> operationClass;
	private boolean formatted;
	private List<Object> parameterList;

	public OperationConstructor(Class<?> operationClass, boolean formatted, Object... parameter) {
		this.operationClass = operationClass;
		this.formatted = formatted;
		this.parameterList = new ArrayList<>(Arrays.asList(parameter));
	}

	public Class<?> getOperationClass() {
		return operationClass;
	}

	public void setOperationClass(Class<?> operationClass) {
		this.operationClass = operationClass;
	}

	public boolean isFormatted() {
		return formatted;
	}

	public void setFormatted(boolean formatted) {
		this.formatted = formatted;
	}

	public List<Object> getParameterList() {
		return parameterList;
	}

	public void setParameterList(List<Object> parameterList) {
		this.parameterList = parameterList;
	}
}
