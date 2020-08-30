package com.xn.common.util.edit;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.xn.common.util.DateUtil;

public class DateEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else if (text.length() == 19) {
			setValue(DateUtil.string2Date(text, "yyyy-MM-dd HH:mm:ss"));
		} else if (text.length() == 16) {
			setValue(DateUtil.string2Date(text, "yyyy-MM-dd HH:mm"));
		} else if (text.length() == 10) {
			setValue(DateUtil.string2Date(text, "yyyy-MM-dd"));
		} else if (text.length() == 7) {
			setValue(DateUtil.string2Date(text, "yyyy-MM"));
		} else if (text.length() == 4) {
			setValue(DateUtil.string2Date(text, "yyyy"));
		}
	}

	@Override
	public String getAsText() {

		return getValue().toString();
	}
}
