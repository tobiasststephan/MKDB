package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import xml.*;

public class MKDBConnection {
	private File file;
	private ConcurrentHashMap<String, MKDBTable> tables;
	
	private ConcurrentHashMap<String, MKDBTable> read(File file) {
		ConcurrentHashMap<String, MKDBTable> result = new ConcurrentHashMap<String, MKDBTable>();
		
		if(file.length() != 0) {
			try {
				JAXBContext context = JAXBContext.newInstance(Tables.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				Tables tables = (Tables) unmarshaller.unmarshal(file);
				
				for(Table table : tables.getTables()) {
					List<MKDBLine> lines = new ArrayList<MKDBLine>();
					
					for(Line line : table.getLines()) {
						String exp = "";
						
						for(Value value : line.getValues()) {
							exp += value.getVar() + "=" + value.getValue() + ",";
						}
						
						lines.add(new MKDBLine(exp.substring(0, exp.length() - 1)));
					}
					
					result.put(table.getName(), new MKDBTable(lines, table.getVars(), table.getName()));
				}
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public MKDBConnection(File file) {
		this.file = file;
		
		tables = read(this.file);
	}
	
	public MKDBConnection(String filepath) {
		file = new File(filepath);
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		tables = read(this.file);
	}
	
	public MKDBTable getTable(String name) {
		return tables.get(name);
	}
	
	public void createTable(String name, String vars) {
		boolean found = false;
		
	    @SuppressWarnings("rawtypes")
		Enumeration enu = this.tables.keys(); 
		
	    while(enu.hasMoreElements()) {
	    	MKDBTable table = this.tables.get(enu.nextElement());
	    	
	    	if(table.getName().equals(name)) {
	    		found = true;
	    	}
	    }
		
		if(!found) {
			tables.put(name, new MKDBTable(new ArrayList<MKDBLine>(), vars, name));
		}
	}
	
	public void deleteTable(String name) {
		tables.remove(name);
	}
	
	public void save() {
		try {
		    JAXBContext context = JAXBContext.newInstance(Tables.class);
	
		    Marshaller marshaller = context.createMarshaller();
	
		    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
		    List<Table> tables = new ArrayList<Table>();
		    
		    @SuppressWarnings("rawtypes")
			Enumeration enu = this.tables.keys(); 
		    
		    while(enu.hasMoreElements()) {
		    	MKDBTable table = this.tables.get(enu.nextElement());
		    	
		    	if(table.getName() != "") {
			    	List<Line> lines = new ArrayList<Line>();
			    	
			    	for(MKDBLine line : table.getLines()) {
			    		List<Value> values = new ArrayList<Value>();
			    		
			    		for(String val : table.getVars().split(",")) {
			    			//System.out.println(val);
			    			values.add(new Value(val, line.getValue(val)));
			    		}
			    		
			    		lines.add(new Line(values));
			    	}
			    	
			    	tables.add(new Table(lines, table.getVars(), table.getName()));
		    	}
		    }
		    
		    marshaller.marshal(new Tables(tables), file);

		} catch (JAXBException ex) {
		    ex.printStackTrace();
		}
	}
	
	public void disconnect() {
		save();
		
		file = null;
	}
}
