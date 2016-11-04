package Interface;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;


public class NoweUri {

	protected Shell shlTworzenieKapsuekCdnspl;
	static NoweUri window = new NoweUri();
	Display display;
	static String myUri;
	
	

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
		shlTworzenieKapsuekCdnspl.setImage(SWTResourceManager.getImage(NoweUri.class, "/Interface/icon3.png"));
		shlTworzenieKapsuekCdnspl.setBackgroundImage(SWTResourceManager.getImage(NoweUri.class, "/Interface/tlo2.jpg"));
		shlTworzenieKapsuekCdnspl.setSize(719,670);
		shlTworzenieKapsuekCdnspl.setText("Tworzenie kapsu\u0142ek c.DnSPL");
		
		Button btnDalej = new Button(shlTworzenieKapsuekCdnspl, SWT.TOGGLE);
		btnDalej.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnDalej.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnDalej.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		
		btnDalej.setBounds(600, 413, 75, 25);
		btnDalej.setText("Dalej");
		
		
		 final Menu m = new Menu(shlTworzenieKapsuekCdnspl, SWT.BAR);

		    final MenuItem file = new MenuItem(m, SWT.CASCADE);
		    file.setText("&O programie");
		    final Menu filemenu = new Menu(shlTworzenieKapsuekCdnspl, SWT.DROP_DOWN);
		    file.setMenu(filemenu);
		    final MenuItem pomoc = new MenuItem(filemenu, SWT.CASCADE);
		    pomoc.setText("&Pomoc");;
		    final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
		    final MenuItem autorki = new MenuItem(filemenu, SWT.PUSH);
		    autorki.setText("&Autorki");

		    shlTworzenieKapsuekCdnspl.setMenuBar(m);
		    
		    pomoc.addSelectionListener(new SelectionAdapter() {
		        public void widgetSelected(SelectionEvent e) {
		        	MessageBox dialog = new MessageBox(shlTworzenieKapsuekCdnspl);
					dialog.setMessage(Wstep);
					dialog.open();
					dialog.setMessage(Ontologia);
					dialog.open();
					dialog.setMessage(Kapsulka);
					dialog.open();
					dialog.setMessage(Warstwa);
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
		    
		    Composite composite = new Composite(shlTworzenieKapsuekCdnspl, SWT.BORDER );
		    composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    composite.setBounds(188, 46, 487, 350);
		    
		    
		    Label lblWitajWProgramie = new Label(composite, SWT.NONE);
		    lblWitajWProgramie.setBounds(10, 21, 463, 32);
		    lblWitajWProgramie.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		    lblWitajWProgramie.setFont(SWTResourceManager.getFont("Exo 2 Light", 13, SWT.BOLD));
		    lblWitajWProgramie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    lblWitajWProgramie.setText("W tym miejscu mo\u017Cesz nada\u0107 URI dla nowej ontologii");
		    
		    Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		    lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		    lblNewLabel_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.NORMAL));
		    lblNewLabel_1.setBounds(10, 59, 453, 73);
		    lblNewLabel_1.setText("Poni\u017Cej znajduje sie pole, gdzie nale\u017Cy poda\u0107 URI. Mo\u017Cesz skorzysta\u0107\r\nz podanego domy\u015Blnie lub wpisa\u0107 w\u0142asne. Nale\u017Cy pami\u0119ta\u0107 o zachowaniu\r\nodpowiedniej sk\u0142adni podczas wpisywania (patrz domy\u015Blne URI jak przyk\u0142ad)");
		    
		    text = new Text(composite, SWT.BORDER);
		    text.setText("http://www.MyUri.com/myOntology");
		    text.setBounds(10, 150, 401, 21);
		    
		
		    btnDalej.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
						myUri = text.getText();
						Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);
						shlTworzenieKapsuekCdnspl.setCursor(waitCursor);
						WczytajWarstwe warstwa = new WczytajWarstwe();
						shlTworzenieKapsuekCdnspl.dispose();
						warstwa.open();
					
					}
					
			});

		
	}
	
	static String Wstep = ("W niniejszej pomocy zostan¹ wyjaœnione wa¿niejsze terminy, które pojawiaj¹ siê w trakcie programu.");
	static String Ontologia = ("\r\nOntologia - spos\u00F3b zaprezentowania poj\u0119\u0107 oraz relacji mi\u0119dzy nimi "
						+ "odnosz¹cych si\u0119 do okre\u015Blonej dziedziny wiedzy. Inaczej mo¿na to rozumie\u0107 jako zbi\u00F3r s\u0142\u00F3w, kt\u00F3re odnosz\u0105 si\u0119 do okre\u015Blonej dziedziny jak "
						+ "np. medycyna czy okreœlonej sytuacji np. rezerwacja hotelu. W ka\u017Cdym z tych przypadk\u00F3w b\u0119d\u0105 wyst\u0119powa\u0107 inne s\u0142owa, kt\u00F3re b\u0119da opisywa\u0142y dane ontologie: \r\n\n"
						+ "- w przypadku medycyny mog¹ to by\u0107 s\u0142owa zmi\u0105zane z chorobami, terminologia lekarska, s\u0142owa takie jak: pacjent, lekarz, leki itp.\r\n"
						+ "- w przypadku rezerwacji mog\u0105 znajdywa\u0107 si\u0119 okreœlenia zwi\u0105zane z "
						+ "d\u0142ugo\u015Bci\u0105 pobytu w hotelu, jako\u015Bci\u0105, miejscem znajdywania si\u0119 hotelu "
						+ "i r\u00F3wnie\u017C moga pojawi\u0107 sie s\u0142owa typu turysta, pokoj\u00F3wka, baga\u017C");
	
	static String Kapsulka = ("c.DnSPL jest sposobem reprezentacji ontologii, kt\u00F3ra wyr\u00F3\u017Cnia si\u0119 "
			+ "przedstawieniem ontologii w tzw. kapsu\u0142ce. Sk\u0142adaj\u0105 si\u0119 na ni\u0105: \r\n\n"
			+ "- Agent (A), \r\n- Kolektyw Agent\u00F3w (K),\r\n- Deskrypcja (D), Sytuacja (S),\r\n- Czas Wyst\u0105pienia Sytuacji (TS), \r\n"
			+ "- Czas Opisu Sytuacji (TD), \r\n- Obiekt Informacyjny (I),\r\n- Byt Bazowy (E)\r\n- oraz Pojecie (C). \r\n\n"
			+ "Program zapisuje kapsu\u0142ki w formie cDnSPL-StyuacjiXYZ, gdzie XYZ to nazwa sytuacji np. WystawienieFaktury, ProwadzenieZajec itd. R\u00F3wnie\u017C "
			+ "takie kapsu\u0142ki s\u0105 wyszukiwane przez program, podczas wczytywania z pliku.\r\n");
	
	static String Warstwa = ("Warstê fundacjonistyczn¹ bêdziemy nazywaæ ontologiê, która nie zawiera kapsu³ek i jest to ontologia wykorzystywana"
			+ "w procesie dodawania pojêæ do elementów kapsu³ki. Podstawow¹ warstw¹ wykorzystywanej w programie jest Byt, mo¿na jednak "
			+ "dodaæ w³asn¹ warstwê do programu, wtedy znajdowaæ siê ona bêdzie w BytBazowy");
	private Text text;
}
