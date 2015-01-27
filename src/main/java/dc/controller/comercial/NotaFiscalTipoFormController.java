package dc.controller.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.comercial.NotaFiscalTipo;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.NotaFiscalTipoDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.NotaFiscalTipoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class NotaFiscalTipoFormController extends CRUDFormController<NotaFiscalTipo> {

	NotaFiscalTipo currentBean;

	NotaFiscalTipoFormView subView;

	@Autowired
	NotaFiscalTipoDAO dao;

	@Override
	public String getViewIdentifier() {
		return "notaFiscalTipoForm";
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new NotaFiscalTipo();

	}

	@Override
	protected void initSubView() {
		subView = new NotaFiscalTipoFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getTxtSerie().setValue(currentBean.getSerie());
		subView.getTxtUltimoNumero().setValue(currentBean.getUltimoNumero().toString());
	}

	@Override
	protected void actionSalvar() {

		try {

			String nome = subView.getTxtNome().getValue();
			String descricao = subView.getTxtDescricao().getValue();
			
			String serie = subView.getTxtSerie().getValue();
			
			String ultimoNumero = subView.getTxtUltimoNumero().getValue();

			if (!Validator.validateString(nome)) {
				throw new ErroValidacaoException("Informe o Nome!");
			}

			currentBean.setNome(nome);
			currentBean.setDescricao(descricao);
			
			currentBean.setSerie(serie);
			

			
			if (Validator.validateString(ultimoNumero))
				currentBean.setUltimoNumero(new Integer(ultimoNumero));

			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);

		} catch (ErroValidacaoException e) {
			mensagemErro(e.montaMensagemErro());
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
		return subView;
	}

	@Override
	protected String getNome() {
		return "Nota Fiscal Tipo";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		dao.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	@Override
	public NotaFiscalTipo getModelBean() {
		return currentBean;
	}

}
