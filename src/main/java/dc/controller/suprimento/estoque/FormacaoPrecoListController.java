package dc.controller.suprimento.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.estoque.FormacaoPrecoEntity;
import dc.servicos.dao.suprimentos.estoque.FormacaoPrecoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class FormacaoPrecoListController extends CRUDListController<FormacaoPrecoEntity> {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Autowired
		FormacaoPrecoDAO dao;

		@Autowired
		FormacaoPrecoFormController formController;

		@Override
		public String[] getColunas() {
			return new String[] {"" };
		}

		@Override
		protected String getTitulo() {
			return "Formação Preço";
		}

		@Override
		protected List<FormacaoPrecoEntity> pesquisa(String valor) {
			return dao.fullTextSearch(valor);
		}

		@Override
		public String getViewIdentifier() {
			return ClassUtils.getUrl(this);
		}

		@Override
		protected CRUDFormController<FormacaoPrecoEntity> getFormController() {
			return formController;
		}

		@Override
		public Class<? super FormacaoPrecoEntity> getEntityClass() {
			return FormacaoPrecoEntity.class;
		}

		@Override
		protected List<FormacaoPrecoEntity> pesquisaDefault() {
			return dao.getAll(FormacaoPrecoEntity.class);
		}

		@Override
		protected boolean deletaEmCascata() {
			return true;
		}

}
