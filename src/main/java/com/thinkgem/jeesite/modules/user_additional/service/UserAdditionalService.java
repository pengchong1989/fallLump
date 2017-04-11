/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.user_additional.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.user_additional.entity.UserAdditional;
import com.thinkgem.jeesite.modules.user_additional.dao.UserAdditionalDao;

/**
 * 用户附加信息Service
 * @author pengc
 * @version 2016-06-16
 */
@Service
@Transactional(readOnly = true)
public class UserAdditionalService extends CrudService<UserAdditionalDao, UserAdditional> {

	public UserAdditional get(String id) {
		return super.get(id);
	}
	
	public List<UserAdditional> findList(UserAdditional userAdditional) {
		return super.findList(userAdditional);
	}
	
	public Page<UserAdditional> findPage(Page<UserAdditional> page, UserAdditional userAdditional) {
		return super.findPage(page, userAdditional);
	}
	
	@Transactional(readOnly = false)
	public void save(UserAdditional userAdditional) {
		super.save(userAdditional);
	}
	
	@Transactional(readOnly = false)
	public void delete(UserAdditional userAdditional) {
		super.delete(userAdditional);
	}
	
}