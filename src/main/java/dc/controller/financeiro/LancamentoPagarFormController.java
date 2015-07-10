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
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.control.enums.TipoVencimentoEn;
import dc.control.util.classes.financeiro.LancamentoPagarUtils;
import dc.control.validator.DotErpException;
import dc.controller.geral.pessoal.FornecedorListController;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.DocumentoOrigem;
import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.financeiro.LctoPagarNtFinanceira;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.financeiro.ParcelaPagar;
import dc.entidade.financeiro.StatusParcela;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.DocumentoOrigemDAO;
import dc.servicos.dao.financeiro.LancamentoPagarDAO;
import dc.servicos.dao.financeiro.LctoPagarNtFinanceiraDAO;
import dc.servicos.dao.financeiro.ParcelaPagarDAO;
import dc.servicos.dao.financeiro.StatusParcelaDAO;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.LancamentoPagarFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class LancamentoPagarFormController extends
		CRUDFormController<LancamentoPagarEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * BUSINESS
	 */
	//@Autowired
	//private LancamentoPagarBusiness<LancamentoPagarEntity> business;

	// @Autowired
	// private StatusParcelaBusiness<StatusParcela> businessStatus;
	
	//@Autowired
	//private FornecedorBusiness<FornecedorEntity> businessFornecedor;
	
	//@Autowired
	//private LctoPagarNtFinanceiraBusiness<LctoPagarNtFinanceira> naturezaFinanceiraBusiness;
	
	//@Autowired
	//private NaturezaFinanceiraBusiness<NaturezaFinanceira> naturezaFinBusiness;

	LancamentoPagarFormView subView;

	@Autowired
	private LancamentoPagarDAO lancamentoPagarDAO;

	@Autowired
	private ParcelaPagarDAO parcelaPagarDAO;

	private LancamentoPagarEntity currentBean;

	@Autowired
	private ContabilContaDAO contabilcontaDAO;

	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	@Autowired
	private FornecedorDAO fornecedorDAO;

	@Autowired
	private LctoPagarNtFinanceiraDAO naturezaFinanceiraDAO;

	@Autowired
	private DocumentoOrigemDAO documentoOrigemDAO;

	@Autowired
	private StatusParcelaDAO statusParcelaDAO;

	//public LancamentoPagarBusiness<LancamentoPagarEntity> getBusiness() {
//	 return business;
	//}

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

		try {

			boolean valido = validaSalvar();
			if (valido) {
				subView.preencheBean(currentBean);
				currentBean.setEmpresa(SecuritySessionProvider.getUsuario()
						.getConta().getEmpresa());

				List<ParcelaPagar> parcelasPagar = subView.getParcelasSubForm()
						.getDados();
				List<LctoPagarNtFinanceira> naturezasFinanceiras = subView
						.getNaturezaFinanceiraSubForm().getDados();

				if (((BigDecimal) subView.getTxValorPagar().getConvertedValue())
						.compareTo(getTotalParcelaPagar(parcelasPagar)) != 0) {
					adicionarErroDeValidacao(subView.getParcelasSubForm(),
							"Os valores informados nas parcelas não batem com o valor a pagar.");
					//valido = false;
					mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
				}

				setIntervaloParcelaByTipoVencimento();
				salvarParcelasPagar();

				if (((BigDecimal) subView.getTxValorPagar().getConvertedValue())
						.compareTo(getTotalNaturezaFinanceira(naturezasFinanceiras)) != 0) {
					adicionarErroDeValidacao(
							subView.getNaturezaFinanceiraSubForm(),
							"Os valores informados nas naturezas financeiras não batem com o valor a pagar.");
					//valido = false;

					mensagemErro("Os valores informados nas naturezas financeiras não batem com o valor a pagar.");
				}

				//this.business.saveOrUpdate(this.currentBean);
				this.lancamentoPagarDAO.saveOrUpdate(this.currentBean);

				notifiyFrameworkSaveOK(this.currentBean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void salvarParcelasPagar() {
		StatusParcela statusParcela;
		try {

			statusParcela = this.statusParcelaDAO.findBySituacao("01");
			// statusParcela =
			// this.businessStatus.findByLancamento(this.currentBean);

			if (statusParcela == null) {
				mensagemErro("O status de parcela em aberto não está cadastrado.\nEntre em contato com a Software House.");
			} else {
				for (ParcelaPagar p : currentBean.getParcelasPagar()) {
					p.setStatusParcela(statusParcela);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setIntervaloParcelaByTipoVencimento() {
		if (TipoVencimentoEn.M.equals(subView.getCbTipoVencimento().getValue())) {
			currentBean.setIntervaloEntreParcelas(30);
		}
	}

	@Override
	protected void carregar(Serializable id) {

		try {
			currentBean = this.lancamentoPagarDAO.find(id);
			//currentBean = this.business.find((Integer) id);
			subView.preencheForm(currentBean);
			
			List<LctoPagarNtFinanceira> itens = naturezaFinanceiraDAO.findByNatureza(currentBean);

			subView.preencheSubForm(itens);

		} catch (Exception e) {
			e.printStackTrace();
		}
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

		subView.getTxValorTotal().addBlurListener(new BlurListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void blur(BlurEvent event) {
				subView.getTxValorPagar().setConvertedValue(
						subView.getTxValorTotal().getConvertedValue());

			}
		});

		subView.getDtLancamento().setValue(new Date());
		subView.getTxIntervaloParcela().setEnabled(false);

		subView.getCbTipoVencimento().addValueChangeListener(
				new ValueChangeListener() {

					/**
			 * 
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						TipoVencimentoEn tipoVencimento = (TipoVencimentoEn) subView
								.getCbTipoVencimento().getValue();

						if (TipoVencimentoEn.M.equals(tipoVencimento)) {
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
		currentBean = new LancamentoPagarEntity();
	}

	private void preencheCombos() {

		DefaultManyToOneComboModel<ContaCaixa> model1 = new DefaultManyToOneComboModel<ContaCaixa>(
				ContaCaixaListController.class, this.contaCaixaDAO,
				super.getMainController());

		this.subView.getCbContaCaixa().setModel(model1);

		DefaultManyToOneComboModel<DocumentoOrigem> model3 = new DefaultManyToOneComboModel<DocumentoOrigem>(
				DocumentoOrigemListController.class, this.documentoOrigemDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "descricao";
			}

		};

		this.subView.getCbDocumentoOrigem().setModel(model3);
		
		DefaultManyToOneComboModel<FornecedorEntity> model2 = new DefaultManyToOneComboModel<FornecedorEntity>(FornecedorListController.class,
				this.fornecedorDAO, super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}
		};
		this.subView.getCbFornecedor().setModel(model2);

	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			//this.business.deleteAll(ids);
			this.lancamentoPagarDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		try {
			LancamentoPagarUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());
			return false;
		}
	}

	private boolean validaCampos() {
		boolean valido = true;

		if (!Validator
				.validateObject(subView.getCbDocumentoOrigem().getValue())) {
			adicionarErroDeValidacao(subView.getCbDocumentoOrigem(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbFornecedor().getValue())) {
			adicionarErroDeValidacao(subView.getCbFornecedor(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbPagamentoCompartilhado()
				.getValue())) {
			adicionarErroDeValidacao(subView.getCbPagamentoCompartilhado(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtLancamento().getValue())) {
			adicionarErroDeValidacao(subView.getDtLancamento(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtPrimeiroVencimento()
				.getValue())) {
			adicionarErroDeValidacao(subView.getDtPrimeiroVencimento(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxQuantidadeParcela()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTxQuantidadeParcela(),
					"Não pode ficar em branco");
			valido = false;
		} else if (verificaSeFoiParcelado()
				&& !Validator.validateNumber(subView.getTxIntervaloParcela()
						.getValue())) {
			adicionarErroDeValidacao(subView.getTxIntervaloParcela(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxValorPagar()
				.getConvertedValue().toString())) {
			adicionarErroDeValidacao(subView.getTxValorPagar(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxValorPagar()
				.getConvertedValue().toString())) {
			adicionarErroDeValidacao(subView.getTxValorTotal(),
					"Não pode ficar em branco");
			valido = false;
		}
		return valido;
	}

	private boolean verificaSeFoiParcelado() {
		return ((Integer) subView.getTxQuantidadeParcela().getConvertedValue()) > 1
				&& TipoVencimentoEn.D.equals(subView.getCbTipoVencimento()
						.getValue());
	}

	private BigDecimal getTotalParcelaPagar(List<ParcelaPagar> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValor());
		}
		return total;
	}

	private BigDecimal getTotalNaturezaFinanceira(
			List<LctoPagarNtFinanceira> naturezasFinanceiras) {
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
			LancamentoPagarEntity lancamentoPagar = (LancamentoPagarEntity) id;
			List<LctoPagarNtFinanceira> lctoPagarNtFinanceiras = lancamentoPagar
					.getLctoPagarNtFinanceiras();

			for (LctoPagarNtFinanceira lctoPagarNtFinanceira : lctoPagarNtFinanceiras) {
				lctoPagarNtFinanceira.setLancamentoPagar(null);

			}

			List<ParcelaPagar> parcelasPagar = lancamentoPagar
					.getParcelasPagar();

			for (ParcelaPagar parcelaPagar : parcelasPagar) {

				parcelaPagar.setLancamentoPagar(null);
			}

			remover(ids);
			// lancamentoPagarDAO.delete(lancamentoPagar);

		}
		mensagemRemovidoOK();
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

		if (validaCampos()) {
			final ContaCaixa contaCaixa = (ContaCaixa) subView
					.getCbContaCaixa().getValue();
			if (contaCaixa == null || contaCaixa.getId() == null) {
				throw new Exception(
						"É necessário informar a conta caixa para previsão das parcelas.");
			}
			final List<ParcelaPagar> parcelasPagar = new ArrayList<ParcelaPagar>();
			List<ParcelaPagar> dados = subView.getParcelasSubForm().getDados();
			if (dados != null) {
				parcelasPagar.addAll(subView.getParcelasSubForm().getDados());
			}

			if (parcelasPagar != null && !parcelasPagar.isEmpty()) {
				ConfirmDialog
						.show(MainUI.getCurrent(),
								"Confirme a remoção",
								"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
								"Sim", "Não", new ConfirmDialog.Listener() {

									/**
							 * 
							 */
									private static final long serialVersionUID = 1L;

									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											excluiParcelas(parcelasPagar);
											geraParcelas(contaCaixa,
													parcelasPagar);
										}
									}
								});
			} else {
				geraParcelas(contaCaixa, parcelasPagar);
			}

		} else {
			mensagemErro("Preencha todos os campos corretamente!");
		}

	}

	private void geraParcelas(ContaCaixa contaCaixa,
			final List<ParcelaPagar> parcelasPagar) {
		subView.getParcelasSubForm().removeAllItems();

		subView.preencheBean(currentBean);

		setIntervaloParcelaByTipoVencimento();

		LancamentoPagarEntity lancamentoPagar = currentBean;
		ParcelaPagar parcelaPagar;
		Date dataEmissao = new Date();
		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(lancamentoPagar.getPrimeiroVencimento());
		BigDecimal valorParcela = lancamentoPagar.getValorAPagar().divide(
				BigDecimal.valueOf(lancamentoPagar.getQuantidadeParcela()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		for (int i = 0; i < lancamentoPagar.getQuantidadeParcela(); i++) {
			parcelaPagar = new ParcelaPagar();
			parcelaPagar.setContaCaixa(contaCaixa);
			parcelaPagar.setNumeroParcela(i + 1);
			parcelaPagar.setDataEmissao(dataEmissao);
			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH,
						lancamentoPagar.getIntervaloEntreParcelas());
			}
			parcelaPagar.setDataVencimento(primeiroVencimento.getTime());
			// parcelaPagar.setSofreRetencao(lancamentoPagar.getFornecedor()
			// .getSofreRetencao());
			parcelaPagar.setValor(valorParcela);

			somaParcelas = somaParcelas.add(valorParcela);
			if (i == (lancamentoPagar.getQuantidadeParcela() - 1)) {
				residuo = lancamentoPagar.getValorAPagar().subtract(
						somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaPagar.setValor(valorParcela);
			}

			parcelasPagar.add(parcelaPagar);
			novoParcelaPagar(parcelaPagar);
		}

		subView.getParcelasSubForm().fillWith(parcelasPagar);
	}

	private void excluiParcelas(List<ParcelaPagar> parcelasPagar) {
		List<ParcelaPagar> persistentObjects = subView.getParcelasSubForm()
				.getDados();

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
		
		LctoPagarNtFinanceira item = new LctoPagarNtFinanceira();
		currentBean.addLctoPagarNtFinanceira(item);
		return item;
	}

	public void removerLctoPagarNtFinanceira(List<LctoPagarNtFinanceira> values) {
		for (LctoPagarNtFinanceira value : values) {
			currentBean.removeLctoPagarNtFinanceira(value);
		}
		
		mensagemRemovidoOK();

	}
	
	@Override
	public LancamentoPagarEntity getModelBean() {
		return currentBean;
	}

	/*
	 * public LctoPagarNtFinanceira adicionarLctoPagarNtFinanceira() { try {
	 * LctoPagarNtFinanceira ent = new LctoPagarNtFinanceira();
	 * ent.setLancamentoPagar(this.currentBean);
	 * 
	 * this.currentBean.getLctoPagarNtFinanceiras().add(ent);
	 * 
	 * return ent; } catch (Exception e) { e.printStackTrace();
	 * 
	 * throw e; } }
	 * 
	 * public BeanItemContainer<NaturezaFinanceira> getNaturezaFinanceiraBic() {
	 * 
	 * try { List<NaturezaFinanceira> auxLista =
	 * this.naturezaFinanceiraDAO.findAll();
	 * 
	 * BeanItemContainer<NaturezaFinanceira> bic = new
	 * BeanItemContainer<NaturezaFinanceira>( NaturezaFinanceira.class,
	 * auxLista);
	 * 
	 * return bic; } catch (Exception e) { e.printStackTrace();
	 * 
	 * return null; } }
	 * 
	 * /*public List<NaturezaFinanceira> getNaturezasFinanceiras() { // TODO
	 * Auto-generated method stub try { List<NaturezaFinanceira> auxLista =
	 * this.naturezaFinanceiraDAO.findAll();
	 * //BeanItemContainer<NaturezaFinanceira> bic = new
	 * BeanItemContainer<NaturezaFinanceira>(NaturezaFinanceira.class,
	 * auxLista);
	 * 
	 * return auxLista; } catch (Exception e) { e.printStackTrace();
	 * 
	 * return null; } }
	 */
	
	
    public List<NaturezaFinanceira> getNaturezasFin() {
		
		try {
			
			DefaultManyToOneComboModel<LctoPagarNtFinanceira> model1 = new DefaultManyToOneComboModel<LctoPagarNtFinanceira>(
					LctoPagarNtFinanceira.class, this.naturezaFinanceiraDAO,
					super.getMainController());

			this.subView.getNaturezaFinanceiraSubForm();
			
			return naturezaFinanceiraDAO.findByNaturezaFin(currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public List<LctoPagarNtFinanceira> getNaturezasFinanceiras() {
		
		try {
			return naturezaFinanceiraDAO.findByNatureza(currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
