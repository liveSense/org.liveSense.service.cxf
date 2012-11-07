package org.liveSense.service.cxf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.InitialContext;

import org.apache.cxf.resource.ResourceResolver;

public class ClassLoaderResourceResolver implements ResourceResolver {

	ClassLoader cl = null;
	Map<String, URL> urlMap = new ConcurrentHashMap<String, URL>();


	public ClassLoaderResourceResolver(ClassLoader cl) {
	}

	public <T> T resolve(String entryName, Class<T> clz) {
		Object obj = null;
		try {
			if (entryName != null) {
				InitialContext ic = new InitialContext();
				try {
					obj = ic.lookup(entryName);
				} finally {
					ic.close();
				}
			}
		} catch (Throwable e) {
			//do nothing
		}

		if (obj != null && clz.isInstance(obj)) {
			return clz.cast(obj);
		}

		if (clz.isAssignableFrom(URL.class)) {
			if (urlMap.containsKey(entryName)) {
				return clz.cast(urlMap.get(entryName));
			}
			try {
				URL url = cl.getResource(entryName);
				if (url != null
						&& "file".equals(url.getProtocol())
						&& !(new File(url.toURI()).exists())) {
					url = null;
				}
				if (url != null) {
					urlMap.put(url.toString(), url);
					return clz.cast(url);
				}
			} catch (URISyntaxException e) {
				//ignore
			}
			try {
				URL url = cl.getResource("/" + entryName);
				if (url != null
						&& "file".equals(url.getProtocol())
						&& !(new File(url.toURI()).exists())) {
					url = null;
				}
				if (url != null) {
					urlMap.put(url.toString(), url);
					return clz.cast(url);
				}
			} catch (URISyntaxException e) {
				//ignore
			}
		} else if (clz.isAssignableFrom(InputStream.class)) {
			return clz.cast(getAsStream(entryName));
		}
		return null;	
	}


	public InputStream getAsStream(String name) {
		if (urlMap.containsKey(name)) {
			try {
				return urlMap.get(name).openStream();
			} catch (IOException e) {
				//ignore
			}
		}
		return cl.getResourceAsStream(name);	}
}
