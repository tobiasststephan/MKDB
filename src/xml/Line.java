package xml;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "line")
public class Line {
	private List<Value> values;	

	public Line() {
		
	}
	
	public Line(List<Value> values) {
		this.values = values;
	}
	
	@XmlElementWrapper(name = "values")
	@XmlElement(name = "value")
	public List<Value> getValues() {
		return values;
	}
	
	public void setValues(List<Value> values) {
		this.values = values;
	}
}
