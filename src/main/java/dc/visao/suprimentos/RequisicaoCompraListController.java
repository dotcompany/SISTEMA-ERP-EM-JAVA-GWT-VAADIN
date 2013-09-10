package dc.visao.suprimentos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.suprimentos.Requisicao;
import dc.servicos.dao.suprimentos.RequisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class RequisicaoCompraListController extends CRUDListController<Requisicao> {

	@Autowired
	RequisicaoDAO dao;
	
	@Autowired
	RequisicaoCompraFormController formController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"id", "dataRequisicao", "colaborador.pessoa.nome", "tipoRequisicao.descricao"};
	}

	@Override
	protected String getTitulo() {
		return "Requisição de Compra";
	}

	@Override
	protected List<Requisicao> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaRequisicaoCompra";
	}

	@Override
	protected CRUDFormController<Requisicao> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super Requisicao> getEntityClass() {
		return Requisicao.class;
	}

	@Override
	protected List<Requisicao> pesquisaDefault() {
		return dao.getAll(Requisicao.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}
