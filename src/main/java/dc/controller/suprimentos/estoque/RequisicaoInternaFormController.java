package dc.controller.suprimentos.estoque;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.geral.Usuario;
import dc.entidade.pessoal.Colaborador;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.RequisicaoInternaDetalhe;
import dc.entidade.suprimentos.estoque.RequisicaoInterna;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.suprimentos.estoque.RequisicaoInternaDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.suprimentos.estoque.RequisicaoInternaFormView;

@Controller
@Scope("prototype")
public class RequisicaoInternaFormController extends
		CRUDFormController<RequisicaoInterna> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RequisicaoInternaFormView subView;

	@Autowired
	RequisicaoInternaDAO dao;

	private RequisicaoInterna currentBean;

	@Autowired
	ProdutoDAO produtoDAO;

	@Override
	protected String getNome() {
		return "Requisição Interna";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	public Colaborador buscaColaborador() {
		Usuario usuario = SecuritySessionProvider.getUsuario();
		Colaborador col = usuario.getColaborador();

		return col;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setDataRequisicao(subView.getDataRequisicao()
					.getValue());
			currentBean.setColaborador(buscaColaborador());
			dao.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find((Integer) id);
		subView.getDataRequisicao().setValue(currentBean.getDataRequisicao());
		subView.fillRequisicaoDetalhesSubForm(currentBean.getDetalhes());
	}

	@Override
	protected void initSubView() {
		subView = new RequisicaoInternaFormView(this);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new RequisicaoInterna();
	}

	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		Date dataRequisicao = (Date) subView.getDataRequisicao().getValue();

		if (!Validator.validateObject(dataRequisicao)) {
			adicionarErroDeValidacao(subView.getDataRequisicao(),
					"Informe a Data da Requisição!");
			valido = false;
		}

		System.out.println("");

		return valido;
	}

	@Override
	protected void quandoNovo() {
		subView.fillRequisicaoDetalhesSubForm(currentBean.getDetalhes());

	}

	@Override
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	public RequisicaoInternaDetalhe novoRequisicaoDetalhe() {
		RequisicaoInternaDetalhe detalhe = new RequisicaoInternaDetalhe();
		currentBean.addRequisicaoDetalhe(detalhe);

		return detalhe;
	}

	public List<Produto> buscarProdutos() {
		return produtoDAO.getAll(Produto.class);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public RequisicaoInterna getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}