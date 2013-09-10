package dc.visao.framework.geral;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.vaadin.addons.lazyquerycontainer.AbstractBeanQuery;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import com.sun.istack.logging.Logger;

import dc.servicos.dao.framework.geral.AbstractCrudDAO;

public class DCBeanQuery extends AbstractBeanQuery<Serializable>{
	
	private Logger logger = Logger.getLogger(DCBeanQuery.class);
	
	public DCBeanQuery(QueryDefinition definition,
			Map<String, Object> queryConfiguration, Object[] sortPropertyIds,
			boolean[] sortStates) {
		super(definition, queryConfiguration, sortPropertyIds, sortStates);
	}
	
	@Override
	protected Serializable constructBean() {
		Class pojoClass = (Class) getQueryConfiguration().get("pojoClass");
		Object instance = null;
		try {
			instance = pojoClass.getConstructor().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Serializable) instance;
	}

	@Override
	protected List<Serializable> loadBeans(int arg0, int arg1) {
		logger.info("loading beans from " + arg0);
		logger.info("loading beans page size" + arg1);
		AbstractCrudDAO dao = (AbstractCrudDAO) getQueryConfiguration().get("dao");
		String searchTerm =  (String) getQueryConfiguration().get("search");
		Class pojoClass =  (Class) getQueryConfiguration().get("pojoClass");
		if(searchTerm != null && !searchTerm.trim().isEmpty()){
			return dao.fullTextSearch(searchTerm,arg0,arg1);	
		}else{
			logger.info("null or empty search term, loading all..");
			return dao.getAllPaged(pojoClass,arg0,arg1);
		}
	}

	@Override
	protected void saveBeans(List<Serializable> arg0, List<Serializable> arg1,
			List<Serializable> arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		AbstractCrudDAO dao = (AbstractCrudDAO) getQueryConfiguration().get("dao");
		String searchTerm =  (String) getQueryConfiguration().get("search");
		Class pojoClass =  (Class) getQueryConfiguration().get("pojoClass");
		int size = 0;
		if(searchTerm != null && !searchTerm.trim().isEmpty()){
			size = dao.fullTextSearchCount(searchTerm);	
		}else{
			size = dao.count(pojoClass);
		}
		
		logger.info("query result set size:" + size);
		
		return size;
		
		
	}

	

}

