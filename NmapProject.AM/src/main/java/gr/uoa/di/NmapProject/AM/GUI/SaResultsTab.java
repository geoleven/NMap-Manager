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

/**
 * Class that handles the tabs for the preview of the S.A.s results.
 * 
 * @author George
 *
 */
public class SaResultsTab {
	/**
	 * It populated the drop down list with S.A.s which have results.
	 * 
	 * @param cb The combo box element to populate.
	 */
	public static void populateSAWithResultsList(JComboBox<String> cb) {
		LinkedList<String> curList = AdminPanelDAO.geSAWithResults();
		cb.removeAllItems();
		if (curList == null)
			return;
		for (String s : curList) {
			cb.addItem(s);
		}
	}
	
	/**
	 * It populates the result text area with the actual results after parsing them from xml.
	 * @param ta The text area to use.
	 * @param sa If showing results for only one S.A., the S.A. to show results for.
	 * @param start The starting date from which the results should be shown.
	 * @param end The ending date till which the results should be shown.
	 * @param saSpecific A boolean showing if all S.A.s' results will be show or only for one S.A..
	 */
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
