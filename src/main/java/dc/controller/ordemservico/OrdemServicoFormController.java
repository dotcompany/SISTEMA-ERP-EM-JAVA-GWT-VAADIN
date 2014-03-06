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
import dc.entidade.ordemservico.EntradaServico;
import dc.entidade.ordemservico.InformacaoGeral;
import dc.entidade.ordemservico.LaudoTecnico;
import dc.entidade.ordemservico.MaterialServico;
import dc.entidade.ordemservico.OrdemServico;
import dc.entidade.ordemservico.ServicoOs;
import dc.entidade.ordemservico.SituacaoServico;
import dc.entidade.ordemservico.StatusOs;
import dc.entidade.ordemservico.TipoServico;
import dc.entidade.ordemservico.VendaPeca;
import dc.entidade.pessoal.Cliente;
import dc.entidade.pessoal.Colaborador;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.PedidoDetalhe;
import dc.servicos.dao.financeiro.TipoPagamentoDAO;
import dc.servicos.dao.ordemservico.AcessorioDAO;
import dc.servicos.dao.ordemservico.CarroDAO;
import dc.servicos.dao.ordemservico.EntradaServicoDAO;
import dc.servicos.dao.ordemservico.GarantiaDAO;
import dc.servicos.dao.ordemservico.InformacaoGeralDAO;
import dc.servicos.dao.ordemservico.LaudoTecnicoDAO;
import dc.servicos.dao.ordemservico.MarcaDAO;
import dc.servicos.dao.ordemservico.MaterialServicoDAO;
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
	StatusOsDAO statusOsDAO;

	@Autowired
	TipoServicoDAO tipoServicoDAO;
	@Autowired
	SituacaoServicoDAO situacaoServicoDAO;

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
	ObservacaoDAO observacaoDAO;

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

//			salvarInformacaoGeral();	
//			salvarLaudoTecnico();
//			salvarEntradaServico();
//			salvarTransporte();
//			salvarFatura();

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
		 
	}

	public void salvarInformacaoGeral(){
	//	InformacaoGeral informacaoGeral = informacaoGeralDAO.buscaInformacaoGeral(currentBean);
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
			if(subView.getCbAtendente()!=null){
				informacaoGeral.setAtendente(subView.getCbAtendente().getValue());
			}
			if(subView.getTfFone()!=null){
				informacaoGeral.setTelefone(subView.getTfFone().getValue());
			}
			if(subView.getTfkm()!=null){
				informacaoGeral.setTelefone(subView.getTfkm().getValue());
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
//			informacaoGeralDAO.saveOrUpdate(informacaoGeral);
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
		//	laudoTecnicoDAO.saveOrUpdate(laudoTecnico);
		}
	}
	

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find((Integer) id);
		
//		subView.getCbTipoServico().setValue(currentBean.);
		
		
//		subView.carregarFormaPagamento();
//		subView.carregarFormaEmissao();
//		subView.carregarTipoOperacao();
//		subView.carregarFinalidadeEmissao();
//		subView.carregarTipoDANFE();
//		subView.carregarCRT();
	//	subView.carregarView(currentBean);

//		carregarEmitente();
//		carregarCuponsVinculados();	
//		carregarNotasReferenciadas();
//		carregarLocalEntrega(); 
//		carregarLocalRetirada();
//		carregarTransporte();
//		carregarFatura();
//		carregarDuplicatas();

	}

//	public void carregarFatura(){
//		NfeFatura fatura = faturaDAO.buscaFaturaPorNota(currentBean);
//
//		if(fatura != null){
//			subView.getNumeroFatura().setValue(fatura.getNumero());
//			subView.getValorOriginalFatura().setValue(fatura.getValorOriginal().toString());
//			subView.getValorDescontoFatura().setValue(fatura.getValorDesconto().toString());
//			subView.getValorLiquidoFatura().setValue(fatura.getValorLiquido().toString());
//		}
//	}
//
//	public void carregarTransporte(){
//		try{
//			NFeTransporte transporte = transporteDAO.buscaTransportePorNota(currentBean);
//			if(transporte!=null){
//				subView.getCpfTransp().setValue(transporte.getCpfCnpj());
//				subView.getRazaoSocialTransp().setValue(transporte.getRazaoSocial());
//				subView.getInscricaoEstadualTransp().setValue(transporte.getInscricaoEstadual());
//				subView.getLogradouroTransp().setValue(transporte.getLogradouro());
//				subView.getCidadeTransp().setValue(transporte.getCidade());
//				subView.getUfTransp().setValue(transporte.getUf());
//				if(transporte.getCodigoIBGE()!=null )subView.getCodigoMunicipioTransp().setValue(transporte.getCodigoIBGE().toString());
//				if(transporte.getCfop()!=null )subView.getCfopTransp().setValue(transporte.getCfop().toString());
//				if(transporte.getBaseCalculo()!=null )subView.getBaseCalculoTransp().setValue(transporte.getBaseCalculo().toString());
//				if(transporte.getAliquota()!=null )subView.getAliquotaTransp().setValue(transporte.getAliquota().toString());
//				if(transporte.getValorServico()!=null )	subView.getValorServicoTransp().setValue(transporte.getValorServico().toString());
//				if(transporte.getValorIcmsRetido()!=null )subView.getIcmsRetidoTransp().setValue(transporte.getValorIcmsRetido().toString());
//				if(transporte.getUfVeiculo()!=null )subView.getUfVeiculo().setValue(transporte.getUfVeiculo().toString());
//				subView.getPlacaVeiculo().setValue(transporte.getPlacaVeiculo());
//				subView.getRntcVeiculo().setValue(transporte.getRntcVeiculo());
//			}
//
//		}catch(Exception e){
//			System.out.println("PROBLEMA AO CARREGAR TRANSPORTE");
//			e.printStackTrace();
//		}
//	}

//	public void carregarEmitente(){
//
//		try{
//			NotaFiscalEmitente emitente = emitenteDAO.findByNota(currentBean);
//			subView.carregarViewCRT(emitente);
//			if(emitente!=null) {
//
//				subView.getCpfCnpjEm().setValue(emitente.getCpfCnpj());
//				subView.getRazaoEm().setValue(emitente.getRazaoSocial());
//				subView.getFantasiaEm().setValue(emitente.getNomeFantasia());
//                subView.getCep().setValue(emitente.getCep());
//				subView.getLogradouro().setValue(emitente.getLogradouro());
//				subView.getNumero().setValue(emitente.getNumero());
//				subView.getComplemento().setValue(emitente.getComplemento());
//
//				subView.getBairro().setValue(emitente.getBairro());
//				subView.getCodigoMunicipio().setValue(emitente.getCodigoIBGE().toString());
//				subView.getCidade().setValue(emitente.getCidade());
//				subView.getUf().setValue(emitente.getUf());
//				subView.getInscricao().setValue(emitente.getInscricaoEstadual());
//
//				subView.getTelefone().setValue(emitente.getTelefone());
//			}
//
//		}catch(Exception e){
//			System.out.println("PROBLEMA AO CARREGAR EMITENTE");
//			e.printStackTrace();
//		}
//
//	}
//
//	public void carregarLocalEntrega(){
//		NfeLocalEntrega entrega = localEntregaDAO.buscaEntregaPorNota(currentBean);
//
//		try{
//			if(entrega!=null){
//				subView.getCnpjEnt().setValue(entrega.getCpfCnpj());
//				subView.getLogradouroEnt().setValue(entrega.getLogradouro());
//				subView.getNumeroEnt().setValue(entrega.getNumero());
//				subView.getComplementoEnt().setValue(entrega.getComplemento());
//				subView.getBairroEnt().setValue(entrega.getBairro());
//				if(entrega.getCodigoMunicipio()!=null)subView.getIbgeEnt().setValue(entrega.getCodigoMunicipio().toString());
//				subView.getCidadeEnt().setValue(entrega.getCidade());
//				subView.getUfEnt().setValue(entrega.getUf());
//			}	  
//		}catch(Exception e){
//
//			e.printStackTrace();
//		}
//	}
//
//	public void carregarLocalRetirada(){
//		NfeLocalRetirada retirada = localRetiradaDAO.buscaRetiradaPorNota(currentBean);
//
//		try{
//			if(retirada!=null){
//				subView.getCnpjRet().setValue(retirada.getCpfCnpj());
//				subView.getLogradouroRet().setValue(retirada.getLogradouro());
//				subView.getNumeroRet().setValue(retirada.getNumero());
//				subView.getComplementoRet().setValue(retirada.getComplemento());
//				subView.getBairroRet().setValue(retirada.getBairro());
//			    if(retirada.getCodigoMunicipio()!=null)subView.getIbgeRet().setValue(retirada.getCodigoMunicipio().toString());
//				subView.getCidadeRet().setValue(retirada.getCidade());
//				subView.getUfRet().setValue(retirada.getUf());
//			}	  
//		}catch(Exception e){
//
//			e.printStackTrace();
//		}
//	}
//
//	public void carregarCuponsVinculados(){
//		try{
//			currentBean.setCuponsVinculados(cupomDAO.buscaCuponsPorNota(currentBean));
//			subView.preencheCupomSubForm(currentBean.getCuponsVinculados());	 
//		}catch(Exception e){
//			System.out.println("PROBLEMA AO CARREGAR CUPONS VINCULADOS");
//			e.printStackTrace();
//		}
//
//	}
//
//	public void carregarNotasReferenciadas(){
//		try{
//			currentBean.setNotasReferenciados(notaReferenciadaDAO.buscaNotasReferenciadas(currentBean));
//			subView.preencherNotasSubForm(currentBean.getNotasReferenciadas());	
//		}catch(Exception e){
//			System.out.println("PROBLEMA AO CARREGAR NOTAS REFERENCIADAS");
//			e.printStackTrace();
//		}
//
//	}
//	
//	public void carregarDuplicatas(){
//		try{
//			currentBean.setDuplicatas(duplicataDAO.buscarDuplicatasPorNota(currentBean));
//			subView.preencherDuplicatasSubForm(currentBean.getDuplicatas());	
//		}catch(Exception e){
//			System.out.println("PROBLEMA AO CARREGAR NOTAS DUPLICATAS");
//			e.printStackTrace();
//		}
//
//	}



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
		return c;
	}
	public VendaPeca novoVendaPeca(){
		VendaPeca c = new VendaPeca();
		currentBean.adicionarVendaPeca(c);
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

