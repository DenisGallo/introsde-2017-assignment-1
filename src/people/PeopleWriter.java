package people;

import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import people.dao.PeopleStore;
import people.generated_classes.ActivityPreferenceType;
import people.generated_classes.PersonType;

//class to marshall a Java to an XML Object
public class PeopleWriter {  	
	public static PeopleStore people = new PeopleStore();

	//method to load the PeopleStore (=db) with some People
	public static void initializeDB() throws DatatypeConfigurationException {
		PersonType person1 = new PersonType();
		person1.setId("0001");
		person1.setFirstname("Bond");
		person1.setLastname("Martin");
		Date mydate=new Date();
		mydate.setYear(84);
		mydate.setMonth(8);
		mydate.setDate(20);
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(mydate);
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		person1.setBirthdate(date2);
		ActivityPreferenceType apt1=new ActivityPreferenceType();
		apt1.setId("100");
		apt1.setName("Running");
		apt1.setDescription("Running to the Park");
		apt1.setPlace("Gocciadoro");
		mydate=new Date();
		mydate.setYear(117);
		mydate.setMonth(9);
		mydate.setDate(13);
		mydate.setHours(11);
		mydate.setMinutes(50);
		mydate.setSeconds(0);
		c = new GregorianCalendar();
		c.setTime(mydate);
		date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		apt1.setStartdate(date2);
		person1.setActivitypreference(apt1);
		people.getData().add(person1);
		
		person1 = new PersonType();
		person1.setId("0002");
		person1.setFirstname("Mattia");
		person1.setLastname("Buffa");
		mydate=new Date();
		mydate.setYear(94);
		mydate.setMonth(2);
		mydate.setDate(24);
		c = new GregorianCalendar();
		c.setTime(mydate);
		date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		person1.setBirthdate(date2);
		apt1=new ActivityPreferenceType();
		apt1.setId("101");
		apt1.setName("Playing");
		apt1.setDescription("Playing videogames");
		apt1.setPlace("Home");
		mydate=new Date();
		mydate.setYear(117);
		mydate.setMonth(9);
		mydate.setDate(14);
		mydate.setHours(11);
		mydate.setMinutes(54);
		mydate.setSeconds(0);
		c = new GregorianCalendar();
		c.setTime(mydate);
		date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		apt1.setStartdate(date2);
		person1.setActivitypreference(apt1);
		people.getData().add(person1);
		
		person1 = new PersonType();
		person1.setId("0003");
		person1.setFirstname("Marco");
		person1.setLastname("Canale");
		mydate=new Date();
		mydate.setYear(44);
		mydate.setMonth(2);
		mydate.setDate(13);
		c = new GregorianCalendar();
		c.setTime(mydate);
		date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		person1.setBirthdate(date2);
		apt1=new ActivityPreferenceType();
		apt1.setId("102");
		apt1.setName("Gym");
		apt1.setDescription("Doing workout");
		apt1.setPlace("Gym");
		mydate=new Date();
		mydate.setYear(117);
		mydate.setMonth(8);
		mydate.setDate(25);
		mydate.setHours(16);
		mydate.setMinutes(50);
		mydate.setSeconds(0);
		c = new GregorianCalendar();
		c.setTime(mydate);
		date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		apt1.setStartdate(date2);
		person1.setActivitypreference(apt1);
		people.getData().add(person1);
	}	

	//main method executing instruction 7 of the assignment
	public static void main(String[] args) throws Exception {
		initializeDB();
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(people,new File("generated_people.xml")); 
        m.marshal(people, System.out);
    }
}
