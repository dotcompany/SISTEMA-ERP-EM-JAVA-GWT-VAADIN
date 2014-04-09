package dc.servicos.dao.framework.geral;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;

import dc.control.validator.ObjectValidator;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.FmMenu;
import dc.visao.spring.SecuritySessionProvider;

/**
 * 
 * @author Wesley Jr /* Classe onde é Abstract, temos nela alguns métodos do
 *         Save, do pesquisar também, onde aqui está um pouco da lógica do
 *         pesquisar que utilizamos dentro da Tela, para pegarmos
 * 
 *         informações!
 * 
 */

@SuppressWarnings({ "unchecked" })
public abstract class AbstractCrudDAO<T> {

	private static final int FIRST_ROW = 1;
	private static final int DEFAULT_PAGE_SIZE = 50;

	@Autowired
	protected SessionFactory sessionFactory;

	private String comboValue;
	private String comboCode;
	private String[] defaultSearchFields;

	public static Logger logger = Logger.getLogger(AbstractCrudDAO.class);

	@PostConstruct
	public void init() {
		configureDefaultComboFields();
		Class<T> entityClass = getEntityClass();

		if (entityClass != null) {
			configureComboFields(entityClass);
		}
	}

	private void configureDefaultComboFields() {

		String[] defaultSearchFields = getDefaultSearchFields();

		if (defaultSearchFields != null && defaultSearchFields.length != 0) {
			comboValue = defaultSearchFields[0];
			comboCode = defaultSearchFields[0];
		}
	}

	private void configureComboFields(Class<T> entityClass) {
		logger.info("combo config for class: " + entityClass);
		java.lang.reflect.Field[] entityFields = entityClass.getDeclaredFields();
		logger.info("fields.." + entityFields);
		logger.info("fields..length: " + entityFields.length);

		for (Field f : entityFields) {
			logger.info("Field: " + f);

			if (f.getAnnotation(ComboCode.class) != null) {
				comboCode = f.getName();
			}

			if (f.getAnnotation(ComboValue.class) != null) {
				comboValue = f.getName();
			}
		}

		logger.info("combo config code: " + comboCode);
		logger.info("combo config value: " + comboValue);
	}

	public abstract Class<T> getEntityClass();

	@Transactional
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Transactional
	public T find(Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(getEntityClass(), id);
	}

	@Transactional
	public void save(T obj) {
		if (obj instanceof AbstractMultiEmpresaModel) {
			@SuppressWarnings("rawtypes")
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
		for (Serializable s : objs) {
			sessionFactory.getCurrentSession().delete(s);
		}
	}

	@Transactional
	public <E> void saveOrUpdate(final E o) {
		if (o instanceof AbstractMultiEmpresaModel) {
			@SuppressWarnings("rawtypes")
			AbstractMultiEmpresaModel a = (AbstractMultiEmpresaModel) o;

			if (a.getEmpresa() == null) {
				a.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			}
		}

		sessionFactory.getCurrentSession().saveOrUpdate(o);
	}

	@Transactional
	public <E> List<E> getAll(final Class<E> type) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(type);

		return crit.list();
	}

	@Transactional
	public List<T> getAllForCombo(final Class<T> type, int idEmpresa, FmMenu menu) {

		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(type);

		if (isConsultaMultiEmpresa(getEntityClass(), menu)) {
			crit.add(Restrictions.eq("empresa.id", idEmpresa));
		}

		String order = comboValue.contains(".") ? comboValue.split("\\.")[0] : comboValue;

		return crit.addOrder(Order.asc(order)).list();
	}

	@Transactional
	public List<T> getAllForComboSelect(final Class<T> type, int idEmpresa, FmMenu menu, final String typeSelected, Integer idSelected) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(type);

		if (isConsultaMultiEmpresa(getEntityClass(), menu)) {
			crit.add(Restrictions.eq("empresa.id", idEmpresa));
		}
		crit.add(Restrictions.eq(typeSelected.toLowerCase() + ".id", idSelected));
		String order = comboValue.contains(".") ? comboValue.split("\\.")[0] : comboValue;

		return crit.addOrder(Order.asc(order)).list();
	}

	public boolean isMultiEmpresa(@SuppressWarnings("rawtypes") Class c) {
		return AbstractMultiEmpresaModel.class.isAssignableFrom(c);
	}

	public boolean isConsultaMultiEmpresa(@SuppressWarnings("rawtypes") Class c, FmMenu ent) {
		if (ent != null) {
			return isMultiEmpresa(c) && ent.isConsultaMultiempresa();
		} else {
			return isMultiEmpresa(c);
		}
	}

	@Transactional
	public List<T> fullTextSearch(String valor) {
		return fullTextSearch(valor, getSearchFields(), FIRST_ROW, DEFAULT_PAGE_SIZE, new String[0], new boolean[0], null, null);
	}

	@Transactional
	public List<T> fullTextSearch(String valor, int first, int pageSize, String[] sortingFields, boolean[] sortingStates, Map<Object, String> filters) {
		return fullTextSearch(valor, getSearchFields(), first, pageSize, sortingFields, sortingStates, null, filters);
	}

	@Transactional
	public List<T> fullTextSearch(String valor, String[] sortingFields, boolean[] states, Map<Object, String> filters) {

		return fullTextSearch(valor, getSearchFields(), FIRST_ROW, DEFAULT_PAGE_SIZE, sortingFields, states, null, filters);
	}

	@Transactional
	private List<T> fullTextSearch(String value, String[] searchFields, int first, int pageSize, String[] sortingFieldsStrings, boolean[] sortStates,
			FmMenu menu, Map<Object, String> filters) {
		FullTextSession fullTextSession = getFullTextSession();

		SortField[] sortingFields = new SortField[sortingFieldsStrings.length];

		for (int i = 0; i < sortingFieldsStrings.length; i++) {
			sortingFields[i] = new SortField(sortingFieldsStrings[i], SortField.STRING, sortStates[i]);
		}

		org.apache.lucene.search.Query query = createQuery(value, searchFields, menu, filters, fullTextSession);

		FullTextQuery q = fullTextSession.createFullTextQuery(query, getEntityClass());

		configureSorting(sortingFields, q);
		// q.setFirstResult(first);
		// q.setMaxResults(pageSize);
		List<T> resultSet = q.list();

		logger.info("found for: " + value);
		logger.info("found: " + resultSet.size() + " entities...");

		return resultSet;
	}

	private org.apache.lucene.search.Query createQuery(String value, String[] searchFields, FmMenu menu, Map<Object, String> filters,
			FullTextSession fullTextSession) {
		org.apache.lucene.search.Query query;
		if (isConsultaMultiEmpresa(getEntityClass(), menu)) {
			query = createMultiEmpresaQuery(value, searchFields, menu, filters);

		} else {
			query = createSimpleFullTextQuery(value, searchFields, fullTextSession, filters);

		}
		return query;
	}

	@Transactional
	public int fullTextSearchCount(String searchValue, FmMenu menu, Map<Object, String> filters) {
		FullTextSession fullTextSession = getFullTextSession();

		org.apache.lucene.search.Query query = createQuery(searchValue, getSearchFields(), menu, filters, fullTextSession);

		return fullTextSession.createFullTextQuery(query, getEntityClass()).list().size();
	}

	private org.apache.lucene.search.Query createSimpleFullTextQuery(String value, String[] searchFields, FullTextSession fullTextSession,
			Map<Object, String> filters) {

		org.apache.lucene.search.Query booleanQuery = new BooleanQuery();

		if (dc.control.validator.ObjectValidator.validateString(value)) {
			QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(getEntityClass()).get();
			org.apache.lucene.search.Query query = qb.keyword().fuzzy().onFields(searchFields).matching(value).createQuery();
			org.apache.lucene.search.Query[] queries = new org.apache.lucene.search.Query[] { query };

			booleanQuery = booleanQuery.combine(queries);

		}

		booleanQuery = generateFiltersQuery(filters, booleanQuery);

		return booleanQuery;
	}

	private org.apache.lucene.search.Query createMultiEmpresaQuery(String value, String[] searchFields, FmMenu menu, Map<Object, String> filters) {

		FullTextSession fullTextSession = getFullTextSession();
		org.apache.lucene.search.Query booleanQuery = new BooleanQuery();

		org.apache.lucene.search.Query multiEmpresaQuery = buildMultiEmpresaQuery(value, searchFields, fullTextSession, menu);
		org.apache.lucene.search.Query[] empresaQuery = new org.apache.lucene.search.Query[] { multiEmpresaQuery };

		booleanQuery = booleanQuery.combine(empresaQuery);

		booleanQuery = generateFiltersQuery(filters, booleanQuery);

		return booleanQuery;
	}

	private org.apache.lucene.search.Query generateFiltersQuery(Map<Object, String> filters, org.apache.lucene.search.Query booleanQuery) {
		if (filters != null && filters.size() > 0) {
			org.apache.lucene.search.Query[] queriesArray = new org.apache.lucene.search.Query[filters.size()];

			int i = 0;
			for (Object property : filters.keySet()) {
				BooleanQuery boolQuery = new BooleanQuery();
				String search = filters.get(property);
				String[] words = search.split(" ");
				StringBuffer sb = new StringBuffer();
				for (String word : words) {
					sb.append(word + "*");
				}

				boolQuery.add(new FuzzyQuery(new Term(property.toString(), sb.substring(0, sb.length() - 1))), Occur.MUST);
				queriesArray[i] = boolQuery;
				i++;
			}

			booleanQuery = booleanQuery.combine(queriesArray);
		}
		return booleanQuery;
	}

	private void configureSorting(SortField[] sortingFields, FullTextQuery q) {
		if (sortingFields != null && sortingFields.length > 0) {
			org.apache.lucene.search.Sort sort = new Sort(sortingFields);
			q.setSort(sort);
		}
	}

	private org.apache.lucene.search.Query buildMultiEmpresaQuery(String value, String[] searchFields, FullTextSession fullTextSession, FmMenu menu) {

		org.apache.lucene.search.Query luceneQueryForEmpresa = new org.apache.lucene.search.Query() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String toString(String arg0) {

				return null;
			}
		};

		try {
			Integer idEmpresa = SecuritySessionProvider.getUsuario().getConta().getEmpresa().getId();

			Analyzer an2 = fullTextSession.getSearchFactory().getAnalyzer("id_empresa_analyzer");
			QueryParser parser = new QueryParser(Version.LUCENE_31, "empresa.id", an2);
			luceneQueryForEmpresa = parser.parse(String.valueOf(idEmpresa));

			BooleanQuery booleanQuery = new BooleanQuery();
			if (ObjectValidator.validateString(value)) {
				value = value.trim();

				QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(getEntityClass()).get();
				org.apache.lucene.search.Query query = qb.keyword().fuzzy().onFields(searchFields).matching(value).createQuery();
				booleanQuery.add(query, Occur.MUST);

			}

			booleanQuery.add(luceneQueryForEmpresa, Occur.MUST);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return luceneQueryForEmpresa;
	}

	protected abstract String[] getDefaultSearchFields();

	@Transactional
	public FullTextSession getFullTextSession() {
		return Search.getFullTextSession(getSession());
	}

	@Transactional
	public List<T> fullTextSearch(String searchValue, int arg0, int arg1, String[] sortingFields, boolean[] sortStates, FmMenu menu,
			Map<Object, String> filters) {
		return fullTextSearch(searchValue, getSearchFields(), arg0, arg1, sortingFields, sortStates, menu, filters);
	}

	public String[] getSearchFields() {
		if (defaultSearchFields == null || defaultSearchFields.length > 0) {
			this.defaultSearchFields = getDefaultSearchFields();

			if (defaultSearchFields == null || defaultSearchFields.length == 0) {
				ClassMetadata classMetadata = sessionFactory.getClassMetadata(getEntityClass());
				String[] allProps = classMetadata.getPropertyNames();
				ArrayList<String> searchFields = new ArrayList<String>();

				for (int i = 0; i < allProps.length; i++) {
					Type t = classMetadata.getPropertyType(allProps[i]);
					if (t.getReturnedClass() == java.lang.String.class) {
						searchFields.add(allProps[i]);
					}
				}

				defaultSearchFields = searchFields.toArray(new String[searchFields.size()]);
			}
		}

		return defaultSearchFields;
	}

	@Transactional
	public int count(Class<T> c) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(c);
		List<T> l = criteria.setProjection(Projections.rowCount()).list();

		return Integer.valueOf(l.get(0).toString());
	}

	@Transactional
	public List<T> getAllPaged(Class<T> clazz, int start, int pageSize, String[] sortingFields, boolean[] states, Map<Object, String> filters) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		configureHQLOrder(sortingFields, states, criteria);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageSize);
		List<T> result = criteria.list();

		return result;
	}

	private void configureHQLOrder(String[] sortingFields, boolean[] states, Criteria criteria) {
		for (int i = 0; i < sortingFields.length; i++) {
			if (states[i]) {
				criteria.addOrder(Order.desc(sortingFields[i]).ignoreCase());
			} else {
				criteria.addOrder(Order.asc(sortingFields[i]).ignoreCase());
			}
		}
	}

	private String getHQLOrderByClause(String[] sortingFields, boolean[] states) {
		if (sortingFields.length > 0) {
			String clause = " ORDER BY ";

			for (int i = 0; i < sortingFields.length; i++) {
				if (states[i]) {
					clause = clause + " " + sortingFields[i] + " DESC ,";
				} else {
					clause = clause + " " + sortingFields[i] + " ASC ,";
				}
			}

			return clause.substring(0, clause.length() - 1);
		} else {
			return "";
		}
	}

	@Transactional
	public List<Serializable> getAllPagedByEmpresa(Class<T> pojoClass, Integer idEmpresa, int start, int pageSize, String[] sortingFields,
			boolean[] states) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from " + pojoClass.getName() + " where empresa.id = :id_empresa " + getHQLOrderByClause(sortingFields, states));
		query.setParameter("id_empresa", idEmpresa);
		query.setFirstResult(start);

		query.setMaxResults(pageSize);

		List<Serializable> result = query.list();

		return result;
	}

	@Transactional
	public int countByEmpresa(Class<T> c, Integer idEmpresa) {
		List<T> l = sessionFactory.getCurrentSession().createCriteria(c).add(Restrictions.eq("empresa.id", idEmpresa))
				.setProjection(Projections.rowCount()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return Integer.valueOf(l.get(0).toString());
	}

	@Transactional
	public List<T> comboTextSearch(String value, FmMenu menu) {
		String[] fields = { comboCode, comboValue };

		FullTextSession fullTextSession = getFullTextSession();
		List<T> resultSet = new ArrayList<T>();
		org.apache.lucene.search.Query booleanQuery = createQuery(value, fields, menu, null, fullTextSession);
		resultSet = fullTextSession.createFullTextQuery(booleanQuery, getEntityClass()).setFirstResult(0).list();

		return resultSet;
	}
}