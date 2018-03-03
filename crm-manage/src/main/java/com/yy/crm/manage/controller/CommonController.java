package com.yy.crm.manage.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yy.crm.security.core.properties.SecurityProperties;
import com.yy.crm.utils.YYUtil;
import com.yy.crm.utils.shell.ShellResult;
import com.yy.crm.utils.shell.ShellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author luyuanyuan on 2018/3/2.
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    private final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    private final ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    private SecurityProperties securityProperties;
    //@Autowired
    //private MailUtil mailUtil;
    private static final String EOL = "\n";
    private static final int SIGNATURE_LENGTH = 45;

    private static final String PROJECT_NAME_VUE_ADMIN = "vue-admin";

    @PostMapping("/pushCallback")
    public ResponseEntity<String> pushCallback(@RequestHeader("X-Hub-Signature") String signature,
                                       @RequestBody String payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Webhook-Version", "1.0.0");
        if (signature == null) {
            return new ResponseEntity<>("No signature given." + EOL, headers,
                    HttpStatus.BAD_REQUEST);
        }
        String computed = String.format("sha1=%s", HmacUtils.hmacSha1Hex(securityProperties.getOauth2().getJwtSigningKey(), payload));
        boolean invalidLength = signature.length() != SIGNATURE_LENGTH;

        if (invalidLength || !YYUtil.constantTimeCompare(signature, computed)) {
            return new ResponseEntity<>("Invalid signature." + EOL, headers,
                    HttpStatus.UNAUTHORIZED);
        }

        JsonObject payloadObj = new JsonParser().parse(payload).getAsJsonObject();
        String projectName = payloadObj.get("repository").getAsJsonObject().get("name").getAsString();
        singleThreadPool.execute(() -> {
            ShellResult shellResult;
            if(PROJECT_NAME_VUE_ADMIN.equals(projectName)){
                shellResult = ShellUtil.exceCommand("/home/crm/vue-admin.sh");
            }else {
                shellResult = ShellUtil.exceCommand("/home/crm/deploy.sh");
            }
            if(shellResult != null && shellResult.getCode() != 0){
                //mailUtil.sendHtmlMessage(email,"项目启动错误",shellResult.getErrorInfoList().toString());
                log.error(projectName+"---项目启动错误，错误信息："+shellResult.getErrorInfoList().toString());
            }
        });

        int bytes = payload.getBytes().length;
        StringBuilder message = new StringBuilder();
        message.append("Signature OK.").append(EOL);
        message.append(String.format("Received %d bytes.", bytes)).append(EOL);
        return new ResponseEntity<>(message.toString(), headers, HttpStatus.OK);

    }

    @GetMapping("/redirect")
    public void redir(String redirect, String code, String state, HttpServletResponse response,
                      HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException, ServletException {
//        response.setStatus(301);
//        String aaa = "window.opener.location.href = 'http://192.168.1.55:8999/login#code=" + code+"';";
//
////        String bbb = "<script>"+aaa+"</script>";
//        String bbb = "<!doctype html>" +
//                "<html><body><script>"+aaa+"window.close();</script></body></html>";
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().write(bbb);
        response.sendRedirect(redirect+"?code="+code);
        return;

    }

}
