package dc.servicos.business.pessoal;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.geral.PessoaFisicaEntity;
import dc.servicos.dao.geral.pessoal.PessoaFisicaDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("pessoaFisicaBusinessImpl")
@Transactional(readOnly = true)
public class PessoaFisicaBusinessImpl implements Serializable,
		PessoaBusiness<PessoaFisicaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private PessoaFisicaDAO mainDAO;

	@Override
	public void delete(PessoaFisicaEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllByIds(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public PessoaFisicaEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PessoaFisicaEntity find(PessoaFisicaEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaFisicaEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaFisicaEntity> findAll(PessoaFisicaEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaFisicaEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaFisicaEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaFisicaEntity> fullTextSearch(String valor,
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

	@Override
	public void save(PessoaFisicaEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PessoaFisicaEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaFisicaEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaFisicaEntity> search(PessoaFisicaEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}