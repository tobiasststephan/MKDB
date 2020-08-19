package xml;

import java.util.List;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "tables")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tables {
	@XmlElement(name = "table")
	private List<Table> tables = null;
	
	public Tables() {
		
	}
	
	public Tables(List<Table> tables) {
		this.tables = tables;
	}
	
	public List<Table> getTables() {
		return tables;
	}
}
