import com.irufus.norae.entities.SongsEntity;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Ishmael on 10/23/2016.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Properties prop = new Properties();
            System.out.println("Prop file: " + Main.class.getClassLoader().getResourceAsStream("db.properties"));
            prop.load(new FileInputStream(Main.class.getResource("db.properties").getPath()));
            ourSessionFactory = new Configuration().
                    configure("hibernate.cfg.xml")
                    .addProperties(prop)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            //final Query query = session.createQuery("from " + entityName);
            List songs = session.createQuery("from SongsEntity")
                .list();
            for(Object o : songs) {
                SongsEntity song = (SongsEntity) o;
                System.out.println("  " + song.getSongTitle());
            }

        } finally {
            session.close();
        }
    }
}
