package com.kfwy.park.common.easyexcel.importfile;


import com.kfwy.park.common.easyexcel.importfile.domain.common.ImportResult;
import com.kfwy.park.common.easyexcel.importfile.exception.FileImportException;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Administrator on 2015/11/19.
 */
public abstract class FileImportor {

    public abstract ImportResult<Object> getImportResult(File file, String fileName) throws FileImportException;

    public abstract ImportResult<Object> getImportResult(InputStream inputStream, String fileName) throws FileImportException;

}
