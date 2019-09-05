// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package restservices.actions;

import restservices.consume.RestConsumer;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;

/**
 * Sets a header that will be used in the next request being made by the modele
 */
public class addHeaderToNextRequest extends CustomJavaAction<java.lang.Boolean>
{
	private java.lang.String header;
	private java.lang.String value;

	public addHeaderToNextRequest(IContext context, java.lang.String header, java.lang.String value)
	{
		super(context);
		this.header = header;
		this.value = value;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		RestConsumer.addHeaderToNextRequest(header, value);
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "addHeaderToNextRequest";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
