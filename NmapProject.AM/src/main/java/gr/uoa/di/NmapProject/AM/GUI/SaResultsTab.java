package gr.uoa.di.NmapProject.AM.GUI;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.xml.transform.TransformerException;

import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;

public class SaResultsTab {
	public static void populateSAWithResultsList(JComboBox<String> cb) {
		LinkedList<String> curList = AdminPanelDAO.geSAWithResults();
		cb.removeAllItems();
		if (curList == null)
			return;
		for (String s : curList) {
			cb.addItem(s);
		}
	}
	
	public static void populateResultTextArea(JTextPane ta, String sa, Date start, Date end, boolean saSpecific) {
		LinkedList<String> resList = AdminPanelDAO.getResultsOfSABetweenTime(sa, start.getTime(), end.getTime(), saSpecific);
		LinkedList<String> parsedList = parseResultList(resList);
		for(String s : parsedList) {
			HTMLDocument doc = (HTMLDocument)ta.getDocument();
			HTMLEditorKit editorKit = (HTMLEditorKit)ta.getEditorKit();
			//HTMLEditorKit editorKit = new HTMLEditorKit();
			try {
				editorKit.insertHTML(doc, doc.getLength(), s, 0, 0, null);
			} catch (BadLocationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static LinkedList<String> parseResultList(LinkedList<String> unparsed) {
		LinkedList<String> results = new LinkedList<String>();
		if (unparsed != null) {
			for(String s : unparsed) {
				StringWriter temp = new StringWriter();
				javax.xml.transform.TransformerFactory tFactory = 
		                  javax.xml.transform.TransformerFactory.newInstance();
				try {
					javax.xml.transform.Transformer transformer = tFactory.newTransformer
					        (new javax.xml.transform.stream.StreamSource("/usr/share/nmap/nmap.xsl"));
//					(new javax.xml.transform.stream.StreamSource(new URL("http://nmap.org/svn/docs/nmap.xsl").openStream()));
					transformer.transform
				    (new javax.xml.transform.stream.StreamSource(new StringReader(s)), 
				     new javax.xml.transform.stream.StreamResult(temp));
				} catch (TransformerException e) {
					e.printStackTrace();
				}
				String temps = temp.toString();
				results.add(temps);
			}
		}
		return results;
	}
}
