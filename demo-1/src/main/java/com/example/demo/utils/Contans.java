package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contans {
	public static final String FORMAT_DATE="yyyy-MM-dd ";
	public static final String ERROR="Tên sản phẩm đã tồn tại vui lòng chọn tên khác";
	public static final String ERROR_DATE="Vui lòng chọn lại thời gian hiện tại";
	public static final String DELETE_SUCCESS="Xóa sản phẩm thành công";
	public static final String ID_DO_NOT_EXIT="Id sản phẩm không tồn tại";
	public static final String UPDATE_SUCCESS="Sửa sản phẩm thành công";
	public static final String ADD_SUCCESS="Thêm mới sản phẩm thành công";
	public static final String LOAD_DATA_SUCCESS="Dữ liệu sản phẩm đã được load thành công";
	public static final String YOU_CAN_CHOOSE_NAME="Bạn có thể chọn tên này";
    
	public static Date formatDate(Date dateTime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = sdf.format(dateTime);

			return sdf.parse(dateString);
		} catch (ParseException e) {
			return null;
		}

	}
	public static final String FIND_BY_USER_POSTED_BY="select p from Post p join p.postedBy u where u.userId=?1";
}
