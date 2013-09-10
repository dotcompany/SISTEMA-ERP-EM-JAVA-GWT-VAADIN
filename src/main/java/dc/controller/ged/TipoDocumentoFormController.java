package dc.controller.ged;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.Empresa;
import dc.entidade.ged.TipoDocumento;
import dc.entidade.geral.Usuario;
import dc.servicos.dao.ged.TipoDocumentoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ged.TipoDocumentoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class TipoDocumentoFormController extends CRUDFormController<TipoDocumento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TipoDocumentoFormView subView;

	@Autowired
	TipoDocumentoDAO tipoDocumentoDAO;

	private TipoDocumento currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxtTamanho().getValue())) {
			adicionarErroDeValidacao(subView.getTxtTamanho(), "Número inválido");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new TipoDocumento();
		Usuario usuario = SecuritySessionProvider.getUsuario();
		Empresa empresa = usuario.getConta().getEmpresa();
		currentBean.setEmpresa(empresa);
	}

	@Override
	protected void initSubView() {
		subView = new TipoDocumentoFormView();

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoDocumentoDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtTamanho().setValue(currentBean.getTamanhoMaximo().toString());

	}

	@Override
	protected void actionSalvar() {
		String nome = subView.getTxtNome().getValue();
		String tamanhoMaximo = subView.getTxtTamanho().getValue();
		currentBean.setNome(nome);
		currentBean.setTamanhoMaximo(new BigDecimal(tamanhoMaximo));
		try {
			tipoDocumentoDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void quandoNovo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Component getSubView() {
		// TODO Auto-generated method stub
		return subView;
	}

	@Override
	protected String getNome() {
		// TODO Auto-generated method stub
		return "Tipo Documento";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoDocumentoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "tipoDocumentoForm";
	}

}
