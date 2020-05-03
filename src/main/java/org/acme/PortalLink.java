package org.acme;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortalLink {

  private String link;
  private String text;
}
