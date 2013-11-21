package dc.servicos.dao.comercial;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.CondicaoPagamento;
import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class TipoNotaFiscalDAO extends AbstractCrudDAO<TipoNotaFiscal> {

	@Override
	public Class<TipoNotaFiscal> getEntityClass() {
		return TipoNotaFiscal.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<TipoNotaFiscal> listarTodos() {
		List<TipoNotaFiscal> lista = null;
		try {
			String sql = "FROM TipoNotaFiscal";
			lista = super.getSession().createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
