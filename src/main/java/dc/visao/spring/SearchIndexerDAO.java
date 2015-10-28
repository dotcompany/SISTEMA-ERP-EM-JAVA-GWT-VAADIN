package dc.visao.spring;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Map;

import org.hibernate.metadata.ClassMetadata;
import org.hibernate.search.annotations.Indexed;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.Teste;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class SearchIndexerDAO extends AbstractCrudDAO<Serializable> {

	public static Logger logger = Logger.getLogger(SearchIndexerDAO.class);

	@Override
	public Class<Serializable> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void loadIndex() {
		logger.info("loading index");
		
		Map<String, ClassMetadata> meta = getSessionFactory().getAllClassMetadata();
		java.util.Iterator<ClassMetadata> it = meta.values().iterator();
		ArrayList<Class> classes = new ArrayList<Class>();
		while (it.hasNext()) {
			ClassMetadata data = it.next();
			Class c = data.getMappedClass();
			Annotation a = c.getAnnotation(Indexed.class);
			if (a != null && c != Teste.class) {
				classes.add(c);
			}
		}
	try {
			doIndex(classes.toArray(new Class[classes.size()]));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("call to index loading finished");
		
		try {
			doIndex(classes.toArray(new Class[classes.size()]));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("call to index loading finished");
		
	}
	private void doIndex(Class[] classes) throws InterruptedException {
		logger.info("indexing: " + classes);
		logger.info("indexing how many classes: " + classes.length);
		for (int i = 0; i < classes.length; i++) {
			logger.info("indexing:" + classes[i]);
			getFullTextSession().createIndexer(classes[i]).batchSizeToLoadObjects(33).threadsForSubsequentFetching(20).threadsToLoadObjects(5)
			// .cacheMode(CacheMode.NORMAL) // defaults to CacheMode.IGNORE
					.startAndWait();
		}
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

}
