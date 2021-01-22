
import desserts.*;
import ecommerce.Product;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OurApplication {

    public static void main(String[] args) {
        try {
            hibernateSession = HibernateUtils
                    .buildSessionFactory()
                    .openSession();
            hibernateSession.beginTransaction();

            /* Insert some product */
            for (int i = 0; i <= 10; i++) {
                Product product = new Product();
                product.setName("iphone" + i);
                product.setPrice(10F * i);
                hibernateSession.save(product);
            }

            hibernateSession.getTransaction().commit();
        } catch(Exception sqlException) {
            if (null != hibernateSession.getTransaction()) {
                hibernateSession.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (hibernateSession != null) {
                hibernateSession.close();
            }
        }
    }

    static Session hibernateSession;


    static int counter = 0;
}