package cn.springcloud.book.feign.controller;

import cn.springcloud.book.feign.service.HelloFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@SuppressWarnings("all")
@RestController
@RequestMapping("/fegin")
public class HelloFeignController {

    @Autowired
    private HelloFeignService helloFeignService;

    // 服务消费者对位提供的服务
    @GetMapping(value = "/search/github")
    public void searchGithubRepoByStr(@RequestParam("str") String queryStr, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");//可以保证输出给客户端的字符都是使用UTF-8编码的
        response.setContentType("application/json;charset=utf-8");//通知客户端使用UTF-8来解读响应数据

        String result = helloFeignService.searchRepo(queryStr);

        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(result);
            printWriter.flush();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    //http://localhost:8010/test/fegin/search/github?str=liuxiang
    /**
     * boot 1.4以上版本使用   server.servlet.context-path=/example
     * 以下版本使用           server.context-path=/example
     */
}
