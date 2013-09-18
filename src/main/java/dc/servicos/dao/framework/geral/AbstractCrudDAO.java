package dc.servicos.dao.framework.geral;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.dev.util.collect.HashSet;
import com.sun.istack.logging.Logger;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.SecuritySessionProvider;

/**
*
* @author Wesley Jr
/*
 *Classe onde é Abstract, temos nela alguns métodos do Save, do pesquisar também, onde
 *aqui está um pouco da lógica do pesquisar que utilizamos dentro da Tela, para pegarmos

 *informações! 
 * 
 
*/

@SuppressWarnings({ "unchecked" })
public abstract class AbstractCrudDAO<T> {
	
	private static final int FIRST_ROW = 1;
	private static final int DEFAULT_PAGE_SIZE = 50;

	@Autowired
	protected SessionFactory sessionFactory;
	
	private String comboValue; 
	private String comboCode ;
	
	public static Logger logger = Logger.getLogger(AbstractCrudDAO.class);
	@PostConstruct
	public void init(){
		configureDefaultComboFields();
		Class entityClass = getEntityClass();
		if(entityClass != null){
			configureComboFields(entityClass);	
		}
		
	}

	private void configureDefaultComboFields() {
		String[] defaultSearchFields = getDefaultSearchFields();
		if(defaultSearchFields != null && defaultSearchFields.length != 0 ){
			comboValue = defaultSearchFields[0];
			comboCode = defaultSearchFields[0];	
		}
	}

	private void configureComboFields(Class entityClass) {
		logger.info("combo config for class: " + entityClass);
		java.lang.reflect.Field[] entityFields = entityClass.getDeclaredFields();
		logger.info("fields.." + entityFields);
		logger.info("fields..length: " + entityFields.length);
		for(Field f : entityFields){
			logger.info("Field: " + f);
			if (f.getAnnotation(ComboCode.class) != null){
				comboCode = f.getName();
			}
			if (f.getAnnotation(ComboValue.class) != null){
				comboValue = f.getName();
			}
		}
		logger.info("combo config code: " + comboCode);
		logger.info("combo config value: " + comboValue);
	}

	protected abstract Class<T> getEntityClass();

	@Transactional
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}

	@Transactional
	public T find(Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(getEntityClass(), id);
	}

	@Transactional
	public void save(T obj){
		if (obj instanceof AbstractMultiEmpresaModel) {
			AbstractMultiEmpresaModel a = (AbstractMultiEmpresaModel) obj;
			a.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
		}
		sessionFactory.getCurrentSession().save(obj);
	}

	@Transactional
	public void delete(T obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}
	
	@Transactional
	public void deleteAllByIds(List<Serializable> ids) {

			String tableName = getEntityClass().getSimpleName();
			Query q = sessionFactory.getCurrentSession().createQuery("delete from " + tableName + " where id in (:idList) ");
			q.setParameterList("idList", ids.toArray());
			q.executeUpdate();	
		
		
	}
	
	
	@Transactional
	public void deleteAll(List<Serializable> objs) {
		for(Serializable s : objs){
			sessionFactory.getCurrentSession().delete(s);
		}
	}
	
	@Transactional
	public <T> void saveOrUpdate(final T o) {
		if (o instanceof AbstractMultiEmpresaModel) {
			AbstractMultiEmpresaModel a = (AbstractMultiEmpresaModel) o;
			a.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
		}
		sessionFactory.getCurrentSession().saveOrUpdate(o);
	}
		    
	@Transactional
	public <T> List<T> getAll(final Class<T> type) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(type);
		return crit.list();
	}
	
	@Transactional
	public List<T> fullTextSearch(String valor) {
		return fullTextSearch(valor, getSearchFields(),FIRST_ROW,DEFAULT_PAGE_SIZE);
	}
	
	
	@Transactional
	public List<T> fullTextSearch(String value, String[] searchFields,int first,int pageSize) {
		FullTextSession fullTextSession = getFullTextSession();
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(getEntityClass()).get();
			org.apache.lucene.search.Query query = qb
			  .keyword().fuzzy()
			  .onFields(searchFields)
			  .matching(value)
			  .createQuery();

			// wrap Lucene query in a org.hibernate.Query
			return fullTextSession.createFullTextQuery(query, getEntityClass())
					.setFirstResult(first)
					.setMaxResults(pageSize)
					.list();
	}
	
	
	@Transactional
	public List<T> comboTextSearch(String value, String[] searchFields) {
		List<T> resultSet = new ArrayList<T>();
		value = value.trim();
		FullTextSession fullTextSession = getFullTextSession();
	    Analyzer an = fullTextSession.getSearchFactory().getAnalyzer("dc_combo_analyzer");
	    String[] fields = {comboCode,comboValue};
	    BooleanQuery booleanQuery = new BooleanQuery();
		
	    MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_31,fields, an); 
	    try {
	    	org.apache.lucene.search.Query luceneQuery = parser.parse(value);
	    	org.apache.lucene.search.Query luceneQuery2 = parser.parse(value+"*");
	    	booleanQuery.add(luceneQuery, Occur.SHOULD);
			booleanQuery.add(luceneQuery2, Occur.SHOULD);	
			resultSet= fullTextSession.createFullTextQuery(booleanQuery, getEntityClass())
					.setMaxResults(DEFAULT_PAGE_SIZE)
					.list();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    return resultSet;
			
			
	}
	

	protected abstract String[] getDefaultSearchFields();

	@Transactional
	public FullTextSession getFullTextSession() {
		return Search.getFullTextSession(getSession());
	}

	@Transactional
	public List fullTextSearch(String searchValue, int arg0, int arg1) {
		return fullTextSearch(searchValue, getSearchFields(),arg0, arg1);
	}

	@Transactional
	public int fullTextSearchCount(String searchValue) {
		FullTextSession fullTextSession = getFullTextSession();
		
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(getEntityClass()).get();
		
			org.apache.lucene.search.Query query = qb
			  .keyword().fuzzy()
			  .onFields(getSearchFields())
			  .matching(searchValue)
			  .createQuery();

			return fullTextSession.createFullTextQuery(query, getEntityClass()).getResultSize();
	}
	
	public String[] getSearchFields(){
		String[] defaultSearchFields = getDefaultSearchFields();
		if(defaultSearchFields == null || defaultSearchFields.length == 0 ){
			 ClassMetadata classMetadata = sessionFactory.getClassMetadata(getEntityClass());
			 String[] allProps = classMetadata.getPropertyNames();
			 ArrayList<String> searchFields = new ArrayList<String>();
			 for (int i = 0; i < allProps.length; i++) {
				 Type t = classMetadata.getPropertyType(allProps[i]);
				 if (t.getReturnedClass() == java.lang.String.class){
					 searchFields.add(allProps[i]);
				 }
			}
			defaultSearchFields = searchFields.toArray(new String[searchFields.size()]); 
		}
		return defaultSearchFields;
	}

	@Transactional
	public int count(Class c) {
		// TODO Auto-generated method stub
		List l =  sessionFactory.getCurrentSession().createCriteria(c).setProjection(Projections.rowCount()).list();
		return Integer.valueOf(l.get(0).toString());
	}
	
	@Transactional
	public List getAllPaged(Class clazz, int start, int pageSize) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return result;
	}
	
	@Transactional
	public List<Serializable> getAllPagedByEmpresa(Class pojoClass,
			Integer idEmpresa, int start, int pageSize) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(pojoClass);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageSize); 
		criteria.add(Restrictions.eq("empresa.id", idEmpresa));
		List result = criteria.list();
		return result;
	}
	
	@Transactional
	public int countByEmpresa(Class c,Integer idEmpresa) {
		List l =  sessionFactory.getCurrentSession().createCriteria(c).add(Restrictions.eq("empresa.id", idEmpresa)).setProjection(Projections.rowCount()).list();
		return Integer.valueOf(l.get(0).toString());
	}

	
}
