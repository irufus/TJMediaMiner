package com.irufus.norae;

import com.irufus.norae.entities.SongsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;



public class TJMediaMiner {
	private static SessionFactory sessionFactory;
	public static void main(String[] args) {
		try {
			sessionFactory = new Configuration()
					.configure("hibernate.cfg.xml")
					.buildSessionFactory();
			Session session = sessionFactory.openSession();
			SimpleDateFormat month = new SimpleDateFormat("MM");
			SimpleDateFormat year = new SimpleDateFormat("YYYY");
			Calendar cal = new GregorianCalendar(2007, 0, 1); //you can go as far back as 2007
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MONTH, 1);

			String url = "http://www.tjmedia.co.kr/tjsong/song_monthNew.asp?YY=%s&MM=%s";
			try {
				//PrintWriter pw = new PrintWriter("songs.dat", "ISO-8859-1"); //move into database
				while (cal.compareTo(now) < 0) {
				//	session.beginTransaction();
					System.out.println("Getting info for: " + year.format(cal.getTime()) + "-" + month.format(cal.getTime()));
					String formatedURL = String.format(url, new String[]{year.format(cal.getTime()), month.format(cal.getTime())});
					System.out.println(formatedURL);
					Document doc = Jsoup.parse(new URL(formatedURL).openStream(), "ISO-8859-1", url);
					Elements table = doc.select("table.board_type1");
					session.beginTransaction();
					for (Element tr : table.select("tr")) {
						// 0 - id, 1 - Song title, 2 - Singer, 3 - Lyricsist, 4 - Composer
						Element[] data = tr.select("td:not(td:empty)").toArray(new Element[tr.select("td:not(td:empty)").size()]);
						if (data.length > 4){
							System.out.println("[0] - " + data[0].text() + " [1] - "
									+ converToUTF8(data[1].text()));
							SongsEntity entity = new SongsEntity();
							entity.setSongId(Integer.parseInt(data[0].text()));
							entity.setSongTitle(converToUTF8(data[1].text()));
							entity.setSongSinger(converToUTF8(data[2].text()));
							entity.setSongLyricist(converToUTF8(data[3].text()));
							entity.setSongComposer(converToUTF8(data[4].text()));
							entity.setSonglistCo(1);
							session.save(entity);
						}
					}
					session.getTransaction().commit();
					cal.add(Calendar.MONTH, 1);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		System.exit(0);
			
	}
	private static String converToUTF8(String data){
		String result = "";
		try {
			result = new String(data.getBytes("ISO-8859-1"), "UTF-8");
		}
		catch(UnsupportedEncodingException un){
			un.printStackTrace();
		}
		return result;
	}

}
