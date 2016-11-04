package Interface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntTools;
import org.apache.jena.ontology.OntTools.PredicatesFilter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDFS;
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
import org.eclipse.swt.widgets.Composite;

public class EdyReifikacja {

	protected Shell shlCdnspl;
	Display display;
	static String nazwa;
	private Text klasa;
	Tree tree;
	private Text text;
	FileDialog fd;
	public static OntModel modelnowy3 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
	//public static String MyUrinowy = "http://www.MyUri.com/myOntology";
	
	public static void setnazwa(String nazwaaa) {
		nazwa = nazwaaa;
	};
	
	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		try {
			EdyReifikacja window = new EdyReifikacja();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private boolean hasSubClassTransitive( OntClass parent, OntClass child ) {
	    return OntTools.findShortestPath( child.getOntModel(), child, parent,new PredicatesFilter( RDFS.subClassOf ) ) != null;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
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
	 * @wbp.parser.entryPoint
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
	    
	    Button btnPomi = new Button(shlCdnspl, SWT.NONE);
	    btnPomi.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		shlCdnspl.dispose();
				EdyRole koniec = new EdyRole();
				koniec.open();
	    	}
	    });
	    btnPomi.setText("Cofnij");
	    btnPomi.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	    btnPomi.setBounds(519, 466, 75, 25);
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlCdnspl = new Shell();
		shlCdnspl.setImage(SWTResourceManager.getImage(Powitalne.class, "/Interface/icon3.png"));
		shlCdnspl.setBackgroundImage(SWTResourceManager.getImage(WyborKapsulki.class, "/Interface/tlo2.jpg"));
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
		tree = new Tree(composite, SWT.HORIZONTAL | SWT.BORDER | SWT.V_SCROLL
		        | SWT.H_SCROLL);
		tree.setLocation(10, 159);
		tree.setSize(432, 179);
		tree.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		
				refresh(tree);
				
  		tree.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		      final String nazwwa=event.item.toString();
		      String nazwaa = nazwwa.substring(10, (nazwwa.length()-1));
		      setnazwa(nazwaa);
		      klasa.setText(nazwa);
		      }
		    });
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(448, 113, 25, 23);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox dialog = new MessageBox(shlCdnspl);
				dialog.setMessage("W okienku zostanie wyœwietlona ontologia.\r\n Zaznaczaj¹c element spod BytBazowy, jego nazwa powinna siê ukazaæ w polu na dole.\r\n"
						+ " Po wyœwietleniu nazwy, mo¿na wcisn¹æ przycisk \"Podwieœ\"\r\n powinny wtedy do elementu C-"+EdyWyswietl.skrot+" oraz E-"+EdyWyswietl.skrot+" przypisaæ siê podane klasy wraz z ich podklasami");
						dialog.open();
			}
		});
		btnNewButton.setText("?");
		Label lblWybierzKapsukeKtr = new Label(composite, SWT.NONE);
		lblWybierzKapsukeKtr.setLocation(10, 116);
		lblWybierzKapsukeKtr.setSize(432, 37);
		lblWybierzKapsukeKtr.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWybierzKapsukeKtr.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.NORMAL));
		lblWybierzKapsukeKtr.setText("Tutaj zostan\u0105 podwieszone wybrane poj\u0119cia z BytBazowy pod elementy\nE-"+EdyWyswietl.skrot+" oraz C-"+EdyWyswietl.skrot+"");
				
		Button btnDodajKlase = new Button(composite, SWT.NONE);
		btnDodajKlase.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnDodajKlase.setBounds(384, 370, 89, 25);
		btnDodajKlase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if((nazwa != null && nazwa != ""))
				{
					OntClass klasKopiowana = null;
					OntClass klasWklajajaca = null;
				Iterator classIter = EdyWczytajPlik.modelnowy2.listClasses();
				while(classIter.hasNext())
					{
					OntClass ontClass = (OntClass) classIter.next();
					if(ontClass.getLocalName() != null && ontClass.getLocalName().equals(nazwa))	    			
					{					
						Iterator classIter2 = EdyWczytajPlik.modelnowy2.listClasses();
						while(classIter2.hasNext())
						{
							OntClass ontClass2 = (OntClass) classIter2.next();
							if(ontClass2.getLocalName() != null && ontClass2.getLocalName().startsWith("E-"+EdyWyswietl.skrot) )
							{
								ontClass2.addSubClass(ontClass);
								tree.removeAll();
								refresh(tree);	
								
							}
							else if(ontClass2.getLocalName() != null && ontClass2.getLocalName().startsWith("C-"+EdyWyswietl.skrot))
							{
								klasKopiowana = ontClass;
								klasWklajajaca = ontClass2;
							}
							
						}
				
					}
					}	
				reif(klasKopiowana, klasWklajajaca);
				tree.removeAll();
				refresh(tree);
				
				MessageBox dialog = new MessageBox(shlCdnspl);
				dialog.setMessage("Dodano! Widok ontologii zosta³ zaktualizowany.");
				dialog.open();			
				}	    		
			}
		});
		btnDodajKlase.setText("Podwie\u015B");
		
		klasa = new Text(composite, SWT.BORDER);
		klasa.setBounds(10, 372, 248, 25);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.NORMAL));
		lblNewLabel.setBounds(10, 351, 187, 15);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setText("Wybrane poj\u0119cie z byt\u00F3w");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(10, 41, 463, 25);
		lblNewLabel_1.setText("Je\u015Bli potrzebujesz, mo\u017Cesz wczyta\u0107 zewn\u0119trzn\u0105 ontologi\u0119 do zreifikowania");
		
		Label lblReifikowanieZewntrznejOntologii = new Label(composite, SWT.NONE);
		lblReifikowanieZewntrznejOntologii.setText("Reifikowanie zewn\u0119trznej ontologii");
		lblReifikowanieZewntrznejOntologii.setFont(SWTResourceManager.getFont("Exo 2 Light", 11, SWT.BOLD));
		lblReifikowanieZewntrznejOntologii.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblReifikowanieZewntrznejOntologii.setBounds(10, 10, 299, 25);
		
		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		text.setBounds(10, 74, 248, 21);
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				fd = new FileDialog(shlCdnspl, SWT.MULTI);
				String[]  filter = {"*.owl", "*.*"};
				fd.setFilterExtensions(filter);
			
				fd.open();
				text.setText(fd.getFilterPath() +"\\" + fd.getFileName());			
			}
			
			
		});
		button.setText("Przegl\u0105daj...");
		button.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		button.setBounds(310, 72, 79, 25);
		
		Button btnDodaj = new Button(composite, SWT.NONE);
		btnDodaj.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					try{					
						FileInputStream stream = new FileInputStream(fd.getFilterPath() +"\\" + fd.getFileName());
						modelnowy3.read(stream,null);
						EdyWczytajPlik.modelnowy2.add(modelnowy3);
						StringWriter writer = new StringWriter();
						EdyWczytajPlik.modelnowy2.write(writer, "RDF/XML-ABBREV") ;
						Iterator classIter = EdyWczytajPlik.modelnowy2.listClasses();
						Iterator classIter2 = EdyWczytajPlik.modelnowy2.listClasses();
				  		
						while (classIter.hasNext()) 
						{
							OntClass ontClass = (OntClass) classIter.next();
							
							String uri = ontClass.getLocalName();
							
							if (uri != null && uri.startsWith("BytBazowy"))
									{
										while (classIter2.hasNext()) 
										{
											OntClass ontClass2 = (OntClass) classIter2.next();
											
											String uri2 = ontClass2.getLocalName();
											
											if (uri2 != null && ontClass2.isHierarchyRoot() && !(uri2.startsWith("Byt")) && 
													!(uri2.startsWith("cDnSPL")) && !(uri2.startsWith("Element-cDnSPL")))
													{
														ontClass.addSubClass(ontClass2);
													}
										}
										//ontClass.addSubClass(Cel);
										//ontClass.addSubClass(Plan);
										//WczytajWarstwe.bb.BytBazowy.addSubClass(ontClass);
										}
						}
						
					
				} catch (Exception e1) { e1.printStackTrace();}		
				
				
				MessageBox dialog = new MessageBox(shlCdnspl);
				dialog.setMessage("Dodano ontologiê zewnêtrzn¹!\nZosta³a podwieszona pod \"BytBazowy\"");
				dialog.open();
				text.setText("");
				
			}
		});
		btnDodaj.setText("Dodaj");
		btnDodaj.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnDodaj.setBounds(394, 72, 79, 25);
		
		Button btnDalej = new Button(shlCdnspl, SWT.NONE);
		btnDalej.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnDalej.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			shlCdnspl.dispose();
			EdyKoniec koniec = new EdyKoniec();
			koniec.open();
				}
		});
		btnDalej.setBounds(600, 466, 75, 25);
		btnDalej.setText("Dalej");
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
			       EdyWczytajPlik.modelnowy2.write(writer, "RDF/XML-ABBREV");
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

	}
	public void refresh(Tree tree){
		EdyWczytajPlik.modelnowy2.reset();
	Iterator classIter = EdyWczytajPlik.modelnowy2.listClasses();
	
		while (classIter.hasNext()) 
		{
			OntClass ontClass = (OntClass) classIter.next();
			
			String uri = ontClass.getLocalName();
			
			
			if ((uri != null && ontClass.isHierarchyRoot()) || (uri != null && uri.startsWith("cDnSPL-Sytuacji"+EdyWyswietl.nazwa1)) || (uri != null && uri.startsWith("Element-cDnSPL-Sytuacji"+EdyWyswietl.nazwa1)))
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
					  																						ExtendedIterator<OntClass> n11 =ontClass12.listSubClasses();

			  																							}}}}}}}}}}}}}}}}}
				
			
		}
		
		}}}}}}
	} 
	public void reif(OntClass klasKopiowana, OntClass klasDoKtorejKopiuje){
		try{
	OntClass newClass = EdyWczytajPlik.modelnowy2.createClass(EdyWczytajPlik.MyUrinowy+"#"+klasKopiowana.getLocalName()+"-"+EdyWyswietl.skrot);
	 ObjectProperty klasyfikuje = EdyWczytajPlik.modelnowy2.createObjectProperty(EdyWczytajPlik.MyUrinowy+"#klasyfikuje");
	 ObjectProperty jestKlasyfikowany = EdyWczytajPlik.modelnowy2.createObjectProperty(EdyWczytajPlik.MyUrinowy+"#jestKlasyfikowanyPrzez");
	newClass.addSuperClass(klasDoKtorejKopiuje);
	klasKopiowana.addSuperClass(EdyWczytajPlik.modelnowy2.createSomeValuesFromRestriction(null,  jestKlasyfikowany, newClass));
	 newClass.addSuperClass(EdyWczytajPlik.modelnowy2.createSomeValuesFromRestriction(null, klasyfikuje, klasKopiowana));
	 Iterator classIter = klasKopiowana.listSubClasses();
	 while(classIter.hasNext()){
		 OntClass Temp = (OntClass) classIter.next();
		 {				
		 if((Temp.getLocalName() != null))
			 reif(Temp, klasDoKtorejKopiuje.getSubClass());
		 }
	 }} catch(Exception e){};
	};
}