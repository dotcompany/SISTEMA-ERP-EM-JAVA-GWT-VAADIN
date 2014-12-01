package dc.servicos.dao.tributario;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.GrupoTributarioEntity;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@SuppressWarnings("unchecked")
public class OperacaoFiscalDAO extends AbstractCrudDAO<OperacaoFiscalEntity> {

	@Override
	public Class<OperacaoFiscalEntity> getEntityClass() {
		return OperacaoFiscalEntity.class;
	}

//	@Override
//	public ContagemEstoque find(Serializable id) {
//		 ContagemEstoque contagemEstoque = super.find(id);
//		// workaround para lazy initialization exception
//		//contagemEstoque.getContagemDetalhes().size();
//		return contagemEstoque;
//	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"data"};
	}
	
	@Transactional
	public List<dc.entidade.tributario.OperacaoFiscalEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from OperacaoFiscal where lower(descricao) like :q").setParameter("q", q).list();
	}
	
	@Transactional
	public List<OperacaoFiscalEntity> listaTodos() {
		return getSession().createQuery("from OperacaoFiscal").list();
	}

}
 
