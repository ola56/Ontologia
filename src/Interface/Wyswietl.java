package Interface;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntTools;
import org.apache.jena.ontology.OntTools.PredicatesFilter;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDFS;
import org.eclipse.swt.widgets.Composite;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Tree;

public class Wyswietl {

	protected Shell shlCdnspl;
	Display display;
	private Text text;
	private Text text_1;
	static OntClass Cel, Plan;
	Tree tree;
	String skrot = NazwaKapsulki.skrot;
	static String nazwa;
	Property maJakoGlownyCel, jestGlownymCelem;
	static String cel="";
	static String plan="";
	static String nazwaa = "";
	static String nazwaa1 = "";

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Wyswietl window = new Wyswietl();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private boolean hasSubClassTransitive( OntClass parent, OntClass child ) {
	    return OntTools.findShortestPath( child.getOntModel(), child, parent,new PredicatesFilter( RDFS.subClassOf ) ) != null;
	}

	public List<OntClass> namedRootsOf( OntClass c ) {
	    List<OntClass> cRoots = new ArrayList<OntClass>();
	    for (OntClass root: OntTools.namedHierarchyRoots( c.getOntModel() )) {
	        if (hasSubClassTransitive( root, c )) {
	            cRoots.add( root );
	        }
	    }
	    return cRoots;
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
		    
		Composite composite = new Composite(shlCdnspl, SWT.BORDER);
	    composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    composite.setBounds(188, 46, 487, 408);
		Label lblWybierzKapsukeKtr = new Label(composite, SWT.NONE);
		lblWybierzKapsukeKtr.setBounds(10, 10, 334, 25);
		lblWybierzKapsukeKtr.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWybierzKapsukeKtr.setFont(SWTResourceManager.getFont("Exo 2 Light", 12, SWT.BOLD));
		lblWybierzKapsukeKtr.setText("Ontologia");
		tree = new Tree(composite, SWT.FLAT | SWT.BORDER | SWT.V_SCROLL
		        | SWT.H_SCROLL);
		tree.setBounds(10, 35, 463, 190);
		tree.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		
		Label lblTerazWykonajSpecjalizacj = new Label(composite, SWT.NONE);
		lblTerazWykonajSpecjalizacj.setBounds(10, 231, 350, 25);
		lblTerazWykonajSpecjalizacj.setText("Teraz wykonaj specjalizacj\u0119 elementu D-"+ skrot );
		lblTerazWykonajSpecjalizacj.setFont(SWTResourceManager.getFont("Exo 2 Light", 12, SWT.BOLD));
		lblTerazWykonajSpecjalizacj.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblPodajCelPlanu = new Label(composite, SWT.NONE);
		lblPodajCelPlanu.setBounds(10, 262, 449, 15);
		lblPodajCelPlanu.setText("Dopisz nazwe dla celu planu dzia\u0142a\u0144 albo aktywno\u015Bci albo zdarze\u0144 albo stan\u00F3w");
		lblPodajCelPlanu.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblPodajCelPlanu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(10, 283, 190, 21);
		text.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		text.setText("CelPlanu");
		
		Label lblPodajPlanReprezentujcy = new Label(composite, SWT.NONE);
		lblPodajPlanReprezentujcy.setBounds(10, 310, 350, 15);
		lblPodajPlanReprezentujcy.setText("Dopisz nazw\u0119 dla planu reprezentujacego deskrypcj\u0119 sytuacji");
		lblPodajPlanReprezentujcy.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblPodajPlanReprezentujcy.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(10, 331, 190, 21);
		text_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		text_1.setText("Plan");
		
		Button btnDodaj = new Button(composite, SWT.NONE);
		btnDodaj.setBounds(384, 329, 75, 25);
		btnDodaj.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
		  		
		  		nazwaa=text.getText();
		  		int dlu1 = nazwaa.length();
		  		cel=nazwaa.substring(8, dlu1);
		  		nazwaa1=text_1.getText();
		  		int dlu2 = nazwaa1.length();
		  		plan=nazwaa1.substring(4, dlu2);
		  		Scanner scaner = new Scanner(System.in); 		
		  		
		  		
		  		Cel = WczytajWarstwe.model.createClass(WczytajWarstwe.MyUri + "#CelPlanu" + cel );
		  		Plan = WczytajWarstwe.model.createClass(WczytajWarstwe.MyUri + "#Plan" + plan );

		  		//Cel.addLabel("CelPlanu" + cel /*+ NazwaKapsulki.nazwa*/, "pl");
		  		//Cel.addLabel("GoalOf" + cel + NazwaKapsulki.tlumaczenie, "en");
		  		//Plan.addLabel("Plan" + plan /*+ NazwaKapsulki.nazwa*/, "pl");
		  		//Plan.addLabel("PlanOf" + plan + NazwaKapsulki.tlumaczenie, "en");
		  		
		  		Iterator classIter = WczytajWarstwe.model.listClasses();
		  		
				while (classIter.hasNext()) 
				{
					OntClass ontClass = (OntClass) classIter.next();
					
					String uri = ontClass.getLocalName();
					
					if (uri != null && uri.startsWith("D-"+skrot))
							{
								ontClass.addSubClass(Cel);
								ontClass.addSubClass(Plan);
							}
				}
				
				jestGlownymCelem = WczytajWarstwe.model.createObjectProperty(WczytajWarstwe.MyUri + "#jestGlownymCelem");
			    maJakoGlownyCel = WczytajWarstwe.model.createObjectProperty(WczytajWarstwe.MyUri + "#maJakoCelGlowny");
			      
			    Cel.addSuperClass(WczytajWarstwe.model.createSomeValuesFromRestriction(null, jestGlownymCelem, Plan));
			    Plan.addSuperClass(WczytajWarstwe.model.createSomeValuesFromRestriction(null, maJakoGlownyCel, Cel));
				MessageBox dialog = new MessageBox(shlCdnspl);
				dialog.setMessage("Dodano! Widok ontologii zosta³ zaktualizowany.");
				dialog.open();
				
				
				text.setText("CelPlanu");
				text_1.setText("Plan");
				tree.removeAll();
				refresh(tree);
				MessageBox msgBox = new MessageBox(shlCdnspl, SWT.ICON_QUESTION
			            | SWT.YES | SWT.NO);
			    msgBox.setMessage("Czy chcesz dodaæ etykiety do utworzonego celu i planu?");
			    msgBox.setText("Dodanie etykiet");
			    int response = msgBox.open();
			    if (response == SWT.YES)
			    {
			       shlCdnspl.dispose();
			       EtykietyCel Dod = new EtykietyCel();
			       Dod.open();				        	
			    }
		 
		 
			}
		});
		btnDodaj.setText("Dodaj");
		btnDodaj.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		
		Label lblPoKlikniciudodaj = new Label(composite, SWT.NONE);
		lblPoKlikniciudodaj.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblPoKlikniciudodaj.setText("Po klikni\u0119ciu \"Dodaj\" zostan\u0105 dodane do ontologii poj\u0119cia podane dla \r\nCelu i Planu oraz zostanie zaktualizowany widok ontologii.");
		lblPoKlikniciudodaj.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblPoKlikniciudodaj.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPoKlikniciudodaj.setBounds(10, 362, 463, 32);
		
		
		
		
		 tree.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		        String string = event.detail == SWT.CHECK ? "Checked"
		            : "Selected";
		      }
		    });
		
		  shlCdnspl.addListener(SWT.Close, new Listener()
	      {
	          public void handleEvent(Event event)
	          {
	              int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
	              MessageBox messageBox = new MessageBox(shlCdnspl, style);
	              messageBox.setText("Opuszczasz program");
	              messageBox.setMessage("Czy chcesz zapisaæ ontologie?");
	              int check = messageBox.open();
	             if(check == SWT.YES)
	             {
	            	 StringWriter writer = new StringWriter();
				       WczytajWarstwe.model.write(writer, "RDF/XML-ABBREV");
				       String owlcode = writer.toString(); 
				          
				      FileDialog dialog = new FileDialog(shlCdnspl, SWT.SAVE);
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
		  
		Button btnDalej = new Button(shlCdnspl, SWT.NONE);
		btnDalej.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnDalej.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlCdnspl.dispose();
				Dalej dalej = new Dalej();
				dalej.open();
			}
		});
		btnDalej.setBounds(600, 466, 75, 25);
		btnDalej.setText("Dalej");
	
		refresh(tree);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(448, 10, 25, 23);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox dialog = new MessageBox(shlCdnspl);
				dialog.setMessage("W tym oknie zostanie przeprowadzona specjalizacja elementu D-"+NazwaKapsulki.skrot+" czyli zostan¹ dodane do D-"+NazwaKapsulki.skrot+" pojêcia zwi¹zane z planem oraz celem sytuacji, której dotyczy kapsu³ka.\r\n"
						+ "Wystarczy podaæ nazwê Celu oraz Planu, nie trzeba poprzedzaæ ich s³owami \"Cel\" oraz \"Plan\", gdy¿ program dodaje je sam przed podan¹ nazw¹. \r\n"
						+ "Po klikniêciu \"Dodaj\" podany Cel oraz Plan zostan¹ dodane do elementu D-"+NazwaKapsulki.skrot+" i widok ontologii sie zaktualizuje o podan¹ zmiane. \r\n"
						+ "Nale¿y stosowaæ zasady jak w przypadki nazewnictwa sytuacji czyli nie stosowaæ spacji, a poszczególne s³owa rozdzieliæ pisz¹c je du¿¹ liter¹.\r\n"
						+ "Przyk³ady: PrzygotowanieObiadu - wygenerowanie CelPrzygotowanieObiadu\r\n"
						+ "ZastosowaniePrzepisu - wygenerowanie PlanZastosowaniePrzepisu.");
				dialog.open();
			}
		});
		btnNewButton.setText("?");
		
		tree.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		      final String nazwwa=event.item.toString();
		      String nazwaa = nazwwa.substring(10, (nazwwa.length()-1));
		      setnazwa(nazwaa);
		     System.out.println(nazwa);
		      }
		    });

	}
	public static void setnazwa(String nazwaaa) {
		nazwa = nazwaaa;
	};
	public void refresh(Tree tree){
		
	Iterator classIter = WczytajWarstwe.model.listClasses();
	
		while (classIter.hasNext()) 
		{
			OntClass ontClass = (OntClass) classIter.next();
			
			String uri = ontClass.getLocalName(); 
			
			
			if ((uri != null && uri.startsWith("cDnSPL-")) || (uri != null && uri.startsWith("Element-cDnSPL")) || (uri!=null && ontClass.isHierarchyRoot()) )
				{
					TreeItem treeItem0 = new TreeItem(tree, 0);
					treeItem0.setText(uri);
					
					ExtendedIterator<OntClass> n =ontClass.listSubClasses();
					
					while (n.hasNext()) 
			  		{
						OntClass ontClass2 = (OntClass) n.next();
						String uri2 = ontClass2.getLocalName();
						if (uri2 != null)
						{
							TreeItem treeItem1 = new TreeItem(treeItem0, 0);
							treeItem1.setText(uri2);
	  				  					
							ExtendedIterator<OntClass> n1 =ontClass2.listSubClasses();
							while (n1.hasNext()) 
							{
								OntClass ontClass3 = (OntClass) n1.next();
			  					String uri3 = ontClass3.getLocalName();
			  					if (uri3 != null)
			  					{
			  						TreeItem treeItem2 = new TreeItem(treeItem1, 0);
			  						treeItem2.setText(uri3);
	  					
			  						ExtendedIterator<OntClass> n2 =ontClass3.listSubClasses();
			  						while (n2.hasNext()) 
			  						{
			  							OntClass ontClass4 = (OntClass) n2.next();
			  							String uri4 = ontClass4.getLocalName();
			  							if (uri4 != null)
			  							{
			  								TreeItem treeItem3 = new TreeItem(treeItem2, 0);
			  								treeItem3.setText(uri4);
	  					
			  								ExtendedIterator<OntClass> n3 =ontClass4.listSubClasses();
			  								while (n3.hasNext()) 
			  								{
			  									OntClass ontClass5 = (OntClass) n3.next();
			  									String uri5 = ontClass5.getLocalName();
			  									if (uri5 != null)
			  									{
			  										TreeItem treeItem4 = new TreeItem(treeItem3, 0);
			  										treeItem4.setText(uri5);
	  					  	  					
			  										ExtendedIterator<OntClass> n4 =ontClass5.listSubClasses();
			  										while (n4.hasNext()) 
			  										{
			  											OntClass ontClass6 = (OntClass) n4.next();
			  											String uri6 = ontClass6.getLocalName();
			  											if (uri6 != null)
			  											{
			  												TreeItem treeItem5 = new TreeItem(treeItem4, 0);
			  												treeItem5.setText(uri6);	
			  											  	  					
			  												ExtendedIterator<OntClass> n5 =ontClass6.listSubClasses();
			  												while (n5.hasNext()) 
			  												{
			  													OntClass ontClass7 = (OntClass) n5.next();
			  													String uri7 = ontClass7.getLocalName();
			  													if (uri7 != null)
			  													{
			  														TreeItem treeItem6 = new TreeItem(treeItem5, 0);
			  														treeItem6.setText(uri7);
	  				
			  														ExtendedIterator<OntClass> n6 =ontClass7.listSubClasses();
			  														while (n6.hasNext()) 
			  														{
			  															OntClass ontClass8 = (OntClass) n6.next();
			  															String uri8 = ontClass8.getLocalName();
			  															if (uri8 != null)
			  															{
			  																TreeItem treeItem7 = new TreeItem(treeItem6, 0);
			  																treeItem7.setText(uri8);
	  					
			  																ExtendedIterator<OntClass> n7 =ontClass8.listSubClasses();
			  																while (n7.hasNext()) 
			  																{
			  																	OntClass ontClass9 = (OntClass) n7.next();
			  																	String uri9 = ontClass9.getLocalName();
			  																	if (uri9 != null)
			  																	{
			  																		TreeItem treeItem8 = new TreeItem(treeItem7, 0);
			  																		treeItem8.setText(uri9);
	  					
			  																		ExtendedIterator<OntClass> n8 =ontClass9.listSubClasses();
			  																		while (n8.hasNext()) 
			  																		{
			  																			OntClass ontClass10 = (OntClass) n8.next();
			  																			String uri10 = ontClass10.getLocalName();
			  																			if (uri10 != null)
			  																			{
			  																				TreeItem treeItem9 = new TreeItem(treeItem8, 0);
			  																				treeItem9.setText(uri10);
	  					
			  																				ExtendedIterator<OntClass> n9 =ontClass10.listSubClasses();
			  																				while (n9.hasNext()) 
			  																				{
			  																					OntClass ontClass11 = (OntClass) n9.next();
			  																					String uri11 = ontClass11.getLocalName();
			  																					if (uri11 != null)
			  																					{
			  																						TreeItem treeItem10 = new TreeItem(treeItem9, 0);
			  																						treeItem10.setText(uri11);
			  																						
			  																						ExtendedIterator<OntClass> n10 =ontClass11.listSubClasses();
			  																						while (n10.hasNext()) 
			  																						{
			  																							OntClass ontClass12 = (OntClass) n10.next();
			  																							String uri12 = ontClass12.getLocalName();
			  																							if (uri12 != null)
			  																							{
			  																								TreeItem treeItem11 = new TreeItem(treeItem10, 0);
			  																								treeItem11.setText(uri12);
			  																								
			  																							
			  																							
			  																						}}}}}}}}}}}}}}}}}
	}}}}}}}}	
}
