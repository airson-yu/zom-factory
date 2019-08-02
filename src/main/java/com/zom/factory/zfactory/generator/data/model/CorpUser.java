package com.zom.factory.zfactory.generator.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> ZoneIdAssign <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华-airson <p>
* <strong> 编写时间：</strong> 2017年8月30日12:20:28 <p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都零壹众科技有限公司 http://www.01more.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
*/
public class CorpUser implements Serializable {

	private static final long serialVersionUID = -5625452958621348805L;

	private List<Long>	userList	= new ArrayList<Long>(2000000);
	private List<Long>	con1List	= new ArrayList<Long>(100);
	private List<Long>	con2List	= new ArrayList<Long>(100);
	private List<Long>	con3List	= new ArrayList<Long>(100);
	private List<Long>	con4List	= new ArrayList<Long>(100);
	private List<Long>	con5List	= new ArrayList<Long>(100);
	private List<Long>	con6List	= new ArrayList<Long>(100);
	private List<Long>	con7List	= new ArrayList<Long>(100);

	private Map<Long, Long>	userZoneMap		= new HashMap<Long, Long>(2000000);
	private Map<Long, Long>	consoleZoneMap	= new HashMap<Long, Long>(200);

	public List<Long> getConAllList() {
		List<Long> conAllList = new ArrayList<Long>(300);//所有2-7阶
		conAllList.addAll(con1List);
		conAllList.addAll(con2List);
		conAllList.addAll(con3List);
		conAllList.addAll(con4List);
		conAllList.addAll(con5List);
		conAllList.addAll(con6List);
		conAllList.addAll(con7List);
		return conAllList;
	}

	public List<Long> getUserList() {
		return userList;
	}

	public void setUserList(List<Long> userList) {
		this.userList = userList;
	}

	public List<Long> getCon1List() {
		return con1List;
	}

	public void setCon1List(List<Long> con1List) {
		this.con1List = con1List;
	}

	public List<Long> getCon2List() {
		return con2List;
	}

	public void setCon2List(List<Long> con2List) {
		this.con2List = con2List;
	}

	public List<Long> getCon3List() {
		return con3List;
	}

	public void setCon3List(List<Long> con3List) {
		this.con3List = con3List;
	}

	public List<Long> getCon4List() {
		return con4List;
	}

	public void setCon4List(List<Long> con4List) {
		this.con4List = con4List;
	}

	public List<Long> getCon5List() {
		return con5List;
	}

	public void setCon5List(List<Long> con5List) {
		this.con5List = con5List;
	}

	public List<Long> getCon6List() {
		return con6List;
	}

	public void setCon6List(List<Long> con6List) {
		this.con6List = con6List;
	}

	public List<Long> getCon7List() {
		return con7List;
	}

	public void setCon7List(List<Long> con7List) {
		this.con7List = con7List;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<Long, Long> getUserZoneMap() {
		return userZoneMap;
	}

	public void setUserZoneMap(Map<Long, Long> userZoneMap) {
		this.userZoneMap = userZoneMap;
	}

	public Map<Long, Long> getConsoleZoneMap() {
		return consoleZoneMap;
	}

	public void setConsoleZoneMap(Map<Long, Long> consoleZoneMap) {
		this.consoleZoneMap = consoleZoneMap;
	}

}
