package dc.controller.tributario;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.enums.OrigemMercadoriaEn;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.tabela.CfopEntity;
import dc.entidade.geral.tabela.CsosnbEntity;
import dc.entidade.geral.tabela.CstIcmsbEntity;
import dc.entidade.tributario.IcmsCustomizadoDetalheEntity;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.dao.geral.tabela.CfopDAO;
import dc.servicos.dao.geral.tabela.CsosnbDAO;
import dc.servicos.dao.geral.tabela.CstIcmsbDAO;
import dc.servicos.dao.tributario.IcmsCustomizadoDAO;
import dc.servicos.dao.tributario.IcmsCustomizadoDetalheDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.tributario.ICMSCustomizadoFormView;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class IcmsCustomizadoFormController extends
		CRUDFormController<IcmsCustomizadoCabecalhoEntity> {

	ICMSCustomizadoFormView subView;

	@Autowired
	IcmsCustomizadoDAO dao;

	@Autowired
	UfDAO ufDAO;

	@Autowired
	IcmsCustomizadoDetalheDAO detalheDAO;

	IcmsCustomizadoCabecalhoEntity currentBean;

	@Autowired
	CfopDAO cfopDAO;

	@Autowired
	CsosnbDAO csosnbDAO;

	@Autowired
	CstIcmsbDAO cstbDAO;

	@Override
	public String getViewIdentifier() {
		return "icmsCustomizadoForm";
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

	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.getTxtDescricao().setValue(currentBean.getNome());
		subView.carregarOrigemMercadoria();
		subView.getOrigemMercadoria().setValue(
				currentBean.getOrigemMercadoria());

		List<IcmsCustomizadoDetalheEntity> detalhes = currentBean.getDetalhes();

		for (IcmsCustomizadoDetalheEntity d : detalhes) {
			Integer idCsosn = new Integer(d.getCsosnB().trim());
			d.setCsosn(csosnbDAO.find(idCsosn));

			Integer idCst = new Integer(d.getCstB().trim());
			d.setCst(cstbDAO.find(idCst));

		}

		subView.preencheSubForm(detalhes);
	}

	public EmpresaEntity empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try {

			List<IcmsCustomizadoDetalheEntity> detalhes = subView
					.getDetalhesSubForm().getDados();

			String descricao = subView.getTxtDescricao().getValue();
			OrigemMercadoriaEn origem;

			if (!(Validator.validateString(descricao)))
				throw new ErroValidacaoException("Informe o Campo Descrição");

			if (!(Validator.validateObject(subView.getOrigemMercadoria()
					.getValue()))) {
				throw new ErroValidacaoException(
						"Informe o Campo Origem da Mercadoria");
			} else {
				origem = (OrigemMercadoriaEn) subView.getOrigemMercadoria()
						.getValue();
			}

			currentBean.setNome(subView.getTxtDescricao().getValue());
			currentBean.setOrigemMercadoria(origem);
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
		} catch (Exception e) {
			mensagemErro("Erro!!");
			e.printStackTrace();
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

		// TODO Auto-generated method stub

	}

	public IcmsCustomizadoDetalheEntity novoDetalhe() {
		IcmsCustomizadoDetalheEntity detalhe = new IcmsCustomizadoDetalheEntity();
		currentBean.adicionarDetalhe(detalhe);
		return detalhe;
	}

	public List<UfEntity> listarUfs() {
		return ufDAO.listaTodos();
	}

	public String formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return format;
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

	@Override
	public IcmsCustomizadoCabecalhoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}