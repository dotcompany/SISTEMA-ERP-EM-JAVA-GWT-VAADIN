package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.geral.FornecedorListController;
import dc.entidade.geral.Fornecedor;
import dc.entidade.suprimentos.Cotacao;
import dc.entidade.suprimentos.CotacaoDetalhe;
import dc.entidade.suprimentos.FornecedorCotacao;
import dc.entidade.suprimentos.RequisicaoCotacaoDetalhe;
import dc.entidade.suprimentos.RequisicaoDetalhe;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.suprimentos.CotacaoDAO;
import dc.servicos.dao.suprimentos.RequisicaoDetalheDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.compra.ConfirmaCotacaoFormView;

@Controller
@Scope("prototype")
public class ConfirmaCotacaoFormController extends CRUDFormController<Cotacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ConfirmaCotacaoFormView subView;

	@Autowired
	CotacaoDAO cotacaoDao;

	@Autowired
	FornecedorDAO fornecedorDao;

	@Autowired
	RequisicaoDetalheDAO requisicaoDetalheDao;

	private Cotacao currentBean;

	@Override
	protected String getNome() {
		return "Confirma cotação";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setDescricao(subView.getTxtDescricao().getValue());
			currentBean.setDataCotacao(subView.getCalDataCotacao().getValue());
			cotacaoDao.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = cotacaoDao.find(id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getCalDataCotacao().setValue(currentBean.getDataCotacao());

		List<FornecedorCotacao> fornecedorCotacaos = currentBean
				.getCompraFornecedorCotacaos();
		subView.fillCompraFornecedorCotacoesSubForm(fornecedorCotacaos);

		List<RequisicaoCotacaoDetalhe> compraReqCotacaoDetalhes = currentBean
				.getCompraReqCotacaoDetalhes();

		for (RequisicaoCotacaoDetalhe requisicaoCotacaoDetalhe : compraReqCotacaoDetalhes) {
			RequisicaoDetalhe requisicaoDetalhe = requisicaoCotacaoDetalhe
					.getRequisicaoDetalhe();

			COTACOES: for (FornecedorCotacao fornecedorCotacao : fornecedorCotacaos) {
				List<CotacaoDetalhe> cotacaoDetalhes = fornecedorCotacao
						.getCotacaoDetalhes();

				for (CotacaoDetalhe cotacaoDetalhe : cotacaoDetalhes) {
					if (cotacaoDetalhe.getProduto().equals(
							requisicaoDetalhe.getProduto())
							&& cotacaoDetalhe.getQuantidade().equals(
									requisicaoDetalhe.getQuantidade())) {
						continue COTACOES;
					}
				}

				CotacaoDetalhe cotacaoDetalhe = new CotacaoDetalhe();
				cotacaoDetalhe.setProduto(requisicaoDetalhe.getProduto());
				cotacaoDetalhe.setQuantidade(requisicaoDetalhe.getQuantidade());
				fornecedorCotacao.addCotacaoDetalhe(cotacaoDetalhe);
			}
		}
	}

	@Override
	protected void initSubView() {
		subView = new ConfirmaCotacaoFormView();

		DefaultManyToOneComboModel<Fornecedor> fornecedorModel = new DefaultManyToOneComboModel<Fornecedor>(
				FornecedorListController.class, this.fornecedorDao,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "pessoa";
			}
		};

		// subView.getCmbFornecedor().setModel(fornecedorModel);
	}

	@Override
	protected void criarNovoBean() {

	}

	@Override
	protected void remover(List<Serializable> ids) {

	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		remover(ids);
	}

	public List<RequisicaoDetalhe> buscarRequisicaoProdutos() {
		return requisicaoDetalheDao.getAll(RequisicaoDetalhe.class);
	}

	public List<Fornecedor> buscarFornecedores() {
		return fornecedorDao.getAll(Fornecedor.class);
	}

	public FornecedorCotacao novoFornecedorCotacao() {
		FornecedorCotacao fornecedorCotacao = new FornecedorCotacao();
		currentBean.addCompraFornecedorCotacao(fornecedorCotacao);

		return fornecedorCotacao;
	}

	public void removerFornecedorCotacaos(List<FornecedorCotacao> values) {
		for (FornecedorCotacao fornecedorCotacao : values) {
			currentBean.removeCompraFornecedorCotacao(fornecedorCotacao);
		}

		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "confirmaCotacaoForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public Cotacao getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}