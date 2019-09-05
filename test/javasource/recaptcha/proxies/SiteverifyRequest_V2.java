// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package recaptcha.proxies;

public class SiteverifyRequest_V2
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject siteverifyRequest_V2MendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "reCAPTCHA.SiteverifyRequest_V2";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		secret("secret"),
		response("response");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@java.lang.Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public SiteverifyRequest_V2(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "reCAPTCHA.SiteverifyRequest_V2"));
	}

	protected SiteverifyRequest_V2(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject siteverifyRequest_V2MendixObject)
	{
		if (siteverifyRequest_V2MendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("reCAPTCHA.SiteverifyRequest_V2", siteverifyRequest_V2MendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a reCAPTCHA.SiteverifyRequest_V2");

		this.siteverifyRequest_V2MendixObject = siteverifyRequest_V2MendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'SiteverifyRequest_V2.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static recaptcha.proxies.SiteverifyRequest_V2 initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return recaptcha.proxies.SiteverifyRequest_V2.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static recaptcha.proxies.SiteverifyRequest_V2 initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new recaptcha.proxies.SiteverifyRequest_V2(context, mendixObject);
	}

	public static recaptcha.proxies.SiteverifyRequest_V2 load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return recaptcha.proxies.SiteverifyRequest_V2.initialize(context, mendixObject);
	}

	/**
	 * Commit the changes made on this proxy object.
	 */
	public final void commit() throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 */
	public final void commit(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(com.mendix.systemwideinterfaces.core.IContext context)
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of secret
	 */
	public final java.lang.String getsecret()
	{
		return getsecret(getContext());
	}

	/**
	 * @param context
	 * @return value of secret
	 */
	public final java.lang.String getsecret(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.secret.toString());
	}

	/**
	 * Set value of secret
	 * @param secret
	 */
	public final void setsecret(java.lang.String secret)
	{
		setsecret(getContext(), secret);
	}

	/**
	 * Set value of secret
	 * @param context
	 * @param secret
	 */
	public final void setsecret(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String secret)
	{
		getMendixObject().setValue(context, MemberNames.secret.toString(), secret);
	}

	/**
	 * @return value of response
	 */
	public final java.lang.String getresponse()
	{
		return getresponse(getContext());
	}

	/**
	 * @param context
	 * @return value of response
	 */
	public final java.lang.String getresponse(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.response.toString());
	}

	/**
	 * Set value of response
	 * @param response
	 */
	public final void setresponse(java.lang.String response)
	{
		setresponse(getContext(), response);
	}

	/**
	 * Set value of response
	 * @param context
	 * @param response
	 */
	public final void setresponse(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String response)
	{
		getMendixObject().setValue(context, MemberNames.response.toString(), response);
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return siteverifyRequest_V2MendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@java.lang.Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final recaptcha.proxies.SiteverifyRequest_V2 that = (recaptcha.proxies.SiteverifyRequest_V2) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@java.lang.Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "reCAPTCHA.SiteverifyRequest_V2";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@java.lang.Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
