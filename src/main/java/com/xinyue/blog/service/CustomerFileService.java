package com.xinyue.blog.service;

import com.xinyue.blog.dao.FileRepository;
import com.xinyue.blog.model.CustomerFile;
import com.xinyue.blog.model.Message;
import com.xinyue.blog.utils.CollectionUtils;
import com.xinyue.blog.utils.NumberUtils;
import com.xinyue.blog.vo.CustomerFileVO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sangz
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class CustomerFileService {
    private final FileRepository fileRepository;

    public CustomerFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<CustomerFileVO> convertCustomerFileList(Set<CustomerFile> customerFiles) {
        List<CustomerFileVO> customerFileVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(customerFiles)) {
            for (CustomerFile customerFile : customerFiles) {
                CustomerFileVO customerFileVO = convertCustomerFile(customerFile);
                if (customerFileVO != null) {
                    customerFileVOList.add(customerFileVO);
                }
            }
        }
        return customerFileVOList;
    }

    private CustomerFileVO convertCustomerFile(CustomerFile customerFile) {
        if (customerFile != null) {
            CustomerFileVO fileVO = new CustomerFileVO();
            fileVO.setId(customerFile.getId());
            fileVO.setFileName(customerFile.getFileName());
            fileVO.setFileType(customerFile.getFileType());
            fileVO.setFilePath(customerFile.getFilePath());
            fileVO.setFileSize(customerFile.getFileSize());
            return fileVO;
        }
        return null;
    }

    public Set<CustomerFile> convertCustomerFileVOList(List<CustomerFileVO> files, Message message) {
        Set<CustomerFile> customerFileSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(files)) {
            Set<Integer> existFileIdSet = new HashSet<>();
            for (CustomerFileVO file : files) {
                existFileIdSet.add(file.getId());
            }

            Set<Integer> removeFileIdSet = new HashSet<>();
            Set<CustomerFile> existFiles = message.getCustomerFiles();
            if (!CollectionUtils.isEmpty(existFiles)) {
                for (CustomerFile existFile : existFiles) {
                    if (!existFileIdSet.contains(existFile.getId())) {
                        removeFileIdSet.add(existFile.getId());
                    }
                }
            }

            List<CustomerFile> removeCustomerFiles = new ArrayList<>();
            for (Integer removeFiledId : removeFileIdSet) {
                fileRepository.findById(removeFiledId).ifPresent(removeCustomerFiles::add);
            }
            fileRepository.deleteAll(removeCustomerFiles);

            for (CustomerFileVO fileVO : files) {
                if (fileVO != null) {
                    CustomerFile file;
                    if (!NumberUtils.isEmptyInt(fileVO.getId())) {
                        file = saveCustomerFile(fileVO, fileRepository.findById(fileVO.getId()), message);
                    } else {
                        file = saveCustomerFile(fileVO, null, message);
                    }
                    customerFileSet.add(file);
                }
            }
        }
        return customerFileSet;
    }

    private CustomerFile saveCustomerFile(CustomerFileVO fileVO, CustomerFile file, Message message) {
        if (file == null) {
            file = new CustomerFile();
        }
        file.setFileName(fileVO.getFileName());
        file.setFileType(fileVO.getFileType());
        file.setFilePath(fileVO.getFilePath());
        file.setFileSize(fileVO.getFileSize());
        file.setMessage(message);
        fileRepository.save(file);
        return file;
    }

    public void deleteMessageFiles(Message message) {
        if (message != null && !CollectionUtils.isEmpty(message.getCustomerFiles())) {
            fileRepository.deleteAll(message.getCustomerFiles());
        }
    }

    public CustomerFile getCustomerFile(int id) {
        if (!NumberUtils.isEmptyInt(id)) {
            return fileRepository.findById(id);
        }
        return null;
    }
}
