package dc.controller.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.ParcelaPagamento;
import dc.entidade.financeiro.ParcelaPagar;
import dc.entidade.financeiro.StatusParcela;
import dc.entidade.financeiro.TipoPagamento;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.ParcelaPagamentoDAO;
import dc.servicos.dao.financeiro.ParcelaPagarDAO;
import dc.servicos.dao.financeiro.StatusParcelaDAO;
import dc.servicos.dao.financeiro.TipoPagamentoDAO;
import dc.visao.financeiro.ParcelaPagamentoFormView;
import dc.visao.financeiro.ParcelaPagamentoFormView.TipoBaixa;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ParcelaPagamentoFormController extends CRUDFormController<ParcelaPagar> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParcelaPagamentoFormView subView;

	@Autowired
	private ParcelaPagarDAO parcelaPagarDAO;
	@Autowired
	private ParcelaPagamentoDAO parcelaPagamentoDAO;
	@Autowired
	private StatusParcelaDAO statusParcelaDAO;
	@Autowired
	private TipoPagamentoDAO tipoPagamentoDAO;
	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	private ParcelaPagamento currentBean;

	private ParcelaPagar parcelaPagar;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		subView.getCbTipoPagamento();
		subView.getCbContaCaixa();
		subView.getDtDataPagamento();

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ParcelaPagamento();
		preencheCombos();
	}

	@Override
	protected void initSubView() {
		subView = new ParcelaPagamentoFormView(this);

		subView.getBtnEfetuaPagamento().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				efetuaPagamento();
			}
		});

		subView.getBtnExcluiPagamento().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				excluiPagamento();
			}
		});

		subView.getCbTipoBaixa().addBlurListener(new BlurListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void blur(BlurEvent event) {

				TipoBaixa tipoBaixa = (TipoBaixa) subView.getCbTipoBaixa().getValue();

				switch (tipoBaixa) {
				case PARCIAL:
					subView.getTxValorPago().setEnabled(true);
					break;
				case TOTAL:
					subView.getTxValorPago().setEnabled(false);
					calculaTotalPago();

					break;

				default:
					break;
				}

			}
		});

		subView.getTxTaxaJuro().addBlurListener(new CalculaTotalPagoBlurListener());
		subView.getTxTaxaMulta().addBlurListener(new CalculaTotalPagoBlurListener());
		subView.getTxTaxaDesconto().addBlurListener(new CalculaTotalPagoBlurListener());
		subView.getDtDataPagamento().addBlurListener(new CalculaTotalPagoBlurListener());

		subView.getTxValorJuro().setEnabled(false);
		subView.getTxValorMulta().setEnabled(false);
		subView.getTxValorDesconto().setEnabled(false);
		subView.getTxValorPago().setEnabled(false);
		subView.getDtDataVencimento().setEnabled(false);
		subView.getTxValorPagar().setEnabled(false);
		
		preencheCombos();
	}

	@Override
	protected void carregar(Serializable id) {

		parcelaPagar = parcelaPagarDAO.find(id);

		currentBean = new ParcelaPagamento();

		currentBean.setParcelaPagar(parcelaPagar);
		currentBean.setContaCaixa(parcelaPagar.getContaCaixa());
		currentBean.setValorPago(parcelaPagar.getValor());

		preencheCombos();
		subView.preencheForm(currentBean);
		subView.fillParcelaPagamentosSubForm(parcelaPagamentoDAO.buscaPorParcelaPagar(parcelaPagar));
		calculaTotalPago();
	}

	private void preencheCombos() {
		subView.preencheComboTipoBaixa();
		DefaultManyToOneComboModel<ContaCaixa> model1 = new DefaultManyToOneComboModel<ContaCaixa>(ContaCaixaListController.class,
				this.contaCaixaDAO, super.getMainController());

		this.subView.getCbContaCaixa().setModel(model1);

		DefaultManyToOneComboModel<TipoPagamento> model2 = new DefaultManyToOneComboModel<TipoPagamento>(TipoPagamentoListController.class,
				this.tipoPagamentoDAO, super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};

		this.subView.getCbTipoPagamento().setModel(model2);
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			parcelaPagamentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean.getParcelaPagar());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Pagamento Parcela";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		parcelaPagamentoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "parcelaPagamentoFormController";
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	public void calculaTotalPago() {
		subView.preencheBean(currentBean);
		ParcelaPagamento pagamento = currentBean;
		BigDecimal valorJuro = BigDecimal.ZERO;
		BigDecimal valorMulta = BigDecimal.ZERO;
		BigDecimal valorDesconto = BigDecimal.ZERO;
		if (pagamento.getTaxaJuro() != null && pagamento.getDataPagamento() != null) {
			Calendar dataPagamento = Calendar.getInstance();
			dataPagamento.setTime(pagamento.getDataPagamento());
			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTime(pagamento.getParcelaPagar().getDataVencimento());
			if (dataVencimento.before(dataPagamento)) {
				long diasAtraso = (dataPagamento.getTimeInMillis() - dataVencimento.getTimeInMillis()) / 86400000l;

				pagamento.setValorJuro(calculaJuros(pagamento, diasAtraso));
				valorJuro = pagamento.getValorJuro();
			}
		}
		pagamento.setValorJuro(valorJuro);

		if (pagamento.getTaxaMulta() != null) {
			pagamento.setValorMulta(pagamento.getParcelaPagar().getValor().multiply(pagamento.getTaxaMulta())
					.divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
			valorMulta = pagamento.getValorMulta();
		} else {
			pagamento.setValorMulta(valorMulta);
		}

		if (pagamento.getTaxaDesconto() != null) {
			pagamento.setValorDesconto(pagamento.getParcelaPagar().getValor().multiply(pagamento.getTaxaDesconto())
					.divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
			valorDesconto = pagamento.getValorDesconto();
		} else {
			pagamento.setValorDesconto(valorDesconto);
		}

		pagamento.setValorPago(pagamento.getValorPago().add(valorJuro).add(valorMulta).subtract(valorDesconto));

		subView.preencheForm(currentBean);
	}

	private BigDecimal calculaJuros(ParcelaPagamento pagamento, long diasAtraso) {
		// valorJuro = valor * ((taxaJuro / 30) / 100) * diasAtraso
		BigDecimal juros = pagamento.getParcelaPagar().getValor().multiply(pagamento.getTaxaJuro().setScale(5).divide(BigDecimal.valueOf(30.0), 5))
				.divide(BigDecimal.valueOf(100), 5).multiply(BigDecimal.valueOf(diasAtraso));

		return juros.setScale(2, RoundingMode.HALF_DOWN);
	}

	public void efetuaPagamento() {

		if (validaSalvar()) {
			calculaTotalPago();
			ParcelaPagamento pagamento = currentBean;
			pagamento.setChequeEmitido(null);
			if (pagamento.getTipoPagamento().getTipo().equals("02")) {
				// FinSelecionaChequeGrid chequeGrid = new
				// FinSelecionaChequeGrid(MDIFrame.getInstance(), true, true);
				// chequeGrid.setVisible(true);
				// if (!chequeGrid.cancelado) {
				// pagamento.setFinChequeEmitido(chequeGrid.getChequeEmitido());
				// } else {
				// JOptionPane.showMessageDialog(finParcelaPagamentoDetalhe,
				// "É necessário informar um cheque para este tipo de pagamento.",
				// "Informação do Sistema", JOptionPane.ERROR_MESSAGE);
				// return;
				// }
			}

			try {
				salvaPagamento();
			} catch (Exception e) {
				mensagemErro(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	@Transactional
	private void salvaPagamento() throws Exception {
		ParcelaPagamento parcelaPagamento = currentBean;
		String tipoBaixa = ((TipoBaixa) subView.getCbTipoBaixa().getValue()).getCodigo();
		if (parcelaPagamento.getChequeEmitido() != null) {
			// session.save(parcelaPagamento.getFinChequeEmitido());
			// ChequeVO cheque =
			// parcelaPagamento.getFinChequeEmitido().getCheque();
			// cheque.setStatusCheque("U");
			// session.update(cheque);
		}

		parcelaPagamentoDAO.save(parcelaPagamento);

		StatusParcela statusParcela = null;
		if ("T".equalsIgnoreCase(tipoBaixa)) {
			statusParcela = statusParcelaDAO.findBySituacao("02");
		} else {
			statusParcela = statusParcelaDAO.findBySituacao("03");
		}

		if (statusParcela == null) {
			throw new Exception("Status de parcela não cadastrado. Entre em contato com a Software House");
		}

		ParcelaPagar parcelaPagar = parcelaPagamento.getParcelaPagar();
		parcelaPagar.setStatusParcela(statusParcela);
		parcelaPagarDAO.saveOrUpdate(parcelaPagar);

		List<ParcelaPagamento> dados = subView.getPagamentosSubForm().getDados();
		dados.add(parcelaPagamento);
		subView.getPagamentosSubForm().removeAllItems();
		subView.fillParcelaPagamentosSubForm(dados);
	}

	public void excluiPagamento() {
		Collection<ParcelaPagamento> selectedItens = subView.getPagamentosSubForm().getSelectedItens();

		Collection<ParcelaPagamento> deletedItens = new ArrayList<ParcelaPagamento>();

		for (ParcelaPagamento parcelaPagamento : selectedItens) {
			StatusParcela statusParcela = statusParcelaDAO.findBySituacao("01");

			if (statusParcela == null) {
				mensagemErro("Status de parcela não cadastrado. Entre em contato com a Software House");
			} else {
				ParcelaPagar parcelaPagar = parcelaPagamento.getParcelaPagar();
				parcelaPagar.setStatusParcela(statusParcela);

				parcelaPagarDAO.saveOrUpdate(parcelaPagar);
				parcelaPagamentoDAO.delete(parcelaPagamento);
				deletedItens.add(parcelaPagamento);
			}
		}

		if (!deletedItens.isEmpty()) {
			mensagemAtencao("Pagamento excluído com sucesso!");
			subView.getPagamentosSubForm().removeItens(deletedItens);
		}
	}

	private class CalculaTotalPagoBlurListener implements BlurListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void blur(BlurEvent event) {
			calculaTotalPago();
		}
	}
}