package Interface;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;


import org.apache.jena.ontology.OntClass;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;


public class KoniecProgramu {

	protected Shell shlTworzenieKapsuekCdnspl;
	static KoniecProgramu  window = new KoniecProgramu ();
	Display display;
	
	

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
		
		Button btnDalej = new Button(shlTworzenieKapsuekCdnspl, SWT.TOGGLE);
		btnDalej.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnDalej.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		btnDalej.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		
		btnDalej.setBounds(565, 466, 110, 25);
		btnDalej.setText("Zako\u0144cz program");
		
		
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
		    
		    Composite composite = new Composite(shlTworzenieKapsuekCdnspl, SWT.BORDER );
		    composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    composite.setBounds(188, 46, 487, 408);
		    
		    
		    Label lblWitajWProgramie = new Label(composite, SWT.NONE);
		    lblWitajWProgramie.setBounds(10, 10, 473, 63);
		    lblWitajWProgramie.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		    lblWitajWProgramie.setFont(SWTResourceManager.getFont("Exo 2 Light", 11, SWT.BOLD));
		    lblWitajWProgramie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    lblWitajWProgramie.setText("W\u0142a\u015Bnie uko\u0144czy\u0142e\u015B tworzenie ontologii oraz prac\u0119 z programem. \r\nPoni\u017Cej zosta\u0142a przedstawiona struktura utworzonej ontologii.\r\nW celu zachowania swoich wynik\u00F3w zapisz je do pliku");
		    
		    Tree tree = new Tree(composite, SWT.BORDER | SWT.FLAT | SWT.V_SCROLL | SWT.H_SCROLL);
		    tree.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		    tree.setBounds(10, 101, 450, 279);
		    Iterator classIter = WczytajWarstwe.model.listClasses();
			
	  		while (classIter.hasNext()) 
	  		{
	  			OntClass ontClass = (OntClass) classIter.next();
	  			
	  			String uri = ontClass.getLocalName();
	  			
	  			
	  			if ((uri != null && ontClass.isHierarchyRoot()) || (uri!=null && uri.startsWith("cDnSPL")) || (uri!=null && uri.startsWith("Element-cDnSPL")))
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
	  			  																							}}}}}}}}}}}}}}}}}}}}}}}}
		    
		    Label lblTakWygldaUtworzona = new Label(composite, SWT.NONE);
		    lblTakWygldaUtworzona.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		    lblTakWygldaUtworzona.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    lblTakWygldaUtworzona.setBounds(10, 79, 221, 15);
		    lblTakWygldaUtworzona.setText("Tak wygl\u0105da utworzona ontologia");
		    
		    Button button = new Button(shlTworzenieKapsuekCdnspl, SWT.NONE);
		    button.addSelectionListener(new SelectionAdapter() {
		    	@Override
		    	public void widgetSelected(SelectionEvent e) {
		    		StringWriter writer = new StringWriter();
				       WczytajWarstwe.model.write(writer, "RDF/XML-ABBREV");
				       String owlcode = writer.toString(); 
				          
				      FileDialog dialog = new FileDialog(shlTworzenieKapsuekCdnspl, SWT.SAVE);
				      dialog.setFilterExtensions(new String[] { "*.owl", "*.*" });
				      dialog.setFileName("plik.owl");
				                       
				      String filepath =  dialog.open(); // - podana œcie¿ka zapisu
				     
				    try{
				       FileWriter Fwriter = new FileWriter(filepath);
				       Fwriter.write(owlcode);
				       Fwriter.close();
				      } catch(FileNotFoundException fnfe) {fnfe.printStackTrace();}
				      catch(IOException ioe) {ioe.printStackTrace();}
				    MessageBox dialogg = new MessageBox(shlTworzenieKapsuekCdnspl);
					dialogg.setMessage("Zapisano plik");
					dialogg.open();
				    
		    		
		    	}
		    });
		    button.setText("Zapisz plik ");
		    button.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		    button.setBounds(449, 466, 110, 25);
		    
		
		    btnDalej.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				
					shlTworzenieKapsuekCdnspl.dispose();
					}
					
			});

		
	}
}
