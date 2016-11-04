package Interface;

import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

public class BudowaKapsulki {
	
	String[] litery = {"TD", "TS", "E", "C", "K", "I", "D", "A", "S"};

	BudowaKapsulki(OntModel model, OntClass[] klas, String MyUri, OntClass cdnspl, OntClass Elcdnspl)
	{
		
  		ObjectProperty MaJakoCzescWlasciwa = model.createObjectProperty(MyUri + "#maJakoCzescWlasciwa");
  		cdnspl.addSuperClass(model.createSomeValuesFromRestriction(null, MaJakoCzescWlasciwa, Elcdnspl));
  		
  		for (int i = 0; i<litery.length; i++)
  			cdnspl.addSuperClass(model.createSomeValuesFromRestriction(null, MaJakoCzescWlasciwa, klas[i]));
  		
  		ObjectProperty JestCzesciaWlasciwa = model.createObjectProperty(MyUri + "#jestCzesciaWlasciwa");
  		
  		for (int i = 0; i < litery.length; i++)
  		{
  			klas[i].addSuperClass(model.createSomeValuesFromRestriction(null, JestCzesciaWlasciwa, Elcdnspl));
  		}
  		//cdnspl.addSuperClass(model.createSomeValuesFromRestriction(null, JestCzesciaWlasciwa, Elcdnspl));
  		Elcdnspl.addSuperClass(model.createSomeValuesFromRestriction(null, JestCzesciaWlasciwa, cdnspl));
  		  		
  		ObjectProperty JestKlasyfikowanyPrzez = model.createObjectProperty(MyUri + "#jestKlasyfikowanyPrzez");
  		klas[2].addSuperClass(model.createSomeValuesFromRestriction(null, JestKlasyfikowanyPrzez, klas[3]));
  		// E jest klasyfikowane przez C
  		
  		//relacja klasyfikuje
  		ObjectProperty klasyfikuje = model.createObjectProperty(MyUri + "#klasyfikuje");
  		klas[3].addSuperClass(model.createSomeValuesFromRestriction(null, klasyfikuje, klas[2]));
  		 // C klasyfikuje E
  		
  		//relacja ustanawia
  		ObjectProperty ustanawia = model.createObjectProperty(MyUri + "#ustanawia");
  		klas[2].addSuperClass(model.createSomeValuesFromRestriction(null, ustanawia, klas[8]));
  		 // E ustanawia S
  		
  		//relacja JestUstanawianePrzez
  		ObjectProperty JestUstanawianePrzez = model.createObjectProperty(MyUri + "#jestUstanawianePrzez");
  		klas[8].addSuperClass(model.createSomeValuesFromRestriction(null, JestUstanawianePrzez,klas[2]));
  		//S jest ustanowione przez E
  		
  		//relacja spe³nia
  		ObjectProperty spelnia = model.createObjectProperty(MyUri + "#spelnia");
  		klas[8].addSuperClass(model.createSomeValuesFromRestriction(null, spelnia, klas[6]));
  		 //  S spelnia D
  		
  		//relacja JestSpelniony
  		ObjectProperty JestSpelniony = model.createObjectProperty(MyUri + "#jestSpelniony");
  		klas[6].addSuperClass(model.createSomeValuesFromRestriction(null, JestSpelniony, klas[8]));
  		 // D jest spelniane przez S
  		
  		//JestPodzielanaPrzez
  		ObjectProperty JestPodzielanaPrzez = model.createObjectProperty(MyUri + "#jestPodzielanaPrzez");
  		klas[6].addSuperClass(model.createSomeValuesFromRestriction(null, JestPodzielanaPrzez, klas[7]));
  		//D jest podzielane przez A
  		
  		//podziela
  		ObjectProperty podziela = model.createObjectProperty(MyUri + "#podziela");
  		klas[7].addSuperClass(model.createSomeValuesFromRestriction(null, podziela, klas[6]));
  		 // A podziela D
  		
  		//JestWyrazanaPrzez
  		ObjectProperty JestWyrazanaPrzez = model.createObjectProperty(MyUri + "#jestWyrazanaPrzez");
  		klas[6].addSuperClass(model.createSomeValuesFromRestriction(null, JestWyrazanaPrzez, klas[5]));
  		// D jest wyrazane przez I
  		
  		//wyraza
  		ObjectProperty wyraza = model.createObjectProperty(MyUri + "#wyraza");
  		klas[5].addSuperClass(model.createSomeValuesFromRestriction(null, wyraza, klas[6]));
  		//I  wyraza D
  		
  		//JestCzlonkiem
  		ObjectProperty JestCzlonkiem = model.createObjectProperty(MyUri + "#jestCzlonkiem");
  		klas[7].addSuperClass(model.createSomeValuesFromRestriction(null, JestCzlonkiem, klas[4]));
  	 // A jest czlonkiem K
  		
  		//MaJakoCzlonka
  		ObjectProperty MaJakoCzlonka = model.createObjectProperty(MyUri + "#maJakoCzlonka");
  		klas[4].addSuperClass(model.createSomeValuesFromRestriction(null, MaJakoCzlonka, klas[7]));
  		// K ma jako cz³onka A
  	}
}
