package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.TipoDesligamentoEntity;
import dc.servicos.dao.pessoal.TipoDesligamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TipoDesligamentoListController extends CRUDListController<TipoDesligamentoEntity> {

	@Autowired
	TipoDesligamentoDAO dao;

	@Autowired
	TipoDesligamentoFormController tipoDesligamentoFormController;

	@Override
	protected CRUDFormController<TipoDesligamentoEntity> getFormController() {
		return tipoDesligamentoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaTipoDesligamento";
	}

	@Override
	public Class<? super TipoDesligamentoEntity> getEntityClass() {
		return TipoDesligamentoEntity.class;
	}

	@Override
	protected List<TipoDesligamentoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Tipo Desligamento";
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
	protected List<TipoDesligamentoEntity> pesquisaDefault() {
		return (List<TipoDesligamentoEntity>) dao.getAll(getEntityClass());
	}

}
