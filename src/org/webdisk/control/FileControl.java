package org.webdisk.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.webdisk.pojo.FileInFo;
import org.webdisk.service.FileService;

@Controller("fileControl")
@RequestMapping("/upload")
public class FileControl {
	private FileService fileService;

	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String insert(MultipartFile file01, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String value = (String) session.getAttribute("user");

		File file = new File("F:/WebDisk/upload/" + value + "/" + file01.getOriginalFilename());
		try {
			file01.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileInFo fileInFo01 = new FileInFo();
		fileInFo01.setFileName(file01.getOriginalFilename());
		fileInFo01.setFileSize(file.length());
		fileInFo01.setUserName(value);
		fileInFo01.setFilaUploadDate(new Date());
		fileInFo01.setFilePath("F:/WebDisk/upload/" + value + "/" + file01.getOriginalFilename());
		FileInFo fileInFo = new FileInFo();
		fileInFo.setUserName(value);
		fileInFo.setFileName(file01.getOriginalFilename());
		FileInFo selectfilename = fileService.selectfilename(fileInFo);
		if (selectfilename != null) {
			session.setAttribute("exis", "文件已经存在");
			return "redirect:/jsp/main/upload.jsp";
		} else {

			fileService.insert(fileInFo01);
			return "redirect:/jsp/main/index.jsp";
		}

	}

	public FileService getFileService() {
		return fileService;
	}

	@Resource(name = "fileServiceImp")
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	@RequestMapping(value = "/select.do", method = RequestMethod.POST)
	public void fileSelect(HttpServletResponse response, HttpServletRequest request, String value)
			throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			if (attributeNames.nextElement().equals("exis")) {
				session.removeAttribute("exis");
			}
		}

		if (value == null || value.equals("")) {
			response.sendRedirect("/webdisk/jsp/login.jsp");
		} else {
			response.setContentType("application/xml");
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				FileInFo fileInFo2 = new FileInFo();
				fileInFo2.setUserName(value);
				List<FileInFo> fileSelect = fileService.fileSelect(fileInFo2);
				writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				writer.write("<files>");
				for (FileInFo fileInFo : fileSelect) {
					writer.write("<file>");
					writer.write("<id>" + fileInFo.getId() + "</id>");
					writer.write("<name>" + URLEncoder.encode(fileInFo.getFileName(), "utf-8") + "</name>");
					writer.write("<size>" + URLEncoder.encode(fileInFo.getFileSize() + "字节", "utf-8") + "</size>");
					writer.write("<date>" + fileInFo.getFilaUploadDate() + "</date>");
					writer.write("</file>");
				}

				writer.write("</files>");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@RequestMapping(value = "/del.do", method = RequestMethod.GET)
	public String fileDel(FileInFo fileinfo, HttpServletRequest request) {
		FileInFo selectPath = fileService.selectPath(fileinfo);
		String filePath = selectPath.getFilePath();
		new File(filePath).delete();
		fileService.del(fileinfo);
		return "redirect:/jsp/main/index.jsp";

	}

	@RequestMapping(value = "/down.do", method = RequestMethod.GET)
	public void down(HttpServletResponse response, FileInFo fileinfo, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(false);
		String value = (String) session.getAttribute("user");
		fileinfo.setUserName(value);
		FileInFo selectPath = fileService.selectPath(fileinfo);

		InputStream bis = new BufferedInputStream(new FileInputStream(new File(selectPath.getFilePath())));
		// 假如以中文名下载的话 selectPath.getFilePath()
		int indexOf = selectPath.getFilePath().lastIndexOf("/");
		String substring = selectPath.getFilePath().substring(indexOf + 1, selectPath.getFilePath().length());
		// 转码，免得文件名中文乱码
		substring = URLEncoder.encode(substring, "UTF-8");
		// 设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=" + substring);
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		while ((len = bis.read()) != -1) {
			out.write(len);
			out.flush();
		}
		out.close();
	}

	@RequestMapping(value = "/size.do", method = RequestMethod.GET)
	public void size(HttpServletResponse response, HttpServletRequest request, String value)
			throws IOException, ServletException {

		if (value == null || value.equals("")) {
			// request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			response.sendRedirect("/webdisk/jsp/login.jsp");

		} else {
			response.setContentType("text/html;charset=gbk");
			PrintWriter writer = response.getWriter();

			FileInFo fileInFo2 = new FileInFo();
			fileInFo2.setUserName(value);
			List<FileInFo> selectSize = fileService.selectSize(fileInFo2);
			Long relust = 0L;
			for (FileInFo fileInFo : selectSize) {
				Long fileSize = fileInFo.getFileSize();
				relust += fileSize;
			}

			writer.write("文件总大小:" + (relust / 1024) + "KB");
		}
	}
}
