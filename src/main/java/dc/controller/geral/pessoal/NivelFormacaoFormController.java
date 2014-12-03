package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
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

	@Autowired
	private NivelFormacaoDAO nivelFormacaoDAO;

	private NivelFormacaoEntity currentBean;

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
		if (this.subView.getTfNome().getValue() == null
				|| this.subView.getTfNome().getValue().isEmpty()) {
			adicionarErroDeValidacao(this.subView.getTfNome(),
					"Não pode ficar em branco!");

			return false;
		}

		return true;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setDescricao(this.subView.getTfDescricao()
					.getValue());

			String grauInstrucaoCaged = (String) this.subView
					.getTfGrauInstrucaoCaged().getConvertedValue();

			if (grauInstrucaoCaged != null && !grauInstrucaoCaged.equals("")) {
				this.currentBean.setGrauInstrucaoCaged(Integer
						.valueOf(grauInstrucaoCaged));
			}

			String grauInstrucaoRais = (String) this.subView
					.getTfGrauInstrucaoRais().getConvertedValue();

			if (grauInstrucaoRais != null && !grauInstrucaoRais.equals("")) {
				this.currentBean.setGrauInstrucaoRais(Integer
						.valueOf(grauInstrucaoRais));
			}

			String grauInstrucaoSefip = (String) this.subView
					.getTfGrauInstrucaoSefip().getConvertedValue();

			if (grauInstrucaoSefip != null && !grauInstrucaoSefip.equals("")) {
				this.currentBean.setGrauInstrucaoSefip(Integer
						.valueOf(grauInstrucaoSefip));
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

			if (grauInstrucaoCaged != null) {
				this.subView.getTfGrauInstrucaoCaged().setValue(
						grauInstrucaoCaged.toString());
			}

			Integer grauInstrucaoRais = this.currentBean.getGrauInstrucaoRais();

			if (grauInstrucaoRais != null) {
				this.subView.getTfGrauInstrucaoRais().setValue(
						grauInstrucaoRais.toString());
			}

			Integer grauInstrucaoSefip = this.currentBean
					.getGrauInstrucaoSefip();

			if (grauInstrucaoSefip != null) {
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