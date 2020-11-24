package com.smeup.web.interpreter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.inject.Inject;

import com.smeup.rpgparser.execution.CommandLineProgram;
import com.smeup.rpgparser.execution.RunnerKt;
import com.smeup.rpgparser.jvminterop.JavaSystemInterface;
import com.smeup.rpgparser.parsing.ast.MuteAnnotationExecuted;

public class Intepreter {

	@Inject
	private RpgController rpgController;
	
	private CommandLineProgram commandLineProgram;
	private JavaSystemInterface javaSystemInterface;
	private ByteArrayOutputStream byteArrayOutputStream;
	private PrintStream printStream;

	public LinkedHashMap<String, String> interpretate(final String rpgContent, final String rpgParameters) {
		
		LinkedHashMap<String, String> results = new LinkedHashMap<String, String>();
		String rpgSource = lineEndingConversion(rpgContent);

		// To handle system.out response
		byteArrayOutputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrayOutputStream);
		javaSystemInterface = new JavaSystemInterface(printStream);

		// load JD_URL commandLineProgram (a java programm called as an RPG from an
		// interpreted RPG)
		javaSystemInterface.addJavaInteropPackage("com.smeup.jd");
		List<String> parms = new ArrayList<String>();
		if (null != rpgParameters && !"".equals(rpgParameters)) {
			String[] splitted = rpgParameters.split("\\|");
			parms = Arrays.asList(splitted);
		}
		
		Instant beginOperation = Instant.now();
		commandLineProgram = RunnerKt.getProgram(rpgSource, javaSystemInterface);
		Instant endOperation = Instant.now();
		beginOperation = Instant.now();
		commandLineProgram.singleCall(parms);
		endOperation = Instant.now();
		
		rpgController.calculateElapsedTime(beginOperation, endOperation);
		String response = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
		String muteResponse = getMuteResults();
		
		byteArrayOutputStream.reset();
		
		results.put("RESPONSE", response);
		results.put("MUTERESPONSE", muteResponse);

		return results;
	}
	
	private String getMuteResults() {
		String muteResults = "";
		Set<Entry<Integer, MuteAnnotationExecuted>> executedAnnotations = javaSystemInterface.getExecutedAnnotation().entrySet();		
		for (Entry<Integer, MuteAnnotationExecuted> entry : executedAnnotations) {
			muteResults = muteResults + "Mute annotation at line " + entry.getKey() + " : " + entry.getValue().resultAsString().toUpperCase() + "\n";
		}
		System.out.println(muteResults);
		return muteResults;
	}

	private String lineEndingConversion(String rpgContent) {
		Scanner scanner = new Scanner(rpgContent);
		StringBuilder result = new StringBuilder();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			result.append(line);
			result.append(System.lineSeparator());
		}
		scanner.close();
		return result.toString();
	}

}
