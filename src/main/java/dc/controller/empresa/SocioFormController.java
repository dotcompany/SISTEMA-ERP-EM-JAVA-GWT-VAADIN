package dc.controller.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.empresa.Dependente;
import dc.entidade.empresa.ParticipacaoSocietaria;
import dc.entidade.empresa.Socio;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.UF;
import dc.entidade.pessoal.TipoRelacionamento;
import dc.entidade.produto.Produto;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.empresa.SocioDAO;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.pessoal.TipoRelacionamentoDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.empresa.SocioFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SocioFormController extends CRUDFormController<Socio> {

	SocioFormView subView;

	@Autowired
	SocioDAO dao;

	Socio currentBean;

	@Autowired
	TipoRelacionamentoDAO tipoRelacionamentoDAO;

	@Autowired
	UFDAO ufDAO;

	@Override
	public String getViewIdentifier() {
		return "EstoqueForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Socio();
	}

	@Override
	protected void initSubView() {
		subView = new SocioFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
	}

	public Empresa empresaAtual(){
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {

		dao.saveOrUpdate(currentBean);

	}



	@Override
	protected void quandoNovo() {
		try{
			//subView.filEstoqueDetalhesSubForm(currentBean.getDetalhes());
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Sócio";
	}

	@Override
	protected void remover(List<Serializable> ids) {

		dao.deleteAllByIds(ids);

	}
	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");

	}

	@Override
	public boolean isFullSized(){
		return true;
	}

	public Dependente adicionarDependente(){
		Dependente dep = new Dependente();
		currentBean.adicionarDependente(dep);
		return dep;
	}

	public ParticipacaoSocietaria adicionarParticipacao(){
		ParticipacaoSocietaria p = new ParticipacaoSocietaria();
		currentBean.adicionarDependente(p);
		return p;
	}

	public List<TipoRelacionamento> carregarTipoRelacionamento(){
		List<TipoRelacionamento> lista = new ArrayList<TipoRelacionamento>();
		for(TipoRelacionamento tipo : tipoRelacionamentoDAO.listaTodos()){
			lista.add(tipo);
		}
		return lista;
	}

	public List<UF> listarUfs(){
		return ufDAO.listaTodos();
	}

	public BeanItemContainer<String> carregarUFs(){
		BeanItemContainer<String> container = new BeanItemContainer<>(String.class);
		List<UF> ufs = listarUfs();
		for (UF u : ufs) {
			container.addBean(u.getSigla());
		}	

		return container;
	}

}
