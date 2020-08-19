MineKingDataBase (MKDB) ist eine einfach zu benutzende Datenbank für java. Kurze erläuterung:

Classen:
-MKDBConnection (wird benutzt um eine Datenbank verbindung zu einer Datei aufzubauen)
-MKDBTable      (Ist eine Tabelle in einer Datenbank)
-MKDBLine       (Ist eine Zeile in einer Tabelle)

Beispiel:
MKDBConnection conn = new MKDBConnection("datenbank.mkdb"); //1. Argument: Pfad zur datenbankdatei
conn.createTable("Test", "var1,var2,var3") //1. Argument: Tabellenname, 2. Argument: Variablen

MKDBTable table = conn.getTable("Test"); //1. Argument: Tabellenname
table.addLine("var=val2,var2=val2,var3=val3"); 1.Argument: Werte

//Möglichkeit 1:
System.out.println(table.getLine(0).getValue("var1")); //getLine(): 1. Argument: Zeilenindex   getValue(): 1. Argument: Variablenname
//Möglichkeit 2:
System.out.println(table.getLine("var2=val2").getValue("var1")); //getLine(): 1. Argument: Zeilenindex   Anmerkung: momentan ist es nicht möglich mehrere Bedingungen anzugeben

//Mehrer Bidingungen:
MKDBLine line = table.getLine("var2=val2");
if(line.getValue("var3").equals("val3")) {
	System.out.println(line.getValue("var3"));
}
