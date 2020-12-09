package com.tangp.algo.pieces;

public class RegTest {
    private static final String REG = ".*(\\.png|\\.gif|\\.css|\\.js|\\.svg|\\.html)$";

    public static void main(String[] args) {
        System.out.println("/static/bd9cb5d1/images/24x24/new-package.png".matches(REG));
        System.out.println("/static/bd9cb5d1/images/24x24/new-package.gif".matches(REG));
        System.out.println("/static/bd9cb5d1/images/24x24/new-package.jsp".matches(REG));
        System.out.println("/static/bd9cb5d1/images/24x24/new-package.html".matches(REG));
    }
}
