package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.comercial.ItemOrcamento;
import dc.entidade.comercial.Orcamento;
import dc.entidade.ordemservico.OrcamentoOs;
import dc.entidade.ordemservico.OrcamentoOsItem;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class OrcamentoItemOsDAO extends AbstractCrudDAO<OrcamentoOsItem>{

	@Override
	public Class<OrcamentoOsItem> getEntityClass() {
		return OrcamentoOsItem.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<OrcamentoOsItem> listaTodos() {
		return getSession().createQuery("from OrcamentoOsItem").list();
	}
	
	@Transactional
	public List<OrcamentoOsItem> findByOrcamentoOs(OrcamentoOs orcamentoOs){

		List<OrcamentoOsItem> lista = new ArrayList<>();

		try{
			if(orcamentoOs!=null){
				lista =  getSession()
						.createQuery("from OrcamentoOsItem i where i.orcamentoOs = :orcamentoOs")
						.setParameter("orcamentoOs", orcamentoOs).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
}
