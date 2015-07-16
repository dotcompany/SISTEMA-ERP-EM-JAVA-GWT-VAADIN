package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.ProdutoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ProdutoDAO extends AbstractCrudDAO<ProdutoEntity> {

	@Override
	public Class<ProdutoEntity> getEntityClass() {
		return ProdutoEntity.class;
	}

	@Transactional
	public List<ProdutoEntity> listaTodos() {
		return getSession().createQuery("from Produto").list();
	}

	@Transactional
	public List<ProdutoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Produto where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	@Transactional
	public void saveOrUpdateProduto(ProdutoEntity entity) throws Exception {
		try {
			//entity.getNcm().getProdutoList().clear();
			//entity.getSubGrupo().getProdutoList().clear();
			//entity.getGrupo().getProdutoList().clear();
			//entity.getMarca().getProdutoList().clear();
			
			super.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "subGrupo","unidadeProduto","marca","almoxarifado", "codigoInterno", 
				"nome", "descricao"};
	}

}