package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.geral.PessoaContatoEntity;
import dc.entidade.geral.PessoaEntity;
import dc.model.dao.geral.pessoal.PessoaContatoDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service
@Transactional(readOnly = true)
public class PessoaContatoBusinessImpl implements Serializable,
		PessoaContatoBusiness<PessoaContatoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(PessoaContatoBusinessImpl.class);

	@Autowired
	private PessoaContatoDAO<PessoaContatoEntity> dao;

	/**
	 * **********************************************
	 */

	@Transactional(readOnly = false)
	@Override
	public void delete(PessoaContatoEntity t) throws Exception {
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
	public PessoaContatoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			PessoaContatoEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public PessoaContatoEntity find(PessoaContatoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaContatoEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaContatoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaContatoEntity> findAll(PessoaContatoEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaContatoEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaContatoEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaContatoEntity> fullTextSearch(String valor,
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
	public void save(PessoaContatoEntity t) throws Exception {
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

	/**
	 * 
	 */

	@Override
	public List<PessoaContatoEntity> list(PessoaEntity entity) {
		// TODO Auto-generated method stub
		try {
			List<PessoaContatoEntity> auxLista = this.dao.list(entity);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}