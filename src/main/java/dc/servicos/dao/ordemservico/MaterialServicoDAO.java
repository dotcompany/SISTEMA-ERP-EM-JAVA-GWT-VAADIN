package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.MaterialServico;
import dc.entidade.ordemservico.OrdemServico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class MaterialServicoDAO extends AbstractCrudDAO<MaterialServico>{

	@Override
	public Class<MaterialServico> getEntityClass() {
		return MaterialServico.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<MaterialServico> listaTodos() {
		return getSession().createQuery("from MaterialServico").list();
	}
	
	@Transactional
	public List<MaterialServico> findByEntradaServico(OrdemServico ordemServico){

		List<MaterialServico> lista = new ArrayList<>();

		try{
			if(ordemServico!=null){
				lista =  getSession()
						.createQuery("from MaterialServico i where i.ordemServico = :ordemServico")
						.setParameter("ordemServico", ordemServico).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
}
