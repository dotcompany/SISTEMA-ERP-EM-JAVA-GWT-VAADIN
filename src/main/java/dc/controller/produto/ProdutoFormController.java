package dc.controller.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

import dc.entidade.diversos.Almoxarifado;
import dc.entidade.produto.Produto;
import dc.entidade.produto.SubGrupoProduto;
import dc.entidade.produto.UnidadeProduto;
import dc.entidade.tributario.GrupoTributario;
import dc.servicos.dao.diversos.AlmoxarifadoDAO;
import dc.servicos.dao.produto.MarcaProdutoDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.produto.SubGrupoProdutoDAO;
import dc.servicos.dao.produto.UnidadeProdutoDAO;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.produto.ProdutoFormView;


@Controller
@Scope("prototype")
public class ProdutoFormController extends CRUDFormController<Produto> {

	private ProdutoFormView subView;

	@Autowired
	private ProdutoDAO produtoDAO;
	
	/*@Autowired
	private SubGrupoProdutoDAO subGrupoProdutoDAO;*/
	
	@Autowired
	private UnidadeProdutoDAO unidadeProdutoDAO;
	
	@Autowired
	private MarcaProdutoDAO marcaProdutoDAO;
	
	@Autowired
	private AlmoxarifadoDAO almoxarifadoDAO;
	
	@Autowired
	private GrupoTributarioDAO grupoTributarioDAO;
	
	@Autowired
	private SubGrupoProdutoDAO subGrupoProdutoDAO;
	
	private Produto currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}
		return valido;
	}
	
	@Override
	protected void criarNovoBean() {
		currentBean = new Produto();
		
	}

	@Override
	protected void initSubView() {
		subView = new ProdutoFormView();
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = produtoDAO.find(id);
		
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		
		/* Configura combo Sub Grupo Produto*/
		/*ManyToOneComboModel<SubGrupoProduto> modelsub = new ManyToOneComboModel<SubGrupoProduto>() {

			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}

			@Override
			public List<SubGrupoProduto> getResultado(String q) {
				return subGrupoProdutoDAO.query(q);
			}

			@Override
			public Class<SubGrupoProduto> getEntityClass() {
				return SubGrupoProduto.class;
			}

			@Override
			public String getCaptionProperty() {
				return "descricao";
			}

			@Override
			public void onEditar(SubGrupoProduto value) {
				Notification.show("Selecionado Editar: " + value.getDescricao());

			}
		};
		subView.getCmbSubGrupoProduto().setModel(modelsub);
		subView.getCmbSubGrupoProduto().setValue(currentBean.getIdSubGrupo());*/
		
		/* Configura combo Unidade Produto */
		/*ManyToOneComboModel<UnidadeProduto> model = new ManyToOneComboModel<UnidadeProduto>() {

			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}

			@Override
			public List<UnidadeProduto> getResultado(String q) {
				return unidadeProdutoDAO.query(q);
			}

			@Override
			public Class<UnidadeProduto> getEntityClass() {
				return UnidadeProduto.class;
			}

			@Override
			public String getCaptionProperty() {
				return "sigla";
			}

			@Override
			public void onEditar(UnidadeProduto value) {
				Notification.show("Selecionado Editar: " + value.getSigla());

			}
		};
		subView.getCmbUnidadeProduto().setModel(model);
		subView.getCmbUnidadeProduto().setValue(currentBean.getUnidade());*/
		
		
		/* Configura combo Marca Produto */
		/*ManyToOneComboModel<MarcaProduto> modelmarca = new ManyToOneComboModel<MarcaProduto>() {

			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}

			@Override
			public List<MarcaProduto> getResultado(String q) {
				return marcaProdutoDAO.query(q);
			}

			@Override
			public Class<MarcaProduto> getEntityClass() {
				return MarcaProduto.class;
			}

			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(MarcaProduto value) {
				Notification.show("Selecionado Editar: " + value.getNome());

			}
		};
		subView.getCmbMarcaProduto().setModel(modelmarca);
		subView.getCmbMarcaProduto().setValue(currentBean.getIdMarcaProduto());*/
		
		
		/* Configura combo Almoxarifadooooo */
          ManyToOneComboModel<Almoxarifado> modelalmoxarifado = new ManyToOneComboModel<Almoxarifado>() {

			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}

			@Override
			public List<Almoxarifado> getResultado(String q) {
				return almoxarifadoDAO.query(q);
			}

			@Override
			public Class<Almoxarifado> getEntityClass() {
				return Almoxarifado.class;
			}

			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(Almoxarifado value) {
				Notification.show("Selecionado Editar: " + value.getNome());

			}
		};
		subView.getCmbAlmoxarifado().setModel(modelalmoxarifado);
		subView.getCmbAlmoxarifado().setValue(currentBean.getIdAlmoxarifado());
		
		
		/* Configura combo Grupo Tributárioooo*/
		ManyToOneComboModel<GrupoTributario> modelgrupo = new ManyToOneComboModel<GrupoTributario>() {

			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}

			@Override
			public List<GrupoTributario> getResultado(String q) {
				return grupoTributarioDAO.query(q);
			}

			@Override
			public Class<GrupoTributario> getEntityClass() {
				return GrupoTributario.class;
			}

			@Override
			public String getCaptionProperty() {
				return "descricao";
			}

			@Override
			public void onEditar(GrupoTributario value) {
				Notification.show("Selecionado Editar: " + value.getDescricao());

			}
		};
		subView.getCmbGrupoTributario().setModel(modelgrupo);
		subView.getCmbGrupoTributario().setValue(currentBean.getIdGrupoTributario());
	}

	@Override
	protected void actionSalvar() {

		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		
		try {
			produtoDAO.saveOrUpdate(currentBean);


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
		return "Produto";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		produtoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}
	
	@Override
	public String getViewIdentifier() {
		return "produtoForm";
	}
	
	public Produto getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(Produto currentBean) {
		this.currentBean = currentBean;
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