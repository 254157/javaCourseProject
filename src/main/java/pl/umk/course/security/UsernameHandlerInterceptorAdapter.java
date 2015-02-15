package pl.umk.course.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by emagdnim on 2015-02-14.
 */
@Component
public class UsernameHandlerInterceptorAdapter  extends HandlerInterceptorAdapter {



    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
            modelAndView.getModelMap().
                    addAttribute("username",
                            SecurityContextHolder.getContext().getAuthentication().getName());
        }
    }
}