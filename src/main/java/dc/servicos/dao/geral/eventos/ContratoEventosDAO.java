package dc.servicos.dao.geral.eventos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.eventos.ContratoEventosEntity;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
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
				.createQuery("from ContratoEventos where curso like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "unidade", "curso", "dataContrato", "dataPrimeiroEvento" };
	}
	
	@Transactional
	public List<ContratoEventosEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("from ContratoEventos where lower(curso) like :q")
				.setParameter("q", q).list();
	}

}
