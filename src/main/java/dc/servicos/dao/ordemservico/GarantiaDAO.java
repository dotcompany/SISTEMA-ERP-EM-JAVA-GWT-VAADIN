package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Garantia;
import dc.entidade.ordemservico.OrdemServico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class GarantiaDAO extends AbstractCrudDAO<Garantia>{

	@Override
	public Class<Garantia> getEntityClass() {
		return Garantia.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<Garantia> listaTodos() {
		return getSession().createQuery("from MaterialServico").list();
	}
	
	@Transactional
	public List<Garantia> findByEntradaServico(OrdemServico ordemServico){

		List<Garantia> lista = new ArrayList<>();

		try{
			if(ordemServico!=null){
				lista =  getSession()
						.createQuery("from Garantia i where i.ordemServico = :ordemServico")
						.setParameter("ordemServico", ordemServico).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
}
