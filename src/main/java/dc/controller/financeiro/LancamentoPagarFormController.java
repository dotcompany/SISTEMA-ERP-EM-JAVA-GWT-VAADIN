package dc.controller.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.LancamentoPagar;
import dc.entidade.financeiro.LctoPagarNtFinanceira;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.financeiro.ParcelaPagar;
import dc.entidade.financeiro.StatusParcela;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.DocumentoOrigemDAO;
import dc.servicos.dao.financeiro.LancamentoPagarDAO;
import dc.servicos.dao.financeiro.NaturezaFinanceiraDAO;
import dc.servicos.dao.financeiro.ParcelaPagarDAO;
import dc.servicos.dao.financeiro.StatusParcelaDAO;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.visao.financeiro.LancamentoPagarFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;

@Controller
@Scope("prototype")
public class LancamentoPagarFormController extends CRUDFormController<LancamentoPagar> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoPagarFormView subView;

	@Autowired
	private LancamentoPagarDAO lancamentoPagarDAO;

	@Autowired
	private ParcelaPagarDAO parcelaPagarDAO;

	private LancamentoPagar currentBean;

	@Autowired
	private ContabilContaDAO contabilcontaDAO;

	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	@Autowired
	private FornecedorDAO fornecedorDAO;

	@Autowired
	private NaturezaFinanceiraDAO naturezaFinanceiraDAO;

	@Autowired
	private DocumentoOrigemDAO documentoOrigemDAO;

	@Autowired
	private StatusParcelaDAO statusParcelaDAO;

	@Override
	protected String getNome() {
		return "Lançamento à Pagar";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);

		StatusParcela statusParcela = statusParcelaDAO.findBySituacao("01");
		if (statusParcela == null) {
			mensagemErro("O status de parcela em aberto não está cadastrado.\nEntre em contato com a Software House.");
		} else {
			for (ParcelaPagar p : currentBean.getParcelasPagar()) {
				p.setStatusParcela(statusParcela);
			}

			try {
				lancamentoPagarDAO.saveOrUpdate(currentBean);
				mensagemSalvoOK();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = lancamentoPagarDAO.find(id);
		preencheCombos();
		subView.preencheForm(currentBean);
	}

	/*
	 * Callback para quando novo foi acionado. Colocar programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new LancamentoPagarFormView(this);

		subView.getBtnGerarParcelas().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					gerarParcelas();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					mensagemErro(e.getMessage());
				}

			}
		});
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new LancamentoPagar();
		preencheCombos();
	}

	private void preencheCombos() {
		subView.preencheComboContaCaixa(contaCaixaDAO.listaTodos());
		subView.preencheComboFornecedores(fornecedorDAO.listarTodos());
		subView.preencheComboDocumentoOrigem(documentoOrigemDAO.listaTodos());
	}

	@Override
	protected void remover(List<Serializable> ids) {
		lancamentoPagarDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		List<ParcelaPagar> parcelasPagar = subView.getParcelasSubForm().getDados();
		List<LctoPagarNtFinanceira> naturezasFinanceiras = subView.getNaturezaFinanceiraSubForm().getDados();

		if (((BigDecimal) subView.getTxValorPagar().getConvertedValue()).compareTo(getTotalParcelaPagar(parcelasPagar)) != 0) {
			adicionarErroDeValidacao(subView.getParcelasSubForm(), "Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
		}
		
		if (((BigDecimal) subView.getTxValorPagar().getConvertedValue()).compareTo(getTotalNaturezaFinanceira(naturezasFinanceiras)) != 0) {
			adicionarErroDeValidacao(subView.getNaturezaFinanceiraSubForm(), "Os valores informados nas naturezas financeiras não batem com o valor a pagar.");
			//valido = false;
		}

	
		return valido;
	}

	private BigDecimal getTotalParcelaPagar(List<ParcelaPagar> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValor());
		}
		return total;
	}

	private BigDecimal getTotalNaturezaFinanceira(List<LctoPagarNtFinanceira> naturezasFinanceiras) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < naturezasFinanceiras.size(); i++) {
			total = total.add(naturezasFinanceiras.get(i).getValor());
		}
		return total;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {

		return "lancamentoPagarForm";
	}

	@Override
	protected boolean isFullSized() {

		return true;
	}

	public void gerarParcelas() throws Exception {
		final ContaCaixa contaCaixa = (ContaCaixa) subView.getCbContaCaixa().getValue();
		if (contaCaixa == null || contaCaixa.getId() == null) {
			throw new Exception("É necessário informar a conta caixa para previsão das parcelas.");
		}
		final List<ParcelaPagar> parcelasPagar = new ArrayList<ParcelaPagar>();
		List<ParcelaPagar> dados = subView.getParcelasSubForm().getDados();
		if (dados != null) {
			parcelasPagar.addAll(subView.getParcelasSubForm().getDados());
		}

		if (parcelasPagar != null && !parcelasPagar.isEmpty()) {
			ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
					"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não", new ConfirmDialog.Listener() {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public void onClose(ConfirmDialog dialog) {
							if (dialog.isConfirmed()) {
								excluiParcelas(parcelasPagar);
								geraParcelas(contaCaixa, parcelasPagar);
							}
						}
					});
		} else {
			geraParcelas(contaCaixa, parcelasPagar);
		}

	}

	private void geraParcelas(ContaCaixa contaCaixa, final List<ParcelaPagar> parcelasPagar) {
		subView.getParcelasSubForm().removeAllItems();

		subView.preencheBean(currentBean);
		LancamentoPagar lancamentoPagar = currentBean;
		ParcelaPagar parcelaPagar;
		Date dataEmissão = new Date();
		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(lancamentoPagar.getPrimeiroVencimento());
		BigDecimal valorParcela = lancamentoPagar.getValorAPagar().divide(BigDecimal.valueOf(lancamentoPagar.getQuantidadeParcela()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		for (int i = 0; i < lancamentoPagar.getQuantidadeParcela(); i++) {
			parcelaPagar = new ParcelaPagar();
			parcelaPagar.setContaCaixa(contaCaixa);
			parcelaPagar.setNumeroParcela(i + 1);
			parcelaPagar.setDataEmissao(dataEmissão);
			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, lancamentoPagar.getIntervaloEntreParcelas());
			}
			parcelaPagar.setDataVencimento(primeiroVencimento.getTime());
			parcelaPagar.setSofreRetencao(lancamentoPagar.getFornecedor().getSofreRetencao());
			parcelaPagar.setValor(valorParcela);

			somaParcelas = somaParcelas.add(valorParcela);
			if (i == (lancamentoPagar.getQuantidadeParcela() - 1)) {
				residuo = lancamentoPagar.getValorAPagar().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaPagar.setValor(valorParcela);
			}

			parcelasPagar.add(parcelaPagar);
		}

		for (ParcelaPagar p : parcelasPagar) {
			novoParcelaPagar(p);
		}

		subView.getParcelasSubForm().fillWith(parcelasPagar);
	}

	private void excluiParcelas(List<ParcelaPagar> parcelasPagar) {
		List<ParcelaPagar> persistentObjects = subView.getParcelasSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
			parcelaPagarDAO.delete(persistentObjects.get(i));
		}
		parcelasPagar.clear();
	}

	public ParcelaPagar novoParcelaPagar() {
		ParcelaPagar parcela = new ParcelaPagar();
		return novoParcelaPagar(parcela);
	}

	public ParcelaPagar novoParcelaPagar(ParcelaPagar parcela) {

		currentBean.addParcelaPagar(parcela);

		return parcela;
	}

	public void removerParcelaPagar(List<ParcelaPagar> values) {
		for (ParcelaPagar value : values) {
			currentBean.removeParcelaPagar(value);
		}

	}

	public LctoPagarNtFinanceira novoLctoPagarNtFinanceira() {
		LctoPagarNtFinanceira lctoPagarNtFinanceira = currentBean.addLctoPagarNtFinanceira();
		return lctoPagarNtFinanceira;
	}

	public void removerLctoPagarNtFinanceira(List<LctoPagarNtFinanceira> values) {
		for (LctoPagarNtFinanceira value : values) {
			currentBean.removeLctoPagarNtFinanceira(value);
		}

	}

	public List<NaturezaFinanceira> getNaturezasFinanceiras() {
		return naturezaFinanceiraDAO.listaTodos();
	}

}
