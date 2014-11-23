package dc.servicos.dao.suprimentos.estoque;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.estoque.RequisicaoInterna;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class RequisicaoInternaDAO extends AbstractCrudDAO<RequisicaoInterna> {

	@Override
	public Class<RequisicaoInterna> getEntityClass() {
		return RequisicaoInterna.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "data" };
		// requisicao.getRequisicaoDetalhes().size();
	}

	// @Transactional
	// public List<RequisicaoInterna> findBySetor(){
	//
	// List<RequisicaoInterna> lista = new ArrayList<>();
	// Criteria c = createCriteria(RequisicaoInterna.class);
	// System.out.println(c);
	// return lista;
	// }

}