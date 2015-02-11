package model;
import java.util.ArrayList;

public class Publication {

	String key;
	String mdate;
	ArrayList<String> authors = new ArrayList<String>();
	String title;
	String pages;
	String year;
	String booktitle;
	String ee;
	String crossref;
	String url;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public ArrayList<String> getAuthors() {
		return authors;
	}
	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getEe() {
		return ee;
	}
	public void setEe(String ee) {
		this.ee = ee;
	}
	public String getCrossref() {
		return crossref;
	}
	public void setCrossref(String crossref) {
		this.crossref = crossref;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void addAuthor(String author) {
		this.authors.add(author);
		//System.out.print(authors.size());
	}
 
	public ArrayList<String> toSQL(){
		ArrayList<String> values = new ArrayList<String>();
		if (authors.size()==0){	
		String value = String.format(
					"INSERT INTO LABDATABASE.publication VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);",
					checkNull(getKey()), checkNull(getMdate()), "NULL",
					checkNull(getTitle()), checkNull(getPages()), checkNull(getYear()),
					checkNull(getBooktitle()), checkNull(getEe()), checkNull(getCrossref()), checkNull(getUrl()));	
		values.set(0, value);
		}
		else{
		for (int j=0; j<authors.size(); j++){
			String	value = String.format(
					"INSERT INTO LABDATABASE.publication VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);",
					checkNull(getKey()), checkNull(getMdate()), checkNull(getAuthors().get(j)),
					checkNull(getTitle()), checkNull(getPages()), checkNull(getYear()),
					checkNull(getBooktitle()), checkNull(getEe()), checkNull(getCrossref()), checkNull(getUrl()));	
			values.add(j, value);
			
		}
		}
		return values;
	}
		

	
	public String checkNull(String str){
				if (str==null || str.isEmpty()) {
					return "NULL";
				} else {
					str=str.replace("'", "%");
					return String.format("'%s'", str);
				}
	}
	
}
