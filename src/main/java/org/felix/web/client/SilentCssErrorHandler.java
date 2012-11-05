package org.felix.web.client;

import org.w3c.css.sac.CSSParseException;

import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;

public class SilentCssErrorHandler extends DefaultCssErrorHandler
{

	@Override
	public void error(CSSParseException exception)
	{
		// TODO Auto-generated method stub
		super.error(exception);
	}

	@Override
	public void fatalError(CSSParseException exception)
	{
		// TODO Auto-generated method stub
		super.fatalError(exception);
	}

	@Override
	public void warning(CSSParseException exception)
	{
		// TODO Auto-generated method stub
		// super.warning(exception);
	}

}
