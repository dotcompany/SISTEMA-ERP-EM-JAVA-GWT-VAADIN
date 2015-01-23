package dc.model.business.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.geral.diverso.PaisEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.model.dao.geral.diverso.PaisDAO;
import dc.model.dao.geral.diverso.UfDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service
@Transactional(readOnly = true)
public class UfBusinessImpl implements Serializable, UfBusiness<UfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UfBusinessImpl.class);

	@Autowired
	private UfDAO<UfEntity> dao;

	@Autowired
	private PaisDAO<PaisEntity> paisDAO;

	/**
	 * **********************************************
	 */

	@Override
	public Class<UfEntity> getEntityClass() {
		return UfEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(UfEntity t) throws Exception {
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
	public UfEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			UfEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public UfEntity find(UfEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UfEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UfEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<UfEntity> auxLista = this.dao.listaTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<UfEntity> findAll(UfEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UfEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UfEntity> fullTextSearch(String valor, int first, int pageSize,
			String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UfEntity> fullTextSearch(String valor, String[] sortingFields,
			boolean[] states, List<Filter> filters) throws Exception {
		return dao.fullTextSearch(valor, sortingFields, states, filters);
	}

	@Override
	public <E> List<E> getAll(Class<E> type) throws Exception {
		return dao.getAll(type);
	}

	@Transactional(readOnly = false)
	@Override
	public void save(UfEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			UfEntity uf = (UfEntity) o;

			PaisEntity ent = this.paisDAO.find(uf.getPais().getId());

			uf.setPais(ent);

			this.dao.saveOrUpdate(o);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	/**
	 * 
	 */

	@Transactional
	public UfEntity getObject(String sigla) throws Exception {
		try {
			UfEntity entity = this.dao.getObject(sigla);

			return entity;
		} catch (Exception e) {
			throw e;
		}
	}

}