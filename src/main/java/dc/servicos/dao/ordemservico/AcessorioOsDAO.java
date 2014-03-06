package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.AcessorioOs;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class AcessorioOsDAO extends AbstractCrudDAO<AcessorioOs>{

	@Override
	public Class<AcessorioOs> getEntityClass() {
		return AcessorioOs.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"acessorio.nome"};
	}
	
	@Transactional
	public List<AcessorioOs> listaTodos() {
		return getSession().createQuery("from AcessorioOs").list();
	}
}
