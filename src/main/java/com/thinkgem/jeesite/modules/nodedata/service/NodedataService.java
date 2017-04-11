/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.nodedata.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.nodedata.entity.Nodedata;
import com.thinkgem.jeesite.modules.nodedata.dao.NodedataDao;

/**
 * nodeDataService
 * @author peng
 * @version 2017-02-13
 */
@Service
@Transactional(readOnly = true)
public class NodedataService extends CrudService<NodedataDao, Nodedata> {

	public Nodedata get(String id) {
		return super.get(id);
	}
	
	public List<Nodedata> findList(Nodedata nodedata) {
		return super.findList(nodedata);
	}
	
	public Page<Nodedata> findPage(Page<Nodedata> page, Nodedata nodedata) {
		return super.findPage(page, nodedata);
	}
	
	@Transactional(readOnly = false)
	public void save(Nodedata nodedata) {
		super.save(nodedata);
	}
	
	@Transactional(readOnly = false)
	public void delete(Nodedata nodedata) {
		super.delete(nodedata);
	}
	
}