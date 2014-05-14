package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Cor;
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
	public List<Cor> listaTodos() {
		return getSession().createQuery("from TipoEfetivacao").list();
	}
}


