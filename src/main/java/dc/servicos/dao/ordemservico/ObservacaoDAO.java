package dc.servicos.dao.ordemservico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Equipamento;
import dc.entidade.ordemservico.Observacao;
import dc.entidade.ordemservico.OrdemServico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ObservacaoDAO extends AbstractCrudDAO<Observacao>{

	@Override
	public Class<Observacao> getEntityClass() {
		return Observacao.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"ordemServico.id"};
	} 
	
	@Transactional
	public List<Equipamento> listaTodos() {
		return getSession().createQuery("from Observacao").list();
	}
	
	@Transactional  
	public Observacao buscaObservacao(OrdemServico ordemServico){
		
		Criteria c = getSession().createCriteria(Observacao.class);
		c.add(Restrictions.eq("ordemServico",ordemServico));
		return (Observacao)c.uniqueResult();
	}
}
