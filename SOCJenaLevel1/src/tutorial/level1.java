package tutorial;

import java.util.Scanner;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

public class level1 {
	//create an empty Model
	static Model model = ModelFactory.createDefaultModel();
	
	public static void main(String[] args){
		//add three resources
		System.out.println("First publication");
		addResource("incollection1");
		System.out.println("Second publication");
		addResource("incollection2");
		System.out.println("Third publication");
		addResource("incollection3");
		
		//write
		//model.write(System.out, "RDF/XML-ABBREV");
		model.write(System.out);
		
		System.out.print("Please input the title of the publication you want to research: ");
		Scanner scanner_query = new Scanner(System.in); 
		String query = scanner_query.nextLine();
		queryPublicationName(query);
	}
	
	public static void addResource(String namespace){
		//some definitions
		String author = "";
		String title = "";
		String year = "";
		
		System.out.print("Please input author:");
		Scanner scanner_author = new Scanner(System.in); 
		author = scanner_author.nextLine();
		System.out.print("Please input title:");
		Scanner scanner_title = new Scanner(System.in); 
		title = scanner_title.nextLine();
		System.out.print("Please input year:");
		Scanner scanner_year = new Scanner(System.in); 
		year = scanner_year.nextLine();
		
		String NS = "http://somewhere/publication/";
		//create the resource
		Resource incollection = model.createResource(NS+namespace);
		//create properties
		Property authorP = model.createProperty(NS+"author");
		Property titleP = model.createProperty(NS+"title");
		Property yearP = model.createProperty(NS+"year");
				
		//add properties to the resource
		incollection.addProperty(authorP, author, XSDDatatype.XSDstring);
		incollection.addProperty(titleP, title, XSDDatatype.XSDstring);
		incollection.addProperty(yearP, year, XSDDatatype.XSDstring);

	}
	
	public static void queryPublicationName(String publicationName){
		String NS = "http://somewhere/publication/";
		// select all the resources with a VCARD.FN property
		ResIterator iter = model.listSubjectsWithProperty(model.getProperty(NS+"title"), publicationName);
		if (iter.hasNext()) {
		    System.out.println("Info of the publication:");
		    while (iter.hasNext()) {
		    	Resource re = iter.nextResource();
		        System.out.println("author: " + re.getProperty(model.getProperty(NS+"author")).getString());
		        System.out.println("title: " + re.getProperty(model.getProperty(NS+"title")).getString());
		        System.out.println("year: " + re.getProperty(model.getProperty(NS+"year")).getString());
		    }
		} else {
		    System.out.println("No publication were found in the RDF");
		}
		
	}

}
