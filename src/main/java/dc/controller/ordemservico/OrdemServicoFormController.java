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

import com.vaadin.ui.Component;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import dc.controller.financeiro.TipoPagamentoListController;
import dc.controller.pessoal.ClienteListController;
import dc.controller.pessoal.ColaboradorListController;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.financeiro.ParcelaReceber;
import dc.entidade.financeiro.TipoPagamento;
import dc.entidade.ordemservico.Acessorio;
import dc.entidade.ordemservico.AcessorioOs;
import dc.entidade.ordemservico.Carro;
import dc.entidade.ordemservico.Cor;
import dc.entidade.ordemservico.EntradaServico;
import dc.entidade.ordemservico.Equipamento;
import dc.entidade.ordemservico.InformacaoGeral;
import dc.entidade.ordemservico.LaudoTecnico;
import dc.entidade.ordemservico.Marca;
import dc.entidade.ordemservico.MaterialServico;
import dc.entidade.ordemservico.Modelo;
import dc.entidade.ordemservico.Observacao;
import dc.entidade.ordemservico.OrdemServico;
import dc.entidade.ordemservico.OrdemServicoEfetivacao;
import dc.entidade.ordemservico.ServicoOs;
import dc.entidade.ordemservico.SituacaoServico;
import dc.entidade.ordemservico.StatusOs;
import dc.entidade.ordemservico.TipoServico;
import dc.entidade.ordemservico.VendaPeca;
import dc.entidade.pessoal.Cliente;
import dc.entidade.pessoal.Colaborador;
import dc.entidade.produto.Produto;
import dc.servicos.dao.financeiro.TipoPagamentoDAO;
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
import dc.servicos.dao.ordemservico.OrdemServicoEfetivacaoDAO;
import dc.servicos.dao.ordemservico.OrdemServicoDAO;
import dc.servicos.dao.ordemservico.ServicoOsDAO;
import dc.servicos.dao.ordemservico.SituacaoServicoDAO;
import dc.servicos.dao.ordemservico.StatusOsDAO;
import dc.servicos.dao.ordemservico.TipoServicoDAO;
import dc.servicos.dao.ordemservico.VendaPecaDAO;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.visao.financeiro.LancamentoReceberFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;
import dc.visao.ordemservico.OrdemServicoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class OrdemServicoFormController extends CRUDFormController<OrdemServico>{

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
			currentBean.setCliente(subView.getCbCliente().getValue());
//			currentBean.setValorServico(new BigDecimal(subView.getTfTotalServico().getValue()));
//			currentBean.setValorPeca(new BigDecimal(subView.getTfTotalProdutoGeral().getValue()));
//			currentBean.setValorFrete(new BigDecimal(subView.getTfTotalFreteGeral().getValue()));
//			currentBean.setValorOutros(new BigDecimal(subView.getTfTotalOutrosGeral().getValue()));
//			currentBean.setValorDesconto(new BigDecimal(subView.getTfDescontoGeral().getValue()));
//			currentBean.setValorTotalOs(new BigDecimal(subView.getTfTotalGeral().getValue()));
			
			dao.saveOrUpdate(currentBean);

			salvarInformacaoGeral();	
			salvarLaudoTecnico();
			salvarObservacao();
			
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	protected void actionSalvarEfetivacao() {
		subView.preencheBean(currentBean);

		boolean valido = true;

		if (((BigDecimal) subView.getValorTotalChequeOs()).compareTo(getTotalParcelaReceberCheque(parcelasChequeOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoChequeSubForm(),"Os valores informados nas parcelas nÃ£o batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas nÃ£o batem com o valor a pagar.");
		}

		if (((BigDecimal) subView.getValorTotalCarneOs()).compareTo(getTotalParcelaReceberCarne(parcelasCarneOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoCarneSubForm(),"Os valores informados nas parcelas nÃ£o batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas nÃ£o batem com o valor a pagar.");
		}

		if (((BigDecimal) subView.getValorTotalCartaoOs()).compareTo(getTotalParcelaReceberCartao(parcelasCartaoOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoCartaoSubForm(),"Os valores informados nas parcelas nÃ£o batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas nÃ£o batem com o valor a pagar.");
		}
		if (((BigDecimal) subView.getValorTotalBoletoOs()).compareTo(getTotalParcelaReceberBoleto(parcelasBoletoOs)) != 0) {
			adicionarErroDeValidacao(subView.getEfetivacaoBoletoSubForm(),"Os valores informados nas parcelas nÃ£o batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas nÃ£o batem com o valor a pagar.");
		}

		if (valido) {
			try {

				for (OrdemServicoEfetivacao p : parcelasChequeOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
				}
				ordemServicoEfetivacaoDAO.saveOrUpdate(parcelasChequeOs);

				for (OrdemServicoEfetivacao p : parcelasCarneOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
				}
				ordemServicoEfetivacaoDAO.saveOrUpdate(parcelasCarneOs);

				for (OrdemServicoEfetivacao p : parcelasCartaoOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
				}
				ordemServicoEfetivacaoDAO.saveOrUpdate(parcelasCartaoOs);

				for (OrdemServicoEfetivacao p : parcelasBoletoOs) {
					p.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
				}
				ordemServicoEfetivacaoDAO.saveOrUpdate(parcelasBoletoOs);
				// notifiyFrameworkSaveOK(this.currentBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private BigDecimal getTotalParcelaReceberCheque(List<OrdemServicoEfetivacao> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private BigDecimal getTotalParcelaReceberCarne(List<OrdemServicoEfetivacao> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private BigDecimal getTotalParcelaReceberCartao(List<OrdemServicoEfetivacao> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private BigDecimal getTotalParcelaReceberBoleto(List<OrdemServicoEfetivacao> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValorTotal());
		}
		return total;
	}

	private void preencheCombos() {

		DefaultManyToOneComboModel<Cliente> cliente = new DefaultManyToOneComboModel<Cliente>(ClienteListController.class, this.clienteDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}
		};
		this.subView.getCbCliente().setModel(cliente);
		
		DefaultManyToOneComboModel<Carro> carro = new DefaultManyToOneComboModel<Carro>(CarroListController.class,
				this.carroDAO, super.getMainController()){
					@Override
					public String getCaptionProperty() {
						return "placa";
					}
				};

		this.subView.getCbPlaca().setModel(carro);

		DefaultManyToOneComboModel<Colaborador> atendente = new DefaultManyToOneComboModel<Colaborador>(ColaboradorListController.class,
				this.atendenteDAO, super.getMainController()){
					@Override
					public String getCaptionProperty() {
						return "pessoa.nome";
					}
				};

		this.subView.getCbAtendente().setModel(atendente);
		
		DefaultManyToOneComboModel<StatusOs> statusOs = new DefaultManyToOneComboModel<StatusOs>(StatusOsListController.class,
				this.statusOsDAO, super.getMainController()){
					@Override
					public String getCaptionProperty() {
						return "descricao";
					}
				};
		this.subView.getCbStatus().setModel(statusOs);

		DefaultManyToOneComboModel<TipoServico> tipoServico = new DefaultManyToOneComboModel<TipoServico>(TipoServicoListController.class,
				this.tipoServicoDAO, super.getMainController()){
					@Override
					public String getCaptionProperty() {
						return "descricao";
					}
				};

		this.subView.getCbTipoServico().setModel(tipoServico);

		DefaultManyToOneComboModel<SituacaoServico> situacaoServico = new DefaultManyToOneComboModel<SituacaoServico>(SituacaoServicoListController.class,
				this.situacaoServicoDAO, super.getMainController()){
					@Override
					public String getCaptionProperty() {
						return "descricao";
					}
				};

		this.subView.getCbSituacaoServico().setModel(situacaoServico);

		DefaultManyToOneComboModel<TipoPagamento> tipoPagamento = new DefaultManyToOneComboModel<TipoPagamento>(TipoPagamentoListController.class,
				this.tipoPagamentoDAO, super.getMainController()){
					@Override
					public String getCaptionProperty() {
						return "descricao";
					}
				};

		this.subView.getCbFormaPagamento().setModel(tipoPagamento);

		DefaultManyToOneComboModel<Equipamento> equipamento = new DefaultManyToOneComboModel<Equipamento>(EquipamentoListController.class,
				this.equipamentoDAO, super.getMainController()){
					@Override
					public String getCaptionProperty() {
						return "descricao";
					}
				};

		this.subView.getCbEquipamentoGarantia().setModel(equipamento);

		
		DefaultManyToOneComboModel<Marca> marca = new DefaultManyToOneComboModel<Marca>(MarcaListController.class,
				this.marcaDAO, super.getMainController());

		this.subView.getCbMarcaGarantia().setModel(marca);
		
		DefaultManyToOneComboModel<Modelo> modelo = new DefaultManyToOneComboModel<Modelo>(ModeloListController.class,
				this.modeloDAO, super.getMainController());

		this.subView.getCbModeloGarantia().setModel(modelo);
	
		DefaultManyToOneComboModel<Cor> cor = new DefaultManyToOneComboModel<Cor>(CorListController.class,
				this.corDAO, super.getMainController());

		this.subView.getCbCorGarantia().setModel(cor);

	}

	public void salvarInformacaoGeral(){
//		InformacaoGeral informacaoGeral = informacaoGeralDAO.buscaInformacaoGeral(currentBean);
//		if(informacaoGeral == null){
//			informacaoGeral = new InformacaoGeral();
//		}

		InformacaoGeral informacaoGeral = new InformacaoGeral();
		
		if(currentBean!=null){
			if(currentBean.getId()>0){
				informacaoGeral.setOrdemServico(currentBean);
			}

			if(subView.getPdfDataEntrada()!=null){
				informacaoGeral.setDataEntrada(subView.getPdfDataEntrada().getValue());
			}
			if(subView.getPdfDataEfetiv()!=null){
				informacaoGeral.setDataEfetivacao(subView.getPdfDataEfetiv().getValue());
			}

			if(subView.getTfNumeroComanda()!=null){
				informacaoGeral.setNumeroComanda(Integer.parseInt(subView.getTfNumeroComanda().getValue()));
			}
			if(subView.getCbStatus()!=null){
				informacaoGeral.setStatusOs(subView.getCbStatus().getValue());
			}
			if(subView.getCbSituacaoServico()!=null){
				informacaoGeral.setSituacaoServico(subView.getCbSituacaoServico().getValue());
			}
			if(subView.getCbPlaca()!=null){
				informacaoGeral.setCarro(subView.getCbPlaca().getValue());
			}
			if(subView.getCbAtendente()!=null){
				informacaoGeral.setAtendente(subView.getCbAtendente().getValue());
			}
			if(subView.getTfFone()!=null){
				informacaoGeral.setTelefone(subView.getTfFone().getValue());
			}
			if(subView.getTfkm()!=null){
				informacaoGeral.setKmHorRodado(Integer.parseInt(subView.getTfkm().getValue()));
			}
			if(subView.getPdfProximaRevisao()!=null){
				informacaoGeral.setDataProximaRevisao(subView.getPdfProximaRevisao().getValue());
			}
			if(subView.getCbTipoServico()!=null){
				informacaoGeral.setTipoServico(subView.getCbTipoServico().getValue());
			}
			if(subView.getTaObservacaoDefeito()!=null){
				informacaoGeral.setObservacao(subView.getTaObservacaoDefeito().getValue());
			}
			if(subView.getCbFormaPagamento()!=null){
				informacaoGeral.setTipoPagamento(subView.getCbFormaPagamento().getValue());
			}
			if(subView.getPdfEntrega()!=null){
				informacaoGeral.setDataEntrega(subView.getPdfEntrega().getValue());
			}
			informacaoGeral.setOrdemServico(currentBean);
			informacaoGeralDAO.saveOrUpdate(informacaoGeral);
		}

	}

	public void salvarLaudoTecnico(){
//		LaudoTecnico laudoTecnico = laudoTecnicoDAO.buscaLaudoTecnico(currentBean);
//
//		if(laudoTecnico == null){
//			laudoTecnico = new LaudoTecnico();
//		}
		
		LaudoTecnico laudoTecnico = new LaudoTecnico();
		
		if(currentBean!=null){
			if(currentBean.getId()>0){
				laudoTecnico.setOrdemServico(currentBean);
			}
			if(subView.getTaObservacaoLaudoTecnico()!=null){
				laudoTecnico.setObservacaoLaudoTecnico((String)subView.getTaObservacaoLaudoTecnico().getValue());
			}
			if(subView.getTaObservacaoLaudoFerramentas()!=null){
				laudoTecnico.setObservacaoLaudoFerramentas((String)subView.getTaObservacaoLaudoFerramentas().getValue());
			}
			laudoTecnico.setOrdemServico(currentBean);
			laudoTecnicoDAO.saveOrUpdate(laudoTecnico);
		}
	}
	
	public void salvarObservacao(){
		
		Observacao observacao = new Observacao();
		
		if(currentBean!=null){
			if(subView.getTaObservacaoOS()!=null){
				observacao.setObservacaoOs(subView.getTaObservacaoOS().getValue());
			}
			if(subView.getTaObservacaoLocal()!=null){
				observacao.setFicandoLocal(subView.getTaObservacaoLocal().getValue());
			}
			observacao.setOrdemServico(currentBean);
			observacaoDAO.saveOrUpdate(observacao);
		}
	}
	
	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find((Integer) id);
		
		subView.getCbCliente().setValue(currentBean.getCliente());
		
		carregarInformacaoGeral();
		carregarLaudoTecnico();
		carregarEntradaServico();
		carregarVendaPeca();
		carregarMaterialServico();
		carregarAcessorioOs();
		carregarObservacao();
	}

	public void carregarInformacaoGeral(){
		InformacaoGeral info = informacaoGeralDAO.buscaInformacaoGeral(currentBean);

		if(info != null){
			subView.getCbAtendente().setValue(info.getAtendente());
			subView.getPdfDataEntrada().setValue(info.getDataEntrada());
			subView.getPdfDataEfetiv().setValue(info.getDataEntrada());
			subView.getTfNumeroComanda().setValue(info.getNumeroComanda().toString());
			subView.getCbStatus().setValue(info.getStatusOs());
			subView.getCbSituacaoServico().setValue(info.getSituacaoServico());
			subView.getTfFone().setValue(info.getTelefone());
			subView.getCbPlaca().setValue(info.getCarro());
			subView.getTfkm().setValue(info.getKmHorRodado().toString());
			subView.getPdfProximaRevisao().setValue(info.getDataProximaRevisao());
			subView.getCbTipoServico().setValue(info.getTipoServico());
			subView.getTaObservacaoDefeito().setValue(info.getObservacao());
			subView.getCbFormaPagamento().setValue(info.getTipoPagamento());
			subView.getPdfEntrega().setValue(info.getDataEntrega());
		}
	}

	public void carregarLaudoTecnico(){
		LaudoTecnico laudo = laudoTecnicoDAO.buscaLaudoTecnico(currentBean);

		if(laudo != null){
			subView.getTaObservacaoLaudoTecnico().setValue(laudo.getObservacaoLaudoTecnico());
			subView.getTaObservacaoLaudoFerramentas().setValue(laudo.getObservacaoLaudoFerramentas());
		}
	}
	
	public void carregarEntradaServico(){
		try{
			currentBean.setItensEntradaServico(entradaServicoDAO.findByEntradaServico(currentBean));
			subView.preencheEntradaServicoSubForm(currentBean.getItensEntradaServico());	
			subView.preencheEntradaServicoFinanceiraSubForm(currentBean.getItensEntradaServico());	
		}catch(Exception e){
			System.out.println("PROBLEMA AO CARREGAR ENTRADA DE SERVIÇO");
			e.printStackTrace();
		}
	}

	public void carregarVendaPeca(){
		try{
			currentBean.setItensVendaPeca(vendaPecaDAO.findByVendaPeca(currentBean));
			subView.preencheVendaPecaSubForm(currentBean.getItensVendaPeca());	 
			subView.preencheVendaPecaFinanceiraSubForm(currentBean.getItensVendaPeca());
			
		}catch(Exception e){
			System.out.println("PROBLEMA AO CARREGAR VENDA DE PEÇA");
			e.printStackTrace();
		}
	}

	public void carregarMaterialServico(){
		try{
			currentBean.setItensMaterialServico(materialServicoDAO.findByMaterialServico(currentBean));
			subView.preencheMaterialServicoSubForm(currentBean.getItensMaterialServico());	 
		}catch(Exception e){
			System.out.println("PROBLEMA AO CARREGAR MATERIAL DE SERVIÇO");
			e.printStackTrace();
		}
	}
	
	public void carregarAcessorioOs(){
		try{
			currentBean.setItensAcessorioOs(acessorioOsDAO.findByAcessorioOs(currentBean));
			subView.preencheAcessorioOsSubForm(currentBean.getItensAcessorioOs());	 
		}catch(Exception e){
			System.out.println("PROBLEMA AO CARREGAR ACESSORIOS DE ORDEM DE SERVIÇO");
			e.printStackTrace();
		}
	}
	
	public void carregarObservacao(){
		Observacao observacao = observacaoDAO.buscaObservacao(currentBean);

		if(observacao != null){
			subView.getTaObservacaoOS().setValue(observacao.getObservacaoOs());
			subView.getTaObservacaoLocal().setValue(observacao.getFicandoLocal());
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
					gerarParcelasOs();
				} catch (Exception e) {
					mensagemErro(e.getMessage());
				}
			}
		});
		
		subView.getBtnGravarEfetivacao().addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					actionSalvarEfetivacao();
				} catch (Exception e) {
					mensagemErro(e.getMessage());
				}
			}
		});
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new OrdemServico();

	}
	
	public boolean validaCampos(){
		return true;
	}
	
	//controller initSubView e deverá ser chamada quando clicar no botão o sair do campo para gerar parcelas
	public void gerarParcelasOs() throws Exception {
		if (validaCampos()) {
			final List<OrdemServicoEfetivacao> parcelasChequeOs = new ArrayList<OrdemServicoEfetivacao>();
			final List<OrdemServicoEfetivacao> parcelasCarneOs = new ArrayList<OrdemServicoEfetivacao>();
			final List<OrdemServicoEfetivacao> parcelasCartaoOs = new ArrayList<OrdemServicoEfetivacao>();
			final List<OrdemServicoEfetivacao> parcelasBoletoOs = new ArrayList<OrdemServicoEfetivacao>();
			List<OrdemServicoEfetivacao> dadosCheque = subView.getParcelasChequeSubForm().getDados();
			if (dadosCheque != null) {
				parcelasChequeOs.addAll(subView.getParcelasChequeSubForm().getDados());
			}
			List<OrdemServicoEfetivacao> dadosCarne = subView.getParcelasCarneSubForm().getDados();
			if (dadosCarne != null) {
				parcelasCarneOs.addAll(subView.getParcelasCarneSubForm().getDados());
			}
			List<OrdemServicoEfetivacao> dadosCartao = subView.getParcelasCartaoSubForm().getDados();
			if (dadosCartao != null) {
				parcelasCartaoOs.addAll(subView.getParcelasCartaoSubForm().getDados());
			}
			List<OrdemServicoEfetivacao> dadosBoleto = subView.getParcelasBoletoSubForm().getDados();
			if (dadosBoleto != null) {
				parcelasBoletoOs.addAll(subView.getParcelasBoletoSubForm().getDados());
			}

			if (parcelasChequeOs != null && !parcelasChequeOs.isEmpty()) {
				ConfirmDialog.show(MainUI.getCurrent(),
								"Confirme a remoção","As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
								"Sim", "Não", new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											excluiParcelasCheque(parcelasChequeOs);
											geraParcelasChequeOs(parcelasChequeOs);
										}
									}
								});
			} else {
				//if (parcelasChequeOs != null && parcelasChequeOs.size() > 0) {
					geraParcelasChequeOs(parcelasChequeOs);
				//}
//				System.out.println("parcelasCarneOs.size(): "+parcelasCarneOs.size());
//				if (parcelasCarneOs != null  && parcelasCarneOs.size() > 0) {
//					geraParcelasCarneOs(parcelasCarneOs);
//				}
//				System.out.println("Dados cartao: "+parcelasCartaoOs);
//				if (parcelasCartaoOs != null) {
					geraParcelasCartaoOs(parcelasCartaoOs);
//				}
//				System.out.println("Dados boleto: "+parcelasBoletoOs);
//				if (parcelasBoletoOs != null) {
//					geraParcelasBoletoOs(parcelasBoletoOs);
//				}
			}
		} else {
			mensagemErro("Preencha todos os campos corretamente!");
		}
	}		
		
	// controller
	private void geraParcelasChequeOs(final List<OrdemServicoEfetivacao> parcelasCheque) {
		System.out.println("geraParcelasChequeOs");
		subView.getParcelasChequeSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcelaChequeOs;
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		// primeiroVencimento.setTime(ordemServico.getPrimeiroVencimento());

		primeiroVencimento.setTime(primeiroVencimento.getTime());

		// para teste depois retirar
		
		currentBean.setValorTotalOs(new BigDecimal(subView.getTfTotalServicoGeral().getValue()));
		currentBean.setQuantidadeParcelaCheque(5);
		currentBean.setPrimeiroVencimentoCheque(primeiroVencimento.getTime());
		Cliente cli = new Cliente();
		cli.setId(3);
		currentBean.setCliente(cli);

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaCheque()),RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		System.out.println("Gerar parcelas cheque os 11");

		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();

		for (int i = 0; i < currentBean.getQuantidadeParcelaCheque(); i++) {
			parcelaChequeOs = new OrdemServicoEfetivacao();
			parcelaChequeOs.setOrdemParcela(i + 1);
			parcelaChequeOs.setDataEfetivacao(dataEmissão);
			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH,30);
				parcelaChequeOs.setDias(30 * i);
			}
			parcelaChequeOs.setDataVencimento(primeiroVencimento.getTime());
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
			nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaCheque());
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
		subView.getParcelasChequeSubForm().fillWith(parcelasCheque);
	}

	// controller
	private void geraParcelasCarneOs(final List<OrdemServicoEfetivacao> parcelasCarne) {
		System.out.println("geraParcelasCarneOs");

		subView.getParcelasCarneSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcelaCarneOs;
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		// para teste depois retirar
		currentBean.setValorTotalOs(BigDecimal.valueOf(5000));
		currentBean.setQuantidadeParcelaCarne(5);
		currentBean.setPrimeiroVencimentoCarne(primeiroVencimento.getTime());
		Cliente cli = new Cliente();
		cli.setId(3);
		currentBean.setCliente(cli);

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaCarne()),RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;

		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();

		for (int i = 0; i < currentBean.getQuantidadeParcelaCarne(); i++) {
			parcelaCarneOs = new OrdemServicoEfetivacao();
			parcelaCarneOs.setOrdemParcela(i + 1);
			parcelaCarneOs.setDataEfetivacao(dataEmissão);
			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH,30);
				parcelaCarneOs.setDias(30 * i);
			}
			parcelaCarneOs.setDataVencimento(primeiroVencimento.getTime());
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
			nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaCarne());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcelaCarneOs.setValorTotal(valorParcela);
			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (currentBean.getQuantidadeParcelaCarne() - 1)) {
				residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaCarneOs.setValorTotal(valorParcela);
			}

			parcelasCarne.add(parcelaCarneOs);
			novoParcelaCarneOs(parcelaCarneOs);
		}
		subView.getParcelasCarneSubForm().fillWith(parcelasCarne);
	}
	
	// controller
	private void geraParcelasCartaoOs(final List<OrdemServicoEfetivacao> parcelasCartao) {
		subView.getParcelasCarneSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcelaCartaoOs;
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		// para teste depois retirar
		currentBean.setValorTotalOs(BigDecimal.valueOf(5000));
		currentBean.setQuantidadeParcelaCarne(5);
		currentBean.setPrimeiroVencimentoCartao(primeiroVencimento.getTime());
		Cliente cli = new Cliente();
		cli.setId(3);
		currentBean.setCliente(cli);

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaCartao()),RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;

		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();

		for (int i = 0; i < currentBean.getQuantidadeParcelaCartao(); i++) {
			parcelaCartaoOs = new OrdemServicoEfetivacao();
			parcelaCartaoOs.setOrdemParcela(i + 1);
			parcelaCartaoOs.setDataEfetivacao(dataEmissão);
			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH,30);
				parcelaCartaoOs.setDias(30 * i);
			}
			parcelaCartaoOs.setDataVencimento(primeiroVencimento.getTime());
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
			nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaCartao());
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
		subView.getParcelasCartaoSubForm().fillWith(parcelasCartao);
	}
	
	// controller
	private void geraParcelasBoletoOs(final List<OrdemServicoEfetivacao> parcelasBoleto) {
		subView.getParcelasBoletoSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		OrdemServico ordemServico = currentBean;
		OrdemServicoEfetivacao parcelaBoletoOs;
		Date dataEmissão = new Date();

		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(primeiroVencimento.getTime());

		// para teste depois retirar
		currentBean.setValorTotalOs(BigDecimal.valueOf(5000));
		currentBean.setQuantidadeParcelaBoleto(5);
		currentBean.setPrimeiroVencimentoBoleto(primeiroVencimento.getTime());
		Cliente cli = new Cliente();
		cli.setId(3);
		currentBean.setCliente(cli);

		BigDecimal valorParcela = currentBean.getValorTotalOs().divide(BigDecimal.valueOf(currentBean.getQuantidadeParcelaBoleto()),RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;

		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");

		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");

		Date dataAtual = new Date();

		for (int i = 0; i < currentBean.getQuantidadeParcelaCarne(); i++) {
			parcelaBoletoOs = new OrdemServicoEfetivacao();
			parcelaBoletoOs.setOrdemParcela(i + 1);
			parcelaBoletoOs.setDataEfetivacao(dataEmissão);
			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH,30);
				parcelaBoletoOs.setDias(30 * i);
			}
			parcelaBoletoOs.setDataVencimento(primeiroVencimento.getTime());
			nossoNumero = formatoNossoNumero5.format(currentBean.getCliente().getId());
			nossoNumero += formatoNossoNumero4.format(currentBean.getQuantidadeParcelaBoleto());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcelaBoletoOs.setValorTotal(valorParcela);
			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (currentBean.getQuantidadeParcelaBoleto() - 1)) {
				residuo = currentBean.getValorTotalOs().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaBoletoOs.setValorTotal(valorParcela);
			}

			parcelasBoleto.add(parcelaBoletoOs);
			novoParcelaBoletoOs(parcelaBoletoOs);
		}
		subView.getParcelasBoletoSubForm().fillWith(parcelasBoleto);
	}

//	 public OrdemServicoCheque novoParcelaChequeOs() {
//		OrdemServicoCheque parcela = new OrdemServicoCheque();
//		return novoParcelaReceber(parcela);
//	}

	public OrdemServicoEfetivacao novoParcelaChequeOs(OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaCheque(parcela);
		return parcela;
	}
	public OrdemServicoEfetivacao novoParcelaCarneOs(OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaCarne(parcela);
		return parcela;
	}
	public OrdemServicoEfetivacao novoParcelaCartaoOs(OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaCartao(parcela);
		return parcela;
	}
	public OrdemServicoEfetivacao novoParcelaBoletoOs(OrdemServicoEfetivacao parcela) {
		currentBean.addParcelaBoleto(parcela);
		return parcela;
	}

//	public void removerParcelaReceber(List<OrdemServicoCheque> values) {
//		for (OrdemServicoCheque value : values) {
//			currentBean.removeParcelaChequeOs(value);
//		}
//	}







//		public void gerarParcelasCarneOs() throws Exception {
//
//			if (validaCampos()) {
//				final List<OrdemServicoCarne> parcelasCarneOs = new ArrayList<OrdemServicoCarne>();
//				List<OrdemServicoCarne> dados = subView.getParcelasCarneSubForm().getDados();
//				if (dados != null) {
//					parcelasCarneOs.addAll(subView.getParcelasCarneSubForm().getDados());
//				}
//
//				if (parcelasCarneOs != null && !parcelasCarneOs.isEmpty()) {
//					ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
//							"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
//							new ConfirmDialog.Listener() {
//								private static final long serialVersionUID = 1L;
//
//								public void onClose(ConfirmDialog dialog) {
//									if (dialog.isConfirmed()) {
//										excluiParcelasCarneOs(parcelasCarneOs);
//										geraParcelasCarneOs(parcelasCarneOs);
//									}
//								}
//							});
//				} else {
//					geraParcelasCarneOs(parcelasCarneOs);
//				}
//
//			} else {
//				mensagemErro("Preencha todos os campos corretamente!");
//			}
//
//		}
//		
//	//controller
//	private void geraParcelasCarneOs(final List<OrdemServicoCarne> parcelasCarne) {
//
//	  // gera as parcelas
//	  subView.getCarneSubForm().removeAllItems();
//	  subView.preencheBean(currentBean);
//
//	  OrdemServicoCarne carneOs = currentBean;
//	  OrdemServicoCarne parcelaCarneOs;
//	  Date dataEmissão = new Date();
//
//	  Calendar primeiroVencimento = Calendar.getInstance();
//
//	  primeiroVencimento.setTime(parcelaCarneOs.getDataVencimento());
//
//	  BigDecimal valorParcela = carneOs.getValorAReceber().divide(BigDecimal.valueOf(carneOs.getQuantidadeParcela()), RoundingMode.HALF_DOWN);
//	  BigDecimal somaParcelas = BigDecimal.ZERO;
//	  BigDecimal residuo = BigDecimal.ZERO;
//
//	  String nossoNumero;
//	  DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
//	  DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
//
//	  SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");
//
//	  Date dataAtual = new Date();
//
//	  for (int i = 0; i < carneOs.getQuantidadeParcela(); i++) {
//	    parcelaCarneOs = new OrdemServicoCarne();
//	    parcelaCarneOs.setContaCaixa(contaCaixa);
//	    parcelaCarneOs.setNumeroParcela(i + 1);
//	    parcelaCarneOs.setDataEmissao(dataEmissão);
//
//	   if (i > 0) {
//	    primeiroVencimento.add(Calendar.DAY_OF_MONTH, carneOs.getIntervaloEntreParcelas());
//	   }
//	   parcelaCarneOs.setDataVencimento(primeiroVencimento.getTime());
//	    parcelaCarneOs.setEmitiuBoleto("S");
//
//	   nossoNumero = formatoNossoNumero5.format(carneOs.getCliente().getId());
//	   nossoNumero += formatoNossoNumero5.format(parcelaCarneOs.getContaCaixa().getId());
//	    nossoNumero += formatoNossoNumero4.format(parcelaCarneOs.getNumeroParcela());
//	   nossoNumero += formatoNossoNumero6.format(dataAtual);
//	   parcelaCarneOs.setBoletoNossoNumero(nossoNumero);
//	   parcelaCarneOs.setValor(valorParcela);
//	    somaParcelas = somaParcelas.add(valorParcela);
//
//	   if (i == (carneOs.getQuantidadeParcela() - 1)) {
//	    residuo = carneOs.getValorAReceber().subtract(somaParcelas);
//	    valorParcela = valorParcela.add(residuo);
//	     parcelaCarneOs.setValor(valorParcela);
//	   }
//
//	   parcelasCarneOs.add(parcelaCarneOs);
//	   novoParcelaReceber(parcelaCarneOs);
//	  }
//
//	  subView.getParcelasSubForm().fillWith(parcelasCarneOs);
//	 }
//
//	 public OrdemServicoCarne novoParcelaCarneOs() {
//		OrdemServicoCarne parcela = new OrdemServicoCarne();
//		return novoParcelaReceber(parcela);
//	}
//
//	public OrdemServicoCarne novoParcelaReceber(OrdemServicoCarne parcela) {
//		currentBean.addParcelaReceber(parcela);
//		return parcela;
//	}
//
//	public void removerParcelaReceber(List<OrdemServicoCarne> values) {
//		for (OrdemServicoCarne value : values) {
//			currentBean.removeParcelaCarneOs(value);
//		}
//	}
//
//
//
//
//		public void gerarParcelasCartaoOs() throws Exception {
//
//			if (validaCampos()) {
//				final List<OrdemServicoCartao> parcelasCartaoOs = new ArrayList<OrdemServicoCartao>();
//				List<OrdemServicoCartao> dados = subView.getParcelasCartaoSubForm().getDados();
//				if (dados != null) {
//					parcelasCartaoOs.addAll(subView.getParcelasCartaoSubForm().getDados());
//				}
//
//				if (parcelasCartaoOs != null && !parcelasCartaoOs.isEmpty()) {
//					ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
//							"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
//							new ConfirmDialog.Listener() {
//								private static final long serialVersionUID = 1L;
//
//								public void onClose(ConfirmDialog dialog) {
//									if (dialog.isConfirmed()) {
//										excluiParcelasCartaoOs(parcelasCartaoOs);
//										geraParcelasCartaoOs(parcelasCartaoOs);
//									}
//								}
//							});
//				} else {
//					geraParcelasCartaoOs(parcelasCartaoOs);
//				}
//
//			} else {
//				mensagemErro("Preencha todos os campos corretamente!");
//			}
//
//		}
//		
//	//controller
//	private void geraParcelasCartaoOs(final List<OrdemServicoCartao> parcelasCarne) {
//
//	  // gera as parcelas
//	  subView.getCartaoSubForm().removeAllItems();
//	  subView.preencheBean(currentBean);
//
//	  OrdemServicoCartao cartaoOs = currentBean;
//	  OrdemServicoCartao parcelaCartaoOs;
//	  Date dataEmissão = new Date();
//
//	  Calendar primeiroVencimento = Calendar.getInstance();
//
//	  primeiroVencimento.setTime(parcelaCartaoOs.getDataVencimento());
//
//	  BigDecimal valorParcela = cartaoOs.getValorAReceber().divide(BigDecimal.valueOf(cartaoOs.getQuantidadeParcela()), RoundingMode.HALF_DOWN);
//	  BigDecimal somaParcelas = BigDecimal.ZERO;
//	  BigDecimal residuo = BigDecimal.ZERO;
//
//	  String nossoNumero;
//	  DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
//	  DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
//
//	  SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");
//
//	  Date dataAtual = new Date();
//
//	  for (int i = 0; i < cartaoOs.getQuantidadeParcela(); i++) {
//	    parcelaCartaoOs = new OrdemServicoCartao();
//	    parcelaCartaoOs.setContaCaixa(contaCaixa);
//	    parcelaCartaoOs.setNumeroParcela(i + 1);
//	    parcelaCartaoOs.setDataEmissao(dataEmissão);
//
//	   if (i > 0) {
//	    primeiroVencimento.add(Calendar.DAY_OF_MONTH, cartaoOs.getIntervaloEntreParcelas());
//	   }
//	   parcelaCartaoOs.setDataVencimento(primeiroVencimento.getTime());
//	    parcelaCartaoOs.setEmitiuBoleto("S");
//
//	   nossoNumero = formatoNossoNumero5.format(cartaoOs.getCliente().getId());
//	   nossoNumero += formatoNossoNumero5.format(parcelaCartaoOs.getContaCaixa().getId());
//	    nossoNumero += formatoNossoNumero4.format(parcelaCartaoOs.getNumeroParcela());
//	   nossoNumero += formatoNossoNumero6.format(dataAtual);
//	   parcelaCartaoOs.setBoletoNossoNumero(nossoNumero);
//	   parcelaCartaoOs.setValor(valorParcela);
//	    somaParcelas = somaParcelas.add(valorParcela);
//
//	   if (i == (cartaoOs.getQuantidadeParcela() - 1)) {
//	    residuo = cartaoOs.getValorAReceber().subtract(somaParcelas);
//	    valorParcela = valorParcela.add(residuo);
//	     parcelaCartaoOs.setValor(valorParcela);
//	   }
//
//	   parcelasCartaoOs.add(parcelaCartaoOs);
//	   novoParcelaReceber(parcelaCartaoOs);
//	  }
//
//	  subView.getParcelasSubForm().fillWith(parcelasCartaoOs);
//	 }
//
//	 public OrdemServicoCartao novoParcelaCartaoOs() {
//		OrdemServicoCartao parcela = new OrdemServicoCartao();
//		return novoParcelaReceber(parcela);
//	}
//
//	public OrdemServicoCartao novoParcelaReceber(OrdemServicoCartao parcela) {
//		currentBean.addParcelaReceber(parcela);
//		return parcela;
//	}
//
//	public void removerParcelaReceber(List<OrdemServicoCartao> values) {
//		for (OrdemServicoCartao value : values) {
//			currentBean.removeParcelaCartaoOs(value);
//		}
//	}
	private void excluiParcelasCheque(List<OrdemServicoEfetivacao> parcelasReceber) {
		List<OrdemServicoEfetivacao> persistentObjects = subView.getParcelasChequeSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
//			parcelaChequeDAO.delete(persistentObjects.get(i));
		}
		parcelasReceber.clear();
	}
	private void excluiParcelasCarne(List<OrdemServicoEfetivacao> parcelasReceber) {
		List<OrdemServicoEfetivacao> persistentObjects = subView.getParcelasCarneSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
//			parcelaChequeDAO.delete(persistentObjects.get(i));
		}
		parcelasReceber.clear();
	}
	private void excluiParcelasCartao(List<OrdemServicoEfetivacao> parcelasReceber) {
		List<OrdemServicoEfetivacao> persistentObjects = subView.getParcelasCartaoSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
//			parcelaChequeDAO.delete(persistentObjects.get(i));
		}
		parcelasReceber.clear();
	}
	@Override
	public String getViewIdentifier() {
		return "ordemServicoForm";
	}

	@Override
	protected boolean validaSalvar() {
		return true;
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

	public void buscarOsAgrupada(Cliente cliente) {
		List<OrdemServico> osAgrupada = ordemServicoDAO.buscarOsPorCliente(cliente);
		subView.preencheOsAgrupadaSubForm(osAgrupada);
	}

	public List<Produto> buscarProdutos() {
		return produtoDAO.getAll(Produto.class);
	}
	
	public List<Acessorio> buscarAcessorio() {
		return acessorioDAO.getAll(Acessorio.class);
	}

	@Override
	public boolean isFullSized(){
		return true;
	}

	public InformacaoGeral novaInformacaoGeral(){
		InformacaoGeral d = new InformacaoGeral();
	//	currentBean.adicionarInformacaoGeral(d);
		return d;
	}
	
	public EntradaServico novoEntradaServico(){
		EntradaServico c = new EntradaServico();
		currentBean.adicionarEntradaServico(c);
		subView.preencheEntradaServicoFinanceiraSubForm(currentBean.getItensEntradaServico());
		return c;
	}
	public VendaPeca novoVendaPeca(){
		VendaPeca c = new VendaPeca();
		currentBean.adicionarVendaPeca(c);
		subView.preencheVendaPecaFinanceiraSubForm(currentBean.getItensVendaPeca());
		return c;
	}

	public MaterialServico novoMaterialServico(){
		MaterialServico c = new MaterialServico();
		currentBean.adicionarMaterialServico(c);
		return c;
	}
	
	public AcessorioOs novoAcessorioOs(){
		AcessorioOs c = new AcessorioOs();
		currentBean.adicionarAcessorioOs(c);
		return c;
	} 

	public void carregaTotais(){
//		currentBean.setValorServico(BigDecimal.ZERO);
		System.out.println("carregaTotais");
		
		if(subView.getEntradaServicoFinanceiraSubForm()!=null){
			if(subView.getEntradaServicoFinanceiraSubForm().getDados()!=null){
				for(EntradaServico es : subView.getEntradaServicoFinanceiraSubForm().getDados()){
					System.out.println("es.getValorTotal(): "+es.getValorTotal());
					currentBean.setValorServico(currentBean.getValorServico().add(es.getValorTotal()));
				}
			}
		}
	}
}

