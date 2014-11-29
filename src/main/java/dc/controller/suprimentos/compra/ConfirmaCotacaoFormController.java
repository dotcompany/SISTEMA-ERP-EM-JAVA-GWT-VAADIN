package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.FornecedorListController;
import dc.entidade.geral.FornecedorEntity;
import dc.entidade.suprimentos.compra.CotacaoEntity;
import dc.entidade.suprimentos.compra.CotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.entidade.suprimentos.compra.ReqCotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoDetalheEntity;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.suprimentos.compra.CotacaoDAO;
import dc.servicos.dao.suprimentos.compra.RequisicaoDetalheDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.compra.ConfirmaCotacaoFormView;

@Controller
@Scope("prototype")
public class ConfirmaCotacaoFormController extends CRUDFormController<CotacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConfirmaCotacaoFormView subView;

	@Autowired
	private CotacaoDAO cotacaoDao;

	@Autowired
	private FornecedorDAO fornecedorDao;

	@Autowired
	private RequisicaoDetalheDAO requisicaoDetalheDao;

	private CotacaoEntity currentBean;

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
			this.currentBean.setDescricao(this.subView.getTfDescricao()
					.getValue());
			this.currentBean.setDataCotacao(this.subView.getPdfDataCotacao()
					.getValue());
			// this.subView.getCbFornecedor();
			// this.subView.getTfPrazoEntrega();
			// this.subView.getTfCondicaoPagamento();
			// this.subView.getTfSubtotal();
			// this.subView.getTfTaxaDesconto();
			// this.subView.getTfValorDesconto();
			// this.subView.getTfTotal();

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
		subView.getTfDescricao().setValue(currentBean.getDescricao());
		subView.getPdfDataCotacao().setValue(currentBean.getDataCotacao());

		List<FornecedorCotacaoEntity> fornecedorCotacaos = currentBean
				.getCompraFornecedorCotacaos();
		subView.fillCompraFornecedorCotacoesSubForm(fornecedorCotacaos);

		List<ReqCotacaoDetalheEntity> compraReqCotacaoDetalhes = currentBean
				.getCompraReqCotacaoDetalhes();

		for (ReqCotacaoDetalheEntity requisicaoCotacaoDetalhe : compraReqCotacaoDetalhes) {
			RequisicaoDetalheEntity requisicaoDetalhe = requisicaoCotacaoDetalhe
					.getRequisicaoDetalhe();

			COTACOES: for (FornecedorCotacaoEntity fornecedorCotacao : fornecedorCotacaos) {
				List<CotacaoDetalheEntity> cotacaoDetalhes = fornecedorCotacao
						.getCotacaoDetalhes();

				for (CotacaoDetalheEntity cotacaoDetalhe : cotacaoDetalhes) {
					if (cotacaoDetalhe.getProduto().equals(
							requisicaoDetalhe.getProduto())
							&& cotacaoDetalhe.getQuantidade().equals(
									requisicaoDetalhe.getQuantidade())) {
						continue COTACOES;
					}
				}

				CotacaoDetalheEntity cotacaoDetalhe = new CotacaoDetalheEntity();
				cotacaoDetalhe.setProduto(requisicaoDetalhe.getProduto());
				cotacaoDetalhe.setQuantidade(requisicaoDetalhe.getQuantidade());
				fornecedorCotacao.addCotacaoDetalhe(cotacaoDetalhe);
			}
		}
	}

	@Override
	protected void initSubView() {
		subView = new ConfirmaCotacaoFormView();

		DefaultManyToOneComboModel<FornecedorEntity> fornecedorModel = new DefaultManyToOneComboModel<FornecedorEntity>(
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

	public List<RequisicaoDetalheEntity> buscarRequisicaoProdutos() {
		return requisicaoDetalheDao.getAll(RequisicaoDetalheEntity.class);
	}

	public List<FornecedorEntity> buscarFornecedores() {
		return fornecedorDao.getAll(FornecedorEntity.class);
	}

	public FornecedorCotacaoEntity novoFornecedorCotacao() {
		FornecedorCotacaoEntity fornecedorCotacao = new FornecedorCotacaoEntity();
		currentBean.getCompraFornecedorCotacaos().add(fornecedorCotacao);

		return fornecedorCotacao;
	}

	public void removerFornecedorCotacaos(List<FornecedorCotacaoEntity> values) {
		for (FornecedorCotacaoEntity fornecedorCotacao : values) {
			currentBean.getCompraFornecedorCotacaos().remove(fornecedorCotacao);
		}

		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public CotacaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}