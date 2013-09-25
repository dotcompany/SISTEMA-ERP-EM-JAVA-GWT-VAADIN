package dc.visao.framework.component;

import dc.org.vaadin.hhe.nanoscrollpanel.NanoScrollPanel;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ScrollBarLayout extends AbstractOrderedLayout{
	
	

   public ScrollBarLayout (){
	   
	   VerticalLayout v = new VerticalLayout();
	   NanoScrollPanel nPanel = new NanoScrollPanel();
	   nPanel.setWidth("50px");
	   nPanel.setHeight("100px");
	   nPanel.setSizeFull();
	   nPanel.flashScrollbar();
	   nPanel.setPreventPageScrolling(true); 
	   nPanel.setContent(v);
	
   }

}
