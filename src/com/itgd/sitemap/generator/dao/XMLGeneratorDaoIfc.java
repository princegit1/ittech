package com.itgd.sitemap.generator.dao;

import java.util.List;

import com.itgd.dto.xmlcontent.XmlContentDTO;

public interface XMLGeneratorDaoIfc {
	public List<XmlContentDTO> processToGenerateSectionStoryXml(int sectionId) throws Exception;
}
