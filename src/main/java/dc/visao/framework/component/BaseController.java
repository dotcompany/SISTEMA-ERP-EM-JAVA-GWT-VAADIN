package dc.visao.framework.component;

import org.springframework.beans.factory.annotation.Autowired;

import dc.visao.framework.geral.Controller;
import dc.visao.framework.geral.ControllerTask;
import dc.visao.framework.geral.MainController;

public abstract class BaseController<V extends BaseView<?>> extends ControllerTask implements Controller {

	@Autowired
	protected MainController mainController;
	
	protected V view;
	
	protected abstract void initReports();
	
	protected void addReport(ReportController report) {
		report.setModuleId(getModuleId());
		view.addReport(report);
	}

	public void showReport(ReportController reportController) {
		mainController.showTaskableContent(reportController);
	}
	
}
