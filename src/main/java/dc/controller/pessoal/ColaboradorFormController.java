package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import dc.controller.diversos.EstadoListController;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.diversos.Setor;
import dc.entidade.financeiro.Sindicato;
import dc.entidade.framework.PapelMenu;
import dc.entidade.geral.NivelFormacao;
import dc.entidade.geral.Pessoa;
import dc.entidade.geral.PessoaFisica;
import dc.entidade.geral.UF;
import dc.entidade.geral.Usuario;
import dc.entidade.pessoal.Cargo;
import dc.entidade.pessoal.Colaborador;
import dc.entidade.pessoal.SituacaoColaborador;
import dc.entidade.pessoal.TipoColaborador;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.diversos.SetorDAO;
import dc.servicos.dao.financeiro.SindicatoDAO;
import dc.servicos.dao.geral.NivelFormacaoDAO;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.pessoal.CargoDAO;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.dao.pessoal.SituacaoColaboradorDAO;
import dc.servicos.dao.pessoal.TipoColaboradorDAO;
import dc.servicos.dao.sistema.PapelDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;
import dc.visao.framework.geral.ControllerAcesso;
import dc.visao.framework.geral.MainController;
import dc.visao.pessoal.ColaboradorFormView;
import dc.visao.spring.SecuritySessionProvider;


@Controller
@Scope("prototype")
public class ColaboradorFormController extends CRUDFormController<Colaborador> {
	
	@Autowired
	private MainController mainController;

	private  ColaboradorFormView subView;

	@Autowired
	private ColaboradorDAO colaboradorDAO;
	
	@Autowired
	private PapelDAO daoPapel;
	
	@Autowired
	private PessoaDAO pessoaDAO;
	
	@Autowired
	private TipoColaboradorDAO tipoColaboradorDAO;
	
	@Autowired
	private SituacaoColaboradorDAO situacaoColaboradorDAO;
	
	@Autowired
	private SindicatoDAO sindicatoDAO;
	
	@Autowired
	private NivelFormacaoDAO nivelFormacaoDAO;
	
	@Autowired
	private CargoDAO cargoDAO;
	
	@Autowired
	private ContabilContaDAO contaContabilDAO;
	
	@Autowired
	private SetorDAO setorDAO;
	
	@Autowired
	private UFDAO ufDAO;
	
	private Colaborador currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtMatricula().getValue())) {
			adicionarErroDeValidacao(subView.getTxtMatricula(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtCategoria().getValue())) {
			adicionarErroDeValidacao(subView.getTxtCategoria(),
					"Não pode ficar em branco");
			valido = false;
		}
		return valido;
	}
	
	@Override
	protected void criarNovoBean() {
		currentBean = new Colaborador();
		
	}

	@Override
	protected void initSubView() {
		subView = new ColaboradorFormView();

		/* Configura combo Pessoa */
		//DefaultManyToOneComboModel<Pessoa> model= new DefaultManyToOneComboModel(PessoaFisicaListController.class,pessoaDAO,mainController,daoPapel);
		//subView.getCmbPessoa().setModel(model);
		
		DefaultManyToOneComboModel<TipoColaborador> modelTipo = new DefaultManyToOneComboModel(TipoColaboradorListController.class,tipoColaboradorDAO,mainController,daoPapel);
		subView.getCmbTipoColaborador().setModel(modelTipo);
		
		DefaultManyToOneComboModel<UF> modelUf= new DefaultManyToOneComboModel(EstadoListController.class,tipoColaboradorDAO,mainController,daoPapel);
		subView.getCmbUf().setModel(modelUf);
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = colaboradorDAO.find(id);
		
		subView.getDtCadastro().setValue(currentBean.getDataCadastro());
		subView.getDtAdmissao().setValue(currentBean.getDataAdmissao());
		subView.getDtVencimentoFerias().setValue(currentBean.getVencimentoFerias());
		subView.getDtTransferencia().setValue(currentBean.getDataTransferencia());
		subView.getDtDemissao().setValue(currentBean.getDataDemissao());
		subView.getDtUltimoExame().setValue(currentBean.getExameMedicoUltimo());
		subView.getDtVencimento().setValue(currentBean.getExameMedicoVencimento());
		subView.getDtDataOpcao().setValue(currentBean.getFgtsDataOpcao());
		subView.getDtCadastroPis().setValue(currentBean.getPisDataCadastro());
		subView.getTxtBanco1().setValue(currentBean.getPisBanco());
		subView.getTxtAgencia1().setValue(currentBean.getPisAgencia());
		subView.getTxtBanco().setValue(currentBean.getPagamentoBanco());
		subView.getTxtAgencia().setValue(currentBean.getPagamentoAgencia());
		subView.getTxtConta().setValue(currentBean.getPagamentoConta());
		subView.getTxtObservacao().setValue(currentBean.getObservacao());
		
		subView.getCmbPessoa().setValue(currentBean.getPessoa());
		
		

		subView.getCmbTipoColaborador().setValue(currentBean.getIdTipoColaborador());
		
		
		/* Configura combo Situação COLABORADOR */
		ManyToOneComboModel<SituacaoColaborador> modelsituacao = new ManyToOneComboModel<SituacaoColaborador>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<SituacaoColaborador> getResultado(String q) {
				return situacaoColaboradorDAO.query(q);
			}
			
			@Override
			public Class<SituacaoColaborador> getEntityClass() {
				return SituacaoColaborador.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(SituacaoColaborador value) {
				Notification.show("Selecionado Editar: " + value.getNome());
				
			}
		};
		subView.getCmbSituacaoColaborador().setModel(modelsituacao);
		subView.getCmbSituacaoColaborador().setValue(currentBean.getIdSituacaoColaborador());
		
		
		/* Configura combo SINDICATO */
		ManyToOneComboModel<Sindicato> modelsindicato = new ManyToOneComboModel<Sindicato>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<Sindicato> getResultado(String q) {
				return sindicatoDAO.query(q);
			}
			
			@Override
			public Class<Sindicato> getEntityClass() {
				return Sindicato.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(Sindicato value) {
				Notification.show("Selecionado Editar: " + value.getNome());
				
			}
		};
		subView.getCmbSindicato().setModel(modelsindicato);
		subView.getCmbSindicato().setValue(currentBean.getIdSindicato());
		
		
		/* Configura combo NIVEL FORMAção */
		ManyToOneComboModel<NivelFormacao> modelnivel = new ManyToOneComboModel<NivelFormacao>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<NivelFormacao> getResultado(String q) {
				return nivelFormacaoDAO.query(q);
			}
			
			@Override
			public Class<NivelFormacao> getEntityClass() {
				return NivelFormacao.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(NivelFormacao value) {
				Notification.show("Selecionado Editar: " + value.getNome());
				
			}
		};
		subView.getCmbNivelFormacao().setModel(modelnivel);
		subView.getCmbNivelFormacao().setValue(currentBean.getIdNivelFormacao());
		
		
		/* Configura combo CARGO */
		ManyToOneComboModel<Cargo> modelcargo = new ManyToOneComboModel<Cargo>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<Cargo> getResultado(String q) {
				return cargoDAO.query(q);
			}
			
			@Override
			public Class<Cargo> getEntityClass() {
				return Cargo.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(Cargo value) {
				Notification.show("Selecionado Editar: " + value.getNome());
				
			}
		};
		subView.getCmbCargo().setModel(modelcargo);
		subView.getCmbCargo().setValue(currentBean.getIdCargo());
		
		
		/* Configura combo Contábil CONTA */
		ManyToOneComboModel<ContabilConta> modelconta = new ManyToOneComboModel<ContabilConta>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<ContabilConta> getResultado(String q) {
				return contaContabilDAO.query(q);
			}
			
			@Override
			public Class<ContabilConta> getEntityClass() {
				return ContabilConta.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(ContabilConta value) {
				Notification.show("Selecionado Editar: " + value.getClassificacao());
				
			}
		};
		subView.getCmbContaContabil().setModel(modelconta);
		subView.getCmbContaContabil().setValue(currentBean.getIdContaContabil());
		
		
		/* Configura combo SETOR */
		ManyToOneComboModel<Setor> modelsetor = new ManyToOneComboModel<Setor>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<Setor> getResultado(String q) {
				return setorDAO.query(q);
			}
			
			@Override
			public Class<Setor> getEntityClass() {
				return Setor.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(Setor value) {
				Notification.show("Selecionado Editar: " + value.getNome());
				
			}
		};
		subView.getCmbSetor().setModel(modelsetor);
		subView.getCmbSetor().setValue(currentBean.getIdSetor());

		subView.getCmbUf().setValue(currentBean.getCtpsUf());
		

	}

	@Override
	protected void actionSalvar() {
		
		currentBean.setPessoa((Pessoa) subView.getCmbPessoa().getValue());
		currentBean.setIdTipoColaborador((TipoColaborador) subView.getCmbTipoColaborador().getValue());
		currentBean.setIdSituacaoColaborador((SituacaoColaborador) subView.getCmbSituacaoColaborador().getValue());
		currentBean.setIdSindicato((Sindicato) subView.getCmbSindicato().getValue());
		currentBean.setIdNivelFormacao((NivelFormacao) subView.getCmbNivelFormacao().getValue());
		currentBean.setIdCargo((Cargo) subView.getCmbCargo().getValue());
		currentBean.setIdContaContabil((ContabilConta) subView.getCmbContaContabil().getValue());
		currentBean.setCtpsUf((UF) subView.getCmbUf().getValue());

		currentBean.setDataCadastro(subView.getDtCadastro().getValue());
		currentBean.setDataAdmissao(subView.getDtAdmissao().getValue());
		currentBean.setVencimentoFerias(subView.getDtVencimentoFerias().getValue());
		currentBean.setDataTransferencia(subView.getDtTransferencia().getValue());
		currentBean.setDataDemissao(subView.getDtDemissao().getValue());
		currentBean.setExameMedicoUltimo(subView.getDtUltimoExame().getValue());
		currentBean.setExameMedicoVencimento(subView.getDtVencimento().getValue());
		currentBean.setFgtsDataOpcao(subView.getDtDataOpcao().getValue());
		currentBean.setPisDataCadastro(subView.getDtCadastroPis().getValue());
		currentBean.setPisBanco(subView.getTxtBanco1().getValue());
		currentBean.setPisAgencia(subView.getTxtAgencia1().getValue());
		currentBean.setPagamentoBanco(subView.getTxtBanco().getValue());
		currentBean.setPagamentoAgencia(subView.getTxtAgencia().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());
		
		try {
			colaboradorDAO.saveOrUpdate(currentBean);


			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		
	}

	@Override
	protected void quandoNovo() {
	}

	@Override
	protected String getNome() {
		return "Colaborador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		colaboradorDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}
	
	@Override
	public String getViewIdentifier() {
		return "colaboradorForm";
	}
	
	@Override
	public boolean isFullSized(){
	   return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}
}