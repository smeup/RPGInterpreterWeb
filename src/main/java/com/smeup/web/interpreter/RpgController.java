package com.smeup.web.interpreter;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
	private String muteOutput;

	private String rpgPreloaded;
	private Map<String, Object> rpgPreloadedValues;
	private String elapsedTime;
	private Map<String, Object> themePreloadedValues;
	private String themePreloaded;

	private final String HEADER_CONTENT_01 = ".....DName+++++++++++ETDsFrom+++To/L+++IDc.P.chiav.+++++++++++++++++++++++++++++Comments++++++++++++"
			+ "<br>";
	private final String HEADER_CONTENT_02 = ".....CL0N01Factor1+++++++Opcode&ExtFactor2+++++++Result++++++++Len++D+HiLoEq....Comments++++++++++++"
			+ "<br>";
	private final String HEADER_CONTENT_03 = ".....CL0N01Factor1+++++++Opcode&ExtExtended-factor2+++++++++++++++++++++++++++++Comments++++++++++++"
			+ "<br>";
	private final String HEADER_CONTENT_04 = " ....+... 1 ...+... 2 ...+... 3 ...+... 4 ...+... 5 ...+... 6 ...+... 7 ...+... 8 ...+... 9 ...+... 0";
	private String rpgHeaderContent1;
	private String rpgHeaderContent2;
	private String rpgHeaderContent3;
	private String rpgHeaderContent4;

	@PostConstruct
	public void initPreloadedContent() {
		rpgPreloadedValues = new LinkedHashMap<String, Object>();
		themePreloadedValues = new LinkedHashMap<String, Object>();
		themePreloadedValues.put("Dark theme", "dark-theme");
		themePreloadedValues.put("Light theme", "light-theme");
		setRpgHeaderContent1(HEADER_CONTENT_01);
		setRpgHeaderContent2(HEADER_CONTENT_02);
		setRpgHeaderContent3(HEADER_CONTENT_03);
		setRpgHeaderContent4(HEADER_CONTENT_04);
		
		loadRPGComboSources();
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
		LinkedHashMap<String, String> results = new LinkedHashMap<String, String>();
		results = interpreter.interpretate(getRpgContent(), getRpgParmList());
		Instant endOperation = Instant.now();
		calculateElapsedTime(beginOperation, endOperation);
		setInterpretationOutput(results.get("RESPONSE"));
		setMuteOutput(results.get("MUTERESPONSE"));
	}

	private void calculateElapsedTime(final Instant beginOperation, final Instant endOperation) {
		Duration duration = Duration.between(beginOperation, endOperation);
		setElapsedTime("Started: " + beginOperation.toString() + " - Ended: " + endOperation + " - Elapsed: "
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

	private void loadRPGComboSources() {
		rpgPreloadedValues.put("RPG source selection...", "");
		final URL url = this.getClass().getClassLoader().getResource("rpgle");
        // fixed issue on windows platform
		try (Stream<Path> walk = Files.walk(Paths.get(new File(url.getPath()).getAbsolutePath()))) {
			List<Path> result = walk.filter(Files::isRegularFile).map(x -> x.getFileName()).collect(Collectors.toList());
			result.forEach( (Path path) -> {
				try {
					String programName = path.toFile().getName();
					String fileName = url.getPath() + programName;
					programName = programName.substring(0, programName.length()-6);
                    // fixed issue on windows platform
					String programContent = getRpgleContent(new File(fileName).getAbsolutePath());
					rpgPreloadedValues.put(programName, programContent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getRpgleContent(final String rpgleFileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get(rpgleFileName)));
	}

	public String getMuteOutput() {
		return muteOutput;
	}

	public void setMuteOutput(String muteOutput) {
		this.muteOutput = muteOutput;
	}

	public String getRpgHeaderContent1() {
		return rpgHeaderContent1;
	}

	public void setRpgHeaderContent1(String rpgHeaderContent1) {
		this.rpgHeaderContent1 = rpgHeaderContent1;
	}

	public String getRpgHeaderContent2() {
		return rpgHeaderContent2;
	}

	public void setRpgHeaderContent2(String rpgHeaderContent2) {
		this.rpgHeaderContent2 = rpgHeaderContent2;
	}

	public String getRpgHeaderContent3() {
		return rpgHeaderContent3;
	}

	public void setRpgHeaderContent3(String rpgHeaderContent3) {
		this.rpgHeaderContent3 = rpgHeaderContent3;
	}

	public String getRpgHeaderContent4() {
		return rpgHeaderContent4;
	}

	public void setRpgHeaderContent4(String rpgHeaderContent4) {
		this.rpgHeaderContent4 = rpgHeaderContent4;
	}

}
