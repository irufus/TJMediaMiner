package com.irufus.norae;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;



public class TJMediaMiner {

	public static void main(String[] args) {
		SimpleDateFormat month = new SimpleDateFormat("MM");
		SimpleDateFormat year = new SimpleDateFormat("YYYY");
		Calendar cal = new GregorianCalendar(2016,0,1); //you can go as far back as 2007
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, 1);
		
		String url = "http://www.tjmedia.co.kr/tjsong/song_monthNew.asp?YY=%s&MM=%s";
		try 
		{
			PrintWriter pw = new PrintWriter("songs.dat", "ISO-8859-1"); //move into database
			while(cal.compareTo(now) < 0)
			{
				System.out.println("Getting info for: " + year.format(cal.getTime()) + "-" + month.format(cal.getTime()));
				String formatedURL = String.format(url, new String[] { year.format(cal.getTime()), month.format(cal.getTime()) });
				System.out.println(formatedURL);
				Document doc = Jsoup.parse(new URL(formatedURL).openStream(), "ISO-8859-1", url);
				Elements table = doc.select("table.board_type1");
				Iterator<Element> ite = table.select("tr").iterator();				
				while(ite.hasNext())
				{
					Element tr = ite.next();
					Iterator<Element> tds = tr.select("td").iterator();
					while(tds.hasNext())
					{
						String entry = tds.next().text();
						if(!tds.hasNext())
							pw.print(entry);						
						else
							pw.print(entry + ",");
					}
					pw.println();		
				}
				cal.add(Calendar.MONTH, 1);
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
