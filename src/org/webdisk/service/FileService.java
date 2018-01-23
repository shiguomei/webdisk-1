package org.webdisk.service;

import java.util.List;

import org.webdisk.pojo.FileInFo;

public interface FileService {

	void insert(FileInFo fileInFo);

	void del(FileInFo fileInFo);

	FileInFo selectPath(FileInFo fileInFo);

	List<FileInFo> fileSelect(FileInFo fileInFo);

	List<FileInFo> selectSize(FileInFo fileInFo);
	
	FileInFo selectfilename(FileInFo fileInFo);
}