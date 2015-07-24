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

import dc.control.enums.TipoBaixaEn;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.ParcelaReceber;
import dc.entidade.financeiro.ParcelaRecebimento;
import dc.entidade.financeiro.StatusParcela;
import dc.entidade.financeiro.TipoRecebimento;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.ParcelaReceberDAO;
import dc.servicos.dao.financeiro.ParcelaRecebimentoDAO;
import dc.servicos.dao.financeiro.StatusParcelaDAO;
import dc.servicos.dao.financeiro.TipoRecebimentoDAO;
import dc.visao.financeiro.ParcelaRecebimentoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ParcelaRecebimentoFormController extends CRUDFormController<ParcelaReceber> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParcelaRecebimentoFormView subView;

	@Autowired
	private ParcelaReceberDAO parcelaReceberDAO;
	@Autowired
	private ParcelaRecebimentoDAO parcelaRecebimentoDAO;
	@Autowired
	private StatusParcelaDAO statusParcelaDAO;
	@Autowired
	private TipoRecebimentoDAO tipoRecebimentoDAO;
	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	private ParcelaRecebimento currentBean;

	private ParcelaReceber parcelaReceber;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		subView.getCbTipoRecebimento();
		subView.getCbContaCaixa();
		subView.getDtDataRecebimento();

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ParcelaRecebimento();
		preencheCombos();
	}

	@Override
	protected void initSubView() {
		subView = new ParcelaRecebimentoFormView(this);

		subView.getBtnEfetuaRecebimento().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				efetuaRecebimento();
			}
		});

		subView.getBtnExcluiRecebimento().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				excluiRecebimento();
			}
		});

		subView.getCbTipoBaixa().addBlurListener(new BlurListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void blur(BlurEvent event) {

				TipoBaixaEn tipoBaixa = (TipoBaixaEn) subView.getCbTipoBaixa().getValue();

				switch (tipoBaixa) {
				case P:
					subView.getTxValorRecebido().setEnabled(true);
					break;
				case T:
					subView.getTxValorRecebido().setEnabled(false);
					new CalculaTotalRecebidoBlurListener();
					break;

				default:
					break;
				}

			}
		});

		subView.getTxTaxaJuro().addBlurListener(new CalculaTotalRecebidoBlurListener());
		subView.getTxTaxaMulta().addBlurListener(new CalculaTotalRecebidoBlurListener());
		subView.getTxTaxaDesconto().addBlurListener(new CalculaTotalRecebidoBlurListener());
		subView.getDtDataRecebimento().addBlurListener(new CalculaTotalRecebidoBlurListener());

		subView.getTxValorJuro().setEnabled(false);
		subView.getTxValorMulta().setEnabled(false);
		subView.getTxValorDesconto().setEnabled(false);
		subView.getTxValorRecebido().setEnabled(false);
		subView.getDtDataVencimento().setEnabled(false);
		subView.getTxValorReceber().setEnabled(false);
	}

	@Override
	protected void carregar(Serializable id) {

		parcelaReceber = parcelaReceberDAO.find(id);

		currentBean = new ParcelaRecebimento();

		currentBean.setParcelaReceber(parcelaReceber);
		currentBean.setContaCaixa(parcelaReceber.getContaCaixa());
		currentBean.setValorRecebido(parcelaReceber.getValor());

		preencheCombos();
		subView.preencheForm(currentBean);
		subView.fillParcelaRecebimentosSubForm(parcelaRecebimentoDAO.buscaPorParcelaReceber(parcelaReceber));
		new CalculaTotalRecebidoBlurListener();
	}

	private void preencheCombos() {
		DefaultManyToOneComboModel<ContaCaixa> model1 = new DefaultManyToOneComboModel<ContaCaixa>(ContaCaixaListController.class,
				this.contaCaixaDAO, super.getMainController());

		this.subView.getCbContaCaixa().setModel(model1);

		DefaultManyToOneComboModel<TipoRecebimento> model2 = new DefaultManyToOneComboModel<TipoRecebimento>(TipoRecebimentoListController.class,
				this.tipoRecebimentoDAO, super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};

		this.subView.getCbTipoRecebimento().setModel(model2);
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			parcelaRecebimentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean.getParcelaReceber());
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
		return "Recebimento Parcela";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		parcelaRecebimentoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "parcelaRecebimentoFormController";
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/*public void calculaTotalRecebido() {
		subView.preencheBean(currentBean);
		ParcelaRecebimento pagamento = currentBean;
		BigDecimal valorJuro = BigDecimal.ZERO;
		BigDecimal valorMulta = BigDecimal.ZERO;
		BigDecimal valorDesconto = BigDecimal.ZERO;
		if (pagamento.getTaxaJuro() != null && pagamento.getDataRecebimento() != null) {
			Calendar dataRecebimento = Calendar.getInstance();
			dataRecebimento.setTime(pagamento.getDataRecebimento());
			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTime(pagamento.getParcelaReceber().getDataVencimento());
			if (dataVencimento.before(dataRecebimento)) {
				long diasAtraso = (dataRecebimento.getTimeInMillis() - dataVencimento.getTimeInMillis()) / 86400000l;

				pagamento.setValorJuro(calculaJuros(pagamento, diasAtraso));
				valorJuro = pagamento.getValorJuro();
			}
		}
		pagamento.setValorJuro(valorJuro);

		if (pagamento.getTaxaMulta() != null) {
			
			//subView.getTxValorMulta().setConvertedValue(pagamento.getParcelaReceber().getValor().multiply(pagamento.getTaxaMulta()).divide(
			//		BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
			//valorMulta = pagamento.getValorMulta();
			
			pagamento.setValorMulta(((pagamento.getParcelaReceber().getValor()).multiply(pagamento.getTaxaMulta()))
					.divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
			valorMulta = pagamento.getValorMulta();
		} else {
			pagamento.setValorMulta(valorMulta);
		}

		if (pagamento.getTaxaDesconto() != null) {
			pagamento.setValorDesconto(pagamento.getParcelaReceber().getValor().multiply(pagamento.getTaxaDesconto())
					.divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
			valorDesconto = pagamento.getValorDesconto();
		} else {
			pagamento.setValorDesconto(valorDesconto);
		}

		//pagamento.setValorRecebido(pagamento.getValorRecebido().add(valorJuro).add(valorMulta).subtract(valorDesconto));
		pagamento.setValorRecebido(pagamento.getValorRecebido().add(pagamento.getValorJuro()).add(pagamento.getValorMulta()).subtract(pagamento.getValorDesconto()));

		subView.preencheForm(currentBean);
	}*/

	private BigDecimal calculaJuros(ParcelaRecebimento pagamento, long diasAtraso) {
		// valorJuro = valor * ((taxaJuro / 30) / 100) * diasAtraso
		BigDecimal juros = pagamento.getParcelaReceber().getValor().multiply(pagamento.getTaxaJuro().setScale(5).divide(BigDecimal.valueOf(30.0), 5))
				.divide(BigDecimal.valueOf(100), 5).multiply(BigDecimal.valueOf(diasAtraso));

		return juros.setScale(2, RoundingMode.HALF_DOWN);
	}

	public void efetuaRecebimento() {

		if (validaSalvar()) {
			new CalculaTotalRecebidoBlurListener();
			ParcelaRecebimento pagamento = currentBean;
			pagamento.setChequeRecebido(null);
			if (pagamento.getTipoRecebimento().getTipo().equals("02")) {
				// FinSelecionaChequeGrid chequeGrid = new
				// FinSelecionaChequeGrid(MDIFrame.getInstance(), true, true);
				// chequeGrid.setVisible(true);
				// if (!chequeGrid.cancelado) {
				// pagamento.setFinChequeEmitido(chequeGrid.getChequeEmitido());
				// } else {
				// JOptionPane.showMessageDialog(finParcelaRecebimentoDetalhe,
				// "É necessário informar um cheque para este tipo de pagamento.",
				// "Informação do Sistema", JOptionPane.ERROR_MESSAGE);
				// return;
				// }
			}

			try {
				salvaRecebimento();
			} catch (Exception e) {
				mensagemErro(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	@Transactional
	private void salvaRecebimento() throws Exception {
		ParcelaRecebimento parcelaRecebimento = currentBean;
		String tipoBaixa = ((TipoBaixaEn) subView.getCbTipoBaixa().getValue()).getKey();

		if (parcelaRecebimento.getChequeRecebido() != null) {
			// session.save(parcelaRecebimento.getFinChequeEmitido());
			// ChequeVO cheque =
			// parcelaRecebimento.getFinChequeEmitido().getCheque();
			// cheque.setStatusCheque("U");
			// session.update(cheque);
		}

		parcelaRecebimentoDAO.save(parcelaRecebimento);

		StatusParcela statusParcela = null;
		if ("T".equalsIgnoreCase(tipoBaixa)) {
			statusParcela = statusParcelaDAO.findBySituacao("02");
		} else {
			statusParcela = statusParcelaDAO.findBySituacao("03");
		}

		if (statusParcela == null) {
			throw new Exception("Status de parcela não cadastrado. Entre em contato com a Software House");
		}

		ParcelaReceber parcelaReceber = parcelaRecebimento.getParcelaReceber();
		parcelaReceber.setStatusParcela(statusParcela);
		parcelaReceberDAO.saveOrUpdate(parcelaReceber);

		List<ParcelaRecebimento> dados = subView.getRecebimentosSubForm().getDados();
		dados.add(parcelaRecebimento);
		subView.getRecebimentosSubForm().removeAllItems();
		subView.fillParcelaRecebimentosSubForm(dados);
	}

	public void excluiRecebimento() {
		Collection<ParcelaRecebimento> selectedItens = subView.getRecebimentosSubForm().getSelectedItens();

		Collection<ParcelaRecebimento> deletedItens = new ArrayList<ParcelaRecebimento>();

		for (ParcelaRecebimento parcelaRecebimento : selectedItens) {
			StatusParcela statusParcela = statusParcelaDAO.findBySituacao("01");

			if (statusParcela == null) {
				mensagemErro("Status de parcela não cadastrado. Entre em contato com a Software House");
			} else {
				ParcelaReceber parcelaReceber = parcelaRecebimento.getParcelaReceber();
				parcelaReceber.setStatusParcela(statusParcela);

				parcelaReceberDAO.saveOrUpdate(parcelaReceber);
				parcelaRecebimentoDAO.delete(parcelaRecebimento);
				deletedItens.add(parcelaRecebimento);
			}
		}

		if (!deletedItens.isEmpty()) {
			mensagemAtencao("Recebimento excluído com sucesso!");
			subView.getRecebimentosSubForm().removeItens(deletedItens);
		}
	}

	private class CalculaTotalRecebidoBlurListener implements BlurListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void blur(BlurEvent event) {
				subView.preencheBean(currentBean);
				ParcelaRecebimento pagamento = currentBean;
				BigDecimal valorJuro = BigDecimal.ZERO;
				BigDecimal valorMulta = BigDecimal.ZERO;
				BigDecimal valorDesconto = BigDecimal.ZERO;
				if (pagamento.getTaxaJuro() != null && pagamento.getDataRecebimento() != null) {
					Calendar dataRecebimento = Calendar.getInstance();
					dataRecebimento.setTime(pagamento.getDataRecebimento());
					Calendar dataVencimento = Calendar.getInstance();
					dataVencimento.setTime(pagamento.getParcelaReceber().getDataVencimento());
					if (dataVencimento.before(dataRecebimento)) {
						long diasAtraso = (dataRecebimento.getTimeInMillis() - dataVencimento.getTimeInMillis()) / 86400000l;

						pagamento.setValorJuro(calculaJuros(pagamento, diasAtraso));
						valorJuro = pagamento.getValorJuro();
					}
				}
				pagamento.setValorJuro(valorJuro);

				if (pagamento.getTaxaMulta() != null) {
					
					//subView.getTxValorMulta().setConvertedValue(pagamento.getParcelaReceber().getValor().multiply(pagamento.getTaxaMulta()).divide(
					//		BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
					//valorMulta = pagamento.getValorMulta();
					
					pagamento.setValorMulta(((pagamento.getParcelaReceber().getValor()).multiply(pagamento.getTaxaMulta()))
							.divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
					valorMulta = pagamento.getValorMulta();
				} else {
					pagamento.setValorMulta(valorMulta);
				}

				if (pagamento.getTaxaDesconto() != null) {
					pagamento.setValorDesconto(pagamento.getParcelaReceber().getValor().multiply(pagamento.getTaxaDesconto())
							.divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
					valorDesconto = pagamento.getValorDesconto();
				} else {
					pagamento.setValorDesconto(valorDesconto);
				}

				//pagamento.setValorRecebido(pagamento.getValorRecebido().add(valorJuro).add(valorMulta).subtract(valorDesconto));
				
				////// ERRO AKI abaixo ///////////
				pagamento.setValorRecebido(pagamento.getValorRecebido().add(pagamento.getValorJuro()).add(pagamento.getValorMulta()).subtract(pagamento.getValorDesconto()));

				subView.preencheForm(currentBean);
			}
	}

	@Override
	public ParcelaReceber getModelBean() {
		return parcelaReceber;
	}
}