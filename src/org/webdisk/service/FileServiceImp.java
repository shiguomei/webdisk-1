package org.webdisk.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.webdisk.mapper.FileMapper;
import org.webdisk.pojo.FileInFo;

@Service(value = "fileServiceImp")
public class FileServiceImp implements FileService {
	private FileMapper fileMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.webdisk.service.FileService#insert(org.webdisk.pojo.FileInFo)
	 */
	@Override
	public void insert(FileInFo fileInFo) {
		fileMapper.fileInsert(fileInFo);
	}

	public FileMapper getFileMapper() {
		return fileMapper;
	}

	@Resource(name = "fileMapper")
	public void setFileMapper(FileMapper fileMapper) {
		this.fileMapper = fileMapper;
	}

	@Override
	public List<FileInFo> fileSelect(FileInFo fileInFo) {
		List<FileInFo> fileSelect = fileMapper.fileSelect(fileInFo);
		return fileSelect;
	}

	@Override
	public void del(FileInFo fileInFo) {
		fileMapper.del(fileInFo);

	}

	@Override
	public FileInFo selectPath(FileInFo fileInFo) {

		return fileMapper.selectPath(fileInFo);
	}
	
	@Override
	public List<FileInFo> selectSize(FileInFo fileInFo) {
		List<FileInFo> selectSize = fileMapper.selectSize(fileInFo);
		return selectSize;
	}

	@Override
	public FileInFo selectfilename(FileInFo fileInFo) {
		
		return fileMapper.selectfilename(fileInFo);
	}



}
