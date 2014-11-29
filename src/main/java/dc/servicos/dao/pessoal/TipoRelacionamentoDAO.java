package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.TipoRelacionamentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalTipoRelacionamentoDAO")
public class TipoRelacionamentoDAO extends
		AbstractCrudDAO<TipoRelacionamentoEntity> {

	@Override
	public Class<TipoRelacionamentoEntity> getEntityClass() {
		return TipoRelacionamentoEntity.class;
	}

	@Transactional
	public List<TipoRelacionamentoEntity> listaTodos() {
		return getSession().createQuery("from TipoRelacionamento").list();
	}

	@Transactional
	public List<TipoRelacionamentoEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from TipoRelacionamento where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "codigo", "nome", "descricao" };
	}

}