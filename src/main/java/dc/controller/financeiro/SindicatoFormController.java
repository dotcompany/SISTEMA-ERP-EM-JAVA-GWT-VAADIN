package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.TipoSindicatoEn;
import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.StringUtils;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.geral.diverso.UfListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.financeiro.SindicatoEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.SindicatoDAO;
import dc.servicos.dao.geral.UfDAO;
import dc.visao.financeiro.SindicatoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class SindicatoFormController extends
		CRUDFormController<SindicatoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SindicatoFormView subView;

	private SindicatoEntity currentBean;

	@Autowired
	private SindicatoDAO sindicatoDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	@Autowired
	private UfDAO ufDAO;

	@Override
	protected String getNome() {
		return "Sindicato";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	protected boolean validaSalvar() {
		boolean valido = true;

		String nome = this.subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			adicionarErroDeValidacao(this.subView.getTfNome(),
					"Não pode ficar em branco!");

			valido = false;
		}

		String logradouro = this.subView.getTfLogradouro().getValue();

		if (StringUtils.isBlank(logradouro)) {
			adicionarErroDeValidacao(this.subView.getTfLogradouro(),
					"Não pode ficar em branco!");

			valido = false;
		}

		return valido;
	}

	@Override
	protected void actionSalvar() {
		try {
			ContabilContaEntity contabilConta = this.subView
					.getMocContabilConta().getValue();

			if (contabilConta != null) {
				this.currentBean.setContabilConta(contabilConta);
			}

			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setLogradouro(this.subView.getTfLogradouro()
					.getValue());

			this.currentBean.setEmail(this.subView.getTfEmail().getValue());
			this.currentBean.setDataBase(this.subView.getPdfDataBase()
					.getValue());
			this.currentBean.setBairro(this.subView.getTfBairro().getValue());
			this.currentBean.setCnpj(this.subView.getTfCnpj().getValue());

			boolean bCodigoAgencia = NumberUtils.isNumber(this.subView
					.getTfCodigoAgencia().getValue());

			if (bCodigoAgencia) {
				this.currentBean.setCodigoAgencia(NumberUtils
						.toInt(this.subView.getTfCodigoAgencia().getValue()));
			}

			boolean bCodigoBanco = NumberUtils.isNumber(this.subView
					.getTfCodigoBanco().getValue());

			if (bCodigoBanco) {
				this.currentBean.setCodigoBanco(NumberUtils.toInt(this.subView
						.getTfCodigoBanco().getValue()));
			}

			this.currentBean.setCodigoCedente(this.subView.getTfCodigoCedente()
					.getValue());
			this.currentBean.setContaBanco(this.subView.getTfContaBanco()
					.getValue());

			this.currentBean.setFone1(this.subView.getTfTelefone1().getValue());
			this.currentBean.setFone2(this.subView.getTfTelefone2().getValue());
			this.currentBean.setNumero(this.subView.getTfNumero().getValue());

			boolean bPisoSalarial = NumberUtils.isNumber(this.subView
					.getTfPisoSalarial().getValue());

			if (bPisoSalarial) {
				this.currentBean.setPisoSalarial(NumberUtils
						.createBigDecimal(this.subView.getTfPisoSalarial()
								.getValue()));
			}

			TipoSindicatoEn tipoSindicatoEn = (TipoSindicatoEn) (this.subView
					.getCbTipo().getValue());

			this.currentBean.setTipoSindicato(tipoSindicatoEn);

			UfEntity uf = this.subView.getMocUf().getValue();

			if (uf != null && !uf.equals("")) {
				this.currentBean.setUf(uf.getSigla());
			}

			this.sindicatoDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.sindicatoDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfLogradouro().setValue(
					this.currentBean.getLogradouro());
			this.subView.getTfEmail().setValue(this.currentBean.getEmail());
			this.subView.getPdfDataBase().setValue(
					this.currentBean.getDataBase());
			this.subView.getTfBairro().setValue(this.currentBean.getBairro());
			this.subView.getTfCnpj().setValue(this.currentBean.getCnpj());

			ContabilContaEntity contabilConta = this.currentBean
					.getContabilConta();

			if (contabilConta != null) {
				this.subView.getMocContabilConta().setValue(contabilConta);
			}

			this.subView.getTfCodigoAgencia().setValue(
					this.currentBean.getCodigoAgencia().toString());
			this.subView.getTfCodigoBanco().setValue(
					this.currentBean.getCodigoBanco().toString());
			this.subView.getTfCodigoCedente().setValue(
					this.currentBean.getCodigoCedente());
			this.subView.getTfContaBanco().setValue(
					this.currentBean.getContaBanco());
			this.subView.getTfTelefone1().setValue(this.currentBean.getFone1());
			this.subView.getTfTelefone2().setValue(this.currentBean.getFone2());
			this.subView.getTfNumero().setValue(this.currentBean.getNumero());
			this.subView.getTfPisoSalarial().setValue(
					this.currentBean.getPisoSalarial().toString());

			TipoSindicatoEn tipoSindicatoEn = this.currentBean
					.getTipoSindicato();

			if (tipoSindicatoEn != null) {
				this.subView.getCbTipo().setValue(tipoSindicatoEn);
			}

			String sUf = this.currentBean.getUf();

			if (StringUtils.isNotBlank(sUf)) {
				UfEntity uf = this.ufDAO.find(sUf);

				this.subView.getMocUf().setValue(uf);
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
			this.subView = new SindicatoFormView(this);

			DefaultManyToOneComboModel<ContabilContaEntity> model1 = new DefaultManyToOneComboModel<ContabilContaEntity>(
					ContabilContaListController.class, this.contabilContaDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "descricao";
				}

			};

			this.subView.getMocContabilConta().setModel(model1);

			DefaultManyToOneComboModel<UfEntity> model2 = new DefaultManyToOneComboModel<UfEntity>(
					UfListController.class, this.ufDAO,
					super.getMainController());

			this.subView.getMocUf().setModel(model2);

			comboTipoSindicato();
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
		this.currentBean = new SindicatoEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.sindicatoDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public SindicatoEntity getModelBean() {
		return currentBean;
	}

	public void comboTipoSindicato() {
		for (TipoSindicatoEn en : TipoSindicatoEn.values()) {
			this.subView.getCbTipo().addItem(en);
		}
	}

}