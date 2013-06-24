/*
 * COPYRIGHT (c) 1999 - 2009 by Percussion Software, Inc., Woburn, MA USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Percussion.
 *
 * com.percussion.pso.demandpreview.service LinkBuilderService.java
 *
 */
package com.percussion.pso.demandpreview.service;
import com.percussion.services.assembly.IPSAssemblyTemplate;
import com.percussion.services.sitemgr.IPSSite;
import com.percussion.utils.guid.IPSGuid;

/**
 * Service to build links to a content item.
 * 
 *
 * @author davidbenua
 *
 */
public interface LinkBuilderService
{
   /**
    * Service to build the URL of an item published on a site.
    * The URL will contain the site root path and the location 
    * as computed by the location scheme in effect for the content
    * type, template and context. 
    * 
    * @param site the site where the item is to be published.
    * @param template the template used to publish the item.
    * @param content the content item
    * @param folder the folder where the item resides
    * @param context the assembly context id used for publishing the item. 
    * @return the URL.  
    */
   public String buildLinkUrl(IPSSite site, IPSAssemblyTemplate template,
         IPSGuid content, IPSGuid folder, int context);
}