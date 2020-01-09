package com.smeup.web.interpreter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.smeup.rpgparser.execution.CommandLineProgram;
import com.smeup.rpgparser.execution.RunnerKt;
import com.smeup.rpgparser.jvminterop.JavaSystemInterface;

public class Intepreter {

	private CommandLineProgram commandLineProgram;
	private JavaSystemInterface javaSystemInterface;
	private ByteArrayOutputStream byteArrayOutputStream;
	private PrintStream printStream;

	public String interpretate(final String rpgContent, final String rpgParameters) {
		String rpgSource = lineEndingConversion(rpgContent);

		// To handle system.out response
		byteArrayOutputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrayOutputStream);
		javaSystemInterface = new JavaSystemInterface(printStream);

		// load JD_URL commandLineProgram (a java programm called as an RPG from an
		// interpreted RPG)
		javaSystemInterface.addJavaInteropPackage("com.smeup.jd");
		commandLineProgram = RunnerKt.getProgram(rpgSource, javaSystemInterface);

		List<String> parms = new ArrayList<String>();
		if (null != rpgParameters && !"".equals(rpgParameters)) {
			String[] splitted = rpgParameters.split("\\|");
			parms = Arrays.asList(splitted);
		}

		commandLineProgram.singleCall(parms);
		String response = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
		byteArrayOutputStream.reset();

		return response;
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
