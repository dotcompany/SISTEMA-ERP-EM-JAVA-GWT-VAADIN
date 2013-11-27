package dc.servicos.dao.comercial;


import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.Orcamento;
import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.produto.NCM;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class OrcamentoDAO extends AbstractCrudDAO<Orcamento> {

	@Override
	public Class<Orcamento> getEntityClass() {
		return Orcamento.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<Orcamento> listaTodos() {
		return getSession().createQuery("from Orcamento").list();
	}
	
	
}

