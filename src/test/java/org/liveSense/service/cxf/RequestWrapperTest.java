package org.liveSense.service.cxf;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;


public class RequestWrapperTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public String getPath(String path) {
		MockHttpServletRequest req = new MockHttpServletRequest("GET", path);
		req.setPathInfo(path);
		RequestWrapper rw = new RequestWrapper(req, "/webservices/servlet");
		
		return rw.getPathInfo();
	}
	
	@Test
	public void test() throws URISyntaxException {
		assertEquals("prefix/webservices/servlet", getPath("prefix/webservices/servlet"));
		assertEquals("/prefix/webservices/servlet", getPath("/prefix/webservices/servlet"));
		assertEquals("/prefix/webservices/servlet/selector", getPath("/prefix/webservices/servlet/selector"));
		assertEquals("/prefix/webservices/servlet/selector1.selector2", getPath("/prefix/webservices/servlet/selector1.selector2"));

		assertEquals("/webservices/servlet", getPath("/webservices/servlet"));
		assertEquals("/webservices/servlet?query", getPath("/webservices/servlet?query"));
		assertEquals("/webservices/servlet?query&query2", getPath("/webservices/servlet?query&query2"));
		assertEquals("/webservices/servlet?query=value", getPath("/webservices/servlet?query=value"));
		assertEquals("/webservices/servlet?query=value&query2=value2", getPath("/webservices/servlet?query=value&query2=value2"));
		assertEquals("/webservices/servlet#fragment", getPath("/webservices/servlet#fragment"));
		assertEquals("/webservices/servlet?query#fragment", getPath("/webservices/servlet?query#fragment"));
		assertEquals("/webservices/servlet?query&query2#fragment", getPath("/webservices/servlet?query&query2#fragment"));
		assertEquals("/webservices/servlet?query=value#fragment", getPath("/webservices/servlet?query=value#fragment"));
		assertEquals("/webservices/servlet?query=value&query2=value2#fragment", getPath("/webservices/servlet?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet/", getPath("/webservices/servlet/"));
		assertEquals("/webservices/servlet/?query", getPath("/webservices/servlet/?query"));
		assertEquals("/webservices/servlet/?query&query2", getPath("/webservices/servlet/?query&query2"));
		assertEquals("/webservices/servlet/?query=value", getPath("/webservices/servlet/?query=value"));
		assertEquals("/webservices/servlet/?query=value&query2=value2", getPath("/webservices/servlet/?query=value&query2=value2"));
		assertEquals("/webservices/servlet/#fragment", getPath("/webservices/servlet/#fragment"));
		assertEquals("/webservices/servlet/?query#fragment", getPath("/webservices/servlet/?query#fragment"));
		assertEquals("/webservices/servlet/?query&query2#fragment", getPath("/webservices/servlet/?query&query2#fragment"));
		assertEquals("/webservices/servlet/?query=value#fragment", getPath("/webservices/servlet/?query=value#fragment"));
		assertEquals("/webservices/servlet/?query=value&query2=value2#fragment", getPath("/webservices/servlet/?query=value&query2=value2#fragment"));
		
		assertEquals("/webservices/servlet", getPath("/webservices/servlet."));
		assertEquals("/webservices/servlet?query", getPath("/webservices/servlet.?query"));
		assertEquals("/webservices/servlet?query&query2", getPath("/webservices/servlet.?query&query2"));
		assertEquals("/webservices/servlet?query=value", getPath("/webservices/servlet.?query=value"));
		assertEquals("/webservices/servlet?query=value&query2=value2", getPath("/webservices/servlet.?query=value&query2=value2"));
		assertEquals("/webservices/servlet#fragment", getPath("/webservices/servlet.#fragment"));
		assertEquals("/webservices/servlet?query#fragment", getPath("/webservices/servlet.?query#fragment"));
		assertEquals("/webservices/servlet?query&query2#fragment", getPath("/webservices/servlet.?query&query2#fragment"));
		assertEquals("/webservices/servlet?query=value#fragment", getPath("/webservices/servlet.?query=value#fragment"));
		assertEquals("/webservices/servlet?query=value&query2=value2#fragment", getPath("/webservices/servlet.?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet/", getPath("/webservices/servlet./"));
		assertEquals("/webservices/servlet/?query", getPath("/webservices/servlet./?query"));
		assertEquals("/webservices/servlet/?query&query2", getPath("/webservices/servlet./?query&query2"));
		assertEquals("/webservices/servlet/?query=value", getPath("/webservices/servlet./?query=value"));
		assertEquals("/webservices/servlet/?query=value&query2=value2", getPath("/webservices/servlet./?query=value&query2=value2"));
		assertEquals("/webservices/servlet/#fragment", getPath("/webservices/servlet./#fragment"));
		assertEquals("/webservices/servlet/?query#fragment", getPath("/webservices/servlet./?query#fragment"));
		assertEquals("/webservices/servlet/?query&query2#fragment", getPath("/webservices/servlet./?query&query2#fragment"));
		assertEquals("/webservices/servlet/?query=value#fragment", getPath("/webservices/servlet./?query=value#fragment"));
		assertEquals("/webservices/servlet/?query=value&query2=value2#fragment", getPath("/webservices/servlet./?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet.type", getPath("/webservices/servlet.type"));
		assertEquals("/webservices/servlet.type?query", getPath("/webservices/servlet.type?query"));
		assertEquals("/webservices/servlet.type?query&query2", getPath("/webservices/servlet.type?query&query2"));
		assertEquals("/webservices/servlet.type?query=value", getPath("/webservices/servlet.type?query=value"));
		assertEquals("/webservices/servlet.type?query=value&query2=value2", getPath("/webservices/servlet.type?query=value&query2=value2"));
		assertEquals("/webservices/servlet.type#fragment", getPath("/webservices/servlet.type#fragment"));
		assertEquals("/webservices/servlet.type?query#fragment", getPath("/webservices/servlet.type?query#fragment"));
		assertEquals("/webservices/servlet.type?query&query2#fragment", getPath("/webservices/servlet.type?query&query2#fragment"));
		assertEquals("/webservices/servlet.type?query=value#fragment", getPath("/webservices/servlet.type?query=value#fragment"));
		assertEquals("/webservices/servlet.type?query=value&query2=value2#fragment", getPath("/webservices/servlet.type?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet/.type", getPath("/webservices/servlet.type/"));
		assertEquals("/webservices/servlet/.type?query", getPath("/webservices/servlet.type/?query"));
		assertEquals("/webservices/servlet/.type?query&query2", getPath("/webservices/servlet.type/?query&query2"));
		assertEquals("/webservices/servlet/.type?query=value", getPath("/webservices/servlet.type/?query=value"));
		assertEquals("/webservices/servlet/.type?query=value&query2=value2", getPath("/webservices/servlet.type/?query=value&query2=value2"));
		assertEquals("/webservices/servlet/.type#fragment", getPath("/webservices/servlet.type/#fragment"));
		assertEquals("/webservices/servlet/.type?query#fragment", getPath("/webservices/servlet.type/?query#fragment"));
		assertEquals("/webservices/servlet/.type?query&query2#fragment", getPath("/webservices/servlet.type/?query&query2#fragment"));
		assertEquals("/webservices/servlet/.type?query=value#fragment", getPath("/webservices/servlet.type/?query=value#fragment"));
		assertEquals("/webservices/servlet/.type?query=value&query2=value2#fragment", getPath("/webservices/servlet.type/?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet.type.language", getPath("/webservices/servlet.type.language"));
		assertEquals("/webservices/servlet.type.language?query", getPath("/webservices/servlet.type.language?query"));
		assertEquals("/webservices/servlet.type.language?query&query2", getPath("/webservices/servlet.type.language?query&query2"));
		assertEquals("/webservices/servlet.type.language?query=value", getPath("/webservices/servlet.type.language?query=value"));
		assertEquals("/webservices/servlet.type.language?query=value&query2=value2", getPath("/webservices/servlet.type.language?query=value&query2=value2"));
		assertEquals("/webservices/servlet.type.language#fragment", getPath("/webservices/servlet.type.language#fragment"));
		assertEquals("/webservices/servlet.type.language?query#fragment", getPath("/webservices/servlet.type.language?query#fragment"));
		assertEquals("/webservices/servlet.type.language?query&query2#fragment", getPath("/webservices/servlet.type.language?query&query2#fragment"));
		assertEquals("/webservices/servlet.type.language?query=value#fragment", getPath("/webservices/servlet.type.language?query=value#fragment"));
		assertEquals("/webservices/servlet.type.language?query=value&query2=value2#fragment", getPath("/webservices/servlet.type.language?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet/.type.language", getPath("/webservices/servlet.type.language/"));
		assertEquals("/webservices/servlet/.type.language?query", getPath("/webservices/servlet.type.language/?query"));
		assertEquals("/webservices/servlet/.type.language?query&query2", getPath("/webservices/servlet.type.language/?query&query2"));
		assertEquals("/webservices/servlet/.type.language?query=value", getPath("/webservices/servlet.type.language/?query=value"));
		assertEquals("/webservices/servlet/.type.language?query=value&query2=value2", getPath("/webservices/servlet.type.language/?query=value&query2=value2"));
		assertEquals("/webservices/servlet/.type.language#fragment", getPath("/webservices/servlet.type.language/#fragment"));
		assertEquals("/webservices/servlet/.type.language?query#fragment", getPath("/webservices/servlet.type.language/?query#fragment"));
		assertEquals("/webservices/servlet/.type.language?query&query2#fragment", getPath("/webservices/servlet.type.language/?query&query2#fragment"));
		assertEquals("/webservices/servlet/.type.language?query=value#fragment", getPath("/webservices/servlet.type.language/?query=value#fragment"));
		assertEquals("/webservices/servlet/.type.language?query=value&query2=value2#fragment", getPath("/webservices/servlet.type.language/?query=value&query2=value2#fragment"));

		
		assertEquals("/webservices/servlet/function", getPath("/webservices/servlet/function"));
		assertEquals("/webservices/servlet/function?query", getPath("/webservices/servlet/function?query"));
		assertEquals("/webservices/servlet/function?query&query2", getPath("/webservices/servlet/function?query&query2"));
		assertEquals("/webservices/servlet/function?query=value", getPath("/webservices/servlet/function?query=value"));
		assertEquals("/webservices/servlet/function?query=value&query2=value2", getPath("/webservices/servlet/function?query=value&query2=value2"));
		assertEquals("/webservices/servlet/function#fragment", getPath("/webservices/servlet/function#fragment"));
		assertEquals("/webservices/servlet/function?query#fragment", getPath("/webservices/servlet/function?query#fragment"));
		assertEquals("/webservices/servlet/function?query&query2#fragment", getPath("/webservices/servlet/function?query&query2#fragment"));
		assertEquals("/webservices/servlet/function?query=value#fragment", getPath("/webservices/servlet/function?query=value#fragment"));
		assertEquals("/webservices/servlet/function?query=value&query2=value2#fragment", getPath("/webservices/servlet/function?query=value&query2=value2#fragment"));
		
		assertEquals("/webservices/servlet/function", getPath("/webservices/servlet./function"));
		assertEquals("/webservices/servlet/function?query", getPath("/webservices/servlet./function?query"));
		assertEquals("/webservices/servlet/function?query&query2", getPath("/webservices/servlet./function?query&query2"));
		assertEquals("/webservices/servlet/function?query=value", getPath("/webservices/servlet./function?query=value"));
		assertEquals("/webservices/servlet/function?query=value&query2=value2", getPath("/webservices/servlet./function?query=value&query2=value2"));
		assertEquals("/webservices/servlet/function#fragment", getPath("/webservices/servlet./function#fragment"));
		assertEquals("/webservices/servlet/function?query#fragment", getPath("/webservices/servlet./function?query#fragment"));
		assertEquals("/webservices/servlet/function?query&query2#fragment", getPath("/webservices/servlet./function?query&query2#fragment"));
		assertEquals("/webservices/servlet/function?query=value#fragment", getPath("/webservices/servlet./function?query=value#fragment"));
		assertEquals("/webservices/servlet/function?query=value&query2=value2#fragment", getPath("/webservices/servlet./function?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet/function/", getPath("/webservices/servlet./function/"));
		assertEquals("/webservices/servlet/function/?query", getPath("/webservices/servlet./function/?query"));
		assertEquals("/webservices/servlet/function/?query&query2", getPath("/webservices/servlet./function/?query&query2"));
		assertEquals("/webservices/servlet/function/?query=value", getPath("/webservices/servlet./function/?query=value"));
		assertEquals("/webservices/servlet/function/?query=value&query2=value2", getPath("/webservices/servlet./function/?query=value&query2=value2"));
		assertEquals("/webservices/servlet/function/#fragment", getPath("/webservices/servlet./function/#fragment"));
		assertEquals("/webservices/servlet/function/?query#fragment", getPath("/webservices/servlet./function/?query#fragment"));
		assertEquals("/webservices/servlet/function/?query&query2#fragment", getPath("/webservices/servlet./function/?query&query2#fragment"));
		assertEquals("/webservices/servlet/function/?query=value#fragment", getPath("/webservices/servlet./function/?query=value#fragment"));
		assertEquals("/webservices/servlet/function/?query=value&query2=value2#fragment", getPath("/webservices/servlet./function/?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet/function.type", getPath("/webservices/servlet.type/function"));
		assertEquals("/webservices/servlet/function.type?query", getPath("/webservices/servlet.type/function?query"));
		assertEquals("/webservices/servlet/function.type?query&query2", getPath("/webservices/servlet.type/function?query&query2"));
		assertEquals("/webservices/servlet/function.type?query=value", getPath("/webservices/servlet.type/function?query=value"));
		assertEquals("/webservices/servlet/function.type?query=value&query2=value2", getPath("/webservices/servlet.type/function?query=value&query2=value2"));
		assertEquals("/webservices/servlet/function.type#fragment", getPath("/webservices/servlet.type/function#fragment"));
		assertEquals("/webservices/servlet/function.type?query#fragment", getPath("/webservices/servlet.type/function?query#fragment"));
		assertEquals("/webservices/servlet/function.type?query&query2#fragment", getPath("/webservices/servlet.type/function?query&query2#fragment"));
		assertEquals("/webservices/servlet/function.type?query=value#fragment", getPath("/webservices/servlet.type/function?query=value#fragment"));
		assertEquals("/webservices/servlet/function.type?query=value&query2=value2#fragment", getPath("/webservices/servlet.type/function?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet/function.type", getPath("/webservices/servlet.type/function"));
		assertEquals("/webservices/servlet/function.type?query", getPath("/webservices/servlet.type/function?query"));
		assertEquals("/webservices/servlet/function.type?query&query2", getPath("/webservices/servlet.type/function?query&query2"));
		assertEquals("/webservices/servlet/function.type?query=value", getPath("/webservices/servlet.type/function?query=value"));
		assertEquals("/webservices/servlet/function.type?query=value&query2=value2", getPath("/webservices/servlet.type/function?query=value&query2=value2"));
		assertEquals("/webservices/servlet/function.type#fragment", getPath("/webservices/servlet.type/function#fragment"));
		assertEquals("/webservices/servlet/function.type?query#fragment", getPath("/webservices/servlet.type/function?query#fragment"));
		assertEquals("/webservices/servlet/function.type?query&query2#fragment", getPath("/webservices/servlet.type/function?query&query2#fragment"));
		assertEquals("/webservices/servlet/function.type?query=value#fragment", getPath("/webservices/servlet.type/function?query=value#fragment"));
		assertEquals("/webservices/servlet/function.type?query=value&query2=value2#fragment", getPath("/webservices/servlet.type/function?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet/function.type.language", getPath("/webservices/servlet.type.language/function"));
		assertEquals("/webservices/servlet/function.type.language?query", getPath("/webservices/servlet.type.language/function?query"));
		assertEquals("/webservices/servlet/function.type.language?query&query2", getPath("/webservices/servlet.type.language/function?query&query2"));
		assertEquals("/webservices/servlet/function.type.language?query=value", getPath("/webservices/servlet.type.language/function?query=value"));
		assertEquals("/webservices/servlet/function.type.language?query=value&query2=value2", getPath("/webservices/servlet.type.language/function?query=value&query2=value2"));
		assertEquals("/webservices/servlet/function.type.language#fragment", getPath("/webservices/servlet.type.language/function#fragment"));
		assertEquals("/webservices/servlet/function.type.language?query#fragment", getPath("/webservices/servlet.type.language/function?query#fragment"));
		assertEquals("/webservices/servlet/function.type.language?query&query2#fragment", getPath("/webservices/servlet.type.language/function?query&query2#fragment"));
		assertEquals("/webservices/servlet/function.type.language?query=value#fragment", getPath("/webservices/servlet.type.language/function?query=value#fragment"));
		assertEquals("/webservices/servlet/function.type.language?query=value&query2=value2#fragment", getPath("/webservices/servlet.type.language/function?query=value&query2=value2#fragment"));

		assertEquals("/webservices/servlet/function.type.language", getPath("/webservices/servlet.type.language/function"));
		assertEquals("/webservices/servlet/function.type.language?query", getPath("/webservices/servlet.type.language/function?query"));
		assertEquals("/webservices/servlet/function.type.language?query&query2", getPath("/webservices/servlet.type.language/function?query&query2"));
		assertEquals("/webservices/servlet/function.type.language?query=value", getPath("/webservices/servlet.type.language/function?query=value"));
		assertEquals("/webservices/servlet/function.type.language?query=value&query2=value2", getPath("/webservices/servlet.type.language/function?query=value&query2=value2"));
		assertEquals("/webservices/servlet/function.type.language#fragment", getPath("/webservices/servlet.type.language/function#fragment"));
		assertEquals("/webservices/servlet/function.type.language?query#fragment", getPath("/webservices/servlet.type.language/function?query#fragment"));
		assertEquals("/webservices/servlet/function.type.language?query&query2#fragment", getPath("/webservices/servlet.type.language/function?query&query2#fragment"));
		assertEquals("/webservices/servlet/function.type.language?query=value#fragment", getPath("/webservices/servlet.type.language/function?query=value#fragment"));
		assertEquals("/webservices/servlet/function.type.language?query=value&query2=value2#fragment", getPath("/webservices/servlet.type.language/function?query=value&query2=value2#fragment"));


		
	
	}

}
