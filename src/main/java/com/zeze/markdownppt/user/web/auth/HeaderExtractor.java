package com.zeze.markdownppt.user.web.auth;

import javax.servlet.http.HttpServletRequest;

public interface HeaderExtractor {
    String extract(HttpServletRequest request, String type);
}
