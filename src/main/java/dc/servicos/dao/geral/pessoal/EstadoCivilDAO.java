package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalEstadoCivilDAO")
public class EstadoCivilDAO extends AbstractCrudDAO<EstadoCivilEntity> {

	@Override
	public Class<EstadoCivilEntity> getEntityClass() {
		return EstadoCivilEntity.class;
	}

	@Transactional
	public List<EstadoCivilEntity> listaTodos() {
		return getSession().createQuery("from EstadoCivil").list();
	}

	@Transactional
	public List<EstadoCivilEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from EstadoCivil where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}