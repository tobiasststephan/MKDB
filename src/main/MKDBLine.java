package main;

import java.util.concurrent.ConcurrentHashMap;

public class MKDBLine {
	private ConcurrentHashMap<String, String> vars;
	
	public MKDBLine(String values) {
		vars = new ConcurrentHashMap<String, String>();
		
		for(String exp : values.split(",")) {
			if(exp.split("=").length == 1 || exp.split("=")[1].equals("_NULL_")) {
				vars.put(exp.split("=")[0], "");
			}
			
			else {
				vars.put(exp.split("=")[0], exp.split("=")[1]);
			}
		}
	}
	
	public String getValue(String name) {
		return vars.get(name);
	}
	
	public void setValue(String var, String value) {
		vars.remove(var);
		vars.put(var, value);
	}
}
