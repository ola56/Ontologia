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



public class EtykietyCel {
	protected Shell shlCdnspl2;
	Display display;
	private Text text;
	private Text txtGoaloftheplan;
	static OntClass cdnspl, Elcdnspl;
	static String tlumaczenie;
	public static String nazwa;
	static String skrot ="s";
	private Text text_2;
	private Text text_3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			EtykietyCel window = new EtykietyCel();
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
	Composite composite = new Composite(shlCdnspl2, SWT.BORDER);
	composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	composite.setBounds(188, 46, 487, 408);
	 
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
	
	Label lblWpiszNazwSytuacji = new Label(composite, SWT.NONE);
	lblWpiszNazwSytuacji.setBounds(10, 10, 463, 53);
	lblWpiszNazwSytuacji.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	lblWpiszNazwSytuacji.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
	lblWpiszNazwSytuacji.setText("Podaj etykiety dla utworzonego celu i planu. Je\u015Bli pobrane automatycznie\r\nnazwy Ci nie odpowiadaj\u0105, mo\u017Cesz edytowa\u0107 i poda\u0107 w\u0142asne. Etykieta \r\nw j\u0119zyku angielskim jest autouzupe\u0142niona tylko w po\u0142owie");
	
	Label lblNazwaPolska = new Label(composite, SWT.NONE);
	lblNazwaPolska.setBounds(10, 108, 190, 15);
	lblNazwaPolska.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	lblNazwaPolska.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.NORMAL));
	lblNazwaPolska.setText("Etykieta w j\u0119zyku polskim");
	
	text = new Text(composite, SWT.BORDER);
	text.setBounds(10, 129, 239, 21);
	text.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	text.setText(Wyswietl.nazwaa);
	
	Label lblNazwaAngielskaEnglishname = new Label(composite, SWT.NONE);
	lblNazwaAngielskaEnglishname.setBounds(10, 156, 201, 15);
	lblNazwaAngielskaEnglishname.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	lblNazwaAngielskaEnglishname.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.NORMAL));
	lblNazwaAngielskaEnglishname.setText("Etykieta w j\u0119zyku angielskim");
	
	txtGoaloftheplan = new Text(composite, SWT.BORDER);
	txtGoaloftheplan.setBounds(10, 177, 239, 21);
	txtGoaloftheplan.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	txtGoaloftheplan.setText("GoalOfThePlanOf");
	
	Label lblDla = new Label(composite, SWT.NONE);
	lblDla.setText("Dla: " + Wyswietl.nazwaa);
	lblDla.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
	lblDla.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	lblDla.setBounds(10, 87, 190, 15);
	
	Label label = new Label(composite, SWT.NONE);
	label.setText("Dla: " + Wyswietl.nazwaa1);
	label.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
	label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	label.setBounds(10, 220, 190, 15);
	
	Label label_1 = new Label(composite, SWT.NONE);
	label_1.setText("Etykieta w j\u0119zyku polskim");
	label_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.NORMAL));
	label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	label_1.setBounds(10, 244, 190, 15);
	
	text_2 = new Text(composite, SWT.BORDER);
	text_2.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	text_2.setBounds(10, 265, 239, 21);
	text_2.setText(Wyswietl.nazwaa1);
	
	Label label_2 = new Label(composite, SWT.NONE);
	label_2.setText("Etykieta w j\u0119zyku angielskim");
	label_2.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.NORMAL));
	label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	label_2.setBounds(10, 292, 201, 15);
	
	text_3 = new Text(composite, SWT.BORDER);
	text_3.setText("PlanOf");
	text_3.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	text_3.setBounds(10, 313, 239, 21);
	
	Button btnDalej = new Button(shlCdnspl2, SWT.NONE);
	btnDalej.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String etykietaplcel="";
			String etykietaencel="";
			String etykietaplplan="";
			String etykietaenplan="";
	  		
			etykietaplcel = text.getText();
			etykietaencel = txtGoaloftheplan.getText();
			etykietaplplan = text_2.getText();
			etykietaenplan = text_3.getText();
	  		
	  		//cdnspl = WczytajWarstwe.model.createClass(WczytajWarstwe.MyUri + "#cDnSPL-Sytuacji" + nazwa);
	  		//Elcdnspl = WczytajWarstwe.model.createClass(WczytajWarstwe.MyUri + "#Element-cDnSPL-Sytuacji" + nazwa);

	  		Wyswietl.Cel.addLabel(etykietaplcel, "pl");
	  		Wyswietl.Cel.addLabel(etykietaencel, "en");
	  		Wyswietl.Plan.addLabel(etykietaplplan, "pl");
	  		Wyswietl.Plan.addLabel(etykietaenplan, "en");
			
	  		MessageBox dialog = new MessageBox(shlCdnspl2);
			dialog.setMessage("Dodano!");
			dialog.open();
	  		
	  		shlCdnspl2.dispose();
	  		Wyswietl wyswietl = new Wyswietl();
	  		wyswietl.open();
	  		
	  	}
	  	 
			
		
	});
	btnDalej.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	btnDalej.setBounds(599, 466, 75, 25);
	btnDalej.setText("Dodaj");
    
    
  
}
}


