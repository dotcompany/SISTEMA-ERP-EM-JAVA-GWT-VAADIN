package dc.servicos.dao.geral;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.control.util.ListUtils;
import dc.entidade.geral.pessoal.PessoaContatoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PessoaContatoDAO extends AbstractCrudDAO<PessoaContatoEntity> {

	@Override
	public Class<PessoaContatoEntity> getEntityClass() {
		return PessoaContatoEntity.class;
	}

	@Transactional
	public List<PessoaContatoEntity> listaTodos() {
		return getSession().createQuery("FROM PessoaContatoEntity").list();
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "email" };
	}

	@Transactional
	public List<PessoaContatoEntity> getPessoaContatoList(PessoaEntity entity) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.pessoa.id = :id";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", entity.getId());

			List<PessoaContatoEntity> auxLista = query.list();

			// if (auxLista == null) {
			// auxLista = new ArrayList<PessoaContatoEntity>();
			// }

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public void saveOrUpdatePessoaContatoList(List<PessoaContatoEntity> auxLista) {
		try {
			if (ListUtils.isNullOrEmpty(auxLista)) {
				return;
			}

			for (PessoaContatoEntity ent : auxLista) {
				super.saveOrUpdate(ent);
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}