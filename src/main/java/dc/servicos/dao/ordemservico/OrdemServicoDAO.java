package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.OrdemServico;
import dc.entidade.ordemservico.VendaPeca;
import dc.entidade.pessoal.ClienteEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class OrdemServicoDAO extends AbstractCrudDAO<OrdemServico>{

	@Override
	public Class<OrdemServico> getEntityClass() {
		return OrdemServico.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<OrdemServico> listaTodos() {
		return getSession().createQuery("from OrdemServico").list();
	}
	@Transactional
	public List<OrdemServico> buscarOsPorCliente(ClienteEntity cliente){

		List<OrdemServico> lista = new ArrayList<>();

		try{
			if(cliente!=null){
				lista =  getSession()
						.createQuery("from OrdemServico i where i.cliente = :cliente")
						.setParameter("cliente", cliente).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}

}
