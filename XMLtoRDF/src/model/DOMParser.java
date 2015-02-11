package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParser {

	public static void parser() throws Exception{
		//Get the DOM Builder Factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		
		//Get the DOM Builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//Load and Parse the XML document
		//document contains the complete XML as a Tree
		Document document = builder.parse(ClassLoader.getSystemResourceAsStream("dblp2.xml"));
		
		ArrayList<Publication> pubList = new ArrayList<>();
		
		//Iterating through the nodes and extracting the data.
		NodeList nodeList = document.getDocumentElement().getChildNodes(); //nodeList=incollection
		
		for (int i = 0; i < nodeList.getLength(); i++){
			
			//We have encountered an publication tag, such as <www>, <incollection>.
			Node node = nodeList.item(i); //node = elements in incollection
			if (node instanceof Element) {
				Publication publication = new Publication();
				publication.setMdate(node.getAttributes().getNamedItem("mdate").getNodeValue()) ;
				publication.setKey(node.getAttributes().getNamedItem("key").getNodeValue());
				NodeList childNodes = node.getChildNodes();
				
				for (int j = 0; j < childNodes.getLength(); j++){
					Node cNode = childNodes.item(j);
					//Identifying the child tag of publication encountered.
					if (cNode instanceof Element){
						String content = cNode.getLastChild().getTextContent().trim();
						switch (cNode.getNodeName()){
						case "author":
							publication.addAuthor(content);
							break;
						case "editor":
							publication.addAuthor(content);
							break;
						case "title":
							publication.setTitle(content);
							break;
						case "pages":
							publication.setPages(content);
							break;
						case "year":
							publication.setYear(content);
							break;
						case "booktitle":
							publication.setBooktitle(content);
							break;
						case "ee":
							publication.setEe(content);
							break;
						case "crossref":
							publication.setCrossref(content);
						case "url":
							publication.setUrl(content);	
						}
					}
				}
				convertToRDF.addResource(publication, publication.getTitle());
				pubList.add(publication);
			}
		}
		//System.out.println(pubList.size());
		DB.addPublicationToDB(pubList);
		convertToRDF.generateRDFFile();
		
	}
	
	public static void main(String[] args) throws Exception{
		parser();
		
		
	}
}
