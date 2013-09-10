package dc.servicos.dao.suprimentos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.diversos.Setor;
import dc.entidade.suprimentos.Requisicao;
import dc.entidade.suprimentos.RequisicaoInterna;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class RequisicaoInternaDAO
extends AbstractCrudDAO<RequisicaoInterna>{

	@Override
	protected Class<RequisicaoInterna> getEntityClass() {
		return RequisicaoInterna.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"data"};
		//requisicao.getRequisicaoDetalhes().size();
	}
//	@Transactional	
//	public List<RequisicaoInterna> findBySetor(){
//	
//		List<RequisicaoInterna> lista = new ArrayList<>();
//		Criteria c = createCriteria(RequisicaoInterna.class);
//		System.out.println(c);
//		return lista;
//	}
	
}
