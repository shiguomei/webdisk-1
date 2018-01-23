package org.webdisk.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.webdisk.pojo.FileInFo;
@Repository(value="fileMapper")
public interface FileMapper {
	void fileInsert(FileInFo fileInFo);
	
	List<FileInFo> fileSelect(FileInFo fileInFo);
	
	void del(FileInFo fileInFo);
	
	FileInFo selectPath(FileInFo fileInFo);
	
	List<FileInFo> selectSize(FileInFo fileInFo);
	
	FileInFo selectfilename(FileInFo fileInFo);
}
