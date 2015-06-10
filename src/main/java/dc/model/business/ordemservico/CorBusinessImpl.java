package dc.model.business.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.ordemservico.CorEntity;
import dc.model.dao.ordemservico.CorDAO;

/**
 * 
 * @author Paulo S�rgio Ferreira
 * 
 */

@Service
@Transactional(readOnly = true)
public class CorBusinessImpl implements Serializable,
		CorBusiness<CorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CorBusinessImpl.class);

	@Autowired
	private CorDAO<CorEntity> dao;

	/**
	 * **********************************************
	 */

	@Override
	public Class<CorEntity> getEntityClass() {
		return CorEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(CorEntity t) throws Exception {
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
	public CorEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			CorEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public CorEntity find(CorEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CorEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CorEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<CorEntity> auxLista = this.dao.listaTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<CorEntity> findAll(CorEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CorEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CorEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CorEntity> fullTextSearch(String valor,
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
	public void save(CorEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			CorEntity ent = (CorEntity) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<CorEntity> getAllForComboSelect(Class<CorEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<CorEntity> getAllForCombo(Class<CorEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<CorEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}