package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.EntradaServico;
import dc.entidade.ordemservico.OrdemServico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class EntradaServicoDAO extends AbstractCrudDAO<EntradaServico>{

	@Override
	public Class<EntradaServico> getEntityClass() {
		return EntradaServico.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<EntradaServico> listaTodos() {
		return getSession().createQuery("from EntradaServico").list();
	}
	
	@Transactional
	public List<EntradaServico> findByEntradaServico(OrdemServico ordemServico){

		List<EntradaServico> lista = new ArrayList<>();

		try{
			if(ordemServico!=null){
				lista =  getSession()
						.createQuery("from EntradaServico i where i.ordemServico = :ordemServico")
						.setParameter("ordemServico", ordemServico).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
}
