package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Controller
@Scope("prototype")
public class FornecedorListController extends CRUDListController<FornecedorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private FornecedorDAO dao;

	@Autowired
	private FornecedorFormController fornecedorFormController;
	
	public FornecedorListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] getColunas() {
		return new String[] { "pessoa", "desde", "optanteSimplesNacional",
				"localizacao", "dataCadastro", "sofreRetencao" };
	}

	@Override
	public Class<? super FornecedorEntity> getEntityClass() {
		return FornecedorEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Fornecedor";
	}

	@Override
	protected List<FornecedorEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<FornecedorEntity> getFormController() {
		return fornecedorFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaFornecedor";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<FornecedorEntity> pesquisaDefault() {
		return (List<FornecedorEntity>) dao.getAll(getEntityClass());
	}
	
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

}