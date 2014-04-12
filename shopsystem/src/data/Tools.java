package data;

import java.util.Stack;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class Tools {

	public static UIComponent dfsComponent(String id){
		Stack<UIComponent> stack = new Stack<>();
		stack.push(FacesContext.getCurrentInstance().getViewRoot());
		UIComponent current = null;
		boolean found = false;
		
		while(!found){
			current = stack.pop();
			
			if(current.getId().equals(id))
				found = true;
			else
				for(UIComponent child : current.getChildren())
					stack.push(child);
		}
		
		return current;
	}
}
