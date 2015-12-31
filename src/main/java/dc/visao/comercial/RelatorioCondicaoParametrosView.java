package dc.visao.comercial;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

import dc.entidade.relatorio.RelatorioParameterView;
import dc.servicos.dao.comercial.ICondicaoPagamentoDAO;
import dc.visao.framework.util.ComponentUtil;

@Controller
@Scope("prototype")
public class RelatorioCondicaoParametrosView extends CustomComponent implements
		RelatorioParameterView<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout mainLayout;
	private Map<String, Object> params;

	@Autowired(required = true)
	private ICondicaoPagamentoDAO dao;

	private TextField txNome;

	public RelatorioCondicaoParametrosView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		params = new HashMap<String, Object>();
	}

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setRows(6);
		mainLayout.setColumns(1);

		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");

		txNome = ComponentUtil.buildTextField("Nome");
		mainLayout.addComponent(txNome, 0, 0);

		return mainLayout;
	}

	@Override
	public Map<String, Object> getParametersMap() {
		params.put("nome", txNome.getValue());

		return params;
	}

	@Override
	public JRDataSource getJRDataSource() {
		System.out.println(dao);
		return null;
	}

	public ICondicaoPagamentoDAO getDao() {
		return dao;
	}

	public void setDao(ICondicaoPagamentoDAO dao) {
		this.dao = dao;
	}

}