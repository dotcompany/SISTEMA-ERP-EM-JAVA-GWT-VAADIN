package dc.controller.ordemservico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import dc.controller.financeiro.TipoPagamentoListController;
import dc.controller.pessoal.ClienteListController;
import dc.controller.pessoal.ColaboradorListController;
import dc.entidade.financeiro.TipoPagamento;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.ordemservico.Acessorio;
import dc.entidade.ordemservico.AcessorioOs;
import dc.entidade.ordemservico.Carro;
import dc.entidade.ordemservico.EntradaServico;
import dc.entidade.ordemservico.InformacaoGeral;
import dc.entidade.ordemservico.LaudoTecnico;
import dc.entidade.ordemservico.MaterialServico;
import dc.entidade.ordemservico.Observacao;
import dc.entidade.ordemservico.OrdemServico;
import dc.entidade.ordemservico.OrdemServicoEfetivacao;
import dc.entidade.ordemservico.ServicoOs;
import dc.entidade.ordemservico.SituacaoServico;
import dc.entidade.ordemservico.StatusOs;
import dc.entidade.ordemservico.TipoEfetivacao;
import dc.entidade.ordemservico.TipoServico;
import dc.entidade.ordemservico.VendaPeca;
import dc.entidade.pessoal.ClienteEntity;
import dc.entidade.pessoal.Colaborador;
import dc.servicos.dao.financeiro.TipoPagamentoDAO;
import dc.servicos.dao.geral.produto.ProdutoDAO;
import dc.servicos.dao.ordemservico.AcessorioDAO;
import dc.servicos.dao.ordemservico.AcessorioOsDAO;
import dc.servicos.dao.ordemservico.CarroDAO;
import dc.servicos.dao.ordemservico.CorDAO;
import dc.servicos.dao.ordemservico.EntradaServicoDAO;
import dc.servicos.dao.ordemservico.EquipamentoDAO;
import dc.servicos.dao.ordemservico.GarantiaDAO;
import dc.servicos.dao.ordemservico.InformacaoGeralDAO;
import dc.servicos.dao.ordemservico.LaudoTecnicoDAO;
import dc.servicos.dao.ordemservico.MarcaDAO;
import dc.servicos.dao.ordemservico.MaterialServicoDAO;
import dc.servicos.dao.ordemservico.ModeloDAO;
import dc.servicos.dao.ordemservico.ObservacaoDAO;
import dc.servicos.dao.ordemservico.OrdemServicoDAO;
import dc.servicos.dao.ordemservico.OrdemServicoEfetivacaoDAO;
import dc.servicos.dao.ordemservico.ServicoOsDAO;
import dc.servicos.dao.ordemservico.SituacaoServicoDAO;
import dc.servicos.dao.ordemservico.StatusOsDAO;
import dc.servicos.dao.ordemservico.TipoEfetivacaoDAO;
import dc.servicos.dao.ordemservico.TipoServicoDAO;
import dc.servicos.dao.ordemservico.VendaPecaDAO;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;
import dc.visao.ordemservico.OrdemServicoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class OrdemServicoFormController extends
		CRUDFormController<OrdemServico> {

	private static final long serialVersionUID = 1L;

	@Autowired
	OrdemServicoDAO dao;

	@Autowired
	CarroDAO carroDAO;

	@Autowired
	ClienteDAO clienteDAO;

	@Autowired
	ColaboradorDAO atendenteDAO;

	@Autowired
	ColaboradorDAO colaboradorDAO;

	@Autowired
	ServicoOsDAO servicoOsDAO;

	@Autowired
	ProdutoDAO produtoDAO;

	@Autowired
	TipoPagamentoDAO tipoPagamentoDAO;

	@Autowired
	TipoEfetivacaoDAO tipoEfetivacaoDAO;

	@Autowired
	MarcaDAO marcaDAO;

	@Autowired
	CorDAO corDAO;

	@Autowired
	StatusOsDAO statusOsDAO;

	@Autowired
	TipoServicoDAO tipoServicoDAO;

	@Autowired
	SituacaoServicoDAO situacaoServicoDAO;

	@Autowired
	EquipamentoDAO equipamentoDAO;

	@Autowired
	ModeloDAO modeloDAO;

	@Autowired
	InformacaoGeralDAO informacaoGeralDAO;

	@Autowired
	LaudoTecnicoDAO laudoTecnicoDAO;

	@Autowired
	EntradaServicoDAO entradaServicoDAO;

	@Autowired
	VendaPecaDAO vendaPecaDAO;

	@Autowired
	MaterialServicoDAO materialServicoDAO;

	@Autowired
	GarantiaDAO garantiaDAO;

	@Autowired
	AcessorioDAO acessorioDAO;

	@Autowired
	AcessorioOsDAO acessorioOsDAO;

	@Autowired
	ObservacaoDAO observacaoDAO;

	@Autowired
	OrdemServicoDAO ordemServicoDAO;

	@Autowired
	OrdemServicoEfetivacaoDAO ordemServicoEfetivacaoDAO;

	private OrdemServico currentBean;

	OrdemServicoFormView subView;

	final List<OrdemServicoEfetivacao> parcelasChequeOs = new ArrayList<OrdemServicoEfetivacao>();
	final List<OrdemServicoEfetivacao> parcelasCarneOs = new ArrayList<OrdemServicoEfetivacao>();
	final List<OrdemServicoEfetivacao> parcelasCartaoOs = new ArrayList<OrdemServicoEfetivacao>();
	final List<OrdemServicoEfetivacao> parcelasBoletoOs = new ArrayList<OrdemServicoEfetivacao>();
	final List<OrdemServicoEfetivacao> parcelasDuplicataOs = new ArrayList<OrdemServicoEfetivacao>();
	final List<OrdemServicoEfetivacao> parcelasValeOs = new ArrayList<OrdemServicoEfetivacao>();
	final List<OrdemServicoEfetivacao> parcelasCobrancaBancariaOs = new ArrayList<OrdemServicoEfetivacao>();
	final List<OrdemServicoEfetivacao> parcelasCobrancaCarteiraOs = new ArrayList<OrdemServicoEfetivacao>();
	private ClienteEntity cliente = new ClienteEntity();
	Window subWindow;

	@Override
	protected String getNome() {
		return "Ordem de Serviço";
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
				ClienteEntity cli = new ClienteEntity();
				if (subView.getCbCliente().getValue() != null) {
					cli = subView.getCbCliente().getValue();
					this.currentBean.setCliente(cli);
				}
				if (subView.getTfTotalServico() != null) {
					String valorTotalSevico = subView.getTfTotalServico()
							.getValue();
					if (Validator.validateString(valorTotalSevico)) {
						valorTotalSevico = formataBigDecimal(valorTotalSevico);
						this.currentBean.setValorServico(new BigDecimal(
								valorTotalSevico));
					}
				}
				if (subView.getTfTotalProdutoGeral() != null) {
					String valorTotalProduto = subView.getTfTotalProdutoGeral()
							.getValue();
					if (Validator.validateString(valorTotalProduto)) {
						valorTotalProduto = formataBigDecimal(valorTotalProduto);
						this.currentBean.setValorPeca(new BigDecimal(
								valorTotalProduto));
					}
				}
				// this.currentBean.setValorFrete(new
				// BigDecimal(subView.getTfTotalFreteGeral().getValue()));
				// this.currentBean.setValorOutros(new
				// BigDecimal(subView.getTfTotalOutrosGeral().getValue()));
				if (subView.getTfDescontoGeral() != null) {
					String valorTotalDesconto = subView.getTfDescontoGeral()
							.getValue();
					if (Validator.validateString(valorTotalDesconto)) {
						valorTotalDesconto = formataBigDecimal(valorTotalDesconto);
						this.currentBean.setValorDesconto(new BigDecimal(
								valorTotalDesconto));
					}
				}
				if (subView.getTfTotais() != null) {
					String valorTotalOs = subView.getTfTotais().getValue();
					if (Validator.validateString(valorTotalOs)) {
						valorTotalOs = formataBigDecimal(valorTotalOs);
						this.currentBean.setValorTotalOs(new BigDecimal(
								valorTotalOs));
					}
				}

				if (currentBean.getItensOrdemServicoEfetivacao().size() > 0) {
					currentBean.setEfetivada(true);
					// subView.getBtnEfetivacao().setCaption("Financeiro");
				}

				dao.saveOrUpdate(this.currentBean);

				salvarInformacaoGeral();
				salvarLaudoTecnico();
				salvarObservacao();

				removeModal();
				notifiyFrameworkSaveOK(this.currentBean);

			}
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		ClienteEntity cli = (ClienteEntity) subView.getCbCliente().getValue();
		if (!Validator.validateObject(cli)) {
			adicionarErroDeValidacao(subView.getCbCliente(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	public void salvarInformacaoGeral() {
		InformacaoGeral informacaoGeral = informacaoGeralDAO
				.buscaInformacaoGeral(currentBean);
		if (informacaoGeral == null) {
			informacaoGeral = new InformacaoGeral();
		}

		if (currentBean != null) {
			if (currentBean.getId() > 0) {
				informacaoGeral.setOrdemServico(currentBean);
			}
			if (subView.getPdfDataEntrada() != null) {
				informacaoGeral.setDataEntrada(subView.getPdfDataEntrada()
						.getValue());
			}
			if (subView.getPdfDataEfetiv() != null) {
				informacaoGeral.setDataEfetivacao(subView.getPdfDataEfetiv()
						.getValue());
			}
			if (!subView.getTfNumeroComanda().getValue().equals("")) {
				informacaoGeral.setNumeroComanda(Integer.parseInt(subView
						.getTfNumeroComanda().getValue()));
			}
			if (subView.getCbStatus() != null) {
				informacaoGeral.setStatusOs(subView.getCbStatus().getValue());
			}
			if (subView.getCbSituacaoServico() != null) {
				informacaoGeral.setSituacaoServico(subView
						.getCbSituacaoServico().getValue());
			}
			if (subView.getCbPlaca() != null) {
				informacaoGeral.setCarro(subView.getCbPlaca().getValue());
			}
			if (subView.getCbAtendente() != null) {
				informacaoGeral.setAtendente(subView.getCbAtendente()
						.getValue());
			}
			if (subView.getTfFone() != null) {
				informacaoGeral.setTelefone(subView.getTfFone().getValue());
			}
			if (!subView.getTfkm().getValue().equals("")) {
				informacaoGeral.setKmHorRodado(Integer.parseInt(subView
						.getTfkm().getValue()));
			}
			if (subView.getPdfProximaRevisao() != null) {
				informacaoGeral.setDataProximaRevisao(subView
						.getPdfProximaRevisao().getValue());
			}
			if (subView.getCbTipoServico() != null) {
				informacaoGeral.setTipoServico(subView.getCbTipoServico()
						.getValue());
			}
			if (subView.getTaObservacaoDefeito() != null) {
				informacaoGeral.setObservacao(subView.getTaObservacaoDefeito()
						.getValue());
			}
			if (subView.getCbFormaPagamento() != null) {
				informacaoGeral.setTipoPagamento(subView.getCbFormaPagamento()
						.getValue());
			}
			if (subView.getPdfEntrega() != null) {
				informacaoGeral.setDataEntrega(subView.getPdfEntrega()
						.getValue());
			}
			informacaoGeral.setOrdemServico(currentBean);
			informacaoGeralDAO.saveOrUpdate(informacaoGeral);
		}
	}

	public void salvarLaudoTecnico() {
		LaudoTecnico laudoTecnico = laudoTecnicoDAO
				.buscaLaudoTecnico(currentBean);

		if (laudoTecnico == null) {
			laudoTecnico = new LaudoTecnico();
		}

		if (currentBean != null) {
			if (currentBean.getId() > 0) {
				laudoTecnico.setOrdemServico(currentBean);
			}
			if (subView.getTaObservacaoLaudoTecnico() != null) {
				laudoTecnico.setObservacaoLaudoTecnico((String) subView
						.getTaObservacaoLaudoTecnico().getValue());
			}
			if (subView.getTaObservacaoLaudoFerramentas() != null) {
				laudoTecnico.setObservacaoLaudoFerramentas((String) subView
						.getTaObservacaoLaudoFerramentas().getValue());
			}
			laudoTecnico.setOrdemServico(currentBean);
			laudoTecnicoDAO.saveOrUpdate(laudoTecnico);
		}
	}

	public void salvarObservacao() {

		Observacao observacao = new Observacao();

		if (currentBean != null) {
			if (!subView.getTaObservacaoOS().equals("")) {
				observacao.setObservacaoOs(subView.getTaObservacaoOS()
						.getValue());
			}
			if (!subView.getTaObservacaoLocal().equals("")) {
				observacao.setFicandoLocal(subView.getTaObservacaoLocal()
						.getValue());
			}
			observacao.setOrdemServico(currentBean);
			observacaoDAO.saveOrUpdate(observacao);
		}
	}

	protected void actionSalvarEfetivacao() {
		subView.preencheBean(currentBean);

		boolean valido = true;
		if (((BigDecimal) subView.getValorTotalChequeOs())
				.compareTo(getTotalParcelaReceberCheque(parcelasChequeOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoChequeSubForm(),
					"Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		}

		if (((BigDecimal) subView.getValorTotalCarneOs())
				.compareTo(getTotalParcelaReceberCarne(parcelasCarneOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoCarneSubForm(),
					"Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		}

		if (((BigDecimal) subView.getValorTotalCartaoOs())
				.compareTo(getTotalParcelaReceberCartao(parcelasCartaoOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoCartaoSubForm(),
					"Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		}
		if (((BigDecimal) subView.getValorTotalBoletoOs())
				.compareTo(getTotalParcelaReceberBoleto(parcelasBoletoOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoBoletoSubForm(),
					"Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		}

		if (valido) {
			try {
				for (OrdemServicoEfetivacao p : parcelasChequeOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario()
							.getConta().getEmpresa());
					ordemServicoEfetivacaoDAO.saveOrUpdate(p);
				}

				for (OrdemServicoEfetivacao p : parcelasCarneOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario()
							.getConta().getEmpresa());
					ordemServicoEfetivacaoDAO.saveOrUpdate(p);
				}

				for (OrdemServicoEfetivacao p : parcelasCartaoOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario()
							.getConta().getEmpresa());
					ordemServicoEfetivacaoDAO.saveOrUpdate(p);
				}

				for (OrdemServicoEfetivacao p : parcelasBoletoOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario()
							.getConta().getEmpresa());

					ordemServicoEfetivacaoDAO.saveOrUpdate(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private BigDecimal getTotalParcelaReceberCheque(
			List<OrdemServicoEfetivacao> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private BigDecimal getTotalParcelaReceberCarne(
			List<OrdemServicoEfetivacao> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private BigDecimal getTotalParcelaReceberCartao(
			List<OrdemServicoEfetivacao> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private BigDecimal getTotalParcelaReceberBoleto(
			List<OrdemServicoEfetivacao> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private void preencheCombos() {

		DefaultManyToOneComboModel<ClienteEntity> cliente = new DefaultManyToOneComboModel<ClienteEntity>(
				ClienteListController.class, this.clienteDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}
		};
		this.subView.getCbCliente().setModel(cliente);

		DefaultManyToOneComboModel<Carro> carro = new DefaultManyToOneComboModel<Carro>(
				CarroListController.class, this.carroDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "placa";
			}
		};

		this.subView.getCbPlaca().setModel(carro);

		DefaultManyToOneComboModel<Colaborador> atendente = new DefaultManyToOneComboModel<Colaborador>(
				ColaboradorListController.class, this.atendenteDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}
		};

		this.subView.getCbAtendente().setModel(atendente);

		DefaultManyToOneComboModel<StatusOs> statusOs = new DefaultManyToOneComboModel<StatusOs>(
				StatusOsListController.class, this.statusOsDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};
		this.subView.getCbStatus().setModel(statusOs);

		DefaultManyToOneComboModel<TipoServico> tipoServico = new DefaultManyToOneComboModel<TipoServico>(
				TipoServicoListController.class, this.tipoServicoDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};

		this.subView.getCbTipoServico().setModel(tipoServico);

		DefaultManyToOneComboModel<SituacaoServico> situacaoServico = new DefaultManyToOneComboModel<SituacaoServico>(
				SituacaoServicoListController.class, this.situacaoServicoDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};

		this.subView.getCbSituacaoServico().setModel(situacaoServico);

		DefaultManyToOneComboModel<TipoPagamento> tipoPagamento = new DefaultManyToOneComboModel<TipoPagamento>(
				TipoPagamentoListController.class, this.tipoPagamentoDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};

		this.subView.getCbFormaPagamento().setModel(tipoPagamento);

		// DefaultManyToOneComboModel<Equipamento> equipamento = new
		// DefaultManyToOneComboModel<Equipamento>(EquipamentoListController.class,
		// this.equipamentoDAO, super.getMainController()) {
		// @Override
		// public String getCaptionProperty() {
		// return "descricao";
		// }
		// };
		//
		// this.subView.getCbEquipamentoGarantia().setModel(equipamento);
		//
		// DefaultManyToOneComboModel<Marca> marca = new
		// DefaultManyToOneComboModel<Marca>(MarcaListController.class,
		// this.marcaDAO,
		// super.getMainController());
		//
		// this.subView.getCbMarcaGarantia().setModel(marca);
		//
		// DefaultManyToOneComboModel<Modelo> modelo = new
		// DefaultManyToOneComboModel<Modelo>(ModeloListController.class,
		// this.modeloDAO,
		// super.getMainController());
		//
		// this.subView.getCbModeloGarantia().setModel(modelo);
		//
		// DefaultManyToOneComboModel<Cor> cor = new
		// DefaultManyToOneComboModel<Cor>(CorListController.class, this.corDAO,
		// super.getMainController());
		//
		// this.subView.getCbCorGarantia().setModel(cor);

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find((Integer) id);
		subView.preencheForm(currentBean);
		carregarInformacaoGeral();
		carregarLaudoTecnico();
		carregarEntradaServico();
		carregarVendaPeca();
		carregarMaterialServico();
		carregarAcessorioOs();
		carregarObservacao();
		carregarInformacaoFinanceira();
	}

	public void carregarInformacaoGeral() {
		InformacaoGeral info = informacaoGeralDAO
				.buscaInformacaoGeral(currentBean);

		if (info != null) {
			if (info.getAtendente() != null) {
				subView.getCbAtendente().setValue(info.getAtendente());
			}
			if (info.getDataEntrada() != null) {
				subView.getPdfDataEntrada().setValue(info.getDataEntrada());
			}
			if (info.getDataEntrada() != null) {
				subView.getPdfDataEfetiv().setValue(info.getDataEntrada());
			}
			if (info.getNumeroComanda() != null) {
				subView.getTfNumeroComanda().setValue(
						info.getNumeroComanda().toString());
			}
			if (info.getStatusOs() != null) {
				subView.getCbStatus().setValue(info.getStatusOs());
			}
			if (info.getSituacaoServico() != null) {
				subView.getCbSituacaoServico().setValue(
						info.getSituacaoServico());
			}
			if (info.getTelefone() != null) {
				subView.getTfFone().setValue(info.getTelefone());
			}
			if (info.getCarro() != null) {
				subView.getCbPlaca().setValue(info.getCarro());
			}
			if (info.getKmHorRodado() != null) {
				subView.getTfkm().setValue(info.getKmHorRodado().toString());
			}
			if (info.getDataProximaRevisao() != null) {
				subView.getPdfProximaRevisao().setValue(
						info.getDataProximaRevisao());
			}
			if (info.getTipoServico() != null) {
				subView.getCbTipoServico().setValue(info.getTipoServico());
			}
			if (info.getObservacao() != null) {
				subView.getTaObservacaoDefeito().setValue(info.getObservacao());
			}
			if (info.getTipoPagamento() != null) {
				subView.getCbFormaPagamento().setValue(info.getTipoPagamento());
			}
			if (info.getDataEntrega() != null) {
				subView.getPdfEntrega().setValue(info.getDataEntrega());
			}
		}
	}

	public void carregarLaudoTecnico() {
		LaudoTecnico laudo = laudoTecnicoDAO.buscaLaudoTecnico(currentBean);

		if (laudo != null) {
			subView.getTaObservacaoLaudoTecnico().setValue(
					laudo.getObservacaoLaudoTecnico());
			subView.getTaObservacaoLaudoFerramentas().setValue(
					laudo.getObservacaoLaudoFerramentas());
		}
	}

	public void carregarEntradaServico() {
		try {
			currentBean.setItensEntradaServico(entradaServicoDAO
					.findByEntradaServico(currentBean));
			if (currentBean.getItensEntradaServico().size() > 0) {
				subView.preencheEntradaServicoSubForm(currentBean
						.getItensEntradaServico());
			}
			if (currentBean.getItensEntradaServico().size() > 0) {
				subView.preencheEntradaServicoFinanceiraSubForm(currentBean
						.getItensEntradaServico());
			}
		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR ENTRADA DE SERVIÇO");
			e.printStackTrace();
		}
	}

	public void carregarVendaPeca() {
		try {
			currentBean.setItensVendaPeca(vendaPecaDAO
					.findByVendaPeca(currentBean));
			if (currentBean.getItensVendaPeca().size() > 0) {
				subView.preencheVendaPecaSubForm(currentBean
						.getItensVendaPeca());
			}
			if (currentBean.getItensVendaPeca().size() > 0) {
				subView.preencheVendaPecaFinanceiraSubForm(currentBean
						.getItensVendaPeca());
			}

		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR VENDA DE PEÇA");
			e.printStackTrace();
		}
	}

	public void carregarMaterialServico() {
		try {
			currentBean.setItensMaterialServico(materialServicoDAO
					.findByMaterialServico(currentBean));
			if (currentBean.getItensMaterialServico().size() > 0) {
				subView.preencheMaterialServicoSubForm(currentBean
						.getItensMaterialServico());
			}
		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR MATERIAL DE SERVIÇO");
			e.printStackTrace();
		}
	}

	public void carregarAcessorioOs() {
		try {
			currentBean.setItensAcessorioOs(acessorioOsDAO
					.findByAcessorioOs(currentBean));
			if (currentBean.getItensAcessorioOs().size() > 0) {
				subView.preencheAcessorioOsSubForm(currentBean
						.getItensAcessorioOs());
			}
		} catch (Exception e) {
			System.out
					.println("PROBLEMA AO CARREGAR ACESSORIOS DE ORDEM DE SERVIÇO");
			e.printStackTrace();
		}
	}

	public void carregarObservacao() {
		Observacao observacao = observacaoDAO.buscaObservacao(currentBean);

		if (observacao != null) {
			subView.getTaObservacaoOS().setValue(observacao.getObservacaoOs());
			subView.getTaObservacaoLocal().setValue(
					observacao.getFicandoLocal());
		}
	}

	public void carregarInformacaoFinanceira() {
		if (this.currentBean.getValorPeca() != null) {
			subView.getTfTotalPeca().setConvertedValue(
					this.currentBean.getValorPeca());
		}
		if (this.currentBean.getValorLucroPeca() != null) {
			subView.getTfLucroPeca().setConvertedValue(
					this.currentBean.getValorLucroPeca());
		}
		if (this.currentBean.getValorServico() != null) {
			subView.getTfTotalServico().setConvertedValue(
					this.currentBean.getValorServico());
		}
		if (this.currentBean.getValorLucroServico() != null) {
			subView.getTfLucroServico().setConvertedValue(
					this.currentBean.getValorLucroServico());
		}
		if (this.currentBean.getValorComissaoTecnico() != null) {
			subView.getTfComissaoTecnico().setConvertedValue(
					this.currentBean.getValorComissaoTecnico());
		}
		if (this.currentBean.getValorComissaoVendedor() != null) {
			subView.getTfComissaoVendedor().setConvertedValue(
					this.currentBean.getValorComissaoVendedor());
		}
		if (this.currentBean.getValorComissaoAtendente() != null) {
			subView.getTfComissaoAtendente().setConvertedValue(
					this.currentBean.getValorComissaoAtendente());
		}
		if (this.currentBean.getValorDesconto() != null) {
			subView.getTfDesconto().setConvertedValue(
					this.currentBean.getValorDesconto());
		}
		if (this.currentBean.getValorLucroParcial() != null) {
			subView.getTfLucroParcialServico().setConvertedValue(
					this.currentBean.getValorLucroParcial());
		}
		if (this.currentBean.getValorTotalOs() != null) {
			subView.getTfTotais().setConvertedValue(
					this.currentBean.getValorTotalOs());
		}
		if (this.currentBean.getEfetivada() != null
				&& this.currentBean.getEfetivada()) {
			subView.getBtnEfetivacao().setCaption("Financeiro");
		} else {
			subView.getBtnEfetivacao().setCaption("Efetivar OS");
		}

	}

	@Override
	protected void initSubView() {
		subView = new OrdemServicoFormView(this);
		preencheCombos();

		subView.getBtnFinalizar().addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					if (subView.getCbCliente().getValue() != null) {
						cliente = (ClienteEntity) subView.getCbCliente()
								.getValue();
						currentBean.setCliente(cliente);
					} else {
						mensagemErro("Favor selecionar um cliente.");
					}
					gerarParcelasOs();
				} catch (Exception e) {
					mensagemErro(e.getMessage());
				}
			}
		});

		subView.getBtnEfetivacao().addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				System.out.println("btnEfetivacao 1");
				if (subView.getCbCliente().getValue() == null) {
					mensagemErro("Antes de gravar, escolha o cliente, e grave novamente.");
					return;
				}
				System.out.println("btnEfetivacao 2");
				if (subView.getCbPlaca().getValue() == null) {
					mensagemErro("Antes de gravar, escolha primeiramente a placa do carro, e grave novamente.");
					return;
				}
				subWindow = new Window("Financeiro da Ordem de Serviço");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(true);

				GridLayout gridLayout = null;

				if (currentBean.getEfetivada() != null) {
					if (!currentBean.getEfetivada()) {
						ConfirmDialog.show(
								MainUI.getCurrent(),
								"Efetivar Ordem Serviço",
								"Tem certeza que deseja efetivar esta OS? depois de efetivado não poderá ser alterado.",
								"Sim", "Não", new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											excluiParcelas(parcelasChequeOs);
											geraParcelasChequeOs(parcelasChequeOs);
										}
									}
								});
					} else {
						if (currentBean.getEfetivada()) {
							subWindow.setModal(true);
							subWindow.setWidth("57%");
							gridLayout = subView
									.buildAbaEfetivacaoOsFinanceiro(currentBean);
							subView.preencheGeralFinanceiroSubForm(currentBean
									.getItensOrdemServicoEfetivacao());
							subView.preencheTituloFinanceiroSubForm(currentBean
									.getItensOrdemServicoEfetivacao());
							subView.preencheParcelasChequeSubForm(currentBean
									.getItensOrdemServicoEfetivacao());
						} else {
							subWindow.setModal(false);
							subWindow.setWidth("59%");
							gridLayout = subView.buildAbaEfetivacaoOs();
						}
					}
				} else {
					subWindow.setModal(false);
					subWindow.setWidth("59%");
					gridLayout = subView.buildAbaEfetivacaoOs();
				}
				subContent.addComponent(gridLayout);
				subWindow.setContent(subContent);
				subWindow.setHeight("70%");
				subWindow.center();
				UI.getCurrent().addWindow(subWindow);
			}
		});
	}

	public void removeModal() {
		UI.getCurrent().removeWindow(subWindow);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new OrdemServico();

	}

	public boolean validaCampos() {
		return true;
	}

	public void gerarParcelasOs() throws Exception {
		if (validaCampos()) {
			final List<OrdemServicoEfetivacao> parcelasChequeOs = new ArrayList<OrdemServicoEfetivacao>();
			final List<OrdemServicoEfetivacao> parcelasCarneOs = new ArrayList<OrdemServicoEfetivacao>();
			final List<OrdemServicoEfetivacao> parcelasCartaoOs = new ArrayList<OrdemServicoEfetivacao>();
			final List<OrdemServicoEfetivacao> parcelasBoletoOs = new ArrayList<OrdemServicoEfetivacao>();
			final List<OrdemServicoEfetivacao> parcelasDuplicataOs = new ArrayList<OrdemServicoEfetivacao>();
			final List<OrdemServicoEfetivacao> parcelasValeOs = new ArrayList<OrdemServicoEfetivacao>();
			final List<OrdemServicoEfetivacao> parcelasCobrancaBancariaOs = new ArrayList<OrdemServicoEfetivacao>();
			final List<OrdemServicoEfetivacao> parcelasCobrancaCarteiraOs = new ArrayList<OrdemServicoEfetivacao>();

			List<OrdemServicoEfetivacao> dadosCheque = subView
					.getParcelasChequeSubForm().getDados();
			if (dadosCheque != null) {
				parcelasChequeOs.addAll(subView.getParcelasChequeSubForm()
						.getDados());
			}
			if (subView.getTfCheque().getValue() != null) {
				if (parcelasChequeOs != null && !parcelasChequeOs.isEmpty()) {
					ConfirmDialog
							.show(MainUI.getCurrent(),
									"Confirme a remoção",
									"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
									"Sim", "Não", new ConfirmDialog.Listener() {
										private static final long serialVersionUID = 1L;

										public void onClose(ConfirmDialog dialog) {
											if (dialog.isConfirmed()) {
												excluiParcelas(parcelasChequeOs);
												geraParcelasChequeOs(parcelasChequeOs);
											}
										}
									});
				} else {
					geraParcelasChequeOs(parcelasChequeOs);
				}
			}

			List<OrdemServicoEfetivacao> dadosCartao = subView
					.getParcelasCartaoSubForm().getDados();
			if (dadosCartao != null) {
				parcelasCartaoOs.addAll(subView.getParcelasCartaoSubForm()
						.getDados());
			}
			if (subView.getTfCartao().getValue() != null) {
				if (parcelasCartaoOs.isEmpty()) {
					geraParcelasCartaoOs(parcelasCartaoOs);
				} else {
					if (parcelasCartaoOs != null) {
						ConfirmDialog
								.show(MainUI.getCurrent(),
										"Confirme a remoção",
										"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
										"Sim", "Não",
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													excluiParcelas(parcelasCartaoOs);
													geraParcelasCartaoOs(parcelasCartaoOs);
												}
											}
										});
					}
				}
			}
			List<OrdemServicoEfetivacao> dadosCarne = subView
					.getParcelasCarneSubForm().getDados();
			if (dadosCarne != null) {
				parcelasCarneOs.addAll(subView.getParcelasCarneSubForm()
						.getDados());
			}
			if (subView.getTfCarne().getValue() != null) {
				if (parcelasCarneOs.isEmpty()) {
					geraParcelasCarneOs(parcelasCarneOs);
				} else {
					if (parcelasCarneOs != null) {
						ConfirmDialog
								.show(MainUI.getCurrent(),
										"Confirme a remoção",
										"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
										"Sim", "Não",
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													excluiParcelas(parcelasCarneOs);
													geraParcelasCarneOs(parcelasCarneOs);
												}
											}
										});
					}
				}
			}
			List<OrdemServicoEfetivacao> dadosBoleto = subView
					.getParcelasBoletoSubForm().getDados();
			if (dadosBoleto != null) {
				parcelasBoletoOs.addAll(subView.getParcelasBoletoSubForm()
						.getDados());
			}
			if (subView.getTfBoleto().getValue() != null) {
				if (parcelasBoletoOs.isEmpty()) {
					geraParcelasBoletoOs(parcelasBoletoOs);
				} else {
					if (parcelasBoletoOs != null) {
						ConfirmDialog
								.show(MainUI.getCurrent(),
										"Confirme a remoção",
										"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
										"Sim", "Não",
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													excluiParcelas(parcelasBoletoOs);
													geraParcelasBoletoOs(parcelasBoletoOs);
												}
											}
										});
					}
				}
			}
			List<OrdemServicoEfetivacao> dadosDuplicata = subView
					.getParcelasDuplicataSubForm().getDados();
			if (dadosDuplicata != null) {
				parcelasDuplicataOs.addAll(subView
						.getParcelasDuplicataSubForm().getDados());
			}
			if (subView.getTfDuplicata().getValue() != null) {
				if (parcelasDuplicataOs.isEmpty()) {
					geraParcelasDuplicataOs(parcelasDuplicataOs);
				} else {
					if (parcelasDuplicataOs != null) {
						ConfirmDialog
								.show(MainUI.getCurrent(),
										"Confirme a remoção",
										"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
										"Sim", "Não",
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													excluiParcelas(parcelasDuplicataOs);
													geraParcelasDuplicataOs(parcelasDuplicataOs);
												}
											}
										});
					}
				}
			}

			List<OrdemServicoEfetivacao> dadosVale = subView
					.getParcelasValeSubForm().getDados();
			if (dadosVale != null) {
				parcelasValeOs.addAll(subView.getParcelasValeSubForm()
						.getDados());
			}
			if (subView.getTfVale().getValue() != null) {
				if (parcelasValeOs.isEmpty()) {
					geraParcelasValeOs(parcelasValeOs);
				} else {
					if (parcelasValeOs != null) {
						ConfirmDialog
								.show(MainUI.getCurrent(),
										"Confirme a remoção",
										"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
										"Sim", "Não",
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													excluiParcelas(parcelasValeOs);
													geraParcelasValeOs(parcelasValeOs);
												}
											}
										});
					}
				}
			}
			List<OrdemServicoEfetivacao> dadosCobrancaBancaria = subView
					.getParcelasCobrancaBancariaSubForm().getDados();
			if (dadosCobrancaBancaria != null) {
				parcelasCobrancaBancariaOs.addAll(subView
						.getParcelasCobrancaBancariaSubForm().getDados());
			}
			if (subView.getTfCobrancaBancaria().getValue() != null) {
				if (parcelasCobrancaBancariaOs.isEmpty()) {
					geraParcelasCobrancaBancariaOs(parcelasCobrancaBancariaOs);
				} else {
					if (parcelasCobrancaBancariaOs != null) {
						ConfirmDialog
								.show(MainUI.getCurrent(),
										"Confirme a remoção",
										"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
										"Sim", "Não",
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													excluiParcelas(parcelasCobrancaBancariaOs);
													geraParcelasCobrancaBancariaOs(parcelasCobrancaBancariaOs);
												}
											}
										});
					}
				}
			}
			List<OrdemServicoEfetivacao> dadosCarteira = subView
					.getParcelasCobrancaCarteiraSubForm().getDados();
			if (dadosCarteira != null) {
				parcelasCobrancaCarteiraOs.addAll(subView
						.getParcelasCobrancaCarteiraSubForm().getDados());
			}
			if (subView.getTfCobrancaCarteira().getValue() != null) {
				if (parcelasCobrancaCarteiraOs.isEmpty()) {
					geraParcelasCobrancaCarteiraOs(parcelasCobrancaCarteiraOs);
				} else {
					if (parcelasCobrancaCarteiraOs != null) {
						ConfirmDialog
								.show(MainUI.getCurrent(),
										"Confirme a remoção",
										"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
										"Sim", "Não",
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													excluiParcelas(parcelasCobrancaCarteiraOs);
													geraParcelasCobrancaCarteiraOs(parcelasCobrancaCarteiraOs);
												}
											}
										});
					}
				}
			}
		}
	}

	private void geraParcelasChequeOs(
			final List<OrdemServicoEfetivacao> parcelasCheque) {
		subView.getParcelasChequeSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcelaChequeOs;

		TipoEfetivacao tipoEfetivacao = new TipoEfetivacao();
		tipoEfetivacao = tipoEfetivacaoDAO.findByCodigo(2);

		Date dataEmissão = new Date();
		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		if (!subView.getTfCheque().getConvertedValue().toString().equals("")) {
			BigDecimal vlrTotalCheque = (BigDecimal) subView.getTfCheque()
					.getConvertedValue();
			currentBean.setValorTotalOs(vlrTotalCheque);
		}

		currentBean.setQuantidadeParcelaCheque(Integer.valueOf(subView
				.getTfQtParcelaCheque().getConvertedValue().toString()));
		currentBean.setPrimeiroVencimentoCheque(primeiroVencimento.getTime());

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(
				BigDecimal.valueOf(currentBean.getQuantidadeParcelaCheque()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;

		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();

		for (int i = 0; i < currentBean.getQuantidadeParcelaCheque(); i++) {
			parcelaChequeOs = new OrdemServicoEfetivacao();
			parcelaChequeOs.setOrdemParcela(i);
			parcelaChequeOs.setDataEfetivacao(dataEmissão);
			parcelaChequeOs.setTipoEfetivacao(tipoEfetivacao);
			if (i >= 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
				parcelaChequeOs.setDias(30 * (i + 1));
				parcelaChequeOs.setDataVencimento(primeiroVencimento.getTime());
			}
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente()
					.getId());
			nossoNumero += formatoNossoNumero4.format(currentBean
					.getQuantidadeParcelaCheque());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcelaChequeOs.setValorTotal(valorParcela);
			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (currentBean.getQuantidadeParcelaCheque() - 1)) {
				residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaChequeOs.setValorTotal(valorParcela);
			}
			parcelasCheque.add(parcelaChequeOs);
			novoParcelaChequeOs(parcelaChequeOs);
		}
		if (parcelasCheque.size() > 0) {
			subView.preencheParcelasChequeSubForm(parcelasCheque);
		}
	}

	private void geraParcelasCarneOs(
			final List<OrdemServicoEfetivacao> parcelasCarne) {
		subView.getParcelasCarneSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcela;
		TipoEfetivacao tipoEfetivacao = new TipoEfetivacao();
		tipoEfetivacao = tipoEfetivacaoDAO.findByCodigo(6);
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		if (!subView.getTfCarne().getConvertedValue().toString().equals("")) {
			BigDecimal vlrTotalCarne = (BigDecimal) subView.getTfCarne()
					.getConvertedValue();
			currentBean.setValorTotalOs(vlrTotalCarne);
		}
		currentBean.setQuantidadeParcelaCarne(Integer.valueOf(subView
				.getTfQtParcelaCarne().getConvertedValue().toString()));
		currentBean.setPrimeiroVencimentoCarne(primeiroVencimento.getTime());

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(
				BigDecimal.valueOf(currentBean.getQuantidadeParcelaCarne()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");
		Date dataAtual = new Date();

		for (int i = 0; i < currentBean.getQuantidadeParcelaCarne(); i++) {
			parcela = new OrdemServicoEfetivacao();
			parcela.setOrdemParcela(i);
			parcela.setDataEfetivacao(dataEmissão);
			parcela.setTipoEfetivacao(tipoEfetivacao);
			if (i >= 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
				parcela.setDias(30 * (i + 1));
				parcela.setDataVencimento(primeiroVencimento.getTime());
			}
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente()
					.getId());
			nossoNumero += formatoNossoNumero4.format(currentBean
					.getQuantidadeParcelaCarne());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcela.setValorTotal(valorParcela);
			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (currentBean.getQuantidadeParcelaCarne() - 1)) {
				residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcela.setValorTotal(valorParcela);
			}

			parcelasChequeOs.add(parcela);
			novoParcelaCarneOs(parcela);
		}
		if (parcelasChequeOs.size() > 0) {
			subView.preencheParcelasCarneSubForm(parcelasChequeOs);
		}
	}

	private void geraParcelasCartaoOs(
			final List<OrdemServicoEfetivacao> parcelasCartao) {
		subView.getParcelasCartaoSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcelaCartaoOs;
		TipoEfetivacao tipoEfetivacao = new TipoEfetivacao();
		tipoEfetivacao = tipoEfetivacaoDAO.findByCodigo(3);
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		if (subView.getTfCartao().getConvertedValue() != null) {
			BigDecimal vlrTotalCartao = (BigDecimal) subView.getTfCartao()
					.getConvertedValue();
			currentBean.setValorTotalOs(vlrTotalCartao);
		}

		currentBean.setQuantidadeParcelaCartao(Integer.valueOf(subView
				.getTfQtParcelaCartao().getConvertedValue().toString()));
		currentBean.setPrimeiroVencimentoCartao(primeiroVencimento.getTime());

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(
				BigDecimal.valueOf(currentBean.getQuantidadeParcelaCartao()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();
		for (int i = 0; i < currentBean.getQuantidadeParcelaCartao(); i++) {
			parcelaCartaoOs = new OrdemServicoEfetivacao();
			parcelaCartaoOs.setOrdemParcela(i);
			parcelaCartaoOs.setDataEfetivacao(dataEmissão);
			parcelaCartaoOs.setTipoEfetivacao(tipoEfetivacao);

			if (i >= 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
				parcelaCartaoOs.setDias(30 * (i + 1));
				parcelaCartaoOs.setDataVencimento(primeiroVencimento.getTime());
			}
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente()
					.getId());
			nossoNumero += formatoNossoNumero4.format(currentBean
					.getQuantidadeParcelaCartao());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcelaCartaoOs.setValorTotal(valorParcela);
			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (currentBean.getQuantidadeParcelaCartao() - 1)) {
				residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaCartaoOs.setValorTotal(valorParcela);
			}
			parcelasCartao.add(parcelaCartaoOs);
			novoParcelaCartaoOs(parcelaCartaoOs);
		}
		if (parcelasCartao.size() > 0) {
			subView.preencheParcelasCartaoSubForm(parcelasCartao);
		}
	}

	private void geraParcelasBoletoOs(
			final List<OrdemServicoEfetivacao> parcelasBoletoOs) {
		subView.getParcelasBoletoSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcela;
		TipoEfetivacao tipoEfetivacao = new TipoEfetivacao();
		tipoEfetivacao = tipoEfetivacaoDAO.findByCodigo(4);
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		if (subView.getTfBoleto().getConvertedValue() != null) {
			BigDecimal vlrTotalBoleto = (BigDecimal) subView.getTfBoleto()
					.getConvertedValue();
			currentBean.setValorTotalOs(vlrTotalBoleto);
		}
		currentBean.setQuantidadeParcelaBoleto(Integer.valueOf(subView
				.getTfQtParcelaBoleto().getConvertedValue().toString()));
		currentBean.setPrimeiroVencimentoBoleto(primeiroVencimento.getTime());

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(
				BigDecimal.valueOf(currentBean.getQuantidadeParcelaBoleto()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();
		for (int i = 0; i < currentBean.getQuantidadeParcelaBoleto(); i++) {
			parcela = new OrdemServicoEfetivacao();
			parcela.setOrdemParcela(i);
			parcela.setDataEfetivacao(dataEmissão);
			parcela.setTipoEfetivacao(tipoEfetivacao);
			if (i >= 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
				parcela.setDias(30 * (i + 1));
				parcela.setDataVencimento(primeiroVencimento.getTime());
			}
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente()
					.getId());
			nossoNumero += formatoNossoNumero4.format(currentBean
					.getQuantidadeParcelaBoleto());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcela.setValorTotal(valorParcela);
			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (currentBean.getQuantidadeParcelaBoleto() - 1)) {
				residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcela.setValorTotal(valorParcela);
			}
			parcelasBoletoOs.add(parcela);
			novoParcelaBoletoOs(parcela);
		}
		if (parcelasBoletoOs.size() > 0) {
			subView.preencheParcelasBoletoSubForm(parcelasBoletoOs);
		}
	}

	private void geraParcelasDuplicataOs(
			final List<OrdemServicoEfetivacao> parcelasDuplicata) {
		subView.getParcelasDuplicataSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcelaDuplicataOs;
		TipoEfetivacao tipoEfetivacao = new TipoEfetivacao();
		tipoEfetivacao = tipoEfetivacaoDAO.findByCodigo(5);
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		if (subView.getTfDuplicata().getConvertedValue() != null) {
			BigDecimal vlrTotalDuplicata = (BigDecimal) subView
					.getTfDuplicata().getConvertedValue();
			currentBean.setValorTotalOs(vlrTotalDuplicata);
		}
		currentBean.setQuantidadeParcelaDuplicata(Integer.valueOf(subView
				.getTfQtParcelaDuplicata().getConvertedValue().toString()));
		currentBean
				.setPrimeiroVencimentoDuplicata(primeiroVencimento.getTime());

		BigDecimal valorParcela = currentBean.getValorTotalOs()
				.divide(BigDecimal.valueOf(currentBean
						.getQuantidadeParcelaDuplicata()),
						RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();
		for (int i = 0; i < currentBean.getQuantidadeParcelaDuplicata(); i++) {
			parcelaDuplicataOs = new OrdemServicoEfetivacao();
			parcelaDuplicataOs.setOrdemParcela(i);
			parcelaDuplicataOs.setDataEfetivacao(dataEmissão);
			parcelaDuplicataOs.setTipoEfetivacao(tipoEfetivacao);

			if (i >= 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
				parcelaDuplicataOs.setDias(30 * (i + 1));
				parcelaDuplicataOs.setDataVencimento(primeiroVencimento
						.getTime());
			}
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente()
					.getId());
			nossoNumero += formatoNossoNumero4.format(currentBean
					.getQuantidadeParcelaDuplicata());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcelaDuplicataOs.setValorTotal(valorParcela);
			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (currentBean.getQuantidadeParcelaDuplicata() - 1)) {
				residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaDuplicataOs.setValorTotal(valorParcela);
			}
			parcelasDuplicata.add(parcelaDuplicataOs);
			novoParcelaDuplicataOs(parcelaDuplicataOs);
		}
		if (parcelasDuplicata.size() > 0) {
			subView.preencheParcelasDuplicataSubForm(parcelasDuplicata);
		}
	}

	private void geraParcelasValeOs(
			final List<OrdemServicoEfetivacao> parcelasVale) {
		subView.getParcelasValeSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcelaValeOs;
		TipoEfetivacao tipoEfetivacao = new TipoEfetivacao();
		tipoEfetivacao = tipoEfetivacaoDAO.findByCodigo(7);
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		if (subView.getTfVale().getConvertedValue() != null) {
			BigDecimal vlrTotalVale = (BigDecimal) subView.getTfVale()
					.getConvertedValue();
			currentBean.setValorTotalOs(vlrTotalVale);
		}
		currentBean.setQuantidadeParcelaVale(Integer.valueOf(subView
				.getTfQtParcelaVale().getConvertedValue().toString()));
		currentBean.setPrimeiroVencimentoVale(primeiroVencimento.getTime());

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(
				BigDecimal.valueOf(currentBean.getQuantidadeParcelaVale()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();
		for (int i = 0; i < currentBean.getQuantidadeParcelaVale(); i++) {
			parcelaValeOs = new OrdemServicoEfetivacao();
			parcelaValeOs.setOrdemParcela(i);
			parcelaValeOs.setDataEfetivacao(dataEmissão);
			parcelaValeOs.setTipoEfetivacao(tipoEfetivacao);
			if (i >= 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
				parcelaValeOs.setDias(30 * (i + 1));
				parcelaValeOs.setDataVencimento(primeiroVencimento.getTime());
			}
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente()
					.getId());
			nossoNumero += formatoNossoNumero4.format(currentBean
					.getQuantidadeParcelaVale());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcelaValeOs.setValorTotal(valorParcela);
			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (currentBean.getQuantidadeParcelaVale() - 1)) {
				residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaValeOs.setValorTotal(valorParcela);
			}
			parcelasVale.add(parcelaValeOs);
			novoParcelaValeOs(parcelaValeOs);
		}
		if (parcelasVale.size() > 0) {
			subView.preencheParcelasValeSubForm(parcelasVale);
		}
	}

	private void geraParcelasCobrancaBancariaOs(
			final List<OrdemServicoEfetivacao> parcelasCobrancaBancaria) {
		subView.getParcelasCobrancaBancariaSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcelaCobrancaBancariaOs;
		TipoEfetivacao tipoEfetivacao = new TipoEfetivacao();
		tipoEfetivacao = tipoEfetivacaoDAO.findByCodigo(8);
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		if (subView.getTfCobrancaBancaria().getConvertedValue() != null) {
			BigDecimal vlrTotalCobrancaBancaria = (BigDecimal) subView
					.getTfCobrancaBancaria().getConvertedValue();
			currentBean.setValorTotalOs(vlrTotalCobrancaBancaria);
		}
		currentBean.setQuantidadeParcelaCobrancaBancaria(Integer
				.valueOf(subView.getTfQtParcelaCobrancaBancaria()
						.getConvertedValue().toString()));
		currentBean.setPrimeiroVencimentoCobrancaBancaria(primeiroVencimento
				.getTime());

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(
				BigDecimal.valueOf(currentBean
						.getQuantidadeParcelaCobrancaBancaria()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();
		for (int i = 0; i < currentBean.getQuantidadeParcelaCobrancaBancaria(); i++) {
			parcelaCobrancaBancariaOs = new OrdemServicoEfetivacao();
			parcelaCobrancaBancariaOs.setOrdemParcela(i);
			parcelaCobrancaBancariaOs.setDataEfetivacao(dataEmissão);
			parcelaCobrancaBancariaOs.setTipoEfetivacao(tipoEfetivacao);
			if (i >= 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
				parcelaCobrancaBancariaOs.setDias(30 * (i + 1));
				parcelaCobrancaBancariaOs.setDataVencimento(primeiroVencimento
						.getTime());
			}
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente()
					.getId());
			nossoNumero += formatoNossoNumero4.format(currentBean
					.getQuantidadeParcelaCobrancaBancaria());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcelaCobrancaBancariaOs.setValorTotal(valorParcela);
			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (currentBean.getQuantidadeParcelaCobrancaBancaria() - 1)) {
				residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaCobrancaBancariaOs.setValorTotal(valorParcela);
			}
			parcelasCobrancaBancaria.add(parcelaCobrancaBancariaOs);
			novoParcelaCobrancaBancariaOs(parcelaCobrancaBancariaOs);
		}
		if (parcelasCobrancaBancaria.size() > 0) {
			subView.preencheParcelasCobrancaBancariaSubForm(parcelasCobrancaBancaria);
		}
	}

	private void geraParcelasCobrancaCarteiraOs(
			final List<OrdemServicoEfetivacao> parcelasCobrancaCarteira) {
		subView.getParcelasCobrancaCarteiraSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcelaCobrancaCarteiraOs;
		TipoEfetivacao tipoEfetivacao = new TipoEfetivacao();
		tipoEfetivacao = tipoEfetivacaoDAO.findByCodigo(9);
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		if (subView.getTfCobrancaCarteira().getConvertedValue() != null) {
			BigDecimal vlrTotalCobrancaCarteira = (BigDecimal) subView
					.getTfCobrancaCarteira().getConvertedValue();
			currentBean.setValorTotalOs(vlrTotalCobrancaCarteira);
		}
		currentBean.setQuantidadeParcelaCobrancaCarteira(Integer
				.valueOf(subView.getTfQtParcelaCobrancaCarteira()
						.getConvertedValue().toString()));
		currentBean.setPrimeiroVencimentoCobrancaCarteira(primeiroVencimento
				.getTime());

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(
				BigDecimal.valueOf(currentBean
						.getQuantidadeParcelaCobrancaCarteira()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();
		for (int i = 0; i < currentBean.getQuantidadeParcelaCobrancaCarteira(); i++) {
			parcelaCobrancaCarteiraOs = new OrdemServicoEfetivacao();
			parcelaCobrancaCarteiraOs.setOrdemParcela(i);
			parcelaCobrancaCarteiraOs.setDataEfetivacao(dataEmissão);
			parcelaCobrancaCarteiraOs.setTipoEfetivacao(tipoEfetivacao);
			if (i >= 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);
				parcelaCobrancaCarteiraOs.setDias(30 * (i + 1));
				parcelaCobrancaCarteiraOs.setDataVencimento(primeiroVencimento
						.getTime());
			}
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente()
					.getId());
			nossoNumero += formatoNossoNumero4.format(currentBean
					.getQuantidadeParcelaCobrancaCarteira());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcelaCobrancaCarteiraOs.setValorTotal(valorParcela);
			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (currentBean.getQuantidadeParcelaCobrancaCarteira() - 1)) {
				residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaCobrancaCarteiraOs.setValorTotal(valorParcela);
			}
			parcelasCobrancaCarteira.add(parcelaCobrancaCarteiraOs);
			novoParcelaCobrancaCarteiraOs(parcelaCobrancaCarteiraOs);
		}
		if (parcelasCobrancaCarteira.size() > 0) {
			subView.preencheParcelasCobrancaCarteiraSubForm(parcelasCobrancaCarteira);
		}
	}

	public OrdemServicoEfetivacao novoParcelaChequeOs(
			OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaCheque(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacao novoParcelaCarneOs(
			OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaCarne(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacao novoParcelaCartaoOs(
			OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaCartao(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacao novoParcelaBoletoOs(
			OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaBoleto(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacao novoParcelaDuplicataOs(
			OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaDuplicata(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacao novoParcelaValeOs(
			OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaVale(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacao novoParcelaCobrancaBancariaOs(
			OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaCobrancaBancaria(parcela);
		return parcela;
	}

	public OrdemServicoEfetivacao novoParcelaCobrancaCarteiraOs(
			OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaCobrancaCarteira(parcela);
		return parcela;
	}

	private void excluiParcelas(List<OrdemServicoEfetivacao> parcelasReceber) {
		List<OrdemServicoEfetivacao> persistentObjects = subView
				.getParcelasChequeSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
			// parcelaChequeDAO.delete(persistentObjects.get(i));
		}
		parcelasReceber.clear();
	}

	@Override
	public String getViewIdentifier() {
		return "ordemServicoForm";
	}

	@Override
	protected void quandoNovo() {
	}

	@Override
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	public List<Colaborador> getVendedores() {
		return colaboradorDAO.listaTodos();
	}

	public List<Colaborador> getTecnicos() {
		return colaboradorDAO.listaTodos();
	}

	public List<ServicoOs> buscarServicoOs() {
		return servicoOsDAO.getAll(ServicoOs.class);
	}

	public List<InformacaoGeral> buscarInformacaoGeral() {
		return informacaoGeralDAO.getAll(InformacaoGeral.class);
	}

	public void buscarOsAgrupada(ClienteEntity cliente) {
		// List<OrdemServico> osAgrupada =
		// ordemServicoDAO.buscarOsPorCliente(cliente);
		// subView.preencheOsAgrupadaSubForm(osAgrupada);
	}

	public List<ProdutoEntity> buscarProdutos() {
		return produtoDAO.getAll(ProdutoEntity.class);
	}

	public List<Acessorio> buscarAcessorio() {
		return acessorioDAO.getAll(Acessorio.class);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public InformacaoGeral novaInformacaoGeral() {
		InformacaoGeral d = new InformacaoGeral();
		// currentBean.adicionarInformacaoGeral(d);
		return d;
	}

	public EntradaServico novoEntradaServico() {
		EntradaServico c = new EntradaServico();
		currentBean.adicionarEntradaServico(c);
		// subView.preencheEntradaServicoFinanceiraSubForm(currentBean.getItensEntradaServico());
		return c;
	}

	public VendaPeca novoVendaPeca() {
		VendaPeca c = new VendaPeca();
		currentBean.adicionarVendaPeca(c);
		// subView.preencheVendaPecaFinanceiraSubForm(currentBean.getItensVendaPeca());
		return c;
	}

	public MaterialServico novoMaterialServico() {
		MaterialServico c = new MaterialServico();
		currentBean.adicionarMaterialServico(c);
		return c;
	}

	public AcessorioOs novoAcessorioOs() {
		AcessorioOs c = new AcessorioOs();
		currentBean.adicionarAcessorioOs(c);
		return c;
	}

	public void carregaTotais() {
		// currentBean.setValorServico(BigDecimal.ZERO);
		if (subView.getEntradaServicoFinanceiraSubForm() != null) {
			if (subView.getEntradaServicoFinanceiraSubForm().getDados() != null) {
				for (EntradaServico es : subView
						.getEntradaServicoFinanceiraSubForm().getDados()) {
					currentBean.setValorServico(currentBean.getValorServico()
							.add(es.getValorTotal()));
				}
			}
		}
	}

	@Override
	public OrdemServico getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

	public String formataBigDecimal(String valor) {
		String format = "";
		format = valor.replace(".", "").replace(",", ".");
		return format;
	}

	public OrdemServico getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(OrdemServico currentBean) {
		this.currentBean = currentBean;
	}

	public List montaFinanceiro(
			List<OrdemServicoEfetivacao> listOrdemServicoEfetivacao) {
		List<OrdemServicoEfetivacao> listGeral = new ArrayList<OrdemServicoEfetivacao>();
		int idOs = listOrdemServicoEfetivacao.get(0).getTipoEfetivacao()
				.getCodigo();
		BigDecimal valorTotal = BigDecimal.ZERO;
		String tipo = "";
		int quantidade = 0;
		int i = 1;

		for (OrdemServicoEfetivacao os : listOrdemServicoEfetivacao) {
			os.setQuantidade(1);
			if (os.getTipoEfetivacao().getCodigo() != idOs) {
				OrdemServicoEfetivacao totalizador = new OrdemServicoEfetivacao();
				totalizador.setValorTotal(valorTotal);
				totalizador.setTipo(tipo);
				totalizador.setQuantidade(quantidade);
				quantidade = 0;
				listGeral.add(totalizador);
				valorTotal = new BigDecimal(0);
				idOs = os.getTipoEfetivacao().getCodigo();
			}
			if (os.getTipoEfetivacao().getCodigo() == 1) {
				tipo = "Dinheiro";
				if (os.getQuantidade() != null && os.getQuantidade() > 0) {
					quantidade += os.getQuantidade();
				} else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if (os.getTipoEfetivacao().getCodigo() == 2) {
				tipo = "Cheque";
				if (os.getQuantidade() != null && os.getQuantidade() > 0) {
					quantidade += os.getQuantidade();
				} else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if (os.getTipoEfetivacao().getCodigo() == 3) {
				tipo = "Cartão";
				if (os.getQuantidade() != null && os.getQuantidade() > 0) {
					quantidade += os.getQuantidade();
				} else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if (os.getTipoEfetivacao().getCodigo() == 4) {
				tipo = "Boleto";
				if (os.getQuantidade() != null && os.getQuantidade() > 0) {
					quantidade += os.getQuantidade();
				} else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if (os.getTipoEfetivacao().getCodigo() == 5) {
				tipo = "Duplicata";
				if (os.getQuantidade() != null && os.getQuantidade() > 0) {
					quantidade += os.getQuantidade();
				} else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if (os.getTipoEfetivacao().getCodigo() == 6) {
				tipo = "Carnê";
				if (os.getQuantidade() != null && os.getQuantidade() > 0) {
					quantidade += os.getQuantidade();
				} else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if (os.getTipoEfetivacao().getCodigo() == 7) {
				tipo = "Vale";
				if (os.getQuantidade() != null && os.getQuantidade() > 0) {
					quantidade += os.getQuantidade();
				} else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}
			if (os.getTipoEfetivacao().getCodigo() == 8) {
				tipo = "Cobrança bancária";
				if (os.getQuantidade() != null && os.getQuantidade() > 0) {
					quantidade += os.getQuantidade();
				} else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}

			if (os.getTipoEfetivacao().getCodigo() == 9) {
				tipo = "Cobrança carteira";
				if (os.getQuantidade() != null && os.getQuantidade() > 0) {
					quantidade += os.getQuantidade();
				} else {
					quantidade = os.getQuantidade();
				}
				valorTotal = valorTotal.add(os.getValorTotal());
			}

			if (i == listOrdemServicoEfetivacao.size()) {
				OrdemServicoEfetivacao totalizador = new OrdemServicoEfetivacao();
				totalizador.setValorTotal(valorTotal);
				totalizador.setTipo(tipo);
				totalizador.setQuantidade(quantidade);
				listGeral.add(totalizador);
				quantidade = 0;
				valorTotal = new BigDecimal(0);
				idOs = os.getTipoEfetivacao().getCodigo();
			}

			i++;
		}

		return listGeral;
	}

}