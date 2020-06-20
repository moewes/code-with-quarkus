package org.acme;

import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.extensions.HTTPIngressPath;
import io.fabric8.kubernetes.api.model.extensions.HTTPIngressPathBuilder;
import io.fabric8.kubernetes.api.model.extensions.HTTPIngressRuleValue;
import io.fabric8.kubernetes.api.model.extensions.HTTPIngressRuleValueBuilder;
import io.fabric8.kubernetes.api.model.extensions.Ingress;
import io.fabric8.kubernetes.api.model.extensions.IngressBackendBuilder;
import io.fabric8.kubernetes.api.model.extensions.IngressBuilder;
import io.fabric8.kubernetes.api.model.extensions.IngressRule;
import io.fabric8.kubernetes.api.model.extensions.IngressRuleBuilder;
import io.fabric8.kubernetes.api.model.extensions.IngressSpec;
import io.fabric8.kubernetes.api.model.extensions.IngressSpecBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pod")
public class ClusterResource {

  private final KubernetesClient kubernetesClient;

  @Inject
  public ClusterResource(KubernetesClient kubernetesClient) {
    this.kubernetesClient = kubernetesClient;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{namespace}")
  public List<Pod> pods(@PathParam("namespace") String namespace) {
    return kubernetesClient.pods().inNamespace(namespace).list().getItems();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("services/{namespace}")
  public List<Service> services(@PathParam("namespace") String namespace) {
    return kubernetesClient.services().inNamespace(namespace).list().getItems();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("ingress/{namespace}")
  public List<Ingress> ingress(@PathParam("namespace") String namespace) {

    HTTPIngressPath app1 = new HTTPIngressPathBuilder().withPath("/greeting")
        .withBackend(
            new IngressBackendBuilder().withNewServiceName("getting-started")
                .withServicePort(new IntOrString(8080)).build()).build();

    HTTPIngressRuleValue rule = new HTTPIngressRuleValueBuilder().withPaths(app1).build();

    IngressRule ingressRule = new IngressRuleBuilder().withHost("localhost").withHttp(rule).build();

    IngressSpec i = new IngressSpecBuilder().withRules(ingressRule).build();

    Ingress ingress = new IngressBuilder().withKind("Ingress")
        .withApiVersion("extensions/v1beta1")
        .withMetadata(
            new ObjectMetaBuilder().withName("vote-ingress").withNamespace("default").build())
        .withSpec(i).build();

    kubernetesClient.extensions().ingresses().createOrReplace(ingress);

    return kubernetesClient.extensions().ingresses().list().getItems();
  }

}

