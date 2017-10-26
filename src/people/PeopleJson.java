package people;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import people.generated_classes.ActivityPreferenceType;
import people.generated_classes.PersonType;
import people.dao.PeopleStore;

//this class is needed to convert our Java to Json
public class PeopleJson {  	
	public static PeopleStore people = new PeopleStore();
	
	//method to insert some people into our PeopleStore (=db)
	public static void initializeDB() throws DatatypeConfigurationException {
		PersonType person1 = new PersonType();
		person1.setId("0001");
		person1.setFirstname("Bond");
		person1.setFirstname("Martin");
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
		person1.setFirstname("Buffa");
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
		person1.setFirstname("Canale");
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
		
		person1 = new PersonType();
		person1.setId("0004");
		person1.setFirstname("Kevin");
		person1.setFirstname("Martin");
		mydate=new Date();
		mydate.setYear(84);
		mydate.setMonth(10);
		mydate.setDate(20);
		c = new GregorianCalendar();
		c.setTime(mydate);
		date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		person1.setBirthdate(date2);
		apt1=new ActivityPreferenceType();
		apt1.setId("103");
		apt1.setName("Poker");
		apt1.setDescription("Playing online poker");
		apt1.setPlace("home");
		mydate=new Date();
		mydate.setYear(117);
		mydate.setMonth(6);
		mydate.setDate(13);
		mydate.setHours(10);
		mydate.setMinutes(50);
		mydate.setSeconds(0);
		c = new GregorianCalendar();
		c.setTime(mydate);
		date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		apt1.setStartdate(date2);
		person1.setActivitypreference(apt1);
		people.getData().add(person1);	 
	}	

	//main method to execute the instruction 8 of the assignment
	public static void main(String[] args) throws Exception {
		initializeDB(); 
		ObjectMapper mapper = new ObjectMapper();
		JaxbAnnotationModule module = new JaxbAnnotationModule();
        mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(new File("generated_people.json"), people);
    }
}
