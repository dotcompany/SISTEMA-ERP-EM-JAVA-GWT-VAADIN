package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.classes.NivelFormacaoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.NivelFormacaoEntity;
import dc.model.business.geral.pessoal.NivelFormacaoBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.NivelFormacaoFormView;

@Controller
@Scope("prototype")
public class NivelFormacaoFormController extends
		CRUDFormController<NivelFormacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NivelFormacaoFormView subView;

	/**
	 * ENTITY
	 */

	private NivelFormacaoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private NivelFormacaoBusiness<NivelFormacaoEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public NivelFormacaoFormController() {
		// TODO Auto-generated constructor stub
	}

	public NivelFormacaoBusiness<NivelFormacaoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Estado civil";
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
	public NivelFormacaoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new NivelFormacaoFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			NivelFormacaoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setDescricao(this.subView.getTfDescricao().getValue());

			String grauInstrucaoCaged = (String) this.subView
					.getTfGrauInstrucaoCaged().getConvertedValue();

			if (NumberUtils.isNumber(grauInstrucaoCaged)) {
				this.entity.setGrauInstrucaoCaged(NumberUtils
						.toInt(grauInstrucaoCaged));
			} else {
				this.entity.setGrauInstrucaoCaged(0);
			}

			String grauInstrucaoRais = (String) this.subView
					.getTfGrauInstrucaoRais().getConvertedValue();

			if (NumberUtils.isNumber(grauInstrucaoRais)) {
				this.entity.setGrauInstrucaoRais(NumberUtils
						.toInt(grauInstrucaoRais));
			} else {
				this.entity.setGrauInstrucaoRais(0);
			}

			String grauInstrucaoSefip = (String) this.subView
					.getTfGrauInstrucaoSefip().getConvertedValue();

			if (NumberUtils.isNumber(grauInstrucaoSefip)) {
				this.entity.setGrauInstrucaoSefip(NumberUtils
						.toInt(grauInstrucaoSefip));
			} else {
				this.entity.setGrauInstrucaoSefip(0);
			}

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getTfNome().setValue(this.entity.getNome());
			this.subView.getTfDescricao().setValue(this.entity.getDescricao());

			Integer grauInstrucaoCaged = this.entity.getGrauInstrucaoCaged();

			if (NumberUtils.isNotBlank(grauInstrucaoCaged)) {
				this.subView.getTfGrauInstrucaoCaged().setValue(
						grauInstrucaoCaged.toString());
			}

			Integer grauInstrucaoRais = this.entity.getGrauInstrucaoRais();

			if (NumberUtils.isNotBlank(grauInstrucaoRais)) {
				this.subView.getTfGrauInstrucaoRais().setValue(
						grauInstrucaoRais.toString());
			}

			Integer grauInstrucaoSefip = this.entity.getGrauInstrucaoSefip();

			if (NumberUtils.isNotBlank(grauInstrucaoSefip)) {
				this.subView.getTfGrauInstrucaoSefip().setValue(
						grauInstrucaoSefip.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new NivelFormacaoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

}