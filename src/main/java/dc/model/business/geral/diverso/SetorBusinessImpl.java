package dc.model.business.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.geral.diverso.SetorEntity;
import dc.model.dao.geral.diverso.SetorDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service
@Transactional(readOnly = true)
public class SetorBusinessImpl implements Serializable,
		SetorBusiness<SetorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(SetorBusinessImpl.class);

	@Autowired
	private SetorDAO<SetorEntity> dao;

	/**
	 * **********************************************
	 */

	@Transactional(readOnly = false)
	@Override
	public void delete(SetorEntity t) throws Exception {
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
	public SetorEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			SetorEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public SetorEntity find(SetorEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SetorEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SetorEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SetorEntity> findAll(SetorEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SetorEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SetorEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SetorEntity> fullTextSearch(String valor,
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
	public void save(SetorEntity t) throws Exception {
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

}