package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.control.enums.TipoPessoaEn;
import dc.control.util.ObjectUtils;
import dc.entidade.geral.PessoaContatoEntity;
import dc.entidade.geral.PessoaEnderecoEntity;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.PessoaFisicaEntity;
import dc.entidade.geral.PessoaJuridicaEntity;
import dc.model.dao.geral.pessoal.PessoaContatoDAO;
import dc.model.dao.geral.pessoal.PessoaDAO;
import dc.model.dao.geral.pessoal.PessoaEnderecoDAO;
import dc.model.dao.geral.pessoal.PessoaFisicaDAO;
import dc.model.dao.geral.pessoal.PessoaJuridicaDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service
@Transactional(readOnly = true)
public class PessoaBusinessImpl implements Serializable,
		PessoaBusiness<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(PessoaBusinessImpl.class);

	@Autowired
	private PessoaDAO<PessoaEntity> dao;

	@Autowired
	private PessoaFisicaDAO<PessoaFisicaEntity> pessoaFisicaDAO;

	@Autowired
	private PessoaJuridicaDAO<PessoaJuridicaEntity> pessoaJuridicaDAO;

	@Autowired
	private PessoaContatoDAO<PessoaContatoEntity> pessoaContatoDAO;

	@Autowired
	private PessoaEnderecoDAO<PessoaEnderecoEntity> pessoaEnderecoDAO;

	/**
	 * **********************************************
	 */

	@Transactional(readOnly = false)
	@Override
	public void delete(PessoaEntity t) throws Exception {
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
	public PessoaEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			PessoaEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public PessoaEntity find(PessoaEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> findAll(PessoaEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> fullTextSearch(String valor,
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
	public void save(PessoaEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			PessoaEntity entity = (PessoaEntity) o;

			if (entity.getTipoPessoa().equals(TipoPessoaEn.F)) {
				if (entity.getPessoaJuridica() != null
						&& entity.getPessoaJuridica().getId() != null) {
					this.pessoaJuridicaDAO.delete(entity.getPessoaJuridica());
				}

				entity.setPessoaJuridica(null);
			} else if (entity.getTipoPessoa().equals(TipoPessoaEn.J)) {
				if (entity.getPessoaFisica() != null
						&& entity.getPessoaFisica().getId() != null) {
					this.pessoaFisicaDAO.delete(entity.getPessoaFisica());
				}

				entity.setPessoaFisica(null);
			}

			this.dao.saveOrUpdate(entity);

			for (PessoaContatoEntity ent : entity.getPessoaContatoList()) {
				this.pessoaContatoDAO.saveOrUpdate(ent);
			}

			for (PessoaEnderecoEntity ent : entity.getPessoaEnderecoList()) {
				if (ObjectUtils.isNotBlank(ent.getUf())) {
					ent.setIdUf(ent.getUf().getId());
					ent.setSiglaUf(ent.getUf().getSigla());
				}

				this.pessoaEnderecoDAO.saveOrUpdate(ent);
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}