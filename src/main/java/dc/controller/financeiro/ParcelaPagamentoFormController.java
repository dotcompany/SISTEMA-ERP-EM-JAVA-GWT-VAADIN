package dc.controller.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.ParcelaPagamento;
import dc.entidade.financeiro.ParcelaPagar;
import dc.entidade.financeiro.StatusParcela;
import dc.servicos.dao.financeiro.ParcelaPagamentoDAO;
import dc.servicos.dao.financeiro.ParcelaPagarDAO;
import dc.servicos.dao.financeiro.StatusParcelaDAO;
import dc.visao.financeiro.ParcelaPagamentoFormView;
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

	private ParcelaPagamento currentBean;

	private ParcelaPagar parcelaPagar;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ParcelaPagamento();
	}

	@Override
	protected void initSubView() {
		subView = new ParcelaPagamentoFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {

		parcelaPagar = parcelaPagarDAO.find(id);

		currentBean = new ParcelaPagamento();

		currentBean.setParcelaPagar(parcelaPagar);
		currentBean.setContaCaixa(parcelaPagar.getContaCaixa());
		currentBean.setValorPago(parcelaPagar.getValor());

		subView.preencheForm(currentBean);
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
		return "Ponto Abono";
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
				// valorJuro = valor * ((taxaJuro / 30) / 100) * diasAtraso
				pagamento.setValorJuro(pagamento.getParcelaPagar().getValor()
						.multiply(pagamento.getTaxaJuro().divide(BigDecimal.valueOf(30), RoundingMode.HALF_DOWN))
						.divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(diasAtraso)));
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

		pagamento.setValorPago(pagamento.getParcelaPagar().getValor().add(valorJuro).add(valorMulta).subtract(valorDesconto));
	}

	public void efetuaPagamento() {

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

	@Transactional
	private void salvaPagamento() throws Exception {
		ParcelaPagamento parcelaPagamento = currentBean;
		String tipoBaixa = subView.getCbTipoBaixa().getValue().toString();

		if (parcelaPagamento.getChequeEmitido() != null) {
			// session.save(parcelaPagamento.getFinChequeEmitido());
			// ChequeVO cheque =
			// parcelaPagamento.getFinChequeEmitido().getCheque();
			// cheque.setStatusCheque("U");
			// session.update(cheque);
		}

		parcelaPagamentoDAO.save(parcelaPagamento);

		StatusParcela statusParcela = null;
		if (tipoBaixa.equals("T")) {
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
	}

	public void excluiPagamento() {

		subView.preencheBean(currentBean);
		// pegar o objeto da grid

		StatusParcela statusParcela = statusParcelaDAO.findBySituacao("01");

		if (statusParcela == null) {
			mensagemErro("Status de parcela não cadastrado. Entre em contato com a Software House");
		} else {
			ParcelaPagar parcelaPagar = currentBean.getParcelaPagar();
			parcelaPagar.setStatusParcela(statusParcela);

			parcelaPagarDAO.saveOrUpdate(parcelaPagar);
			parcelaPagamentoDAO.delete(currentBean);

			// finParcelaPagamentoDetalhe.getForm1().setMode(Consts.READONLY);
			// finParcelaPagamentoDetalhe.getForm1().reload();
			mensagemAtencao("Pagamento excluído com sucesso!");

		}

	}
}
