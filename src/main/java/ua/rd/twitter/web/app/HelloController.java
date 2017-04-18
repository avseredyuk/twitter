package ua.rd.twitter.web.app;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author andrii
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Spring MVC Controller");
        try (PrintWriter out = response.getWriter()) {
            out.println("<b>Hello from Hello Controller!</b></br>");                                  
        }       
    }

}
