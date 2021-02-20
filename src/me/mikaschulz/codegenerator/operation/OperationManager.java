package me.mikaschulz.codegenerator.operation;

import me.mikaschulz.codegenerator.operation.operations.Checksum;
import me.mikaschulz.codegenerator.operation.operations.EntryList;
import me.mikaschulz.codegenerator.operation.operations.Hash;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class OperationManager {

	private static final Map<String, Class<?>> operationMap;

	static {
		operationMap = new HashMap<>();

//		OperationConstructor formattedHash = construct(Hash.class, true);
//		OperationConstructor formattedChecksum = construct(Checksum.class, true);
//
//		operationMap.put("HASH", formattedHash);
//		operationMap.put("FHASH", formattedHash);
//		operationMap.put("UHASH", construct(Hash.class, false));
//		operationMap.put("CHECKSUM", formattedChecksum);
//		operationMap.put("FCHECKSUM", formattedChecksum);
//		operationMap.put("UCHECKSUM", construct(Checksum.class, false));


		operationMap.put("", EntryList.class);
		operationMap.put("HASH", Hash.class);
		operationMap.put("CHECKSUM", Checksum.class);
//		operationMap.put("BOOL", Bool.class);
	}

//	public static Class<?>[] getOperationParameters(String name) {
//		for (Map.Entry<String, Class<?>[]> operation : operationMap.entrySet()) {
//			String operationName = operation.getKey();
//			if (name.equalsIgnoreCase(operationName)) {
//				return operation.getValue();
//			}
//		}
//		return null;
//	}


	public static Operation createOperation(String operationName) {

		Class<?> operationClass = operationMap.get(operationName.toUpperCase());
		if (operationClass == null) return null;

		try {
			return (Operation) operationClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException
				| NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

//	private static OperationConstructor getOperationConstructor(String name) {
//		for (Map.Entry<String, OperationConstructor> operation : operationMap.entrySet()) {
//			String operationName = operation.getKey();
//			if (name.toUpperCase().equals(operationName)) {
//				return operation.getValue();
//			}
//		}
//		return null;
//	}

//	private static OperationConstructor construct(Class<?> operationClass, boolean formatted, Object... parameter) {
//		return new OperationConstructor(operationClass, formatted, parameter);
//	}

}
