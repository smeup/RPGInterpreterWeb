package com.smeup.web.interpreter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.smeup.rpgparser.execution.CommandLineProgram;
import com.smeup.rpgparser.execution.RunnerKt;
import com.smeup.rpgparser.jvminterop.JavaSystemInterface;
import javax.faces.context.FacesContext;


@Named
@RequestScoped
public class RpgController implements Serializable {

    /**
     * WARNING: On Windows remember to start payara with this JVM option:
     * -Dfile.encoding=UTF-8
     */
    private static final long serialVersionUID = 1L;
    private CommandLineProgram commandLineProgram;
    private JavaSystemInterface javaSystemInterface;
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;

    @Inject
    private RpgSource rpgSource;

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
        String rpgSource = lineEndingConversion(getRpgContent());

        // To handle system.out response
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
        javaSystemInterface = new JavaSystemInterface(printStream);

        // load JD_URL commandLineProgram (a java programm called as an RPG from an interpreted RPG)
        javaSystemInterface.addJavaInteropPackage("com.smeup.jd");
        commandLineProgram = RunnerKt.getProgram(rpgSource, javaSystemInterface);

        List<String> parms = new ArrayList<String>();
        if (null != getRpgParmList() && !"".equals(getRpgParmList())) {
            String[] splitted = getRpgParmList().split("\\|");
            parms = Arrays.asList(splitted);
        }
        String response = callProgram(parms);
        setInterpretationOutput(response);
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

    public String getInterpretationOutput() {
        return interpretationOutput;
    }

    public void setInterpretationOutput(String interpretationOutput) {
        this.interpretationOutput = interpretationOutput;
    }

    private String callProgram(final List<String> parms) {
        Instant beginOperation = Instant.now();
        commandLineProgram.singleCall(parms);
        Instant endOperation = Instant.now();
        calculateElapsedTime(beginOperation, endOperation);
        String response = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        byteArrayOutputStream.reset();

        return response;
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

    private void calculateElapsedTime(final Instant beginOperation, final Instant endOperation) {
        Duration duration = Duration.between(beginOperation, endOperation);
        setElapsedTime("Started: " + beginOperation.toString() + "<br>Ended: " + endOperation + "<br>Elapsed: "
                + humanReadableFormat(duration));
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public static String humanReadableFormat(Duration duration) {
        return duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
    }

}
