package Interface;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;

import org.apache.jena.ontology.OntClass;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;


public class WKaDodanieRolThing {

	protected Shell shlTworzenieKapsuekCdnspl;
	static WKaDodanieRolThing window = new WKaDodanieRolThing();
	Display display;
	private Text text;
	static String rola;
	static String nazwa, rolanazwa;
	static OntClass ontKlass;
	

	public void setRola(String text)
	{
		rola = text;
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	
	public static void main(String[] args) {
		try {
			
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
	    Rectangle rect = shlTworzenieKapsuekCdnspl.getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
	    shlTworzenieKapsuekCdnspl.setLocation(x, y);
	    
		shlTworzenieKapsuekCdnspl.open();
		shlTworzenieKapsuekCdnspl.layout();
		
		while (!shlTworzenieKapsuekCdnspl.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
	

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlTworzenieKapsuekCdnspl = new Shell();
		shlTworzenieKapsuekCdnspl.setImage(SWTResourceManager.getImage(Powitalne.class, "/Interface/icon3.png"));
		shlTworzenieKapsuekCdnspl.setBackgroundImage(SWTResourceManager.getImage(Powitalne.class, "/Interface/tlo2.jpg"));
		shlTworzenieKapsuekCdnspl.setSize(719,670);
		shlTworzenieKapsuekCdnspl.setText("Tworzenie kapsu\u0142ek c.DnSPL");
		Composite composite = new Composite(shlTworzenieKapsuekCdnspl, SWT.BORDER);
	    composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    composite.setBounds(188, 24, 487, 502);
	    
	    final Menu m = new Menu(shlTworzenieKapsuekCdnspl, SWT.BAR);
		   shlTworzenieKapsuekCdnspl.setMenuBar(m);
		    
		    final MenuItem file = new MenuItem(m, SWT.CASCADE);
		    file.setText("&O programie");
		    final Menu filemenu = new Menu(shlTworzenieKapsuekCdnspl, SWT.DROP_DOWN);
		    file.setMenu(filemenu);
		    final MenuItem pomoc = new MenuItem(filemenu, SWT.CASCADE);
		    pomoc.setText("&Pomoc");;
		    final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
		    final MenuItem autorki = new MenuItem(filemenu, SWT.PUSH);
		    autorki.setText("&Autorki");
		    
		    pomoc.addSelectionListener(new SelectionAdapter() {
		        public void widgetSelected(SelectionEvent e) {
		        	MessageBox dialog = new MessageBox(shlTworzenieKapsuekCdnspl);
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
		        	MessageBox dialog = new MessageBox(shlTworzenieKapsuekCdnspl);
					dialog.setMessage("Graficzny interfejs do tworzenia dobrze ufundowanych ontologii w paradygmacie c.DnSPL\r\n\n"
							+ "Program napisany w ramach pracy in¿ynierskiej\r\n\n"
							+ "Autorki: Aleksandra Piotrowicz, Izabela Procek\r\n\n"
							+ "Promotor: dr in¿. Jolanta Cybulka");
					dialog.open();
		        }
		      });

		    shlTworzenieKapsuekCdnspl.setMenuBar(m);
	    
	    		
	   
		Label lblPrzykadoweRole = new Label(composite, SWT.NONE);
		lblPrzykadoweRole.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPrzykadoweRole.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
		lblPrzykadoweRole.setBounds(53, 77, 203, 21);
		lblPrzykadoweRole.setText("Zaznacz wybran\u0105 rol\u0119");
		
		Tree tree_1 = new Tree(composite, SWT.BORDER | SWT.V_SCROLL);
		tree_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		tree_1.setBounds(53, 104, 273, 276);
		
		TreeItem item = new TreeItem(tree_1, 0); 
		item.setText("RolaAblatywna");
		TreeItem item1 = new TreeItem(tree_1, 0); 
		item1.setText("RolaAdlatywna");
		TreeItem item2 = new TreeItem(tree_1, 0); 
		item2.setText("RolaAgentywna");
		TreeItem item3 = new TreeItem(tree_1, 0); 
		item3.setText("RolaInstrumentalna");
		TreeItem item4 = new TreeItem(tree_1, 0); 
		item4.setText("RolaLokacyjna");
		TreeItem item5 = new TreeItem(tree_1, 0); 
		item5.setText("RolaPacjensywna");
		TreeItem item6 = new TreeItem(tree_1, 0); 
		item6.setText("RolaPacjensa-Beneficjenta");
		TreeItem item7 = new TreeItem(tree_1, 0); 
		item7.setText("RolaPacjensa-Obiektu");
		TreeItem item8 = new TreeItem(tree_1, 0); 
		item8.setText("RolaPerlatywna");
		TreeItem item9 = new TreeItem(tree_1, 0); 
		item9.setText("RolaReifikujaca");
		TreeItem item10 = new TreeItem(tree_1, 0); 
		item10.setText("RolaSposobuWykonania");
		TreeItem item11 = new TreeItem(tree_1, 0); 
		item11.setText("RolaWiedzy");
		TreeItem item12 = new TreeItem(tree_1, 0); 
		item12.setText("RolaWynikowa");
		TreeItem item13 = new TreeItem(tree_1, 0); 
		item13.setText("SkutekKonczonyWykonaniemZadania");
		TreeItem item14 = new TreeItem(tree_1, 0); 
		item14.setText("SkutekRozpoczynanyWykonaniemZadania");
		
		text = new Text(composite, SWT.BORDER );
		text.setBounds(10, 456, 354, 21);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnNewButton.setBounds(370, 453, 102, 27);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if((text.getText() != null || text.getText() != ""))
				{
					rolanazwa = text.getText();
					ontKlass = WczytajPlik.modelnowy.createClass(WczytajPlik.MyUrinowy + "#" + text.getText());
					Iterator classIter = WczytajPlik.modelnowy.listClasses();
		   		while(classIter.hasNext())
		   			{
		   			OntClass ontClass = (OntClass) classIter.next();
		   			if(ontClass.getLocalName() != null && ontClass.getLocalName().startsWith("C-"+WKaNazwaKapsulkiThing.skrot))
		   			{
		   				ontClass.addSubClass(ontKlass);
		   				
		   			}
		   			}
		   		MessageBox dialog = new MessageBox(shlTworzenieKapsuekCdnspl);
				dialog.setMessage("Dodano!");
				dialog.open();
				text.setText("");
				MessageBox msgBox1 = new MessageBox(shlTworzenieKapsuekCdnspl, SWT.ICON_QUESTION
			            | SWT.YES | SWT.NO);
			    msgBox1.setMessage("Czy chcesz dodaæ etykiety do utworzonej roli?");
			    msgBox1.setText("Dodanie etykiet");
			    int response1 = msgBox1.open();
			    if (response1 == SWT.YES)
			    {
			       shlTworzenieKapsuekCdnspl.dispose();
			       WKaEtykietyRoleThing role = new WKaEtykietyRoleThing();
			       role.open();				        	
			    }
				 
				}
			
			}
		});
		btnNewButton.setText("Dodaj");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblNewLabel.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 403, 473, 44);
		lblNewLabel.setText("Pole tekstowe nie mo\u017Ce by\u0107 puste w trakcie dodawania roli. Zaznacz wybran\u0105 role,\r\npoka\u017Ce si\u0119 ona w polu tekstowym. Nast\u0119pnie mozna nacisna\u0107 przycisk \"Dodaj\" w \r\ncelu dodania roli do ontologii.");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.BOLD));
		lblNewLabel_1.setBounds(10, 10, 425, 61);
		lblNewLabel_1.setText("Poni\u017Cej zostan\u0105 dodane role. Mo\u017Cesz skorzysta\u0107 z podanych wzorc\u00F3w\r\nnp. zaznaczajac wybrany z nich. Pojawi si\u0119 on w polu tekstowym\r\nna dole, kt\u00F3ry mo\u017Cna uzupe\u0142ni\u0107, dopisuj\u0105c nazw\u0119.");
		
		Button btnZakocz = new Button(shlTworzenieKapsuekCdnspl, SWT.NONE);
		btnZakocz.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlTworzenieKapsuekCdnspl.dispose();
				WKaRoleThing role = new WKaRoleThing();
				role.open();
			}
		});
		btnZakocz.setText("Zako\u0144cz dodawanie r\u00F3l");
		btnZakocz.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnZakocz.setBounds(526, 532, 149, 33);
		 
		tree_1.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {		    
		      final String nazwwa=event.item.toString();
		      String nazwaa = nazwwa.substring(10, (nazwwa.length()-1));
		      setRola(nazwaa);
		      text.setText(rola+WKaRoleThing.bytWyd);
		      }
		    });
		  shlTworzenieKapsuekCdnspl.addListener(SWT.Close, new Listener()
	      {
	          public void handleEvent(Event event)
	          {
	              int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
	              MessageBox messageBox = new MessageBox(shlTworzenieKapsuekCdnspl, style);
	              messageBox.setText("Opuszczasz program");
	              messageBox.setMessage("Czy chcesz zapisaæ ontologie?");
	              int check = messageBox.open();
	             if(check == SWT.YES)
	             {
	            	 StringWriter writer = new StringWriter();
	            	 WczytajPlik.modelnowy.write(writer, "RDF/XML-ABBREV");
				       String owlcode = writer.toString(); 
				          
				      FileDialog dialog = new FileDialog(shlTworzenieKapsuekCdnspl, SWT.SAVE);
				      dialog.setFilterExtensions(new String[] { "*.owl", "*.*" });
				      dialog.setFileName("plik.owl");
				                       
				      String filepath =  dialog.open(); 
				     
				    try{
				       FileWriter Fwriter = new FileWriter(filepath);
				       Fwriter.write(owlcode);
				       Fwriter.close();
				      } catch(FileNotFoundException fnfe) {fnfe.printStackTrace();}
				      catch(IOException ioe) {ioe.printStackTrace();}
				  }
	          }
	      });
	}
	
	public static void setnazwa(String nazwaaa) {
		  nazwa = nazwaaa;
		 };
}
