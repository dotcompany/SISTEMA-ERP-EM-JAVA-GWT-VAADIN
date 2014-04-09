package dc.framework;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import dc.framework.client.SerchTextFieldServerRPC;
import dc.visao.framework.geral.CRUDListController;

public class SearchTextFieldExtension extends AbstractExtension {

	private CRUDListController controller;

	public SearchTextFieldExtension(CRUDListController controller) {
		this.controller = controller;

	}

	public void extend(TextField field) {
		System.out.println("extend..being called");
		field.setTextChangeTimeout(500);
		super.extend(field);
		SerchTextFieldServerRPC rpc = new SerchTextFieldServerRPC() {

			private static final long serialVersionUID = 2695079502474207881L;
			private int clickCount = 0;

			public void clicked(String buttonName) {
				Notification.show("Clicou" + buttonName);
			}

			public void search(String value) {
				String txt = value;
				if (value == null || value.trim().isEmpty()) {
					txt = " todos os registros";
				}
				controller.doSearch(value);
			}

			@Override
			public void schedule() {

			}
		};

		registerRpc(rpc);
	}

}
