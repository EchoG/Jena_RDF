package model;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class convertToRDF {
	//create an empty Model
	static Model model = ModelFactory.createDefaultModel();
	public static void addResource(Publication publication, String namespace){
		//some definitions
		String key = publication.getKey();
		String mdate = publication.getMdate();
		ArrayList<String> authors = publication.getAuthors();
		String title = publication.getTitle();
		String pages = publication.getPages();
		String year = publication.getYear();
		String booktitle = publication.getBooktitle();
		String ee = publication.getEe();
		String crossref = publication.getCrossref();
		String url = publication.getUrl();
		
		String NS = "http://somewhere/publication/";
		//create the resource
		Resource incollection = model.createResource(NS+namespace);
		//create properties
		Property keyP = model.createProperty(NS+"key");
		Property mdateP = model.createProperty(NS+"mdate");
		Property titleP = model.createProperty(NS+"title");
		Property pagesP = model.createProperty(NS+"pages");
		Property booktitleP = model.createProperty(NS+"booktitle");
		Property yearP = model.createProperty(NS+"year");
		Property eeP = model.createProperty(NS+"ee");
		Property crossrefP = model.createProperty(NS+"crossref");
		Property urlP = model.createProperty(NS+"url");
		Property authorP = model.createProperty(NS+"author");
				
		//add properties to the resource
		if(key!=null && key!=""){
			incollection.addProperty(keyP, key, XSDDatatype.XSDstring);
		}
		if(mdate!=null && mdate!=""){
			incollection.addProperty(mdateP, mdate, XSDDatatype.XSDstring);
		}
		if(authors.size()>0){
			for(int i=0;i<authors.size();i++){
				incollection.addProperty(authorP, authors.get(i), XSDDatatype.XSDstring);
			}
		}
		if(title!=null && title!=""){
			incollection.addProperty(titleP, title, XSDDatatype.XSDstring);
		}
		if(pages!=null && pages!=""){
			incollection.addProperty(pagesP, pages, XSDDatatype.XSDstring);
		}
		if(booktitle!=null && booktitle!=""){
			incollection.addProperty(booktitleP, booktitle, XSDDatatype.XSDstring);
		}
		if(year!=null && year!=""){
			incollection.addProperty(yearP, year, XSDDatatype.XSDstring);
		}
		if(ee!=null && ee!=""){
			incollection.addProperty(eeP, ee, XSDDatatype.XSDstring);
		}
		if(crossref!=null && crossref!=""){
			incollection.addProperty(crossrefP, crossref, XSDDatatype.XSDstring);
		}
		if(url!=null && url!=""){
			incollection.addProperty(urlP, url, XSDDatatype.XSDstring);
		}
		
	}
	
	public static void generateRDFFile(){
		PrintStream p;
		try {
			p = new PrintStream("rdf.rdf");
			model.write(p);
	        p.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
