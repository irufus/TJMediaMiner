package home.irufus.TJMediaMiner;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class TJMediaMiner {

	public static void main(String[] args) {
		String url = "http://www.tjmedia.co.kr/tjsong/song_monthNew.asp?YY=2014&MM=01";
		try 
		{
			Document doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
			Elements table = doc.select("table");
			Iterator<Element> ite = table.select("tr").iterator();
			PrintWriter pw = new PrintWriter("test.dat", "ISO-8859-1");
			while(ite.hasNext())
			{
//				System.out.println(ite.next().text());
				pw.println(ite.next().text());				
			}
			pw.close();
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
