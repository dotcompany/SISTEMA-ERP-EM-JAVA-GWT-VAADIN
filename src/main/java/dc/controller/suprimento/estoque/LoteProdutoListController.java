package dc.controller.suprimento.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.estoque.LoteProdutoEntity;
import dc.model.dao.suprimento.estoque.LoteProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class LoteProdutoListController extends CRUDListController<LoteProdutoEntity> {
	
	
		private static final long serialVersionUID = 1L;

		@Autowired
		private LoteProdutoDAO dao;

		@Autowired
		private LoteProdutoFormController loteProdutoFormController;

		@Override
		public String[] getColunas() {
			return new String[] {"nome" };
		}

		@Override
		public Class<? super LoteProdutoEntity> getEntityClass() {
			return LoteProdutoEntity.class;
		}

		@Override
		protected String getTitulo() {
			return "Lote Produto";
		}

		@Override
		protected List<LoteProdutoEntity> pesquisa(String valor) {
			return dao.fullTextSearch(valor);
		}

		@Override
		protected CRUDFormController<LoteProdutoEntity> getFormController() {
			return loteProdutoFormController;
		}

		@Override
		public String getViewIdentifier() {
			return ClassUtils.getUrl(this);
		}

		@Override
		protected boolean deletaEmCascata() {
			// TODO Auto-generated method stub
			return true;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected List<LoteProdutoEntity> pesquisaDefault() {
			return (List<LoteProdutoEntity>) dao.getAll(getEntityClass());
		}

}
