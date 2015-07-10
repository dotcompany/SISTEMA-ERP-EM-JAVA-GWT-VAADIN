package dc.servicos.dao.financeiro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class LancamentoPagarDAO extends AbstractCrudDAO<LancamentoPagarEntity> {

	@Override
	public Class<LancamentoPagarEntity> getEntityClass() {
		return LancamentoPagarEntity.class;
	}

	@Transactional
	public List<LancamentoPagarEntity> listaTodos() {
		return getSession().createQuery("from LancamentoPagarEntity").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "pagamentoCompartilhado" };
	}
	
	@Transactional
	public List<LancamentoPagarEntity> buscar(FornecedorEntity fornecedor){

		List<LancamentoPagarEntity> lista = new ArrayList<>();

		try{
			if(fornecedor!=null){
				lista =  getSession()
						.createQuery("from LancamentoPagarEntity i where i.fornecedor = :fornecedor")
						.setParameter("fornecedor", fornecedor).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}

}
