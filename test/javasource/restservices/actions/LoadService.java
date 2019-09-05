// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package restservices.actions;

import restservices.publish.RestServiceHandler;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;

public class LoadService extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __def;
	private restservices.proxies.DataServiceDefinition def;

	public LoadService(IContext context, IMendixObject def)
	{
		super(context);
		this.__def = def;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.def = __def == null ? null : restservices.proxies.DataServiceDefinition.initialize(getContext(), __def);

		// BEGIN USER CODE
		RestServiceHandler.loadConfig(def, true, getContext());
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "LoadService";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
