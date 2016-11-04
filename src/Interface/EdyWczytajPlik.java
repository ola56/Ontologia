package Interface;

import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.Iterator;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;


public class EdyWczytajPlik {

	protected Shell shlCdnspl;
	static Text text;
	public static OntModel modelnowy2 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
	public static String MyUrinowy; //= "http://www.MyUri.com/myOntology"
	Display display;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EdyWczytajPlik window = new EdyWczytajPlik();
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
		    //final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
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
		    
		Label lblWczytajPlik = new Label(composite, SWT.NONE);
		lblWczytajPlik.setBounds(10, 10, 473, 41);
		lblWczytajPlik.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWczytajPlik.setFont(SWTResourceManager.getFont("Exo 2 Light", 11, SWT.BOLD));
		lblWczytajPlik.setText("Wczytaj plik w celu wyboru kapsu³ki, któr¹ chcesz dokoñczyæ\ntworzyæ");
				
		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		text.setBounds(10, 57, 198, 21);
		
		Button btnPrzegldaj = new Button(composite, SWT.NONE);
		btnPrzegldaj.setBounds(214, 55, 79, 25);
		btnPrzegldaj.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				FileDialog fd = new FileDialog(shlCdnspl, SWT.MULTI);
				String[]  filter = {"*.owl", "*.*"};
				fd.setFilterExtensions(filter);
			
				fd.open();
				text.setText(fd.getFilterPath() +"\\" + fd.getFileName());
								
				try{					
					FileInputStream stream = new FileInputStream(fd.getFilterPath() +"\\" + fd.getFileName());
					modelnowy2.read(stream,null);
					StringWriter writer = new StringWriter();
				modelnowy2.write(writer, "RDF/XML-ABBREV") ;
				} catch (Exception e1) { e1.printStackTrace();}
			}
		});
		btnPrzegldaj.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnPrzegldaj.setText("Przegl\u0105daj...");
		
		Button btnDalej = new Button(shlCdnspl, SWT.NONE);
		btnDalej.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnDalej.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String kod="", uri;
				int i=0;
				Iterator classIter = modelnowy2.listClasses();
				OntClass ontclass = (OntClass) classIter.next();
				uri = ontclass.getURI();
				
				while(uri.charAt(i) != '#' && uri.length() >= i)
				{
					kod = kod+uri.charAt(i);
					i++;
				}
				MyUrinowy = kod;
				System.out.println(MyUrinowy);
				
				shlCdnspl.dispose();
				EdyWyborKapsulki kapsulka = new EdyWyborKapsulki();
				kapsulka.open();
			/*}
			else{
				WczytajWarstwe.model.add(modelnowy);
				Iterator classIter = WczytajWarstwe.model.listClasses();
				while (classIter.hasNext()) 
					{
						OntClass ontClass = (OntClass) classIter.next();
						
						String uri = ontClass.getLocalName(); 						
						if (uri != null && ontClass.isHierarchyRoot() && !(uri.startsWith("Byt")))
						{
							WczytajWarstwe.bb.BytBazowy.addSubClass(ontClass);
						}
        
					}
				shlCdnspl.dispose();
			    NazwaKapsulki nazwaKap = new NazwaKapsulki();
				nazwaKap.open();
			}*/
			}
		});
		btnDalej.setBounds(600, 466, 75, 25);
		btnDalej.setText("Dalej");

	}

}
