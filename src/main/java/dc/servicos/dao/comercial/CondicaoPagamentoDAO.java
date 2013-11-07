package dc.servicos.dao.comercial;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.CondicaoPagamento;
import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.empresa.EmpresaCnae;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CondicaoPagamentoDAO extends AbstractCrudDAO<CondicaoPagamento> {

	@Override
	public Class<CondicaoPagamento> getEntityClass() {
		return CondicaoPagamento.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<CondicaoPagamento> listarTodos() {
		List<CondicaoPagamento> lista = null;
		try {
			String sql = "FROM CondicaoPagamento";
			lista = super.getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
