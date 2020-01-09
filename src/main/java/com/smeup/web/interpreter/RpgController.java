package com.smeup.web.interpreter;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RpgController implements Serializable {

	/**
	 * WARNING: On Windows remember to start payara with this JVM option:
	 * -Dfile.encoding=UTF-8
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private RpgSource rpgSource;

	@Inject
	private Intepreter interpreter;

	private String interpretationOutput;
	private String rpgPreloaded;
	private Map<String, Object> rpgPreloadedValues;
	private String elapsedTime;
	private Map<String, Object> themePreloadedValues;
	private String themePreloaded;

	@PostConstruct
	public void initPreloadedContent() {
		rpgPreloadedValues = new LinkedHashMap<String, Object>();
		rpgPreloadedValues.put("Samples...", "");
		rpgPreloadedValues.put("Hello world", HardcodedRPG.HELLO_WORLD.getSource());
		rpgPreloadedValues.put("Entry plist", HardcodedRPG.ENTRY_PLIST.getSource());
		rpgPreloadedValues.put("Fibonacci", HardcodedRPG.FIBONACCI.getSource());
		rpgPreloadedValues.put("Java call", HardcodedRPG.JAVA_CALL.getSource());

		themePreloadedValues = new LinkedHashMap<String, Object>();
		themePreloadedValues.put("Dark", "dark-theme");
		themePreloadedValues.put("Light", "light-theme");
	}

	public Map<String, Object> getRpgPreloadedValue() {
		return rpgPreloadedValues;
	}

	public Map<String, Object> getThemePreloadedValue() {
		return themePreloadedValues;
	}

	public String getRpgContent() {
		return rpgSource.getContent();
	}

	public void setRpgContent(final String content) {
		rpgSource.setContent(content);
	}

	public String getRpgParmList() {
		return rpgSource.getRpgParmList();
	}

	public void setRpgParmList(final String parmList) {
		rpgSource.setRpgParmList(parmList);
	}

	public void interpretate() {
		Instant beginOperation = Instant.now();
		String response = interpreter.interpretate(getRpgContent(), getRpgParmList());
		Instant endOperation = Instant.now();
		calculateElapsedTime(beginOperation, endOperation);
		setInterpretationOutput(response);
	}

	private void calculateElapsedTime(final Instant beginOperation, final Instant endOperation) {
		Duration duration = Duration.between(beginOperation, endOperation);
		setElapsedTime("Started: " + beginOperation.toString() + "<br>Ended: " + endOperation + "<br>Elapsed: "
				+ humanReadableFormat(duration));
	}

	public static String humanReadableFormat(Duration duration) {
		return duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
	}

	public String getInterpretationOutput() {
		return interpretationOutput;
	}

	public void setInterpretationOutput(String interpretationOutput) {
		this.interpretationOutput = interpretationOutput;
	}

	public String getRpgPreloaded() {
		return rpgPreloaded;
	}

	public void setRpgPreloaded(String rpgPreloaded) {
		this.rpgPreloaded = rpgPreloaded;
	}

	public String getThemePreloaded() {
		return themePreloaded;
	}

	public void setThemePreloaded(String themePreloaded) {
		this.themePreloaded = themePreloaded;
	}

	public void valueChangeMethod(AjaxBehaviorEvent abe) {
		setRpgContent(programFromGUI(abe));
	}

	private String programFromGUI(AjaxBehaviorEvent abe) {
		return (String) ((UIOutput) abe.getSource()).getValue();
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getElapsedTime() {
		return elapsedTime;
	}

}
