package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ParcelaPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ParcelaPagamentoDAO extends AbstractCrudDAO<ParcelaPagamento> {

	@Override
	public Class<ParcelaPagamento> getEntityClass() {
		return ParcelaPagamento.class;
	}

	@Transactional
	public List<ParcelaPagamento> listaTodos() {
		return getSession().createQuery("from ParcelaPagamento").list();
	}
	
	@Transactional
	public List<ParcelaPagamento> buscaPorParcelaPagar(ParcelaPagamento parcelaPagamento){
		 Session session = getSession();
         Criteria criteria = session.createCriteria(ParcelaPagamento.class);
         criteria.add(Restrictions.eq("parcelaPagamento", parcelaPagamento));

         List<ParcelaPagamento> parcelaPagamentos = criteria.list();
         
         return parcelaPagamentos;
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[] { "contaCaixa", "tipoPagamento", "dataPagamento", "taxaJuro", "taxaMulta", "taxaDesconto", "valorJuro",
				"valorMulta", "valorDesconto","valorPago" ,"historico"  };
	}

}
