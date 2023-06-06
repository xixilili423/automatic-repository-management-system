package org.openapitools.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-06T16:31:21.921210200+08:00[Asia/Shanghai]")

@Controller
@RequestMapping("${openapi..base-path:}")
public class CenterApiController implements CenterApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CenterApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
