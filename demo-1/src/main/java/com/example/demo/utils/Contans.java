package com.example.demo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contans {
	public static final String FIND_BY_USER_POSTED_BY="select p from Post p join p.postedBy u where u.userId=?1";
	
	public static final String FORMAT_DATE="yyyy-MM-dd ";
	public static final String ERROR="Tên sản phẩm đã tồn tại vui lòng chọn tên khác";
	public static final String ERROR_DATE="Vui lòng chọn lại thời gian hiện tại";
	public static final String DELETE_SUCCESS="Xóa sản phẩm thành công";
	public static final String ID_DO_NOT_EXIT="Id sản phẩm không tồn tại";
	public static final String UPDATE_SUCCESS="Sửa sản phẩm thành công";
	public static final String ADD_SUCCESS="Thêm mới sản phẩm thành công";
	public static final String LOAD_DATA_SUCCESS="Dữ liệu sản phẩm đã được load thành công";
	public static final String YOU_CAN_CHOOSE_NAME="Bạn có thể chọn tên này";
	public static final String ADD_SUCCESS_USER="Tạo tài khoản mới thành công";
	public static final String UPLOAD_PATH="//static//";
	
	public static Date formatDate(Date dateTime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = sdf.format(dateTime);

			return sdf.parse(dateString);
		} catch (ParseException e) {
			return null;
		}

	}

	public static void saveFile(InputStream inputStream,String path) {
		try {
			OutputStream outputStream=new FileOutputStream(new File(path));
			int read=0;
			byte[] bytes=new byte[1024];
			while ((read=inputStream.read(bytes))!=-1) {
				outputStream.write(bytes,0,read);
				outputStream.flush();
				outputStream.close();
				
			}
		} catch (Exception e) {
			
		}
	}
	
}
