package me.mikaschulz.codegenerator;

import me.mikaschulz.codegenerator.entry.Entry;
import me.mikaschulz.codegenerator.entry.entries.ColumnEntry;
import me.mikaschulz.codegenerator.entry.entries.TextEntry;
import me.mikaschulz.codegenerator.operation.Operation;
import me.mikaschulz.codegenerator.operation.OperationManager;
import me.mikaschulz.codegenerator.operation.operations.Checksum;
import me.mikaschulz.codegenerator.operation.operations.Hash;

import java.util.*;

public class CodeGenerator {

	/*
	 * ###TARGET_COLUMNS###					- Spalten der Zieltabelle
	 * ###SOURCE_COLUMNS###					- Spalten der Quelltabelle
	 *
	 * ###OUTPUT_VALUES###					- Ausgabewerte für Debugging
	 * ###INSERT_VALUES###					- Werte die im Ziel eingefügt werden
	 * ###CHECK_VALUES###					- Werte die auf Ungleichheit überprüft werden
	 *
	 * #HASH#column#						- Verhasht die Spalte "column" mit MD5
	 * #FCHECKSUM#column1,column2#			- Kreiert eine formatierte Checksumme für die Spalte "column1", "column2" ...
	 * #UCHECKSUM#column1,column2#			- Kreiert eine unformatierte Checksumme für die Spalte "column1", "column2" ...
	 *
	 */

	private static final int SOURCE = 0;
	private static final int TARGET = 1;

	private static final Map<ColumnEntry, Integer> columnMap = new LinkedHashMap<>();
	private static final Map<Entry, String> insertValues = new LinkedHashMap<>();

	/*
		VERALTET:
		private static List<Column> targetColumns = new ArrayList<>();
		private static List<Column> sourceColumns = new ArrayList<>();
		private static List<String> insertValues = new ArrayList<>();

	 */

	public static void main(String[] args) {

		// Scannt die Eingaben
		Scanner scan = new Scanner(System.in);
		// Eingaben werden mit ";" getrennt
		scan.useDelimiter(";");

		System.out.println("Gib die Spalten der Quelltabelle an (Kommasepariert, Beenden mit \";\"):");
		addToColumns(scan.next(), SOURCE);

		System.out.println("Gib die Spalten und Datentypen der Zieltabelle an (Kommasepariert, Beenden mit \";\"):");
		addToColumns(scan.next(), TARGET);

		columnMap.forEach((column, table) -> System.out.println(((table == 0) ? "SOURCE" : "TARGET")
				+ " " + column.getText() + " " + column.getDataType()));

		System.out.println("Gib die Werte an, die eingefügt werden sollen (Kommasepariert, Beenden mit \";\"):");

		String trimmed = scan.next().replaceAll("\\s","");
		List<String> columnsList = new LinkedList<>(Arrays.asList(trimmed.split("#")));
		System.out.println(trimmed);
		columnsList.forEach(System.out::println);

		ListIterator<String> columnIterator = columnsList.listIterator();
		while (columnIterator.hasNext()) {
			String column = columnIterator.next();
			column = trimCommas(column);
			if (column.isEmpty()) {
				columnIterator.remove();
			} else {
				columnIterator.set(column);
			}
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		columnsList.forEach(System.out::println);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		Operation currentOperation = null;

		for (int i = 0; i < columnsList.size(); i++) {

			String input = columnsList.get(i);

//			List<String> columns = Arrays.asList(input.split(","));
//
//			if (currentOperation == null) {
//				currentOperation = OperationManager.createOperation(input);
//				System.out.println("OPERATION GESETZT: " + ((currentOperation == null) ? "NULL" : "NOT NULL"));
//				if (currentOperation != null) continue;
//				for (int x = 0; x < columns.size(); x++) {
//					System.out.println(x + ": " +  columns.get(x));
//				}
//			}


//			if (currentOperation != null) {
//				// Operation mit Colums ausstatten
//				System.out.println("OPERATION VORHANDEN");
//				for (String text : columns) {
//					String[] nameAndPrefix = getNameAndPrefix(text);
//					if (nameAndPrefix == null) {
//						throwMessage(5);
//						return;
//					}
//					String
//					currentOperation.addEntry(getEntry(text));
//				}
//				System.out.println(currentOperation.isFormatted());
//				System.out.println(currentOperation.toString());
//				currentOperation = null;
//			} else {
//				currentOperation = OperationManager.createOperation(input);
//				System.out.println("OPERATION GESETZT: " + ((currentOperation == null) ? "NULL" : "NOT NULL"));
//				if (currentOperation == null) {
//					for (int x = 0; x < columns.size(); x++) {
//						System.out.println(x + ": " +  columns.get(x));
//					}
//				}
//			}

			String[] columns = input.split(",");
			StringBuilder builder = new StringBuilder();
			builder.append("--------------------------").append("\n");
			builder.append("columnList no. ").append(i).append("\n");
			for (String column : columns) {
				builder.append(column);
				builder.append("\n");
			}
			builder.append("--------------------------");
			System.out.println(builder.toString());

			if (currentOperation == null) {
				currentOperation = OperationManager.createOperation(input);
				System.out.println("OPERATION GESETZT: " + ((currentOperation == null) ? "NULL" : "NOT NULL"));
				if (currentOperation != null) continue;
			}
			for (String text : columns) {
				System.out.println("#####################");
				String[] nameAndPrefix = getNameAndPrefix(text);
				if (nameAndPrefix == null) {
					throwMessage(5);
					return;
				}
				String name = nameAndPrefix[0];
				String prefix = nameAndPrefix[1];

				System.out.println("prefix: " + prefix);
				System.out.println("name: " + name);
				Entry entry = getEntry(name, text);
				System.out.println("#####################");

				if (currentOperation != null) {
					currentOperation.addEntry(entry);
				} else {
					insertValues.put(entry, prefix);
				}

			}
			if (currentOperation != null) {
				insertValues.put(currentOperation, "");
				currentOperation = null;
			}

			for (Map.Entry<Entry, String> values : insertValues.entrySet()) {
				Entry entry = values.getKey();
				String prefix = values.getValue();
				if (entry instanceof ColumnEntry) {
					ColumnEntry columnEntry = (ColumnEntry) entry;
					System.out.println("COLUMN: " + columnEntry.getText() + ", DATATYPE: "
							+ columnEntry.getDataType() + ", PREFIX: " + prefix);

				} else if (entry instanceof TextEntry) {
					TextEntry textEntry = (TextEntry) entry;
					System.out.println("TEXTENTRY: " + entry.getText() + ", PREFIX: " + prefix);
				} else if (entry instanceof Operation) {
					Operation operation = (Operation) entry;
					if (operation instanceof Checksum) {
						Checksum checksum = (Checksum) operation;
						System.out.println("CHECKSUM: " + checksum.getText());
					} else if (operation instanceof Hash) {
						Hash hash = (Hash) operation;
						System.out.println("HASH: " + hash.getText());
					}
				}
			}




		}
		
		
		
		
//		for (int i = 0; i < columnsList.size(); i++) {
////			System.out.println(i + " " + columnArray[i]);
//			String column = columnsList.get(i);
//			System.out.println(i + " " + column);
////			if (column.equals(",")) column = "";
////			if (column.startsWith(",")) column = column.substring(1);
////			if (column.endsWith(",")) column = column.substring(0, column.length() - 2);
//			if (i % 3 == 0) {
////				List<String> values = Arrays.asList(arrays[i].split(",").fi);
////				values.stream().filter(String::isEmpty).toar;
//
//				List<String> columns = Arrays.asList(column.split(","));
////				Arrays.stream(column.split(","))
////						.filter(n -> (!n.isEmpty())).collect(Collectors.toList());
//				columns.forEach(System.out::println);
//
////				for (String s : columns) {
////					System.out.println("xxxx " + s);
////				}
////				values.removeAll(Arrays.asList("", null));
//				insertValues.addAll(columns);
//			}
//			if ((i-1) % 3 == 0) {
//				operation = column;
//			}
//			if ((i-2) % 3 == 0) {
////				System.out.println(operation);
////				System.out.println(arrays[i]);
//				insertValues.add(createOperation(operation, column));
//			}
//		}
//
////		insertValues.addAll(Arrays.asList(scan.next().replaceAll("\\s","").split(",")));
//		System.out.println("ENDE");
//		insertValues.forEach(System.out::println);

	}

	private static String[] getNameAndPrefix(String text) {
		String column = text;
		String prefix = "";
		String[] combo = column.split("\\.");
		if (combo.length > 2) return null;
		if (combo.length == 2) {
			prefix = combo[0];
			column = combo[1];
		}
		return new String[]{column, prefix};
	}

	private static void throwMessage(int messageCode) {
		switch (messageCode) {
			case 1:
				System.out.println("Erfolgreich ausgeführt");
			case 5:
				System.out.println("Konnte Spalte nicht korrekt von Prefix trennen. Zwei \".\" angegeben.");
		}
	}

	/**
	 * Entfernt alle überschüssigen Tabulatoren, Strings
	 * @param input Vom User eingegebenen Spalten
	 * @param table Gibt die Quelle SOURCE oder TARGET an
	 */
	public static void addToColumns(String input, int table) {

		if (table != 0 && table != 1) return;

		String[] combos = input.trim().replaceAll("[\\t\\n\\r]+","").split(",");
		for (String combo : combos) {
			List<String> comboArray = new ArrayList<>(Arrays.asList(combo.trim().split(" ")));
			comboArray.removeIf(String::isEmpty);
			columnMap.put(new ColumnEntry(comboArray.get(0), comboArray.get(1)), table);
		}
	}


	public static List<ColumnEntry> createColumnList(String input) {
		List<ColumnEntry> columnList = new ArrayList<>();
		String[] combos = input.trim().replaceAll("[\\t\\n\\r]+","").split(",");
		for (String combo : combos) {
			List<String> comboArray = new ArrayList<>(Arrays.asList(combo.trim().split(" ")));
			comboArray.removeIf(String::isEmpty);
			columnList.add(new ColumnEntry(comboArray.get(0), comboArray.get(1)));
		}
//		columnList.forEach(column -> System.out.println(column.getColumnName() + " " + column.getDataType()));
		return columnList;
	}

	public static String trimCommas(String input) {
		StringBuilder builder = new StringBuilder(input);
		while (builder.length() > 0 && builder.charAt(0) == ',') {
			builder.deleteCharAt(0);
		}
		while (builder.length() > 0 && builder.charAt(builder.length() - 1) == ',') {
			builder.setLength(builder.length() - 1);
		}
		return builder.toString();
	}

//	private static String createOperation(String operation, String value) {
//
//		if (operation.equalsIgnoreCase("HASH")) {
//			return "";
//		} else if (operation.toUpperCase().endsWith("CHECKSUM")) {
////			Checksum checksum = new Checksum(Arrays.asList(value.split(",")));
////			if (operation.toUpperCase().startsWith("F")) {
////				return checksum.toFormattedString();
////			} else if (operation.toUpperCase().startsWith("U")) {
////				return checksum.toUnformattedString();
////			}
//		}
//		return null;
//	}

	private static Entry getEntry(String column, String text) {
		for (ColumnEntry entry : columnMap.keySet()) {
			if (entry.getText().equals(column)) {
				return entry;
			}
		}
		return new TextEntry(text);
	}


}