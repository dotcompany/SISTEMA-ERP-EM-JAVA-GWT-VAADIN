package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.financeiro.TipoPagamentoListController;
import dc.controller.pessoal.ClienteListController;
import dc.controller.pessoal.ColaboradorListController;
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
import dc.servicos.dao.ordemservico.OrdemServicoDAO;
import dc.servicos.dao.ordemservico.ServicoOsDAO;
import dc.servicos.dao.ordemservico.SituacaoServicoDAO;
import dc.servicos.dao.ordemservico.StatusOsDAO;
import dc.servicos.dao.ordemservico.TipoServicoDAO;
import dc.servicos.dao.ordemservico.VendaPecaDAO;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.OrdemServicoFormView;

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

	private OrdemServico currentBean;

	OrdemServicoFormView subView;

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
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new OrdemServico();

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
}

