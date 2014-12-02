package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.SefipCodigoRecolhimentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SefipCodigoRecolhimentoDAO extends AbstractCrudDAO<SefipCodigoRecolhimentoEntity>{

	@Override
	public Class<SefipCodigoRecolhimentoEntity> getEntityClass() {
		return SefipCodigoRecolhimentoEntity.class;
	}
	
	@Transactional
	public List<SefipCodigoRecolhimentoEntity> listaTodos() {
		return getSession().createQuery("from SefipCodigoRecolhimento").list();
	}

	@Transactional
	public List<SefipCodigoRecolhimentoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from SefipCodigoRecolhimento where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "aplicacao"};
	}


}
