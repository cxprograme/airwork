package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jdbc.JdbcConnection;
import user.uentity;

/**
 * Servlet implementation class UploadExcelServlet
 */
@WebServlet("/UploadExcelServlet")
@MultipartConfig//标识Servlet支持文件上传
public class UploadExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadExcelServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 获取服务器路径 上传文件到服务器
		String dirPath = request.getServletContext().getRealPath("/");
	/*	InputStream in = request.getInputStream();
		File f = new File(dirPath, "temp.xlsx");
		FileOutputStream fos = new FileOutputStream(f);
		byte[] b = new byte[1024];
		int n = 0;
		while ((n = in.read(b)) != -1) {
			fos.write(b, 0, n);
		}
		fos.close();
		in.close();*/
		// 读取上传到服务器的文件

		Part part = request.getPart("file");// 通过表单file控件(<input type="file"
											// name="file">)的名字直接获取Part对象
		// Servlet3没有提供直接获取文件名的方法,需要从请求头中解析出来
		// 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
		String header = part.getHeader("content-disposition");
		// 获取文件名
		String fileName = getFileName(header);
		// 把文件写到指定路径
		part.write(dirPath + fileName);
		
		
		File file = new File(dirPath,fileName);
		file = new File(file.getPath());
		try {
			OPCPackage fs = OPCPackage.open(new FileInputStream(file));
			// 创建工作簿
			XSSFWorkbook workBook = new XSSFWorkbook(fs);
			/**
			 * 获得Excel中工作表个数
			 */
			out.println("工作表个数 :" + workBook.getNumberOfSheets() + "<br>");

			List<uentity> uList = new ArrayList<>();
			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {

				out.println("<font color='red'> " + i + " ***************工作表名称：" + workBook.getSheetName(i)
						+ "  ************</font><br>");
				// 创建工作表
				XSSFSheet sheet = workBook.getSheetAt(i);
				int rows = sheet.getPhysicalNumberOfRows(); // 获得行数
				if (rows > 0) {
					sheet.getMargin(XSSFSheet.TopMargin);
					for (int j = 1; j < rows; j++) { // 行循环
						XSSFRow row = sheet.getRow(j);
						uentity u = new uentity();
						if (row != null) {
							int cells = row.getLastCellNum();// 获得列数
							for (int k = 0; k < cells; k++) { // 列循环
								XSSFCell cell = row.getCell(k);
								// /////////////////////
								if (cell != null) {
									String value = "";
									switch (cell.getCellType()) {

									// 数值型
									case XSSFCell.CELL_TYPE_NUMERIC:
										if (HSSFDateUtil.isCellDateFormatted(cell)) {
											// 如果是date类型则 ，获取该cell的date值
											value = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
											out.println("第" + j + "行,第" + k + "列值：" + value + "<br>");
										} else {// 纯数字

											value = String.valueOf(cell.getNumericCellValue());
											out.println("第" + j + "行,第" + k + "列值：" + value + "<br>");
										}
										break;

									/* 此行表示单元格的内容为string类型 */
									case XSSFCell.CELL_TYPE_STRING: // 字符串型
										value = cell.getRichStringCellValue().toString();
										out.println("第" + j + "行,第" + k + "列值：" + value + "<br>");
										break;

									// 公式型
									case XSSFCell.CELL_TYPE_FORMULA:
										// 读公式计算值
										value = String.valueOf(cell.getNumericCellValue());
										if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串

											value = cell.getRichStringCellValue().toString();
										}
										// cell.getCellFormula();读公式
										out.println("第" + j + "行,第" + k + "列值：" + value + "<br>");
										break;

									// 布尔
									case XSSFCell.CELL_TYPE_BOOLEAN:
										value = " " + cell.getBooleanCellValue();
										out.println("第" + j + "行,第" + k + "列值：" + value + "<br>");
										break;

									/* 此行表示该单元格值为空 */
									case XSSFCell.CELL_TYPE_BLANK: // 空值
										value = "";
										out.println("第" + j + "行,第" + k + "列值：" + value + "<br>");
										break;
									case XSSFCell.CELL_TYPE_ERROR: // 故障
										value = "";
										out.println("第" + j + "行,第" + k + "列值：" + value + "<br>");
										break;
									default:
										value = cell.getRichStringCellValue().toString();
										out.println("第" + j + "行,第" + k + "列值：" + value + "<br>");
									}

									// 如果循环的是第一列则值放入中
									if (k == 0) {
										u.setName(value);
									}
								}
							}
						}

						// 上面的是完整的读出一行，可以执行插入操作的说哦,将得到的一条用户数据，放入ulist中
						uList.add(u);
					}
				}
			}
			JdbcConnection.saveBatch(uList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print("<script>alert('上传完毕');</script>");
		out.close();
		
	}
	
	
    /**
   * 根据请求头解析出文件名
    * 请求头的格式：火狐和google浏览器下：form-data; name="file"; filename="snmp4j--api.zip"
   *                 IE浏览器下：form-data; name="file"; filename="E:\snmp4j--api.zip"
    * @param header 请求头
    * @return 文件名
    */
   public String getFileName(String header) {
       /**
        * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
        * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
        * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
       */         
	   String[] tempArr1 = header.split(";");
       /**
        *火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
        *IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
        */
       String[] tempArr2 = tempArr1[2].split("=");
       //获取文件名，兼容各种浏览器的写法
       String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");
                return fileName;
   }

}
