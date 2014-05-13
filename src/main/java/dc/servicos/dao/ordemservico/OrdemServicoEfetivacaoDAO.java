package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.OrdemServico;
import dc.entidade.ordemservico.OrdemServicoEfetivacao;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class OrdemServicoEfetivacaoDAO extends AbstractCrudDAO<OrdemServicoEfetivacao>{

	@Override
	public Class<OrdemServicoEfetivacao> getEntityClass() {
		return OrdemServicoEfetivacao.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<OrdemServicoEfetivacao> listaTodos() {
		return getSession().createQuery("from OrdemServicoEfetivacao").list();
	}
	
	@Transactional
	public List<OrdemServicoEfetivacao> buscarOsPorOrdemServico(OrdemServico ordemServico){

		List<OrdemServicoEfetivacao> lista = new ArrayList<>();

		try{
			if(ordemServico!=null){
				lista =  getSession()
						.createQuery("from OrdemServicoEfetivacao i where i.ordemServico = :ordemServico")
						.setParameter("ordemServico", ordemServico).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}

}
