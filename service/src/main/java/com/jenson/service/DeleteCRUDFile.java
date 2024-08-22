package com.jenson.service;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class DeleteCRUDFile {

    static final String baseUrl = "./src/main/java/com/jenson/service";

    public static void main(String[] args) throws IOException {
        delete("clock", "Renewal");
    }

    private static void delete(String url, String name) throws IOException {
        deleteEntity(url, name);
        deleteVo(url + "/vo", name);
        deleteBo(url + "/bo", name);
        deleteSearchBO(url + "/searchBo", name);
        deleteRepository(url, name);
        deleteMapper(url,name);
        deleteService(url,name);
        deleteServiceImpl(url,name);
        deleteController(url,name);
    }

    private static void deleteEntity(String fileUrl, String name) throws IOException {
        String basePath = "/common/dto/";
        String filePath = baseUrl + basePath + fileUrl + "/" + name + ".java";
        deleteFile(filePath);
    }

    private static void deleteVo(String fileUrl, String name) throws IOException {
        String basePath = "/common/dto/";
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Vo.java";
        deleteFile(filePath);
    }

    private static void deleteBo(String fileUrl, String name) throws IOException {
        String basePath = "/common/dto/";
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Bo.java";
        deleteFile(filePath);
    }

    private static void deleteSearchBO(String fileUrl, String name) throws IOException {
        String basePath = "/common/dto/";
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "SearchBo.java";
        deleteFile(filePath);
    }

    private static void deleteRepository(String fileUrl, String name) throws IOException {
        String basePath = "/repository/";
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Repository.java";
        deleteFile(filePath);


    }

    private static void deleteMapper(String fileUrl, String name) throws IOException {
        String basePath = "/mapper/";
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Mapper.java";
        deleteFile(filePath);
    }

    private static void deleteService(String fileUrl, String name) throws IOException {
        String basePath = "/service/";
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Service.java";
        deleteFile(filePath);
    }

    private static void deleteServiceImpl(String fileUrl, String name) throws IOException {
        String basePath = "/service/";
        String filePath = baseUrl + basePath + fileUrl+ "/impl" + "/" + name + "ServiceImpl.java";
        deleteFile(filePath);


    }

    private static void deleteController(String fileUrl, String name) throws IOException {
        String basePath = "/controller/";
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Controller.java";
        deleteFile(filePath);
    }

    private static void deleteFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.delete();
    }


}
