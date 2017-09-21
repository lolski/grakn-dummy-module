package ai.grakn.graknmodule.test.fixture;

import ai.grakn.graknmodule.GraknModule;
import ai.grakn.graknmodule.http.HttpBeforeFilterResult;
import ai.grakn.graknmodule.http.HttpBeforeFilter;
import ai.grakn.graknmodule.http.HttpEndpoint;
import ai.grakn.graknmodule.http.HttpMethods;
import ai.grakn.graknmodule.http.HttpRequest;
import ai.grakn.graknmodule.http.HttpResponse;
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

        public List<HttpEndpoint> getHttpEndpoints() {
            return Arrays.asList(dummy);
        }

        public List<HttpBeforeFilter> getHttpBeforeFilters() {
            return Arrays.asList(beforeHttpEndpoint);
        }
    }

    private static HttpEndpoint dummy = new HttpEndpoint() {
        public HttpMethods.HTTP_METHOD getHttpMethod() {
            return HttpMethods.HTTP_METHOD.GET;
        }

        public String getEndpoint() {
            return "/dummy-endpoint";
        }

        public HttpResponse getRequestHandler(HttpRequest request) {
            return new HttpResponse(200, "dummy-endpoint-response");
        }
    };

    private static HttpBeforeFilter beforeHttpEndpoint = new HttpBeforeFilter() {
        public String getUrlPattern() {
            return "/before-http-endpoint-pattern";
        }

        public HttpBeforeFilterResult getHttpBeforeFilter(HttpRequest request) {
            return HttpBeforeFilterResult.allowRequest();
        }
    };
}

