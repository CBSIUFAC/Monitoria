package util;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;

import com.mysql.jdbc.Connection;

public class HibernateUtil {
	//	Variaveis estaticas para serem usadas na configuracao
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	public static java.sql.Connection connectionOk;
//	Bloco Estatico carregado pelo Classloader do java
	static{
//		Bloco try
		try{
//			Objeto de configuracao
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
//			Gerenciamento de servicos do Hibernate
			StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            sb.applySettings(configuration.getProperties());
            StandardServiceRegistry standardServiceRegistry = sb.build();                   
//			Sessao
            sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);              
		}
		catch (HibernateException he){
			System.err.println("Erro ao criar a sessão: " + he);
			throw new ExceptionInInitializerError(he);
		}
	}
//	Retorna a sessao para ser utilizada pelo programa
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void getConnection() {

		
		Session session = getSessionFactory().openSession();
		session.doWork(new Work() {
			@Override
			public void execute(java.sql.Connection connection)
					throws SQLException {
				connectionOk = connection;
			}
			
		});
		
	}

}