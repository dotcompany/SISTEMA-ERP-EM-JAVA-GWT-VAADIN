package dc.visao.suprimentos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.Empresa;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.entidade.suprimentos.ContagemEstoqueDetalhe;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.suprimentos.ContagemEstoqueDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ContagemEstoqueFormController extends CRUDFormController<ContagemEstoque> {

	ContagemEstoqueFormView subView;

	@Autowired
	ContagemEstoqueDAO dao;

	@Autowired
	ProdutoDAO produtoDAO;

	private ContagemEstoque currentBean;

	@Override
	public String getViewIdentifier() {
		return "contagemEstoqueForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;
		if (!Validator.validateObject(subView.getDataContagem().getValue())) {
			adicionarErroDeValidacao(subView.getDataContagem(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ContagemEstoque();
	}

	@Override
	protected void initSubView() {
		subView = new ContagemEstoqueFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.getDataContagem().setValue(currentBean.getData());
		subView.fillContagemEstoqueDetalhesSubForm(currentBean.getContagemDetalhes());
	}

	public Empresa empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setData(subView.getDataContagem().getValue());
			currentBean.setEmpresa(empresaAtual());
			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro("Erro!!");
			e.printStackTrace();
		}

	}

	@Override
	protected void quandoNovo() {
		subView.fillContagemEstoqueDetalhesSubForm(currentBean.getContagemDetalhes());

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Contagem Estoque";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		for (Serializable i : ids) {
			ContagemEstoque c = dao.find(i);
			c.setContagemDetalhes(null);
		}
		dao.deleteAllByIds(ids);

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public ContagemEstoqueDetalhe novoContagemEstoqueDetalhe() {
		ContagemEstoqueDetalhe detalhe = new ContagemEstoqueDetalhe();
		currentBean.addContagemDetalhe(detalhe);
		return detalhe;
	}

	public List<Produto> buscarProdutos() {
		return produtoDAO.getAll(Produto.class);
	}

	@Override
	public ContagemEstoque getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
