package ai.grakn.graknmodule.test.fixture;

import ai.grakn.graknmodule.GraknModule;
import ai.grakn.graknmodule.http.Before;
import ai.grakn.graknmodule.http.BeforeHttpEndpoint;
import ai.grakn.graknmodule.http.HttpEndpoint;
import ai.grakn.graknmodule.http.HttpMethods;
import ai.grakn.graknmodule.http.Request;
import ai.grakn.graknmodule.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.fortsoft.pf4j.Extension;
import ro.fortsoft.pf4j.Plugin;
import ro.fortsoft.pf4j.PluginWrapper;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *     Dummy plugin
 * </p>
 *
 * @author Ganeshwara Herawan Hananda
 */

public class DummyModule extends Plugin {
    public DummyModule(PluginWrapper pluginWrapper) {
        super(pluginWrapper);
    }

    @Extension
    public static class Dummy implements GraknModule {
        public String getGraknModuleName() {
            return "dummy-module";
        }

        @Override
        public List<HttpEndpoint> getHttpEndpoints() {
            return Arrays.asList(dummy);
        }

        @Override
        public List<BeforeHttpEndpoint> getBeforeHttpEndpoints() {
            return Arrays.asList(beforeHttpEndpoint);
        }
    }

    private static HttpEndpoint dummy = new HttpEndpoint() {
        @Override
        public HttpMethods.HTTP_METHOD getHttpMethod() {
            return HttpMethods.HTTP_METHOD.GET;
        }

        @Override
        public String getEndpoint() {
            return "/dummy-endpoint";
        }

        @Override
        public Response getRequestHandler(Request request) {
            return new Response(200, "dummy-endpoint-response");
        }
    };

    private static BeforeHttpEndpoint beforeHttpEndpoint = new BeforeHttpEndpoint() {
        private final Logger LOG = LoggerFactory.getLogger(BeforeHttpEndpoint.class);

        @Override
        public String getUrlPattern() {
            return "/before-http-endpoint-pattern";
        }

        @Override
        public Before getBeforeHttpEndpoint(Request request) {
            return Before.allow();
        }
    };
}

