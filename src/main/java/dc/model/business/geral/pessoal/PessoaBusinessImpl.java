package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.control.enums.TipoPessoaEn;
import dc.control.util.ListUtils;
import dc.control.util.ObjectUtils;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.entidade.geral.pessoal.PessoaContatoEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.entidade.geral.pessoal.PessoaJuridicaEntity;
import dc.model.dao.geral.pessoal.EstadoCivilDAO;
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

	@Autowired
	private EstadoCivilDAO<EstadoCivilEntity> estadoCivilDAO;

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

			PessoaEntity ent = (PessoaEntity) o;

			if (ent.getTipoPessoa().equals(TipoPessoaEn.F)) {
				if (ObjectUtils.isNotBlank(ent.getPessoaJuridica())) {
					this.pessoaJuridicaDAO.delete(ent.getPessoaJuridica());
				}

				ent.setPessoaJuridica(null);

				if (ObjectUtils.isNotBlank(ent.getPessoaFisica()
						.getEstadoCivil())) {
					EstadoCivilEntity estadoCivil = this.estadoCivilDAO
							.find(ent.getPessoaFisica().getEstadoCivil()
									.getId());
					ent.getPessoaFisica().setEstadoCivil(estadoCivil);
				}
			} else if (ent.getTipoPessoa().equals(TipoPessoaEn.J)) {
				if (ObjectUtils.isNotBlank(ent.getPessoaFisica())) {
					this.pessoaFisicaDAO.delete(ent.getPessoaFisica());
				}

				ent.setPessoaFisica(null);
			}

			this.dao.saveOrUpdate(ent);

			if (ListUtils.isNotNullAndNotEmpty(ent.getPessoaContatoList())) {
				for (PessoaContatoEntity ent1 : ent.getPessoaContatoList()) {
					this.pessoaContatoDAO.saveOrUpdate(ent1);
				}
			}

			if (ListUtils.isNotNullAndNotEmpty(ent.getPessoaEnderecoList())) {
				for (PessoaEnderecoEntity ent1 : ent.getPessoaEnderecoList()) {
					if (ObjectUtils.isNotBlank(ent1.getUf())) {
						ent1.setIdUf(ent1.getUf().getId());
						ent1.setSiglaUf(ent1.getUf().getSigla());
					}

					this.pessoaEnderecoDAO.saveOrUpdate(ent1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}