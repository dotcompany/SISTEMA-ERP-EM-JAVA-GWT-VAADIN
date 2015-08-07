package dc.controller.suprimento.estoque;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.suprimentos.estoque.LoteProdutoEntity;
import dc.model.dao.suprimento.estoque.LoteProdutoDAO;
import dc.servicos.dao.nfe.NfeDetalheDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.estoque.LoteProdutoFormView;

@Controller
@Scope("prototype")
public class LoteProdutoFormController extends CRUDFormController<LoteProdutoEntity> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LoteProdutoEntity entity;
	LoteProdutoFormView subView;
	
	@Autowired
	private LoteProdutoDAO loteProdutoDAO;
	
	@Autowired
	private NfeDetalheDAO nfeDetalheDAO;

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean validaSalvar() {
		try {
	        // Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
	        fieldGroup.commit();
	        return true;
	    } catch (FieldGroup.CommitException ce) {
	        return false;
	    }
	}

	@Override
	protected void criarNovoBean() {
		try {
	        this.entity = new LoteProdutoEntity();

	        // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
	        //fieldGroup.setItemDataSource(this.entity);

	    } catch (Exception e) {
	        e.printStackTrace();
	        mensagemErro(e.getMessage());
	    }
		
	}

	@Override
	protected void initSubView() {
       try {
			
			subView = new LoteProdutoFormView(this);
			
			//this.fieldGroup = new DCFieldGroup<>(LoteProdutoEntity.class);
			
			// Mapeia os campos
			/*fieldGroup.bind(this.subView.getTxQuantidadeParcela(),"quantidadeParcela");
			fieldGroup.bind(this.subView.getDtPrimeiroVencimento(),"primeiroVencimento");
			fieldGroup.bind(this.subView.getCbDocumentoOrigem(),"documentoOrigem");
			fieldGroup.bind(this.subView.getCbCliente(),"cliente");
			
			this.subView.getCbContaCaixa().configuraCombo(
					"nome", ContaCaixaListController.class, this.contaCaixaDAO, this.getMainController());
			this.subView.getCbDocumentoOrigem().configuraCombo(
					"descricao", DocumentoOrigemListController.class, this.documentoOrigemDAO, this.getMainController());
			this.subView.getCbCliente().configuraCombo(
					"pessoa.nome", ClienteListController.class, this.clienteDAO, this.getMainController());*/

		}catch (Exception e) {
		   e.printStackTrace();
		}
		
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.loteProdutoDAO.find(id);
			
			// Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
			//fieldGroup.setItemDataSource(this.currentBean);
			
			List<NfeDetalheEntity> itens = nfeDetalheDAO.findByNfeDetalhe(entity);
			subView.preencheSubForm(itens);
			
		} catch (Exception e) {
		       e.printStackTrace();
		}
		
	}

	@Override
	protected void actionSalvar() {
		try {
			
			loteProdutoDAO.saveOrUpdate(entity);
			notifiyFrameworkSaveOK(this.entity);
				
		}catch (Exception e) {
		        e.printStackTrace();
		        mensagemErro(e.getMessage());
		}
		
	}

	@Override
	protected Component getSubView() {
		// TODO Auto-generated method stub
		return subView;
	}

	@Override
	protected String getNome() {
		// TODO Auto-generated method stub
		return "Lote Produto ";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			//this.business.deleteAll(ids);
			this.loteProdutoDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
		
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		try {
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
		
	}

	@Override
	public LoteProdutoEntity getModelBean() {
		return entity;
	}

	public NfeDetalheEntity novoNfe() {
		NfeDetalheEntity nfe = entity.addNfe();
		return nfe;
	}

	public void removerNfe(
			List<NfeDetalheEntity> values) {
		for (NfeDetalheEntity value : values) {
			entity.removeNfe(value);
		}

	}
	
	@Override
	public boolean isFullSized() {
		return true;
	}

	public List<NfeDetalheEntity> getNfeDetalhe() {
		return nfeDetalheDAO.listarTodos();
	}
	
}
