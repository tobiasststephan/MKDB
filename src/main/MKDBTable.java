package main;

import java.util.List;

public class MKDBTable {
	private List<MKDBLine> lines;
	private String vars;
	private String name;
	
	public MKDBTable(List<MKDBLine> lines, String vars, String name) {
		this.lines = lines;
		this.vars = vars;
		this.name = name;
	}
	
	public List<MKDBLine> getLines() {
		return lines;
	}
	
	public MKDBLine getLine(int num) {
		return lines.get(num);
	}
	
	public MKDBLine getLine(String cond) {
		for(MKDBLine line : this.lines) {
			if(line.getValue(cond.split("=")[0]).equals(cond.split("=")[1])) {
				return line;
			}
		}
		
		return null;
	}
	
	public void addLine() {
		String exp = "";
		
		for(String var : vars.split(",")) {
			exp += var + "=_NULL_,";
		}
		
		lines.add(new MKDBLine(exp.substring(0, exp.length() - 1)));
	}
	
	public void addLine(String values) {
		lines.add(new MKDBLine(values));
	}
	
	public void addLine(MKDBLine line) {
		lines.add(line);
	}
	
	public void deleteLine(int num) {
		lines.remove(num);
	}
	
	public void deleteLine(String cond) {
		int num = -1;
		
		for(int i = 0; i < lines.size(); i++) {
			MKDBLine line = lines.get(i);
			
			for(String var : vars.split(",")) {
				if(line.getValue(var).equals(cond.split("=")[1]) && var.equals(cond.split("=")[0])) {
					num = i;
				}
			}
		}
		
		lines.remove(num);
	}
	
	public String getVars() {
		return vars;
	}
	
	public String getName() {
		return name;
	}
}
