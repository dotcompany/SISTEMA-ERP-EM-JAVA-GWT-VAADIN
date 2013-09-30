package dc.visao.framework.component;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VOverlay;
import com.vaadin.shared.ui.Connect;

@Connect(SearchTextFieldExtension.class)
public class SearchTextFieldConnector extends AbstractExtensionConnector {
	
	
	
    @Override
    protected void extend(ServerConnector target) {
        final Widget txtFieldWidget = ((ComponentConnector) target).getWidget();

        final VOverlay warning = new VOverlay();
        warning.setOwner(txtFieldWidget(txtFieldWidget));
        warning.add(new HTML("Caps Lock is enabled!"));

        txtFieldWidget(txtFieldWidget).addDomHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
            	System.out.println("key press on searchtext");
                if (isEnabled() && isCapsLockOn(event)) {
                    warning.showRelativeTo(txtFieldWidget(txtFieldWidget));
                } else {
                    warning.hide();
                }
            }
        }, KeyPressEvent.getType());
    }

	private Widget txtFieldWidget(final Widget passwordWidget) {
		return passwordWidget;
	}

    private boolean isCapsLockOn(KeyPressEvent e) {
        return e.isShiftKeyDown() ^ Character.isUpperCase(e.getCharCode());
    }
}
