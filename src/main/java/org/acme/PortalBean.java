package org.acme;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PortalBean {

  private String page = "Hallo from Bean";

  @PostConstruct
  public void init() {
    page = "<!DOCTYPE html>"
        + "<html lang=\"en\">"
        + "<head>"

        + "</head>"
        + "<body>"
        + "<main>\n"
        + "  <a href=\"/portal\">Home</a>\n"
        + "  <a href=\"/portal/users\">Users</a>\n"
        + "  <div id=\"outlet\"></div>\n"
        + "</main>"
        + "<script src=\"/index.js\" type=\"module\"></script>"
        + "</body>"
        + "</html>";
  }

  public String getPortalPage() {
    return page;
  }
}
