package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.entidade.suprimentos.estoque.ContagemDetalheEntity;
import dc.servicos.dao.geral.produto.ProdutoDAO;
import dc.servicos.dao.suprimentos.compra.ContagemEstoqueDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.suprimentos.compra.ContagemEstoqueFormView;

@Controller
@Scope("prototype")
public class ContagemCabecalhoFormController extends
		CRUDFormController<ContagemCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContagemEstoqueFormView subView;

	@Autowired
	private ContagemEstoqueDAO dao;

	@Autowired
	private ProdutoDAO produtoDAO;

	private ContagemCabecalhoEntity currentBean;

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateObject(subView.getDataContagem().getValue())) {
			adicionarErroDeValidacao(subView.getDataContagem(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ContagemCabecalhoEntity();
	}

	@Override
	protected void initSubView() {
		subView = new ContagemEstoqueFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.getDataContagem().setValue(currentBean.getDataContagem());
		// subView.fillContagemEstoqueDetalhesSubForm(currentBean
		// .getContagemDetalhes());
	}

	public EmpresaEntity empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setDataContagem(subView.getDataContagem().getValue());
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
		// subView.fillContagemEstoqueDetalhesSubForm(currentBean
		// .getContagemDetalhes());
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
			ContagemCabecalhoEntity c = dao.find(i);
			// c.setContagemDetalhes(null);
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

	public ContagemDetalheEntity novoContagemEstoqueDetalhe() {
		ContagemDetalheEntity detalhe = new ContagemDetalheEntity();
		// currentBean.addContagemDetalhe(detalhe);

		return detalhe;
	}

	public List<ProdutoEntity> buscarProdutos() {
		return produtoDAO.getAll(ProdutoEntity.class);
	}

	@Override
	public ContagemCabecalhoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}