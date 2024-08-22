package com.jenson.service;


import com.jenson.service.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class CRUDFile {

    static final String baseUrl = "./src/main/java/com/jenson/service";

    static final String baoUrl = "com.jenson.service";

    private static String entityUrl = "";
    private static String qEntityUrl = "";
    private static String voUrl = "";
    private static String boUrl = "";
    private static String searchBoUrl = "";
    private static String repositoryUrl = "";
    private static String mapperUrl = "";
    private static String serviceUrl = "";

    public static void main(String[] args) throws IOException {
        find("clock", "Renewal");

    }

    private static void find(String url, String name) throws IOException {
        findEntity(url, name);
        findVo(url + "/vo", name);
        findBo(url + "/bo", name);
        findSearchBO(url + "/searchBo", name);
        findRepository(url, name);
        findMapper(url,name);
        findService(url,name);
        findServiceImpl(url,name);
        findController(url,name);
    }

    private static void findEntity(String fileUrl, String name) throws IOException {
        String basePath = "/common/dto/";
        FileUtil.checkDirExists(baseUrl + basePath + fileUrl);
        String filePath = baseUrl + basePath + fileUrl + "/" + name + ".java";
        String bao = (baoUrl + basePath + fileUrl).replace("/", ".");
        String entity = "package " + bao + ";\n" +
                "import com.jenson.service.common.basic.SuperEntity;\n" +
                "import lombok.*;\n" +
                "import lombok.experimental.Accessors;\n" +
                "import javax.persistence.Entity;\n" +
                "@Getter\n" +
                "@Setter\n" +
                "@NoArgsConstructor\n" +
                "@Accessors(chain = true)\n" +
                "@Builder\n" +
                "@EqualsAndHashCode(callSuper = true)\n" +
                "@Entity\n" +
                "public class " + name + " extends SuperEntity {\n" +
                "}\n";
        entityUrl = bao+"."+name+";";
        qEntityUrl = bao+".Q"+name+";";
        setFile(filePath, entity);
    }

    private static void findVo(String fileUrl, String name) throws IOException {
        String basePath = "/common/dto/";
        FileUtil.checkDirExists(baseUrl + basePath + fileUrl);
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Vo.java";
        String bao = (baoUrl + basePath + fileUrl).replace("/", ".");
        String content = "package " + bao + ";\n" +
                "import lombok.*;\n" +
                "import lombok.experimental.Accessors;\n" +
                "import javax.persistence.Entity;\n" +
                "@Getter\n" +
                "@Setter\n" +
                "@AllArgsConstructor\n" +
                "@NoArgsConstructor\n" +
                "@Accessors(chain = true)\n" +
                "@Builder\n" +
                "public class " + name + "Vo{\n" +
                "    private Integer id;\n" +
                "}\n";
        voUrl = bao+"."+name+"Vo;";
        setFile(filePath, content);
    }

    private static void findBo(String fileUrl, String name) throws IOException {
        String basePath = "/common/dto/";
        FileUtil.checkDirExists(baseUrl + basePath + fileUrl);
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Bo.java";
        String bao = (baoUrl + basePath + fileUrl).replace("/", ".");
        String content = "package " + bao + ";\n" +
                "import lombok.*;\n" +
                "import lombok.experimental.Accessors;\n" +
                "import javax.persistence.Entity;\n" +
                "@Getter\n" +
                "@Setter\n" +
                "@AllArgsConstructor\n" +
                "@NoArgsConstructor\n" +
                "@Accessors(chain = true)\n" +
                "@Builder\n" +
                "public class " + name + "Bo{\n" +
                "    private Integer id;\n" +
                "}\n";
        boUrl = bao+"."+name+"Bo;";
        setFile(filePath, content);
    }

    private static void findSearchBO(String fileUrl, String name) throws IOException {
        String basePath = "/common/dto/";
        FileUtil.checkDirExists(baseUrl + basePath + fileUrl);
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "SearchBo.java";
        String bao = (baoUrl + basePath + fileUrl).replace("/", ".");
        String content = "package " + bao + ";\n" +
                "import lombok.*;\n" +
                "import lombok.experimental.Accessors;\n" +
                "import javax.persistence.Entity;\n" +
                "import com.jenson.service.common.basic.SuperSearchBo;\n" +
                "@Getter\n" +
                "@Setter\n" +
                "@NoArgsConstructor\n" +
                "@Accessors(chain = true)\n" +
                "@Builder\n" +
                "@EqualsAndHashCode(callSuper = true)\n" +
                "public class " + name + "SearchBo extends SuperSearchBo {\n" +
                "}\n";
        searchBoUrl = bao+"."+name+"SearchBo;";
        setFile(filePath, content);
    }

    private static void findRepository(String fileUrl, String name) throws IOException {
        String basePath = "/repository/";
        FileUtil.checkDirExists(baseUrl + basePath + fileUrl);
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Repository.java";
        log.info(filePath);
        String bao = (baoUrl + basePath + fileUrl).replace("/", ".");
        String content = "package "+bao+";\n"+
                "\n" +
                "import com.jenson.service.common.basic.SuperRepository;\n" +
                "import org.springframework.data.jpa.repository.JpaRepository;\n" +
                "import org.springframework.stereotype.Repository;\n" +
                "import "+entityUrl+"\n" +
                "\n" +
                "@Repository\n" +
                "public interface "+ name+"Repository extends SuperRepository<"+name+">, JpaRepository<"+ name +",Long> {\n" +
                "}";
        repositoryUrl = bao+"."+name+"Repository;";
        setFile(filePath, content);


    }

    private static void findMapper(String fileUrl, String name) throws IOException {
        String basePath = "/mapper/";
        FileUtil.checkDirExists(baseUrl + basePath + fileUrl);
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Mapper.java";
        String bao = (baoUrl + basePath + fileUrl).replace("/", ".");
        String content = "package "+bao+";\n"+
                "\n" +
                "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
                "import org.apache.ibatis.annotations.Mapper;\n" +
                "import "+entityUrl+"\n" +
                "\n" +
                "@Mapper\n" +
                "public interface "+ name+"Mapper extends BaseMapper<"+name+">{\n" +
                "}";
        mapperUrl = bao+"."+name+"Mapper;";
        setFile(filePath, content);
    }

    private static void findService(String fileUrl, String name) throws IOException {
        String basePath = "/service/";
        FileUtil.checkDirExists(baseUrl + basePath + fileUrl);
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Service.java";
        String bao = (baoUrl + basePath + fileUrl).replace("/", ".");
        String content = "package "+bao+";\n"+
                "\n" +
                "import com.jenson.service.common.basic.SuperService;\n"+
                "import "+boUrl+"\n" +
                "import "+searchBoUrl+"\n" +
                "import "+voUrl+"\n"+
                "public interface "+name+"Service extends SuperService<"+name+"Vo, "+name+"Bo, "+name+"SearchBo> {\n" +
                "}";
        serviceUrl = bao+"."+name+"Service;";
        setFile(filePath, content);
    }

    private static void findServiceImpl(String fileUrl, String name) throws IOException {
        String basePath = "/service/";
        FileUtil.checkDirExists(baseUrl + basePath + fileUrl + "/impl");
        String filePath = baseUrl + basePath + fileUrl+ "/impl" + "/" + name + "ServiceImpl.java";
        String bao = (baoUrl + basePath + fileUrl + "/impl").replace("/", ".");
        String _name = name.toLowerCase();
        String content = "package "+bao+";\n"+
                "\n" +
                "import com.jenson.service.common.basic.SuperServiceImpl;\n" +
                "import "+boUrl+"\n" +
                "import "+searchBoUrl+"\n" +
                "import "+voUrl+"\n"+
                "import "+entityUrl+"\n"+
                "import "+qEntityUrl+"\n"+
                "import "+mapperUrl+"\n"+
                "import "+repositoryUrl+"\n"+
                "import "+serviceUrl+"\n" +
                "\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import com.querydsl.jpa.impl.JPAQueryFactory;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "import java.util.Objects;\n" +
                "import java.util.stream.Collectors;\n" +
                "import org.springframework.data.domain.Page;\n" +
                "import org.springframework.data.domain.PageImpl;\n" +
                "import com.querydsl.core.types.dsl.BooleanExpression;\n" +
                "import com.jenson.service.common.exception.NotFoundException;\n" +
                "import com.querydsl.jpa.impl.JPAQuery;\n" +
                "import com.jenson.service.util.ObjectUtil;\n" +
                "\n" +
                "import javax.annotation.Resource;\n"+
                "\n"+
                "@Service\n"+
                "public class "+name+"ServiceImpl extends SuperServiceImpl<"+name+"Mapper,"+name+","+name+"Vo, "+name+"SearchBo> implements "+name+"Service{\n" +
                "    @Resource\n" +
                "    JPAQueryFactory jpaQueryFactory;\n" +
                "\n" +
                "    @Resource\n" +
                "    "+name+"Mapper "+_name+"Mapper;\n" +
                "\n" +
                "    @Resource\n" +
                "    "+name+"Repository "+_name+"Repository;\n" +
                "\n" +
                "    Q"+name+" q"+name+" = Q"+name+"."+_name+";  \n" +
                "\n" +
                "@Override\n" +
                "    public Page<"+name+"Vo> pageAll("+name+"SearchBo searchBo) {\n" +
                "        JPAQuery<"+name+"> jpaQuery = jpaQueryFactory\n" +
                "                .selectFrom(q"+name+")\n" +
                "                .where(getWhere(searchBo));\n" +
                "\n" +
                "        List<"+name+"> "+_name+"List = jpaQuery\n" +
                "                .offset(searchBo.getPageRequest().getOffset())\n" +
                "                .limit(searchBo.getPageRequest().getPageSize())\n" +
                "                .fetch();\n" +
                "\n" +
                "        Page<"+name+"Vo> page = new PageImpl<>(\n" +
                "                ObjectUtil.copyNotNullPropertiesList("+_name+"List, "+name+"Vo.class),\n" +
                "                searchBo.getPageRequest(),\n" +
                "                jpaQuery.fetchCount()\n" +
                "        );\n" +
                "\n" +
                "        supplement(page);\n" +
                "        return page;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<"+name+"Vo> listAll("+name+"SearchBo searchBo) {\n" +
                "        List<"+name+"> "+_name+"List = jpaQueryFactory\n" +
                "                .selectFrom(q"+name+")\n" +
                "                .where(getWhere(searchBo))\n" +
                "                .fetch();\n" +
                "\n" +
                "        List<"+name+"Vo> "+_name+"VoList = ObjectUtil.copyNotNullPropertiesList("+_name+"List, "+name+"Vo.class);\n" +
                "        supplement("+_name+"VoList);\n" +
                "        return "+_name+"VoList;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public "+name+"Vo get(Integer id) {\n" +
                "        "+name+" "+_name+" = "+_name+"Repository.findById(id).orElseThrow(() -> NotFoundException.notFound(id, \"权限\"));\n" +
                "        "+name+"Vo "+_name+"Vo = ObjectUtil.copyNotNullProperties("+_name+", "+name+"Vo.class);\n" +
                "        supplement("+_name+"Vo);\n" +
                "        return "+_name+"Vo;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public "+name+"Vo edit("+name+"Bo "+_name+"Bo) {\n" +
                "        "+name+" "+_name+" = "+_name+"Repository.findById("+_name+"Bo.getId()).orElse(new "+name+"());\n" +
                "        ObjectUtil.copyNotNullProperties("+_name+"Bo, "+_name+");\n" +
                "        "+_name+" = "+_name+"Repository.save("+_name+");\n" +
                "        return ObjectUtil.copyNotNullProperties("+_name+", "+name+"Vo.class);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<"+name+"Vo> edit(List<"+name+"Bo> "+_name+"Bos) {\n" +
                "        List<"+name+"> "+_name+"List = "+_name+"Repository.findByIdIn("+_name+"Bos.stream().map("+name+"Bo::getId).collect(Collectors.toList()));\n" +
                "        List<"+name+"> "+_name+"EntityList = new ArrayList<>();\n" +
                "        for ("+name+"Bo "+_name+"Bo : "+_name+"Bos) {\n" +
                "            "+name+" "+_name+" = "+_name+"List.stream().filter(_"+_name+" -> Objects.equals("+_name+"Bo.getId(), _"+_name+".getId())).findFirst().orElse(new "+name+"());\n" +
                "            ObjectUtil.copyNotNullProperties("+_name+"Bo, "+_name+");\n" +
                "            "+_name+"EntityList.add("+_name+");\n" +
                "        }\n" +
                "        "+_name+"EntityList = "+_name+"Repository.saveAll("+_name+"EntityList);\n" +
                "        return ObjectUtil.copyNotNullPropertiesList("+_name+"EntityList, "+name+"Vo.class);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public "+name+"Vo del(Integer id) {\n" +
                "        "+name+" "+_name+" = "+_name+"Repository.findById(id).orElseThrow(() -> NotFoundException.notFound(id, \"权限\"));\n" +
                "        "+_name+".setDeleted(true);\n" +
                "        "+_name+" = "+_name+"Repository.save("+_name+");\n" +
                "        return ObjectUtil.copyNotNullProperties("+_name+", "+name+"Vo.class);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<"+name+"Vo> del("+name+"SearchBo searchBo) {\n" +
                "        List<"+name+"> "+_name+"List = "+_name+"Repository.findByIdIn(searchBo.getIdList());\n" +
                "        "+_name+"List = "+_name+"List.stream().peek(it -> it.setDeleted(true)).collect(Collectors.toList());\n" +
                "        "+_name+"List = "+_name+"Repository.saveAll("+_name+"List);\n" +
                "        return ObjectUtil.copyNotNullPropertiesList("+_name+"List, "+name+"Vo.class);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void supplement(List<"+name+"Vo> "+_name+"Vos) {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public BooleanExpression getWhere("+name+"SearchBo searchBo) {\n" +
                "        return q"+name+".deleted.eq(false);\n" +
                "    }" +
                "\n" +
                "\n" +
                "}";
        setFile(filePath, content);


    }

    private static void findController(String fileUrl, String name) throws IOException {
        String basePath = "/controller/";
        FileUtil.checkDirExists(baseUrl + basePath + fileUrl);
        String filePath = baseUrl + basePath + fileUrl + "/" + name + "Controller.java";
        String bao = (baoUrl + basePath + fileUrl).replace("/", ".");
        String content = "package "+bao+";\n"+
                "\n" +
                "import com.jenson.service.common.basic.SuperController;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "import "+boUrl+"\n" +
                "import "+searchBoUrl+"\n" +
                "import "+voUrl+"\n"+
                "import "+serviceUrl+"\n" +
                "@RestController\n" +
                "@RequestMapping(\"/api/"+name.toLowerCase()+"\")\n" +
                "public class "+name+"Controller extends SuperController<"+name+"Vo, "+name+"Bo, "+name+"SearchBo, "+name+"Service> {\n" +
                "\n" +
                "}";
        setFile(filePath, content);


    }

    private static void setFile(String filePath, String content) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write("");
        fileWriter.append(content);
        fileWriter.flush();
        fileWriter.close();
    }


}
