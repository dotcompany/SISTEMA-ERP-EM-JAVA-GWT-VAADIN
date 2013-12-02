package dc.servicos.dao.comercial;


import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.Orcamento;
import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.comercial.Venda;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class VendaDAO extends AbstractCrudDAO<Venda> {

	@Override
	public Class<Venda> getEntityClass() {
		return Venda.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<Venda> listaTodos() {
		return getSession().createQuery("from Venda").list();
	}
	
	
}


