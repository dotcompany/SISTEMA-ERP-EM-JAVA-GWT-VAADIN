package dc.visao.framework.geral;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import com.sun.istack.logging.Logger;

import dc.entidade.framework.FmMenu;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

public class DCBeanQueryMultiEmpresa extends AbstractDCBeanQuery {

	public DCBeanQueryMultiEmpresa(QueryDefinition definition, Map<String, Object> queryConfiguration, Object[] sortPropertyIds, boolean[] sortStates) {
		super(definition, queryConfiguration, sortPropertyIds, sortStates);
		// TODO Auto-generated constructor stub
	}

	private Logger logger = Logger.getLogger(DCBeanQueryMultiEmpresa.class);

	@Override
	protected List<Serializable> loadBeans(int arg0, int arg1) {
		logger.info("loading beans from " + arg0);
		logger.info("loading beans page size" + arg1);

		AbstractCrudDAO dao = (AbstractCrudDAO) getQueryConfiguration().get("dao");
		String searchTerm = (String) getQueryConfiguration().get("search");
		Class pojoClass = (Class) getQueryConfiguration().get("pojoClass");
		Integer idEmpresa = (Integer) getQueryConfiguration().get("id_empresa");
		FmMenu menu = (FmMenu) getQueryConfiguration().get("menu");

		if (searchTerm != null && !searchTerm.trim().isEmpty() || filters != null) {

			return dao.fullTextSearch(searchTerm, arg0, arg1, this.sortingFields, this.sortingStates, menu, filters);
		} else {
			logger.info("null or empty search term, loading all..");

			return dao.getAllPagedByEmpresa(pojoClass, idEmpresa, arg0, arg1, this.sortingFields, this.sortingStates);
		}
	}

	@Override
	public int size() {
		AbstractCrudDAO dao = (AbstractCrudDAO) getQueryConfiguration().get("dao");
		String searchTerm = (String) getQueryConfiguration().get("search");
		Class pojoClass = (Class) getQueryConfiguration().get("pojoClass");
		Integer idEmpresa = (Integer) getQueryConfiguration().get("id_empresa");
		FmMenu menu = (FmMenu) getQueryConfiguration().get("menu");

		int size = 0;

		if (searchTerm != null && !searchTerm.trim().isEmpty() || filters != null) {
			size = dao.fullTextSearchCount(searchTerm, menu, filters);
		} else {
			size = dao.countByEmpresa(pojoClass, idEmpresa);
		}

		logger.info("query result set size:" + size);

		return size;
	}

}