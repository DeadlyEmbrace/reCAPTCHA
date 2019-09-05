// This file was generated by Mendix Business Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package recaptcha.proxies;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixIdentifier;
import com.mendix.systemwideinterfaces.core.IMendixObject;

/**
 * 
 */
public class Dummy
{
	private final IMendixObject dummyMendixObject;

	private final IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final String entityName = "reCAPTCHA.Dummy";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		Challenge("Challenge"),
		Response("Response");

		private String metaName;

		MemberNames(String s)
		{
			metaName = s;
		}

		@Override
		public String toString()
		{
			return metaName;
		}
	}

	public Dummy(IContext context)
	{
		this(context, Core.instantiate(context, "reCAPTCHA.Dummy"));
	}

	protected Dummy(IContext context, IMendixObject dummyMendixObject)
	{
		if (dummyMendixObject == null)
			throw new IllegalArgumentException("The given object cannot be null.");
		if (!Core.isSubClassOf("reCAPTCHA.Dummy", dummyMendixObject.getType()))
			throw new IllegalArgumentException("The given object is not a reCAPTCHA.Dummy");

		this.dummyMendixObject = dummyMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'Dummy.load(IContext, IMendixIdentifier)' instead.
	 */
	@Deprecated
	public static recaptcha.proxies.Dummy initialize(IContext context, IMendixIdentifier mendixIdentifier) throws CoreException
	{
		return recaptcha.proxies.Dummy.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.getSudoContext() can be used to obtain sudo access).
	 */
	public static recaptcha.proxies.Dummy initialize(IContext context, IMendixObject mendixObject)
	{
		return new recaptcha.proxies.Dummy(context, mendixObject);
	}

	public static recaptcha.proxies.Dummy load(IContext context, IMendixIdentifier mendixIdentifier) throws CoreException
	{
		IMendixObject mendixObject = Core.retrieveId(context, mendixIdentifier);
		return recaptcha.proxies.Dummy.initialize(context, mendixObject);
	}

	/**
	 * Commit the changes made on this proxy object.
	 */
	public final void commit() throws CoreException
	{
		Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 */
	public final void commit(IContext context) throws CoreException
	{
		Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(IContext context)
	{
		Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of Challenge
	 */
	public final String getChallenge()
	{
		return getChallenge(getContext());
	}

	/**
	 * @param context
	 * @return value of Challenge
	 */
	public final String getChallenge(IContext context)
	{
		return (String) getMendixObject().getValue(context, MemberNames.Challenge.toString());
	}

	/**
	 * Set value of Challenge
	 * @param challenge
	 */
	public final void setChallenge(String challenge)
	{
		setChallenge(getContext(), challenge);
	}

	/**
	 * Set value of Challenge
	 * @param context
	 * @param challenge
	 */
	public final void setChallenge(IContext context, String challenge)
	{
		getMendixObject().setValue(context, MemberNames.Challenge.toString(), challenge);
	}

	/**
	 * @return value of Response
	 */
	public final String getResponse()
	{
		return getResponse(getContext());
	}

	/**
	 * @param context
	 * @return value of Response
	 */
	public final String getResponse(IContext context)
	{
		return (String) getMendixObject().getValue(context, MemberNames.Response.toString());
	}

	/**
	 * Set value of Response
	 * @param response
	 */
	public final void setResponse(String response)
	{
		setResponse(getContext(), response);
	}

	/**
	 * Set value of Response
	 * @param context
	 * @param response
	 */
	public final void setResponse(IContext context, String response)
	{
		getMendixObject().setValue(context, MemberNames.Response.toString(), response);
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final IMendixObject getMendixObject()
	{
		return dummyMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final IContext getContext()
	{
		return context;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final recaptcha.proxies.Dummy that = (recaptcha.proxies.Dummy) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static String getType()
	{
		return "reCAPTCHA.Dummy";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@Deprecated
	public String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
