package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.SefipCodigoRecolhimento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SefipCodigoRecolhimentoDAO extends AbstractCrudDAO<SefipCodigoRecolhimento>{

	@Override
	public Class<SefipCodigoRecolhimento> getEntityClass() {
		return SefipCodigoRecolhimento.class;
	}
	
	@Transactional
	public List<SefipCodigoRecolhimento> listaTodos() {
		return getSession().createQuery("from SefipCodigoRecolhimento").list();
	}

	@Transactional
	public List<SefipCodigoRecolhimento> procuraNomeContendo(String query) {
		return getSession().createQuery("from SefipCodigoRecolhimento where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "aplicacao"};
	}


}
