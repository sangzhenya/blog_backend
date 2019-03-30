package com.xinyue.blog.controller;

import com.xinyue.blog.constant.MessageEnum;
import com.xinyue.blog.model.CustomerFile;
import com.xinyue.blog.service.*;
import com.xinyue.blog.service.security.CustomUserServiceImpl;
import com.xinyue.blog.vo.ArticleVO;
import com.xinyue.blog.vo.CategoryVO;
import com.xinyue.blog.vo.MessageVO;
import com.xinyue.blog.vo.TagVO;
import com.xinyue.blog.vo.requestVO.RequestVO;
import com.xinyue.blog.vo.requestVO.SearchVO;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * @author sangz
 */
@RestController
public class AdminController {
    private static String filePath = "file/";
//    private static String filePath = "D://test//";
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final CustomUserServiceImpl customUserServiceImpl;
    private final ArticleService articleService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final MessageService messageService;
    private final CustomerFileService customerFileService;

    public AdminController(CustomUserServiceImpl customUserServiceImpl, ArticleService articleService,
                           CategoryService categoryService, TagService tagService,
                           MessageService messageService, CustomerFileService customerFileService) {
        this.customUserServiceImpl = customUserServiceImpl;
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.messageService = messageService;
        this.customerFileService = customerFileService;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/info")
    public String admin(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        logger.info("Start to visit index page, Log test " + Calendar.getInstance().getTime());
        logger.info("Current user:: " + principal.getName());
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        logger.info("Current user:: " + username);

        boolean adminRoleAuth = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        logger.info("Have Role Admin? :: " + adminRoleAuth);

        boolean adminAuth = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
        logger.info("Have Auth Admin? :: " + adminAuth);

        return "Hello World";
    }

    @ResponseBody
    @RequestMapping("/admin/search/article")
    public ArticleVO searchArticle(@RequestBody SearchVO searchVO) {
        return articleService.searchArticle(searchVO.getSearchKey());
    }

    @ResponseBody
    @RequestMapping("/admin/search/category")
    public List<CategoryVO> searchCategory(@RequestBody SearchVO searchVO) {
        return categoryService.searchCategory(searchVO.getSearchKey());
    }

    @ResponseBody
    @RequestMapping("/admin/search/tag")
    public List<TagVO> searchTag(@RequestBody SearchVO searchVO) {
        return tagService.searchTag(searchVO.getSearchKey());
    }

    @ResponseBody
    @RequestMapping("/admin/save/article")
    public String saveArticle(@RequestBody ArticleVO article) {
        return articleService.saveArticle(article);
    }

    @ResponseBody
    @RequestMapping("/admin/delete/article")
    public String deleteArticle(@RequestBody RequestVO requestVO) {
        return articleService.deleteArticle(requestVO.getId());
    }

    @ResponseBody
    @RequestMapping("/admin/list/category")
    public List<CategoryVO> liseCategory() {
        return categoryService.listCategory();
    }

    @ResponseBody
    @RequestMapping("/admin/list/tag")
    public List<TagVO> searchTag() {
        return tagService.listTag();
    }

    @ResponseBody
    @RequestMapping("/admin/save/category")
    public String saveCategory(@RequestBody RequestVO requestVO) {
        return categoryService.saveCategory(requestVO.getName());
    }

    @ResponseBody
    @RequestMapping("/admin/save/tag")
    public String saveTag(@RequestBody RequestVO requestVO) {
        return tagService.saveTag(requestVO.getName());
    }

    @ResponseBody
    @RequestMapping(value = "/admin/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        File dest = new File(filePath + UUID.randomUUID() + suffixName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/batch/upload", method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request) {
        String returnFileName = "";
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String fileName = file.getOriginalFilename();
                    returnFileName = UUID.randomUUID()
                            + fileName.substring(fileName.lastIndexOf("."));
                    File dest = new File(filePath + returnFileName);
                    FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnFileName;
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @RequestMapping("/create/user")
    public String createUser(HttpServletRequest request) {
        request.getUserPrincipal();
        customUserServiceImpl.createUser("xinyue", "xinyue", "ROLE_SPRING_BOOT_ADMIN");
        return "Create Success";
    }

    @ResponseBody
    @RequestMapping("/admin/list/message")
    public List<MessageVO> listMessage() {
        return messageService.listMessage();
    }

    @ResponseBody
    @RequestMapping("/admin/save/message")
    public MessageVO saveMessage(@RequestBody MessageVO messageVO) {
        return messageService.saveMessage(messageVO);
    }

    @ResponseBody
    @RequestMapping("/admin/search/message")
    public MessageVO saveMessage(@RequestBody RequestVO requestVO) {
        return messageService.getMessage(requestVO.getId());
    }

    @ResponseBody
    @RequestMapping("/admin/delete/message")
    public String deleteMessage(@RequestBody RequestVO requestVO) {
        return messageService.deleteMessage(requestVO.getId());
    }

    @RequestMapping("/admin/download")
    public String downloadFile(@RequestBody RequestVO requestVO, HttpServletResponse response) {
        CustomerFile customerFile = customerFileService.getCustomerFile(requestVO.getId());
        if (customerFile != null) {
            File file = new File(filePath, customerFile.getFilePath());
            if (file.exists()) {
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment;fileName=" + customerFile.getFileName());
                try {
                    FileUtils.copyFile(file, response.getOutputStream());
                    response.flushBuffer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return MessageEnum.SUCCESS.getDesc();
        }
        return MessageEnum.ERROR.getDesc();
    }
}
