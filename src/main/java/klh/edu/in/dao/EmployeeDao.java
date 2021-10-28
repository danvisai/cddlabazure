package klh.edu.in.dao;


import javax.transaction.Transaction;

import com.mysql.cj.Session;

import klh.edu.in.entity.Employee;

public class EmployeeDao {
	
	Employee employee = new Employee(0, "Madhukar","Gandra","madhusw511@gmail.com");
    Employee employee1 = new Employee(2, "Vihan","Gandra","vihaan@gmail.com");
    Transaction transaction = null;
    try (Session session1 = HibernateUtil.getSessionFactory().openSession()) {
        // start a transaction
        transaction = session1.beginTransaction();
        // save the student objects
        session1.save(employee);
        session1.save(employee1);
        // commit transaction

        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
           transaction.rollback();
        }
        e.printStackTrace();
    }

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        java.util.List<Employee> employees = session1.createQuery("from student", Employee.class).list();
        employee.forEach(s -> System.out.println(s.getFirstName()));
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}
}



