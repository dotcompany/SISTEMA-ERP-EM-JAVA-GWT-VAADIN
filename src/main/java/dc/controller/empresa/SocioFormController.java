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
import dc.entidade.empresa.QuadroSocietario;
import dc.entidade.empresa.Socio;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.Pessoa;
import dc.entidade.geral.UF;
import dc.entidade.pessoal.TipoRelacionamento;
import dc.servicos.dao.empresa.QuadroSocietarioDAO;
import dc.servicos.dao.empresa.SocioDAO;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.dao.pessoal.TipoRelacionamentoDAO;
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
	PessoaDAO pessoaDAO;

	@Autowired
	QuadroSocietarioDAO quadroSocietarioDAO;

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

		currentBean = dao.find((Integer) id);


		try{
			carregarCombos();

			if(Validator.validateObject(currentBean.getPessoa())){
				subView.getCmbSocio().setValue(currentBean.getPessoa());
			}

			if(Validator.validateObject(currentBean.getQuadroSocietario())){
				subView.getCmbQuadroSocietario().setValue(currentBean.getQuadroSocietario());
			}

			subView.getTxtLogradouro().setValue(currentBean.getLogradouro());
			subView.getTxtNumero().setValue(currentBean.getNumero().toString());
			subView.getTxtComplemento().setValue(currentBean.getComplemento());

			if(Validator.validateObject(currentBean.getBairro())){
				subView.getTxtBairro().setValue(currentBean.getBairro());	
			}

			if(Validator.validateObject(currentBean.getUf())){
				subView.getCmbUF().setValue(currentBean.getUf());	
			}


			if(Validator.validateObject(currentBean.getMunicipio())){
				subView.getTxtMunicipio().setValue(currentBean.getMunicipio());	
			}


			if(Validator.validateObject(currentBean.getCep())){
				subView.getTxtCep().setValue(currentBean.getCep());
			}

			if(Validator.validateObject(currentBean.getFone())){
				subView.getTxtFone().setValue(currentBean.getFone());
			}

			if(Validator.validateObject(currentBean.getCelular())){
				subView.getTxtCelular().setValue(currentBean.getCelular());
			}

			if(Validator.validateObject(currentBean.getEmail())){
				subView.getTxtEmail().setValue(currentBean.getEmail());
			}

			if(Validator.validateObject(currentBean.getParticipacao())){
				subView.getTxtParticipacao().setValue(currentBean.getParticipacao().toString());
			}

			if(Validator.validateObject(currentBean.getQuotas())){
				subView.getTxtQuotas().setValue(currentBean.getQuotas().toString());
			}
			
			if(Validator.validateObject(currentBean.getIntegralizar())){
				subView.getTxtIntegralizar().setValue(currentBean.getIntegralizar().toString());
			}
			
			if(Validator.validateObject(currentBean.getDataIngresso())){
				subView.getDataIngresso().setValue(currentBean.getDataIngresso());
			}
			
			if(Validator.validateObject(currentBean.getDataSaida())){
				subView.getDataSaida().setValue(currentBean.getDataSaida());
			}

		}catch(Exception e){
			e.printStackTrace();
		}



	}

	void carregarCombos(){
		carregarPessoas();
		carregarUFs();
	}

	public Empresa empresaAtual(){
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {


		try{
			Pessoa socio = (Pessoa)subView.getCmbSocio().getValue();
			QuadroSocietario quadro = (QuadroSocietario)subView.getCmbQuadroSocietario().getValue(); 


			String logradouro = subView.getTxtLogradouro().getValue();
			String numero = subView.getTxtNumero().getValue();
			String complemento = subView.getTxtComplemento().getValue();

			String bairro = subView.getTxtBairro().getValue();
			String municipio = subView.getTxtMunicipio().getValue();
			String uf = (String)subView.getCmbUF().getValue();
			String cep = subView.getTxtCep().getValue();

			String fone = subView.getTxtFone().getValue();
			String celular = subView.getTxtCelular().getValue();
			String email = subView.getTxtEmail().getValue();


			String participacao = subView.getTxtParticipacao().getValue();
			String quotas = subView.getTxtQuotas().getValue();
			String integralizar = subView.getTxtIntegralizar().getValue();
			Date dataIngresso = subView.getDataIngresso().getValue();
			Date dataSaida = subView.getDataSaida().getValue();

			if(Validator.validateObject(socio)){
				currentBean.setPessoa(socio);
			}

			if(Validator.validateObject(quadro)){
				currentBean.setQuadroSocietario(quadro);
			}

			if(Validator.validateObject(logradouro)){
				currentBean.setLogradouro(logradouro);
			}

			if(Validator.validateObject(numero)){
				currentBean.setNumero(new Integer(numero));
			}

			if(Validator.validateObject(complemento)){
				currentBean.setComplemento(complemento);
			}

			if(Validator.validateObject(bairro)){
				currentBean.setBairro(bairro);
			}

			if(Validator.validateObject(municipio)){
				currentBean.setMunicipio(municipio);
			}

			if(Validator.validateObject(uf)){
				currentBean.setUf(uf);
			}

			if(Validator.validateObject(cep)){
				currentBean.setCep(cep);
			}

			if(Validator.validateObject(fone)){
				currentBean.setFone(fone);
			}

			if(Validator.validateObject(celular)){
				currentBean.setCelular(celular);			
			}

			if(Validator.validateObject(email)){
				currentBean.setEmail(email);
			}

			if(Validator.validateObject(participacao)){
				currentBean.setParticipacao(new BigDecimal(formataValor(participacao)));
			}

			if(Validator.validateObject(quotas)){
				currentBean.setQuotas(new Integer(quotas));
			}

			if(Validator.validateObject(integralizar)){
				currentBean.setIntegralizar(new BigDecimal(formataValor(integralizar)));
			}

			if(Validator.validateObject(dataIngresso)){
				currentBean.setDataIngresso(dataIngresso);
			}

			if(Validator.validateObject(dataSaida)){
				currentBean.setDataSaida(dataSaida);
			}


			dao.saveOrUpdate(currentBean);
		}catch(Exception e){
			e.printStackTrace();
		}



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

	public List<Pessoa>listarPessoas(){
		return pessoaDAO.listaTodos();
	}

	public List<QuadroSocietario>listarQuadros(){
		return quadroSocietarioDAO.listaTodos();
	}

	public BeanItemContainer<Pessoa> carregarPessoas(){
		BeanItemContainer<Pessoa> container=  new BeanItemContainer<>(Pessoa.class);
		for(Pessoa p : listarPessoas()){
			container.addItem(p);
		}
		return container;
	}

	public BeanItemContainer<QuadroSocietario> carregarQuadros(){
		BeanItemContainer<QuadroSocietario> container=  new BeanItemContainer<>(QuadroSocietario.class);
		for(QuadroSocietario p : listarQuadros()){
			container.addItem(p);
		}
		return container;


	}
	
	public String formataValor(String valor){
		String format = "";
		format = valor.replace("R$","").
				substring(0,valor.indexOf(",")).

				replaceAll( ",","" ).trim();
		return format;
	}

}
