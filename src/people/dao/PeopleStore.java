package people.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import people.generated_classes.*;

@XmlRootElement(name="people")
@XmlAccessorType(XmlAccessType.FIELD)
//DAO class needed to store instances of the Person class to work with 
//them during other tasks like marshalling and unmarshalling
public class PeopleStore {
	@XmlElement(name="person")
	private List<PersonType> myPeopleList = new ArrayList<PersonType>();
	
	public PeopleStore () {
	}

	public List<PersonType> getData() {
		return myPeopleList;
	}

	public void setData(List<PersonType> data) {
		this.myPeopleList = data;
	}
}
