package com.expercise.controller

import spock.lang.Specification
import spock.lang.Unroll

class RedirectUtilsSpec extends Specification {

    @Unroll
    def "should redirect #path to #redirectedPath"() {
        expect:
        RedirectUtils.redirectTo(path).getViewName() == redirectedPath

        where:
        path               | redirectedPath
        "/"                | "redirect:/"
        "/login"           | "redirect:/login"
        "/login?newmember" | "redirect:/login?newmember"
        "/404"             | "redirect:/404"
        "/403"             | "redirect:/403"
    }

}
