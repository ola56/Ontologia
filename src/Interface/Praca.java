package Interface;

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
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;

public class Praca {

	protected Shell shlChceszPracowaNa;
	Display display;
	public static boolean wybor;
		/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Praca window = new Praca();
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
	    Rectangle rect = shlChceszPracowaNa.getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
	    shlChceszPracowaNa.setLocation(x, y);
		shlChceszPracowaNa.open();
		shlChceszPracowaNa.layout();
		
		while (!shlChceszPracowaNa.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlChceszPracowaNa = new Shell();
		shlChceszPracowaNa.setImage(SWTResourceManager.getImage(Powitalne.class, "/Interface/icon3.png"));
		shlChceszPracowaNa.setBackgroundImage(SWTResourceManager.getImage(Powitalne.class, "/Interface/tlo2.jpg"));
		shlChceszPracowaNa.setSize(719,670);
		shlChceszPracowaNa.setText("Tworzenie kapsu\u0142ek c.DnSPL");
		shlChceszPracowaNa.setLayout(null);
		Composite composite = new Composite(shlChceszPracowaNa, SWT.BORDER);
	    composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    composite.setBounds(188, 46, 505, 408);
		Label lblChceszPracowa = new Label(composite, SWT.NONE);
		lblChceszPracowa.setBounds(10, 22, 276, 25);
		lblChceszPracowa.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblChceszPracowa.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblChceszPracowa.setFont(SWTResourceManager.getFont("Exo 2 Light", 13, SWT.BOLD));
		lblChceszPracowa.setText("Rodzaj pracy z programem");
		final Menu m = new Menu(shlChceszPracowaNa, SWT.BAR);
		   shlChceszPracowaNa.setMenuBar(m);
		    
		    final MenuItem file = new MenuItem(m, SWT.CASCADE);
		    file.setText("&O programie");
		    final Menu filemenu = new Menu(shlChceszPracowaNa, SWT.DROP_DOWN);
		    file.setMenu(filemenu);
		    final MenuItem pomoc = new MenuItem(filemenu, SWT.CASCADE);
		    pomoc.setText("&Pomoc");;
		    final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
		    final MenuItem autorki = new MenuItem(filemenu, SWT.PUSH);
		    autorki.setText("&Autorki");
		    
		    pomoc.addSelectionListener(new SelectionAdapter() {
		        public void widgetSelected(SelectionEvent e) {
		        	MessageBox dialog = new MessageBox(shlChceszPracowaNa);
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
		        	MessageBox dialog = new MessageBox(shlChceszPracowaNa);
					dialog.setMessage("Graficzny interfejs do tworzenia dobrze ufundowanych ontologii w paradygmacie c.DnSPL\r\n\n"
							+ "Program napisany w ramach pracy in¿ynierskiej\r\n\n"
							+ "Autorki: Aleksandra Piotrowicz, Izabela Procek\r\n\n"
							+ "Promotor: dr in¿. Jolanta Cybulka");
					dialog.open();
		        }
		      });

		    shlChceszPracowaNa.setMenuBar(m);
		
		final Button btnRadioButton = new Button(composite, SWT.RADIO);
		btnRadioButton.setBounds(10, 63, 359, 16);
		btnRadioButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnRadioButton.setSelection(true);
	
		btnRadioButton.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
		btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnRadioButton.setText("tworzenie nowej kapsu\u0142ki ontologicznej c.DnSPL");
		
		final Button btnRadioButton_1 = new Button(composite, SWT.RADIO);
		btnRadioButton_1.setBounds(10, 150, 346, 16);
		btnRadioButton_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
		btnRadioButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnRadioButton_1.setText("specjalizowanie kapsu\u0142ek wczytanych z pliku");
		
		Label lblzostanieszPrzeprowadzonyPrzez = new Label(composite, SWT.NONE);
		lblzostanieszPrzeprowadzonyPrzez.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		lblzostanieszPrzeprowadzonyPrzez.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblzostanieszPrzeprowadzonyPrzez.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblzostanieszPrzeprowadzonyPrzez.setBounds(30, 85, 443, 59);
		lblzostanieszPrzeprowadzonyPrzez.setText("Zostaniesz przeprowadzony przez ca\u0142y proces tworzenia kapsu\u0142ki:\r\nod podania nazwy, a\u017C do przypisania r\u00F3l i reifikacji.\r\nPoszczeg\u00F3lne czynno\u015Bci zostan\u0105 opisane w odpowiadaj\u0105cym im \r\nkrokach programu. ");
		
		Label lblWTymTrybie = new Label(composite, SWT.NONE);
		lblWTymTrybie.setText("W tym trybie pracy b\u0119dziesz mia\u0142 mo\u017Cliwo\u015B\u0107 specjalizowania w\u0142asnych\r\nkapsu\u0142ek z wczytanego przez siebie pliku, o kt\u00F3ry zostaniesz poproszony\r\nw nast\u0119pnym oknie. W dalszych krokach b\u0119dziesz m\u00F3g\u0142 dokona\u0107 specjalizacji.");
		lblWTymTrybie.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		lblWTymTrybie.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblWTymTrybie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWTymTrybie.setBounds(30, 172, 461, 48);
		
		final Button btnDokoczenieTworzeniaKapsuki = new Button(composite, SWT.RADIO);
		btnDokoczenieTworzeniaKapsuki.setText("doko\u0144czenie tworzenia kapsu\u0142ki z zapisanego wcze\u015Bniej pliku z programu");
		btnDokoczenieTworzeniaKapsuki.setFont(SWTResourceManager.getFont("Exo 2 Light", 10, SWT.BOLD));
		btnDokoczenieTworzeniaKapsuki.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnDokoczenieTworzeniaKapsuki.setBounds(10, 226, 481, 16);
		
		Label lblWTymTrybie_1 = new Label(composite, SWT.NONE);
		lblWTymTrybie_1.setText("W tym trybie pracy mo\u017Cesz doko\u0144czy\u0107 tworzenie kapsu\u0142ki. Aby tego dokona\u0107,\r\nnale\u017Cy wczyta\u0107 wygenerowany plik z poprzedniego dzia\u0142ania programu.\r\nJe\u017Celi poszczeg\u00F3lne kroki tworzenia kapsu\u0142ki zosta\u0142y ju\u017C poprzednio \r\nwykonane kliknij \"Pomi\u0144\".");
		lblWTymTrybie_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		lblWTymTrybie_1.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		lblWTymTrybie_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWTymTrybie_1.setBounds(30, 248, 461, 67);
		
		Button btnDalej = new Button(shlChceszPracowaNa, SWT.NONE);
		btnDalej.setFont(SWTResourceManager.getFont("Exo 2 Light", 9, SWT.NORMAL));
		btnDalej.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(btnRadioButton_1.getSelection()==true){
					Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);
					shlChceszPracowaNa.setCursor(waitCursor);
					wybor = true;
					WczytajPlik plik = new WczytajPlik();	
					shlChceszPracowaNa.dispose();
					plik.open();
					}
				
				else if(btnRadioButton.getSelection()==true){
					Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);
					shlChceszPracowaNa.setCursor(waitCursor);
					wybor = false;
					//this.setCursor(display, SWT.CURSOR_WAIT);
					//shlChceszPracowaNa.dispose();
					NoweUri uri = new NoweUri();
					//WczytajWarstwe wczytaj = new WczytajWarstwe();
					//ShowProgress show = new ShowProgress();
					//show.open();
					//for(int i =0; i<100000; i++)
					//{}
					shlChceszPracowaNa.dispose();
					uri.open();
					//shlChceszPracowaNa.dispose();
				    }
				
				else if(btnDokoczenieTworzeniaKapsuki.getSelection() == true){
					Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);
					shlChceszPracowaNa.setCursor(waitCursor);
					//wybor = true;
					EdyWczytajPlik plik = new EdyWczytajPlik();	
					shlChceszPracowaNa.dispose();
					plik.open();		
				    }		
			}
		});
		btnDalej.setBounds(600, 466, 75, 25);
		btnDalej.setText("Dalej");
	}
}
