// This file was generated by Mendix Modeler 7.23.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package restservices.proxies.microflows;

import java.util.HashMap;
import java.util.Map;
import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.MendixRuntimeException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class Microflows
{
	// These are the microflows for the RestServices module
	public static void aCo_ServiceDefinition(IContext context, restservices.proxies.DataServiceDefinition _serviceDefinition)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("ServiceDefinition", _serviceDefinition == null ? null : _serviceDefinition.getMendixObject());
			Core.execute(context, "RestServices.ACo_ServiceDefinition", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static boolean bDe_ServiceDefinition(IContext context, restservices.proxies.DataServiceDefinition _dataServiceDefinition)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("DataServiceDefinition", _dataServiceDefinition == null ? null : _dataServiceDefinition.getMendixObject());
			return (java.lang.Boolean)Core.execute(context, "RestServices.BDe_ServiceDefinition", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static void createMicroflowService(IContext context, java.lang.String _microflowName, java.lang.String _securityRole, java.lang.String _description, restservices.proxies.HttpMethod _httpMethod)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("MicroflowName", _microflowName);
			params.put("SecurityRole", _securityRole);
			params.put("Description", _description);
			params.put("HttpMethod", _httpMethod == null ? null : _httpMethod.name());
			Core.execute(context, "RestServices.CreateMicroflowService", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static void createMicroflowServiceWithPath(IContext context, java.lang.String _microflowName, java.lang.String _securityRole, java.lang.String _description, java.lang.String _pathTemplate, restservices.proxies.HttpMethod _httpMethod)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("MicroflowName", _microflowName);
			params.put("SecurityRole", _securityRole);
			params.put("Description", _description);
			params.put("PathTemplate", _pathTemplate);
			params.put("HttpMethod", _httpMethod == null ? null : _httpMethod.name());
			Core.execute(context, "RestServices.CreateMicroflowServiceWithPath", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static restservices.proxies.DataServiceDefinition getOrCreateDataService(IContext context, java.lang.String _serviceName)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("ServiceName", _serviceName);
			IMendixObject result = (IMendixObject)Core.execute(context, "RestServices.GetOrCreateDataService", params);
			return result == null ? null : restservices.proxies.DataServiceDefinition.initialize(context, result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static void iVK_DeleteFeed(IContext context, restservices.proxies.DataSyncState _followChangesState)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("FollowChangesState", _followChangesState == null ? null : _followChangesState.getMendixObject());
			Core.execute(context, "RestServices.IVK_DeleteFeed", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static void iVK_OpenServiceOverview(IContext context)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			Core.execute(context, "RestServices.IVK_OpenServiceOverview", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static void iVK_RebuildIndex(IContext context, restservices.proxies.ChangeLog _index)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("Index", _index == null ? null : _index.getMendixObject());
			Core.execute(context, "RestServices.IVK_RebuildIndex", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static void iVK_SaveServiceDefinition(IContext context, restservices.proxies.DataServiceDefinition _serviceDefinition)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("ServiceDefinition", _serviceDefinition == null ? null : _serviceDefinition.getMendixObject());
			Core.execute(context, "RestServices.IVK_SaveServiceDefinition", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static boolean startPublishServices(IContext context)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			return (java.lang.Boolean)Core.execute(context, "RestServices.StartPublishServices", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static restservices.proxies.TrackingState vA_GetTrackingState(IContext context, restservices.proxies.DataSyncState _followChangesState)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("FollowChangesState", _followChangesState == null ? null : _followChangesState.getMendixObject());
			java.lang.String result = (java.lang.String)Core.execute(context, "RestServices.VA_GetTrackingState", params);
			if (result == null)
				return null;
			return restservices.proxies.TrackingState.valueOf(result);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
	public static java.lang.Long vA_ObjectIndexNrOfConnections(IContext context, restservices.proxies.ChangeLog _serviceObjectIndex)
	{
		try
		{
			Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
			params.put("ServiceObjectIndex", _serviceObjectIndex == null ? null : _serviceObjectIndex.getMendixObject());
			return (java.lang.Long)Core.execute(context, "RestServices.VA_ObjectIndexNrOfConnections", params);
		}
		catch (CoreException e)
		{
			throw new MendixRuntimeException(e);
		}
	}
}