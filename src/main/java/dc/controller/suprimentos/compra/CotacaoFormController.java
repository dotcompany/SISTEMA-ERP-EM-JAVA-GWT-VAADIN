package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.geral.Fornecedor;
import dc.entidade.suprimentos.FornecedorCotacao;
import dc.entidade.suprimentos.RequisicaoCotacaoDetalhe;
import dc.entidade.suprimentos.RequisicaoDetalhe;
import dc.entidade.suprimentos.compra.Cotacao;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.suprimentos.RequisicaoDetalheDAO;
import dc.servicos.dao.suprimentos.compra.CotacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.compra.CotacaoFormView;

@Controller
@Scope("prototype")
public class CotacaoFormController extends CRUDFormController<Cotacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CotacaoFormView subView;

	@Autowired
	private CotacaoDAO cotacaoDao;

	@Autowired
	private FornecedorDAO fornecedorDao;

	@Autowired
	private RequisicaoDetalheDAO requisicaoDetalheDao;

	private Cotacao currentBean;

	@Override
	protected String getNome() {
		return "cotação de Compra";
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
			currentBean.setSituacao("A");
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
		subView.getLblId().setValue(String.valueOf(currentBean.getId()));
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getCalDataCotacao().setValue(currentBean.getDataCotacao());

		subView.fillCompraFornecedorCotacoesSubForm(currentBean
				.getCompraFornecedorCotacaos());
		subView.fillReqCotacaoDetalhesSubForm(currentBean
				.getCompraReqCotacaoDetalhes());
	}

	@Override
	protected void initSubView() {
		subView = new CotacaoFormView(this);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Cotacao();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		cotacaoDao.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void quandoNovo() {
		subView.fillCompraFornecedorCotacoesSubForm(currentBean
				.getCompraFornecedorCotacaos());
		subView.fillReqCotacaoDetalhesSubForm(currentBean
				.getCompraReqCotacaoDetalhes());
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		remover(ids);
	}

	public List<RequisicaoDetalhe> buscarRequisicaoProdutos() {
		return requisicaoDetalheDao.getAll(RequisicaoDetalhe.class);
	}

	public RequisicaoCotacaoDetalhe novoRequisicaoCotacaoDetalhe() {
		RequisicaoCotacaoDetalhe cotacaoDetalhe = new RequisicaoCotacaoDetalhe();
		currentBean.getCompraReqCotacaoDetalhes().add(cotacaoDetalhe);

		return cotacaoDetalhe;
	}

	public void removerRequisicaoCotacaoDetalhes(
			List<RequisicaoCotacaoDetalhe> values) {
		for (RequisicaoCotacaoDetalhe requisicaoCotacaoDetalhe : values) {
			currentBean.getCompraReqCotacaoDetalhes().remove(
					requisicaoCotacaoDetalhe);
		}

		mensagemRemovidoOK();
	}

	public List<Fornecedor> buscarFornecedores() {
		return fornecedorDao.getAll(Fornecedor.class);
	}

	public FornecedorCotacao novoFornecedorCotacao() {
		FornecedorCotacao fornecedorCotacao = new FornecedorCotacao();
		currentBean.getCompraFornecedorCotacaos().add(fornecedorCotacao);

		return fornecedorCotacao;
	}

	public void removerFornecedorCotacaos(List<FornecedorCotacao> values) {
		for (FornecedorCotacao fornecedorCotacao : values) {
			currentBean.getCompraFornecedorCotacaos().remove(fornecedorCotacao);
		}

		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClasseUtil.getUrl(this);
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