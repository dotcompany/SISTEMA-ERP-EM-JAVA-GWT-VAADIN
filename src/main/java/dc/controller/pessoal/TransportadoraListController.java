package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.TransportadoraEntity;
import dc.servicos.dao.pessoal.TransportadoraDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TransportadoraListController extends CRUDListController<TransportadoraEntity> {

	@Autowired
	TransportadoraDAO dao;

	@Autowired
	TransportadoraFormController transportadoraFormController;

	@Override
	protected CRUDFormController<TransportadoraEntity> getFormController() {
		return transportadoraFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "observacao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaTransportadora";
	}

	@Override
	public Class<? super TransportadoraEntity> getEntityClass() {
		return TransportadoraEntity.class;
	}

	@Override
	protected List<TransportadoraEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Transportadora";
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
	protected List<TransportadoraEntity> pesquisaDefault() {
		return (List<TransportadoraEntity>) dao.getAll(getEntityClass());
	}

}
