package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.Cliente;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ClienteListController extends CRUDListController<Cliente> {

	@Autowired
	ClienteDAO dao;

	@Autowired
	ClienteFormController clienteFormController;

	@Override
	protected CRUDFormController<Cliente> getFormController() {
		return clienteFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "desde", "contaTomador", "observacao", "geraFinanceiro", "indicadorPreco", "tipoFrete", "formaDesconto",
				"porcentoDesconto", "limiteCredito" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaCliente";
	}

	@Override
	public Class<? super Cliente> getEntityClass() {
		return Cliente.class;
	}

	@Override
	protected List<Cliente> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Cliente";
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
	protected List<Cliente> pesquisaDefault() {
		return (List<Cliente>) dao.getAll(getEntityClass());
	}

}