package com.smeup.jd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.smeup.rpgparser.interpreter.ArrayType;
import com.smeup.rpgparser.interpreter.Program;
import com.smeup.rpgparser.interpreter.ProgramParam;
import com.smeup.rpgparser.interpreter.StringType;
import com.smeup.rpgparser.interpreter.StringValue;
import com.smeup.rpgparser.interpreter.SystemInterface;
import com.smeup.rpgparser.interpreter.Value;

public class JD_URL implements Program {

	private List<ProgramParam> parms;

	public JD_URL() {
		parms = new ArrayList<ProgramParam>();
		parms.add(new ProgramParam("U$FUNZ", new StringType(10)));
		parms.add(new ProgramParam("U$METO", new StringType(10)));
		parms.add(new ProgramParam("U$SVARSK", new ArrayType(new StringType(1050), 200, 0)));
	}

	public String urlCall(final String urlToCall) {
		URL url;
		String responseAsString = "";
		try {
			url = new URL(urlToCall);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			responseAsString = content.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseAsString = "*ERROR " + e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseAsString = "*ERROR " + e.getMessage();
		}
		return responseAsString;

	}

	@Override
	public List<Value> execute(SystemInterface arg0, LinkedHashMap<String, Value> arg1) {
		ArrayList<Value> arrayListResponse = new ArrayList<Value>();
		for (Map.Entry<String, ? extends Value> entry : arg1.entrySet()) {
			if ("U$SVARSK".equals(entry.getKey().toString())) {
				String response = urlCall(entry.getValue().asString().getValue());
				arrayListResponse.add(new StringValue(response.trim()));
			} else {
				arrayListResponse.add(entry.getValue());
			}
		}
		return arrayListResponse;
	}

	@Override
	public List<ProgramParam> params() {
		return parms;
	}

}
