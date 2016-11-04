package Interface;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;



public class Dalej {

	protected Shell shlCdnspl;
	Display display;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Dalej window = new Dalej();
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
	    
	    Button btnCofnij = new Button(shlCdnspl, SWT.NONE);
	    btnCofnij.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		shlCdnspl.dispose();
	    		Wyswietl cofnij = new Wyswietl();
	    		cofnij.open();
	    	}
	    });
	    btnCofnij.setText("Cofnij");
	    btnCofnij.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	    btnCofnij.setBounds(350, 466, 75, 25);
	    
	    
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
		 
		 final Button btnRadioButton_1 = new Button(composite, SWT.RADIO);
		 btnRadioButton_1.setSelection(true);
		 btnRadioButton_1.setBounds(10, 150, 310, 37);
		 
		 btnRadioButton_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
		 btnRadioButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		 btnRadioButton_1.setText("Kontynuowa\u0107 dzia\u0142ania na ontologii");
		 Label lblWczytajPlik = new Label(composite, SWT.NONE);
		 lblWczytajPlik.setText("Kontynuuj\u0105c wykonywanie operacji, w dalszej cz\u0119\u015Bci b\u0119dziesz mia\u0142 mo\u017Cliwo\u015B\u0107\nutworzenia zestawu poj\u0119\u0107 odpowiadaj\u0105cych bytom wydarzaj\u0105cym si\u0119\n(Zadanie, OpisAktywnosci, OpisZdarzenia, OpisStanu) wraz z zestawem r\u00F3l\noraz przeprowadzi\u0107 reifikacj\u0119.");
		 lblWczytajPlik.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		 lblWczytajPlik.setBounds(30, 193, 453, 87);
		 lblWczytajPlik.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		 lblWczytajPlik.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		 final Button btnRadioButton = new Button(composite, SWT.RADIO);
		 btnRadioButton.setBounds(10, 104, 322, 30);
		 btnRadioButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		 
		 	btnRadioButton.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
		 	btnRadioButton.setText("Zako\u0144czy\u0107 prac\u0119 z programem i zapisa\u0107 plik");
		 	Label lblWczytajPlik1 = new Label(composite, SWT.NONE);
		 	lblWczytajPlik1.setBounds(10, 10, 387, 55);
		 	lblWczytajPlik1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		 	lblWczytajPlik1.setFont(SWTResourceManager.getFont("Exo 2 Light", 12, SWT.BOLD));
		 	lblWczytajPlik1.setText("W\u0142a\u015Bnie utworzy\u0142e\u015B podstawow\u0105 wersj\u0119 kapsu\u0142ki! \r\nWybierz co chcesz dalej zrobi\u0107");
		 	final Button btnDalej = new Button(shlCdnspl, SWT.NONE);
			final Button btnNewButton = new Button(shlCdnspl, SWT.NONE);
			btnNewButton.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
			final Button btnNewButton_1 = new Button(shlCdnspl, SWT.NONE);
			btnNewButton_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		 	
		 	btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				btnNewButton_1.setEnabled(true);
				btnNewButton.setEnabled(true);
				btnDalej.setEnabled(false);				
			}
		});
		 btnRadioButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDalej.setEnabled(true);
				btnNewButton_1.setEnabled(false);
				btnNewButton.setEnabled(false);
				
			}
		});
		shlCdnspl.setLayout(null);
		
		
	    btnNewButton.setBounds(431, 466, 82, 25);
	    btnNewButton.setText("Zapisz plik ");
	    
	    
	    btnNewButton_1.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		shlCdnspl.dispose();
	    	}
	    });
	   
	    btnNewButton_1.setBounds(519, 466, 75, 25);
	    btnNewButton_1.setText("Koniec");
		
		
		
		btnDalej.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
	
		btnDalej.setBounds(600, 466, 75, 25);
		btnDalej.setText("Dalej");
	    if(btnRadioButton_1.getSelection()==true){
	    	
	    	btnDalej.setEnabled(true);
			btnNewButton_1.setEnabled(false);
			btnNewButton.setEnabled(false);
	    	
	    }
	    else
	    {
	    	btnNewButton_1.setEnabled(true);
			btnNewButton.setEnabled(true);
			btnDalej.setEnabled(false);
	    }
	    btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
			    MessageBox dialogg = new MessageBox(shlCdnspl);
				dialogg.setMessage("Zapisano plik");
				dialogg.open();
			    
			    
			}
			});
	    btnDalej.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlCdnspl.dispose();
				Role role = new Role();
				role.open();
				
			}
		});
	    }
	

	}

