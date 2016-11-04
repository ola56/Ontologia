package Interface;

import org.apache.jena.ontology.OntClass;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import java.util.Iterator;
import java.util.Scanner;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;



public class WKaNazwaKapsulki {
	protected Shell shlCdnspl2;
	Display display;
	private Text text;
	private Text text_1;
	static OntClass cdnspl, Elcdnspl;
	static String tlumaczenie;
	static String nazwa1;
	static String skrot = "s";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			WKaNazwaKapsulki window = new WKaNazwaKapsulki();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



public void open() {
	display = Display.getDefault();
	createContents();
	
	Monitor primary = display.getPrimaryMonitor();
    Rectangle bounds = primary.getBounds();
    Rectangle rect = shlCdnspl2.getBounds();
    
    int x = bounds.x + (bounds.width - rect.width) / 2;
    int y = bounds.y + (bounds.height - rect.height) / 2;
    
    shlCdnspl2.setLocation(x, y);
	shlCdnspl2.open();
	shlCdnspl2.layout();
	while (!shlCdnspl2.isDisposed()) {
		if (!display.readAndDispatch()) {
			display.sleep();
		}
	}
}

protected void createContents() {
	shlCdnspl2 = new Shell();
	shlCdnspl2.setImage(SWTResourceManager.getImage(Powitalne.class, "/Interface/icon3.png"));
	shlCdnspl2.setBackgroundImage(SWTResourceManager.getImage(Powitalne.class, "/Interface/tlo2.jpg"));
	shlCdnspl2.setSize(719,670);
	shlCdnspl2.setText("Tworzenie kapsu\u0142ek c.DnSPL");
	shlCdnspl2.setLayout(null);
	final Menu m = new Menu(shlCdnspl2, SWT.BAR);
	   shlCdnspl2.setMenuBar(m);
	    
	    final MenuItem file = new MenuItem(m, SWT.CASCADE);
	    file.setText("&O programie");
	    final Menu filemenu = new Menu(shlCdnspl2, SWT.DROP_DOWN);
	    file.setMenu(filemenu);
	    final MenuItem pomoc = new MenuItem(filemenu, SWT.CASCADE);
	    pomoc.setText("&Pomoc");;
	    final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
	    final MenuItem autorki = new MenuItem(filemenu, SWT.PUSH);
	    autorki.setText("&Autorki");
	    
	    pomoc.addSelectionListener(new SelectionAdapter() {
	        public void widgetSelected(SelectionEvent e) {
	        	MessageBox dialog = new MessageBox(shlCdnspl2);
				dialog.setMessage(Powitalne.Wstep);
				dialog.open();
				dialog.setMessage(Powitalne.Ontologia);
				dialog.open();
				dialog.setMessage(Powitalne.Kapsulka);
				dialog.open();
				dialog.setMessage(Powitalne.Warstwa);
				dialog.open();
	        }
	      });
	    
	    autorki.addSelectionListener(new SelectionAdapter() {
	        public void widgetSelected(SelectionEvent e) {
	        	MessageBox dialog = new MessageBox(shlCdnspl2);
				dialog.setMessage("Graficzny interfejs do tworzenia dobrze ufundowanych ontologii w paradygmacie c.DnSPL\r\n\n"
						+ "Program napisany w ramach pracy in¿ynierskiej\r\n\n"
						+ "Autorki: Aleksandra Piotrowicz, Izabela Procek\r\n\n"
						+ "Promotor: dr in¿. Jolanta Cybulka");
				dialog.open();
	        }
	      });

	    shlCdnspl2.setMenuBar(m);
	Composite composite = new Composite(shlCdnspl2, SWT.BORDER | SWT.NO_BACKGROUND);
    composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
    composite.setBounds(188, 46, 487, 408);
    Label lblWpiszNazwSytuacji = new Label(composite, SWT.NONE);
	lblWpiszNazwSytuacji.setBounds(10, 10, 463, 39);
	lblWpiszNazwSytuacji.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	lblWpiszNazwSytuacji.setFont(SWTResourceManager.getFont("Exo 2 Light", 13, SWT.BOLD));
	lblWpiszNazwSytuacji.setText("Dopisz nazw\u0119 sytuacji, kt\u00F3ra dotyczy tworzonej kapsu\u0142ki");
	
	Label lblNazwaPolska = new Label(composite, SWT.NONE);
	lblNazwaPolska.setBounds(10, 54, 225, 15);
	lblNazwaPolska.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	lblNazwaPolska.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
	lblNazwaPolska.setText("Nazwa polska w dope\u0142niaczu");
	
	text = new Text(composite, SWT.BORDER);
	text.setBounds(10, 75, 225, 21);
	text.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	text.setText("cDnSPL-Sytuacji");
	
	Label lblNazwaAngielskaEnglishname = new Label(composite, SWT.NONE);
	lblNazwaAngielskaEnglishname.setBounds(10, 102, 225, 21);
	lblNazwaAngielskaEnglishname.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	lblNazwaAngielskaEnglishname.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
	lblNazwaAngielskaEnglishname.setText("Nazwa angielska w dope\u0142niaczu");
	
	text_1 = new Text(composite, SWT.BORDER);
	text_1.setBounds(10, 128, 225, 21);
	text_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	text_1.setText("cDnSPL-SituationOf");
	
	Label lblnazwyNieMog = new Label(composite, SWT.NONE);
	lblnazwyNieMog.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
	lblnazwyNieMog.setBounds(10, 173, 434, 166);
	lblnazwyNieMog.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	lblnazwyNieMog.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	lblnazwyNieMog.setText("Nazwy nie mog\u0105 zawiera\u0107 spacji mi\u0119dzy s\u0142owami oraz polskich \r\nznak\u00F3w, gdy\u017C mo\u017Ce to doprowadzi\u0107 do powstania b\u0142\u0119d\u00F3w\r\nlub b\u0142\u0119dnego utworzenia si\u0119 kapsu\u0142ki. Nazwa musi by\u0107 \r\nw dope\u0142niaczu (odpowiada\u0107 na pytanie \"kogo?, czego?\" dotyczy).\r\n\r\nWa\u017Cne jest, aby wpisywa\u0107 ka\u017Cde s\u0142owo z wielkiej litery. Taka metoda \r\numo\u017Cliwi poprawne utworzenie skr\u00F3tu sytuacji stosowanego\r\nprzy generowaniu nazw dla element\u00F3w kapsu\u0142ki.\r\n\r\nPrzyk\u0142adowe poprawnie podane nazwy:\r\nWystawieniaFaktury, KarmieniaZwierzat");
	
	Button btnDalej = new Button(shlCdnspl2, SWT.NONE);
	btnDalej.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String[] litery = {"TD", "TS", "E", "C", "K", "I", "D", "A", "S"};
	  		
			String nazwaa=text.getText();
	  		int dlu1 = nazwaa.length();
	  		nazwa1=nazwaa.substring(15, dlu1);
	  		char znak;
	  		for(int i=0;i<nazwa1.length();i++)
	  		{
	  			znak = nazwa1.charAt(i);
	  			if( ((znak > 64) && (znak < 91)))
	  				skrot = skrot + (char)(znak+32);
	  		}
	  		String nazwaa1=text_1.getText();
	  		int dlu2 = nazwaa1.length();
	  		tlumaczenie=nazwaa1.substring(18, dlu2);
	  		Scanner scaner = new Scanner(System.in);
	  		
	  		cdnspl = WczytajPlik.modelnowy.createClass(WczytajPlik.MyUrinowy + "#cDnSPL-Sytuacji" + nazwa1);
	  		Elcdnspl = WczytajPlik.modelnowy.createClass(WczytajPlik.MyUrinowy + "#Element-cDnSPL-Sytuacji" + nazwa1);
	  		
	  		cdnspl.addLabel("cDnSPL-Sytuacji" + nazwa1, "pl");
	  		cdnspl.addLabel("cDnSPL-SituationOf" + tlumaczenie, "en");
	  		Elcdnspl.addLabel("Element-cDnSPL-Sytuacji" + nazwa1, "pl");
	  		Elcdnspl.addLabel("Element-cDnSPL-SituationOf" + tlumaczenie, "en");
			
	  		OntClass[] klas = new OntClass[9];
	  		
	  		for (int i = 0; i< litery.length; i++)
	  		{
	  			klas[i] = WczytajPlik.modelnowy.createClass(WczytajPlik.MyUrinowy + "#" + litery[i] + "-" + skrot);
	  			WKaNazwaKapsulki.Elcdnspl.addSubClass(klas[i]);
	  			klas[i].addLabel(litery[i]+"-Sytuacji" + nazwa1, "pl");
	  			klas[i].addLabel(litery[i]+"-SituationOf" + tlumaczenie, "en");
	  		}
	  		
	  		BudowaKapsulki BD = new BudowaKapsulki(WczytajPlik.modelnowy, klas, WczytajPlik.MyUrinowy, cdnspl, Elcdnspl);
	  		Iterator classIter = WczytajPlik.modelnowy.listClasses();
    		while(classIter.hasNext())
    		{
	    		OntClass ontClass = (OntClass) classIter.next();
	    		if(ontClass.getLocalName() != null && ontClass.getLocalName().startsWith(WyborKapsulki.nazwa))
	    			{
	    			ontClass.addSubClass(WKaNazwaKapsulki.cdnspl);
	    			ontClass.addSubClass(WKaNazwaKapsulki.Elcdnspl);
	    			}
	    		
    		}
	  		  		
			shlCdnspl2.dispose();
			WKaWyswietl wyswietl = new WKaWyswietl();
		  	wyswietl.open();
		}
	});
	
	btnDalej.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	btnDalej.setBounds(600, 466, 75, 25);
	btnDalej.setText("Dalej");
    
    
  
}
  		  



    
    
}


