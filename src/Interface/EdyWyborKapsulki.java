package Interface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntTools;
import org.apache.jena.ontology.OntTools.PredicatesFilter;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDFS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
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

public class EdyWyborKapsulki {

	protected Shell shlCdnspl;
	Display display;
	static String nazwa;
	static Text text;
	
	
	
	public static void setnazwa(String nazwaaa) {
			nazwa = nazwaaa;
		};
		
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EdyWyborKapsulki window = new EdyWyborKapsulki();
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
	    
	    Button btnDodajNowKapsuke = new Button(shlCdnspl, SWT.NONE);
	    btnDodajNowKapsuke.setBounds(573, 466, 102, 25);
	    btnDodajNowKapsuke.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	    btnDodajNowKapsuke.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		shlCdnspl.dispose();
	    		EdyWyswietl nowa = new EdyWyswietl();
	    		nowa.open();	   		
	    	}	   		
	    	
	    });
	    btnDodajNowKapsuke.setText("Wybierz");
	   
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
		lblWybierzKapsukeKtr.setBounds(10, 10, 390, 25);
		lblWybierzKapsukeKtr.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWybierzKapsukeKtr.setFont(SWTResourceManager.getFont("Exo 2 Light", 11, SWT.BOLD));
		lblWybierzKapsukeKtr.setText("Wybierz, kt\u00F3r\u0105 kapsu\u0142k\u0119 chcesz doko\u0144czy\u0107 tworzy\u0107");
		final Tree tree = new Tree(composite, SWT.FLAT | SWT.BORDER | SWT.V_SCROLL
			        | SWT.H_SCROLL);
		tree.setBounds(10, 35, 445, 234);
		tree.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		   
		   
		   Label label = new Label(composite, SWT.NONE);
		   label.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		   label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		   label.setBounds(10, 285, 187, 15);
		   label.setText("Wybrana kapsu\u0142ka");
		   
		   text = new Text(composite, SWT.BORDER);
		   text.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		   text.setBounds(10, 306, 201, 25);
		   tree.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		    
		      final String nazwwa=event.item.toString();
		      String nazwaa = nazwwa.substring(10, (nazwwa.length()-1));
		      setnazwa(nazwaa);
		      text.setText(nazwa);
		      System.out.println(nazwa);
		      }
		    });
			refresh(tree);



	}
	public void refresh(Tree tree){
		EdyWczytajPlik.modelnowy2.reset();
		Iterator classIter = EdyWczytajPlik.modelnowy2.listClasses();
	
		while (classIter.hasNext()) 
		{
			OntClass ontClass = (OntClass) classIter.next();
			
			String uri = ontClass.getLocalName(); 
			
			
			if ((uri != null && uri.startsWith("cDnSPL")) /*|| (uri !=null && uri.startsWith("Element-cDnSPL"))*/ )
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
	}}}}}}}}}

	