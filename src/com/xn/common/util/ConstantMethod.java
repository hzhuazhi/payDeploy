package com.xn.common.util;

import com.xn.common.constant.Constant;







/**
 * 
 *<p>Title: 公共方法类</p>
 * @Description: TODO(用一句话描述该文件做什么)
 * @author yoko
 * @date 2016-3-1下午12:16:40
 */
public class ConstantMethod {
	
	/**
	 * 
	 *<p>Title: 创建业务ID</p>
	 * @Description: TODO(创建业务的ID)
	 * @param id-生成的id
	 * @param type-业务类型：cp、tp、pj
	 * @return type+_+id    如cp_1000
	 * @author yoko
	 * @date 2016-3-1下午12:17:56
	 */
	public String primaryKey(long id,String type){
		String str="";
			
			if (type.equals(Constant.PRIMARY_TYPE_VALUE_DM)) {
				str=Constant.PRIMARY_TYPE_VALUE_DM+Constant.PRIMARY_TYPE_UNDERLINE+id;
			}else if (type.equals(Constant.PRIMARY_TYPE_VALUE_CH)) {
				str=Constant.PRIMARY_TYPE_VALUE_CH+Constant.PRIMARY_TYPE_UNDERLINE+id;
			}else if (type.equals(Constant.PRIMARY_TYPE_VALUE_PJ)) {
				str=Constant.PRIMARY_TYPE_VALUE_PJ+Constant.PRIMARY_TYPE_UNDERLINE+id;
			}else if (type.equals(Constant.PRIMARY_TYPE_VALUE_XNJ)) {
				str=Constant.PRIMARY_TYPE_VALUE_XNJ+Constant.PRIMARY_TYPE_UNDERLINE+id;
			}else if (type.equals(Constant.PRIMARY_TYPE_VALUE_YW)) {
				str=Constant.PRIMARY_TYPE_VALUE_YW+Constant.PRIMARY_TYPE_UNDERLINE+id;
			}else if (type.equals(Constant.PRIMARY_TYPE_VALUE_SDK)) {
				str=Constant.PRIMARY_TYPE_VALUE_SDK+Constant.PRIMARY_TYPE_UNDERLINE+id;
			}else if (type.equals(Constant.PRIMARY_TYPE_VALUE_DL)) {
				str=Constant.PRIMARY_TYPE_VALUE_DL+Constant.PRIMARY_TYPE_UNDERLINE+id;
			}
		return str;
	}

}
