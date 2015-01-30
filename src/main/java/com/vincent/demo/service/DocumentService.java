package com.vincent.demo.service;

import java.util.List;

import com.vincent.demo.entity.Document;
import com.vincent.demo.entity.User;



public interface DocumentService {


	Document createDocument(Document u);
	
	Document findById(String id);
	
    public List<Document> listAll();
}
