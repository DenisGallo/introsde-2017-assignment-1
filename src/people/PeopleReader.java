package people;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import people.dao.PeopleStore;
import people.generated_classes.ActivityPreferenceType;
import people.generated_classes.PersonType;

//class to unmarshall an XML file to Java
public class PeopleReader {  	
	public static PeopleStore people = new PeopleStore();

	//main method to execute the instruction 7 of the assignment
	public static void main(String[] args) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        System.out.println();
        System.out.println("Output from our XML File: ");
        Unmarshaller um = jc.createUnmarshaller();
        PeopleStore people = (PeopleStore) um.unmarshal(new FileReader("people.xml"));
        List<PersonType> list = people.getData();
        for (PersonType person : list) {
			System.out.println("Firstname : "+person.getFirstname());
			System.out.println("Lastname : "+person.getLastname());
			System.out.println(person.getBirthdate());
			Calendar calendar = person.getBirthdate().toGregorianCalendar();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			formatter.setTimeZone(calendar.getTimeZone());
			String dateString = formatter.format(calendar.getTime());
			System.out.println("Birthdate : "+dateString);
			System.out.println("Activity name : "+person.getActivitypreference().getName());
			System.out.println("Description : "+person.getActivitypreference().getDescription());
			System.out.println("Place : "+person.getActivitypreference().getPlace()); 
			calendar = person.getActivitypreference().getStartdate().toGregorianCalendar();
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			formatter.setTimeZone(calendar.getTimeZone());
			dateString = formatter.format(calendar.getTime());
			System.out.println("Startdate : "+dateString);
			System.out.println("");
        }

    }
}
