package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class FornecedorDAO extends AbstractCrudDAO<FornecedorEntity> {

	@Override
	public Class<FornecedorEntity> getEntityClass() {
		return FornecedorEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "pessoa","situacaoForCli","atividadeForCli","desde", "contaRemetente","prazoMedioEntrega","quantidadeParcelas","observacao" };
	}
	
	@Transactional
	public List<FornecedorEntity> listaTodos() {
		
		List<FornecedorEntity> lista = getSession().createQuery("from Fornecedor")
				.list();

		return lista;
	}
	
	@Transactional
	public List<FornecedorEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Fornecedor where observacao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	@Transactional
	public List<FornecedorEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Fornecedor where lower(observacao) like :q").setParameter("q", q).list();
	}
	
}