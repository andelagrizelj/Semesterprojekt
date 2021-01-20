import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Klinikum {

	public String name, anschrift;
	public int anzahlAngestellten, anzahlPatienten, id;
	public double durchschnittsgehalt;
	
	public Klinikum (int id, String name, String anschrift, int anzahlAngestellten, int anzahlPatienten, double durchschnittsgehalt) {
		this.id = id;
		this.name = name;
		this.anschrift = anschrift;
		this.anzahlAngestellten = anzahlAngestellten;
		this.anzahlPatienten = anzahlPatienten;
		this.durchschnittsgehalt = durchschnittsgehalt;
	}
	
	public String toString() {
		
		return "Klinikum (ID " + id + "): Name: " + name + ", Anschrift: " + anschrift + ", Anzahl der Angestellten: " + anzahlAngestellten + ", Anzahl der Patienten: " + anzahlPatienten + ", Durchschnittsgehalt: " + durchschnittsgehalt + "\n";
	}
	
	public static ArrayList <Klinikum> KlinikenFromFile(String filename) throws FileNotFoundException{
		
		Scanner scanner = new Scanner (new File (filename));
		ArrayList <Klinikum> allekliniken = new ArrayList <>();
		while (scanner.hasNext()) {
			String t = (scanner.nextLine());
			String[] b = t.split(";");
			Klinikum c = new Klinikum (Integer.parseInt(b[0]), b[1], b[2], Integer.parseInt(b[3]), Integer.parseInt(b[4]), Double.parseDouble(b[5]));
			allekliniken.add(c);	
		}
		return allekliniken;
	}
	
	public static void showAlleKliniken() throws FileNotFoundException{
		System.out.println("ID    \t\t    Name \t\t  Anschrift \t\t\t    Angestellten \t\t Patienten (01.21)\t  Durchschnittsgehalt" );
		ArrayList <Klinikum> a =  KlinikenFromFile("src/Kliniken.csv");
		for (int i = 0; i<a.size(); i++) {
			System.out.println(a.get(i).id + "\t\t" + a.get(i).name + "\t\t" + a.get(i).anschrift + "\t\t\t\t" + a.get(i).anzahlAngestellten + "\t\t\t\t" + a.get(i).anzahlPatienten + "\t\t\t" + a.get(i).durchschnittsgehalt);
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}
	
	public static Klinikum NiedrigsteDurchschnittsgehalt (ArrayList<Klinikum> allekliniken) {
		
		Klinikum k = allekliniken.get(0);
		for (int i = 1; i < allekliniken.size(); i++) {
			if (allekliniken.get(i).durchschnittsgehalt < k.durchschnittsgehalt) {
				k = allekliniken.get(i);
			}
		}
		return k;
	}
	
	public static Klinikum MeistenPatienten(ArrayList<Klinikum> allekliniken) {
		
		Klinikum k = allekliniken.get(0);
		for (int i = 1; i < allekliniken.size(); i++) {
			if (allekliniken.get(i).anzahlPatienten > k.anzahlPatienten) {
				k = allekliniken.get(i);
			}
		}
		return k;
	}
	
	public static void sucheKlinikum (int n) throws FileNotFoundException {
		
		boolean x = false;
		ArrayList <Klinikum> a = KlinikenFromFile("src/Kliniken.csv");
		for (int i = 0; i<a.size(); i++) {
			if (a.get(i).id == n) {
				System.out.println(a.get(i).toString());
				x=true;
				break;
			}
		}
		
		if (x==false) {
			System.out.println("Es gibt keine Klinik mit der ID " + n);
		}
		
	}


	public static void main(String[] args) throws FileNotFoundException {
	
	Scanner input = new Scanner (System.in);
	System.out.println("Hallo! Willkommen zum Programm für das Klinikmanagementsystem");
	
	while (true) {
		
		System.out.println("Was möchten Sie tun?");
		String in = input.nextLine().toLowerCase();
		
		if (in.contains("zeige") || in.contains("zeigen")) {
			
			if (in.contains("zeige alle") || in.contains("zeigen alle")) {
			
				showAlleKliniken();
			}
			else {
				try {
				String [] a = in.split(" ");
				int i = Integer.parseInt(a[1]);
				sucheKlinikum (i);
				}
				
				catch (java.lang.NumberFormatException e) {
					System.out.println("Falsche Eingabe. Bitte benutzen Sie folgende Muster um eine bestimmte Klinik zu zeigen: Zeige 'ID'");
				}
			}
		}
		
		else if (in.contains("niedrigste durchschnittsgehalt")) {
		
			System.out.println(NiedrigsteDurchschnittsgehalt(KlinikenFromFile("src/Kliniken.csv")).toString());
		}
		else if (in.contains("meisten patienten")) {
		
			System.out.println(MeistenPatienten(KlinikenFromFile("src/Kliniken.csv")).toString());
		}
		else if (in.contains("end")) {
			
			System.out.println("Programm beendet");
			break;
		}
		else {System.out.println("Entschuldigung, falsche Eingabe. Bitte versuchen Sie es erneut");}
		
	}
	
	input.close();	

}
	
}
