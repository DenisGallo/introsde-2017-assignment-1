package people.classes;



import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "person")	
@XmlType(propOrder = { "firstname", "lastname", "birthdate", "activitypreference" })
@XmlAccessorType(XmlAccessType.FIELD)

//Person class to store the basic information of a person with them getters/setters
public class Person {
	private String firstname;		
	private String lastname;		
	private String birthdate;
	@XmlElement(name="activitypreference")
	private Activity activitypreference;
	@XmlAttribute(name="id")
	private Long personId;
	
	public Person(Long personId, String fname, String lname, String birthdate, Activity activitypreference) {
		this.setPersonId(personId); 	
		this.setFirstname(fname);
		this.setLastname(lname);
		this.setBirthdate(birthdate); 	
		this.setActivitypreference(activitypreference);
	}
	
	public Person(Long personId, String fname, String lname, String birthdate) {
		this.setPersonId(personId); 	
		this.setFirstname(fname);
		this.setLastname(lname);
		this.setBirthdate(birthdate); 
	}
	
	public Person() {
		this.firstname="Pinco";
		this.lastname="Pallino";
		this.personId = Math.round(Math.floor(Math.random()*9998)+1); 
		this.birthdate = this.getRandomDate();
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	
	public Activity getActivityprefence() {
		return activitypreference;
	}
	public void setActivitypreference(Activity a) {
		this.activitypreference = a;
	}
	private String getRandomDate() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); 		
		int year = (int) Math.round(Math.random()*(currentYear-1950)+1950);	
		int month = (int) Math.round(Math.floor(Math.random()*11)+1);
		int[] months = new int[]{31,28,30,30,31,30,31,31,30,31,30,31};
		if ((currentYear % 4 == 0) && ((currentYear % 100 != 0) || (currentYear % 400 == 0))) {
			months[1] = 29;
		}
		long day = Math.round(Math.floor(Math.random()*(months[month-1]-1)+1));
		return ""+year+"-"+month+"-"+day;
	}
}
