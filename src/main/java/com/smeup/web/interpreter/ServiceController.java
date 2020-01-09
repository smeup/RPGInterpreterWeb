package com.smeup.web.interpreter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.smeup.rpgparser.execution.CommandLineProgram;
import com.smeup.rpgparser.execution.RunnerKt;
import com.smeup.rpgparser.jvminterop.JavaSystemInterface;

@Named
@RequestScoped
public class ServiceController implements Serializable {

    private static final long serialVersionUID = 1L;
    private CommandLineProgram commandLineProgram;
    private JavaSystemInterface javaSystemInterface;
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;

    private Map<String,String> requestParams;
    private String interpretationOutput;
    
    private String beginOperation;
    private String endOperation;
    private String elapsedTime;
    private String duration;
    
    /**
     * curl --data-urlencode rpgSource="$(cat '/home/tron/rpg/CALCFIB.rpgle')" --data-binary rpgParms="10" -X POST http://localhost:8080/RPGInterpreterWeb/interpretate.xhtml
     */

    @PostConstruct
    public void initPreloadedContent() {        
        requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }

    private void interpretate(String source, String parms) {
        String rpgSource = lineEndingConversion(source);

        // To handle system.out response
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
        javaSystemInterface = new JavaSystemInterface(printStream);

        // load JD_URL commandLineProgram (a java programm called as an RPG from an interpreted RPG)
        javaSystemInterface.addJavaInteropPackage("com.smeup.jd");
        commandLineProgram = RunnerKt.getProgram(rpgSource, javaSystemInterface);

        List<String> rpgParms = new ArrayList<String>();
        if (null != parms && !"".equals(parms)) {
            String[] splitted = parms.split("\\|");
            rpgParms = Arrays.asList(splitted);
        }
        String response = callProgram(rpgParms);
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
        setBeginOperation(beginOperation.toString());
        setEndOperation(endOperation.toString());
        calculateElapsedTime(beginOperation, endOperation);
        String response = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        byteArrayOutputStream.reset();

        return response;
    }

    private void calculateElapsedTime(final Instant beginOperation, final Instant endOperation) {
        Duration d = Duration.between(beginOperation, endOperation);
        setDuration(humanReadableFormat(d));
        setElapsedTime("Started: " + beginOperation.toString() + "<br>Ended: " + endOperation + "<br>Elapsed: "
                + getDuration());
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

    public void renderJson() throws IOException {
        final String s = requestParams.get("rpgSource");
        final String p = requestParams.get("rpgParms"); 
        interpretate(s, p);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/json");
        externalContext.setResponseCharacterEncoding("UTF-8");
        Date date = new Date();
        String jsonStringResponse = "{ \"date\":\"" + date.toString() + 
        		"\" , \"begin\":\"" + getBeginOperation() + 
        		"\", \"end\":\"" + getEndOperation() + 
        		"\", \"duration\":\"" + getDuration() + 
        		"\", \"output\":\"" + getInterpretationOutput() + "\"}";
        externalContext.getResponseOutputWriter().write(jsonStringResponse);
        facesContext.responseComplete();
    }

	public String getBeginOperation() {
		return beginOperation;
	}

	public void setBeginOperation(String beginOperation) {
		this.beginOperation = beginOperation;
	}

	public String getEndOperation() {
		return endOperation;
	}

	public void setEndOperation(String endOperation) {
		this.endOperation = endOperation;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
