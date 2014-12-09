package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.EmpresaEntity;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalCargoDAO")
public class CargoDAO extends AbstractCrudDAO<CargoEntity> {

	@Override
	public Class<CargoEntity> getEntityClass() {
		return CargoEntity.class;
	}

	@Transactional
	public List<CargoEntity> listaTodos() {
		return getSession().createQuery("FROM CargoEntity").list();
	}

	@Transactional
	public List<CargoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("FROM CargoEntity WHERE nome LIKE :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<CargoEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("FROM CargoEntity WHERE lower(nome) LIKE :q")
				.setParameter("q", q).list();
	}

	@Transactional
	public List<CargoEntity> list(EmpresaEntity empresa) {
		List<CargoEntity> auxLista = getSession()
				.createQuery("FROM CargoEntity WHERE empresa.id = :bid")
				.setParameter("bid", empresa.getId()).list();

		return auxLista;
	}

}