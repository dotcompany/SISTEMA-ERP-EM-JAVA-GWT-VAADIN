package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.AcessorioOs;
import dc.entidade.ordemservico.Cor;
import dc.entidade.ordemservico.InformacaoGeral;
import dc.entidade.ordemservico.OrdemServico;
import dc.entidade.ordemservico.TipoEfetivacao;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TipoEfetivacaoDAO extends AbstractCrudDAO<TipoEfetivacao>{

	@Override
	public Class<TipoEfetivacao> getEntityClass() {
		return TipoEfetivacao.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	} 
	
	@Transactional
	public List<TipoEfetivacao> listaTodos() {
		return getSession().createQuery("from TipoEfetivacao").list();
	}
	
	@Transactional  
	public TipoEfetivacao findByCodigo(Integer codigo){
		
		Criteria c = getSession().createCriteria(TipoEfetivacao.class);
		c.add(Restrictions.eq("codigo",codigo));
		return (TipoEfetivacao)c.uniqueResult();
	}
}


