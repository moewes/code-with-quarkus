package org.acme;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PortalBean {

  @ConfigProperty(name = "proxypath", defaultValue = "/")
  String path;

  private String page = "Hallo from Bean";

  @PostConstruct
  public void init() {
    page = "<!DOCTYPE html>"
        + "<html lang=\"en\">"
        + "<head>"

        + "</head>"
        + "<body>"
        + "<main>\n"
        + "  <a href=\"" + path + "portal\">Home</a>\n"
        + "  <a href=\"" + path + "portal/users\">Users</a>\n"
        + "  <div id=\"outlet\"></div>\n"
        + "</main>"
        + "<script src=\"" + path + "index.js\" type=\"module\"></script>"
        + "</body>"
        + "</html>";
  }

  public String getPortalPage() {
    return page;
  }
}
