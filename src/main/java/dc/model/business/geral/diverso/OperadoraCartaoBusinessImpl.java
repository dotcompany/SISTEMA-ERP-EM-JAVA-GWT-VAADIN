package dc.model.business.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.geral.diverso.OperadoraCartaoEntity;
import dc.model.dao.geral.diverso.OperadoraCartaoDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service
@Transactional(readOnly = true)
public class OperadoraCartaoBusinessImpl implements Serializable,
		OperadoraCartaoBusiness<OperadoraCartaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(OperadoraCartaoBusinessImpl.class);

	@Autowired
	private OperadoraCartaoDAO<OperadoraCartaoEntity> dao;

	/**
	 * **********************************************
	 */

	@Transactional(readOnly = false)
	@Override
	public void delete(OperadoraCartaoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] deleteAll");

			this.dao.deleteAllByIds(list);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public OperadoraCartaoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			OperadoraCartaoEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public OperadoraCartaoEntity find(OperadoraCartaoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperadoraCartaoEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperadoraCartaoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperadoraCartaoEntity> findAll(OperadoraCartaoEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperadoraCartaoEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperadoraCartaoEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperadoraCartaoEntity> fullTextSearch(String valor,
			String[] sortingFields, boolean[] states, List<Filter> filters)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> getAll(Class<E> type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = false)
	@Override
	public void save(OperadoraCartaoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			this.dao.saveOrUpdate(o);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public Class<OperadoraCartaoEntity> getEntityClass() {
		return OperadoraCartaoEntity.class;
	}

}