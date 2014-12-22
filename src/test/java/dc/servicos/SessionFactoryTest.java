package dc.servicos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.entidade.financeiro.BancoEntity;
import dc.servicos.dao.financeiro.BancoDAO;

public class SessionFactoryTest {

	public static void main(String[] args) throws Exception {
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("src/test/java/applicationContext.xml");
//		LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean) ctx.getBean("&sessionFactory");
//		Configuration cfg = lsfb.getConfiguration();
//		SchemaUpdate su = new SchemaUpdate(cfg);
//		su.execute(true, false);
		
		SessionFactory sf = ctx.getBean(SessionFactory.class);
		Session s = sf.openSession();
		s.createQuery("from Usuario").list();
		
		
	}
}
