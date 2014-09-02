package dc.servicos.dao.framework.geral;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermRangeQuery;
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
import org.hibernate.search.query.dsl.TermTermination;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Operation;
import com.vaadin.data.util.filter.SimpleStringFilter;

import dc.control.validator.ObjectValidator;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.FmMenu;
import dc.visao.spring.SecuritySessionProvider;

/** @author Wesley Jr /* Classe onde é Abstract, temos nela alguns métodos do
 *         Save, do pesquisar também, onde aqui está um pouco da lógica do
 *         pesquisar que utilizamos dentro da Tela, para pegarmos informações! */

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

	@Transactional()
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
	public List<T> fullTextSearch(String valor, int first, int pageSize, String[] sortingFields, boolean[] sortingStates, List<Filter> filters) {
		return fullTextSearch(valor, getSearchFields(), first, pageSize, sortingFields, sortingStates, null, filters);
	}

	@Transactional
	public List<T> fullTextSearch(String valor, String[] sortingFields, boolean[] states, List<Filter> filters) {
		return fullTextSearch(valor, getSearchFields(), FIRST_ROW, DEFAULT_PAGE_SIZE, sortingFields, states, null, filters);
	}

	@Transactional
	private List<T> fullTextSearch(String value, String[] searchFields, int first, int pageSize, String[] sortingFieldsStrings, boolean[] sortStates,
			FmMenu menu, List<Filter> filters) {
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

	private org.apache.lucene.search.Query createQuery(String value, String[] searchFields, FmMenu menu, List<Filter> filters,
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
	public int fullTextSearchCount(String searchValue, FmMenu menu, List<Filter> filters) {
		FullTextSession fullTextSession = getFullTextSession();

		org.apache.lucene.search.Query query = createQuery(searchValue, getSearchFields(), menu, filters, fullTextSession);

		return fullTextSession.createFullTextQuery(query, getEntityClass()).list().size();
	}

	private org.apache.lucene.search.Query createSimpleFullTextQuery(String value, String[] searchFields, FullTextSession fullTextSession,
			List<Filter> filters) {
		BooleanQuery booleanQuery = createFieldQueryByValue(value, searchFields);

		org.apache.lucene.search.Query filtersQuery = generateFiltersQuery(filters);

		if (filtersQuery != null) {
			booleanQuery.add(filtersQuery, Occur.MUST);
		}

		return booleanQuery;
	}

	private BooleanQuery createFieldQueryByValue(String value, String... searchFields) {
		BooleanQuery booleanQuery = new BooleanQuery();

		if (dc.control.validator.ObjectValidator.validateString(value)) {
			QueryBuilder qb = getFullTextSession().getSearchFactory().buildQueryBuilder().forEntity(getEntityClass()).get();
			org.apache.lucene.search.Query query = qb.keyword().fuzzy().onFields(searchFields).matching(value).createQuery();

			booleanQuery.add(query, Occur.MUST);
		}

		return booleanQuery;
	}

	private org.apache.lucene.search.Query createMultiEmpresaQuery(String value, String[] searchFields, FmMenu menu, List<Filter> filters) {
		FullTextSession fullTextSession = getFullTextSession();
		org.apache.lucene.search.BooleanQuery booleanQuery = new BooleanQuery();

		org.apache.lucene.search.Query multiEmpresaQuery = buildMultiEmpresaQuery(value, searchFields, fullTextSession, menu);
		org.apache.lucene.search.Query filterQuery = generateFiltersQuery(filters);

		booleanQuery.add(multiEmpresaQuery, Occur.MUST);

		if (filterQuery != null) {
			booleanQuery.add(filterQuery, Occur.MUST);
		}

		return booleanQuery;
	}

	private org.apache.lucene.search.Query generateFiltersQuery(Collection<Filter> collection) {
		BooleanQuery booleanQuery = null;

		if (collection != null && collection.size() > 0) {
			booleanQuery = new BooleanQuery();

			for (Filter filter : collection) {
				org.apache.lucene.search.Query query = null;

				if (filter instanceof And) {
					And castedFilter = ((And) filter);
					query = generateFiltersQuery(castedFilter.getFilters());

					if (query != null) {
						booleanQuery.add(query, Occur.MUST);
					}
				}

				try {
					if (filter instanceof Compare) {
						Compare castedFilter = ((Compare) filter);
						Object property = castedFilter.getPropertyId();
						Operation operation = castedFilter.getOperation();

						if (operation.equals(Compare.Operation.EQUAL)) {// EQUAL,
																		// GREATER,
																		// NumericRangeQuery
																		// LESS,
																		// GREATER_OR_EQUAL,
																		// LESS_OR_EQUAL
							// query = createEqualQuery(castedFilter.getValue(),
							// property);
							query = createGreaterLessQuery(castedFilter.getValue(), castedFilter.getValue(), property);
						} else if (operation.equals(Compare.Operation.GREATER_OR_EQUAL)) {
							query = createGreaterLessQuery(castedFilter.getValue(), null, property);
						} else if (operation.equals(Compare.Operation.LESS_OR_EQUAL)) {
							query = createGreaterLessQuery(null, castedFilter.getValue(), property);
						} else if (operation.equals(Compare.Operation.GREATER)) {
							query = createGreaterLessQuery(castedFilter.getValue(), null, property);
						} else if (operation.equals(Compare.Operation.LESS)) {
							query = createGreaterLessQuery(null, castedFilter.getValue(), property);
						}
					} else if (filter instanceof Between) {
						Between castedFilter = ((Between) filter);
						Object property = castedFilter.getPropertyId();

						query = createGreaterLessQuery(castedFilter.getStartValue(), castedFilter.getEndValue(), property);
					} else if (filter instanceof SimpleStringFilter) {
						String property = ((SimpleStringFilter) filter).getPropertyId().toString();
						String search = ((SimpleStringFilter) filter).getFilterString();

						if (dc.control.validator.ObjectValidator.validateString(search)) {
							query = createFieldQueryByValue(search, property);
						}
					}

					if (query != null) {
						booleanQuery.add(query, Occur.MUST);
					}
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return booleanQuery;
	}

	private org.apache.lucene.search.Query createDateQuery(Object startValue, Object endValue, Object property) {
		org.apache.lucene.search.Query query;
		// 20131001030000000
		String start = "*";
		String end = "*";

		if (startValue instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			start = sdf.format(startValue) + "000000000";
		}

		if (endValue instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			end = sdf.format(endValue) + "000000000";
		}

		query = new TermRangeQuery(property.toString(), start, end, true, true);

		return query;
	}

	private org.apache.lucene.search.Query createGreaterLessQuery(Object startValue, Object endValue, Object property) {
		org.apache.lucene.search.Query query;
		// 20131001030000000
		String start = "-99999999999999999";
		String end = "99999999999999999";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		if (startValue != null) {
			start = startValue.toString();
		}

		if (endValue != null) {
			end = endValue.toString();
		}

		if (startValue instanceof Date) {
			start = sdf.format(startValue) + "000000000";
		}

		if (endValue instanceof Date) {
			end = sdf.format(endValue) + "000000000";
		}

		// if (startValue instanceof Number || endValue instanceof Number) {
		// query = NumericRangeQuery.newDoubleRange(property.toString(), 8,
		// Double.parseDouble(start), Double.parseDouble(end), true, true);
		// } else {
		query = new TermRangeQuery(property.toString(), start, end, true, true);
		// }

		return query;
	}

	private org.apache.lucene.search.Query createEqualQuery(Object value, Object property) {
		FullTextSession fullTextSession = getFullTextSession();

		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(getEntityClass()).get();
		TermTermination matching = qb.keyword().onField(property.toString()).matching(value);

		return matching.createQuery();
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

		BooleanQuery booleanQuery = new BooleanQuery();

		try {
			Integer idEmpresa = SecuritySessionProvider.getUsuario().getConta().getEmpresa().getId();

			Analyzer an2 = fullTextSession.getSearchFactory().getAnalyzer("id_empresa_analyzer");
			QueryParser parser = new QueryParser(Version.LUCENE_31, "empresa.id", an2);
			luceneQueryForEmpresa = parser.parse(String.valueOf(idEmpresa));

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

		return booleanQuery;
	}

	protected abstract String[] getDefaultSearchFields();

	@Transactional
	public FullTextSession getFullTextSession() {
		return Search.getFullTextSession(getSession());
	}

	@Transactional
	public List<T> fullTextSearch(String searchValue, int arg0, int arg1, String[] sortingFields, boolean[] sortStates, FmMenu menu,
			List<Filter> filters) {
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
	public List<T> getAllPaged(Class<T> clazz, int start, int pageSize, String[] sortingFields, boolean[] states, List<Filter> filters) {
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

	@Transactional
	public FmMenu getMenu(String nomeClasse) {
		try {
			String sql = "FROM FmMenu ent WHERE (1 = 1) AND ent.controllerClass = :controller";

			Query query = getSession().createQuery(sql);
			query.setParameter("controller", nomeClasse);

			FmMenu ent = (FmMenu) query.uniqueResult();

			if (ent == null) {
				throw new Exception();
			}

			return ent;
		} catch (Exception e) {
			return new FmMenu();
		}
	}

}