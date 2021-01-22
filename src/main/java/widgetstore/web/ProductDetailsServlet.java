package widgetstore.web;

import desserts.*;
import ecommerce.Product;
import hibernate.HibernateUtils;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductDetailsServlet extends HttpServlet {

    GenericDAO<DrinkDTO> drinkDAO;

    public ProductDetailsServlet() {
        drinkDAO = new DrinkDAOImpl();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1> Enter product ID for detail inquiry: </h1>");
        out.println("<form action='' method='POST'>");
        out.println("<label>Enter Product ID: <input type='text' name='product-id'></input></label>");
        out.println("<input type='submit'>  Get Details </input>");
        out.println("</form>");
    }

    public void doPost(HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("product-id");
        PrintWriter out = response.getWriter();

        Session session = HibernateUtils.buildSessionFactory().openSession();
        Product productEntity = session.get(
                Product.class,
                Long.parseLong(productId)
        );
        if (productEntity != null) {
        	
            out.println("\n The detail for the product you are looking: \n Found product: " + productEntity.getName() + " with price: " + productEntity.getPrice());
        } else {
            out.println("Sorry, No product found for id: " + productId);
        }
    }

}
