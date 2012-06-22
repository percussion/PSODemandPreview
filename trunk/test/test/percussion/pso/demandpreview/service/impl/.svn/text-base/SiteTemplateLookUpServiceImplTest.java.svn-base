package test.percussion.pso.demandpreview.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.percussion.pso.demandpreview.exception.SiteLookUpException;
import com.percussion.pso.demandpreview.service.SiteEditionConfig;
import com.percussion.pso.demandpreview.service.SiteEditionHolder;
import com.percussion.pso.demandpreview.service.SiteEditionLookUpService;
import com.percussion.pso.demandpreview.service.impl.SiteEditionLookUpServiceImpl;
import com.percussion.services.assembly.IPSAssemblyService;
import com.percussion.services.assembly.IPSAssemblyTemplate;
import com.percussion.services.assembly.PSAssemblyException;
import com.percussion.services.catalog.PSTypeEnum;
import com.percussion.services.error.PSNotFoundException;
import com.percussion.services.guidmgr.IPSGuidManager;
import com.percussion.services.publisher.IPSEdition;
import com.percussion.services.publisher.IPSPublisherService;
import com.percussion.services.sitemgr.IPSSite;
import com.percussion.services.sitemgr.IPSSiteManager;
import com.percussion.utils.guid.IPSGuid;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SiteTemplateLookUpServiceImplTest extends TestCase 
{
	private static Log log = LogFactory.getLog(SiteTemplateLookUpServiceImplTest.class);
	
	Mockery context;
	TestLookUpService lookUp;
	
	IPSSiteManager siteManager;
	IPSPublisherService publisherService;
	IPSAssemblyService asm; 
	SiteEditionLookUpService siteEditionLookUpService;
	
	IPSGuidManager guidManager;

	
	@Before
	protected void setUp() throws Exception 
	{
		context = new Mockery();
		lookUp = new TestLookUpService();
		siteManager = context.mock(IPSSiteManager.class);
		publisherService = context.mock(IPSPublisherService.class);
		siteEditionLookUpService = context.mock(SiteEditionLookUpService.class);
		guidManager = context.mock(IPSGuidManager.class);
		asm = context.mock(IPSAssemblyService.class); 
		lookUp.setSiteManager(siteManager);
		lookUp.setPubisherService(publisherService);
		lookUp.setGuidManager(guidManager);
		lookUp.setAsm(asm); 
	}
	
	@Test 
	public void testLookUpSiteEdition() throws SiteLookUpException
	{
		Map<String, SiteEditionConfig> siteLookUpMap = new HashMap<String, SiteEditionConfig>();
		SiteEditionConfig sConfig = new SiteEditionConfig();
		sConfig.setSiteName("psoSite");
		sConfig.setEditionName("psoEdition"); 
		sConfig.setAssemblyContext(1); 
		siteLookUpMap.put("psoSite", sConfig);
		lookUp.setSiteLookUpMap(siteLookUpMap);
		
		final String siteId = "234";
		final SiteEditionHolder stHolder = new SiteEditionHolder();
		final IPSSite site = context.mock(IPSSite.class); 
		final IPSSite pSite = context.mock(IPSSite.class);
		final IPSEdition edition = context.mock(IPSEdition.class);
		final IPSGuid siteGuid = context.mock(IPSGuid.class);
		final IPSAssemblyTemplate template = context.mock(IPSAssemblyTemplate.class); 
		stHolder.setSite(pSite);
		stHolder.setEdition(edition);
		try
		{
		   context.checking(new Expectations(){{
			one(guidManager).makeGuid(Integer.parseInt(siteId), PSTypeEnum.SITE);
			will(returnValue(siteGuid));
			one(siteManager).loadSite(siteGuid);
			will(returnValue(site));
			atLeast(1).of(site).getName();
			will(returnValue("psoSite"));
			one(publisherService).findEditionByName("psoEdition");
			will(returnValue(edition));		
			one(siteManager).loadSite("psoSite"); 
			will(returnValue(pSite));
		   }});	   
		}
		catch (SiteLookUpException ex)
		{
			log.error("Error looking up site info", ex);
			fail("Exception"); 
		} 
		
		SiteEditionHolder holder = lookUp.LookUpSiteEdition(siteId);
		assertNotNull(holder);
		assertSame(holder.getClass(), stHolder.getClass());
	    context.assertIsSatisfied();
	}
	
	@Test
	public void testLookupWithWrongSite() throws SiteLookUpException
	{
		Map<String, SiteEditionConfig> siteLookUpMap = new HashMap<String, SiteEditionConfig>();
		SiteEditionConfig sConfig = new SiteEditionConfig();
		sConfig.setSiteName("psoSite");
		sConfig.setEditionName("psoTemplate");
		siteLookUpMap.put("siteName", sConfig);
		lookUp.setSiteLookUpMap(siteLookUpMap);
		
		final String siteId = "234";
		final SiteEditionHolder stHolder = new SiteEditionHolder();
		final IPSSite site = context.mock(IPSSite.class); 
		final IPSSite pSite = context.mock(IPSSite.class);
		final IPSEdition eidtion = context.mock(IPSEdition.class);
		final IPSGuid siteGuid = context.mock(IPSGuid.class);
		stHolder.setSite(pSite);
		stHolder.setEdition(eidtion);
		try
		{
		   context.checking(new Expectations(){{
		      one(guidManager).makeGuid(142, PSTypeEnum.SITE);
	            will(returnValue(siteGuid));
	            one(siteManager).loadSite(siteGuid);
	            will(throwException(new PSNotFoundException("Site Not Found")));
	     	   }});	   
		   lookUp.LookUpSiteEdition("142");
           Assert.fail("Test with wrong site name failed");
		}
		catch (SiteLookUpException ex)
		{
			log.info("Got expected exception " + ex, ex);
		}
		context.assertIsSatisfied();

	}
	
	@Test
	public void testLookWithNoSiteName() throws SiteLookUpException
	{
		Map<String, SiteEditionConfig> siteLookUpMap = new HashMap<String, SiteEditionConfig>();
		SiteEditionConfig sConfig = new SiteEditionConfig();
		sConfig.setSiteName("");
		sConfig.setEditionName("psoTemplate");
		siteLookUpMap.put("site", sConfig);
		lookUp.setSiteLookUpMap(siteLookUpMap);
		
		final String siteId = "234";
		final SiteEditionHolder stHolder = new SiteEditionHolder();
		final IPSSite site = context.mock(IPSSite.class); 
		final IPSSite pSite = context.mock(IPSSite.class);
		final IPSEdition eidtion = context.mock(IPSEdition.class);
		final IPSGuid siteGuid = context.mock(IPSGuid.class);
		stHolder.setSite(pSite);
		stHolder.setEdition(eidtion);
		try
		{
		   context.checking(new Expectations(){{
		      one(guidManager).makeGuid(Integer.parseInt(siteId), PSTypeEnum.SITE);
		      will(returnValue(siteGuid));		
		      one(siteManager).loadSite(siteGuid);
		      will(returnValue(site));
		      atLeast(1).of(site).getName();
		      will(returnValue("siteName"));
		   }});	   

		   lookUp.LookUpSiteEdition(siteId);
		   fail("Test with empty site name failed");
		}
		catch (SiteLookUpException ex)
		{
			log.error("Error looking up site info", ex);
		}
		context.assertIsSatisfied();

	}
	
	@Test
	public void testLookWithNoSiteEdition() throws SiteLookUpException
	{
		Map<String, SiteEditionConfig> siteLookUpMap = new HashMap<String, SiteEditionConfig>();
		SiteEditionConfig sConfig = new SiteEditionConfig();
		sConfig.setSiteName("psoSite");
		sConfig.setEditionName("");
		siteLookUpMap.put("site", sConfig);
		lookUp.setSiteLookUpMap(siteLookUpMap);
		
		final String siteId = "234";
		final SiteEditionHolder stHolder = new SiteEditionHolder();
		final IPSSite site = context.mock(IPSSite.class);
		final IPSSite pSite = context.mock(IPSSite.class);
		final IPSEdition eidtion = context.mock(IPSEdition.class);
		final IPSGuid siteGuid = context.mock(IPSGuid.class);
		stHolder.setSite(pSite);
		stHolder.setEdition(eidtion);
		try
		{
		   context.checking(new Expectations(){{
			one(guidManager).makeGuid(Integer.parseInt(siteId), PSTypeEnum.SITE);
			will(returnValue(siteGuid));		
			one(siteManager).loadSite(siteGuid);
            will(returnValue(site));
			atLeast(1).of(site).getName();
	        will(returnValue("siteName"));	        
		   }});
		   
		   lookUp.LookUpSiteEdition(siteId);
           fail("Test with empty edition Name failed");
		}
		catch (SiteLookUpException ex)
		{
			log.error("Error looking up site info", ex);
		}
	    context.assertIsSatisfied(); 

	}	
	
	private class TestLookUpService extends SiteEditionLookUpServiceImpl
	{
		@Override
		public void setSiteManager(IPSSiteManager siteManager)
		{
			super.setSiteManager(siteManager);
		}
		@Override
		public void setPubisherService(IPSPublisherService publisherService)
		{
			super.setPubisherService(publisherService);
		}
		@Override
		public void setGuidManager(IPSGuidManager guidManager)
		{
			super.setGuidManager(guidManager);
		}
      /**
       * @see com.percussion.pso.demandpreview.service.impl.SiteEditionLookUpServiceImpl#setAsm(com.percussion.services.assembly.IPSAssemblyService)
       */
      @Override
      public void setAsm(IPSAssemblyService asm)
      {
          super.setAsm(asm);
      }
		
	}

}
