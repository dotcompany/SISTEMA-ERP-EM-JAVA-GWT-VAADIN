package dc.servicos.dao.geral.eventos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.eventos.ContratoEventosEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("contratoEventosDAO")
public class ContratoEventosDAO extends AbstractCrudDAO<ContratoEventosEntity> {

	@Override
	public Class<ContratoEventosEntity> getEntityClass() {
		return ContratoEventosEntity.class;
	}

	@Transactional
	public List<ContratoEventosEntity> listaTodos() {
		return getSession().createQuery("from ContratoEventos").list();
	}

	@Transactional
	public List<ContratoEventosEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from ContratoEventosEntity where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "unidade", "curso", "nomeCerimonial"};
	}

}
