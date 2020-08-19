package xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "value")
public class Value {
	private String var;
	private String value;

	public Value() {
		
	}
	
	public Value(String var, String value) {
		this.var = var;
		this.value = value;
	}
	
	@XmlAttribute(name = "var")
	public String getVar() {
		return var;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	@XmlElement(name = "v")
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
