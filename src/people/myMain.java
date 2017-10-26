package people;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//execution class for the first tasks
public class myMain {
	static Document doc;
	static XPath xpath;
	
	//method to load the xml file
	public void loadXML() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		doc = builder.parse("people.xml");
		getXPathObj();
	}
	
	//method to create the instance of an XPath object
	public static XPath getXPathObj() {
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		return xpath;
	}
	
	//main method, it checks the input to execute the needed task
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, ParseException {
		myMain test = new myMain();
		test.loadXML();
		if(args.length>0) {
			switch(args[0]) {
			case "getCompleteList": getCompleteList();break;
			case "getActivity": getActivity(args[1]);break;
			case "getDateFilteredPeople": getDateFilteredPeople(args[1], args[2]);break;
			default:break;
			}
		}
	}
	
	//Instruction 2 of the assignment
	public String getActivityDescription(String personId) throws XPathExpressionException {
		XPath xpath=getXPathObj();
		XPathExpression expr = xpath.compile("people/person[@id='"+personId+"']/activitypreference/description/text()");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		return nodes.item(0).getNodeValue();
	}
	
	//Instruction 2 of the assignment
	public String getActivityPlace(String personId) throws XPathExpressionException {
		XPath xpath=getXPathObj();
		XPathExpression expr = xpath.compile("people/person[@id='"+personId+"']/activitypreference/place/text()");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		return nodes.item(0).getNodeValue();
	}
	
	//Instruction 3 of the assignment
	public static void getCompleteList() throws XPathExpressionException, ParseException {
		XPath xpath=getXPathObj();
		XPathExpression expr = xpath.compile("people/person");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		for (int i=0; i<nodes.getLength();i++) {
			Node personi=nodes.item(i);
			NodeList personnodes=personi.getChildNodes();
			System.out.println("Firstname : "+personnodes.item(1).getTextContent());
			System.out.println("Lastname : "+personnodes.item(3).getTextContent());
			System.out.println("Birthdate : "+personnodes.item(5).getTextContent());
			Node activityi=personnodes.item(7);
			NodeList activitynodes=activityi.getChildNodes();
			System.out.println("Activity name : "+activitynodes.item(1).getTextContent());
			System.out.println("Description : "+activitynodes.item(3).getTextContent());
			System.out.println("Place : "+activitynodes.item(5).getTextContent());
			String date_s = activitynodes.item(7).getTextContent();  
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.s"); 
			Date date = dt.parse(date_s); 
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("Startdate : "+dt1.format(date));
			//System.out.println("Startdate : "+activitynodes.item(7).getTextContent());
			System.out.println("");
		}
	}
	
	//Instruction 4 of the assignment
	public static void getActivity(String personId) throws XPathExpressionException, ParseException {
		XPath xpath=getXPathObj();
		XPathExpression expr = xpath.compile("people/person[@id='"+personId+"']/activitypreference");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		Node activity=nodes.item(0);
		System.out.println("The activity preference of the person with id "+Integer.parseInt(personId)+" is: ");
		System.out.println("");
		System.out.println("Name : "+activity.getChildNodes().item(1).getTextContent());
		System.out.println("Description : "+activity.getChildNodes().item(3).getTextContent());
		System.out.println("Place : "+activity.getChildNodes().item(5).getTextContent());
		String date_s = activity.getChildNodes().item(7).getTextContent();  
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.s"); 
		Date date = dt.parse(date_s); 
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Startdate : "+dt1.format(date));
	}
	
	//Instruction 5 of the assignment
	public static void getDateFilteredPeople(String comparator, String mydate) throws XPathExpressionException, ParseException {
		XPath xpath=getXPathObj();
		if(comparator.equals("<")||comparator.equals(">") || comparator.equals("=")){
			String s_date = mydate.split("-")[0]+mydate.split("-")[2]+mydate.split("-")[1];
			XPathExpression expr = xpath.compile("//person[(number(substring(translate(activitypreference/startdate,'-T:.',''),1,8)))"+comparator+s_date+"]");
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i=0; i<nodes.getLength();i++) {
				Node personi=nodes.item(i);
				NodeList personnodes=personi.getChildNodes();
				System.out.println("Firstname : "+personnodes.item(1).getTextContent());
				System.out.println("Lastname : "+personnodes.item(3).getTextContent());
				System.out.println("Birthdate : "+personnodes.item(5).getTextContent());
				Node activityi=personnodes.item(7);
				NodeList activitynodes=activityi.getChildNodes();
				System.out.println("Activity name : "+activitynodes.item(1).getTextContent());
				System.out.println("Description : "+activitynodes.item(3).getTextContent());
				System.out.println("Place : "+activitynodes.item(5).getTextContent());
				String date_s = activitynodes.item(7).getTextContent();  
				SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.s"); 
				Date date = dt.parse(date_s); 
				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("Startdate : "+dt1.format(date));
				System.out.println("");
			}
		}
	}

}
