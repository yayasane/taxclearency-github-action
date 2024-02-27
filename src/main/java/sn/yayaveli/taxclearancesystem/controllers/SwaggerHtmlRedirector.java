package sn.yayaveli.taxclearancesystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Redirects requests for / to the /swagger-ui/ endpoint.
 */
@ApiIgnore
@RestController
public class SwaggerHtmlRedirector {
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/swagger-ui/");
    }
}