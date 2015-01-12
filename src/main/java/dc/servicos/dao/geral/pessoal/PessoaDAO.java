package dc.servicos.dao.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.control.enums.TipoPessoaEn;
import dc.control.util.ObjectUtils;
import dc.entidade.geral.pessoal.PessoaContatoEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.entidade.geral.pessoal.PessoaJuridicaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.geral.PessoaContatoDAO;
import dc.servicos.dao.geral.PessoaEnderecoDAO;

@Repository("pessoalPessoaDAO")
public class PessoaDAO extends AbstractCrudDAO<PessoaEntity> {

	/**
	 * DAOS
	 */

	@Autowired
	private PessoaFisicaDAO pessoaFisicaDAO;

	@Autowired
	private PessoaJuridicaDAO pessoaJuridicaDAO;

	@Autowired
	private PessoaContatoDAO pessoaContatoDAO;

	@Autowired
	private PessoaEnderecoDAO pessoaEnderecoDAO;

	/**
	 * 
	 */

	@Override
	public Class<PessoaEntity> getEntityClass() {
		return PessoaEntity.class;
	}

	@Transactional
	public List<PessoaEntity> listaTodos() {
		return getSession().createQuery("FROM PessoaEntity").list();
	}

	@Transactional
	public PessoaFisicaEntity getPessoaFisica(Integer idPessoa) {
		if (idPessoa == null) {
			return null;
		}

		List<PessoaFisicaEntity> list = getSession()
				.createCriteria(PessoaFisicaEntity.class)
				.add(Restrictions.eq("pessoa.id", idPessoa)).list();

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Transactional
	public PessoaJuridicaEntity getPessoaJuridica(Integer idPessoa) {
		if (idPessoa == null) {
			return null;
		}

		List<PessoaJuridicaEntity> list = getSession()
				.createCriteria(PessoaJuridicaEntity.class)
				.add(Restrictions.eq("pessoa.id", idPessoa)).list();

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Transactional
	public List<PessoaEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("from Pessoa where lower(nome) like :q")
				.setParameter("q", q).list();
	}

	@Transactional
	public void deletePessoa(List<Serializable> ids) throws Exception {
		try {
			for (Serializable id : ids) {
				PessoaEntity ent = this.find(id);

				super.delete(ent);
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public PessoaEntity getEntity(Serializable id) {
		try {
			String sql = "FROM :entity ent INNER JOIN ent.pessoaEnderecoList"
					+ " WHERE (1 = 1) AND ent.id = :id";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", Integer.valueOf(id.toString()));

			PessoaEntity ent = (PessoaEntity) query.uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public void saveOrUpdatePessoa(PessoaEntity entity) throws Exception {
		try {
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

			super.saveOrUpdate(entity);

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

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "tipoPessoa", "email", "site" };
	}

}