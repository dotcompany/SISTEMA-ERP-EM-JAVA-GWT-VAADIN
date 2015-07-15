package dc.controller.tributario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.enums.OrigemMercadoriaEn;
import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.tabela.CfopEntity;
import dc.entidade.geral.tabela.CsosnbEntity;
import dc.entidade.geral.tabela.CstIcmsbEntity;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.entidade.tributario.IcmsCustomizadoDetalheEntity;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.dao.geral.tabela.CfopDAO;
import dc.servicos.dao.geral.tabela.CsosnbDAO;
import dc.servicos.dao.geral.tabela.CstIcmsbDAO;
import dc.servicos.dao.tributario.IcmsCustomizadoDAO;
import dc.servicos.dao.tributario.IcmsCustomizadoDetalheDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tributario.ICMSCustomizadoFormView;

@Controller
@Scope("prototype")
public class IcmsCustomizadoFormController extends CRUDFormController<IcmsCustomizadoCabecalhoEntity> {
	
private static final long serialVersionUID = 1L;

ICMSCustomizadoFormView subView;

@Autowired
private IcmsCustomizadoDAO dao;

@Autowired
private UfDAO ufDAO;

@Autowired
private IcmsCustomizadoDetalheDAO detalheDAO;

private IcmsCustomizadoCabecalhoEntity currentBean;

@Autowired
private CfopDAO cfopDAO;

@Autowired
private CsosnbDAO csosnbDAO;

@Autowired
private CstIcmsbDAO cstbDAO;

@Override
public String getViewIdentifier() {
	return ClassUtils.getUrl(this);
}

@Override
protected boolean validaSalvar() {
	boolean valido = true;

	return valido;
}

@Override
protected void criarNovoBean() {
	currentBean = new IcmsCustomizadoCabecalhoEntity();
}

@Override
protected void initSubView() {
	subView = new ICMSCustomizadoFormView(this);
	
	this.subView.InitCbs(getOrigemMercadoria());
}
	
/** COMBO */
public List<String> getOrigemMercadoria() {
	try {
		List<String> siLista = new ArrayList<String>();
		for (OrigemMercadoriaEn en : OrigemMercadoriaEn.values()) {
			siLista.add(en.ordinal(), en.toString());
		}
			
		return siLista;
			
	} catch (Exception e) {
		e.printStackTrace();

		return null;
	}
}

@Override
protected void carregar(Serializable id) {
		
	try {
		currentBean = this.dao.find((Integer) id);
		currentBean = dao.find((Integer) id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getOrigemMercadoria().setValue(currentBean.getOrigemMercadoria());
		subView.getDetalheSubForm().fillWith(currentBean.getDetalhes());
			
		List<IcmsCustomizadoDetalheEntity> detalhes = currentBean.getDetalhes();

		for (IcmsCustomizadoDetalheEntity d : detalhes) {
			Integer idCsosn = new Integer(d.getCsosnB().trim());
			d.setCsosn(csosnbDAO.find(idCsosn));

			Integer idCst = new Integer(d.getCstB().trim());
			d.setCst(cstbDAO.find(idCst));

		}

		subView.preencheSubForm(detalhes);
			
	} catch (Exception e) {
		e.printStackTrace();
	}
		
}

	@Override
	protected void actionSalvar() {
		try {
			List<IcmsCustomizadoDetalheEntity> detalhes = subView
					.getDetalheSubForm().getDados();

			String descricao = subView.getTxtDescricao().getValue();
			
			OrigemMercadoriaEn ORIGEM = (OrigemMercadoriaEn) (this.subView.getOrigemMercadoria().getValue());
			this.currentBean.setOrigemMercadoria(ORIGEM);


			if (!(Validator.validateString(descricao)))
				throw new ErroValidacaoException("Informe o Campo Descrição");

			if (!(Validator.validateObject(subView.getOrigemMercadoria().getValue()))) {
				throw new ErroValidacaoException(
						"Informe o Campo Origem da Mercadoria");
			} else {
				ORIGEM = (OrigemMercadoriaEn) subView.getOrigemMercadoria().getValue();
			}

			currentBean.setDescricao(subView.getTxtDescricao().getValue());
			currentBean.setOrigemMercadoria(ORIGEM);
			currentBean.setEmpresa(empresaAtual());

			dao.saveOrUpdate(currentBean);

			for (IcmsCustomizadoDetalheEntity d : detalhes) {
				d.setIcmsCustomizado(currentBean);
				d.setCsosnB(d.getCsosn().getId().toString());
				d.setCstB(d.getCst().getId().toString());
				detalheDAO.saveOrUpdate(d);
			}
			notifiyFrameworkSaveOK(currentBean);
			
		} catch (ErroValidacaoException e) {
			mensagemErro(e.montaMensagemErro());
		}

	}

	@Override
	protected void quandoNovo() {
		try {
			// subView.filContagemEstoqueDetalhesSubForm(currentBean.getContagemDetalhes());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "ICMS Customizado";
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

@Override
protected void remover(List<Serializable> ids) {

	try {
		for (Serializable id : ids) {
			IcmsCustomizadoCabecalhoEntity icms = dao.find(id);
			List<IcmsCustomizadoDetalheEntity> detalhes = detalheDAO
					.findByIcms(icms);
			for (IcmsCustomizadoDetalheEntity detalhe : detalhes) {
				detalheDAO.delete(detalhe);
			}
		}
		dao.deleteAllByIds(ids);
	} catch (Exception e) {
		e.printStackTrace();
	}

}

public IcmsCustomizadoDetalheEntity novoDetalhe() {
	IcmsCustomizadoDetalheEntity detalhe = new IcmsCustomizadoDetalheEntity();
	currentBean.adicionarDetalhe(detalhe);
	return detalhe;
}

public List<UfEntity> listarUfs() {
	return ufDAO.listaTodos();
}

public BeanItemContainer<CfopEntity> carregarCfop() {
	BeanItemContainer<CfopEntity> container = new BeanItemContainer<>(
			CfopEntity.class);
	for (CfopEntity obj : cfopDAO.listaTodos()) {
		container.addBean(obj);
	}
	return container;
}

public BeanItemContainer<CsosnbEntity> carregarCsosnb() {
	BeanItemContainer<CsosnbEntity> container = new BeanItemContainer<>(
			CsosnbEntity.class);
	for (CsosnbEntity obj : csosnbDAO.listaTodos()) {
		container.addBean(obj);
	}
	return container;
}

public BeanItemContainer<CstIcmsbEntity> carregarCstb() {
	BeanItemContainer<CstIcmsbEntity> container = new BeanItemContainer<>(
			CstIcmsbEntity.class);
	for (CstIcmsbEntity obj : cstbDAO.listaTodos()) {
		container.addBean(obj);
	}
	return container;
}
	
public void removerDetalhe(List<IcmsCustomizadoDetalheEntity> values) {
	for (IcmsCustomizadoDetalheEntity value : values) {
		currentBean.removeDetalhe(value);
	}
}

@Override
public IcmsCustomizadoCabecalhoEntity getModelBean() {
	return currentBean;
}

}