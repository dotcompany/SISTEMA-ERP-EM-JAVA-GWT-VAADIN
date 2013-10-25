package dc.controller.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.suprimentos.ReajusteEstoque;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.TipoNotaFiscalDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.TipoNotaFiscalFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class TipoNotaFiscalFormController extends CRUDFormController<TipoNotaFiscal> {

	TipoNotaFiscal currentBean;

	TipoNotaFiscalFormView subView;

	@Autowired
	TipoNotaFiscalDAO dao;

	@Override
	public String getViewIdentifier() {
		return "tipoNotaForm";
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new TipoNotaFiscal();

	}

	@Override
	protected void initSubView() {
		subView = new TipoNotaFiscalFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void actionSalvar() {

		try{

			String nome = subView.getTxtNome().getValue();
			String descricao = subView.getTxtDescricao().getValue();
			String modelo = subView.getTxtModelo().getValue();
			String serie = subView.getTxtSerie().getValue();
			String template = subView.getTxtTemplate().getValue();
			String numeroItens = subView.getTxtNumeroItens().getValue();
			String ultimoImpresso = subView.getTxtUltimoImpresso().getValue();

			if(!Validator.validateString(nome)){
				throw new ErroValidacaoException("Informe o Nome!");
			}

			currentBean.setNome(nome);
			currentBean.setDescricao(descricao);
			currentBean.setModelo(modelo);
			currentBean.setSerie(serie);
			currentBean.setTemplate(template);

			if(Validator.validateString(numeroItens))currentBean.setNumeroItens(new Integer(numeroItens));
			if(Validator.validateString(ultimoImpresso))currentBean.setUltimoImpresso(new Integer(ultimoImpresso));

			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);

		}  catch(ErroValidacaoException e){
			 mensagemErro(e.montaMensagemErro());
		}catch(Exception e){
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
		return "Tipo de Nota Fiscal";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}



}
