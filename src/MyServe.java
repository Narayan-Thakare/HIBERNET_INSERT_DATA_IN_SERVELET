import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Model.Student;

@WebServlet("/MyServe")
public class MyServe extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String address = request.getParameter("address");

        Student student = new Student(name, address);

        Configuration cfg = new Configuration();
        cfg.configure("hiber.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
       
            
            Transaction t = session.beginTransaction();
			
			session.save(student);

			t.commit();
			session.close();
			
            out.print("Data inserted");

    }
}
