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

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.controller.geral.FornecedorListController;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.DocumentoOrigem;
import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.financeiro.LctoReceberNtFinanceira;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.financeiro.ParcelaReceber;
import dc.entidade.financeiro.StatusParcela;
import dc.entidade.geral.Fornecedor;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.DocumentoOrigemDAO;
import dc.servicos.dao.financeiro.LancamentoReceberDAO;
import dc.servicos.dao.financeiro.NaturezaFinanceiraDAO;
import dc.servicos.dao.financeiro.ParcelaReceberDAO;
import dc.servicos.dao.financeiro.StatusParcelaDAO;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.LancamentoReceberFormView;
import dc.visao.financeiro.LancamentoReceberFormView.TipoVencimento;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class LancamentoReceberFormController extends CRUDFormController<LancamentoReceber> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoReceberFormView subView;

	@Autowired
	private LancamentoReceberDAO lancamentoReceberDAO;

	@Autowired
	private ParcelaReceberDAO parcelaReceberDAO;

	private LancamentoReceber currentBean;

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
		return "Lançamento à Receber";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);

		boolean valido = true;

		List<ParcelaReceber> parcelasReceber = subView.getParcelasSubForm().getDados();
		List<LctoReceberNtFinanceira> naturezasFinanceiras = subView.getNaturezaFinanceiraSubForm().getDados();

		// if (((BigDecimal)
		// subView.getTxValorReceber().getConvertedValue()).compareTo(getTotalParcelaReceber(parcelasReceber))
		// != 0) {
		// adicionarErroDeValidacao(subView.getParcelasSubForm(),
		// "Os valores informados nas parcelas não batem com o valor a pagar.");
		// valido = false;
		// mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		// }

		// if (((BigDecimal)
		// subView.getTxValorReceber().getConvertedValue()).compareTo(getTotalNaturezaFinanceira(naturezasFinanceiras))
		// != 0) {
		// adicionarErroDeValidacao(subView.getNaturezaFinanceiraSubForm(),
		// "Os valores informados nas naturezas financeiras não batem com o valor a pagar.");
		// valido = false;

		// mensagemErro("Os valores informados nas naturezas financeiras não batem com o valor a pagar.");
		// }

		if (valido) {

			setIntervaloParcelaByTipoVencimento();

			StatusParcela statusParcela = statusParcelaDAO.findBySituacao("01");
			if (statusParcela == null) {
				mensagemErro("O status de parcela em aberto não está cadastrado.\nEntre em contato com a Software House.");
			} else {
				for (ParcelaReceber p : currentBean.getParcelasReceber()) {
					p.setStatusParcela(statusParcela);
				}

				try {
					currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
					lancamentoReceberDAO.saveOrUpdate(currentBean);
					notifiyFrameworkSaveOK(this.currentBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void setIntervaloParcelaByTipoVencimento() {
		if (TipoVencimento.MENSAL.equals(subView.getCbTipoVencimento().getValue())) {
			currentBean.setIntervaloEntreParcelas(30);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = lancamentoReceberDAO.find(id);
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
		subView = new LancamentoReceberFormView(this);

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

		subView.getDtLancamento().setValue(new Date());
		subView.getTxIntervaloParcela().setEnabled(false);

		subView.getCbTipoVencimento().addValueChangeListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				TipoVencimento tipoVencimento = (TipoVencimento) subView.getCbTipoVencimento().getValue();
				if (TipoVencimento.MENSAL.equals(tipoVencimento)) {
					subView.getTxIntervaloParcela().setEnabled(false);
					subView.getTxIntervaloParcela().setValue(null);
					currentBean.setIntervaloEntreParcelas(30);
				} else {
					subView.getTxIntervaloParcela().setEnabled(true);
					currentBean.setIntervaloEntreParcelas(null);
				}

			}
		});

		preencheCombos();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new LancamentoReceber();
	}

	private void preencheCombos() {

		DefaultManyToOneComboModel<ContaCaixa> model1 = new DefaultManyToOneComboModel<ContaCaixa>(ContaCaixaListController.class,
				this.contaCaixaDAO, super.getMainController());

		this.subView.getCbContaCaixa().setModel(model1);

		DefaultManyToOneComboModel<DocumentoOrigem> model3 = new DefaultManyToOneComboModel<DocumentoOrigem>(DocumentoOrigemListController.class,
				this.documentoOrigemDAO, super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};
		this.subView.getCbDocumentoOrigem().setModel(model3);

		DefaultManyToOneComboModel<Fornecedor> model2 = new DefaultManyToOneComboModel<Fornecedor>(FornecedorListController.class,
				this.fornecedorDAO, super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}
		};
		// this.subView.getCbFornecedor().setModel(model2);

	}

	@Override
	protected void remover(List<Serializable> ids) {
		lancamentoReceberDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = validaCampos();

		return valido;
	}

	private boolean validaCampos() {

		boolean valido = true;

		if (!Validator.validateObject(subView.getCbDocumentoOrigem().getValue())) {
			adicionarErroDeValidacao(subView.getCbDocumentoOrigem(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtLancamento().getValue())) {
			adicionarErroDeValidacao(subView.getDtLancamento(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtPrimeiroVencimento().getValue())) {
			adicionarErroDeValidacao(subView.getDtPrimeiroVencimento(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxQuantidadeParcela().getValue())) {
			adicionarErroDeValidacao(subView.getTxQuantidadeParcela(), "Não pode ficar em branco");
			valido = false;
		} else if (verificaSeFoiParcelado() && !Validator.validateNumber(subView.getTxIntervaloParcela().getValue())) {
			adicionarErroDeValidacao(subView.getTxIntervaloParcela(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	private boolean verificaSeFoiParcelado() {
		return ((Integer) subView.getTxQuantidadeParcela().getConvertedValue()) > 1
				&& TipoVencimento.DIARIO.equals(subView.getCbTipoVencimento().getValue());
	}

	private BigDecimal getTotalParcelaReceber(List<ParcelaReceber> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValor());
		}
		return total;
	}

	private BigDecimal getTotalNaturezaFinanceira(List<LctoReceberNtFinanceira> naturezasFinanceiras) {
		BigDecimal total = BigDecimal.ZERO;
		if (naturezasFinanceiras != null) {
			for (int i = 0; i < naturezasFinanceiras.size(); i++) {
				total = total.add(naturezasFinanceiras.get(i).getValor());
			}
		}
		return total;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			LancamentoReceber lancamentoReceber = (LancamentoReceber) id;
			List<LctoReceberNtFinanceira> lctoReceberNtFinanceiras = lancamentoReceber.getLctoReceberNtFinanceiras();

			for (LctoReceberNtFinanceira lctoReceberNtFinanceira : lctoReceberNtFinanceiras) {
				lctoReceberNtFinanceira.setLancamentoReceber(null);

			}

			List<ParcelaReceber> parcelasReceber = lancamentoReceber.getParcelasReceber();

			for (ParcelaReceber parcelaReceber : parcelasReceber) {

				parcelaReceber.setLancamentoReceber(null);
			}

			lancamentoReceberDAO.delete(lancamentoReceber);

		}
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {

		return "lancamentoReceberForm";
	}

	@Override
	protected boolean isFullSized() {

		return true;
	}

	public void gerarParcelas() throws Exception {

		if (validaCampos()) {
			final ContaCaixa contaCaixa = (ContaCaixa) subView.getCbContaCaixa().getValue();
			if (contaCaixa == null || contaCaixa.getId() == null) {
				throw new Exception("É necessário informar a conta caixa para previsão das parcelas.");
			}
			final List<ParcelaReceber> parcelasReceber = new ArrayList<ParcelaReceber>();
			List<ParcelaReceber> dados = subView.getParcelasSubForm().getDados();
			if (dados != null) {
				parcelasReceber.addAll(subView.getParcelasSubForm().getDados());
			}

			if (parcelasReceber != null && !parcelasReceber.isEmpty()) {
				ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
						"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(parcelasReceber);
									geraParcelas(contaCaixa, parcelasReceber);
								}
							}
						});
			} else {
				geraParcelas(contaCaixa, parcelasReceber);
			}

		} else {
			mensagemErro("Preencha todos os campos corretamente!");
		}

	}

	private void geraParcelas(ContaCaixa contaCaixa, final List<ParcelaReceber> parcelasReceber) {
		subView.getParcelasSubForm().removeAllItems();

		subView.preencheBean(currentBean);

		setIntervaloParcelaByTipoVencimento();

		LancamentoReceber lancamentoReceber = currentBean;
		ParcelaReceber parcelaReceber;
		Date dataEmissao = new Date();
		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(lancamentoReceber.getPrimeiroVencimento());
		BigDecimal valorParcela = lancamentoReceber.getValorAReceber().divide(BigDecimal.valueOf(lancamentoReceber.getQuantidadeParcela()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		for (int i = 0; i < lancamentoReceber.getQuantidadeParcela(); i++) {
			parcelaReceber = new ParcelaReceber();
			parcelaReceber.setContaCaixa(contaCaixa);
			parcelaReceber.setNumeroParcela(i + 1);
			parcelaReceber.setDataEmissao(dataEmissao);
			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, lancamentoReceber.getIntervaloEntreParcelas());
			}
			parcelaReceber.setDataVencimento(primeiroVencimento.getTime());
			// parcelaReceber.setSofreRetencao(lancamentoReceber.getFornecedor().getSofreRetencao());
			parcelaReceber.setValor(valorParcela);

			somaParcelas = somaParcelas.add(valorParcela);
			if (i == (lancamentoReceber.getQuantidadeParcela() - 1)) {
				residuo = lancamentoReceber.getValorAReceber().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaReceber.setValor(valorParcela);
			}

			parcelasReceber.add(parcelaReceber);
			novoParcelaReceber(parcelaReceber);
		}

		subView.getParcelasSubForm().fillWith(parcelasReceber);
	}

	private void excluiParcelas(List<ParcelaReceber> parcelasReceber) {
		List<ParcelaReceber> persistentObjects = subView.getParcelasSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
			parcelaReceberDAO.delete(persistentObjects.get(i));
		}
		parcelasReceber.clear();
	}

	public ParcelaReceber novoParcelaReceber() {
		ParcelaReceber parcela = new ParcelaReceber();
		return novoParcelaReceber(parcela);
	}

	public ParcelaReceber novoParcelaReceber(ParcelaReceber parcela) {

		currentBean.addParcelaReceber(parcela);

		return parcela;
	}

	public void removerParcelaReceber(List<ParcelaReceber> values) {
		for (ParcelaReceber value : values) {
			currentBean.removeParcelaReceber(value);
		}

	}

	public LctoReceberNtFinanceira novoLctoReceberNtFinanceira() {
		LctoReceberNtFinanceira lctoReceberNtFinanceira = currentBean.addLctoReceberNtFinanceira();
		return lctoReceberNtFinanceira;
	}

	public void removerLctoReceberNtFinanceira(List<LctoReceberNtFinanceira> values) {
		for (LctoReceberNtFinanceira value : values) {
			currentBean.removeLctoReceberNtFinanceira(value);
		}

	}

	public List<NaturezaFinanceira> getNaturezasFinanceiras() {
		return naturezaFinanceiraDAO.listaTodos();
	}

}
