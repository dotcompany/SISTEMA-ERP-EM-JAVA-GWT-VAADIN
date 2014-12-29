package dc.servicos.business.pessoal;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.geral.PessoaJuridicaEntity;
import dc.servicos.dao.geral.pessoal.PessoaJuridicaDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("pessoaJuridicaBusinessImpl")
@Transactional(readOnly = true)
public class PessoaJuridicaBusinessImpl implements Serializable,
		PessoaBusiness<PessoaJuridicaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private PessoaJuridicaDAO mainDAO;

	@Override
	public void delete(PessoaJuridicaEntity t) throws Exception {
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
	public PessoaJuridicaEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PessoaJuridicaEntity find(PessoaJuridicaEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaJuridicaEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaJuridicaEntity> findAll(PessoaJuridicaEntity t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaJuridicaEntity> fullTextSearch(String valor)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaJuridicaEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaJuridicaEntity> fullTextSearch(String valor,
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
	public void save(PessoaJuridicaEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PessoaJuridicaEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaJuridicaEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaJuridicaEntity> search(PessoaJuridicaEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}