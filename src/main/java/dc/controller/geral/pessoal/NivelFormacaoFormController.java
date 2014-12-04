package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.NivelFormacaoValidator;
import dc.entidade.geral.NivelFormacaoEntity;
import dc.servicos.dao.geral.NivelFormacaoDAO;
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

	private NivelFormacaoEntity currentBean;

	@Autowired
	private NivelFormacaoDAO nivelFormacaoDAO;

	@Override
	protected String getNome() {
		return "Nivel de formação";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected boolean validaSalvar() {
		try {
			NivelFormacaoValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setDescricao(this.subView.getTfDescricao()
					.getValue());

			String grauInstrucaoCaged = (String) this.subView
					.getTfGrauInstrucaoCaged().getConvertedValue();

			if (NumberUtils.isNumber(grauInstrucaoCaged)) {
				this.currentBean.setGrauInstrucaoCaged(NumberUtils
						.toInt(grauInstrucaoCaged));
			} else {
				this.currentBean.setGrauInstrucaoCaged(0);
			}

			String grauInstrucaoRais = (String) this.subView
					.getTfGrauInstrucaoRais().getConvertedValue();

			if (NumberUtils.isNumber(grauInstrucaoRais)) {
				this.currentBean.setGrauInstrucaoRais(NumberUtils
						.toInt(grauInstrucaoRais));
			} else {
				this.currentBean.setGrauInstrucaoRais(0);
			}

			String grauInstrucaoSefip = (String) this.subView
					.getTfGrauInstrucaoSefip().getConvertedValue();

			if (NumberUtils.isNumber(grauInstrucaoSefip)) {
				this.currentBean.setGrauInstrucaoSefip(NumberUtils
						.toInt(grauInstrucaoSefip));
			} else {
				this.currentBean.setGrauInstrucaoSefip(0);
			}

			this.nivelFormacaoDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.nivelFormacaoDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfDescricao().setValue(
					this.currentBean.getDescricao());

			Integer grauInstrucaoCaged = this.currentBean
					.getGrauInstrucaoCaged();

			if (NumberUtils.isNotBlank(grauInstrucaoCaged)) {
				this.subView.getTfGrauInstrucaoCaged().setValue(
						grauInstrucaoCaged.toString());
			}

			Integer grauInstrucaoRais = this.currentBean.getGrauInstrucaoRais();

			if (NumberUtils.isNotBlank(grauInstrucaoRais)) {
				this.subView.getTfGrauInstrucaoRais().setValue(
						grauInstrucaoRais.toString());
			}

			Integer grauInstrucaoSefip = this.currentBean
					.getGrauInstrucaoSefip();

			if (NumberUtils.isNotBlank(grauInstrucaoSefip)) {
				this.subView.getTfGrauInstrucaoSefip().setValue(
						grauInstrucaoSefip.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new NivelFormacaoFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new NivelFormacaoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.nivelFormacaoDAO.deleteAllByIds(ids);

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

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public NivelFormacaoEntity getModelBean() {
		return currentBean;
	}

}