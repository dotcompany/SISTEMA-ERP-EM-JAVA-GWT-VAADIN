package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.TipoAdmissaoEntity;
import dc.servicos.dao.pessoal.TipoAdmissaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TipoAdmissaoListController extends CRUDListController<TipoAdmissaoEntity> {

	@Autowired
	TipoAdmissaoDAO dao;

	@Autowired
	TipoAdmissaoFormController tipoAdmissaoFormController;

	@Override
	protected CRUDFormController<TipoAdmissaoEntity> getFormController() {
		return tipoAdmissaoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaTipoAdmissao";
	}

	@Override
	public Class<? super TipoAdmissaoEntity> getEntityClass() {
		return TipoAdmissaoEntity.class;
	}

	@Override
	protected List<TipoAdmissaoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Tipo Admissão";
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<TipoAdmissaoEntity> pesquisaDefault() {
		return (List<TipoAdmissaoEntity>) dao.getAll(getEntityClass());
	}

}
