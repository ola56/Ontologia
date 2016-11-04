package Interface;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.util.iterator.ExtendedIterator;
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


public class WKaRoleThing {

	protected Shell shlTworzenieKapsuekCdnspl;
	static WKaRoleThing window = new WKaRoleThing();
	Display display;
	private Text text;
	static String rola, nazwa, bytWyd, byt;
	private Text text_1;
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
		    
		Composite composite = new Composite(shlTworzenieKapsuekCdnspl, SWT.BORDER);
	    composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    composite.setBounds(188, 24, 487, 526);
	    
	    Label lblWidokOntologii = new Label(composite, SWT.NONE);
	    lblWidokOntologii.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	    lblWidokOntologii.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    lblWidokOntologii.setBounds(10, 88, 153, 15);
	    lblWidokOntologii.setText("Widok Element-c.DnSPL");
	    
	    final Tree tree = new Tree(composite,  SWT.VERTICAL | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    tree.setBounds(10, 122, 238, 215);
	    tree.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	    tree.setFont(SWTResourceManager.getFont("Calibri", 9, SWT.NORMAL));
	    
	    		
	    refresh(tree);
		
	    text = new Text(composite, SWT.BORDER );
		text.setBounds(10, 404, 348, 21);
		
		Button btnRadioButton = new Button(composite, SWT.RADIO);
		btnRadioButton.setBounds(268, 239, 90, 16);
		btnRadioButton.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnRadioButton.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
		btnRadioButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("Zadanie");
				bytWyd = "WZadaniu";
			}
		});
		btnRadioButton.setText("Zadanie");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnNewButton.setBounds(370, 401, 102, 27);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if((text.getText() != null || text.getText() != ""))
				{
					byt = text.getText();
					ontKlass = WczytajPlik.modelnowy.createClass(WczytajPlik.MyUrinowy + "#" + text.getText());
					Iterator classIter = WczytajPlik.modelnowy.listClasses();
		   		while(classIter.hasNext())
		   			{
		   			OntClass ontClass = (OntClass) classIter.next();
		   			if(ontClass.getLocalName() != null && ontClass.getLocalName().startsWith("C-"+WKaNazwaKapsulkiThing.skrot))
		   			{
		   				ontClass.addSubClass(ontKlass);
		   				tree.removeAll();
		   				refresh(tree);
		   				text.setText("");
		   			}
		   			}
		   		MessageBox dialog = new MessageBox(shlTworzenieKapsuekCdnspl);
				dialog.setMessage("Dodano! Widok ontologii zosta³ zaktualizowany.");
				dialog.open();
				MessageBox msgBox1 = new MessageBox(shlTworzenieKapsuekCdnspl, SWT.ICON_QUESTION
			            | SWT.YES | SWT.NO);
			    msgBox1.setMessage("Czy chcesz dodaæ etykiety do utworzonego bytu wydarzaj¹cego siê?");
			    msgBox1.setText("Dodanie etykiet");
			    int response1 = msgBox1.open();
			    if (response1 == SWT.YES)
			    {
			       shlTworzenieKapsuekCdnspl.dispose();
			       WKaEtykietyBytyThing Dod = new WKaEtykietyBytyThing();
			       Dod.open();				        	
			    }
			   
			    else{
				MessageBox msgBox = new MessageBox(shlTworzenieKapsuekCdnspl, SWT.ICON_QUESTION
			            | SWT.YES | SWT.NO);
			        msgBox.setMessage("Czy chcesz dodaæ role do utworzonego bytu?");
			        msgBox.setText("Dodanie ról");
			        int response = msgBox.open();
			        if (response == SWT.YES)
			        {
			        	shlTworzenieKapsuekCdnspl.dispose();
			        	WKaDodanieRolThing rola = new WKaDodanieRolThing();
			        	rola.open();				        	
			        }
			    }
				}
			
			}
		});
		btnNewButton.setText("Dodaj");
		
		Button btnRadioButton_1 = new Button(composite, SWT.RADIO);
		btnRadioButton_1.setBounds(268, 141, 116, 16);
		btnRadioButton_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnRadioButton_1.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
		btnRadioButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnRadioButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("OpisAktywnosci");
				bytWyd = "WOpisieAktywnosci";
			}
		});
		btnRadioButton_1.setText("OpisAktywnosci");
		
		Button btnRadioButton_2 = new Button(composite, SWT.RADIO);
		btnRadioButton_2.setBounds(268, 190, 90, 16);
		btnRadioButton_2.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnRadioButton_2.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
		btnRadioButton_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnRadioButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("OpisStanu");
				bytWyd = "WOpisieStanu";
			}
		});
		btnRadioButton_2.setText("OpisStanu");
		
		Button btnRadioButton_3 = new Button(composite, SWT.RADIO);
		btnRadioButton_3.setBounds(268, 293, 102, 16);
		btnRadioButton_3.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnRadioButton_3.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
		btnRadioButton_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnRadioButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("OpisZdarzenia");
				bytWyd = "WOpisieZdarzenia";
			}
		});
		btnRadioButton_3.setText("OpisZdarzenia");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblNewLabel.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 354, 462, 44);
		lblNewLabel.setText("Pole tekstowe nie mo\u017Ce by\u0107 puste. Po klikni\u0119ciu przycisku \"Dodaj\" wpisane poj\u0119cie\r\nzostanie dodane do ontologii. Widok wy\u015Bwietlanego fragmentu ontologii\r\nzostanie zaktualizowany");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.BOLD));
		lblNewLabel_1.setBounds(10, 10, 420, 61);
		lblNewLabel_1.setText("Poni\u017Cej zostan\u0105 dodane byty wydarzaj\u0105ce si\u0119. Po zaznaczeniu wybranego\r\nbytu pojawi si\u0119 on w polu tekstowym na dole. Nale\u017Cy dopisa\u0107 do niego\r\nnazw\u0119 i doda\u0107 przyciskiem \"Dodaj\". Po dodaniu b\u0119dzie mo\u017Cliwo\u015B\u0107\r\ndodania do niego r\u00F3l w nowym oknie");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(436, 10, 37, 25);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox dialog = new MessageBox(shlTworzenieKapsuekCdnspl);
				dialog.setMessage("Tutaj mo¿esz dodaæ pojêcia odpowiadaj¹ce bytom wydarzaj¹cym. S¹ to: OpisStanu, OpisZdarzenia, OpisAktywnosci oraz Zadanie.\nZosta³y one podane po prawej stronie. "
						+ "Nale¿y zaznaczyæ wybrany byt ¿eby jego nazwa pokaza³a sie na dole \nw polu tekstowym. Mo¿esz go uzupe³niæ o nazwê dopisuj¹c j¹ za podanym wzorcem np. ZadaniePierwsze\r\n\n"
						+ "Po uzupe³nieniu pola tekstowego mo¿na wcisn¹æ przycisk \"Dodaj\"."
						+ "Dziêki temu podane pojêcie zostanie dodane pod C-"+WKaNazwaKapsulkiThing.skrot+" , \na widok ontologii zaktualizuje siê o podane pojêcie.\r\n Zostaniesz równie¿ zapytany o to czy chcesz"
						+ " dodaæ role do dodanego bytu. Po naciœniêciu \"Tak\" otworzy siê nowe okno, gdzie na podobnej zasadzie co byty, mo¿esz dodaæ role. Po naciœnieciu \"Zakoñcz\" przy"
						+ "dodawaniu ról, powrócisz do okno z bytami.\r\n\n"
						+ "Ni¿ej zosta³ zamieszczony przycisk \"Usuñ\", który usunie pojêcie zaznaczone w widoku ontologii, pod pojêciem C-"+WKaNazwaKapsulkiThing.skrot
						+ "\r\nKlikaj¹c przycisk \"Dalej\" przejdziesz do reifikacji \n(dodawanie pojêæ do E-"+WKaNazwaKapsulkiThing.skrot+" i C-"+WKaNazwaKapsulkiThing.skrot+")");
				dialog.open();
			}
		});
		btnNewButton_1.setText("?");
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Zaznacz wybrany byt wydarzaj\u0105cy");
		label.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(268, 87, 215, 21);
		
		Label lblJeeliChceszUsun = new Label(composite, SWT.NONE);
		lblJeeliChceszUsun.setText("Je\u017Celi chcesz usun\u0105\u0107 dodan\u0105 przez siebie rol\u0119 lub byt wydarzaj\u0105cy si\u0119 (spod C-S),\r\nto zaznacz w widoku wybrane poj\u0119cie i naci\u015Bnij \"Usu\u0144\"");
		lblJeeliChceszUsun.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblJeeliChceszUsun.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblJeeliChceszUsun.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblJeeliChceszUsun.setBounds(10, 448, 462, 35);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(10, 489, 348, 21);
		
		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setBounds(370, 487, 102, 25);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			     OntClass ontClass2 = WczytajPlik.modelnowy.getOntClass(WczytajPlik.MyUrinowy +"#"+ nazwa);
			     OntClass ontClass1 = WczytajPlik.modelnowy.getOntClass(WczytajPlik.MyUrinowy + "#C-" + WKaNazwaKapsulkiThing.skrot);
			     if(ontClass2.hasSuperClass(ontClass1))
			     {
			    	ontClass2.remove();
			    	MessageBox dialog = new MessageBox(shlTworzenieKapsuekCdnspl);
					dialog.setMessage("Usuniêto! Widok ontologii zosta³ zaktualizowany.");
					dialog.open();
					text_1.setText("");
			     }
			     else
			     {
			      MessageBox dialog = new MessageBox(shlTworzenieKapsuekCdnspl);
			     dialog.setMessage("Mo¿esz usun¹æ tylko dodane role lub byty wydarzaj¹ce siê.");
			     dialog.open();
			     }
			    tree.removeAll();
			    refresh(tree);

			}
		});
		btnNewButton_2.setText("Usu\u0144");
		
		Button button = new Button(shlTworzenieKapsuekCdnspl, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlTworzenieKapsuekCdnspl.dispose();
				WKaReifikacjaThing reifikacja = new WKaReifikacjaThing();
				reifikacja.open();
			}
		});
		button.setText("Dalej");
		button.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		button.setBounds(581, 556, 94, 25);
		
		Button btnCofnij = new Button(shlTworzenieKapsuekCdnspl, SWT.NONE);
		btnCofnij.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlTworzenieKapsuekCdnspl.dispose();
				WKaDalejThing cofnij = new WKaDalejThing();
				cofnij.open();
			}
		});
		btnCofnij.setText("Cofnij");
		btnCofnij.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnCofnij.setBounds(481, 556, 94, 25);
		 tree.addListener(SWT.Selection, new Listener() {
		        public void handleEvent(Event event) {
		        final String nazwwa=event.item.toString();
		        String nazwaa = nazwwa.substring(10, (nazwwa.length()-1));
		        setnazwa(nazwaa);
		        text_1.setText(nazwa);
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
		 
	public void refresh(Tree tree){
		Iterator classIter = WczytajPlik.modelnowy.listClasses();
		
		while (classIter.hasNext()) 
		{
			OntClass ontClass = (OntClass) classIter.next();
			
			String uri = ontClass.getLocalName(); 
			
			
			if ((uri != null && uri.startsWith("Element-cDnSPL-Sytuacji" + WKaNazwaKapsulkiThing.nazwa1)))
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
			  											  	  					
			  											}
			  										}
			  									}
			  								}
			  							}
			  						}
			  					}
							}
						}
			  		}
				}
		}	}
}
