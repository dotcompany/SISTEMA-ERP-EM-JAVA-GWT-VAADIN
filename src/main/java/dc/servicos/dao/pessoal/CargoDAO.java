package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.CargoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalCargoDAO")
public class CargoDAO extends AbstractCrudDAO<CargoEntity> {

	@Override
	public Class<CargoEntity> getEntityClass() {
		return CargoEntity.class;
	}

	@Transactional
	public List<CargoEntity> listaTodos() {
		return getSession().createQuery("from Cargo").list();
	}

	@Transactional
	public List<CargoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Cargo where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<CargoEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession().createQuery("from Cargo where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}