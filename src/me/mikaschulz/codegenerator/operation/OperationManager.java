package me.mikaschulz.codegenerator.operation;

import me.mikaschulz.codegenerator.operation.operations.Checksum;
import me.mikaschulz.codegenerator.operation.operations.Hash;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class OperationManager {

	private static final Map<String, OperationConstructor> operationMap;

	static {
		operationMap = new HashMap<>();

		OperationConstructor formattedHash = construct(Hash.class, true);
		OperationConstructor formattedChecksum = construct(Checksum.class, true);

		operationMap.put("HASH", formattedHash);
		operationMap.put("FHASH", formattedHash);
		operationMap.put("UHASH", construct(Hash.class, false));
		operationMap.put("CHECKSUM", formattedChecksum);
		operationMap.put("FCHECKSUM", formattedChecksum);
		operationMap.put("UCHECKSUM", construct(Checksum.class, false));
	}

	public static Operation createOperation(String operationName) {

		OperationConstructor contructor = getOperationConstructor(operationName);
		if (contructor == null) return null;

		try {
			return (Operation) contructor.getOperationClass().getDeclaredConstructor(Boolean.TYPE)
					.newInstance(contructor.isFormatted());
//			return (Operation) Objects.requireNonNull(getOperationConstructor(operationName)).newInstance();
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static OperationConstructor getOperationConstructor(String name) {
		for (Map.Entry<String, OperationConstructor> operation : operationMap.entrySet()) {
			String operationName = operation.getKey();
			if (name.toUpperCase().equals(operationName)) {
				return operation.getValue();
			}
		}
		return null;
	}

	private static OperationConstructor construct(Class<?> operationClass, boolean formatted, Object... parameter) {
		return new OperationConstructor(operationClass, formatted, parameter);
	}

}
