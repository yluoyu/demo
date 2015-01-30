package com.vincent.demo.service.impl;

import java.util.List;

import org.dom4j.dom.DOMEntityReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vincent.demo.dao.DocumentDao;
import com.vincent.demo.entity.Document;
import com.vincent.demo.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao documentDao;
	
	@Override
	public Document createDocument(Document u) {
		documentDao.save(u);
		return null;
	}

	@Override
	public Document findById(String id) {
		documentDao.get(id);
		return documentDao.get(id);
	}

	@Override
	public List<Document> listAll() {
		// TODO Auto-generated method stub
		return documentDao.findAll();

	}

}
