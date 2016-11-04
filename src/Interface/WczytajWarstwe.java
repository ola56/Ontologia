package Interface;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;

public class WczytajWarstwe {

	protected Shell shlCdnspl;
	public static OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
	public static String MyUri = NoweUri.myUri;
	Display display;
	public static BudowaBytow bb;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WczytajWarstwe window = new WczytajWarstwe();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		Monitor primary = display.getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = shlCdnspl.getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
	    shlCdnspl.setLocation(x, y);
		shlCdnspl.open();
		shlCdnspl.layout();
		while (!shlCdnspl.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCdnspl = new Shell();
		shlCdnspl.setImage(SWTResourceManager.getImage(Powitalne.class, "/Interface/icon3.png"));
		shlCdnspl.setBackgroundImage(SWTResourceManager.getImage(Powitalne.class, "/Interface/tlo2.jpg"));
		shlCdnspl.setSize(719,670);
		shlCdnspl.setText("Tworzenie kapsu\u0142ek c.DnSPL");
		shlCdnspl.setLayout(null);
		Composite composite = new Composite(shlCdnspl, SWT.BORDER);
	    composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    composite.setBounds(188, 46, 487, 408);
	    
	    final Menu m = new Menu(shlCdnspl, SWT.BAR);
		   shlCdnspl.setMenuBar(m);
		    
		    final MenuItem file = new MenuItem(m, SWT.CASCADE);
		    file.setText("&O programie");
		    final Menu filemenu = new Menu(shlCdnspl, SWT.DROP_DOWN);
		    file.setMenu(filemenu);
		    final MenuItem pomoc = new MenuItem(filemenu, SWT.CASCADE);
		    pomoc.setText("&Pomoc");;
		    final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
		    final MenuItem autorki = new MenuItem(filemenu, SWT.PUSH);
		    autorki.setText("&Autorki");
		    
		    pomoc.addSelectionListener(new SelectionAdapter() {
		        public void widgetSelected(SelectionEvent e) {
		        	MessageBox dialog = new MessageBox(shlCdnspl);
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
		        	MessageBox dialog = new MessageBox(shlCdnspl);
					dialog.setMessage("Graficzny interfejs do tworzenia dobrze ufundowanych ontologii w paradygmacie c.DnSPL\r\n\n"
							+ "Program napisany w ramach pracy in¿ynierskiej\r\n\n"
							+ "Autorki: Aleksandra Piotrowicz, Izabela Procek\r\n\n"
							+ "Promotor: dr in¿. Jolanta Cybulka");
					dialog.open();
		        }
		      });

		    shlCdnspl.setMenuBar(m);
		    
		Label lblWarstwaFundacjonistyczna = new Label(composite, SWT.NONE);
		lblWarstwaFundacjonistyczna.setBounds(10, 10, 270, 25);
		lblWarstwaFundacjonistyczna.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWarstwaFundacjonistyczna.setFont(SWTResourceManager.getFont("Exo 2 Light", 13, SWT.BOLD));
		lblWarstwaFundacjonistyczna.setText("Warstwa fundacjonistyczna");
		
		Button btnRadioButton = new Button(composite, SWT.RADIO);
		btnRadioButton.setBounds(10, 59, 290, 16);
		btnRadioButton.setSelection(true);
		btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
			}
		});
		btnRadioButton.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
		btnRadioButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnRadioButton.setToolTipText("");
		btnRadioButton.setText("wczytana z repozytorium systemu");
		
		final Button btnRadioButton_1 = new Button(composite, SWT.RADIO);
		btnRadioButton_1.setBounds(10, 121, 276, 16);
		btnRadioButton_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
		btnRadioButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnRadioButton_1.setText("wczytana z w\u0142asnego pliku ");
		
		Label lblWarstwaTaZawiera = new Label(composite, SWT.NONE);
		lblWarstwaTaZawiera.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		lblWarstwaTaZawiera.setText("Warstwa ta zawiera podstawowe poj\u0119cia znajduj\u0105ce si\u0119 w Bytach.");
		lblWarstwaTaZawiera.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblWarstwaTaZawiera.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWarstwaTaZawiera.setBounds(30, 85, 453, 30);
		
		Label lblNastpiPrzekierowaniDo = new Label(composite, SWT.NONE);
		lblNastpiPrzekierowaniDo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		lblNastpiPrzekierowaniDo.setText("Nast\u0105pi tutaj przekierowanie do okna, w kt\u00F3rym mo\u017Cna wczyta\u0107 warstw\u0119 \r\nz pliku. Warstwa fundacjonistyczna b\u0119dzi\u0119 si\u0119 sk\u0142ada\u0107 z warstwy z repozytorium \r\nsystemu wzbogaconej o poj\u0119cia z wczytywanego pliku, kt\u00F3re zostan\u0105 dodane \r\ndo Byt\u00F3w Bazowych.");
		lblNastpiPrzekierowaniDo.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblNastpiPrzekierowaniDo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNastpiPrzekierowaniDo.setBounds(30, 144, 453, 81);;
		
		model.createOntology(MyUri);
		bb = new BudowaBytow(model, MyUri);
		
		Button btnDalej = new Button(shlCdnspl, SWT.NONE);
		btnDalej.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnDalej.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(btnRadioButton_1.getSelection()==true){
					shlCdnspl.dispose();
					WczytajPlik plik = new WczytajPlik();
				
					plik.open();}
				else
				{
					shlCdnspl.dispose();
					NazwaKapsulki nazwa = new NazwaKapsulki();
					nazwa.open();
				}
								
			}
		});

		btnDalej.setBounds(600, 466, 75, 25);
		btnDalej.setText("Dalej");

	}
}
