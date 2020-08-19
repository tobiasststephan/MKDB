package xml;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "table")
public class Table {
	private String name;
	private String vars;
	private List<Line> lines;
	
	public Table() {
		
	}
	
	public Table(List<Line> lines, String vars, String name) {
		this.lines = lines; 
		this.vars = vars;
		this.name = name;
	}
	
	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "vars")
	public String getVars() {
		return vars;
	}
	
	public void setVars(String vars) {
		this.vars = vars;
	}
	
	@XmlElementWrapper(name = "lines")
	@XmlElement(name = "line")
	public List<Line> getLines() {
		return this.lines;
	}
	
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
}
