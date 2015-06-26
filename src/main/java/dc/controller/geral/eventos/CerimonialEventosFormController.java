package dc.controller.geral.eventos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.eventos.CerimonialEventosUtil;
import dc.control.validator.DotErpException;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.eventos.CerimonialEventosEntity;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.dao.geral.eventos.CerimonialEventosDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.eventos.CerimonialEventosFormView;

@Controller
@Scope("prototype")
public class CerimonialEventosFormController extends CRUDFormController<CerimonialEventosEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CerimonialEventosFormView subView;

	/**
	 * ENTITY
	 */

	private CerimonialEventosEntity entity;

	/**
	 * BUSINESS
	 */

	//@Autowired
	//private CerimonialBusiness<CerimonialEventosEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private CerimonialEventosDAO cerimonialEventosDAO;
	
	@Autowired
	private UfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public CerimonialEventosFormController() {
		// TODO Auto-generated constructor stub
	}

	//public CerimonialBusiness<CerimonialEventosEntity> getBusiness() {
	//	return business;
	//}

	@Override
	protected String getNome() {
		return "Cerimonial Eventos";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public CerimonialEventosEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new CerimonialEventosFormView(this);
			
			carregarUf();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validaSalvar() {
		try {
			CerimonialEventosUtil.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			
			this.entity.setNome(this.subView.getTxtNome().getValue());
			this.entity.setEndereco(this.subView.getTfEndereco().getValue());
			this.entity.setCnpj(this.subView.getTxtCnpj().getValue());
			this.entity.setComplemento(this.subView.getTxtComplemento().getValue());
			this.entity.setBairro(this.subView.getTfBairro().getValue());
			this.entity.setCidade(this.subView.getTfCidade().getValue());
			this.entity.setCep(this.subView.getTfCep().getValue());
			this.entity.setTelefone(this.subView.getTfTelefone().getValue());
			this.entity.setContato(this.subView.getTxtContato().getValue());
			this.entity.setCelular(this.subView.getTfCelular().getValue());
			
			UfEntity uf = (UfEntity) this.subView.getCmbUf().getValue();

			this.entity.setSiglaUf(uf.getSigla());
			this.entity.setUf(uf);



			//this.business.saveOrUpdate(this.entity);
			this.cerimonialEventosDAO.saveOrUpdate(this.entity);


			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	protected void carregar(Serializable id) {
		try {
			//this.entity = this.business.find(id);
			
			this.entity = this.cerimonialEventosDAO.find(id);

			
			this.subView.getTxtNome().setValue(this.entity.getNome());
			this.subView.getTfEndereco().setValue(this.entity.getEndereco());
			this.subView.getTfBairro().setValue(this.entity.getBairro());
			this.subView.getTfCep().setValue(this.entity.getCep());
			this.subView.getTxtCnpj().setValue(this.entity.getCnpj());
			this.subView.getTxtComplemento().setValue(this.entity.getComplemento());
			this.subView.getTfTelefone().setValue(this.entity.getTelefone());
			this.subView.getTxtContato().setValue(this.entity.getContato());
			this.subView.getTfCelular().setValue(this.entity.getCelular());
			
			UfEntity uf = this.entity.getUf();

			if (ObjectUtils.isNotBlank(uf)) {
				this.subView.getCmbUf().setValue(uf);
			}



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new CerimonialEventosEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new CerimonialEventosEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			//this.business.deleteAll(ids);
			
			this.cerimonialEventosDAO.deleteAllByIds(ids);


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

	public void carregarUf() {
		try {
			List<UfEntity> auxLista = this.ufDAO.listaTodos();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			this.subView.getCmbUf().setContainerDataSource(bic);
			this.subView.getCmbUf().setItemCaptionPropertyId("nome");
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}


	/**
	 * 
	 */

}