package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@Configuration
@EnableWebMvc
public class IndexController extends WebMvcConfigurerAdapter
{
    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("user") == null)
        {
            request.getSession().setAttribute("user", "user");
            return new ModelAndView("login");
        }
        else
        {
            return new ModelAndView("index");
        }
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addRedirectViewController("/", "index");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/**").addResourceLocations("/");
    }
}
