package dc.servicos.dao.financeiro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ExtratoContaBancoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ExtratoContaBancoDAO extends AbstractCrudDAO<ExtratoContaBancoEntity> {
	
	@Override
	public Class<ExtratoContaBancoEntity> getEntityClass() {
		return ExtratoContaBancoEntity.class;
	}

	@Transactional
	public List<ExtratoContaBancoEntity> listaTodos() {
		return getSession().createQuery("from ExtratoContaBanco").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "mes","ano","dataMovimento","valor","observacoes" };
	}
	
	@Transactional
	public List<ExtratoContaBancoEntity> findByExtratoContaBanco(ExtratoContaBancoEntity extrato){

		List<ExtratoContaBancoEntity> lista = new ArrayList<>();

		try{
			if(extrato!=null){
				lista =  getSession()
						.createQuery("from ExtratoContaBancoEntity i where i.extrato = :extrato")
						.setParameter("extrato", extrato).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
	

}
